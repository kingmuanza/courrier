package net.keama.workspace.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Structure.class)
public abstract class Structure_ {

	public static volatile SingularAttribute<Structure, Integer> idstructure;
	public static volatile SingularAttribute<Structure, ImageLogo> imageLogo;
	public static volatile SingularAttribute<Structure, Site> site;
	public static volatile SetAttribute<Structure, Courrier> courriers;
	public static volatile SetAttribute<Structure, Structure> structures;
	public static volatile SingularAttribute<Structure, String> nom;
	public static volatile SingularAttribute<Structure, Structure> structure;
	public static volatile SetAttribute<Structure, Poste> postes;
	public static volatile SingularAttribute<Structure, String> sigle;

}

