/*
 * __NAME__.java
 *
 * Created on __DATE__, __TIME__
 *
 * Copyright(c) {2017} Falisoft, Inc.  All Rights Reserved.
 * This software is the proprietary information of Falisoft.
 *
 */

package com.falisoft.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 * {Insert class description here}
 *
 * @version $Revision: 1.0 $
 * @since Build {1.0} (11 2017)
 * @author Raphael KEITA
 * 
 */
@Embeddable
public class ProductPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "PRODUCT_ID", nullable = false)
    private long productId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ORDERED_ORDEREDID", nullable = false)
    private long orderedOrderedid;

    public ProductPK() {
    }

    public ProductPK(long productId, long orderedOrderedid) {
        this.productId = productId;
        this.orderedOrderedid = orderedOrderedid;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public long getOrderedOrderedid() {
        return orderedOrderedid;
    }

    public void setOrderedOrderedid(long orderedOrderedid) {
        this.orderedOrderedid = orderedOrderedid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) productId;
        hash += (int) orderedOrderedid;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductPK)) {
            return false;
        }
        ProductPK other = (ProductPK) object;
        if (this.productId != other.productId) {
            return false;
        }
        if (this.orderedOrderedid != other.orderedOrderedid) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.falisoft.entity.ProductPK[ productId=" + productId + ", orderedOrderedid=" + orderedOrderedid + " ]";
    }

}
