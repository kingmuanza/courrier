package net.keama.workspace.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TacheProcedure.class)
public abstract class TacheProcedure_ {

	public static volatile SetAttribute<TacheProcedure, TacheProcedureEffectue> tacheProcedureEffectues;
	public static volatile SingularAttribute<TacheProcedure, Integer> idtacheProcedure;
	public static volatile SingularAttribute<TacheProcedure, Employe> employe;
	public static volatile SingularAttribute<TacheProcedure, Integer> ordre;
	public static volatile SingularAttribute<TacheProcedure, Procedures> procedures;
	public static volatile SingularAttribute<TacheProcedure, String> libelle;
	public static volatile SingularAttribute<TacheProcedure, Integer> dureeEnJour;
	public static volatile SingularAttribute<TacheProcedure, String> description;

}

