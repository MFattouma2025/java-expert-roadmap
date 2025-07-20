package com.expertjava.pooavancee.generics;
// exemple simple : 

/*Dans cet exemple, la classe Boîte peut contenir n'importe quel type
d'objet grâce à la généricité. Lors de l'utilisation, on ne doit pas 
caster pour récupérer l'objet, 
ce qui rend le code plus propre et plus sûr*/

import java.util.List;

public class Boîte<T> { // Définition d'une classe générique
	private T objet;

	public Boîte(T objet) {
		this.objet = objet;
	}

	public T getObjet() {
		return objet;
	}

	public static void main(String[] args) {
		// Utilisation
		Boîte<String> boîteDeChaînes = new Boîte<>("Bonjour");
		String contenu = boîteDeChaînes.getObjet(); // Pas besoin de cast

		Boîte<Integer> boîteDEntiers = new Boîte<>(123);
		int nombre = boîteDEntiers.getObjet(); // Pas besoin de cast

	}
}
