package com.expertjava.threads.classic;


public class ThreadCreationDemo {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> System.out.println("Thread classique exécuté"));
        thread.start();
    }
}
