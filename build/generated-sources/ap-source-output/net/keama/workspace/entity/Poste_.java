package net.keama.workspace.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Poste.class)
public abstract class Poste_ {

	public static volatile SingularAttribute<Poste, String> libelle;
	public static volatile SingularAttribute<Poste, Boolean> estChefStructure;
	public static volatile SingularAttribute<Poste, Integer> idposte;
	public static volatile SingularAttribute<Poste, Poste> poste;
	public static volatile SingularAttribute<Poste, Structure> structure;
	public static volatile SetAttribute<Poste, Employe> employes;
	public static volatile SetAttribute<Poste, Poste> postes;
	public static volatile SingularAttribute<Poste, String> sigle;

}

