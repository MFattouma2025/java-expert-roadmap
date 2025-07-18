package com.expertjava.pooavancee.generics;


import java.util.ArrayList;
import java.util.List;
/*
| Besoin                                | Syntaxe générique               | Pourquoi ?                        |
| ------------------------------------- | ------------------------------- | --------------------------------- |
| Lire des objets d’une hiérarchie      | `List<? extends T>`             | Lecture sûre, interdit l’écriture |
| Écrire des objets dans une hiérarchie | `List<? super T>`               | Écriture sûre, lecture limitée    |
| Méthode générique avec contrainte     | `<T extends InterfaceOuClasse>` | Garantir les capacités de T       |
*/
/*

? extends T ⇒ Lecture seule (get() OK), pas d’add()
? super T ⇒ Écriture sûre (add() OK), get() retourne Object
*/
class Animal {
    void parler() {
        System.out.println("Un animal fait du bruit.");
    }
}

class Chien extends Animal {
    @Override
    void parler() {
        System.out.println("Le chien aboie.");
    }
    
}

class Chat extends Animal {
    @Override
    void parler() {
        System.out.println("Le chat miaule.");
    }
}

public class AdvancedGenericsDemo {

    // 🔹 <? extends Animal> : lecture sécurisée, écriture interdite
    static void afficherAnimaux(List<? extends Animal> animaux) {
        for (Animal a : animaux) {
            a.parler(); // ✅ Lecture OK
        }
        // animaux.add(new Chien()); ❌ Erreur : écriture interdite
    }

    // 🔸 <? super Chien> : écriture sécurisée, lecture limitée
    static void ajouterChien(List<? super Chien> liste) {
        liste.add(new Chien()); // ✅ Écriture OK
        Animal a = (Animal)liste.get(0);
        // Animal a = liste.get(0); ❌ Lecture pas sûre (peut être Object)
    }
    
    // 🔸 <? super Chien> : écriture sécurisée, lecture limitée
    static void ajouterAnimal(List<? super Animal> liste) {
        liste.add(new Chien()); // ✅ Écriture OK
        liste.add(new Chat());
        //liste.add("chaine");❌
        // Animal a = liste.get(0); ❌ Lecture pas sûre (peut être Object)
    }
    // ✅ Méthode générique avec <T>
    static <T> void afficherListe(List<T> liste) {
        for (T element : liste) {
            System.out.println(element);
        }
    }

    public static void main(String[] args) {
        List<Chien> chiens = new ArrayList<>();
        chiens.add(new Chien());

        List<Chat> chats = new ArrayList<>();
        chats.add(new Chat());

        List<Animal> animaux = new ArrayList<>();
        animaux.add(new Chien());
        animaux.add(new Chat());

        System.out.println("=== extends : afficherAnimaux(List<? extends Animal>) ===");
        afficherAnimaux(chiens);
        afficherAnimaux(chats);
        afficherAnimaux(animaux);

        System.out.println("\n=== super : ajouterChien(List<? super Chien>) ===");
        ajouterChien(animaux); // ✅
       //   ajouterChien(chats); //❌ impossible (Chat ≠ super de Chien)
        
        System.out.println("\n=== super : ajouterAnimal(List<? super Animal>) ===");
        ajouterAnimal(animaux);
        afficherListe(animaux);
        
        System.out.println("\n=== méthode générique simple ===");
        afficherListe(chats);
        afficherListe(chiens);
    }
}
