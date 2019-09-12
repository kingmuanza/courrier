package net.keama.workspace.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CourrierResponsable.class)
public abstract class CourrierResponsable_ {

	public static volatile SingularAttribute<CourrierResponsable, Boolean> needFile;
	public static volatile SingularAttribute<CourrierResponsable, Integer> idcourrierResponsable;
	public static volatile SingularAttribute<CourrierResponsable, Employe> employe;
	public static volatile SingularAttribute<CourrierResponsable, Courrier> courrier;
	public static volatile SingularAttribute<CourrierResponsable, Date> dateRecuperation;
	public static volatile SingularAttribute<CourrierResponsable, String> libelle;
	public static volatile SingularAttribute<CourrierResponsable, CourrierProcedureEnCours> courrierProcedureEnCours;
	public static volatile SingularAttribute<CourrierResponsable, String> statutPrecedent;
	public static volatile SingularAttribute<CourrierResponsable, String> typeApprobation;
	public static volatile SetAttribute<CourrierResponsable, CourrierResponsableCommentaire> courrierResponsableCommentaires;
	public static volatile SingularAttribute<CourrierResponsable, CourrierProcedureTache> courrierProcedureTache;
	public static volatile SingularAttribute<CourrierResponsable, String> commentaire;
	public static volatile SingularAttribute<CourrierResponsable, String> statut;
	public static volatile SingularAttribute<CourrierResponsable, Date> dateLimite;

}

