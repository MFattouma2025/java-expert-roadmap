package com.expertjava.pooavancee.heritagedemo;

import com.expertjava.pooavancee.heritagedemo.constants.FoodType;

import lombok.Data;

// Héritage classe abstraite  , la classe fille: Meat

/*Classe abstraite = base avec contenu + comportement partiel
Elle définit à la fois le quoi (méthodes abstraites) et le comment (méthodes implémentées).
Peut contenir :
	des méthodes abstraites (à implémenter dans les sous-classes)
	des méthodes concrètes
	des attributs (état partagé)
Une classe ne peut hériter que d’une seule classe abstraite.*/
//Une classe abstarite peut heriter d'une classe abstraite ou non 


@Data
public abstract class Food {
	
    // Attributs communs
    protected String name;
    protected int calories;
    protected double weightInGrams;
    protected FoodType origin = FoodType.Other;
    
    // Constructeur
    public Food(String name, int calories, double weightInGrams) {
        this.name = name;
        this.calories = calories;
        this.weightInGrams = weightInGrams;
    }
    public Food() {
       
    }

    public final void methodeCanNotBeUpdatedByChild()
    {
    	
    }
    
    public void displayInfo() {
        System.out.println("Nom : " + name);
        System.out.println("Calories : " + calories);
        System.out.println("Poids (g) : " + weightInGrams);
    }

    // Méthodes abstraites à implémenter dans les sous-classes
     abstract boolean isHealthy();

    public abstract void prepare();
}
