package partie;

import carte.Serviteur;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe qui gère le plateau du joueur (les serviteurs posés).
 * On utilise cette classe pour stocker et manipuler les serviteurs en jeu.
 * Toutes les actions liées au plateau (ajout, retrait, affichage) passent par ici.
 * @author Abdel Amir AMIRI, Balla KEITA, Ny Avo RAKOTOARIMAHEFASOA
 */
public class Plateau {
    private List<Serviteur> serviteurs;

    /**
     * Constructeur du plateau : on part d'un plateau vide.
     */
    public Plateau() {
        serviteurs = new ArrayList<>();
    }

    /**
     * Ajoute un serviteur sur le plateau.
     * @param s le serviteur à ajouter
     */
    public void ajouterServiteur(Serviteur s) {
        serviteurs.add(s);
    }

    /**
     * Retire un serviteur du plateau.
     * @param s le serviteur à retirer
     */
    public void retirerServiteur(Serviteur s) {
        serviteurs.remove(s);
    }

    /**
     * Retourne la liste des serviteurs présents sur le plateau.
     * @return liste des serviteurs
     */
    public List<Serviteur> getServiteurs() {
        return serviteurs;
    }

    /**
     * Vérifie si le plateau est vide.
     * @return true si aucun serviteur, false sinon
     */
    public boolean estVide() {
        return serviteurs.isEmpty();
    }

    /**
     * Affiche le plateau et les serviteurs présents.
     */
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
