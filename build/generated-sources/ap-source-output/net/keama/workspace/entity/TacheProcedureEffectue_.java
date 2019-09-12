package net.keama.workspace.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TacheProcedureEffectue.class)
public abstract class TacheProcedureEffectue_ {

	public static volatile SingularAttribute<TacheProcedureEffectue, Integer> idtacheProcedureEffectue;
	public static volatile SingularAttribute<TacheProcedureEffectue, TacheProcedure> tacheProcedure;
	public static volatile SingularAttribute<TacheProcedureEffectue, Date> dateDebut;
	public static volatile SingularAttribute<TacheProcedureEffectue, Date> dateFin;
	public static volatile SingularAttribute<TacheProcedureEffectue, ProcedureEffective> procedureEffective;
	public static volatile SingularAttribute<TacheProcedureEffectue, String> statut;

}

