package com.expertjava.collectionsstreams;


import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class StreamAdvancedDemo {

    public static void main(String[] args) {

        List<String> noms = List.of("Alice", "Bob", "Charlie", "David", "Eve");

        System.out.println("=== 1. Lazy vs Eager Evaluation ===");

        Stream<String> stream = noms.stream()
            .filter(n -> {
                System.out.println("Filtrage de : " + n);
                return n.startsWith("A");
            });

        System.out.println("Avant terminal operation...");
        List<String> resultat = stream.collect(Collectors.toList());
        System.out.println("Résultat final : " + resultat);  //Résultat final : [Alice]

        //Lazy : rien ne s'exécute tant qu’on n’appelle pas collect/forEach/etc.
        //c'est le collect() qui déclenche l’exécution.

        System.out.println("\n=== 2. map / filter / reduce ===");

        int totalLongueur = noms.stream()
            .filter(n -> n.length() > 3)
            .map(String::length)
            .reduce(0, Integer::sum);

        System.out.println("Somme des longueurs > 3 : " + totalLongueur);//Somme des longueurs > 3 : 17

        System.out.println("\n=== 3. parallelStream() ===");
        
       // List<String> noms = List.of("Alice", "Bob", "Charlie", "David", "Eve");
        
        noms.parallelStream()
            .map(n -> {
                System.out.println(Thread.currentThread().getName() + " traite " + n);
                return n.toUpperCase();
            })
            .forEach(System.out::println);

        //  À utiliser quand : gros volume + opération CPU-heavy (éviter IO ou petites listes)
        //  Attention à l'ordre et aux effets de bord
        // j'ai jamais eu le besoin / occasion d'utiliser le parallelStream ou le customer Collector  dans le cadre de mon travail 

        System.out.println("\n=== 4. Custom Collector ===");

        List<String> mots = List.of("java", "stream", "api");

        String phrase = mots.stream()
            .collect(new PhraseCollector());

        System.out.println("Phrase personnalisée : " + phrase); // [JAVA-STREAM-API]
    }

    //  Custom collector : join mots avec tirets et en majuscules
	/*
	 * supplier() → fournit le conteneur initial (ex: StringBuilder) accumulator() →
	 * ajoute un élément dans le conteneur combiner() → fusionne deux conteneurs
	 * (utile en parallèle) finisher() → transforme le conteneur en résultat final
	 * characteristics() → caractéristiques du collector (UNORDERED, CONCURRENT,
	 * etc.)
	 */
    
    static class PhraseCollector implements Collector<String, StringBuilder, String> {

        @Override
        public Supplier<StringBuilder> supplier() {
            return StringBuilder::new;
        }

        @Override
        public BiConsumer<StringBuilder, String> accumulator() {
            return (sb, mot) -> {
                if (sb.length() > 0) sb.append("-");
                sb.append(mot.toUpperCase());
            };
        }

        @Override
        public BinaryOperator<StringBuilder> combiner() {
            return (sb1, sb2) -> {
                if (sb1.length() > 0 && sb2.length() > 0) sb1.append("-");
                return sb1.append(sb2);
            };
        }

        @Override
        public Function<StringBuilder, String> finisher() {
            return sb -> "[" + sb.toString() + "]";
        }

        @Override
        public Set<Characteristics> characteristics() {
            return EnumSet.noneOf(Characteristics.class);
        }
    }
}
