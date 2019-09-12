package net.keama.workspace.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CourrierFichier.class)
public abstract class CourrierFichier_ {

	public static volatile SingularAttribute<CourrierFichier, Integer> ordre;
	public static volatile SingularAttribute<CourrierFichier, Courrier> courrier;
	public static volatile SingularAttribute<CourrierFichier, String> chemin;
	public static volatile SingularAttribute<CourrierFichier, Integer> idcourrierFichier;
	public static volatile SingularAttribute<CourrierFichier, String> nom;
	public static volatile SingularAttribute<CourrierFichier, Date> dateEnr;
	public static volatile SingularAttribute<CourrierFichier, String> statut;

}

