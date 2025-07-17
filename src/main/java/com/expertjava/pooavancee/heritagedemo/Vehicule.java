package com.expertjava.pooavancee.heritagedemo;

import lombok.Data;

// Heritage classique , les classes filles Avion et Camion 
@Data
public class Vehicule {
 

	private String marque;
	private String couleur ;
	private String pays;
	private String immatriculation;


	public Vehicule(String marque, String couleur, String pays, String immatriculation) {
		super();
		this.marque = marque;
		this.couleur = couleur;
		this.pays = pays;
		this.immatriculation = immatriculation;

	}
	
	public void demarrer() {
		
	}
	
	
	

}


