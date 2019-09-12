package net.keama.workspace.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CourrierProcedureTache.class)
public abstract class CourrierProcedureTache_ {

	public static volatile SingularAttribute<CourrierProcedureTache, String> ref;
	public static volatile SetAttribute<CourrierProcedureTache, CourrierResponsable> courrierResponsables;
	public static volatile SingularAttribute<CourrierProcedureTache, Employe> employe;
	public static volatile SingularAttribute<CourrierProcedureTache, Integer> ordre;
	public static volatile SingularAttribute<CourrierProcedureTache, String> libelle;
	public static volatile SingularAttribute<CourrierProcedureTache, String> typeApprobation;
	public static volatile SingularAttribute<CourrierProcedureTache, Integer> duree;
	public static volatile SingularAttribute<CourrierProcedureTache, Integer> idcourrierProcedureTache;
	public static volatile SingularAttribute<CourrierProcedureTache, CourrierProcedure> courrierProcedure;
	public static volatile SingularAttribute<CourrierProcedureTache, String> commentaire;

}

