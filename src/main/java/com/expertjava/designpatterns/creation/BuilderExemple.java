package com.expertjava.designpatterns.creation;

/**Avantages des builders :
 * Simplifie la création d’objets avec beaucoup d’attributs (surtout optionnels)
 * Évite les constructeurs avec 10+ paramètres (pas lisible)
 * Favorise l’immutabilité (pas de setters publics)
 * Code fluide et lisible (méthodes chaînées)
 */

class User {
    private final String prenom;
    private final String nom;
    private final int age;
    private final String email;

    private User(Builder builder) {
        this.prenom = builder.prenom;
        this.nom = builder.nom;
        this.age = builder.age;
        this.email = builder.email;
    }

    // Getters
    public String getPrenom() { return prenom; }
    public String getNom() { return nom; }
    public int getAge() { return age; }
    public String getEmail() { return email; }

    public static class Builder {
        private String prenom;
        private String nom;
        private int age;
        private String email;

        public Builder prenom(String prenom) {
            this.prenom = prenom;
            return this;
        }

        public Builder nom(String nom) {
            this.nom = nom;
            return this;
        }

        public Builder age(int age) {
            this.age = age;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}

public class BuilderExemple {
	   User user = new User.Builder()
			.prenom("Fattouma")
			.nom("Marzouk")
			.age(35)
			.email("fattouma@gmail.com")
			.build();

}

