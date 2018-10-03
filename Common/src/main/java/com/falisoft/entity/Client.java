package com.falisoft.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import org.codehaus.jackson.annotate.JsonBackReference;

/**
 * {Insert class description here}
 *
 * @version $Revision: 1.0 $
 * @since Build {1.0} (08 2018)
 * @author Raphael KEITA
 * 
 */
@Entity
@Table(name = "client")
@NamedQueries({
    @NamedQuery(name = "Client.findAll", query = "SELECT c FROM Client c")})
@JsonIdentityInfo(
  generator = ObjectIdGenerators.PropertyGenerator.class, 
  property = "clientid")
public class Client implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "CLIENTID", nullable = false)
    private Long clientid;
    @Size(max = 99)
    @Column(name = "REFERENCE", length = 99)
    private String reference;
    @Size(max = 45)
    @Column(name = "TYPE", length = 45)
    private String type;
    @Size(max = 99)
    @Column(name = "ADDRESS", length = 99)
    private String address;
    @Size(max = 45)
    @Column(name = "LOCATIONCODE", length = 45)
    private String locationcode;
    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "clientid", fetch = FetchType.LAZY)
    @JsonBackReference
    private Collection<Ordered> orderedCollection;

    public Client() {
        this.orderedCollection = new ArrayList();
    }

    public Client(Long clientid) {
        this.clientid = clientid;
         this.orderedCollection = new ArrayList();
    }

    public Client(String reference, String type, String address, String locationcode) {
        this.reference = reference;
        this.type = type;
        this.address = address;
        this.locationcode = locationcode;
    }

    public Long getClientid() {
        return clientid;
    }

    public void setClientid(Long clientid) {
        this.clientid = clientid;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLocationcode() {
        return locationcode;
    }

    public void setLocationcode(String locationcode) {
        this.locationcode = locationcode;
    }

    public Collection<Ordered> getOrderedCollection() {
        return orderedCollection;
    }

    public void setOrderedCollection(Collection<Ordered> orderedCollection) {
        this.orderedCollection = orderedCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (clientid != null ? clientid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Client)) {
            return false;
        }
        Client other = (Client) object;
        if ((this.clientid == null && other.clientid != null) || (this.clientid != null && !this.clientid.equals(other.clientid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.falisoft.entity.Client[ clientid=" + clientid + " ]";
    }

}
