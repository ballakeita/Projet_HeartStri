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
    public void afficherPlateau() {
        if (serviteurs.isEmpty()) {
            System.out.println("🔹 Plateau vide");
            return;
        }

        for (int i = 0; i < serviteurs.size(); i++) {
            System.out.print((i + 1) + " ➜ ");
            serviteurs.get(i).afficherInfos();
        }
    }

}
