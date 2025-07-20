package com.expertjava.threads.classic;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*Thread incrementer : incrémente le compteur toutes les 0,5s et appelle notifyAll() pour réveiller les autres threads.
Thread watcher : attend que count >= 5 en utilisant wait() dans une boucle while.
Synchronisation sur le même objet compteur pour éviter les accès concurrents.
Même logique ensuite avec un pool de threads ExecutorService.
Requête HTTP = un thread
Quand dans spring on fait  un @RestController, chaque requête HTTP est gérée par 
un thread dédié du pool de Tomcat (ou Jetty, Undertow selon config).
on ne devrais quasiment jamais créer des new Thread() nous-même dans Spring, sauf cas très spécifiques.
Spring te donne des outils plus sûrs et gérables :
@Async pour les appels asynchrones.
TaskExecutor pour exécuter dans des pools personnalisés.
@Scheduled pour les jobs périodiques.
exemple:

@Configuration
public class AsyncConfig {

    @Bean(name = "threadPoolTaskExecutor")
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix("AsyncThread-");
        executor.initialize();
        return executor;
    }
}
et Et dans le @Async, on peux préciser :

@Async("threadPoolTaskExecutor")
public void traitement() { ... }
---------------------------------------------------------->
Quand est-ce utile dans un projet Spring ?
 Cas concrets dans un backend Spring :
Appels à des APIs externes en parallèle (ex : WebClient avec Mono.zip(...))
Traitements longs (PDF, batchs, analyse de fichiers) sans bloquer la requête utilisateur.
Tâches planifiées (envoi d’e-mails toutes les nuits, synchronisation).
Execution parallèle de tâches métiers dans des microservices.
 */

public class ClassicMultithreadingDemo {

    public static void main(String[] args) throws InterruptedException {
    	
        Compteur compteur = new Compteur();

        // Thread 1 : incrémente le compteur 5 fois
        Thread incrementer = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                compteur.increment();
                try {
                    Thread.sleep(500); // Simuler un travail
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        // Thread 2 : attend que le compteur atteigne 5 puis affiche un message
        Thread watcher = new Thread(() -> {
            synchronized (compteur) {
                while (compteur.getCount() < 5) {
                    try {
                        System.out.println("Watcher attend que le compteur atteigne 5");
                        compteur.wait();  // attend la notification
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                System.out.println("Compteur a atteint " + compteur.getCount());
            }
        });

        incrementer.start();
        watcher.start();

        incrementer.join();
        watcher.join();

        System.out.println("---- Maintenant avec ExecutorService ----");

        // Même exemple avec ExecutorService
        ExecutorService executor = Executors.newFixedThreadPool(2);
        Compteur compteur2 = new Compteur();

        executor.submit(() -> {
            for (int i = 0; i < 5; i++) {
                compteur2.increment();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        executor.submit(() -> {
            synchronized (compteur2) {
                while (compteur2.getCount() < 5) {
                    try {
                        System.out.println("Executor Watcher attend...");
                        compteur2.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                System.out.println("Executor Compteur a atteint " + compteur2.getCount());
            }
        });

        executor.shutdown();
    }
}

class Compteur {
    private int count = 0;

    public synchronized void increment() {
        count++;
        System.out.println("Compteur incrémenté à " + count);
        notifyAll(); // Prévenir les threads en attente
    }

    public synchronized int getCount() {
        return count;
    }
}
