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
 * Suggestion generated by hbm2java
 */
@Entity
@Table(name="suggestion"
    ,catalog="workspace"
)
public class Suggestion  implements java.io.Serializable {


     private Integer idsuggestion;
     private Employe employe;
     private String titre;
     private String contenu;
     private Date dateEmission;

    public Suggestion() {
    }

    public Suggestion(Employe employe, String titre, String contenu, Date dateEmission) {
       this.employe = employe;
       this.titre = titre;
       this.contenu = contenu;
       this.dateEmission = dateEmission;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="idsuggestion", unique=true, nullable=false)
    public Integer getIdsuggestion() {
        return this.idsuggestion;
    }
    
    public void setIdsuggestion(Integer idsuggestion) {
        this.idsuggestion = idsuggestion;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="idemploye")
    public Employe getEmploye() {
        return this.employe;
    }
    
    public void setEmploye(Employe employe) {
        this.employe = employe;
    }

    
    @Column(name="titre", length=45)
    public String getTitre() {
        return this.titre;
    }
    
    public void setTitre(String titre) {
        this.titre = titre;
    }

    
    @Column(name="contenu")
    public String getContenu() {
        return this.contenu;
    }
    
    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="date_emission", length=19)
    public Date getDateEmission() {
        return this.dateEmission;
    }
    
    public void setDateEmission(Date dateEmission) {
        this.dateEmission = dateEmission;
    }




}

