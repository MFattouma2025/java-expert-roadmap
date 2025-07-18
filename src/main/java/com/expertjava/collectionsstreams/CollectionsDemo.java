package com.expertjava.collectionsstreams;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
/*
| Type    | Classe          | Particularité principale               |
| ------- | --------------- | -------------------------------------- |
| `List`  | `ArrayList`     | Rapide en lecture, indexable           |
|         | `LinkedList`    | Meilleure pour insertions/suppressions |

| `Set`   | `HashSet`       | Non ordonné, pas de doublons           |
|         | `LinkedHashSet` | Ordre d'insertion préservé             |
|         | `TreeSet`       | Trié automatiquement (ordre naturel)   |

| `Queue` | `LinkedList`    | FIFO, utile pour file d'attente        |
| `Deque` | `ArrayDeque`    | Double-ended queue (pile ou file)      |

| `Map`   | `HashMap`       | Clé → valeur, rapide mais sans ordre   |
|         | `LinkedHashMap` | Ordre d’insertion                      |
|         | `TreeMap`       | Clés triées automatiquement            |*/


public class CollectionsDemo {
	
	record Person (String name , int age ) {};

    public static void main(String[] args) {

        System.out.println("=== LIST ===");
        // Liste ordonnée, accepte les doublons, accès par index
        List<String> arrayList = new ArrayList<>();
        arrayList.add("Alice");
        arrayList.add("Bob");
        arrayList.add("Alice"); // doublon autorisé
        System.out.println("ArrayList (rapide en lecture) : " + arrayList);
        //=> ArrayList (rapide en lecture) : [Alice, Bob, Alice]

        List<String> linkedList = new LinkedList<>();
        linkedList.add("Alice");
        linkedList.add("Bob");
        linkedList.add("Alice");
        System.out.println("LinkedList (optimisée pour insertion/suppression en milieu) : " + linkedList);
        //=> LinkedList (optimisée pour insertion/suppression en milieu) : [Alice, Bob, Alice]

        System.out.println("\n=== SET ===");
        // Ensemble : pas de doublons, pas garanti d’être ordonné
        Set<String> hashSet = new HashSet<>();
        hashSet.add("Banane");
        hashSet.add("Pomme");
        hashSet.add("Banane"); // ignoré
        System.out.println("HashSet (non ordonné) : " + hashSet);
        //=> HashSet (non ordonné) : [Banane, Pomme]
        //!!!!important 
        // les doublons dans  le cas suivant ne sont pas ignoré: , le hashset fait appel au hashcode et equls pour comparer deux objets
        Set<Person> set = new HashSet<>();
        set.add(new Person("Alice", 30));
        set.add(new Person("Alice", 30));
        //pour eviter ce doublons il faut implementer hascode et equals dans l'objet Person
        
        Set<String> linkedHashSet = new LinkedHashSet<>();
        linkedHashSet.add("Banane");
        linkedHashSet.add("Pomme");
        linkedHashSet.add("Ananas");
        System.out.println("LinkedHashSet (ordre d’insertion préservé) : " + linkedHashSet);
        // => LinkedHashSet (ordre d’insertion préservé) : [Banane, Pomme, Ananas]
        		
        Set<String> treeSet = new TreeSet<>();
        treeSet.add("Pomme");
        treeSet.add("Banane");
        treeSet.add("Ananas");
        System.out.println("TreeSet (tri naturel) : " + treeSet);
     // => TreeSet (tri naturel) : [Ananas, Banane, Pomme]
        		
        System.out.println("\n=== QUEUE ===");
        
        // File FIFO : premier entré, premier sorti
        Queue<String> queue = new LinkedList<>();
        queue.add("Tâche 1");
        queue.add("Tâche 2");
        System.out.println("Queue (LinkedList) : " + queue); //=> Queue (LinkedList) : [Tâche 1, Tâche 2]
        System.out.println("Poll (retire 1er) : " + queue.poll());//=> Poll (retire 1er) : Tâche 1
        System.out.println("Après poll : " + queue);//=> Après poll : [Tâche 2]

        System.out.println("\n=== DEQUE (Double-ended Queue) ===");
        
        Deque<String> deque = new ArrayDeque<>();
        deque.addFirst("Début");
        deque.addLast("Fin");
        System.out.println("Deque (ArrayDeque) : " + deque);//=>Deque (ArrayDeque) : [Début, Fin]
        // Lecture (sans suppression)
        deque.getFirst();
        deque.getLast();
        deque.peekFirst();
        deque.peekLast();
        
        deque.removeFirst();
        deque.removeLast();
        System.out.println("Après suppressions : " + deque);//=>Après suppressions : []
       
        
        deque.offerFirst("X"); // idem que addfirst mais version qui retourne false au lieu de lancer une exception
        deque.offerLast("Y"); // ..
        
        deque.pollFirst() ; // idem que removefirst mais retourne null si vide
        deque.pollLast();
        
      
        System.out.println("\n=== MAP ===");
        
        // Dictionnaire clé → valeur
        Map<Integer, String> hashMap = new HashMap<>();
        hashMap.put(1, "Un");
        hashMap.put(2, "Deux");
        hashMap.put(2, "Deux modifié"); // remplace la valeur
        System.out.println("HashMap (ordre non garanti) : " + hashMap);//=>HashMap (ordre non garanti) : {1=Un, 2=Deux modifié}

        Map<Integer, String> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put(1, "Un");
        linkedHashMap.put(2, "Deux");
        linkedHashMap.put(3, "Trois");
        System.out.println("LinkedHashMap (ordre insertion) : " + linkedHashMap);
        //=>LinkedHashMap (ordre insertion) : {1=Un, 2=Deux, 3=Trois}

        Map<Integer, String> treeMap = new TreeMap<>();
        treeMap.put(3, "Trois");
        treeMap.put(1, "Un");
        treeMap.put(2, "Deux");
        System.out.println("TreeMap (trié par clé) : " + treeMap);
        //=>TreeMap (trié par clé) : {1=Un, 2=Deux, 3=Trois}
    }
}

