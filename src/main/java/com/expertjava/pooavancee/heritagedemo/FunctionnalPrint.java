package com.expertjava.pooavancee.heritagedemo;

@FunctionalInterface
public interface FunctionnalPrint {
	
	void print(String message);


	default void printUpperCase(String message) {
		System.out.println(message.toUpperCase());
	}

	static void printOKFood() {
		System.out.println("The food is ok ");
	}
}
