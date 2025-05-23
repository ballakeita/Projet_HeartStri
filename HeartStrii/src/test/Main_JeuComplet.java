package test;

import java.util.Scanner;
import carte.Serviteur;
import partie.Combat;
import partie.Deck;
import partie.Joueur;
import partie.Hero;
import partie.TypeHero;

public class Main_JeuComplet {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // --- Phase 1 : Création des joueurs ---
        System.out.println("👤 Création du joueur 1");
        System.out.print("Entrez le nom du joueur 1 : ");
        String nomJoueur1 = scanner.nextLine();
        TypeHero typeHero1 = choisirHero(scanner);
        Joueur joueur1 = new Joueur(nomJoueur1, new Hero(typeHero1.toString(), typeHero1));

        System.out.println("\n👤 Création du joueur 2");
        System.out.print("Entrez le nom du joueur 2 : ");
        String nomJoueur2 = scanner.nextLine();
        TypeHero typeHero2 = choisirHero(scanner);
        Joueur joueur2 = new Joueur(nomJoueur2, new Hero(typeHero2.toString(), typeHero2));

        System.out.println("\n✅ Les deux joueurs ont été créés !");
        System.out.println(joueur1.getPseudo() + " joue le héros " + joueur1.getHero().getNom());
        System.out.println(joueur2.getPseudo() + " joue le héros " + joueur2.getHero().getNom());

        // --- Phase 2 : Création des decks + pioche ---
        initialiserDeck(joueur1);
        initialiserDeck(joueur2);

        System.out.println("\n🃏 Chaque joueur pioche 3 cartes...");
        for (int i = 0; i < 3; i++) {
            joueur1.piocherCarte();
            joueur2.piocherCarte();
        }

        // --- Boucle principale du jeu ---
        while (!joueur1.getHero().estMort() && !joueur2.getHero().estMort()) {
            // === Tour du joueur 1 ===
            jouerUnTour(scanner, joueur1, joueur2);
            if (joueur2.getHero().estMort()) break;

            // === Tour du joueur 2 ===
            jouerUnTour(scanner, joueur2, joueur1);
        }

        // --- Fin de partie ---
        System.out.println("\n🎉 Fin du combat !");
        if (joueur1.getHero().estMort()) {
            System.out.println("🏆 " + joueur2.getPseudo() + " a gagné !");
        } else if (joueur2.getHero().estMort()) {
            System.out.println("🏆 " + joueur1.getPseudo() + " a gagné !");
        } else {
            System.out.println("🤝 Match nul !");
        }

