package carte;

/**
 * Classe qui gère une carte Serviteur.
 * Un serviteur a un nom, un coût en mana, une attaque et des points de vie.
 * Je peux le soigner, augmenter son attaque, et il peut attaquer d'autres serviteurs.
 * @author Abdel Amir AMIRI, Balla KEITA, Ny Avo RAKOTOARIMAHEFASOA
 */
public class Serviteur extends Carte {

    private int attaque;
    private int vie;

    /**
     * Constructeur du serviteur.
     * @param nom Nom du serviteur
     * @param mana Coût en mana pour poser le serviteur
     * @param attaque Points d'attaque du serviteur
     * @param vie Points de vie du serviteur
     */
    public Serviteur(String nom, int mana, int attaque, int vie) {
        super(nom, mana);
        this.attaque = attaque;
        this.vie = vie;
    }

    /**
     * Récupère les points d'attaque du serviteur.
     * @return attaque du serviteur
     */
    public int getAttaque() {
        return attaque;
    }

    /**
     * Récupère les points de vie restants du serviteur.
     * @return points de vie
     */
    public int getVie() {
        return vie;
    }

    /**
     * Soustrait les points de vie quand le serviteur prend des dégâts.
     * @param degats dégâts subis
     */
    public void subirDegats(int degats) {
        this.vie -= degats;
    }

    /**
     * Vérifie si le serviteur est mort.
     * @return true si mort, false sinon
     */
    public boolean estMort() {
        return vie <= 0;
    }

    /**
     * Affiche les infos du serviteur.
     */
    @Override
    public void afficherInfos() {
        System.out.println(nom + " | Mana: " + mana + " | Attaque: " + attaque + " | Vie: " + vie);
    }

    /**
     * Soigne le serviteur.
     * @param valeur nombre de points de vie à ajouter
     */
    public void soigner(int valeur) {
        vie += valeur;
    }

    /**
     * Augmente l'attaque du serviteur.
     * @param valeur bonus d'attaque
     */
    public void augmenterAttaque(int valeur) {
        attaque += valeur;
    }

    /**
     * Permet de gérer une attaque entre deux serviteurs (version statique).
     * @param attaquant le serviteur qui attaque
     * @param cible le serviteur ciblé
     */
    public void attaquer(Serviteur attaquant, Serviteur cible) {
        cible.subirDegats(attaquant.getAttaque());
        attaquant.subirDegats(cible.getAttaque());
    }

    /**
     * Permet à ce serviteur d'attaquer une cible.
     * @param cible le serviteur ciblé
     */
    public void attaquer(Serviteur cible) {
        cible.subirDegats(this.attaque);
        if (!cible.estMort()) {
            this.subirDegats(cible.getAttaque());
        }
    }
}