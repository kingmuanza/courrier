package net.keama.workspace.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Entreprise.class)
public abstract class Entreprise_ {

	public static volatile SingularAttribute<Entreprise, String> tel1;
	public static volatile SingularAttribute<Entreprise, String> tel2;
	public static volatile SingularAttribute<Entreprise, ImageLogo> imageLogo;
	public static volatile SingularAttribute<Entreprise, Date> dateCreation;
	public static volatile SingularAttribute<Entreprise, String> numeroContribuable;
	public static volatile SingularAttribute<Entreprise, String> adresseComplement;
	public static volatile SetAttribute<Entreprise, Site> sites;
	public static volatile SingularAttribute<Entreprise, Integer> identreprise;
	public static volatile SingularAttribute<Entreprise, String> nom;
	public static volatile SingularAttribute<Entreprise, String> bp;
	public static volatile SingularAttribute<Entreprise, String> tel3;
	public static volatile SingularAttribute<Entreprise, String> sigle;
	public static volatile SingularAttribute<Entreprise, String> email3;
	public static volatile SingularAttribute<Entreprise, String> email2;
	public static volatile SingularAttribute<Entreprise, String> email1;
	public static volatile SingularAttribute<Entreprise, Utilisateur> utilisateur;
	public static volatile SingularAttribute<Entreprise, String> adresse;
	public static volatile SingularAttribute<Entreprise, String> fax;

}

