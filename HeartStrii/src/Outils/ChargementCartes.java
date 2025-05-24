package Outils;

import carte.*;
import java.io.*;
import java.util.*;

/**
 * Classe utilitaire pour charger des cartes depuis un fichier CSV.
 * On utilise cette classe pour générer les decks à partir des fichiers de ressources.
 * @author Abdel Amir AMIRI, Balla KEITA, Ny Avo RAKOTOARIMAHEFASOA
 */
public class ChargementCartes {

    /**
     * Méthode qui lit un fichier CSV et crée une liste de cartes selon le type demandé.
     * @param cheminFichier Le nom du fichier CSV à lire (doit être dans les ressources du projet)
     * @param typeCarte Le type de carte à charger ("serviteur", "sort" ou "arme")
     * @return Une liste de cartes du type demandé, lue et construite à partir du fichier
     */
    public static List<Carte> chargerCartesDepuisCSV(String cheminFichier, String typeCarte) {
        List<Carte> cartes = new ArrayList<>();

        try {
            // On récupère le fichier dans les ressources du projet
            InputStream is = ChargementCartes.class.getClassLoader().getResourceAsStream(cheminFichier);
            if (is == null) {
                // Si le fichier n'existe pas, on affiche un message et on retourne une liste vide
                System.out.println("x Fichier non trouvé dans les ressources : " + cheminFichier);
                return cartes;
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String ligne = br.readLine(); // On saute la première ligne (en-tête du CSV)

            // On lit chaque ligne du fichier
            while ((ligne = br.readLine()) != null) {
                ligne = ligne.trim();
                if (ligne.isEmpty()) continue; // On ignore les lignes vides

                String[] tokens = ligne.split(",");
                if (tokens.length < 4) {
                    // Si la ligne n'a pas assez de champs, on affiche un message et on passe à la suivante
                    System.out.println("x Ligne invalide : " + ligne);
                    continue;
                }

                // On crée la carte selon le type demandé
                switch (typeCarte.toLowerCase()) {
                    case "serviteur":
                        cartes.add(new Serviteur(
                            tokens[0].trim(),
                            Integer.parseInt(tokens[1].trim()),
                            Integer.parseInt(tokens[2].trim()),
                            Integer.parseInt(tokens[3].trim())
                        ));
                        break;

                    case "sort":
                        Sort.TypeEffet effet = Sort.TypeEffet.valueOf(tokens[2].trim().toUpperCase());
                        cartes.add(new Sort(
                            tokens[0].trim(),
                            Integer.parseInt(tokens[1].trim()),
                            effet,
                            Integer.parseInt(tokens[3].trim())
                        ));
                        break;

                    case "arme":
                        cartes.add(new Arme(
                            tokens[0].trim(),
                            Integer.parseInt(tokens[1].trim()),
                            Integer.parseInt(tokens[2].trim()),
                            Integer.parseInt(tokens[3].trim())
                        ));
                        break;

                    default:
                        // Si le type de carte n'est pas reconnu
                        System.out.println("? Type de carte inconnu : " + typeCarte);
                        break;
                }
            }

            br.close();

        } catch (IOException e) {
            // Si une erreur de lecture survient
            System.out.println("x Erreur de lecture : " + cheminFichier);
            e.printStackTrace();
        } catch (Exception e) {
            // Si une erreur de format survient
            System.out.println("x Erreur de format dans le fichier : " + cheminFichier);
            e.printStackTrace();
        }

        // On retourne la liste des cartes chargées
        return cartes;
    }
}