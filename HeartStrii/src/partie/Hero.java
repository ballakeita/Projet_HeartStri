package partie;

import carte.Arme;
import carte.Serviteur;

/**
 * Classe qui gère le héros du joueur.
 * On gère ici les PV, le mana, l'arme équipée et le pouvoir spécial selon la classe du héros.
 * Toutes les actions du héros (soin, dégâts, attaque avec arme, pouvoir) sont centralisées ici.
 * @author Abdel Amir AMIRI, Balla KEITA, Ny Avo RAKOTOARIMAHEFASOA
 */
public class Hero {
    private String nom;
    private int pv = 30;
    private int manaMax = 0;
    private int manaCourant = 0;
    private Arme armeEquipee;
    private TypeHero type;

    /**
     * Constructeur du héros.
     * @param nom Nom du héros
     * @param type Type de héros (classe)
     */
    public Hero(String nom, TypeHero type) {
        this.nom = nom;
        this.type = type;
    }

    /**
     * Retourne le type du héros.
     * @return type du héros
     */
    public TypeHero getType() {
        return type;
    }

    /**
     * Retourne le nom du héros.
     * @return nom du héros
     */
    public String getNom() {
        return nom;
    }

    /**
     * Retourne les points de vie du héros.
     * @return PV du héros
     */
    public int getPv() {
        return pv;
    }

    /**
     * Retourne la quantité de mana courante.
     * @return mana courant
     */
    public int getManaCourant() {
        return manaCourant;
    }

    /**
     * Retourne la quantité de mana maximale.
     * @return mana max
     */
    public int getManaMax() {
        return manaMax;
    }

    /**
     * Soigne le héros d'une certaine valeur (ne dépasse jamais 30 PV).
     * @param valeur nombre de PV à soigner
     */
    public void soigner(int valeur) {
        pv += valeur;
        if (pv > 30) pv = 30;
    }

    /**
     * Inflige des dégâts au héros.
     * @param valeur nombre de PV à retirer
     */
    public void recevoirDegats(int valeur) {
        pv -= valeur;
    }

    /**
     * Vérifie si le héros est mort.
     * @return true si PV <= 0, false sinon
     */
    public boolean estMort() {
        return pv <= 0;
    }

    /**
     * Augmente la mana max et la mana courante (jusqu'à 10).
     */
    public void augmenterMana() {
        if (manaMax < 10) manaMax++;
        if (manaCourant < manaMax) manaCourant++;
    }

    /**
     * Retire du mana quand on joue une carte ou un pouvoir.
     * @param cout coût en mana à retirer
     */
    public void reduireMana(int cout) {
        manaCourant -= cout;
    }

    /**
     * Équipe une arme au héros.
     * @param carteChoisie arme à équiper
     */
    public void setArmeEquipee(carte.Arme carteChoisie) {
        this.armeEquipee = carteChoisie;
    }

    /**
     * Retourne l'arme équipée par le héros.
     * @return arme équipée (ou null si aucune)
     */
    public Arme getArmeEquipee() {
        return armeEquipee;
    }

    /**
     * Permet au héros d'attaquer un autre héros avec son arme.
     * @param hero le héros adverse à attaquer
     */
    public void attaquerAvecArme(Hero hero) {
        if (armeEquipee != null) {
            hero.recevoirDegats(armeEquipee.getAttaque());
            armeEquipee.utiliser();
            if (armeEquipee.estDetruite()) {
                armeEquipee = null;
            }
        }
    }

    /**
     * Utilise le pouvoir spécial du héros selon sa classe.
     * Le coût du pouvoir est de 3 mana.
     * @param cible le joueur adverse (pour appliquer les effets)
     */
    public void utiliserPouvoir(Joueur cible) {
        if (manaCourant < 3) {
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

