package net.keama.workspace.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Reunion.class)
public abstract class Reunion_ {

	public static volatile SingularAttribute<Reunion, Employe> employe;
	public static volatile SingularAttribute<Reunion, SalleReunion> salleReunion;
	public static volatile SingularAttribute<Reunion, String> titre;
	public static volatile SingularAttribute<Reunion, String> description;
	public static volatile SingularAttribute<Reunion, Integer> idreunion;
	public static volatile SingularAttribute<Reunion, Date> dateEnregistrement;
	public static volatile SetAttribute<Reunion, ReunionEmploye> reunionEmployes;
	public static volatile SetAttribute<Reunion, ReunionPoints> reunionPointses;
	public static volatile SingularAttribute<Reunion, String> ref;
	public static volatile SingularAttribute<Reunion, Date> dateDebut;
	public static volatile SetAttribute<Reunion, ReunionRapport> reunionRapports;
	public static volatile SingularAttribute<Reunion, Date> dateFin;
	public static volatile SingularAttribute<Reunion, String> statut;

}

