package test;

import carte.Serviteur;
import partie.Combat;
import partie.Deck;
import partie.Joueur;
import partie.Hero;
import partie.TypeHero;

public class TestGeneral {
    public static void main(String[] args) {
        // Test 1 : Combat entre serviteurs
        testCombatServiteurs();

        System.out.println("\n\n===========================\n\n");

        // Test 2 : Test des pouvoirs des héros
        testPouvoirsHeros();
    }

    private static void testCombatServiteurs() {
        System.out.println("=== Test Combat Serviteurs ===");
        Deck deck = new Deck();

        deck.ajouterCarte(new Serviteur("Loup Alpha", 2, 3, 60));
        deck.ajouterCarte(new Serviteur("Yéti Grincheux", 4, 4, 60));
        deck.ajouterCarte(new Serviteur("Soldat d'élite", 3, 3, 50));
        deck.ajouterCarte(new Serviteur("Golem de pierre", 5, 5, 80));

        System.out.println("📦 Deck initial :");
        deck.afficherDeck();

        Serviteur s1 = deck.tirerCarteAleatoire();
        Serviteur s2 = deck.tirerCarteAleatoire();

        while (s1 == s2) {
            s2 = deck.tirerCarteAleatoire();
        }

        Joueur j1 = new Joueur("Alice", new Hero("Jaina", TypeHero.MAGE));
        Joueur j2 = new Joueur("Bob", new Hero("Rexxar", TypeHero.CHASSEUR));

        j1.getHero().utiliserPouvoir(j2);
        j2.getHero().utiliserPouvoir(j1);

        j1.getHero().augmenterMana();
        j2.getHero().augmenterMana();

        System.out.println("\n🎯 Serviteurs sélectionnés :");
        s1.afficherInfos();
        s2.afficherInfos();

        System.out.println("\n=== COMBAT ===");
        Combat.lancerCombat(s1, s2);
    }

    private static void testPouvoirsHeros() {
        System.out.println("=== Test Pouvoirs Héros ===");
        String[] noms = { "Jaina", "Anduin", "Rexxar", "Garrosh", "Malfurion", "Uther", "Valeera" };
        TypeHero[] types = {
            TypeHero.MAGE, TypeHero.PRÊTRE, TypeHero.CHASSEUR,
            TypeHero.GUERRIER, TypeHero.DRUIDE, TypeHero.PALADIN, TypeHero.VOLEUR
        };

        for (int i = 0; i < noms.length; i++) {
            Hero hero = new Hero(noms[i], types[i]);
            Joueur joueur = new Joueur("J" + i, hero);
            joueur.ajouterCarteDeck(new Serviteur("Recrue test", 1, 1, 1));
            joueur.getHero().augmenterMana();

            System.out.println("🔸 Test du pouvoir de " + hero.getNom() + " (" + hero.getType() + ")");
            joueur.getHero().utiliserPouvoir(joueur);
            System.out.println("PV après pouvoir : " + joueur.getHero().getPv());
            System.out.println("Mana restant : " + joueur.getHero().getManaCourant());
            System.out.println("———");
        }
    }
}
