package carte;

public class Arme extends Carte {
    private int attaque;
    private int durabilite;

    public Arme(String nom, int mana, int attaque, int durabilite) {
        super(nom, mana);
        this.attaque = attaque;
        this.durabilite = durabilite;
    }

    public int getAttaque() {
        return attaque;
    }

    public int getDurabilite() {
        return durabilite;
    }

    public void utiliser() {
        durabilite--;
    }

    public boolean estDetruite() {
        return durabilite <= 0;
    }

    @Override
    public void afficherInfos() {
        System.out.println(nom + " | Mana: " + mana + " | Attaque: " + attaque + " | Durabilité: " + durabilite);
    }
}
