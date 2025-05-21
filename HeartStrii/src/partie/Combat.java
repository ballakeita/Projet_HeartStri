package partie;

import carte.Serviteur;

//Cette classe permet de simuler un combat entre deux serviteurs
public class Combat {

public static void lancerCombat(Serviteur s1, Serviteur s2) {
 System.out.println(" Début du combat entre " + s1.getNom() + " et " + s2.getNom());

 // Combat tour par tour
 while (!s1.estMort() && !s2.estMort()) {
     // Le premier attaque le second
     s2.subirDegats(s1.getAttaque());
     System.out.println(s1.getNom() + " attaque " + s2.getNom() + " ➜ PV restants : " + s2.getVie());

     // Si le second est mort, on arrête là
     if (s2.estMort()) break;

     // Le second riposte
     s1.subirDegats(s2.getAttaque());
     System.out.println(s2.getNom() + " riposte ➜ PV restants : " + s1.getVie());
 }

 // Fin du combat : on annonce le vainqueur
 System.out.println("💀 Le combat est terminé !");
 if (s1.estMort()) {
     System.out.println(s1.getNom() + " est mort !");
 }
 if (s2.estMort()) {
     System.out.println(s2.getNom() + " est mort !");
 }
}

}