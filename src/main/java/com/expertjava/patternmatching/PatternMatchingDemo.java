package com.expertjava.patternmatching;


public class PatternMatchingDemo {

    public static void main(String[] args) {
        Object obj1 = "Bonjour";
        Object obj2 = 123;
        Object obj3 = new User("Fattouma", 48);
        Object obj4 = 3.14;
        var obj5 = new Adresse("Rue", 48,"Paris","France");

        System.out.println("=== instanceof pattern matching avec if ===");
        printIfString(obj1);
        printIfString(obj2);

        System.out.println("\n=== switch pattern matching (Java 21 preview) ===");
        printType(obj3);
        printType(obj5);

        System.out.println("\n=== for avec pattern matching ===");
        Object[] objects = {obj1, obj2, obj3, obj4};
        for (Object o : objects) {
            if (o instanceof User u) {
                System.out.println("Utilisateur détecté dans for : " + u.name + ", " + u.age + " ans");
            } else if (o instanceof String s) {
                System.out.println("Chaîne détectée dans for : " + s);
            } else {
                System.out.println("Autre type détecté dans for : " + o);
            }
        }

        System.out.println("\n=== while avec pattern matching ===");
        int i = 0;
        while (i < objects.length) {
            Object o = objects[i++];
            if (o instanceof Integer n) {
                System.out.println("Entier détecté dans while : " + n);
                break; // on sort de la boucle après avoir trouvé un Integer
            }
        }
    }

    static void printIfString(Object obj) {
        if (obj instanceof String s) {
            System.out.println("C'est une chaîne : " + s.toUpperCase());
        } else {
            System.out.println("Pas une chaîne : " + obj);
        }
    }

    static void printType(Object obj) {
        String result = switch (obj) {
            case String s       -> "Chaîne : " + s;
            case Integer i      -> "Entier : " + i;
            case User u         -> "Utilisateur : " + u.name + ", " + u.age + " ans";
            case Adresse adr when "France".equals(adr.pays)   -> "Adresse en France ";
            default             -> "Inconnu : " + obj;
        };
        System.out.println(result);
    }

    record User(String name, int age) {}
    record Adresse(String rue, int numero, String ville , String pays) {}
}

