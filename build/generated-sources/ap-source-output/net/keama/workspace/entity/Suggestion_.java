package net.keama.workspace.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Suggestion.class)
public abstract class Suggestion_ {

	public static volatile SingularAttribute<Suggestion, Employe> employe;
	public static volatile SingularAttribute<Suggestion, String> titre;
	public static volatile SingularAttribute<Suggestion, Date> dateEmission;
	public static volatile SingularAttribute<Suggestion, Integer> idsuggestion;
	public static volatile SingularAttribute<Suggestion, String> contenu;

}

