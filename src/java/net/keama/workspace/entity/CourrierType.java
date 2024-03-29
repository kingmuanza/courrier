package net.keama.workspace.entity;
// Generated Feb 4, 2017 1:33:29 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * CourrierType generated by hbm2java
 */
@Entity
@Table(name="courrier_type"
    ,catalog="workspace"
)
public class CourrierType  implements java.io.Serializable {


     private Integer idcourrierType;
     private String nom;
     private String commentaire;
     private String statut;
     private Set<CourrierProcedure> courrierProcedures = new HashSet<CourrierProcedure>(0);

    public CourrierType() {
    }

    public CourrierType(String nom, String commentaire, String statut, Set<CourrierProcedure> courrierProcedures) {
       this.nom = nom;
       this.commentaire = commentaire;
       this.statut = statut;
       this.courrierProcedures = courrierProcedures;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="idcourrier_type", unique=true, nullable=false)
    public Integer getIdcourrierType() {
        return this.idcourrierType;
    }
    
    public void setIdcourrierType(Integer idcourrierType) {
        this.idcourrierType = idcourrierType;
    }

    
    @Column(name="nom", length=45)
    public String getNom() {
        return this.nom;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }

    
    @Column(name="commentaire")
    public String getCommentaire() {
        return this.commentaire;
    }
    
    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    
    @Column(name="statut", length=45)
    public String getStatut() {
        return this.statut;
    }
    
    public void setStatut(String statut) {
        this.statut = statut;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="courrierType")
    public Set<CourrierProcedure> getCourrierProcedures() {
        return this.courrierProcedures;
    }
    
    public void setCourrierProcedures(Set<CourrierProcedure> courrierProcedures) {
        this.courrierProcedures = courrierProcedures;
    }




}


