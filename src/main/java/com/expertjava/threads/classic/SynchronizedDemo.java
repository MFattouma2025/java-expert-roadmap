package com.expertjava.threads.classic;


class Compteur1 {
    private int count = 0;

    public void increment() {
        count++; // PAS thread-safe
    }

    public int getCount() {
        return count;
    }
}

class Compteur2 {
    private int count = 0;

    public synchronized void increment() { //synchronized empêche deux threads d’entrer simultanément dans une méthode synchronisée sur le
        count++;
    }

    public synchronized int getCount() {
        return count;
    }
}


public class SynchronizedDemo {
    public static void main(String[] args) throws InterruptedException {
    	Compteur1 c = new Compteur1();
        Compteur2 c2 = new Compteur2();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) c.increment();
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) c.increment();
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("c1 Résultat attendu : 2000");
        System.out.println("c1 Résultat réel    : " + c.getCount()); // souvent < 2000
        
        Thread t11 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) c2.increment();
        });

        Thread t22 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) c2.increment();
        });

        t11.start();
        t22.start();

        t11.join();
        t22.join();

        System.out.println("c2 Résultat attendu : 2000");
        System.out.println("c2  Résultat réel    : " + c2.getCount()); // = 2000
    }
}
//output:
//c1 Résultat attendu : 2000
//c1 Résultat réel    : 1975
//c2 Résultat attendu : 2000
//c2  Résultat réel    : 2000
