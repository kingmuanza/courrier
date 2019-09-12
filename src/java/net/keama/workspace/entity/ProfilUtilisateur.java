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
import javax.persistence.UniqueConstraint;

/**
 * ProfilUtilisateur generated by hbm2java
 */
@Entity
@Table(name="profil_utilisateur"
    ,catalog="workspace"
    , uniqueConstraints = @UniqueConstraint(columnNames="nom") 
)
public class ProfilUtilisateur  implements java.io.Serializable {


     private Integer idprofilUtilisateur;
     private String nom;
     private Boolean isSuper;
     private Set<Utilisateur> utilisateurs = new HashSet<Utilisateur>(0);

    public ProfilUtilisateur() {
    }

    public ProfilUtilisateur(String nom, Boolean isSuper, Set<Utilisateur> utilisateurs) {
       this.nom = nom;
       this.isSuper = isSuper;
       this.utilisateurs = utilisateurs;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="idprofil_utilisateur", unique=true, nullable=false)
    public Integer getIdprofilUtilisateur() {
        return this.idprofilUtilisateur;
    }
    
    public void setIdprofilUtilisateur(Integer idprofilUtilisateur) {
        this.idprofilUtilisateur = idprofilUtilisateur;
    }

    
    @Column(name="nom", unique=true, length=45)
    public String getNom() {
        return this.nom;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }

    
    @Column(name="isSuper")
    public Boolean getIsSuper() {
        return this.isSuper;
    }
    
    public void setIsSuper(Boolean isSuper) {
        this.isSuper = isSuper;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="profilUtilisateur")
    public Set<Utilisateur> getUtilisateurs() {
        return this.utilisateurs;
    }
    
    public void setUtilisateurs(Set<Utilisateur> utilisateurs) {
        this.utilisateurs = utilisateurs;
    }




}


