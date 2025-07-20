package com.expertjava.designpatterns.comportement;
import java.util.ArrayList;
import java.util.List;
/**
 *  Le Observer Pattern est un des plus puissants et les plus utilisés — notamment dans les systèmes réactifs,
 *   les événements UI, ou même en backend pour la logique de notification, audit, logs, etc.
 */

interface Observateur {
    void mettreAJour(String donnees);
}
 

interface Sujet {
	    void ajouterObservateur(Observateur o);
	    void supprimerObservateur(Observateur o);
	    void notifierObservateurs(String donnees);
	}


class StationMeteo implements Sujet {
	
     private List<Observateur> observateurs = new ArrayList<>();

     @Override
     public void ajouterObservateur(Observateur o) {
         observateurs.add(o);
     }

     @Override
     public void supprimerObservateur(Observateur o) {
         observateurs.remove(o);
     }

     @Override
     public void notifierObservateurs(String donnees) {
         for (Observateur o : observateurs) {
             o.mettreAJour(donnees);
         }
     }

     public void nouvellesDonnees(String donnees) {
         System.out.println("Nouvelle donnée météo : " + donnees);
         notifierObservateurs(donnees);
     }
 }

 class ApplicationMobile implements Observateur {
    @Override
    public void mettreAJour(String donnees) {
        System.out.println(" App Mobile : Donnée reçue => " + donnees);
    }
}

 class ConsoleLogger implements Observateur {
    @Override
    public void mettreAJour(String donnees) {
        System.out.println(" Console : Log météo => " + donnees);
    }
}

 class NotificationEmail implements Observateur {
    @Override
    public void mettreAJour(String donnees) {
        System.out.println("📧 Email envoyé : " + donnees);
    }
}
/*
 * | Avantage                    | Détail                                                 |
| --------------------------- | ------------------------------------------------------ |
| Notification automatique | Plusieurs observateurs réagissent aux changements      |
| Faible couplage          | Le sujet ne connaît pas les détails des observateurs   |
| Évolutif                 | Tu peux ajouter ou retirer des observateurs à la volée |
 */
 
public class ObserverDemo {
    public static void main(String[] args) {
        StationMeteo station = new StationMeteo();

        Observateur appMobile = new ApplicationMobile();
        Observateur console = new ConsoleLogger();
        Observateur email = new NotificationEmail();

        // Abonnements
        station.ajouterObservateur(appMobile);
        station.ajouterObservateur(console);
        station.ajouterObservateur(email);

        // Nouvelle donnée météo
        station.nouvellesDonnees("Température: 28°C, Humidité: 60%");

        // Suppression d’un observateur
        station.supprimerObservateur(email);

        // Nouvelle mise à jour
        station.nouvellesDonnees("Température: 30°C, Humidité: 55%");
    }
}

