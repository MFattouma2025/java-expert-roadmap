package com.expertjava.pooavancee.solidexamples;

//S - Single Responsibility Principle (SRP)
//Si on crèe une calsse JournalAvecSauvegarde  


public class SingleResponsibilityPrinciple {
	void ajouterPage(String texte){
       System.out.println("add page .....");
    }
	 void supprimerPage(int index) {
		  System.out.println("remove page .....");
	    }


}

//!!! Mauvais : ici la classe JournalAvecSauvegarde viole SRP, 
//la calsse Journal doit juste gérer les pages et non le suavegarde
class JournalAvecSauvegarde {
    void sauvegarderDansFichier(String nomFichier) {
        // gestion de fichier => autre responsabilité
    }
}
