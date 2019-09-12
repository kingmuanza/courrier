package net.keama.workspace.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(InformationGeneraleDocument.class)
public abstract class InformationGeneraleDocument_ {

	public static volatile SingularAttribute<InformationGeneraleDocument, Integer> idinformationGeneraleDocument;
	public static volatile SingularAttribute<InformationGeneraleDocument, String> chemin;
	public static volatile SingularAttribute<InformationGeneraleDocument, String> nom;
	public static volatile SingularAttribute<InformationGeneraleDocument, InformationGenerale> informationGenerale;
	public static volatile SingularAttribute<InformationGeneraleDocument, Date> dateEnregistrement;
	public static volatile SingularAttribute<InformationGeneraleDocument, String> statut;

}

