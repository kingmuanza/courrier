package net.keama.workspace.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(MessageFichier.class)
public abstract class MessageFichier_ {

	public static volatile SingularAttribute<MessageFichier, Integer> idmessageFichier;
	public static volatile SingularAttribute<MessageFichier, String> chemin;
	public static volatile SingularAttribute<MessageFichier, Message> message;
	public static volatile SingularAttribute<MessageFichier, String> nom;
	public static volatile SingularAttribute<MessageFichier, Date> dateEnregistrement;
	public static volatile SingularAttribute<MessageFichier, String> statut;

}

