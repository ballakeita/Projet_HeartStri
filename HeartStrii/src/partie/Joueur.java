package partie;

import carte.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe qui gère un joueur dans la partie.
 * On retrouve ici le pseudo, le héros, le deck, la main et le plateau du joueur.
 * Toutes les actions du joueur (piocher, invoquer, jouer un sort...) sont centralisées ici.
 * @author Abdel Amir AMIRI, Balla KEITA, Ny Avo RAKOTOARIMAHEFASOA
 */
public class Joueur {
    private String pseudo;
    private Hero hero;
    private Deck deck;
    private List<Carte> main;
    private Plateau plateau;

    /**
     * Constructeur du joueur.
     * @param pseudo Le nom du joueur
     * @param hero Le héros choisi par le joueur
     */
    public Joueur(String pseudo, Hero hero) {
        this.pseudo = pseudo;
        this.hero = hero;
        this.deck = new Deck();
        this.main = new ArrayList<>();
        this.plateau = new Plateau();
    }

    /**
     * Retourne le pseudo du joueur.
     * @return pseudo
     */
    public String getPseudo() {
        return pseudo;
    }

    /**
     * Retourne le héros du joueur.
     * @return le héros
     */
    public Hero getHero() {
        return hero;
    }

    /**
     * Retourne le deck du joueur.
     * @return le deck
     */
    public Deck getDeck() {
        return deck;
    }

    /**
     * Retourne la main du joueur (cartes en main).
     * @return la liste des cartes en main
     */
    public List<Carte> getMain() {
        return main;
    }

    /**
     * Retourne le plateau du joueur (serviteurs en jeu).
     * @return le plateau
     */
    public Plateau getPlateau() {
        return plateau;
    }

    /**
     * Ajoute un serviteur au deck du joueur.
     * @param s le serviteur à ajouter
     */
    public void ajouterCarteDeck(Serviteur s) {
        deck.ajouterCarte(s);
    }

    /**
     * Pioche une carte du deck et l'ajoute à la main.
     * Affiche un message si le deck est vide.
     */
    public void piocherCarte() {
        Carte c = deck.tirerCarteAleatoire();
        if (c != null) {
            main.add(c);
            System.out.println(pseudo + " pioche : " + c.getNom()); // Affiche le nom et les infos de la carte
        } else {
            System.out.println(pseudo + " ne peut pas piocher car le deck est vide.");
        }
    }

    /**
     * Invoque un serviteur depuis la main sur le plateau si le joueur a assez de mana.
     * @param s le serviteur à invoquer
     */
    public void invoquerCarte(Serviteur s) {
        if (main.contains(s) && hero.getManaCourant() >= s.getMana()) {
            plateau.ajouterServiteur(s);
            main.remove(s);
            hero.reduireMana(s.getMana());
            System.out.println(pseudo + " invoque " + s.getNom());
        }
    }

    /**
     * Utilise du mana (retire le coût indiqué au héros).
     * @param cout le coût en mana à retirer
     */
    public void utiliserMana(int cout) {
        hero.reduireMana(cout);
    }

    /**
     * Affiche la main du joueur (toutes les cartes en main).
     */
    public void afficherMain() {
        if (main.isEmpty()) {
            System.out.println("Votre main est vide.");
        } else {
            for (int i = 0; i < main.size(); i++) {
                System.out.print((i + 1) + " - ");
                main.get(i).afficherInfos(); // Affiche les infos de chaque carte dans la main
            }
        }
    }

    /**
     * Joue un sort sur une cible (serviteur ou héros).
     * Retire le sort de la main et le coût en mana au héros.
     * @param sort le sort à jouer
     * @param cible la cible du sort (Serviteur ou Hero)
     */
    public void jouerSort(Sort sort, Object cible) {
        if (main.contains(sort) && hero.getManaCourant() >= sort.getMana()) {
            sort.appliquerEffet(cible);
            hero.reduireMana(sort.getMana());
            main.remove(sort);
        }
    }
}
