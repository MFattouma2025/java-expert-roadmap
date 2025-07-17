package com.expertjava.pooavancee.typesclasses;

public class Externe {

	private String message = "Bonjour depuis Externe";

	// Static nested class
	static class ClasseStatique {
		void afficher() {
			// System.out.println(message); ❌ ne compile pas
			System.out.println("Classe statique imbriquée");
		}
	}

	// Inner class
	class ClasseInterne {
		void afficher() {
			System.out.println("Classe interne accède à message : " + message); // ✅
		}
	}

	public static void main(String[] args) {
		// Static nested class → pas besoin d’objet externe
		ClasseStatique cs = new ClasseStatique();
		cs.afficher();

		// Inner class → nécessite une instance de Externe
		Externe ext = new Externe();
		Externe.ClasseInterne ci = ext.new ClasseInterne();
		ci.afficher();

		// Classe interne :
		Externe.ClasseInterne ci1 = new Externe().new ClasseInterne();

		// Classe statique imbriquée :
		Externe.ClasseStatique cs1 = new Externe.ClasseStatique();

	}
}
