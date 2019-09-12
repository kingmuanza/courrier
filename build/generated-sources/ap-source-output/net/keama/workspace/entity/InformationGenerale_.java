package net.keama.workspace.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(InformationGenerale.class)
public abstract class InformationGenerale_ {

	public static volatile SingularAttribute<InformationGenerale, Integer> idinformationGenerale;
	public static volatile SingularAttribute<InformationGenerale, Employe> employe;
	public static volatile SingularAttribute<InformationGenerale, String> titre;
	public static volatile SetAttribute<InformationGenerale, InformationGeneraleEmploye> informationGeneraleEmployes;
	public static volatile SetAttribute<InformationGenerale, InformationGeneraleDocument> informationGeneraleDocuments;
	public static volatile SingularAttribute<InformationGenerale, String> contenu;
	public static volatile SingularAttribute<InformationGenerale, Date> dateEnregistrement;
	public static volatile SingularAttribute<InformationGenerale, String> statut;

}

