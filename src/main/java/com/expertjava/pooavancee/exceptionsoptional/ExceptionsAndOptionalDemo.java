package com.expertjava.pooavancee.exceptionsoptional;

import java.io.*;
import java.util.Optional;

public class ExceptionsAndOptionalDemo {

	// Exemple d’exception checked
	static void verifierAge(int age) throws InvalidAgeException {
		if (age < 18) {
			throw new InvalidAgeException("Âge insuffisant !");
		}
		System.out.println("Âge valide : " + age);
	}

	// try-with-resources pour gérer les fichiers
	static void lireFichier(String nomFichier) {
		try (InputStream is = ExceptionsAndOptionalDemo.class.getClassLoader().getResourceAsStream(nomFichier);
				BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
			String ligne;

			while ((ligne = reader.readLine()) != null) {
				System.out.println(ligne);
			}

		} catch (IOException e) {
			System.err.println("Erreur de lecture : " + e.getMessage());
		}
	}

	// Utilisation de Optional
	static void afficherNomEnMajuscules(Optional<String> nom) {
		// map transforme la valeur si présente

		nom.ifPresent(val -> System.out.println(val));
		// il faut eviter le if (optional.isPresent()) {// ...}

		String result = nom.map(String::toUpperCase).orElse("Nom inconnu");
		System.out.println("Nom : " + result);
	}

	public static void main(String[] args) {
		System.out.println("=== Gestion d'exception ===");
		try {
			verifierAge(15);
		} catch (InvalidAgeException e) {
			System.err.println("Erreur : " + e.getMessage());
		}

		System.out.println("\n=== try-with-resources ===");
		lireFichier("test.txt");

		System.out.println("\n=== Optional ===");
		afficherNomEnMajuscules(Optional.of("Fattouma"));
		afficherNomEnMajuscules(Optional.empty());

		Optional<String> str = Optional.of("france");

		String result = str.filter(s -> s.length() > 3) // garde si longueur > 3
				.map(String::toUpperCase) // transforme en MAJ
				.orElse("VALEUR PAR DEFAUT"); // sinon valeur par défaut

		System.out.println(result); // FRANCE
	}
}

//Exception personnalisée
class InvalidAgeException extends Exception {
	public InvalidAgeException(String message) {
		super(message);
	}
}
