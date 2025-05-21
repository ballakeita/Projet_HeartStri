package test;

import carte.Serviteur;
import partie.Combat;
import partie.Deck;
import partie.Joueur;
import partie.Hero;
import partie.TypeHero;

//Programme principal
public class Main {
public static void main(String[] args) {
   // On crée un nouveau deck vide
   Deck deck = new Deck();

   // On y ajoute manuellement quelques serviteurs
   deck.ajouterCarte(new Serviteur("Loup Alpha", 2, 3, 60));
   deck.ajouterCarte(new Serviteur("Yéti Grincheux", 4, 4, 60));
   deck.ajouterCarte(new Serviteur("Soldat d'élite", 3, 3, 50));
   deck.ajouterCarte(new Serviteur("Golem de pierre", 5, 5, 80));

   // On affiche le contenu du deck
   System.out.println("📦 Deck initial :");
   deck.afficherDeck();

   // On tire deux cartes au hasard pour les faire combattre
   Serviteur s1 = deck.tirerCarteAleatoire();
   Serviteur s2 = deck.tirerCarteAleatoire();

   // On vérifie qu'on n’a pas tiré deux fois la même carte
   while (s1 == s2) {
       s2 = deck.tirerCarteAleatoire();
   }
   
   Joueur j1 = new Joueur("Alice", new Hero("Jaina", TypeHero.MAGE));
   Joueur j2 = new Joueur("Bob", new Hero("Rexxar", TypeHero.CHASSEUR));

   j1.getHero().utiliserPouvoir(j2); // Mage inflige 1 dégât
   j2.getHero().utiliserPouvoir(j1); // Chasseur inflige 2 dégâts
   
//   tester les effets
   j1.getHero().augmenterMana(); // Donne 2 de mana à Jaina
   j2.getHero().augmenterMana(); // Donne 2 de mana à Rexxar



   // On affiche les deux combattants
   System.out.println("\n🎯 Serviteurs sélectionnés :");
   s1.afficherInfos();
   s2.afficherInfos();

   // On lance le combat
   System.out.println("\n=== COMBAT ===");
   Combat.lancerCombat(s1, s2);
}


}