package com.expertjava.pooavancee.heritagedemo;
//@FunctionalInterface est une annotation informative qui indique qu'une interface 
//est conçue pour être une interface fonctionnelle. Une interface fonctionnelle est une 
//interface qui a une seule et unique méthode abstraite (Single Abstract Method ou SAM).
//Cela signifie qu'elle ne peut avoir qu'une seule méthode dont l'implémentation n'est pas fournie.
//Elle peut cependant contenir des méthodes par défaut 
//(default methods) et des méthodes statiques (static methods). 
//Les interfaces fonctionnelles sont utilisées pour implémenter des expressions 
//lambda ou des références de méthodes, ce qui facilite la programmation fonctionnelle en Java.


// used dans Meat.class
@FunctionalInterface
public interface FunctionnalPrint {
	
	void print(String message);


	default void printUpperCase(String message) {
		System.out.println(message.toUpperCase());
	}

	static void printOKFood() {
		System.out.println("The food is ok ");
	}
}
