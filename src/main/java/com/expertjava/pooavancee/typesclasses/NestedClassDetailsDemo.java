package com.expertjava.pooavancee.typesclasses;


/**
 * Démonstration détaillée des types de classes imbriquées en Java :
 * - Classe externe (Outer)
 * - Classe interne (Inner)
 * - Classe imbriquée statique (Static Nested)
 */

// NestedClassDetailsDemo est une Classe externe 
public class NestedClassDetailsDemo {

    private String instanceMessage = "Message d'instance de Outer";
    private static String staticMessage = "Message statique de Outer";

    // 🔹 Inner :Classe interne (non statique)
    class InnerClass {
        void afficher() {
            System.out.println("➡️ Classe Interne (Inner)");
            System.out.println("Accès à instanceMessage : " + instanceMessage);
            System.out.println("Accès à staticMessage : " + staticMessage);
        }
    }

    // 🔸 Classe imbriquée statique
    static class StaticNestedClass {
        void afficher() {
            System.out.println("➡️ Classe Imbriquée Statique (Static Nested)");
            // System.out.println(instanceMessage); ❌ Erreur : pas accessible
            System.out.println("Accès uniquement à staticMessage : " + staticMessage);
        }
    }

    public static void main(String[] args) {

        System.out.println("===== Classe Imbriquée Statique =====");
        // ✅ Pas besoin d'instance de Outer
		StaticNestedClass nested = new StaticNestedClass();
        nested.afficher();

        System.out.println("\n===== Classe Interne =====");
        // ✅ Nécessite une instance de la classe externe
        NestedClassDetailsDemo outer = new NestedClassDetailsDemo();
        InnerClass inner = outer.new InnerClass();
        inner.afficher();
    }
}
