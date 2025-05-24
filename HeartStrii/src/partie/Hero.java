package partie;

import carte.Arme;
import carte.Serviteur;

// Classe qui gère le héros du joueur
public class Hero {
    private String nom;
    private int pv = 30;
    private int manaMax = 0;
    private int manaCourant = 0;
    private Arme armeEquipee;
    private TypeHero type;

    public Hero(String nom, TypeHero type) {
        this.nom = nom;
        this.type = type;
    }

    public TypeHero getType() {
        return type;
    }

    public String getNom() {
        return nom;
    }

    public int getPv() {
        return pv;
    }

    public int getManaCourant() {
        return manaCourant;
    }

    public int getManaMax() {
        return manaMax;
    }

    // Soigne le héros
    public void soigner(int valeur) {
        pv += valeur;
        if (pv > 30) pv = 30;
    }

    // Le héros reçoit des dégâts
    public void recevoirDegats(int valeur) {
        pv -= valeur;
    }

    // Vérifie si le héros est mort
    public boolean estMort() {
        return pv <= 0;
    }

    // Augmente la mana max et la mana courante (jusqu'à 10)
    public void augmenterMana() {
        if (manaMax < 10) manaMax++;
        if (manaCourant < manaMax) manaCourant++;
    }

    // Retire du mana quand on joue une carte ou un pouvoir
    public void reduireMana(int cout) {
        manaCourant -= cout;
    }

    // Équipe une arme au héros
    public void setArmeEquipee(carte.Arme carteChoisie) {
        this.armeEquipee = carteChoisie;
    }

    // Retourne l'arme équipée
    public Arme getArmeEquipee() {
        return armeEquipee;
    }

    // Permet au héros d'attaquer avec son arme
    public void attaquerAvecArme(Hero cible) {
        if (armeEquipee != null) {
            cible.recevoirDegats(armeEquipee.getAttaque());
            armeEquipee.utiliser();
            if (armeEquipee.estDetruite()) {
                armeEquipee = null;
            }
        }
    }

    // Utilise le pouvoir du héros selon sa classe
    public void utiliserPouvoir(Joueur cible) {
        if (manaCourant < 2) {
            System.out.println(nom + " n’a pas assez de mana pour utiliser son pouvoir.");
            return;
        }
        switch (type) {
            case MAGE:
                cible.getHero().recevoirDegats(1);
                System.out.println(nom + " (Mage) inflige 1 dégât au héros adverse.");
                break;
            case PRÊTRE:
                this.soigner(2);
                System.out.println(nom + " (Prêtre) se soigne de 2 PV.");
                break;
            case CHASSEUR:
                cible.getHero().recevoirDegats(2);
                System.out.println(nom + " (Chasseur) inflige 2 dégâts au héros adverse.");
                break;
            case GUERRIER:
                this.pv += 2;
                System.out.println(nom + " (Guerrier) gagne 2 points d’armure (PV).");
                break;
            case DRUIDE:
                this.pv += 1;
                System.out.println(nom + " (Druide) gagne 1 armure.");
                // Bonus : donner 1 attaque temporaire (à gérer si besoin)
                break;
            case PALADIN:
                Serviteur recrue = new Serviteur("Recrue 1/1", 1, 1, 1);
                // À adapter selon la logique d’invocation dans Joueur
                System.out.println(nom + " (Paladin) invoque une recrue 1/1.");
                break;
            case VOLEUR:
                this.setArmeEquipee(new Arme("Dague", 2, 1, 2));
                System.out.println(nom + " (Voleur) s’équipe d’une dague 1/2.");
                break;
        }
        manaCourant -= 3; // coût du pouvoir
    }
}

