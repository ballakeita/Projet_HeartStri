package carte;

// Carte Serviteur
public class Serviteur extends Carte {

    private int attaque;
    private int vie;

    // Constructeur du serviteur
    public Serviteur(String nom, int mana, int attaque, int vie) {
        super(nom, mana);
        this.attaque = attaque;
        this.vie = vie;
    }

    // Récupère les points d'attaque
    public int getAttaque() {
        return attaque;
    }

    // Récupère les points de vie restants
    public int getVie() {
        return vie;
    }

    // Soustrait les points de vie quand le serviteur prend des dégâts
    public void subirDegats(int degats) {
        this.vie -= degats;
    }

    // Vérifie si le serviteur est mort
    public boolean estMort() {
        return vie <= 0;
    }

    // Affiche les infos du serviteur
    @Override
    public void afficherInfos() {
        System.out.println(nom + " | Mana: " + mana + " | Attaque: " + attaque + " | Vie: " + vie);
    }

    // Soigne le serviteur
    public void soigner(int valeur) {
        vie += valeur;
    }

    // Augmente l'attaque du serviteur
    public void augmenterAttaque(int valeur) {
        attaque += valeur;
    }

    // Permet de gérer une attaque entre deux serviteurs
    public void attaquer(Serviteur attaquant, Serviteur cible) {
        cible.subirDegats(attaquant.getAttaque());
        attaquant.subirDegats(cible.getAttaque());
    }

    // Permet à ce serviteur d'attaquer une cible
    public void attaquer(Serviteur cible) {
        cible.subirDegats(this.attaque);
        if (!cible.estMort()) {
            this.subirDegats(cible.getAttaque());
        }
    }
}