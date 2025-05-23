package test;
import java.util.Scanner;
import carte.*;
import partie.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Initialisation
        Joueur joueur1 = new Joueur("Alice", new Hero("Jaina", TypeHero.MAGE));
        Joueur joueur2 = new Joueur("Bob", new Hero("Rexxar", TypeHero.CHASSEUR));
        Partie partie = new Partie(joueur1, joueur2);

        // Ajouter des cartes à leur deck (exemple simple)
        for (int i = 0; i < 5; i++) {
            joueur1.ajouterCarteDeck(new Serviteur("Yéti", 4, 4, 60));
            joueur2.ajouterCarteDeck(new Serviteur("Loup", 2, 3, 30));
        }

        boolean enCours = true;
        while (enCours) {
            Joueur joueur = partie.getJoueurCourant();
            Joueur adversaire = (joueur == joueur1) ? joueur2 : joueur1;

            System.out.println("\n🎮 Tour de : " + joueur.getPseudo());
            joueur.getHero().augmenterMana();
            joueur.piocherCarte();

            boolean finTour = false;
            while (!finTour) {
                System.out.println("\n--- Menu ---");
                System.out.println("1. Voir la main");
                System.out.println("2. Invoquer un serviteur");
                System.out.println("3. Utiliser pouvoir héroïque");
                System.out.println("4. Voir plateau");
                System.out.println("5. Passer le tour");

                int choix = scanner.nextInt();
                scanner.nextLine(); // flush

                switch (choix) {
                    case 1:
                        joueur.afficherMain();
                        break;
                    case 2:
                    	

                    case 3:
                        joueur.getHero().utiliserPouvoir(adversaire);
                        break;
                    case 4:
                        System.out.println("🧱 Plateau de " + joueur.getPseudo() + " :");
                        for (Serviteur s : joueur.getPlateau().getServiteurs()) {
                            s.afficherInfos();
                        }
                        break;
                    case 5:
                        finTour = true;
                        partie.changerTour();
                        break;
                    default:
                        System.out.println("❌ Choix invalide.");
                }

                if (joueur.getHero().estMort() || adversaire.getHero().estMort()) {
                    enCours = false;
                    break;
                }
            }
        }

        Joueur gagnant = joueur1.getHero().estMort() ? joueur2 : joueur1;
        System.out.println("\n🏆 Partie terminée ! Vainqueur : " + gagnant.getPseudo());
        scanner.close();
    }
}
