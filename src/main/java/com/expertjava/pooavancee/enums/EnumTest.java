package com.expertjava.pooavancee.enums;


public class EnumTest {

    public static void main(String[] args) {
    	EnumDemo etat = EnumDemo.EN_COURS;

        System.out.println("État : " + etat);
        System.out.println("Code : " + etat.getCode());
        System.out.println("Priorité : " + etat.getPriorite());

        etat.traiter();

        System.out.println("\n=== Switch sur enum ===");
        switch (etat) {
            case NOUVELLE -> System.out.println("À valider !");
            case EN_COURS -> System.out.println("Traitement en cours...");
            case EXPEDIEE -> System.out.println("À suivre.");
            case ANNULEE -> System.out.println("Commande stoppée.");
        }
    }
}
