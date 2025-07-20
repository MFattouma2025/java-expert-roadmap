package com.expertjava.pooavancee.typesclasses;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//Une classe est dite immuable si ses instances 
//ne peuvent pas être modifiées après leur création.
/*
Pour rendre une classe immuable, on suit généralement ces règles :
🔒 La classe est déclarée final (facultatif mais recommandé) : empêche l'héritage.
🔐 Tous les champs sont private et final.
❌ Pas de setter.
✅ Les champs sont initialisés uniquement via le constructeur.
🧱 Si un champ est un objet mutable (ex. une liste), on doit :
faire une copie défensive lors de l'affectation,
retourner une copie dans les getters.
*/
final class Person {
	private final String name;
	private final int age;

	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}

	// Getters sans setter
	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}
}

//depuis java 14 on peut juste faire ca :  ca remplace tous le code dans person ci dessus
record Person1(String name, int age) {
}

// on peut aussi faire ca pour ajouter la logique dans le constructuer :
record Person2(String name, int age) {
	public Person2 {
		if (age < 0) {
			throw new IllegalArgumentException("Age must be positive");
		}
	}
}

//Exemple avec un champ mutable (les listes ne sont pas immuable dans ca cas )

final class Employee {
	private final String name;
	private final List<String> tasks;

	public Employee(String name, List<String> tasks) {
		this.name = name;
		// copie défensive
		this.tasks = new ArrayList<>(tasks);
	}

	public String getName() {
		return name;
	}

	public List<String> getTasks() {
		// retour défensif
		return new ArrayList<>(tasks);
	}
}


//Si un champ est mutable (comme une List), il faut toujours faire une copie défensive et ca donne ca :


 record Employee1(String name, List<String> tasks) {
    public Employee1 {
        tasks = Collections.unmodifiableList(new ArrayList<>(tasks));
    }
}

