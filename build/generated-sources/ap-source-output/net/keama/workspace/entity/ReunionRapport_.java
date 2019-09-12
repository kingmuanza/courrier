package net.keama.workspace.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ReunionRapport.class)
public abstract class ReunionRapport_ {

	public static volatile SingularAttribute<ReunionRapport, Integer> idreunionRapport;
	public static volatile SingularAttribute<ReunionRapport, Reunion> reunion;
	public static volatile SingularAttribute<ReunionRapport, String> chemin;
	public static volatile SingularAttribute<ReunionRapport, String> nom;
	public static volatile SingularAttribute<ReunionRapport, Date> dateEnregistrement;
	public static volatile SingularAttribute<ReunionRapport, String> statut;

}

