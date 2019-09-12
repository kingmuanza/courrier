package net.keama.workspace.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ImageLogo.class)
public abstract class ImageLogo_ {

	public static volatile SingularAttribute<ImageLogo, String> extension;
	public static volatile SetAttribute<ImageLogo, Entreprise> entreprises;
	public static volatile SingularAttribute<ImageLogo, Integer> poidsKo;
	public static volatile SetAttribute<ImageLogo, Structure> structures;
	public static volatile SingularAttribute<ImageLogo, Integer> idimageLogo;
	public static volatile SingularAttribute<ImageLogo, String> chemin;
	public static volatile SingularAttribute<ImageLogo, String> nom;
	public static volatile SingularAttribute<ImageLogo, Date> dateEnr;
	public static volatile SetAttribute<ImageLogo, Employe> employes;

}

