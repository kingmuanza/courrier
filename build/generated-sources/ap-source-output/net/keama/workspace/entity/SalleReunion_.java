package net.keama.workspace.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(SalleReunion.class)
public abstract class SalleReunion_ {

	public static volatile SingularAttribute<SalleReunion, Integer> capacite;
	public static volatile SetAttribute<SalleReunion, Reunion> reunions;
	public static volatile SingularAttribute<SalleReunion, Integer> idsalleReunion;
	public static volatile SingularAttribute<SalleReunion, String> nom;
	public static volatile SingularAttribute<SalleReunion, String> statut;

}

