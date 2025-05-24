package carte;

/**
 * Classe abstraite pour une carte du jeu.
 * Toutes les cartes (serviteur, sort, arme) héritent de cette classe.
 * Je définis ici le nom, le coût en mana et la méthode d'affichage à surcharger.
 * @author Abdel Amir AMIRI, Balla KEITA, Ny Avo RAKOTOARIMAHEFASOA
 */
public abstract class Carte {
    // Nom de la carte
    protected String nom;

    // Coût en mana de la carte
    protected int mana;

    /**
     * Constructeur pour initialiser le nom et le mana de la carte.
     * @param nom Nom de la carte
     * @param mana Coût en mana de la carte
     */
    public Carte(String nom, int mana) {
        this.nom = nom;
        this.mana = mana;
    }

    /**
     * Permet de récupérer le nom de la carte.
     * @return nom de la carte
     */
    public String getNom() {
        return nom;
    }

    /**
     * Permet de récupérer le coût en mana de la carte.
     * @return coût en mana
     */
    public int getMana() {
        return mana;
    }

    /**
     * Méthode abstraite : chaque type de carte doit afficher ses infos à sa façon.
     */
    public abstract void afficherInfos();
}