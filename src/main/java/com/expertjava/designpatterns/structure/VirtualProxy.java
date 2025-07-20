package com.expertjava.designpatterns.structure;

/**
 * (lazy loading) Le Virtual Proxy retarde l’instanciation d’un objet coûteux
 * jusqu’au moment où il est vraiment nécessaire.
 */
interface Image {
	void afficher();
}

// Implémentation réelle de l'objet lourd 

class ImageHauteResolution implements Image {
	private String nomFichier;

	public ImageHauteResolution(String nomFichier) {
		this.nomFichier = nomFichier;
		chargerDepuisDisque(nomFichier);
	}

	private void chargerDepuisDisque(String nomFichier) {
		System.out.println("Chargement de " + nomFichier + " depuis le disque...");
		try {
			Thread.sleep(2000); // simulation d’un chargement long
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void afficher() {
		System.out.println("Affichage de l’image : " + nomFichier);
	}
}


class ProxyImage implements Image {

	private String nomFichier;
	private ImageHauteResolution imageReelle;

	public ProxyImage(String nomFichier) {
		this.nomFichier = nomFichier;
	}

	@Override
	public void afficher() {
		if (imageReelle == null) {
			imageReelle = new ImageHauteResolution(nomFichier);
		}
		imageReelle.afficher();
	}
}

public class VirtualProxy {

	public static void main(String[] args) {
		Image image = new ProxyImage("photo-vacances.jpg"); // 

		System.out.println("Image proxy créée.");

		// L'image ne sera chargée qu'à l'affichage
		image.afficher(); // => Chargement... puis Affichage
		System.out.println("--- Deuxième appel ---");
		image.afficher(); // => Affichage immédiat (déjà chargée)
	}

}
