package com.expertjava.pooavancee.solidexamples;

import com.expertjava.pooavancee.heritagedemo.Meat;

//D - Dependency Inversion Principle (DIP)
//Dépendre d’abstractions, pas de classes concrètes.
//Dans spring boot on utilise le autowired , un exemple j eveux envoyer un colis ,
// dans mon colis j'injecte l'objet serviceenvoie , je n'ai pas besoin de comprrndre 
//comment il va faire son trvavail ce service 

interface Clavier {
	String lireSaisie();
}

//
class ClavierUSB implements Clavier {

	public String lireSaisie() {
		return "Saisie via USB";
	}
}

//L'ordinateur dépend de l'interface, pas d'une implémentation spécifique.
class Ordinateur {

	private Clavier clavier;

	public Ordinateur(Clavier clavier) {
		this.clavier = clavier;
	}

	void afficherSaisie() {
		System.out.println(clavier.lireSaisie());
	}

	public static void main(String[] args) {
		Clavier clavier = new ClavierUSB();
		Ordinateur ordinateur = new Ordinateur(clavier);
		ordinateur.afficherSaisie();
	}
}
