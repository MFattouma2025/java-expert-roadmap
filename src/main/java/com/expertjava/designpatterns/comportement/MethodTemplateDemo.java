package com.expertjava.designpatterns.comportement;
/**
* Le Template Method Pattern est un classique des patterns comportementaux.
* Il est idéal pour réutiliser une structure d’algorithme tout en permettant à des sous-classes de personnaliser certaines étapes.
*reflexion : Le Template Method Pattern est probablement le pattern qui met le plus en œuvre 
*les 4 piliers fondamentaux de la programmation orientée objet(Abstraction, le ploymorphismee , l'encapsulation , heritage)
* à la fois de façon claire, pédagogique et puissante. 
 */
abstract class RapportTemplate {

    // Méthode template : fixe les étapes de l'algo
    public final void genererRapport() {
        ouvrirDocument();
        ajouterEntete();
        ajouterContenu();
        fermerDocument();
    }

    protected void ouvrirDocument() {
        System.out.println("Ouverture du document...");
    }

    protected void fermerDocument() {
        System.out.println("Fermeture du document.");
    }

    protected abstract void ajouterEntete();
    protected abstract void ajouterContenu();
}

class RapportPDF extends RapportTemplate {
	
    @Override
    protected void ajouterEntete() {
        System.out.println("[PDF] En-tête ajouté");
    }

    @Override
    protected void ajouterContenu() {
        System.out.println("[PDF] Contenu du rapport PDF ajouté");
    }
}

class RapportExcel extends RapportTemplate {
    @Override
    protected void ajouterEntete() {
        System.out.println("[Excel] En-tête ajouté");
    }

    @Override
    protected void ajouterContenu() {
        System.out.println("[Excel] Contenu du rapport Excel ajouté");
    }
}

public class MethodTemplateDemo {
    public static void main(String[] args) {
        RapportTemplate rapportPDF = new RapportPDF();
        RapportTemplate rapportExcel = new RapportExcel();

        System.out.println("== Génération du rapport PDF ==");
        rapportPDF.genererRapport();

        System.out.println("\n== Génération du rapport Excel ==");
        rapportExcel.genererRapport();
    }
}

