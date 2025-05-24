package carte;

/**
 * Classe qui gère une carte arme pour le héros.
 * Une arme possède un nom, un coût en mana, une valeur d'attaque et une durabilité.
 * @author Abdel Amir AMIRI, Balla KEITA, Ny Avo RAKOTOARIMAHEFASOA
 */
public class Arme extends Carte {
    private int attaque;      // Points d'attaque de l'arme
    private int durabilite;   // Nombre de coups restants

    /**
     * Constructeur : je donne le nom, le coût, l'attaque et la durabilité.
     * @param nom Nom de l'arme
     * @param mana Coût en mana pour équiper l'arme
     * @param attaque Points d'attaque de l'arme
     * @param durabilite Nombre de coups que l'arme peut porter avant d'être détruite
     */
    public Arme(String nom, int mana, int attaque, int durabilite) {
        super(nom, mana);
        this.attaque = attaque;
        this.durabilite = durabilite;
    }

    /**
     * Permet de récupérer l'attaque de l'arme.
     * @return Points d'attaque de l'arme
     */
    public int getAttaque() {
        return attaque;
    }

    /**
     * Permet de récupérer la durabilité de l'arme.
     * @return Nombre de coups restants
     */
    public int getDurabilite() {
        return durabilite;
    }

    /**
     * Quand j'utilise l'arme, je retire 1 à la durabilité.
     */
    public void utiliser() {
        durabilite--;
    }

    /**
     * Vérifie si l'arme est cassée (durabilité <= 0).
     * @return true si l'arme est détruite, false sinon
     */
    public boolean estDetruite() {
        return durabilite <= 0;
    }

    /**
     * Affiche les infos de l'arme.
     */
    @Override
    public void afficherInfos() {
        System.out.println(nom + " | Mana: " + mana + " | Attaque: " + attaque + " | Durabilité: " + durabilite);
    }
}
