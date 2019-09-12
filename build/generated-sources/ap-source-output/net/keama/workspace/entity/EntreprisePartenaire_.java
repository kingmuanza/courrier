package net.keama.workspace.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(EntreprisePartenaire.class)
public abstract class EntreprisePartenaire_ {

	public static volatile SingularAttribute<EntreprisePartenaire, Integer> identreprisePartenaire;
	public static volatile SetAttribute<EntreprisePartenaire, Correspondant> correspondants;
	public static volatile SingularAttribute<EntreprisePartenaire, String> mail;
	public static volatile SingularAttribute<EntreprisePartenaire, String> adresse;
	public static volatile SingularAttribute<EntreprisePartenaire, String> tel;
	public static volatile SingularAttribute<EntreprisePartenaire, String> nom;

}

