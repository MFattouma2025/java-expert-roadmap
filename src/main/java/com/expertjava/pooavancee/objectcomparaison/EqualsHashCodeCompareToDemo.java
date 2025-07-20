package com.expertjava.pooavancee.objectcomparaison;

import java.util.*;

import com.expertjava.pooavancee.heritagedemo.Avion;

public class EqualsHashCodeCompareToDemo {
	public static void main(String[] args) {
		Personne p1 = new Personne("Alice", 30);
		Personne p2 = new Personne("Alice", 30);
		Personne p3 = new Personne("Bob", 25);
		
		System.out.println("\np1 Hashcode :" + p1.hashCode());
		System.out.println("\np2 Hashcode :" + p2.hashCode());

		// 1. equals
		System.out.println("p1.equals(p2) = " + p1.equals(p2)); // true
		System.out.println("p1.equals(p3) = " + p1.equals(p3)); // false

		// 2. hashCode
		Set<Personne> personnesSet = new HashSet<>();
		personnesSet.add(p1);
		personnesSet.add(p2); // ne sera pas ajouté car égal à p1
		personnesSet.add(p3);

		System.out.println("\nContenu du HashSet :");
		personnesSet.forEach(System.out::println);

		// 3. compareTo
		List<Personne> personnesList = new ArrayList<>(personnesSet);
		Collections.sort(personnesList);
		
		System.out.println("\nListe triée par âge :");
		personnesList.forEach(System.out::println);
		
		List<Avion> avionsList = new ArrayList<>();
		avionsList.add(new Avion("Airbus", "Blanc", "France", "F-1234"));
		avionsList.add(new Avion("Boeing", "Gris", "USA", "N-5678"));
		//Dans le cas ou on ne veut pas utiliser l'interface comparable :
		Collections.sort(avionsList, Comparator.comparing(Avion::getCouleur));

		System.out.println("\nListe d'avions triée par couleur :");
		avionsList.forEach(System.out::println);
	}
	
}
// Classe métier : Personne
	class Personne implements Comparable<Personne> {
		private String nom;
		private int age;

		public Personne(String nom, int age) {
			this.nom = nom;
			this.age = age;
		}
		
		public String getNom() {
			return this.nom;
		}
		

		// 1. equals() - égalité logique basée sur le nom et l'âge
		@Override
		public boolean equals(Object o) {
			if (this == o)
				return true;
			if (!(o instanceof Personne))
				return false;
			Personne p = (Personne) o;
			return this.age == p.age && Objects.equals(this.nom, p.nom);
		}

		// 2. hashCode() - cohérent avec equals()
		//Cela permet à deux objets Personne qui ont le même nom et age d’avoir le même hash code.
		//C’est obligatoire si tu veux utiliser cet objet dans un HashSet ou HashMap proprement.
		@Override
		public int hashCode() {
			return Objects.hash(nom, age);
		}

		// 3. compareTo() - tri naturel par âge
		@Override
		public int compareTo(Personne autre) {
			return Integer.compare(this.age, autre.age);
		}

		@Override
		public String toString() {
			return nom + " (" + age + " ans)";
		}
	}


