package partie;

import carte.Serviteur;
import java.util.ArrayList;
import java.util.List;

// Classe qui gère le plateau du joueur (les serviteurs posés)
public class Plateau {
    private List<Serviteur> serviteurs;

    // Constructeur du plateau
    public Plateau() {
        serviteurs = new ArrayList<>();
    }

    // Ajoute un serviteur sur le plateau
    public void ajouterServiteur(Serviteur s) {
        serviteurs.add(s);
    }

    // Retire un serviteur du plateau
    public void retirerServiteur(Serviteur s) {
        serviteurs.remove(s);
    }

    // Retourne la liste des serviteurs sur le plateau
    public List<Serviteur> getServiteurs() {
        return serviteurs;
    }

    // Vérifie si le plateau est vide
    public boolean estVide() {
        return serviteurs.isEmpty();
    }

    // Affiche le plateau et les serviteurs présents
    public void afficherPlateau() {
        if (serviteurs.isEmpty()) {
            System.out.println("• Plateau vide");
            return;
        }

        for (int i = 0; i < serviteurs.size(); i++) {
            System.out.print((i + 1) + " - ");
            serviteurs.get(i).afficherInfos();
        }
    }
}
