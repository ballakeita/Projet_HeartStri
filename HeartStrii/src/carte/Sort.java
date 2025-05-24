package carte;

import partie.Hero;

/**
 * Classe qui gère une carte Sort.
 * Un sort peut infliger des dégâts, soigner ou buffer un serviteur.
 * J'utilise cette classe pour tous les effets magiques du jeu.
 * @author Abdel Amir AMIRI, Balla KEITA, Ny Avo RAKOTOARIMAHEFASOA
 */
public class Sort extends Carte {
    /**
     * Enum pour le type d'effet du sort : dégâts, soin ou buff.
     */
    public enum TypeEffet { DEGATS, SOIN, BUFF }

    private TypeEffet effet;
    private int valeur;

    /**
     * Constructeur du sort.
     * @param nom Nom du sort
     * @param mana Coût en mana pour lancer le sort
     * @param effet Type d'effet du sort (dégâts, soin, buff)
     * @param valeur Valeur numérique de l'effet (dégâts infligés, PV soignés, bonus d'attaque)
     */
    public Sort(String nom, int mana, TypeEffet effet, int valeur) {
        super(nom, mana);
        this.effet = effet;
        this.valeur = valeur;
    }

    /**
     * Applique l'effet du sort sur la cible (serviteur ou héros).
     * @param cible La cible du sort (Serviteur ou Hero)
     */
    public void appliquerEffet(Object cible) {
        if (effet == TypeEffet.DEGATS) {
            if (cible instanceof Serviteur) {
                ((Serviteur) cible).subirDegats(valeur);
                System.out.println("> Le sort " + nom + " inflige " + valeur + " dégâts au serviteur " + ((Serviteur) cible).getNom());
            } else if (cible instanceof Hero) {
                ((Hero) cible).recevoirDegats(valeur);
                System.out.println("> Le sort " + nom + " inflige " + valeur + " dégâts au héros " + ((Hero) cible).getNom());
            }
        } else if (effet == TypeEffet.SOIN) {
            if (cible instanceof Serviteur) {
                ((Serviteur) cible).soigner(valeur);
                System.out.println("+ Le sort " + nom + " soigne " + valeur + " PV au serviteur " + ((Serviteur) cible).getNom());
            } else if (cible instanceof Hero) {
                ((Hero) cible).soigner(valeur);
                System.out.println("+ Le sort " + nom + " soigne " + valeur + " PV au héros " + ((Hero) cible).getNom());
            }
        } else if (effet == TypeEffet.BUFF && cible instanceof Serviteur) {
            ((Serviteur) cible).augmenterAttaque(valeur);
            System.out.println("~ Le sort " + nom + " augmente l'attaque du serviteur " + ((Serviteur) cible).getNom() + " de " + valeur);
        }
    }

    /**
     * Affiche les infos du sort.
     */
    @Override
    public void afficherInfos() {
        System.out.println(nom + " | Mana: " + mana + " | Effet: " + effet + " | Valeur: " + valeur);
    }

    /**
     * Retourne le type d'effet du sort.
     * @return le type d'effet (DEGATS, SOIN, BUFF)
     */
    public TypeEffet getEffet() { 
        return effet; 
    }
}
