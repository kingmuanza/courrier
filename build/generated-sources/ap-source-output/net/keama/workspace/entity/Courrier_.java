package net.keama.workspace.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Courrier.class)
public abstract class Courrier_ {

	public static volatile SingularAttribute<Courrier, String> note;
	public static volatile SingularAttribute<Courrier, Boolean> entrant;
	public static volatile SingularAttribute<Courrier, String> nature;
	public static volatile SingularAttribute<Courrier, String> adresseExpediteur;
	public static volatile SingularAttribute<Courrier, Integer> importance;
	public static volatile SetAttribute<Courrier, CourrierFichier> courrierFichiers;
	public static volatile SingularAttribute<Courrier, String> objet;
	public static volatile SingularAttribute<Courrier, Integer> idcourrier;
	public static volatile SingularAttribute<Courrier, Structure> structure;
	public static volatile SingularAttribute<Courrier, String> ref;
	public static volatile SingularAttribute<Courrier, String> expediteur;
	public static volatile SetAttribute<Courrier, CourrierResponsable> courrierResponsables;
	public static volatile SingularAttribute<Courrier, Date> dateEmission;
	public static volatile SingularAttribute<Courrier, String> support;
	public static volatile SingularAttribute<Courrier, Date> dateEnr;
	public static volatile SingularAttribute<Courrier, Correspondant> correspondant;
	public static volatile SingularAttribute<Courrier, String> statut;
	public static volatile SetAttribute<Courrier, CourrierProcedureEnCours> courrierProcedureEnCourses;

}

