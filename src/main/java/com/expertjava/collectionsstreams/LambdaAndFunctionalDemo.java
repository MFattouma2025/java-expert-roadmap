package com.expertjava.collectionsstreams;


import java.util.*;
import java.util.stream.Collectors;

public class LambdaAndFunctionalDemo {

    static int compteur = 0; // utilisé dans une fonction impure

    public static void main(String[] args) {

        System.out.println("=== 1. Lambda simple ===");
        // Syntaxe lambda : (param) -> expression
        List<String> noms = List.of("Alice", "Bob", "Charlie");

        noms.forEach(n ->  {System.out.println("Bonjour " + n) ;System.out.println("re Bonjour " + n);  });

        System.out.println("\n=== 2. Fonction pure vs impure ===");

        // Fonction pure : pas d'effet de bord
        System.out.println("Fonction pure (2 + 3) = " + additionPure(2, 3)); // 5

        // Fonction impure : modifie compteur : une fonction est impur lorsque'elle utilise une variable externe 
        System.out.println("Fonction impure (3) = " + additionImpure(3)); // 3  
        System.out.println("Encore impure (3) = " + additionImpure(3));   // 6 (compteur a changé)

        System.out.println("\n=== 3. Pipeline avec fonction pure ===");

        List<Integer> nombres = List.of(1, 2, 3, 4, 5);

        List<Integer> doubles = nombres.stream()
            .map(n -> n * 2) //  pure : pas d’effet de bord
            .collect(Collectors.toList());

        System.out.println("Doubles : " + doubles);

        System.out.println("\n=== 4. Pipeline avec fonction impure (à éviter) ===");

        List<Integer> sorties = nombres.stream()
            .map(LambdaAndFunctionalDemo::mapAvecEffetDeBord)
            .collect(Collectors.toList());

        System.out.println("Résultat impure (side effects) : " + sorties);
        System.out.println("Compteur final = " + compteur); // Effet de bord : compteur a changé !
    }
    
    

    // ✅ Fonction pure : ne dépend que des paramètres, ne modifie rien
    public static int additionPure(int a, int b) {
        return a + b;
    }

    // ❌ Fonction impure : modifie une variable externe
    public static int additionImpure(int x) {
        compteur += x;
        return compteur;
    }

    // ❌ Fonction impure utilisée dans un pipeline
    public static int mapAvecEffetDeBord(int x) {
        compteur++;
        return x * compteur; // résultat dépend de compteur → pas stable
    }
}

//expected output :
//=== 1. Lambda simple ===
//Bonjour Alice
//re Bonjour Alice
//Bonjour Bob
//re Bonjour Bob
//Bonjour Charlie
//re Bonjour Charlie
//
//=== 2. Fonction pure vs impure ===
//Fonction pure (2 + 3) = 5
//Fonction impure (3) = 3
//Encore impure (3) = 6
//
//=== 3. Pipeline avec fonction pure ===
//Doubles : [2, 4, 6, 8, 10]
//
//=== 4. Pipeline avec fonction impure (à éviter) ===
//Résultat impure (side effects) : [7, 16, 27, 40, 55]
//Compteur final = 11
