package com.expertjava.pooavancee.heritagedemo;

public class Camion extends Vehicule {

	public Camion(String marque, String couleur, String pays, String immatriculation) {
		super(marque, couleur, pays, immatriculation);
	}
	
	public void demarrer() {
		System.out.println("Le camion va demarrer ");
	}

}
