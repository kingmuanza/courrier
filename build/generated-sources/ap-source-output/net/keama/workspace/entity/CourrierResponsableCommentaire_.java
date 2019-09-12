package net.keama.workspace.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CourrierResponsableCommentaire.class)
public abstract class CourrierResponsableCommentaire_ {

	public static volatile SetAttribute<CourrierResponsableCommentaire, CourrierResponsableCommentaireFichier> courrierResponsableCommentaireFichiers;
	public static volatile SingularAttribute<CourrierResponsableCommentaire, CourrierResponsable> courrierResponsable;
	public static volatile SingularAttribute<CourrierResponsableCommentaire, Integer> idcourrierResponsableCommentaire;
	public static volatile SingularAttribute<CourrierResponsableCommentaire, String> commentaire;
	public static volatile SingularAttribute<CourrierResponsableCommentaire, Date> dateEnregistrement;
	public static volatile SingularAttribute<CourrierResponsableCommentaire, String> statut;

}

