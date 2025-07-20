package com.expertjava.designpatterns.comportement;
import java.util.Stack;
/**
 * Le Command Pattern est un grand classique des patterns comportementaux, 
 *  est très utile notamment pour les systèmes de type undo/redo, macro-commandes, 
 *  scheduling, audit, ou exécution différée (CQRS, job queue…).
 */
// Interface commune pour toutes les actions
interface Commande {
    void executer();
    void annuler();
}

//Receveur1: L'objet réel (lampe) qui effectue l’action
class Lampe {
	 private boolean estAllumee = false;

	    public void allumer() {
	        estAllumee = true;
	        System.out.println(" La lampe est allumée.");
	    }

	    public void eteindre() {
	        estAllumee = false;
	        System.out.println(" La lampe est éteinte.");
	    }

	    public boolean estAllumee() {
	        return estAllumee;
	    }
}

class AllumerLampeCommande implements Commande {
	 private Lampe lampe;

	    public AllumerLampeCommande(Lampe lampe) {
	        this.lampe = lampe;
	    }

	    @Override
	    public void executer() {
	        lampe.allumer();
	    }

	    @Override
	    public void annuler() {
	        lampe.eteindre();
	        System.out.println(" Undo : lampe éteinte.");
	    }
}

class EteindreLampeCommande implements Commande {
	private Lampe lampe;

    public EteindreLampeCommande(Lampe lampe) {
        this.lampe = lampe;
    }

    @Override
    public void executer() {
        lampe.eteindre();
    }

    @Override
    public void annuler() {
        lampe.allumer();
        System.out.println(" Undo : lampe rallumée.");
    }
}

//invoker :Celui qui déclenche l’action (mais ne sait pas quoi)
//Télécommande intelligente avec historique

class Telecommande {
    private final Stack<Commande> historique = new Stack<>();
    private final Stack<Commande> redoPile = new Stack<>();

    public void executerCommande(Commande commande) {
        commande.executer();
        historique.push(commande);
        redoPile.clear(); // une nouvelle commande annule le redo possible
    }

    public void annulerDerniereCommande() {
        if (!historique.isEmpty()) {
            Commande derniere = historique.pop();
            derniere.annuler();
            redoPile.push(derniere);
        } else {
            System.out.println("Aucune commande à annuler.");
        }
    }

    public void refaireDerniereCommande() {
        if (!redoPile.isEmpty()) {
            Commande redo = redoPile.pop();
            redo.executer();
            historique.push(redo);
        } else {
            System.out.println("Aucune commande à refaire.");
        }
    }
}


//Contient l’objet cible + l’action à exécuter
public class CommandDemo {
	public static void main(String[] args) {
        Lampe lampe = new Lampe();

        Commande allumer = new AllumerLampeCommande(lampe);
        Commande eteindre = new EteindreLampeCommande(lampe);

        Telecommande telecommande = new Telecommande();

        System.out.println(" Allumer la lampe");
        telecommande.executerCommande(allumer);

        System.out.println(" Éteindre la lampe");
        telecommande.executerCommande(eteindre);

        System.out.println("Annuler la dernière commande");
        telecommande.annulerDerniereCommande();

        System.out.println(" Refaire la commande");
        telecommande.refaireDerniereCommande();
    }
}

