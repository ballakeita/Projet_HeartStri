package Outils;

import carte.*;
import java.io.*;
import java.util.*;

public class ChargementCartes {

    public static List<Carte> chargerCartesDepuisCSV(String cheminFichier, String typeCarte) {
        List<Carte> cartes = new ArrayList<>();

        try {
            InputStream is = ChargementCartes.class.getClassLoader().getResourceAsStream(cheminFichier);
            if (is == null) {
                System.out.println("❌ Fichier non trouvé dans les ressources : " + cheminFichier);
                return cartes;
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String ligne = br.readLine(); // Ignorer l'en-tête

            while ((ligne = br.readLine()) != null) {
                ligne = ligne.trim();
                if (ligne.isEmpty()) continue;

                String[] tokens = ligne.split(",");
                if (tokens.length < 4) {
                    System.out.println("❌ Ligne invalide : " + ligne);
                    continue;
                }

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
                        System.out.println("❓ Type de carte inconnu : " + typeCarte);
                        break;
                }
            }

            br.close();

        } catch (IOException e) {
            System.out.println("❌ Erreur de lecture : " + cheminFichier);
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("❌ Erreur de format dans le fichier : " + cheminFichier);
            e.printStackTrace();
        }

        return cartes;
    }
}