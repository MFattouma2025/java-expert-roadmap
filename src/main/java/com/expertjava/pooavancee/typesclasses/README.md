# Types de classes en Java

Java permet de définir plusieurs types de classes particulières au sein d’une autre classe. Ces classes offrent des moyens puissants d’organiser et de structurer ton code de manière plus propre ou encapsulée.

---

## 📘 Fichier : `ClassTypesDemo.java`

Ce fichier contient les 4 grands types de classes internes utilisées en Java.

| Type de classe           | Définition rapide                                      | Accès à la classe externe |
|--------------------------|--------------------------------------------------------|---------------------------|
| Inner class (non statique) | Définie dans une classe, liée à l’instance            | ✅ oui                    |
| Static nested class        | Classe imbriquée, mais indépendante de l’instance     | ❌ non (seulement membres `static`) |
| Local class                | Définie dans une méthode                              | ✅ oui                    |
| Anonymous class            | Définie à la volée pour implémenter une interface     | ✅ oui                    |

--

# Usage pratique des types de classes imbriquées en Java

## Pourquoi connaître ces types de classes ?

Même si tu n’as pas encore eu besoin de ces types de classes dans ton travail, ils sont importants pour :

- Comprendre les subtilités du langage Java.
- Écrire du code plus **encapsulé** et **modulaire**.
- Maintenir ou lire du code complexe ou de frameworks.

---

## Quand utiliser ces types de classes ?

| Type de classe           | Quand l’utiliser concrètement                     | Exemple d’usage concret                             |
|-------------------------|-------------------------------------------------|----------------------------------------------------|
| **Inner class**          | Quand une classe doit accéder aux membres privés d’une instance externe | Classe interne dans une GUI pour accéder aux données de la fenêtre |
| **Static nested class**  | Quand une classe appartient logiquement à une autre, sans besoin d’accès à l’instance | Classe `Builder` interne, classes utilitaires       |
| **Local class**          | Quand une classe est spécifique et temporaire à une méthode | Helper local ou validation dans une méthode        |
| **Anonymous class**      | Pour implémenter rapidement une interface ou une classe abstraite | Callbacks, gestionnaires d’événements               |

---

## Pourquoi tu ne les vois pas toujours ?

- Beaucoup de frameworks modernes utilisent des mécanismes comme les **lambdas** ou **proxies** qui masquent ces détails.
- Les projets simples préfèrent souvent des classes nommées classiques, plus lisibles.
- Leur usage est plus fréquent dans les projets complexes ou très modulaires.

---

## Ce que tu gagnes à les connaître

- Mieux comprendre comment Java gère les relations entre classes.
- Être capable d’écrire du code plus flexible et propre.
- Pouvoir lire et maintenir du code avancé ou provenant d’autres développeurs.

---

Tu peux tester les exemples pratiques dans `ClassTypesDemo.java`, `NestedClassDetailsDemo.java` et `AnonymousAndLocalClassDemo.java` pour voir ces concepts en action.

