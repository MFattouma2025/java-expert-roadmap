package com.expertjava.pooavancee.heritagedemo;


import java.util.List;
import java.util.function.Predicate;
import java.util.function.Function;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class PredicateConsumerFunctionDemo {

    public static void main(String[] args) {
        List<String> mots = List.of("chat", "éléphant", "lion", "tigre", "girafe");

        // Predicate : filtre les mots de plus de 4 lettres
        Predicate<String> plusDeQuatre = s -> s.length() > 4;

        // Function : transforme un mot en sa longueur
        Function<String, Integer> longueur = String::length;

        // Consumer : affiche le mot et sa longueur
        Consumer<String> afficherAvecLongueur = s -> 
            System.out.println(s + " (longueur : " + s.length() + ")");

        // Pipeline Stream combiné
        List<Integer> longueurs = mots.stream()
            .filter(plusDeQuatre)                 //Predicate // garde les mots > 4 lettres
            .peek(afficherAvecLongueur)           //Consumer //  affiche les mots filtrés
            .map(longueur)                        //Function // convertit en longueur
            .collect(Collectors.toList());       // collecte les longueurs

        System.out.println("Longueurs collectées : " + longueurs);
    }
    
    //output :
//    éléphant (longueur : 8)
//    tigre (longueur : 5)
//    girafe (longueur : 6)
//    Longueurs collectées : [8, 5, 6]
}
