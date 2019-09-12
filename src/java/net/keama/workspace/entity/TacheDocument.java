package net.keama.workspace.entity;
// Generated Feb 4, 2017 1:33:29 PM by Hibernate Tools 4.3.1


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * TacheDocument generated by hbm2java
 */
@Entity
@Table(name="tache_document"
    ,catalog="workspace"
)
public class TacheDocument  implements java.io.Serializable {


     private Integer idtacheDocument;
     private Employe employe;
     private Tache tache;
     private String nom;
     private String chemin;
     private Date dateEnregistrement;
     private String statut;

    public TacheDocument() {
    }

    public TacheDocument(Employe employe, Tache tache, String nom, String chemin, Date dateEnregistrement, String statut) {
       this.employe = employe;
       this.tache = tache;
       this.nom = nom;
       this.chemin = chemin;
       this.dateEnregistrement = dateEnregistrement;
       this.statut = statut;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="idtache_document", unique=true, nullable=false)
    public Integer getIdtacheDocument() {
        return this.idtacheDocument;
    }
    
    public void setIdtacheDocument(Integer idtacheDocument) {
        this.idtacheDocument = idtacheDocument;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="idemploye")
    public Employe getEmploye() {
        return this.employe;
    }
    
    public void setEmploye(Employe employe) {
        this.employe = employe;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="idtache")
    public Tache getTache() {
        return this.tache;
    }
    
    public void setTache(Tache tache) {
        this.tache = tache;
    }

    
    @Column(name="nom", length=45)
    public String getNom() {
        return this.nom;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }

    
    @Column(name="chemin", length=45)
    public String getChemin() {
        return this.chemin;
    }
    
    public void setChemin(String chemin) {
        this.chemin = chemin;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="date_enregistrement", length=19)
    public Date getDateEnregistrement() {
        return this.dateEnregistrement;
    }
    
    public void setDateEnregistrement(Date dateEnregistrement) {
        this.dateEnregistrement = dateEnregistrement;
    }

    
    @Column(name="statut", length=45)
    public String getStatut() {
        return this.statut;
    }
    
    public void setStatut(String statut) {
        this.statut = statut;
    }




}


