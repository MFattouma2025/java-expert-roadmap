package com.expertjava.collectionsstreams;


import java.util.*;
import java.util.stream.*;

public class StreamGroupingPartitionFlatMapDemo {

    static class Person {
        String nom;
        int age;
        String ville;

        Person(String nom, int age, String ville) {
            this.nom = nom;
            this.age = age;
            this.ville = ville;
        }

        public String getNom() { return nom; }
        public int getAge() { return age; }
        public String getVille() { return ville; }

        @Override
        public String toString() {
            return nom + " (" + age + " ans, " + ville + ")";
        }
    }

    public static void main(String[] args) {
        List<Person> personnes = List.of(
            new Person("Alice", 30, "Paris"),
            new Person("Bob", 17, "Lyon"),
            new Person("Charlie", 40, "Paris"),
            new Person("David", 22, "Lyon"),
            new Person("Eve", 35, "Marseille")
        );
        
   /*     Difference entre groupingBy et partitioningBy
        | Critère           | `groupingBy()`                             | `partitioningBy()`                      |
        | ----------------- | ------------------------------------------ | --------------------------------------- |
        | Condition         | Basée sur **toute valeur** (clé)           | Basée sur un **booléen**                |
        | Nombre de groupes | Autant que de valeurs distinctes de la clé | Toujours **2** groupes : `true`/`false` |
        | Résultat          | `Map<K, List<T>>`                          | `Map<Boolean, List<T>>`                 |
        | Exemple typique   | Par ville, par rôle, par niveau            | Majeur vs Mineur, Valide vs Invalide    |

   */
        System.out.println("=== 1. GroupingBy : personnes par ville ===");
        Map<String, List<Person>> parVille = personnes.stream()
            .collect(Collectors.groupingBy(Person::getVille));

        parVille.forEach((ville, liste) -> {
            System.out.println(ville + " → " + liste);
        });
		/*
		 * Marseille → [Eve (35 ans, Marseille)] 
		 * Lyon → [Bob (17 ans, Lyon), David (22 ans, Lyon)] 
		 * Paris → [Alice (30 ans, Paris), Charlie (40 ans, Paris)]
		 */

        System.out.println("\n=== 2. PartitioningBy : majeurs / mineurs ===");
        Map<Boolean, List<Person>> majeursEtMineurs = personnes.stream()
            .collect(Collectors.partitioningBy(p -> p.getAge() >= 18));
        
        System.out.println("majeursEtMineurs   :"+majeursEtMineurs);
        
       
        majeursEtMineurs.forEach((estMajeur, liste) -> {
            System.out.println((estMajeur ? "Majeurs" : "Mineurs") + " → " + liste);
        });
		/*
		 * Mineurs → [Bob (17 ans, Lyon)] 
		 * Majeurs → [Alice (30 ans, Paris), Charlie (40 ans, Paris), David (22 ans, Lyon), Eve (35 ans, Marseille)]
		 */

        System.out.println("\n=== 3. flatMap : aplatir une liste de listes ===");

        List<List<String>> groupes = List.of(
            List.of("Java", "Python"),
            List.of("C++", "Rust"),
            List.of("Go")
        );

        List<String> toutAPlat = groupes.stream()
            .flatMap(List::stream)  // transforme Stream<List<String>> → Stream<String>
            .collect(Collectors.toList());

        System.out.println("Technos à plat : " + toutAPlat);//Technos à plat : [Java, Python, C++, Rust, Go]
    }
}
