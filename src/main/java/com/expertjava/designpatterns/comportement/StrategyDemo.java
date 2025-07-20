package com.expertjava.designpatterns.comportement;
/**
 * La stratégie est un modèle de conception comportementale qui vous permet de définir une famille d'algorithmes,
 *  de placer chacun d'eux dans une classe distincte et de rendre leurs objets interchangeables.
 * donc il permet de changer dynamiquement le comportement d’un objet sans toucher à son code. 
 * Très utile pour illustrer le principe OCP (Open/Closed) de SOLID.
 * l'objectif est de définir une famille d’algorithmes, de les encapsuler dans des 
 * classes séparées, et de les rendre interchangeables à l'exécution.
 */

interface StrategieReduction {
    double calculerReduction(double montant);
}

class ReductionClientFidele implements StrategieReduction {
    @Override
    public double calculerReduction(double montant) {
        return montant * 0.10; // 10% de réduction
    }
}

class ReductionClientPremium implements StrategieReduction {
    @Override
    public double calculerReduction(double montant) {
        return montant * 0.20; // 20% de réduction
    }
}

class ReductionSansReduction implements StrategieReduction {
    @Override
    public double calculerReduction(double montant) {
        return 0.0;
    }
}
/*
 * | Avantage                  | Détail                                                           |
| -----------------------------| -----------------------------------------------------------------|
|  Algorithme interchangeable  | Tu changes la stratégie sans toucher à la classe `Facture`       |
|  OCP (Open/Closed Principle) | Tu peux ajouter de nouvelles réductions sans modifier l’existant |
|  Encapsulation métier        | Chaque stratégie est isolée dans sa propre classe                |
 */

class Facture {
	
    private StrategieReduction strategie;

    public Facture(StrategieReduction strategie) {
        this.strategie = strategie;
    }

    public void setStrategie(StrategieReduction strategie) {
        this.strategie = strategie;
    }

    public double calculerPrixFinal(double montant) {
        double reduction = strategie.calculerReduction(montant);
        return montant - reduction;
    }
}

public class StrategyDemo {
    public static void main(String[] args) {
        double montant = 200.0;

        Facture facture = new Facture(new ReductionSansReduction());
        System.out.println("Prix sans réduction : " + facture.calculerPrixFinal(montant));

        facture.setStrategie(new ReductionClientFidele());
        System.out.println("Prix client fidèle : " + facture.calculerPrixFinal(montant));

        facture.setStrategie(new ReductionClientPremium());
        System.out.println("Prix client premium : " + facture.calculerPrixFinal(montant));
    }
}

