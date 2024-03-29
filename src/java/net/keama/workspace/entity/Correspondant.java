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
 * Correspondant generated by hbm2java
 */
@Entity
@Table(name="correspondant"
    ,catalog="workspace"
)
public class Correspondant  implements java.io.Serializable {


     private Integer idcorrespondant;
     private EntreprisePartenaire entreprisePartenaire;
     private String nomComplet;
     private String poste;
     private String tel;
     private String email;
     private String statut;
     private String image;
     private Set<Courrier> courriers = new HashSet<Courrier>(0);

    public Correspondant() {
    }

    public Correspondant(EntreprisePartenaire entreprisePartenaire, String nomComplet, String poste, String tel, String email, String statut, String image, Set<Courrier> courriers) {
       this.entreprisePartenaire = entreprisePartenaire;
       this.nomComplet = nomComplet;
       this.poste = poste;
       this.tel = tel;
       this.email = email;
       this.statut = statut;
       this.image = image;
       this.courriers = courriers;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="idcorrespondant", unique=true, nullable=false)
    public Integer getIdcorrespondant() {
        return this.idcorrespondant;
    }
    
    public void setIdcorrespondant(Integer idcorrespondant) {
        this.idcorrespondant = idcorrespondant;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="identreprise")
    public EntreprisePartenaire getEntreprisePartenaire() {
        return this.entreprisePartenaire;
    }
    
    public void setEntreprisePartenaire(EntreprisePartenaire entreprisePartenaire) {
        this.entreprisePartenaire = entreprisePartenaire;
    }

    
    @Column(name="nom_complet", length=45)
    public String getNomComplet() {
        return this.nomComplet;
    }
    
    public void setNomComplet(String nomComplet) {
        this.nomComplet = nomComplet;
    }

    
    @Column(name="poste", length=45)
    public String getPoste() {
        return this.poste;
    }
    
    public void setPoste(String poste) {
        this.poste = poste;
    }

    
    @Column(name="tel", length=45)
    public String getTel() {
        return this.tel;
    }
    
    public void setTel(String tel) {
        this.tel = tel;
    }

    
    @Column(name="email", length=45)
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    
    @Column(name="statut", length=45)
    public String getStatut() {
        return this.statut;
    }
    
    public void setStatut(String statut) {
        this.statut = statut;
    }

    
    @Column(name="image", length=245)
    public String getImage() {
        return this.image;
    }
    
    public void setImage(String image) {
        this.image = image;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="correspondant")
    public Set<Courrier> getCourriers() {
        return this.courriers;
    }
    
    public void setCourriers(Set<Courrier> courriers) {
        this.courriers = courriers;
    }




}


