package net.keama.workspace.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Tache.class)
public abstract class Tache_ {

	public static volatile SingularAttribute<Tache, Employe> employeByIdordonnateur;
	public static volatile SingularAttribute<Tache, Employe> employeByIdemploye;
	public static volatile SingularAttribute<Tache, String> libelle;
	public static volatile SingularAttribute<Tache, String> description;
	public static volatile SetAttribute<Tache, TacheDocument> tacheDocuments;
	public static volatile SingularAttribute<Tache, Date> dateDebut;
	public static volatile SingularAttribute<Tache, Date> dateEmission;
	public static volatile SingularAttribute<Tache, Date> dateTerminee;
	public static volatile SingularAttribute<Tache, Integer> idtache;
	public static volatile SingularAttribute<Tache, Date> dateFin;
	public static volatile SetAttribute<Tache, TacheEmploye> tacheEmployes;
	public static volatile SingularAttribute<Tache, String> statut;
	public static volatile SetAttribute<Tache, TacheCommentaire> tacheCommentaires;

}

