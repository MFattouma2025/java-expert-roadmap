package com.expertjava.pooavancee.typesclasses;

/**
 * Types de classes imbriquées en Java
 * 
1-Classe imbriquée statique (Static Nested Class)
Définie avec le mot clé static.
Ne peut pas accéder aux membres non statiques de la classe externe.
Se comporte comme une classe “normale” dans le scope de la classe externe.

2-Classe interne (Inner Class)
Classe imbriquée non statique.
Chaque instance est liée à une instance de la classe externe.
Peut accéder directement aux membres (même privés) de la classe externe.

3-Classe locale (Local Class)
Définie à l’intérieur d’une méthode.
Accessible uniquement dans cette méthode.

4-Classe anonyme (Anonymous Class)
Classe locale sans nom, définie dans une expression.


 */
//Classe externe
public class ClassTypesDemo {

 // 1. Classe interne (non statique)
 class InnerClass {
     void afficher() {
         System.out.println("Je suis une classe interne.");
     }
 }

 // 2. Classe interne statique
 static class StaticNestedClass {
     void afficher() {
         System.out.println("Je suis une classe interne statique.");
     }
 }

 void utiliserLesClasses() {
     // 3. Classe locale (dans une méthode)
     class LocalClass {
         void afficher() {
             System.out.println("Je suis une classe locale dans une méthode.");
         }
     }

     LocalClass locale = new LocalClass();
     locale.afficher();
 }

 void utiliserClasseAnonyme() {
     // 4. Classe anonyme (implémente une interface à la volée)
     Runnable runnable = new Runnable() {
         public void run() {
             System.out.println("Je suis une classe anonyme exécutée dans un thread.");
         }
     };
     runnable.run();
 }
// L'implemntation des interfaces en java se fait par implements , classe anonyme ou avec une expression lambda si interface fonctionnelle 
 public static void main(String[] args) {
     ClassTypesDemo demo = new ClassTypesDemo();

     // Utilisation de la classe interne
     InnerClass inner = demo.new InnerClass();
     inner.afficher();

     // Utilisation de la classe interne statique
     StaticNestedClass nested = new StaticNestedClass();
     nested.afficher();

     // Classe locale
     demo.utiliserLesClasses();

     // Classe anonyme
     demo.utiliserClasseAnonyme();
 }
}
