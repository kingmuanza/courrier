package net.keama.workspace.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Procedures.class)
public abstract class Procedures_ {

	public static volatile SingularAttribute<Procedures, Integer> idprocedures;
	public static volatile SetAttribute<Procedures, ProcedureEffective> procedureEffectives;
	public static volatile SetAttribute<Procedures, TacheProcedure> tacheProcedures;
	public static volatile SingularAttribute<Procedures, String> nom;
	public static volatile SingularAttribute<Procedures, String> statut;

}

