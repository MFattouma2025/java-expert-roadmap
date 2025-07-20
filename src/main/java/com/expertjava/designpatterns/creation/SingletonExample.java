package com.expertjava.designpatterns.creation;

/**
 * Singleton Pattern
 * But : Garantir qu'une seule instance d'une classe est créée.
 * le singleton est un outil puissant pour garantir l'unicité d'une instance et le contrôle des ressources partagées
 * Utilisation : Gestion de configuration, Loggers, caches, etc.
 * Dans spring boot , le @Service, @Controller, @Component , @Repository sont des singleton
 * Un cas d'usage courant du motif Singleton est la gestion de connexions à une base de données.
 * Dans une application, il est souvent nécessaire de n'avoir qu'une seule instance 
 * de connexion pour éviter les problèmes de performance liés à l'ouverture de multiples connexions.
 * Avantages:
 * Contrôle précis de l'instanciation, Accès global à l'instance, Évite la création d'objets inutiles, 
 * Optimisation des ressources. 
 *  */
public class SingletonExample {

    private static SingletonExample instance;

    private SingletonExample() {
        // Constructeur privé pour empêcher l’instanciation externe
    }

    public static synchronized SingletonExample getInstance() {
        if (instance == null) {
            instance = new SingletonExample();
        }
        return instance;
    }

    public void log(String message) {
        System.out.println("Log: " + message);
    }

    public static void main(String[] args) {
        SingletonExample.getInstance().log("Hello Singleton!");
    }
}

