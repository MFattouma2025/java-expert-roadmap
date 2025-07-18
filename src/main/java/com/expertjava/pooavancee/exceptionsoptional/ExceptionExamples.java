package com.expertjava.pooavancee.exceptionsoptional;

import java.io.*;
import java.util.Scanner;

/*| Exemple                  | Exception              | Type          | Commentaire                                 |
| ------------------------ | ---------------------- | ------------- | ------------------------------------------- |
| `division(10, 0)`        | `ArithmeticException`  | Non vérifiée  | Pas besoin de `throws`, levée à l’exécution |
| `lireFichier(...)`       | `IOException`          | Vérifiée      | Obligé de déclarer `throws`                 |
| `exempleFinally()`       | `finally`              | —             | Toujours exécuté même après une exception   |
| `verifierNom(...)`       | `NomInvalideException` | Personnalisée | Crée ta propre règle métier                 |
| `traitementComplet(...)` | Combinaison            | Mix           | Gère plusieurs exceptions dans une méthode  |
*/
// checked => verifié     ---- unchecked => non verifié
/*
| Type d’exception | Classe parente                            | Vérifiée par le compilateur ? | Doit être catchée ?        | Exemples                                                                  |
| ---------------- | ----------------------------------------- | ----------------------------- | -------------------------- | ------------------------------------------------------------------------- |
| **Checked**      | `Exception` (mais pas `RuntimeException`) | ✅ Oui                         | ✅ Oui ou déclarée `throws` | `IOException`, `SQLException`, etc.                                       |
| **Unchecked**    | `RuntimeException` et ses filles          | ❌ Non                         | ❌ Non (mais possible)      | `NullPointerException`, `IllegalArgumentException`, `ArithmeticException` |
*/
public class ExceptionExamples {

	// 🔹 Exemple 1 : Exception non vérifiée (RuntimeException) --> unchecked
	// Exception
	static void division(int a, int b) {
		int resultat = a / b; // si b == 0 => ArithmeticException
		System.out.println("Résultat : " + resultat);
	}

	// 🔹 Exemple 2 : Exception vérifiée (IOException) --> checked 
	static void lireFichier(String chemin) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(chemin));
		String ligne = reader.readLine();
		System.out.println("1re ligne du fichier : " + ligne);
		reader.close();
	}

	// 🔹 Exemple 3 : finally garanti l’exécution
	static void exempleFinally() {
		Scanner sc = new Scanner(System.in);
		try {
			System.out.print("Entrez un entier : ");
			int val = sc.nextInt();
			System.out.println("Vous avez entré : " + val);
		} catch (Exception e) {
			System.err.println("Erreur : " + e.getMessage());
		} finally {
			sc.close();
			System.out.println("Scanner fermé (finally).");
		}
	}

	// 🔹 Exemple 4 : Exception personnalisée
	static void verifierNom(String nom) throws NomInvalideException {
		if (nom == null || nom.isBlank()) {
			throw new NomInvalideException("Le nom est vide ou null !");
		}
		System.out.println("Nom valide : " + nom);
	}

	// 🔹 Exemple 5 : Chaine d’exceptions (try-catch imbriqués)
	static void traitementComplet(String nom, int a, int b) {
		try {
			verifierNom(nom);
			division(a, b);
		} catch (NomInvalideException e) {
			System.err.println("Erreur de nom : " + e.getMessage());
		} catch (ArithmeticException e) {
			System.err.println("Erreur de division : " + e.getMessage());
		}
	}

	public static void main(String[] args) {
		System.out.println("=== Exemple 1 : RuntimeException ===");
		try {
			division(10, 0); // provoque ArithmeticException
		} catch (ArithmeticException e) {
			System.err.println("Division par zéro : " + e.getMessage());
		}

		System.out.println("\n=== Exemple 2 : IOException ===");
		try {
			lireFichier("src/main/resources/test.txt");
		} catch (IOException e) {
			System.err.println("Erreur fichier : " + e.getMessage());
		}

		System.out.println("\n=== Exemple 3 : finally ===");
		exempleFinally();

		System.out.println("\n=== Exemple 4 : exception personnalisée ===");
		try {
			verifierNom("  "); // provoque NomInvalideException
		} catch (NomInvalideException e) {
			System.err.println("Nom invalide : " + e.getMessage());
		}

		System.out.println("\n=== Exemple 5 : traitement combiné ===");
		traitementComplet("", 10, 0); // 2 exceptions possibles
	}
}

// 🔸 Classe d’exception personnalisée
class NomInvalideException extends Exception {
	public NomInvalideException(String message) {
		super(message);
	}
}
