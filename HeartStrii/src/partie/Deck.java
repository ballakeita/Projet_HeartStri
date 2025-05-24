package partie;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import carte.Carte; // importation de la classe mère

/**
 * Classe qui gère le deck du joueur.
 * On stocke ici toutes les cartes du joueur (serviteurs, sorts, armes...).
 * On peut ajouter, mélanger, tirer et afficher les cartes du deck.
 * @author Abdel Amir AMIRI, Balla KEITA, Ny Avo RAKOTOARIMAHEFASOA
 */
public class Deck {
    private List<Carte> cartes = new ArrayList<>(); // On stocke des Cartes (Serviteur, Arme, Sort...)

    /**
     * Permet de récupérer la liste des cartes du deck.
     * @return la liste des cartes
     */
    public List<Carte> getCartes() {
        return cartes;
    }

    /**
     * Constructeur : on initialise la liste vide.
     */
    public Deck() {
    }

    /**
     * Ajoute une carte dans le deck.
     * @param c la carte à ajouter
     */
    public void ajouterCarte(Carte c) {
        cartes.add(c);
    }

    /**
     * Tire une carte au hasard parmi celles du deck.
     * @return la carte tirée (et retirée du deck), ou null si le deck est vide
     */
    public Carte tirerCarteAleatoire() {
        if (cartes.isEmpty()) return null; // deck vide
        Random rand = new Random();
        int index = rand.nextInt(cartes.size()); 
        return cartes.remove(index); // retire et retourne la carte
    }

    /**
     * Affiche toutes les cartes présentes dans le deck.
     */
    public void afficherDeck() {
        for (Carte c : cartes) {
            c.afficherInfos();
        }
    }

    /**
     * Mélange les cartes du deck.
     */
    public void melangerDeck() {
        Collections.shuffle(cartes);
    }
}