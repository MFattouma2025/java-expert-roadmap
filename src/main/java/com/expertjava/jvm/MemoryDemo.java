package com.expertjava.jvm;

public class MemoryDemo {
	public static void main(String[] args) {
        int a = 10; // stack
        String name = "Fattouma"; // name dans le stack (référence) + fattouma dans le heap (valeur)
        Person p = new Person("Alice"); // p = stack, objet = heap
        //on deboguant on verra pour le p qqe chose qui ressemble à ça com.expertjava.jvm.Person@3b3f295c 
        //C'est une référence dans le Stack qui pointe vers un objet Person stocké dans le Heap.
         // Le @3b3f295c est l'identifiant mémoire (ou hash) de l'objet dans le heap (c’est une représentation de l’adresse mémoire ou du hashCode()).
        Person p2 = p; 
        // on deboguant on verra pour le p2 pointe vers la meme reference  qqe chose qui ressemble à ça : com.expertjava.jvm.Person@3b3f295c
        // ils pointent vers le même objet dans le heap.

        System.out.println(p.getName());
    }
}

class Person {
    private String name;

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
//A retenir :
//	| Élément  | Type   | Affichage     | Emplacement réel                  |
//	| -------- | ------ | ------------- | --------------------------------- |
//	| `a`      | int    | `10`          | Stack                             |
//	| `s`      | String | `"Fattouma"`  | Stack (réf) → Heap (objet)        |
//	| `p`      | Person | `Person@xxxx` | Stack (réf) → Heap                |
//	| `p.name` | String | `"Alice"`     | Heap (champ `name` dans `Person`) |
