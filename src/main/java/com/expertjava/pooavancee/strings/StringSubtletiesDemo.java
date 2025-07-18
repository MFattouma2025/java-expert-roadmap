package com.expertjava.pooavancee.strings;


public class StringSubtletiesDemo {

    public static void main(String[] args) {

        System.out.println("=== 1. Immutabilité ===");
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
        String d = new String("world").intern();
        String e = "world";
        System.out.println("d == e : " + (d == e)); // true

        System.out.println("\n=== 4. StringBuilder vs StringBuffer ===");
        StringBuilder sb = new StringBuilder("Bonjour");
        sb.append(" à tous");
        System.out.println("StringBuilder : " + sb);

        StringBuffer sbf = new StringBuffer("Hello");
        sbf.append(" World");
        System.out.println("StringBuffer : " + sbf);

        System.out.println("\n=== 5. Performance (+ vs StringBuilder) ===");
        long start1 = System.nanoTime();
        String test1 = "";
        for (int i = 0; i < 10000; i++) {
            test1 += i;
        }
        long end1 = System.nanoTime();
        System.out.println("Temps avec '+' : " + (end1 - start1) / 1_000_000 + " ms");

        long start2 = System.nanoTime();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 10000; i++) {
            builder.append(i);
        }
        long end2 = System.nanoTime();
        System.out.println("Temps avec StringBuilder : " + (end2 - start2) / 1_000_000 + " ms");

        System.out.println("\n=== 6. Cas courant d’erreur ===");
        String x = null;
        // System.out.println(x.equals("abc")); // ❌ NullPointerException
        System.out.println("abc".equals(x)); // ✅ plus sûr
    }
}
