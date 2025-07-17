package com.expertjava.pooavancee.typesclasses;


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
