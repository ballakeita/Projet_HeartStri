package carte;

//Classe abstraite représentant une carte générique du jeu
public abstract class Carte {
//Nom de la carte
	protected String nom;

//Mana possédé par la carte
	protected int mana;

//Constructeur pour initialiser les attributs
	public Carte(String nom, int mana) {
		this.nom = nom;
		this.mana = mana;
}

//Méthode pour récupérer le nom
	public String getNom() {
		return nom;
}

//Méthode pour récupérer le coût en mana
	public int getMana() {
		return mana;
}

//Méthode abstraite : chaque type de carte affichera ses infos différemment
	public abstract void afficherInfos();
}