package net.keama.workspace.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Message.class)
public abstract class Message_ {

	public static volatile SingularAttribute<Message, Date> dateEnvoi;
	public static volatile SingularAttribute<Message, Employe> employeByIdemetteur;
	public static volatile SetAttribute<Message, MessageFichier> messageFichiers;
	public static volatile SingularAttribute<Message, String> message;
	public static volatile SingularAttribute<Message, Integer> idmessage;
	public static volatile SingularAttribute<Message, String> statut;
	public static volatile SingularAttribute<Message, Employe> employeByIdrecepteur;

}

