package net.keama.workspace.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Correspondant.class)
public abstract class Correspondant_ {

	public static volatile SingularAttribute<Correspondant, EntreprisePartenaire> entreprisePartenaire;
	public static volatile SingularAttribute<Correspondant, String> image;
	public static volatile SetAttribute<Correspondant, Courrier> courriers;
	public static volatile SingularAttribute<Correspondant, Integer> idcorrespondant;
	public static volatile SingularAttribute<Correspondant, String> nomComplet;
	public static volatile SingularAttribute<Correspondant, String> tel;
	public static volatile SingularAttribute<Correspondant, String> poste;
	public static volatile SingularAttribute<Correspondant, String> email;
	public static volatile SingularAttribute<Correspondant, String> statut;

}

