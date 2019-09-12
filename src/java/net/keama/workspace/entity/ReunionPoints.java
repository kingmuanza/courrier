package net.keama.workspace.entity;
// Generated Feb 4, 2017 1:33:29 PM by Hibernate Tools 4.3.1


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * ReunionPoints generated by hbm2java
 */
@Entity
@Table(name="reunion_points"
    ,catalog="workspace"
)
public class ReunionPoints  implements java.io.Serializable {


     private Integer idreunionPoints;
     private Reunion reunion;
     private String libelle;
     private Integer ordre;
     private String statut;

    public ReunionPoints() {
    }

    public ReunionPoints(Reunion reunion, String libelle, Integer ordre, String statut) {
       this.reunion = reunion;
       this.libelle = libelle;
       this.ordre = ordre;
       this.statut = statut;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="idreunion_points", unique=true, nullable=false)
    public Integer getIdreunionPoints() {
        return this.idreunionPoints;
    }
    
    public void setIdreunionPoints(Integer idreunionPoints) {
        this.idreunionPoints = idreunionPoints;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="idreunion")
    public Reunion getReunion() {
        return this.reunion;
    }
    
    public void setReunion(Reunion reunion) {
        this.reunion = reunion;
    }

    
    @Column(name="libelle", length=45)
    public String getLibelle() {
        return this.libelle;
    }
    
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    
    @Column(name="ordre")
    public Integer getOrdre() {
        return this.ordre;
    }
    
    public void setOrdre(Integer ordre) {
        this.ordre = ordre;
    }

    
    @Column(name="statut", length=45)
    public String getStatut() {
        return this.statut;
    }
    
    public void setStatut(String statut) {
        this.statut = statut;
    }




}

