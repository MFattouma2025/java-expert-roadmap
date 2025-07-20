package com.expertjava.designpatterns.structure;

/**
 * Le Decorator Pattern permet d'ajouter dynamiquement des fonctionnalités à un objet sans modifier sa structure.
 * C’est une alternative élégante à l’héritage : au lieu de créer plein de sous-classes pour chaque variante, 
 * tu emballes l’objet de base dans un "décorateur" qui lui ajoute des fonctionnalités.
 */
interface Boisson {
	String getDescription();
	double getPrix();
}

class Cafe implements Boisson {
	public String getDescription() {
		return "Café";
	}
	public double getPrix() {
		return 2.0;
	}
}

abstract class BoissonDecorator implements Boisson {
	protected Boisson boisson;

	public BoissonDecorator(Boisson boisson) {
		this.boisson = boisson;
	}
}
//
class Lait extends BoissonDecorator {
	public Lait(Boisson boisson) {
		super(boisson);
	}

	public String getDescription() {
		return boisson.getDescription() + ", Lait";
	}

	public double getPrix() {
		return boisson.getPrix() + 0.5;
	}
}
//
class Sucre extends BoissonDecorator {
	public Sucre(Boisson boisson) {
		super(boisson);
	}

	public String getDescription() {
		return boisson.getDescription() + ", Sucre";
	}

	public double getPrix() {
		return boisson.getPrix() + 0.2;
	}
}

public class DecoratorExemple {
	public static void main(String[] args) {
		Boisson boisson = new Cafe(); // Café
		boisson = new Lait(boisson); // Café, Lait
		boisson = new Sucre(boisson); // Café, Lait, Sucre

		System.out.println(boisson.getDescription()); // Café, Lait, Sucre
		System.out.println("Prix: " + boisson.getPrix()); // Prix: 2.7

	}
}
