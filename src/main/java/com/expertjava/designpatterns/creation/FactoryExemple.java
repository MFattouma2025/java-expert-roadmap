package com.expertjava.designpatterns.creation;
/**
 *  Un factory (ou fabrique en français) est un modèle de conception (design pattern) 
 *  qui permet de créer des objets sans spécifier directement la classe concrète à instancier.
 *  Il s'agit d'une méthode ou d'une classe qui encapsule la logique de création d'objets,
 *  permettant ainsi de découpler le code client de l'implémentation concrète de ces objets. 
 * 
 */
//Interface pour les produits
interface Produit {
	String getName();
}

//Classes concrètes de produits
class ProduitA implements Produit {
	@Override
	public String getName() {
		return "Produit A";
	}
}

class ProduitB implements Produit {
	@Override
	public String getName() {
		return "Produit B";
	}
}

//Fabrique simple
class ProduitFactory {
	public Produit creerProduit(String type) {
		if ("A".equals(type)) {
			return new ProduitA();
		} else if ("B".equals(type)) {
			return new ProduitB();
		} else {
			return null; // Ou lancer une exception
		}
	}
}

//Utilisation de la fabrique
public class FactoryExemple {
	public static void main(String[] args) {
		ProduitFactory fabrique = new ProduitFactory();
		Produit produitA = fabrique.creerProduit("A");
		Produit produitB = fabrique.creerProduit("B");

		System.out.println(produitA.getName());
		System.out.println(produitB.getName());
	}
}