package net.keama.workspace.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ProcedureEffective.class)
public abstract class ProcedureEffective_ {

	public static volatile SingularAttribute<ProcedureEffective, String> ref;
	public static volatile SetAttribute<ProcedureEffective, TacheProcedureEffectue> tacheProcedureEffectues;
	public static volatile SingularAttribute<ProcedureEffective, Date> dateDebut;
	public static volatile SingularAttribute<ProcedureEffective, Integer> idprocedureEffective;
	public static volatile SingularAttribute<ProcedureEffective, Procedures> procedures;
	public static volatile SingularAttribute<ProcedureEffective, Date> dateFin;
	public static volatile SingularAttribute<ProcedureEffective, String> statut;

}

