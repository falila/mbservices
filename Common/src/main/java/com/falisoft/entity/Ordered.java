
package com.falisoft.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonManagedReference;

/**
 * {Insert class description here}
 *
 * @version $Revision: 1.0 $
 * @since Build {1.0} (11 2017)
 * @author Raphael KEITA
 *
 */
@Entity
@Table(name = "ordered")
@NamedQueries({
    @NamedQuery(name = "Ordered.findAll", query = "SELECT o FROM Ordered o")})
@JsonIdentityInfo(
  generator = ObjectIdGenerators.PropertyGenerator.class, 
  property = "orderedid")
public class Ordered implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ORDEREDID", nullable = false)
    private Long orderedid;
    @Size(max = 99)
    @Column(name = "REFERENCE", length = 99)
    private String reference;
    @Column(name = "WHENMADE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date whenmade;
    @Size(max = 45)
    @Column(name = "WAYISSUED", length = 45)
    private String wayissued;
    @Size(max = 45)
    @Column(name = "PAYMENTBY", length = 45)
    private String paymentby;
    @Column(name = "TOTALPRICE")
    private Long totalprice;
    @Size(max = 99)
    @Column(name = "FROMS", length = 99)
    private String froms;
    @Size(max = 99)
    @Column(name = "DEST", length = 99)
    private String dest;
    @Column(name = "ESTIMATE_DELIV_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date estimateDelivTime;
    @Size(max = 100)
    @Column(name = "INFODETAIL", length = 100)
    private String infodetail;
    @Size(max = 5)
    @Column(name = "ESTIMATEDISTANCE", length = 5)
    private String estimatedistance;
    @Size(max = 8)
    @Column(name = "ESTIMATETIME", length = 8)
    private String estimatetime;
    @Size(max = 15)
    @Column(name = "PHONE", length = 15)
    private String phone;
    @Size(max = 45)
    @Column(name = "OWNERNAME", length = 45)
    private String ownername;
    @OneToMany(mappedBy = "orderedid", fetch = FetchType.LAZY)
    private Collection<Delivery> deliveryCollection;
    @JoinColumn(name = "CLIENTID", referencedColumnName = "CLIENTID", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonManagedReference
    private Client clientid;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ordered", fetch = FetchType.LAZY)    
    private List<Product> productCollection;

    public Ordered() {        
    }

    public Ordered(Long orderedid) {
        this.orderedid = orderedid;
        
    }

    public Long getOrderedid() {
        return orderedid;
    }

    public void setOrderedid(Long orderedid) {
        this.orderedid = orderedid;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Date getWhenmade() {
        return whenmade;
    }

    public void setWhenmade(Date whenmade) {
        this.whenmade = whenmade;
    }

    public String getWayissued() {
        return wayissued;
    }

    public void setWayissued(String wayissued) {
        this.wayissued = wayissued;
    }

    public String getPaymentby() {
        return paymentby;
    }

    public void setPaymentby(String paymentby) {
        this.paymentby = paymentby;
    }

    public Long getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(Long totalprice) {
        this.totalprice = totalprice;
    }

    public String getFroms() {
        return froms;
    }

    public void setFroms(String froms) {
        this.froms = froms;
    }

    public String getDest() {
        return dest;
    }

    public void setDest(String dest) {
        this.dest = dest;
    }

    public Date getEstimateDelivTime() {
        return estimateDelivTime;
    }

    public void setEstimateDelivTime(Date estimateDelivTime) {
        this.estimateDelivTime = estimateDelivTime;
    }

    public String getInfodetail() {
        return infodetail;
    }

    public void setInfodetail(String infodetail) {
        this.infodetail = infodetail;
    }

    public String getEstimatedistance() {
        return estimatedistance;
    }

    public void setEstimatedistance(String estimatedistance) {
        this.estimatedistance = estimatedistance;
    }

    public String getEstimatetime() {
        return estimatetime;
    }

    public void setEstimatetime(String estimatetime) {
        this.estimatetime = estimatetime;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOwnername() {
        return ownername;
    }

    public void setOwnername(String ownername) {
        this.ownername = ownername;
    }

    public Collection<Delivery> getDeliveryCollection() {
        return deliveryCollection;
    }

    public void setDeliveryCollection(Collection<Delivery> deliveryCollection) {
        this.deliveryCollection = deliveryCollection;
    }

    public Client getClientid() {
        return clientid;
    }

    public void setClientid(Client clientid) {
        this.clientid = clientid;
    }

    public List<Product> getProductCollection() {
        return productCollection;
    }

    public void setProductCollection(List<Product> productCollection) {
        this.productCollection = productCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (orderedid != null ? orderedid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ordered)) {
            return false;
        }
        Ordered other = (Ordered) object;
        if ((this.orderedid == null && other.orderedid != null) || (this.orderedid != null && !this.orderedid.equals(other.orderedid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.falisoft.entity.Ordered[ orderedid=" + orderedid + " ]";
    }

}
