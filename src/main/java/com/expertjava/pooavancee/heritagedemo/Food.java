package com.expertjava.pooavancee.heritagedemo;

import com.expertjava.pooavancee.heritagedemo.constants.FoodType;

import lombok.Data;

// Héritage classe abstraite  , les calsses filles Meat
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
    public abstract boolean isHealthy();

    public abstract void prepare();
}
