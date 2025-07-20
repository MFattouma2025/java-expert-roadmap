package com.expertjava.collectionsstreams;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
/*| Type d’opération  | Exemples                                  | Lazy ? | Déclenche l’exécution ? |
| ----------------- | ----------------------------------------- | ------ | ----------------------- |
| **Intermédiaire** | `map`, `filter`, `peek`, `sorted`         | ✅ Oui  | ❌ Non                   |
| **Terminale**     | `collect`, `forEach`, `count`, `anyMatch` | ❌ Non  | ✅ Oui                   |*/

// les stream sont lazy alors que les list.of et arraylist sont eager...

public class LazyVsEagerDemo {

    public static void main(String[] args) {

        List<String> noms = List.of("Alice", "Bob", "Charlie", "Anna");

        System.out.println("=== 1. Construction du stream (lazy) ===");

        Stream<String> stream = noms.stream()
            .filter(n -> {
                System.out.println("Filtrage : " + n); // s'affichera plus tard
                return n.startsWith("A");
            });

        System.out.println("Rien ne s'est exécuté jusqu'ici.");

        System.out.println("\n=== 2. Déclenchement par une opération terminale ===");

        List<String> resultat = stream.collect(Collectors.toList());

        System.out.println("Résultat : " + resultat);

        System.out.println("\n=== 3. Exécution optimisée avec short-circuit (anyMatch) ===");

        noms.stream()
            .peek(n -> System.out.println("Test : " + n))
            .anyMatch(n -> n.equals("Bob")); // dès que trouvé, ça s'arrête
    }
}

