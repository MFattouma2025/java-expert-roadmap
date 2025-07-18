package com.expertjava.pooavancee.heritagedemo;

import lombok.Data;

@Data

public class Meat extends Food implements FoodPackage  {

	private boolean isOriginUE;

	public Meat(String name, int calories, double weightInGrams) {
		super(name, calories, weightInGrams);

	}
	public Meat() {
		super();

	}

	@Override
	 boolean isHealthy() {
		return true;
	}

	@Override
	public void prepare() {
		System.out.println("Cook the meat ");

	}

	@Override
	public String getPackageType() {
		return "Barquette en plastique recyclé";
	}

	@Override
	public void packageFood() {
		System.out.println(String.format("La viande est emballé dans une %s", getPackageType()));
	}

	public void fullPackagingProcess() {
		
		FunctionnalPrint print = (msg) -> System.out.println("Type de packaging : " + msg);
	
		print.print(String.format("%s , meat origin UE : %s" ,getPackageType(), isOriginUE ));
		FunctionnalPrint.printOKFood();
		
		packageFood(); // méthode implémentée dans la classe
		labelPackage(this.name); // méthode default de l'interface
	}
	
	@Override
	public String getLimitDate() {
		return "12/12/2025";
	}
	
	
	 public static void main(String[] args) {
		 Meat meat = new Meat("Chicken", 100, 100.0);
		 meat.fullPackagingProcess();
		 
	 }

	


}
