package com.expertjava.pooavancee.polymorphisme;

// Le polymorphisme permet à un même code de fonctionner avec 
//**différents types d’objets**, en s’adaptant à leur comportement spécifique.
//On programme "vers une abstraction", pas vers une implémentation.
//Le polymorphisme est essentiel pour écrire du code flexible et maintenable.
//Permet de donner plusieurs formes à une méthode, selon l'objet qui l'appelle.

//Exemple de polymorphisme via héritage
class Animal {
	void faireDuBruit() {
		System.out.println("L'animal fait un bruit");
	}
}

class Chat extends Animal {
	@Override
	void faireDuBruit() {
		System.out.println("Miaou !");
	}
}

class Chien extends Animal {
	@Override
	void faireDuBruit() {
		System.out.println("Wouf !");
	}
}

//Exemple de polymorphisme via interface
interface Transport {
	void demarrer();
}

class Voiture implements Transport {
	public void demarrer() {
		System.out.println("Voiture qui démarre !");
	}
}

class Moto implements Transport {
	public void demarrer() {
		System.out.println("Moto qui démarre !");
	}
}

public class PolymorphismeDemo {

	public static void main(String[] args) {
		// Polymorphisme via héritage
		Animal a1 = new Chat();
		Animal a2 = new Chien();
		faireFaireDuBruit(a1); // Miaou
		faireFaireDuBruit(a2); // Wouf

		System.out.println("----------------------");

		// Polymorphisme via interface
		Transport t1 = new Voiture();
		Transport t2 = new Moto();
		demarrerTransport(t1); // Voiture
		demarrerTransport(t2); // Moto
	}

	// Méthode générique sur le type parent
	static void faireFaireDuBruit(Animal animal) {
		animal.faireDuBruit(); // Appel dynamique (override)
	}

	static void demarrerTransport(Transport t) {
		t.demarrer(); // Appel dynamique (implémentation)
	}
}
