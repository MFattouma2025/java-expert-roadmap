package com.expertjava.designpatterns.structure;

/**
 * Le pattern Adapter est super utile pour illustrer comment connecter deux
 * interfaces incompatibles sans les modifier. le but est de permettre à deux
 * classes qui n’ont pas la même interface de travailler ensemble, en adaptant
 * l’une à l’autre.
 * 
 */
//interface cible 
interface Paiement {
	void payerMontant(double montant);
}

//interface incompatible 
interface PayPalAPI {
	void effectuerPaiement(double montant);
}

//Classe existante :
class PayPalService implements PayPalAPI {
	@Override
	public void effectuerPaiement(double montant) {
		System.out.println("Paiement de " + montant + "€ effectué via PayPal.");
	}
}

//On cree un adapter pour pouvoir adapter entre les deux interfaces 

class PayPalAdapter implements Paiement {

	private PayPalAPI payPalAPI;

	public PayPalAdapter(PayPalAPI payPalAPI) {
		this.payPalAPI = payPalAPI;
	}

	@Override
	public void payerMontant(double montant) {
		// Adaptation entre l’interface attendue et celle du service
		payPalAPI.effectuerPaiement(montant);
	}
}

public class AdapterDemo {

	public static void main(String[] args) {
		PayPalService paypal = new PayPalService();
		Paiement paiement = new PayPalAdapter(paypal);

		paiement.payerMontant(100.0); // Paiement de 100.0€ via PayPal.
	}

}