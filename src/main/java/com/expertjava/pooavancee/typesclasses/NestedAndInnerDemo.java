package com.expertjava.pooavancee.typesclasses;

class Externe {

	private String message = "Bonjour depuis Externe";

	// Static nested class
	static class ClasseInterneStatique {
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
		ClasseInterneStatique cs = new ClasseInterneStatique();
		cs.afficher();

		// Inner class → nécessite une instance de Externe
		Externe ext = new Externe();
		Externe.ClasseInterne ci = ext.new ClasseInterne();
		ci.afficher();

		// Classe interne :
		Externe.ClasseInterne ci1 = new Externe().new ClasseInterne();

		// Classe statique imbriquée :
		Externe.ClasseInterneStatique cs1 = new Externe.ClasseInterneStatique();

	}
}
