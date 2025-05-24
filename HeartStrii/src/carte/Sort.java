package carte;

import partie.Hero;

// Carte Sort, pour gérer les sorts du jeu
public class Sort extends Carte {
    public enum TypeEffet { DEGATS, SOIN, BUFF }

    private TypeEffet effet;
    private int valeur;

    // Constructeur du sort
    public Sort(String nom, int mana, TypeEffet effet, int valeur) {
        super(nom, mana);
        this.effet = effet;
        this.valeur = valeur;
    }

    // Applique l'effet du sort sur la cible
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

    // Affiche les infos du sort
    @Override
    public void afficherInfos() {
        System.out.println(nom + " | Mana: " + mana + " | Effet: " + effet + " | Valeur: " + valeur);
    }

    // Retourne le type d'effet du sort
    public TypeEffet getEffet() { 
        return effet; 
    }
}
