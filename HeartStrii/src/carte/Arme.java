package carte;

// Classe qui gère une carte arme pour le héros
public class Arme extends Carte {
    private int attaque;      // Points d'attaque de l'arme
    private int durabilite;   // Nombre de coups restants

    // Constructeur : je donne le nom, le coût, l'attaque et la durabilité
    public Arme(String nom, int mana, int attaque, int durabilite) {
        super(nom, mana);
        this.attaque = attaque;
        this.durabilite = durabilite;
    }

    // Récupère l'attaque de l'arme
    public int getAttaque() {
        return attaque;
    }

    // Récupère la durabilité de l'arme
    public int getDurabilite() {
        return durabilite;
    }

    // Quand j'utilise l'arme, je retire 1 à la durabilité
    public void utiliser() {
        durabilite--;
    }

    // Vérifie si l'arme est cassée
    public boolean estDetruite() {
        return durabilite <= 0;
    }

    // Affiche les infos de l'arme
    @Override
    public void afficherInfos() {
        System.out.println(nom + " | Mana: " + mana + " | Attaque: " + attaque + " | Durabilité: " + durabilite);
    }
}
