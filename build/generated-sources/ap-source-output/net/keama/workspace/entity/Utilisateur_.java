package net.keama.workspace.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Utilisateur.class)
public abstract class Utilisateur_ {

	public static volatile SingularAttribute<Utilisateur, Integer> idutilisateur;
	public static volatile SingularAttribute<Utilisateur, ProfilUtilisateur> profilUtilisateur;
	public static volatile SingularAttribute<Utilisateur, Date> dateCreation;
	public static volatile SingularAttribute<Utilisateur, Employe> employe;
	public static volatile SetAttribute<Utilisateur, Entreprise> entreprises;
	public static volatile SingularAttribute<Utilisateur, String> login;
	public static volatile SingularAttribute<Utilisateur, String> passe;
	public static volatile SingularAttribute<Utilisateur, String> statut;

}

