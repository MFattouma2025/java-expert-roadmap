package com.expertjava.pooavancee.solidexamples;

//L - Liskov Substitution Principle (LSP)
//Si S est une sous-classe de T, alors les objets de type T peuvent être remplacés
//par des objets de type S sans altérer la cohérence du programme”. En d’autres termes,
//cela signifie que les sous-classes doivent pouvoir être utilisées de manière 
//interchangeable avec leurs classes parentes, sans introduire d’erreurs ni de 
//comportements inattendus.



class Oiseau {
    void voler() {
        System.out.println("Je vole !");
    }
}

//cette classe viole le principe de LISKOV

class Autruche extends Oiseau {
    
	@Override
    void voler() {
        throw new UnsupportedOperationException("Je ne vole pas !");
    }
}
