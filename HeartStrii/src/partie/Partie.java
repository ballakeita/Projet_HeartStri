package partie;

public class Partie {
    private Joueur joueur1;
    private Joueur joueur2;
    private Joueur joueurCourant;

    public Partie(Joueur j1, Joueur j2) {
        this.joueur1 = j1;
        this.joueur2 = j2;
        this.joueurCourant = joueur1;
    }

    public void demarrerTour() {
        joueurCourant.getHero().augmenterMana();
        joueurCourant.piocherCarte();
    }

    public void changerTour() {
        joueurCourant = (joueurCourant == joueur1) ? joueur2 : joueur1;
    }

    public Joueur getJoueurCourant() {
        return joueurCourant;
    }
//    État de partie : victoire / défaite
    public boolean estFinie() {
        return joueur1.getHero().estMort() || joueur2.getHero().estMort();
    }

    

}
