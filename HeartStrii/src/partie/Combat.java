package partie;

import carte.Serviteur;

/**
 * Classe utilitaire pour gérer les combats entre serviteurs.
 * On utilise cette classe pour simuler les affrontements sur le plateau.
 * @author Abdel Amir AMIRI, Balla KEITA, Ny Avo RAKOTOARIMAHEFASOA
 */
public class Combat {

    /**
     * Lance un combat entre deux serviteurs.
     * L'attaquant inflige ses dégâts au défenseur, puis le défenseur riposte s'il est encore en vie.
     * On affiche le déroulement du combat et le résultat (mort éventuelle des serviteurs).
     * @param attaquant Le serviteur qui attaque
     * @param defenseur Le serviteur qui se défend
     */
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