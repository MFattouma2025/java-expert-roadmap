package com.expertjava.threads.classic;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class RunnableVsCallableDemo {
	/*
	 * Runnable : méthode run() ne retourne rien et ne peut pas lancer d’exception checked.
       Callable : méthode call() retourne un résultat (générique) et peut lancer des exceptions.
	 */

    public static void main(String[] args) throws InterruptedException, ExecutionException {
    	
        ExecutorService executor = Executors.newFixedThreadPool(2);

        // Runnable : pas de résultat retourné, pas d'exception checked
        Runnable runnableTask = () -> {
            System.out.println("Runnable exécuté par " + Thread.currentThread().getName());
        };

        executor.submit(runnableTask);

        // Callable : retourne un résultat et peut lancer une exception checked
        Callable<String> callableTask = () -> {
            System.out.println("Callable exécuté par " + Thread.currentThread().getName());
            Thread.sleep(500);
            return "Résultat du Callable";
        };

        Future<String> future = executor.submit(callableTask);

        // Blocage jusqu'à obtention du résultat
        String result = future.get();
        System.out.println("Résultat obtenu : " + result);

        executor.shutdown();
    }
}
