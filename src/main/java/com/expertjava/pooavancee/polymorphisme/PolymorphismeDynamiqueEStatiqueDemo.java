package com.expertjava.pooavancee.polymorphisme;

/*
 * | Concept                      | Aussi appelé                | Résolution  | Description courte                                                   |
| ------------------------------- | --------------------------- | ----------- | -------------------------------------------------------------------- |
| **Surcharge** (`overloading`)   | Polymorphisme **statique**  | Compilation | Plusieurs méthodes avec **même nom** mais **signatures différentes** |
| **Substitution** (`overriding`) | Polymorphisme **dynamique** | Exécution   | Une **sous-classe redéfinit** une méthode de la classe parente       |

 */

//| Caractéristique        | Surcharge (`overloading`) | Substitution (`overriding`)     |
//| ---------------------- | ------------------------- | ------------------------------- |
//| Nom de la méthode      | Même                      | Même                            |
//| Signature              | **Différente**            | **Identique** (ou très proche)  |
//| Portée                 | Dans la **même classe**   | Entre **classe parente/enfant** |
//| Moment de résolution   | **Compilation**           | **Exécution**                   |
//| Héritage nécessaire ?  | ❌ Non                     | ✅ Oui                           |
//| Annotation `@Override` | ❌ Non applicable          | ✅ Recommandé                    |

// 1. Surcharge (Polymorphisme statique)
class Calculateur {

	public int additionner(int a, int b) {
		return a + b;
	}

	public double additionner(double a, double b) {
		return a + b;
	}

	public int additionner(int a, int b, int c) {
		return a + b + c;
	}
}

//2. Substitution (Polymorphisme dynamique)
class Animal1 {
	public void parler() {
		System.out.println("L’animal fait un bruit");
	}
}

class Chien1 extends Animal1 {
	@Override
	public void parler() {
		System.out.println("Le chien aboie");
	}
}
