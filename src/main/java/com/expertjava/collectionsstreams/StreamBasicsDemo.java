package com.expertjava.collectionsstreams;


import java.util.*;
import java.util.stream.Collectors;


public class StreamBasicsDemo {

    public static void main(String[] args) {

        List<String> noms = Arrays.asList("Ahmed", "Bechir", "Charlie", "Ahmed", "David", "Eve");

        System.out.println("=== 1. Filter ===");
        List<String> startsWithA = noms.stream()
            .filter(n -> n.startsWith("A"))
            .collect(Collectors.toList());
        System.out.println("Noms commençant par A : " + startsWithA); //=>Noms commençant par A : [Ahmed, Ahmed]

        System.out.println("\n=== 2. Map ===");
        List<Integer> longueurs = noms.stream()
            .map(String::length)
            .collect(Collectors.toList());
        System.out.println("Longueur de chaque nom : " + longueurs);//=>Longueur de chaque nom : [5, 6, 7, 5, 5, 3]

        System.out.println("\n=== 3. Distinct ===");
        List<String> uniques = noms.stream()
            .distinct()
            .collect(Collectors.toList());
        System.out.println("Noms sans doublons : " + uniques);//=>Noms sans doublons : [Ahmed, Bechir, Charlie, David, Eve]

        System.out.println("\n=== 4. Sorted ===");
        List<String> triAlpha = noms.stream()
            .sorted()
            .collect(Collectors.toList());
        System.out.println("Tri alphabétique : " + triAlpha);//=>Tri alphabétique : [Ahmed, Ahmed, Bechir, Charlie, David, Eve]


        System.out.println("\n=== 5. Limit & Skip ===");
        List<String> deuxPremiers = noms.stream()
            .limit(2)
            .collect(Collectors.toList());
        System.out.println("Deux premiers : " + deuxPremiers);//=>Deux premiers : [Ahmed, Bechir]

        List<String> sautDeux = noms.stream()
            .skip(2)
            .collect(Collectors.toList());
        System.out.println("Après avoir sauté 2 : " + sautDeux);//=>[Charlie, Ahmed, David, Eve]

        System.out.println("\n=== 6. Reduce ===");
        Optional<String> concat = noms.stream()
            .reduce((acc, nom) -> acc + ", " + nom);
        concat.ifPresent(r -> System.out.println("Concaténation : " + r));//=>Concaténation : Ahmed, Bechir, Charlie, Ahmed, David, Eve
       
        //Autre exemple avec reduce *********************************************:
        List<Integer> nombres = List.of(1, 2, 3, 4, 5);
        int somme = nombres.stream()
            .reduce(0, (acc, val) -> acc + val);
        System.out.println(somme); // => 15
		/*
		 * acc = accumulateur → commence à 0 
		 * val = chaque élément de la liste Le stream
		 * calcule : (((0 + 1) + 2) + 3) + 4 + 5 = 15
		 */
        List<String> mots = List.of("Java", "Stream", "API");
        Optional<String> phrase = mots.stream()
            .reduce((a, b) -> a + " " + b);
        phrase.ifPresent(System.out::println); // => Java Stream API
       //  Ici pas de valeur initiale → le résultat est un Optional<String>
        
        int produit = List.of(2, 3, 4).stream()
        	    .reduce(1, (a, b) -> a * b); // 2*3*4 = 24
        //Quand utiliser l'optionnel  :
        //| Forme de reduce                          | Résultat           | Quand l’utiliser                                  |
        //| ---------------------------------------- | ------------------ | ------------------------------------------------- |
        //| `stream.reduce((a, b) -> ...)`           | `Optional<T>`      | ❗ **Sans valeur initiale** → risque de vide       |
        //| `stream.reduce(identity, (a, b) -> ...)`  | `T` (pas Optional) | ✔ **Avec valeur initiale** → toujours un résultat |
           
       //*****************************************************************************:
        
        System.out.println("\n=== 7. Match (any/all/none) ===");
        
        boolean anyA = noms.stream().anyMatch(n -> n.startsWith("A"));
        boolean allShort = noms.stream().allMatch(n -> n.length() <= 10);
        boolean noneZ = noms.stream().noneMatch(n -> n.startsWith("Z"));
        
        System.out.println("Quelqu’un commence par A ? " + anyA); //=> Quelqu’un commence par A ? true
        System.out.println("Tous ≤ 10 caractères ? " + allShort);//=> Tous ≤ 10 caractères ? true
        System.out.println("Personne ne commence par Z ? " + noneZ);//=> Personne ne commence par Z ? true

        System.out.println("\n=== 8. Collect to Set ===");
        
        Set<String> set = noms.stream()
            .collect(Collectors.toSet());
        System.out.println("Vers un Set (pas de doublon) : " + set);//=>Vers un Set (pas de doublon) : [Ahmed, Eve, Charlie, David, Bechir]

        System.out.println("\n=== 9. Statistiques (count, min, max) ===");
        long count = noms.stream().count();
        Optional<String> min = noms.stream().min(String::compareTo);
        Optional<String> max = noms.stream().max(Comparator.comparing(String::length));
        System.out.println("Nombre d'éléments : " + count);//=>Nombre d'éléments : 6
        System.out.println("Min (alpha) : " + min.orElse("n/a"));//=>Min (alpha) : Ahmed
        System.out.println("Max (longueur) : " + max.orElse("n/a"));//=>Max (longueur) : Charlie
    }
}
