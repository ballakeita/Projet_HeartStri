package carte;


import partie.Hero;

public class Sort extends Carte {
    public enum TypeEffet { DEGATS, SOIN, BUFF }

    private TypeEffet effet;
    private int valeur;

    public Sort(String nom, int mana, TypeEffet effet, int valeur) {
        super(nom, mana);
        this.effet = effet;
        this.valeur = valeur;
    }

    public void appliquerEffet(Object cible) {
        if (effet == TypeEffet.DEGATS && cible instanceof Serviteur) {
            ((Serviteur) cible).subirDegats(valeur);
        } else if (effet == TypeEffet.SOIN) {
            if (cible instanceof Serviteur) {
                ((Serviteur) cible).soigner(valeur);
            } else if (cible instanceof Hero) {
                ((Hero) cible).soigner(valeur);
            }
        } else if (effet == TypeEffet.BUFF && cible instanceof Serviteur) {
            ((Serviteur) cible).augmenterAttaque(valeur);
        }
    }

    @Override
    public void afficherInfos() {
        System.out.println(nom + " | Mana: " + mana + " | Effet: " + effet + " | Valeur: " + valeur);
    }
}
