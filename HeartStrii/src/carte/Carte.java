package carte;

// Classe abstraite pour une carte du jeu
public abstract class Carte {
    // Nom de la carte
    protected String nom;

    // Coût en mana de la carte
    protected int mana;

    // Constructeur pour initialiser le nom et le mana
    public Carte(String nom, int mana) {
        this.nom = nom;
        this.mana = mana;
    }

    // Retourne le nom de la carte
    public String getNom() {
        return nom;
    }

    // Retourne le coût en mana de la carte
    public int getMana() {
        return mana;
    }

    // Chaque type de carte doit afficher ses infos à sa façon
    public abstract void afficherInfos();
}