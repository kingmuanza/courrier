package net.keama.workspace.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Employe.class)
public abstract class Employe_ {

	public static volatile SingularAttribute<Employe, String> tel1;
	public static volatile SingularAttribute<Employe, String> tel2;
	public static volatile SingularAttribute<Employe, ImageLogo> imageLogo;
	public static volatile SetAttribute<Employe, TacheProcedure> tacheProcedures;
	public static volatile SetAttribute<Employe, InformationGenerale> informationGenerales;
	public static volatile SingularAttribute<Employe, String> matricule;
	public static volatile SetAttribute<Employe, Message> messagesForIdemetteur;
	public static volatile SingularAttribute<Employe, Boolean> sexe;
	public static volatile SetAttribute<Employe, TacheDocument> tacheDocuments;
	public static volatile SingularAttribute<Employe, String> prenoms;
	public static volatile SetAttribute<Employe, Tache> tachesForIdemploye;
	public static volatile SingularAttribute<Employe, String> email2;
	public static volatile SingularAttribute<Employe, String> email1;
	public static volatile SingularAttribute<Employe, String> noms;
	public static volatile SetAttribute<Employe, CourrierProcedureTache> courrierProcedureTaches;
	public static volatile SingularAttribute<Employe, Poste> poste;
	public static volatile SingularAttribute<Employe, String> lieunaiss;
	public static volatile SetAttribute<Employe, InformationGeneraleEmploye> informationGeneraleEmployes;
	public static volatile SetAttribute<Employe, TacheEmploye> tacheEmployes;
	public static volatile SetAttribute<Employe, TacheCommentaire> tacheCommentaires;
	public static volatile SingularAttribute<Employe, String> cni;
	public static volatile SetAttribute<Employe, Utilisateur> utilisateurs;
	public static volatile SingularAttribute<Employe, Integer> idemploye;
	public static volatile SingularAttribute<Employe, Date> dateAccession;
	public static volatile SetAttribute<Employe, ReunionEmploye> reunionEmployes;
	public static volatile SetAttribute<Employe, Tache> tachesForIdordonnateur;
	public static volatile SetAttribute<Employe, CourrierResponsable> courrierResponsables;
	public static volatile SetAttribute<Employe, Reunion> reunions;
	public static volatile SetAttribute<Employe, Suggestion> suggestions;
	public static volatile SetAttribute<Employe, Message> messagesForIdrecepteur;
	public static volatile SingularAttribute<Employe, Date> datenaiss;

}

