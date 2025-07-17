package com.expertjava.pooavancee.typesclasses;

/**
 * Terminologie : Nested Class, Inner Class, Static Nested Class Nested Class
 * (classe imbriquée) : C’est tout type de classe déclarée à l’intérieur d’une
 * autre classe — donc, toutes les classes imbriquées sont des nested classes.
 * 
 * Static Nested Class : C’est une nested class qui est déclarée avec le mot-clé
 * static. Elle ne dépend pas d’une instance de la classe externe.
 * 
 * Inner Class : C’est une nested class NON statique, donc liée implicitement à
 * une instance de la classe externe. En Java, le terme "inner class" désigne
 * uniquement ces nested classes non statiques.
 * 
 * 
 */
public class AnonymousAndLocalClassDemo {

	interface Operation {
		void execute();
	}

	// Méthode utilisant une classe locale
	void methodeAvecClasseLocale() {
		// Classe locale définie dans la méthode
		class ClasseLocale implements Operation {
			public void execute() {
				System.out.println("Exécution depuis la classe locale.");
			}
		}

		ClasseLocale locale = new ClasseLocale();
		locale.execute();
	}

	// Méthode utilisant une classe anonyme
	void methodeAvecClasseAnonyme() {
		Operation op = new Operation() {
			@Override
			public void execute() {
				System.out.println("Exécution depuis la classe anonyme.");
			}
		};

		op.execute();
	}

	public static void main(String[] args) {
		AnonymousAndLocalClassDemo demo = new AnonymousAndLocalClassDemo();

		System.out.println("=== Classe locale ===");
		demo.methodeAvecClasseLocale();

		System.out.println("\n=== Classe anonyme ===");
		demo.methodeAvecClasseAnonyme();
	}
}
