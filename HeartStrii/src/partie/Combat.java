package partie;

import carte.Serviteur;

// Cette classe permet de simuler un combat entre deux serviteurs
public class Combat {

    // Lance un combat entre deux serviteurs
    public static void lancerCombat(Serviteur attaquant, Serviteur defenseur) {
        System.out.println("Début du combat entre " + attaquant.getNom() + " et " + defenseur.getNom());

        // Attaque de l'attaquant
        System.out.println(attaquant.getNom() + " attaque " + defenseur.getNom() + " > PV restants : " + (defenseur.getVie() - attaquant.getAttaque()));
        defenseur.subirDegats(attaquant.getAttaque());

        // Riposte du défenseur (seulement s'il est encore en vie)
        if (!defenseur.estMort()) {
            System.out.println(defenseur.getNom() + " riposte > PV restants : " + (attaquant.getVie() - defenseur.getAttaque()));
            attaquant.subirDegats(defenseur.getAttaque());
        }

        System.out.println("- Le combat est terminé !");
        if (attaquant.estMort()) System.out.println("x " + attaquant.getNom() + " est mort !");
        if (defenseur.estMort()) System.out.println("x " + defenseur.getNom() + " est mort !");
    }
}