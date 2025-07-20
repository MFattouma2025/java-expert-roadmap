package com.expertjava.designpatterns.structure;
/**
 * Le Proxy est un objet qui agit comme un substitut pour accéder à un autre objet.
Il permet de contrôler, retarder, enrichir ou sécuriser l'accès à cet objet.
 */

//exemple : On veut qu'un  utilisateur ne puisse accéder au service de transfert d’argent que s’il est authentifié.

//prenons cette interface 
interface BanqueService {
    void transfererArgent(String from, String to, double montant);
}

//l'implementation de cette interface 

class BanqueServiceImpl implements BanqueService {
	
    @Override
    public void transfererArgent(String from, String to, double montant) {
        System.out.println("Transfert de " + montant + "€ de " + from + " vers " + to);
    }
}
//Le Proxy qui vérifie l'authentification

 class BanqueServiceProxy implements BanqueService {
    private BanqueService banqueService;
    private boolean utilisateurAuthentifie;

    public BanqueServiceProxy(BanqueService banqueService, boolean utilisateurAuthentifie) {
        this.banqueService = banqueService;
        this.utilisateurAuthentifie = utilisateurAuthentifie;
    }

    @Override
    public void transfererArgent(String from, String to, double montant) {
        if (utilisateurAuthentifie) {
            banqueService.transfererArgent(from, to, montant);
        } else {
            System.out.println("Accès refusé. Veuillez vous authentifier.");
        }
    }
}


public class ProxyDemo {
	public static void main(String[] args) {
        BanqueService vraiService = new BanqueServiceImpl();

        // Cas 1 : utilisateur non authentifié
        BanqueService proxyNonAuth = new BanqueServiceProxy(vraiService, false);
        proxyNonAuth.transfererArgent("Alice", "Bob", 100);

        // Cas 2 : utilisateur authentifié
        BanqueService proxyAuth = new BanqueServiceProxy(vraiService, true);
        proxyAuth.transfererArgent("Alice", "Bob", 100);
    }
}
