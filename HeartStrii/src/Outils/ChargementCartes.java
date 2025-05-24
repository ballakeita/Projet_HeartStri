package Outils;

import carte.*;
import java.io.*;
import java.util.*;

public class ChargementCartes {
    public static List<Carte> chargerCartesDepuisCSV(String cheminFichier, String typeCarte) {
        List<Carte> cartes = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(cheminFichier))) {
            String ligne;
            while ((ligne = br.readLine()) != null) {
                String[] tokens = ligne.split(";");
                switch (typeCarte) {
                    case "serviteur":
                        cartes.add(new Serviteur(tokens[0], Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3])));
                        break;
                    case "sort":
                        Sort.TypeEffet effet = Sort.TypeEffet.valueOf(tokens[2].toUpperCase());
                        cartes.add(new Sort(tokens[0], Integer.parseInt(tokens[1]), effet, Integer.parseInt(tokens[3])));
                        break;
                    case "arme":
                        cartes.add(new Arme(tokens[0], Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3])));
                        break;
                }
            }
        } catch (IOException e) {
            System.out.println("Erreur lors de la lecture du fichier : " + cheminFichier);
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Erreur de format ou de données dans le fichier : " + cheminFichier);
            e.printStackTrace();
        }
        return cartes;
    }
}