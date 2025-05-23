package partie;


import carte.Serviteur;
import java.util.ArrayList;
import java.util.List;

public class Plateau {
    private List<Serviteur> serviteurs;

    public Plateau() {
        serviteurs = new ArrayList<>();
    }

    public void ajouterServiteur(Serviteur s) {
        serviteurs.add(s);
    }

    public void retirerServiteur(Serviteur s) {
        serviteurs.remove(s);
    }

    public List<Serviteur> getServiteurs() {
        return serviteurs;
    }

    public boolean estVide() {
        return serviteurs.isEmpty();
    }
 // Méthode pour afficher tous les serviteurs actuellement posés sur le plateau du joueur
    public void afficherPlateau() {
        // On récupère la liste des serviteurs posés sur le plateau
        List<Serviteur> serviteurs = getServiteurs();

        // On parcourt chaque serviteur et on affiche ses informations
        for (int i = 0; i < serviteurs.size(); i++) {
            // i + 1 : pour afficher un numéro humain (1, 2, 3...)
            // getNom() : nom du serviteur
            // getAttaque() : puissance d’attaque du serviteur
            // getVie() : points de vie restants du serviteur
            System.out.println((i + 1) + " - " + serviteurs.get(i).getNom() +
                               " | Attaque: " + serviteurs.get(i).getAttaque() +
                               " | Vie: " + serviteurs.get(i).getVie());
        }
    }

}
