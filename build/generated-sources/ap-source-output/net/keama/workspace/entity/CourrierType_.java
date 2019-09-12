package net.keama.workspace.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CourrierType.class)
public abstract class CourrierType_ {

	public static volatile SetAttribute<CourrierType, CourrierProcedure> courrierProcedures;
	public static volatile SingularAttribute<CourrierType, Integer> idcourrierType;
	public static volatile SingularAttribute<CourrierType, String> nom;
	public static volatile SingularAttribute<CourrierType, String> commentaire;
	public static volatile SingularAttribute<CourrierType, String> statut;

}

