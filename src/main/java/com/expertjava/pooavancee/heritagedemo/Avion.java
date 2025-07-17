package com.expertjava.pooavancee.heritagedemo;

public class Avion extends Vehicule {

	public Avion(String marque, String couleur, String pays, String immatriculation) {
		super(marque, couleur, pays, immatriculation);
		
	}
	 public void demarrer() {
	    	System.out.println("L'avion vole  ")	;	
		}

}
