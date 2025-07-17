package com.expertjava.pooavancee.solidexamples;

//I - Interface Segregation Principle (ISP)

//Mieux vaut avoir plusieurs interfaces spécifiques qu'une grosse interface générale.

interface Travailleur {
	void travailler();

	void manger();
}

class Humain implements Travailleur {
	@Override
	public void travailler() {
		System.out.println("un humain qui travaille");
	}

	@Override
	public void manger() {
		System.out.println("un humain qui mange ");
	}

}

class Robot implements Travailleur {
	@Override
	public void travailler() {
		System.out.println("un humain qui travaille");
	}

	@Override
	public void manger() {
		System.out.println("un Robot ne mge pas !!!  ");
	}
	// Problème : un robot n’a pas besoin de manger !
//	====================> Solution deux interface 

	interface TravailleurHumain {
		void travailler();
		void manger();
	}

	interface TravailleurRobot {
		void travailler();
	}
}