        scanner.close();
    }

    public static void jouerUnTour(Scanner scanner, Joueur joueur, Joueur adversaire) {
        System.out.println("\n🔁 TOUR DE " + joueur.getPseudo());
        joueur.getHero().augmenterMana();
        joueur.piocherCarte();

        jouerCarteDepuisMain(scanner, joueur);

        if (joueur.getPlateau().getServiteurs().isEmpty()) {
            System.out.println("🛡️ Aucun serviteur à attaquer.");
        } else {
            System.out.println("\n⚔️ " + joueur.getPseudo() + ", choisissez un serviteur pour attaquer :");
            joueur.getPlateau().afficherPlateau();

            int atk = -1;
            while (atk < 0 || atk >= joueur.getPlateau().getServiteurs().size()) {
                System.out.print("Numéro du serviteur : ");
                if (scanner.hasNextInt()) {
                    atk = scanner.nextInt() - 1; // index 0-based
                    scanner.nextLine(); // consommer la fin de ligne
                    if (atk < 0 || atk >= joueur.getPlateau().getServiteurs().size()) {
                        System.out.println("❌ Numéro hors plage, veuillez réessayer.");
                    }
                } else {
                    System.out.println("❌ Veuillez entrer un nombre entier valide.");
                    scanner.nextLine(); // consommer entrée incorrecte
                }
            }

            Serviteur attaquant = joueur.getPlateau().getServiteurs().get(atk);

            if (adversaire.getPlateau().getServiteurs().isEmpty()) {
                adversaire.getHero().recevoirDegats(attaquant.getAttaque());
                System.out.println("💥 " + attaquant.getNom() + " attaque le héros " + adversaire.getHero().getNom() + " pour " + attaquant.getAttaque() + " dégâts.");
                System.out.println("❤️ PV du héros " + adversaire.getHero().getNom() + " : " + adversaire.getHero().getPv());
            } else {
                System.out.println("Cible sur le plateau de " + adversaire.getPseudo() + " :");
                adversaire.getPlateau().afficherPlateau();

                int cible = -1;
                while (cible < 0 || cible >= adversaire.getPlateau().getServiteurs().size()) {
                    System.out.print("Numéro de la cible : ");
                    if (scanner.hasNextInt()) {
                        cible = scanner.nextInt() - 1;
                        scanner.nextLine();
                        if (cible < 0 || cible >= adversaire.getPlateau().getServiteurs().size()) {
                            System.out.println("❌ Numéro hors plage, veuillez réessayer.");
                        }
                    } else {
                        System.out.println("❌ Veuillez entrer un nombre entier valide.");
                        scanner.nextLine();
                    }
                }

                Serviteur defenseur = adversaire.getPlateau().getServiteurs().get(cible);
                Combat.lancerCombat(attaquant, defenseur);

                if (attaquant.estMort()) joueur.getPlateau().getServiteurs().remove(attaquant);
                if (defenseur.estMort()) adversaire.getPlateau().getServiteurs().remove(defenseur);
            }
        }
    }

    public static TypeHero choisirHero(Scanner scanner) {
        System.out.println("Choisissez un héros parmi les suivants :");
        TypeHero[] types = TypeHero.values();
        for (int i = 0; i < types.length; i++) {
            System.out.println((i + 1) + " - " + types[i]);
        }

        int choix = -1;
        while (choix < 1 || choix > types.length) {
            System.out.print("Entrez le numéro du héros : ");
            if (scanner.hasNextInt()) {
                choix = scanner.nextInt();
                scanner.nextLine();
                if (choix < 1 || choix > types.length) {
                    System.out.println("❌ Numéro hors plage, veuillez réessayer.");
                }
            } else {
                System.out.println("❌ Veuillez entrer un nombre entier valide.");
                scanner.nextLine();
            }
        }
        return types[choix - 1];
    }

    public static void initialiserDeck(Joueur joueur) {
        Deck deck = joueur.getDeck();
        deck.ajouterCarte(new Serviteur("Loup Alpha", 2, 3, 60));
        deck.ajouterCarte(new Serviteur("Yéti Grincheux", 4, 4, 60));
        deck.ajouterCarte(new Serviteur("Soldat d'élite", 3, 3, 50));
        deck.ajouterCarte(new Serviteur("Golem de pierre", 5, 5, 80));
        deck.ajouterCarte(new Serviteur("Mage Sinistre", 3, 5, 40));
        deck.melangerDeck();
    }

    public static void jouerCarteDepuisMain(Scanner scanner, Joueur joueur) {
        System.out.println("\n🎮 " + joueur.getPseudo() + ", choisissez une carte à jouer (Mana : " + joueur.getHero().getManaCourant() + ")");
        joueur.afficherMain();
        System.out.print("Entrez le numéro de la carte à jouer (1-" + joueur.getMain().size() + ") ou 0 pour passer : ");

        int choix = -1;
        while (choix < 0 || choix > joueur.getMain().size()) {
            if (scanner.hasNextInt()) {
                choix = scanner.nextInt();
                scanner.nextLine();
                if (choix < 0 || choix > joueur.getMain().size()) {
                    System.out.println("❌ Numéro hors plage, veuillez réessayer.");
                }
            } else {
                System.out.println("❌ Veuillez entrer un nombre entier valide.");
                scanner.nextLine();
            }
        }
//        tata

        if (choix == 0) {
            System.out.println(joueur.getPseudo() + " passe son tour.");
            return;
        }

        Serviteur choisi = (Serviteur) joueur.getMain().get(choix - 1);
        if (choisi.getMana() > joueur.getHero().getManaCourant()) {
            System.out.println("❌ Pas assez de mana pour jouer " + choisi.getNom());
        } else {
            joueur.getHero().reduireMana(choisi.getMana());
            joueur.getPlateau().ajouterServiteur(choisi);
            joueur.getMain().remove(choisi);
            System.out.println("✅ " + choisi.getNom() + " est posé sur le plateau !");
        }
    }
}
