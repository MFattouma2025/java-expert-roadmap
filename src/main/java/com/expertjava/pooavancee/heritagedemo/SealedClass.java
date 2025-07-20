package com.expertjava.pooavancee.heritagedemo;


// 	Restreindre la hiérarchie 
/*avantages des Sealed classes :
    1. Contrôle strict de l’héritage
	Évite que d’autres classes inconnues ou non prévues héritent de ta classe.
	Permet de garantir une architecture fermée.
	2. Sécurité et robustesse
	Utile pour des API où le domaine métier doit être limité.
	Permet un meilleur contrôle des évolutions.
	3. Meilleure gestion du pattern matching (switch)
	Dans les switch expressions avec pattern matching (Java 17+),
	le compilateur sait exactement quelles sous-classes sont possibles, ce qui permet d’avoir un contrôle exhaustif.
 */
public  sealed class SealedClass permits  ChildFromSealed {

}
