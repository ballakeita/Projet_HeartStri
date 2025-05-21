package partie;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import carte.Serviteur;

// Le deck contient les cartes du joueur
public class Deck {
    private List<Serviteur> cartes;

    // Constructeur : on initialise la liste vide
    public Deck() {
        cartes = new ArrayList<>();
    }

    // Ajouter un serviteur dans le deck
    public void ajouterCarte(Serviteur s) {
        cartes.add(s);
    }

    // Tire une carte au hasard parmi celles du deck
    public Serviteur tirerCarteAleatoire() {
        if (cartes.isEmpty()) return null; // deck vide
        Random rand = new Random();
        int index = rand.nextInt(cartes.size()); // index entre 0 et size - 1
        return cartes.get(index);
    }

    // Affiche toutes les cartes présentes dans le deck
    public void afficherDeck() {
        for (Serviteur s : cartes) {
            s.afficherInfos();
        }
    }
}