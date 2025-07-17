package com.expertjava.pooavancee.heritagedemo;

public interface FoodPackage {
    // Méthodes abstraites à implémenter
    String getPackageType();
    
    void packageFood();
    
     String getLimitDate() ;
    
   
    // Méthode avec une implémentation par défaut
    public default void labelPackage(String productName) {
        System.out.println("Étiquette ajoutée : " + productName + " - À consommer de préférence avant fin "+ getLimitDate());
    }
    
    static void printPackagingInstructions() {
        System.out.println("Instructions standards : protéger l'aliment et indiquer la date limite.");
    }
}
