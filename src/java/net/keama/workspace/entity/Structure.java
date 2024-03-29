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
import javax.persistence.UniqueConstraint;

/**
 * Structure generated by hbm2java
 */
@Entity
@Table(name="structure"
    ,catalog="workspace"
    , uniqueConstraints = {@UniqueConstraint(columnNames="nom"), @UniqueConstraint(columnNames="sigle")} 
)
public class Structure  implements java.io.Serializable {


     private Integer idstructure;
     private ImageLogo imageLogo;
     private Site site;
     private Structure structure;
     private String nom;
     private String sigle;
     private Set<Poste> postes = new HashSet<Poste>(0);
     private Set<Courrier> courriers = new HashSet<Courrier>(0);
     private Set<Structure> structures = new HashSet<Structure>(0);

    public Structure() {
    }

	
    public Structure(String nom, String sigle) {
        this.nom = nom;
        this.sigle = sigle;
    }
    public Structure(ImageLogo imageLogo, Site site, Structure structure, String nom, String sigle, Set<Poste> postes, Set<Courrier> courriers, Set<Structure> structures) {
       this.imageLogo = imageLogo;
       this.site = site;
       this.structure = structure;
       this.nom = nom;
       this.sigle = sigle;
       this.postes = postes;
       this.courriers = courriers;
       this.structures = structures;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="idstructure", unique=true, nullable=false)
    public Integer getIdstructure() {
        return this.idstructure;
    }
    
    public void setIdstructure(Integer idstructure) {
        this.idstructure = idstructure;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="idimage_logo")
    public ImageLogo getImageLogo() {
        return this.imageLogo;
    }
    
    public void setImageLogo(ImageLogo imageLogo) {
        this.imageLogo = imageLogo;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="idsite")
    public Site getSite() {
        return this.site;
    }
    
    public void setSite(Site site) {
        this.site = site;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="idstructure_parente")
    public Structure getStructure() {
        return this.structure;
    }
    
    public void setStructure(Structure structure) {
        this.structure = structure;
    }

    
    @Column(name="nom", unique=true, nullable=false, length=245)
    public String getNom() {
        return this.nom;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }

    
    @Column(name="sigle", unique=true, nullable=false, length=45)
    public String getSigle() {
        return this.sigle;
    }
    
    public void setSigle(String sigle) {
        this.sigle = sigle;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="structure")
    public Set<Poste> getPostes() {
        return this.postes;
    }
    
    public void setPostes(Set<Poste> postes) {
        this.postes = postes;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="structure")
    public Set<Courrier> getCourriers() {
        return this.courriers;
    }
    
    public void setCourriers(Set<Courrier> courriers) {
        this.courriers = courriers;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="structure")
    public Set<Structure> getStructures() {
        return this.structures;
    }
    
    public void setStructures(Set<Structure> structures) {
        this.structures = structures;
    }




}


