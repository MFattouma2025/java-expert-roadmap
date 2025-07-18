package com.expertjava.pooavancee.heritagedemo;

/*interface = contrat de comportement
Elle définit ce que la classe doit faire, sans dire comment.
Ne contient que des méthodes abstraites par défaut (depuis Java 8, on peut aussi avoir des default et static).
Pas d'état (pas d'attributs d'instance sauf static final constants).
Une classe peut implémenter plusieurs interfaces.
*/

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
