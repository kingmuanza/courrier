package net.keama.workspace.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TacheCommentaire.class)
public abstract class TacheCommentaire_ {

	public static volatile SingularAttribute<TacheCommentaire, Integer> idtacheCommentaire;
	public static volatile SingularAttribute<TacheCommentaire, Tache> tache;
	public static volatile SingularAttribute<TacheCommentaire, Employe> employe;
	public static volatile SingularAttribute<TacheCommentaire, Date> datePublication;
	public static volatile SingularAttribute<TacheCommentaire, String> contenu;
	public static volatile SingularAttribute<TacheCommentaire, String> statut;

}

