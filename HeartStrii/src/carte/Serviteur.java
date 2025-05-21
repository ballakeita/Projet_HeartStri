package carte;


//Carte Serviteur
public class Serviteur extends Carte {
	
	private int attaque;

	private int vie;

//Constructeur du serviteur
	public Serviteur(String nom, int mana, int attaque, int vie) {
		super(nom, mana);           // J'appelle le constructeur de la Carte
		this.attaque = attaque;
		this.vie = vie;
}

//Récupère les points d'attaque
	public int getAttaque() {
		return attaque;
}

//Récupère les points de vie restants
	public int getVie() {
		return vie;
}

//on soustrait les points de vie quand le serviteur prend des dégats
	public void subirDegats(int degats) {
		this.vie -= degats;
}

//Vérifie si le serviteur est mort
	public boolean estMort() {
		return vie <= 0;
}

@Override // j'appelle la methode afficherInfos pour afficher les infos du Serviteur
	public void afficherInfos() {
	System.out.println(nom + " | Mana: " + mana + " | Attaque: " + attaque + " | Vie: " + vie);
}

public void soigner(int valeur) {
    vie += valeur;
}

public void augmenterAttaque(int valeur) {
    attaque += valeur;
}

//Ajouter une méthode dans Plateau pour gérer les attaques de serviteurs
public void attaquer(Serviteur attaquant, Serviteur cible) {
    cible.subirDegats(attaquant.getAttaque());
    attaquant.subirDegats(cible.getAttaque());
}

//Encapsulation 
public void attaquer(Serviteur cible) {
    cible.subirDegats(this.attaque);
    if (!cible.estMort()) {
        this.subirDegats(cible.getAttaque());
    }
}




}