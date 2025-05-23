package partie;

import carte.Arme;
import carte.Serviteur;

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

    public void soigner(int valeur) {
        pv += valeur;
        if (pv > 30) pv = 30;
    }

    public void recevoirDegats(int valeur) {
        pv -= valeur;
    }

    public boolean estMort() {
        return pv <= 0;
    }

    public void augmenterMana() {
        if (manaMax < 10) manaMax++;
        manaCourant = manaMax;
    }

    public void reduireMana(int cout) {
        manaCourant -= cout;
    }

    public void setArmeEquipee(Arme arme) {
        this.armeEquipee = arme;
    }

    public Arme getArmeEquipee() {
        return armeEquipee;
    }

    public void attaquerAvecArme(Hero cible) {
        if (armeEquipee != null) {
            cible.recevoirDegats(armeEquipee.getAttaque());
            armeEquipee.utiliser();
            if (armeEquipee.estDetruite()) {
                armeEquipee = null;
            }
        }
    }
    
//    Pour utiliser le pouvoir par les joueurs cibles
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
                this.pv += 2; // simulation d’armure
                System.out.println(nom + " (Guerrier) gagne 2 points d’armure (PV).");
                break;
            case DRUIDE:
                this.pv += 1; // 1 armure
                System.out.println(nom + " (Druide) gagne 1 armure.");
                // effet bonus : donner 1 attaque temporaire (pas encore géré globalement)
                break;
            case PALADIN:
                Serviteur recrue = new Serviteur("Recrue 1/1", 1, 1, 1);
                // À adapter selon votre logique d’invocation
                // Ici, on considère que le héros a un joueur associé pour invoquer
                // => dans Joueur, créer une méthode invoquerDepuisPouvoir()
                System.out.println(nom + " (Paladin) invoque une recrue 1/1.");
                break;
            case VOLEUR:
                this.setArmeEquipee(new Arme("Dague", 2, 1, 2));
                System.out.println(nom + " (Voleur) s’équipe d’une dague 1/2.");
                break;
        }

        manaCourant -= 2; // coût du pouvoir
    }

}

