package com.expertjava.pooavancee.strings;


public class StringSubtletiesDemo {

    public static void main(String[] args) {

        System.out.println("=== 1. Immutabilité ===");
        //rappel : En Java, une String est immuable, ce qui signifie que son contenu ne peut jamais être modifié une fois l'objet créé.
        String s1 = "Java";
        String s2 = s1.concat(" Rocks");
        System.out.println("s1: " + s1); // Java
        System.out.println("s2: " + s2); // Java Rocks

        System.out.println("\n=== 2. == vs equals() ===");
        String a = "hello";
        String b = "hello";
        String c = new String("hello");
        System.out.println("a == b : " + (a == b));       // true (interned)
        System.out.println("a == c : " + (a == c));       // false
        System.out.println("a.equals(c) : " + a.equals(c)); // true

        System.out.println("\n=== 3. Interning ===");
       // le intern() : renvoie une version unique (canonique) d’une String, tirée du pool de chaînes internes (string pool) de la JVM.
        String str1 = new String("hello");
        String str2 = "hello";
        System.out.println(str1 == str2); // false
        System.out.println(str1.intern() == str2);    // true 
		/*
		 * Explication :
		 *  * new String("hello") crée un objet String en mémoire heap (hors du pool).
		 *  * "hello" est une chaîne littérale, placée automatiquement dans le string pool.
		 *  * str1.intern() retourne la référence du string pool → donc égale à str2.
		 *  
		 * La méthode intern() crée une copie exacte d'un objet String dans la mémoire du heap et la stocke dans le pool de constantes String .
           Notez que si une autre chaîne avec le même contenu existe dans le pool de constantes de chaîne , 
           aucun nouvel objet ne sera créé et la nouvelle référence pointera vers l'autre chaîne.

 *  | Fonction     | Description courte                                         |
    | ------------ | ---------------------------------------------------------- |
    | `intern()`   | Renvoie la version unique du pool de chaînes               |
    | String pool  | Zone mémoire où les chaînes littérales sont partagées      |
    | Avantage     | Moins de mémoire, comparaison rapide avec `==`             |
    | Inconvénient | Peut surcharger le pool, inutilisable pour chaînes uniques |
		 */
        String d = new String("world").intern(); 
        String e = "world";
        System.out.println("d == e : " + (d == e)); // true
        
        
        System.out.println("\n=== 4. StringBuilder vs StringBuffer  ===");
        
        StringBuilder sb = new StringBuilder("Bonjour");
        sb.append(" à tous");
        System.out.println("StringBuilder : " + sb);

        StringBuffer sbf = new StringBuffer("Hello");
        sbf.append(" World");
        System.out.println("StringBuffer : " + sbf);

        System.out.println("\n=== 5. Performance (+ vs StringBuilder) String , Stringbuffer ===");
        
//        String concaténation est très lente car immuable → crée beaucoup d’objets.
//        StringBuilder est rapide et recommandé en mono-thread.
//        StringBuffer est un peu plus lent à cause de la synchronisation, utile en multi-thread.
        
        int n = 100_000;

        // Benchmark String (concaténation classique, très lente)
        long start1 = System.currentTimeMillis();
        String s = "";
        for (int i = 0; i < n; i++) {
            s += "a";  // Chaque concat crée un nouvel objet String !
        }
        
        long end1 = System.currentTimeMillis();
        System.out.println("String concaténation : " + (end1 - start1) + " ms");

        // Benchmark StringBuilder (rapide, non synchronisé) -Traitement de texte en mono-thread
        
        long start2 = System.currentTimeMillis();
        StringBuilder sbb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append("a");
        }
        long end2 = System.currentTimeMillis();
        System.out.println("StringBuilder append : " + (end2 - start2) + " ms");

        // Benchmark StringBuffer (synchronisé, un peu plus lent) - Partage de chaîne entre plusieurs threads
        long start3 = System.currentTimeMillis();
        StringBuffer sbuf = new StringBuffer(); 
        for (int i = 0; i < n; i++) {
            sbuf.append("a");
        }
        long end3 = System.currentTimeMillis();
        System.out.println("StringBuffer append : " + (end3 - start3) + " ms");
        
        System.out.println("\n=== 6. Cas courant d’erreur ===");
        String x = null;
        // System.out.println(x.equals("abc")); // ❌ NullPointerException
        System.out.println("abc".equals(x)); // ✅ plus sûr
    }
}

//expected Output :
//=== 1. Immutabilité ===
//s1: Java
//s2: Java Rocks
//
//=== 2. == vs equals() ===
//a == b : true
//a == c : false
//a.equals(c) : true
//
//=== 3. Interning ===
//false
//true
//d == e : true
//
//=== 4. StringBuilder vs StringBuffer  ===
//StringBuilder : Bonjour à tous
//StringBuffer : Hello World
//
//=== 5. Performance (+ vs StringBuilder) String , Stringbuffer ===
//String concaténation : 1404 ms
//StringBuilder append : 12 ms
//StringBuffer append : 5 ms
//
//=== 6. Cas courant d’erreur ===
//false

