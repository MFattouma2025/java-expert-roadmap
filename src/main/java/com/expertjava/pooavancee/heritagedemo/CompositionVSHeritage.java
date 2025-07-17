package com.expertjava.pooavancee.heritagedemo;

//il ya des cas où la composition est souvent meilleure que l’héritage
//| Héritage (`extends`)                            | Composition                          |
//| ----------------------------------------------- | ------------------------------------ |
//| Très couplé à la classe parent                  | Moins couplé                         |
//| Peu flexible (une seule classe parente)         | Plus flexible (plusieurs composants) |
//| Risque de casser du code en changeant le parent | Non                                  |
//| Pas possible si tu n’as pas le code source      | Oui 

//🔷 L’héritage permet d’hériter de comportements 
//🔶 La composition permet d’utiliser un composant interne 

public class CompositionVSHeritage {

	Meat m = new Meat();

	public record Adresse(int num, String r, String ville, String pays) {}

	public void envoyer(Food f, Adresse adr) {
		System.out.println(String.format("le meat %s est envoyé à l'adresse %s", f.getName(), adr));
	}

	public void livrer() {
		m.setName("Boeuf");
		m.fullPackagingProcess();
		envoyer(m, new Adresse(4, " rue republique", "Paris", "France"));
	}

	public static void main(String[] args)   {
		CompositionVSHeritage c = new CompositionVSHeritage();
		c.livrer();
	}

}
