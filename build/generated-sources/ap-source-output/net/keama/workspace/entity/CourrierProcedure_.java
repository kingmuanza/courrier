package net.keama.workspace.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CourrierProcedure.class)
public abstract class CourrierProcedure_ {

	public static volatile SingularAttribute<CourrierProcedure, Integer> idcourrierProcedure;
	public static volatile SingularAttribute<CourrierProcedure, String> ref;
	public static volatile SingularAttribute<CourrierProcedure, CourrierType> courrierType;
	public static volatile SetAttribute<CourrierProcedure, CourrierProcedureTache> courrierProcedureTaches;
	public static volatile SingularAttribute<CourrierProcedure, String> description;
	public static volatile SingularAttribute<CourrierProcedure, String> nom;
	public static volatile SingularAttribute<CourrierProcedure, String> statut;
	public static volatile SetAttribute<CourrierProcedure, CourrierProcedureEnCours> courrierProcedureEnCourses;

}

