package net.keama.workspace.entity;
// Generated Feb 4, 2017 1:33:29 PM by Hibernate Tools 4.3.1


import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Message generated by hbm2java
 */
@Entity
@Table(name="message"
    ,catalog="workspace"
)
public class Message  implements java.io.Serializable {


     private Integer idmessage;
     private Employe employeByIdrecepteur;
     private Employe employeByIdemetteur;
     private String message;
     private Date dateEnvoi;
     private String statut;
     private Set<MessageFichier> messageFichiers = new HashSet<MessageFichier>(0);

    public Message() {
    }

    public Message(Employe employeByIdrecepteur, Employe employeByIdemetteur, String message, Date dateEnvoi, String statut, Set<MessageFichier> messageFichiers) {
       this.employeByIdrecepteur = employeByIdrecepteur;
       this.employeByIdemetteur = employeByIdemetteur;
       this.message = message;
       this.dateEnvoi = dateEnvoi;
       this.statut = statut;
       this.messageFichiers = messageFichiers;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="idmessage", unique=true, nullable=false)
    public Integer getIdmessage() {
        return this.idmessage;
    }
    
    public void setIdmessage(Integer idmessage) {
        this.idmessage = idmessage;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="idrecepteur")
    public Employe getEmployeByIdrecepteur() {
        return this.employeByIdrecepteur;
    }
    
    public void setEmployeByIdrecepteur(Employe employeByIdrecepteur) {
        this.employeByIdrecepteur = employeByIdrecepteur;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="idemetteur")
    public Employe getEmployeByIdemetteur() {
        return this.employeByIdemetteur;
    }
    
    public void setEmployeByIdemetteur(Employe employeByIdemetteur) {
        this.employeByIdemetteur = employeByIdemetteur;
    }

    
    @Column(name="message")
    public String getMessage() {
        return this.message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="date_envoi", length=19)
    public Date getDateEnvoi() {
        return this.dateEnvoi;
    }
    
    public void setDateEnvoi(Date dateEnvoi) {
        this.dateEnvoi = dateEnvoi;
    }

    
    @Column(name="statut", length=45)
    public String getStatut() {
        return this.statut;
    }
    
    public void setStatut(String statut) {
        this.statut = statut;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="message")
    public Set<MessageFichier> getMessageFichiers() {
        return this.messageFichiers;
    }
    
    public void setMessageFichiers(Set<MessageFichier> messageFichiers) {
        this.messageFichiers = messageFichiers;
    }




}


