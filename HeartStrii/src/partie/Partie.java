package partie;

/**
 * Classe qui gère une partie entre deux joueurs.
 * On retrouve ici les deux joueurs, le joueur courant, et la gestion du tour et de la fin de partie.
 * Toutes les actions globales de la partie sont centralisées ici.
 * @author Abdel Amir AMIRI, Balla KEITA, Ny Avo RAKOTOARIMAHEFASOA
 */
public class Partie {
    private Joueur joueur1;
    private Joueur joueur2;
    private Joueur joueurCourant;

    /**
     * Constructeur de la partie.
     * @param j1 Premier joueur
     * @param j2 Deuxième joueur
     */
    public Partie(Joueur j1, Joueur j2) {
        this.joueur1 = j1;
        this.joueur2 = j2;
        this.joueurCourant = joueur1;
    }

    /**
     * Démarre le tour du joueur courant : il gagne de la mana et pioche une carte.
     */
    public void demarrerTour() {
        joueurCourant.getHero().augmenterMana();
        joueurCourant.piocherCarte();
    }

    /**
     * Change le joueur courant (passe au joueur suivant).
     */
    public void changerTour() {
        joueurCourant = (joueurCourant == joueur1) ? joueur2 : joueur1;
    }

    /**
     * Retourne le joueur dont c'est le tour.
     * @return le joueur courant
     */
    public Joueur getJoueurCourant() {
        return joueurCourant;
    }

    /**
     * Vérifie si la partie est terminée (si un héros est mort).
     * @return true si la partie est finie, false sinon
     */
    public boolean estFinie() {
        return joueur1.getHero().estMort() || joueur2.getHero().estMort();
    }
}
