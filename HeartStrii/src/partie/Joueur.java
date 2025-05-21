package partie;

import carte.*;
import java.util.ArrayList;
import java.util.List;

public class Joueur {
    private String pseudo;
    private Hero hero;
    private Deck deck;
    private List<Carte> main;
    private Plateau plateau;

    public Joueur(String pseudo, Hero hero) {
        this.pseudo = pseudo;
        this.hero = hero;
        this.deck = new Deck();
        this.main = new ArrayList<>();
        this.plateau = new Plateau();
    }

    public String getPseudo() {
        return pseudo;
    }

    public Hero getHero() {
        return hero;
    }

    public Deck getDeck() {
        return deck;
    }

    public List<Carte> getMain() {
        return main;
    }

    public Plateau getPlateau() {
        return plateau;
    }

    public void ajouterCarteDeck(Serviteur s) {
        deck.ajouterCarte(s);
    }

    public void piocherCarte() {
        Carte c = deck.tirerCarteAleatoire();
        if (c != null) {
            main.add(c);
        }
    }

    public void invoquerCarte(Serviteur s) {
        if (main.contains(s) && hero.getManaCourant() >= s.getMana()) {
            plateau.ajouterServiteur(s);
            main.remove(s);
            hero.reduireMana(s.getMana());
            System.out.println(pseudo + " invoque " + s.getNom());
        }
    }

    public void utiliserMana(int cout) {
        hero.reduireMana(cout);
    }

    public void afficherMain() {
        for (Carte c : main) {
            c.afficherInfos();
        }
    }
    public void jouerSort(Sort sort, Object cible) {
        if (main.contains(sort) && hero.getManaCourant() >= sort.getMana()) {
            sort.appliquerEffet(cible);
            hero.reduireMana(sort.getMana());
            main.remove(sort);
        }
    }

}
