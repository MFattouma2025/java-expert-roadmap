package com.expertjava.designpatterns.comportement;
/**
 * Curiosité ....  une approche moderne et réactive de l’Observer Pattern introduite en Java 9 :
 *  le Reactive Streams API, basé sur l’interface Flow.
 */

import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;

class CapteurMeteo extends SubmissionPublisher<String> {
	
    public void simulerEmissionDonnees() throws InterruptedException {
        String[] mesures = {
            "Température: 25°C", "Température: 26°C",
            "Température: 28°C", "Température: 30°C",
            "Température: 27°C"
        };

        for (String mesure : mesures) {
            System.out.println("[Capteur] Émission : " + mesure);
            submit(mesure);
            Thread.sleep(300);
        }

        close();
    }
}


// le Subscriber 
//ici il s'agit d'un Subscriber lent 

class ObservateurConsole implements Flow.Subscriber<String> {
	
    private Flow.Subscription subscription;

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription = subscription;
        subscription.request(1); // demander le premier élément
    }

    @Override
    public void onNext(String item) {
        System.out.println(" [Observateur] Reçu : " + item + " (traitement en cours...)");
        try {
            Thread.sleep(1000); // traitement long
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(" [Observateur] Traitement terminé pour : " + item);
        subscription.request(1); // demande l'élément suivant uniquement maintenant
    }

    @Override
    public void onError(Throwable throwable) {
        System.err.println("Erreur : " + throwable.getMessage());
    }

    @Override
    public void onComplete() {
        System.out.println("Transmission météo terminée.");
    }
}


class FiltreTemperatureProcessor extends SubmissionPublisher<String> implements Flow.Processor<String, String> {
    private Flow.Subscription subscription;

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription = subscription;
        subscription.request(1); // demander le premier élément
    }

    @Override
    public void onNext(String item) {
        System.out.println("[Processor] Reçu : " + item);

        // Extraire la température
        int temp = extraireValeur(item);
        if (temp >= 28) {
            System.out.println(" [Processor] Transmet : " + item);
            submit(item); // émet vers les subscribers en aval
        } else {
            System.out.println("[Processor] Ignoré : " + item);
        }

        subscription.request(1); // demander le suivant
    }

    private int extraireValeur(String texte) {
        return Integer.parseInt(texte.replaceAll("\\D+", ""));
    }

    @Override
    public void onError(Throwable throwable) {
        closeExceptionally(throwable);
    }

    @Override
    public void onComplete() {
        close(); // fermer le flux émis
    }
}

public class FlowObserverDemo {
	
    public static void main(String[] args) throws InterruptedException {
        CapteurMeteo capteur = new CapteurMeteo();
        FiltreTemperatureProcessor processor = new FiltreTemperatureProcessor();
        ObservateurConsole observateur = new ObservateurConsole();

        // Chaînage : capteur → processor → observateur
        capteur.subscribe(processor);
        processor.subscribe(observateur);

        capteur.simulerEmissionDonnees();

        // attendre la fin
        Thread.sleep(8000);
    }
}
