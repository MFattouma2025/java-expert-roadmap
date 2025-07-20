package com.expertjava.jvm;

import java.util.ArrayList;
import java.util.List;

/**Garbage Collector:
 * La JVM utilise une stratégie de générations pour gérer la mémoire dans le heap, 
 * car les objets jeunes meurent souvent vite, alors que les objets anciens vivent longtemps.
 * Heap
├── Young Generation (objets récents) 
│   ├── Eden (objets tout juste créés)
│   └── Survivor (S0 et S1, rotation)
└── Old Generation (objets anciens qui ont survécu)// traité dans cette demo
 La Old Generation (ou Tenured) est utilisée dans la JVM pour stocker les objets qui ont survécu suffisamment 
 longtemps dans la mémoire Young Generation. Voici quand et comment cela se produit.
 */
public class SurvivorDemo {
    //	Les objets ne sont pas collectés.
    // 	Ils sont d’abord en Eden, puis Survivor, puis promus en Old Gen.

	
    public static void main(String[] args) throws InterruptedException {
        List<byte[]> list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            byte[] array = new byte[1_000_000]; // 1 MB
            list.add(array); // reste référencé
            Thread.sleep(100); // laisse le temps au GC de tourner
        }
    }
}


//=>out put :java -Xlog:gc*,gc+age -XX:+UseG1GC com.expertjava.jvm.SurvivorDemo
//	[4.117s][info][gc,phases   ] GC(2)   Merge Heap Roots: 0.12ms
//	[4.118s][info][gc,phases   ] GC(2)   Evacuate Collection Set: 5.83ms
//	[4.118s][info][gc,phases   ] GC(2)   Post Evacuate Collection Set: 0.82ms
//	[4.119s][info][gc,phases   ] GC(2)   Other: 1.10ms
//	[4.120s][info][gc,heap     ] GC(2) Eden regions: 3->0(5)
//	[4.121s][info][gc,heap     ] GC(2) Survivor regions: 1->1(1)
//	[4.123s][info][gc,heap     ] GC(2) Old regions: 6->9
//	[4.124s][info][gc,heap     ] GC(2) Humongous regions: 0->0
//	[4.125s][info][gc,metaspace] GC(2) Metaspace: 145K(384K)->145K(384K) NonClass: 137K(256K)->137K(256K) Class: 7K(128K)->7K(128K)
//	[4.126s][info][gc          ] GC(2) Pause Young (Normal) (G1 Evacuation Pause) 36M->36M(320M) 19.390ms
//	[4.127s][info][gc,cpu      ] GC(2) User=0.05s Sys=0.02s Real=0.02s
//	[5.659s][info][gc,heap,exit] Heap
//	[5.659s][info][gc,heap,exit]  garbage-first heap   total reserved 5201920K, committed 327680K, used 52314K [0x00000006c2800000, 0x0000000800000000)
//	[5.660s][info][gc,heap,exit]   region size 4096K, 5 young (20480K), 1 survivors (4096K)
//	[5.661s][info][gc,heap,exit]  Metaspace       used 146K, committed 384K, reserved 1114112K
//	[5.661s][info][gc,heap,exit]   class space    used 8K, committed 128K, reserved 1048576K
