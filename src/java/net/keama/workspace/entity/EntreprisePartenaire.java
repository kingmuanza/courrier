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
 * EntreprisePartenaire generated by hbm2java
 */
@Entity
@Table(name="entreprise_partenaire"
    ,catalog="workspace"
)
public class EntreprisePartenaire  implements java.io.Serializable {


     private Integer identreprisePartenaire;
     private String nom;
     private String tel;
     private String mail;
     private String adresse;
     private Set<Correspondant> correspondants = new HashSet<Correspondant>(0);

    public EntreprisePartenaire() {
    }

    public EntreprisePartenaire(String nom, String tel, String mail, String adresse, Set<Correspondant> correspondants) {
       this.nom = nom;
       this.tel = tel;
       this.mail = mail;
       this.adresse = adresse;
       this.correspondants = correspondants;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="identreprise_partenaire", unique=true, nullable=false)
    public Integer getIdentreprisePartenaire() {
        return this.identreprisePartenaire;
    }
    
    public void setIdentreprisePartenaire(Integer identreprisePartenaire) {
        this.identreprisePartenaire = identreprisePartenaire;
    }

    
    @Column(name="nom", length=45)
    public String getNom() {
        return this.nom;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }

    
    @Column(name="tel", length=45)
    public String getTel() {
        return this.tel;
    }
    
    public void setTel(String tel) {
        this.tel = tel;
    }

    
    @Column(name="mail", length=45)
    public String getMail() {
        return this.mail;
    }
    
    public void setMail(String mail) {
        this.mail = mail;
    }

    
    @Column(name="adresse", length=145)
    public String getAdresse() {
        return this.adresse;
    }
    
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="entreprisePartenaire")
    public Set<Correspondant> getCorrespondants() {
        return this.correspondants;
    }
    
    public void setCorrespondants(Set<Correspondant> correspondants) {
        this.correspondants = correspondants;
    }




}


