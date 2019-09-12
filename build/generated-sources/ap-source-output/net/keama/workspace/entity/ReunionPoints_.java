package net.keama.workspace.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ReunionPoints.class)
public abstract class ReunionPoints_ {

	public static volatile SingularAttribute<ReunionPoints, Integer> idreunionPoints;
	public static volatile SingularAttribute<ReunionPoints, Integer> ordre;
	public static volatile SingularAttribute<ReunionPoints, String> libelle;
	public static volatile SingularAttribute<ReunionPoints, Reunion> reunion;
	public static volatile SingularAttribute<ReunionPoints, String> statut;

}

