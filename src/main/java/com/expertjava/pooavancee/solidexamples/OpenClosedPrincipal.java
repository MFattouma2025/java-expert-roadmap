package com.expertjava.pooavancee.solidexamples;

//O - Open/Closed Principle (OCP)
interface Forme {
	double aire();
}

// Les classes doivent être ouvertes à l’extension, mais fermées à la modification.
//Si on ajoute une nouvelle forme, on n’a pas besoin de modifier les autres classes.

class Cercle implements Forme {

	double rayon;

	Cercle(double rayon) {
		this.rayon = rayon;
	}

	public double aire() {
		return Math.PI * rayon * rayon;
	}
}

class Rectangle implements Forme {

	double largeur, hauteur;

	Rectangle(double l, double h) {
		this.largeur = l;
		this.hauteur = h;
	}

	public double aire() {
		return largeur * hauteur;
	}
}
