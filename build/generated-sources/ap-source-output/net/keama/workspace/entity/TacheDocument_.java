package net.keama.workspace.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TacheDocument.class)
public abstract class TacheDocument_ {

	public static volatile SingularAttribute<TacheDocument, Tache> tache;
	public static volatile SingularAttribute<TacheDocument, Employe> employe;
	public static volatile SingularAttribute<TacheDocument, String> chemin;
	public static volatile SingularAttribute<TacheDocument, Integer> idtacheDocument;
	public static volatile SingularAttribute<TacheDocument, String> nom;
	public static volatile SingularAttribute<TacheDocument, Date> dateEnregistrement;
	public static volatile SingularAttribute<TacheDocument, String> statut;

}

