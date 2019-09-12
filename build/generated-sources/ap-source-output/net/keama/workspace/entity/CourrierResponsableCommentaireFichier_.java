package net.keama.workspace.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CourrierResponsableCommentaireFichier.class)
public abstract class CourrierResponsableCommentaireFichier_ {

	public static volatile SingularAttribute<CourrierResponsableCommentaireFichier, Integer> idcourrierResponsableCommentaireFichier;
	public static volatile SingularAttribute<CourrierResponsableCommentaireFichier, CourrierResponsableCommentaire> courrierResponsableCommentaire;
	public static volatile SingularAttribute<CourrierResponsableCommentaireFichier, String> chemin;
	public static volatile SingularAttribute<CourrierResponsableCommentaireFichier, String> nom;
	public static volatile SingularAttribute<CourrierResponsableCommentaireFichier, Date> dateEnregistrement;
	public static volatile SingularAttribute<CourrierResponsableCommentaireFichier, String> statut;

}

