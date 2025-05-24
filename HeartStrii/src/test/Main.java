package test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import carte.Carte;
import carte.Serviteur;
import carte.Sort;
import carte.Arme;
import partie.Combat;
import partie.Deck;
import partie.Joueur;
import partie.Hero;
import partie.TypeHero;
import Outils.ChargementCartes;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Création des joueurs
        System.out.println("* Création du joueur 1");
        System.out.print("Entrez le nom du joueur 1 : ");
        String nomJoueur1 = scanner.nextLine();
        TypeHero typeHero1 = choisirHero(scanner);
        Joueur joueur1 = new Joueur(nomJoueur1, new Hero(typeHero1.toString(), typeHero1));

        System.out.println("\n* Création du joueur 2");
        System.out.print("Entrez le nom du joueur 2 : ");
        String nomJoueur2 = scanner.nextLine();
        TypeHero typeHero2 = choisirHero(scanner);
        Joueur joueur2 = new Joueur(nomJoueur2, new Hero(typeHero2.toString(), typeHero2));

        System.out.println("\n+ Les deux joueurs ont été créés !");
        System.out.println(joueur1.getPseudo() + " joue le héros " + joueur1.getHero().getNom());
        System.out.println(joueur2.getPseudo() + " joue le héros " + joueur2.getHero().getNom());

        // On prépare les decks et on distribue les cartes de départ
        initialiserDecks(joueur1, joueur2);

        System.out.println("\n+ Distribution des cartes de départ...");
        System.out.println(joueur1.getPseudo() + " pioche 3 cartes.");
        for (int i = 0; i < 3; i++) joueur1.piocherCarte();
        System.out.println(joueur2.getPseudo() + " pioche 4 cartes.");
        for (int i = 0; i < 4; i++) joueur2.piocherCarte();

        // Boucle principale du jeu
        while (!joueur1.getHero().estMort() && !joueur2.getHero().estMort()) {
            jouerUnTour(scanner, joueur1, joueur2);
            if (joueur2.getHero().estMort()) break;
            jouerUnTour(scanner, joueur2, joueur1);
        }

        // Affichage du gagnant
        System.out.println("\n+ Fin du combat !");
        if (joueur1.getHero().estMort()) {
            System.out.println("! " + joueur2.getPseudo() + " a gagné !");
        } else if (joueur2.getHero().estMort()) {
            System.out.println("! " + joueur1.getPseudo() + " a gagné !");
        } else {
            System.out.println("= Match nul !");
        }

        scanner.close();
    }

    // Un tour de jeu pour un joueur
    public static void jouerUnTour(Scanner scanner, Joueur joueur, Joueur adversaire) {
        System.out.println("\n# TOUR DE " + joueur.getPseudo());
        joueur.getHero().augmenterMana();
        joueur.piocherCarte();

        // Ici le joueur peut jouer une carte de sa main
        jouerCarteDepuisMain(scanner, joueur, adversaire);

        // Phase d'attaque avec les serviteurs
        if (joueur.getPlateau().getServiteurs().isEmpty()) {
            System.out.println("- Aucun serviteur à attaquer.");
        } else {
            System.out.println("\n> " + joueur.getPseudo() + ", choisis un serviteur pour attaquer ou tape 0 pour passer :");
            joueur.getPlateau().afficherPlateau();

            int atk = -1;
            while (atk < 0 || atk >= joueur.getPlateau().getServiteurs().size()) {
                System.out.print("Numéro du serviteur : ");
                if (scanner.hasNextInt()) {
                    atk = scanner.nextInt() - 1;
                    scanner.nextLine();
                    if (atk < -1 || atk >= joueur.getPlateau().getServiteurs().size()) {
                        System.out.println("x Numéro hors plage, recommence.");
                    }
                } else {
                    System.out.println("x Mets un nombre entier stp.");
                    scanner.nextLine();
                }
            }

            if (atk == -1) {
                System.out.println(joueur.getPseudo() + " passe la phase d'attaque.");
            } else {
                Serviteur attaquant = joueur.getPlateau().getServiteurs().get(atk);

                // Si l'adversaire n'a pas de serviteur, on tape direct le héros
                if (adversaire.getPlateau().getServiteurs().isEmpty()) {
                    adversaire.getHero().recevoirDegats(attaquant.getAttaque());
                    System.out.println("> " + attaquant.getNom() + " attaque le héros " + adversaire.getHero().getNom() + " pour " + attaquant.getAttaque() + " dégâts.");
                    System.out.println("♥ PV du héros " + adversaire.getHero().getNom() + " : " + adversaire.getHero().getPv());
                } else {
                    // Sinon, on choisit une cible sur le plateau adverse
                    System.out.println("Cible sur le plateau de " + adversaire.getPseudo() + " :");
                    adversaire.getPlateau().afficherPlateau();

                    int cible = -1;
                    while (cible < 0 || cible >= adversaire.getPlateau().getServiteurs().size()) {
                        System.out.print("Numéro de la cible : ");
                        if (scanner.hasNextInt()) {
                            cible = scanner.nextInt() - 1;
                            scanner.nextLine();
                            if (cible < 0 || cible >= adversaire.getPlateau().getServiteurs().size()) {
                                System.out.println("x Numéro hors plage, recommence.");
                            }
                        } else {
                            System.out.println("x Mets un nombre entier stp.");
                            scanner.nextLine();
                        }
                    }

                    Serviteur defenseur = adversaire.getPlateau().getServiteurs().get(cible);
                    Combat.lancerCombat(attaquant, defenseur);

                    // On enlève les serviteurs morts du plateau
                    if (attaquant.estMort()) joueur.getPlateau().getServiteurs().remove(attaquant);
                    if (defenseur.estMort()) adversaire.getPlateau().getServiteurs().remove(defenseur);
                }
            }
        }

        // Ici je propose d'utiliser le pouvoir spécial avec '&'
        if (joueur.getHero().getManaCourant() >= 3) {
            System.out.println("\nTape & pour utiliser ton pouvoir spécial, ou appuie sur Entrée pour passer.");
            String choixPouvoir = scanner.nextLine();
            if (choixPouvoir.trim().equals("&")) {
                joueur.getHero().utiliserPouvoir(adversaire);
            } else {
                System.out.println(joueur.getPseudo() + " ne fait pas de pouvoir spécial.");
            }
        } else {
            System.out.println("\nPas assez de mana pour utiliser le pouvoir spécial.");
        }
    }

    // Choix du héros au début
    public static TypeHero choisirHero(Scanner scanner) {
        System.out.println("Choisis un héros parmi ceux-là :");
        TypeHero[] types = TypeHero.values();
        for (int i = 0; i < types.length; i++) {
            System.out.println((i + 1) + " - " + types[i]);
        }

        int choix = -1;
        while (choix < 1 || choix > types.length) {
            System.out.print("Numéro du héros : ");
            if (scanner.hasNextInt()) {
                choix = scanner.nextInt();
                scanner.nextLine();
                if (choix < 1 || choix > types.length) {
                    System.out.println("x Numéro hors plage, recommence.");
                }
            } else {
                System.out.println("x Mets un nombre entier stp.");
                scanner.nextLine();
            }
        }
        return types[choix - 1];
    }

    // On pioche les cartes depuis les fichiers CSV pour faire un deck
    public static void initialiserDeck(Joueur joueur) {
        List<Carte> toutesLesCartes = new ArrayList<>();
        toutesLesCartes.addAll(ChargementCartes.chargerCartesDepuisCSV("serviteurs.csv", "serviteur"));
        toutesLesCartes.addAll(ChargementCartes.chargerCartesDepuisCSV("sorts.csv", "sort"));
        toutesLesCartes.addAll(ChargementCartes.chargerCartesDepuisCSV("armes.csv", "arme"));

        Collections.shuffle(toutesLesCartes);

        Deck deck = joueur.getDeck();
        for (int i = 0; i < 30 && i < toutesLesCartes.size(); i++) {
            deck.ajouterCarte(toutesLesCartes.get(i));
        }
        deck.melangerDeck();
    }

    // On fait les deux decks sans doublon
    public static void initialiserDecks(Joueur joueur1, Joueur joueur2) {
        List<Carte> toutesLesCartes = new ArrayList<>();
        toutesLesCartes.addAll(ChargementCartes.chargerCartesDepuisCSV("serviteurs.csv", "serviteur"));
        toutesLesCartes.addAll(ChargementCartes.chargerCartesDepuisCSV("sorts.csv", "sort"));
        toutesLesCartes.addAll(ChargementCartes.chargerCartesDepuisCSV("armes.csv", "arme"));

        Collections.shuffle(toutesLesCartes);

        Deck deck1 = joueur1.getDeck();
        Deck deck2 = joueur2.getDeck();

        for (int i = 0; i < 30 && !toutesLesCartes.isEmpty(); i++) {
            deck1.ajouterCarte(toutesLesCartes.remove(0));
        }
        for (int i = 0; i < 30 && !toutesLesCartes.isEmpty(); i++) {
            deck2.ajouterCarte(toutesLesCartes.remove(0));
        }
        deck1.melangerDeck();
        deck2.melangerDeck();
    }

    // Ici le joueur joue une carte de sa main
    public static void jouerCarteDepuisMain(Scanner scanner, Joueur joueur, Joueur adversaire) {
        boolean carteJoueeOuPasse = false;
        while (!carteJoueeOuPasse) {
            System.out.println("\n* " + joueur.getPseudo() + ", choisis une carte à jouer (Mana : " + joueur.getHero().getManaCourant() + ")");
            joueur.afficherMain();
            System.out.print("Numéro de la carte à jouer (1-" + joueur.getMain().size() + ") ou 0 pour passer : ");

            int choix = -1;
            while (choix < 0 || choix > joueur.getMain().size()) {
                if (scanner.hasNextInt()) {
                    choix = scanner.nextInt();
                    scanner.nextLine();
                    if (choix < 0 || choix > joueur.getMain().size()) {
                        System.out.println("x Numéro hors plage, recommence.");
                    }
                } else {
                    System.out.println("x Mets un nombre entier stp.");
                    scanner.nextLine();
                }
            }

            if (choix == 0) {
                System.out.println(joueur.getPseudo() + " passe son tour.");
                carteJoueeOuPasse = true;
            } else if (choix >= 1 && choix <= joueur.getMain().size()) {
                Carte carteChoisie = joueur.getMain().get(choix - 1);
                if (carteChoisie.getMana() > joueur.getHero().getManaCourant()) {
                    System.out.println("x Pas assez de mana pour jouer " + carteChoisie.getNom() + ". Choisis une autre carte ou passe.");
                } else {
                    joueur.getHero().reduireMana(carteChoisie.getMana());

                    if (carteChoisie instanceof Serviteur) {
                        joueur.getPlateau().ajouterServiteur((Serviteur) carteChoisie);
                        System.out.println("+ " + carteChoisie.getNom() + " est posé sur le plateau !");
                    } else if (carteChoisie instanceof Arme) {
                        joueur.getHero().setArmeEquipee((Arme) carteChoisie);
                        System.out.println("> " + joueur.getHero().getNom() + " s'équipe de " + carteChoisie.getNom());
                    } else if (carteChoisie instanceof Sort) {
                        Sort sort = (Sort) carteChoisie;
                        Object cible = null;

                        if (sort.getEffet() == Sort.TypeEffet.SOIN) {
                            System.out.println("Choisis la cible du soin :");
                            System.out.println("0 - Ton héros (" + joueur.getHero().getNom() + ")");
                            for (int i = 0; i < joueur.getPlateau().getServiteurs().size(); i++) {
                                System.out.println((i + 1) + " - " + joueur.getPlateau().getServiteurs().get(i).getNom());
                            }
                            int choixCible = scanner.nextInt();
                            scanner.nextLine();
                            if (choixCible == 0) {
                                cible = joueur.getHero();
                            } else if (choixCible > 0 && choixCible <= joueur.getPlateau().getServiteurs().size()) {
                                cible = joueur.getPlateau().getServiteurs().get(choixCible - 1);
                            } else {
                                System.out.println("x Cible invalide, le sort est perdu !");
                            }
                        } else if (sort.getEffet() == Sort.TypeEffet.DEGATS) {
                            if (adversaire.getPlateau().getServiteurs().isEmpty()) {
                                cible = adversaire.getHero();
                                System.out.println("Aucun serviteur adverse, le sort cible le héros adverse.");
                            } else {
                                System.out.println("Choisis la cible du sort de dégâts :");
                                for (int i = 0; i < adversaire.getPlateau().getServiteurs().size(); i++) {
                                    System.out.println((i + 1) + " - " + adversaire.getPlateau().getServiteurs().get(i).getNom());
                                }
                                int choixCible = scanner.nextInt();
                                scanner.nextLine();
                                if (choixCible > 0 && choixCible <= adversaire.getPlateau().getServiteurs().size()) {
                                    cible = adversaire.getPlateau().getServiteurs().get(choixCible - 1);
                                } else {
                                    System.out.println("x Cible invalide, le sort est perdu !");
                                }
                            }
                        }

                        if (cible != null) {
                            sort.appliquerEffet(cible);
                        }
                    }
                    joueur.getMain().remove(carteChoisie);
                    carteJoueeOuPasse = true;
                }
            }
        }
    }
}
