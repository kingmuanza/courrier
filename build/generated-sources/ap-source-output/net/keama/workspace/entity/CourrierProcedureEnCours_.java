package net.keama.workspace.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CourrierProcedureEnCours.class)
public abstract class CourrierProcedureEnCours_ {

	public static volatile SingularAttribute<CourrierProcedureEnCours, Integer> idcourrierProcedureEnCours;
	public static volatile SingularAttribute<CourrierProcedureEnCours, String> ref;
	public static volatile SetAttribute<CourrierProcedureEnCours, CourrierResponsable> courrierResponsables;
	public static volatile SingularAttribute<CourrierProcedureEnCours, Date> dateDebut;
	public static volatile SingularAttribute<CourrierProcedureEnCours, Courrier> courrier;
	public static volatile SingularAttribute<CourrierProcedureEnCours, CourrierProcedure> courrierProcedure;
	public static volatile SingularAttribute<CourrierProcedureEnCours, String> statut;

}

