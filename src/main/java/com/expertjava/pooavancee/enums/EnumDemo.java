package com.expertjava.pooavancee.enums;

//Interface pour démonstration
interface Priorisable {
	int getPriorite();
}

//Enum avancé
public enum EnumDemo implements Priorisable {

	NOUVELLE(1) {
		@Override
		public void traiter() {
			System.out.println("Commande enregistrée. En attente de paiement.");
		}
	},
	EN_COURS(2) {
		@Override
		public void traiter() {
			System.out.println("Commande en cours de traitement.");
		}
	},
	EXPEDIEE(3) {
		@Override
		public void traiter() {
			System.out.println("Commande expédiée.");
		}
	},
	ANNULEE(0) {
		@Override
		public void traiter() {
			System.out.println("Commande annulée.");
		}
	};

	private final int code;

	// Constructeur privé obligatoire
	EnumDemo(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	// Méthode abstraite à implémenter par chaque constante
	public abstract void traiter();

	// Implémentation de l’interface
	@Override
	public int getPriorite() {
		return this.code;
	}
}
