package net.keama.workspace.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ProfilUtilisateur.class)
public abstract class ProfilUtilisateur_ {

	public static volatile SetAttribute<ProfilUtilisateur, Utilisateur> utilisateurs;
	public static volatile SingularAttribute<ProfilUtilisateur, Boolean> isSuper;
	public static volatile SingularAttribute<ProfilUtilisateur, Integer> idprofilUtilisateur;
	public static volatile SingularAttribute<ProfilUtilisateur, String> nom;

}

