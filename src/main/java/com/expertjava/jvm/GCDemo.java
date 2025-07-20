package com.expertjava.jvm;

import java.util.ArrayList;
import java.util.List;
/**Garbage Collector:
 * La JVM utilise une stratégie de générations pour gérer la mémoire dans le heap, 
 * car les objets jeunes meurent souvent vite, alors que les objets anciens vivent longtemps.
 * Heap
├── Young Generation (objets récents) // traité dans cette demo
│   ├── Eden (objets tout juste créés)
│   └── Survivor (S0 et S1, rotation)
└── Old Generation (objets anciens qui ont survécu)

 */
public class GCDemo {
    public static void main(String[] args) {
        List<byte[]> list = new ArrayList<>();
        while (true) {
            list.add(new byte[1_000_000]); // provoque du GC
//            try {
//				Thread.sleep(50);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}

        }
    }
}

//Output :si on lance :  java -Xlog:gc -Xmx128m -Xms128m GCDemo.java  
//0.020s][info][gc] Using G1
//[0.834s][info][gc] GC(0) Pause Young (Normal) (G1 Evacuation Pause) 13M->1M(128M) 6.232ms
/*
 * GC(0) : Numéro du GC
 * Pause Young : GC de la Young Generation
 * 13M->1M(128M) :
 * Avant le GC : 13 Mo utilisés
 * Après le GC : 1 Mo
 * Taille du heap : 128 Mo
 * Temps du GC : 6.232 ms  ✅ → Bonne évacuation : les objets morts ont été collectés.
 */
 // ......
//[1.648s][info][gc] GC(14) Pause Full (G1 Compaction Pause) 126M->126M(128M) 9.163ms
/*
 * Full GC = la JVM n’a pas réussi à libérer assez de mémoire via le GC normal.
 * Humongous Allocation = un objet de grande taille a été demandé (> 50% d’une région du heap).
 * Mais 126M→126M = le Full GC n’a pas pu libérer de mémoire 
 */
//......
//Exception in thread "main" java.lang.OutOfMemoryError: Java heap space