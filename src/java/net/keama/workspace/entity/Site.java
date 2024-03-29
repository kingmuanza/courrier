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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Site generated by hbm2java
 */
@Entity
@Table(name="site"
    ,catalog="workspace"
)
public class Site  implements java.io.Serializable {


     private Integer idsite;
     private Entreprise entreprise;
     private String nom;
     private String sigle;
     private Boolean estSiege;
     private Set<Structure> structures = new HashSet<Structure>(0);

    public Site() {
    }

    public Site(Entreprise entreprise, String nom, String sigle, Boolean estSiege, Set<Structure> structures) {
       this.entreprise = entreprise;
       this.nom = nom;
       this.sigle = sigle;
       this.estSiege = estSiege;
       this.structures = structures;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="idsite", unique=true, nullable=false)
    public Integer getIdsite() {
        return this.idsite;
    }
    
    public void setIdsite(Integer idsite) {
        this.idsite = idsite;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="identreprise")
    public Entreprise getEntreprise() {
        return this.entreprise;
    }
    
    public void setEntreprise(Entreprise entreprise) {
        this.entreprise = entreprise;
    }

    
    @Column(name="nom", length=45)
    public String getNom() {
        return this.nom;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }

    
    @Column(name="sigle", length=45)
    public String getSigle() {
        return this.sigle;
    }
    
    public void setSigle(String sigle) {
        this.sigle = sigle;
    }

    
    @Column(name="est_siege")
    public Boolean getEstSiege() {
        return this.estSiege;
    }
    
    public void setEstSiege(Boolean estSiege) {
        this.estSiege = estSiege;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="site")
    public Set<Structure> getStructures() {
        return this.structures;
    }
    
    public void setStructures(Set<Structure> structures) {
        this.structures = structures;
    }




}


