package partie;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import carte.Carte; // importation de la classe mère

// Le deck contient les cartes du joueur
public class Deck {
    private List<Carte> cartes = new ArrayList<>(); // On stocke des Cartes (Serviteur, Arme, Sort...)

    public List<Carte> getCartes() {
        return cartes;
    }

    // Constructeur : on initialise la liste vide
    public Deck() {
    }

    // Ajouter une carte dans le deck
    public void ajouterCarte(Carte c) {
        cartes.add(c);
    }

    // Tire une carte au hasard parmi celles du deck
    public Carte tirerCarteAleatoire() {
        if (cartes.isEmpty()) return null; // deck vide
        Random rand = new Random();
        int index = rand.nextInt(cartes.size()); 
        return cartes.remove(index); // retire et retourne la carte
    }

    // Affiche toutes les cartes présentes dans le deck
    public void afficherDeck() {
        for (Carte c : cartes) {
            c.afficherInfos();
        }
    }
    
    // Mélange les cartes du deck
    public void melangerDeck() {
        Collections.shuffle(cartes);
    }
}