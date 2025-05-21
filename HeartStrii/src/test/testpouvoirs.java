package test;

import partie.*;
import carte.*;

public class testpouvoirs {
    public static void main(String[] args) {
        String[] noms = { "Jaina", "Anduin", "Rexxar", "Garrosh", "Malfurion", "Uther", "Valeera" };
        TypeHero[] types = {
            TypeHero.MAGE, TypeHero.PRÊTRE, TypeHero.CHASSEUR,
            TypeHero.GUERRIER, TypeHero.DRUIDE, TypeHero.PALADIN, TypeHero.VOLEUR
        };

        for (int i = 0; i < noms.length; i++) {
            Hero hero = new Hero(noms[i], types[i]);
            Joueur joueur = new Joueur("J" + i, hero);
            joueur.ajouterCarteDeck(new Serviteur("Recrue test", 1, 1, 1));
            joueur.getHero().augmenterMana(); // Donne 2 de mana

            System.out.println("🔸 Test du pouvoir de " + hero.getNom() + " (" + hero.getType() + ")");
            joueur.getHero().utiliserPouvoir(joueur); // Cible lui-même ou générique selon pouvoir
            System.out.println("PV après pouvoir : " + joueur.getHero().getPv());
            System.out.println("Mana restant : " + joueur.getHero().getManaCourant());
            System.out.println("———");
        }
    }
}
