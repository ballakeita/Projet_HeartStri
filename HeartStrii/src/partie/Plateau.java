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
}
