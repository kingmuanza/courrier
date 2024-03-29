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
 * InformationGeneraleDocument generated by hbm2java
 */
@Entity
@Table(name="information_generale_document"
    ,catalog="workspace"
)
public class InformationGeneraleDocument  implements java.io.Serializable {


     private Integer idinformationGeneraleDocument;
     private InformationGenerale informationGenerale;
     private String nom;
     private String chemin;
     private Date dateEnregistrement;
     private String statut;

    public InformationGeneraleDocument() {
    }

    public InformationGeneraleDocument(InformationGenerale informationGenerale, String nom, String chemin, Date dateEnregistrement, String statut) {
       this.informationGenerale = informationGenerale;
       this.nom = nom;
       this.chemin = chemin;
       this.dateEnregistrement = dateEnregistrement;
       this.statut = statut;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="idinformation_generale_document", unique=true, nullable=false)
    public Integer getIdinformationGeneraleDocument() {
        return this.idinformationGeneraleDocument;
    }
    
    public void setIdinformationGeneraleDocument(Integer idinformationGeneraleDocument) {
        this.idinformationGeneraleDocument = idinformationGeneraleDocument;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="idinformation_generale")
    public InformationGenerale getInformationGenerale() {
        return this.informationGenerale;
    }
    
    public void setInformationGenerale(InformationGenerale informationGenerale) {
        this.informationGenerale = informationGenerale;
    }

    
    @Column(name="nom", length=45)
    public String getNom() {
        return this.nom;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }

    
    @Column(name="chemin", length=145)
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


