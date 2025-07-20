package com.expertjava.collectionsstreams;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class StreamAdvancedDemo {
	
	 record Personne(String name, int age) {};

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
        // la reduce applique la fonction cumulativement à tous les éléments du stream en partant par 0
        //on peut aussi faire : reduce("", String::concat);  ou 
        //List<Prix> prixList = List.of(new Prix(10), new Prix(20));
        //Prix total = prixList.stream().reduce(new Prix(0), Prix::ajouter); 
        // ...
        //Prix ajouter(Prix autre) {
        // return new Prix(this.montant + autre.montant);
        //}...

        System.out.println("Somme des longueurs > 3 : " + totalLongueur);//Somme des longueurs > 3 : 17

        System.out.println("\n=== 3. parallelStream() ===");
        
       // List<String> noms = List.of("Alice", "Bob", "Charlie", "David", "Eve");
        
        Stream<String> str = noms.parallelStream()
            .map(n -> {
                System.out.println(Thread.currentThread().getName() + " traite " + n);
                return n.toUpperCase();
            });
        System.out.println(" rien n'est encore affiché => le foreach (eager) va déclencher l'affichage ");
        str.forEach(System.out::println);
       // Plusieurs threads sont utilisés, mais pas un par élément.

        //  À utiliser quand : gros volume + opération CPU-heavy (éviter IO ou petites listes)
        //  Attention à l'ordre et aux effets de bord
        // j'ai jamais eu le besoin / occasion d'utiliser le parallelStream ou le customer Collector  dans le cadre de mon travail 

        System.out.println("\n=== 4. Custom Collector ===");

        List<String> mots = List.of("java", "stream", "api");

        String phrase = mots.stream()
            .collect(new PhraseCollector());

        System.out.println("Phrase personnalisée : " + phrase); // [JAVA-STREAM-API]
        
        // un 2eme exemple , supposons qu'on a cette liste personne :
       
        List<Personne>  persons =List.of(  new Personne("Sacha", 4) , new Personne("Fat", 41) , new Personne("Haid", 26) );
        
        String stream2 = persons.stream().filter(p -> p.age() > 10)
                .collect(new PersonneCollector());
        System.out.println("Liste de personne personnalisée : " + stream2); // 
    }

    //  Custom collector : join mots avec tirets et en majuscules
	/*
	 * supplier() → fournit le conteneur initial (ex: StringBuilder)
	 * accumulator() → ajoute un élément dans le conteneur
	 * combiner() → fusionne deux conteneurs (utile en parallèle)
	 *  finisher() → transforme le conteneur en résultat final
	 * characteristics() → caractéristiques du collector (UNORDERED, CONCURRENT,
	 * etc.)
	 */
    //Collector<T, A, R>  => 
//    | Paramètre | Signification                                                              |
//    | --------- | -------------------------------------------------------------------------- |
//    | `T`       | Type des éléments dans le **Stream**                                       |
//    | `A`       | Type du **conteneur mutable** utilisé pendant la collecte (l’accumulateur) |
//    | `R`       | Type du **résultat final** retourné par le collector                       |

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
    
    static class PersonneCollector implements Collector<Personne, StringBuilder, String> {

        @Override
        public Supplier<StringBuilder> supplier() {
            return StringBuilder::new;
        }

        @Override
        public BiConsumer<StringBuilder, Personne> accumulator() {
            return (sb, p) -> {
                if (sb.length() > 0) sb.append("==,==");
                sb.append("nom : "+ p.name + " age : "+ p.age() );
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

//expected output 
//=== 1. Lazy vs Eager Evaluation ===
//Avant terminal operation...
//Filtrage de : Alice
//Filtrage de : Bob
//Filtrage de : Charlie
//Filtrage de : David
//Filtrage de : Eve
//Résultat final : [Alice]
//
//=== 2. map / filter / reduce ===
//Somme des longueurs > 3 : 17
//
//=== 3. parallelStream() ===
// rien n'est encore affiché => le foreach (eager) va dèclencher l'affichage 
//main traite Charlie
//CHARLIE
//main traite David
//DAVID
//ForkJoinPool.commonPool-worker-3 traite Alice
//ALICE
//ForkJoinPool.commonPool-worker-1 traite Bob
//BOB
//ForkJoinPool.commonPool-worker-2 traite Eve
//EVE
//
