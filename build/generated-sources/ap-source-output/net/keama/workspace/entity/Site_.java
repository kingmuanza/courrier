package net.keama.workspace.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Site.class)
public abstract class Site_ {

	public static volatile SingularAttribute<Site, Integer> idsite;
	public static volatile SingularAttribute<Site, Entreprise> entreprise;
	public static volatile SetAttribute<Site, Structure> structures;
	public static volatile SingularAttribute<Site, String> nom;
	public static volatile SingularAttribute<Site, String> sigle;
	public static volatile SingularAttribute<Site, Boolean> estSiege;

}

