package avgreport.mobitv.net.vn.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A Products.
 */
@Entity
@Table(name = "products")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Products implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "prod_id")
    private String prodID;

    @Column(name = "prod_desc")
    private String prodDesc;

    @Column(name = "prod_code")
    private String prodCode;

    @Column(name = "sub_type_name")
    private String subTypeName;

    @Column(name = "prod_user_field_1")
    private String prodUserField1;

    @Column(name = "prod_user_field_2")
    private String prodUserField2;

    @Column(name = "prod_user_field_3")
    private String prodUserField3;

    @Column(name = "prod_user_field_4")
    private String prodUserField4;

    @Column(name = "prod_user_field_5")
    private String prodUserField5;

    @Column(name = "product_date_created")
    private Instant productDateCreated;

    @Column(name = "product_last_updated")
    private Instant productLastUpdated;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProdID() {
        return prodID;
    }

    public Products prodID(String prodID) {
        this.prodID = prodID;
        return this;
    }

    public void setProdID(String prodID) {
        this.prodID = prodID;
    }

    public String getProdDesc() {
        return prodDesc;
    }

    public Products prodDesc(String prodDesc) {
        this.prodDesc = prodDesc;
        return this;
    }

    public void setProdDesc(String prodDesc) {
        this.prodDesc = prodDesc;
    }

    public String getProdCode() {
        return prodCode;
    }

    public Products prodCode(String prodCode) {
        this.prodCode = prodCode;
        return this;
    }

    public void setProdCode(String prodCode) {
        this.prodCode = prodCode;
    }

    public String getSubTypeName() {
        return subTypeName;
    }

    public Products subTypeName(String subTypeName) {
        this.subTypeName = subTypeName;
        return this;
    }

    public void setSubTypeName(String subTypeName) {
        this.subTypeName = subTypeName;
    }

    public String getProdUserField1() {
        return prodUserField1;
    }

    public Products prodUserField1(String prodUserField1) {
        this.prodUserField1 = prodUserField1;
        return this;
    }

    public void setProdUserField1(String prodUserField1) {
        this.prodUserField1 = prodUserField1;
    }

    public String getProdUserField2() {
        return prodUserField2;
    }

    public Products prodUserField2(String prodUserField2) {
        this.prodUserField2 = prodUserField2;
        return this;
    }

    public void setProdUserField2(String prodUserField2) {
        this.prodUserField2 = prodUserField2;
    }

    public String getProdUserField3() {
        return prodUserField3;
    }

    public Products prodUserField3(String prodUserField3) {
        this.prodUserField3 = prodUserField3;
        return this;
    }

    public void setProdUserField3(String prodUserField3) {
        this.prodUserField3 = prodUserField3;
    }

    public String getProdUserField4() {
        return prodUserField4;
    }

    public Products prodUserField4(String prodUserField4) {
        this.prodUserField4 = prodUserField4;
        return this;
    }

    public void setProdUserField4(String prodUserField4) {
        this.prodUserField4 = prodUserField4;
    }

    public String getProdUserField5() {
        return prodUserField5;
    }

    public Products prodUserField5(String prodUserField5) {
        this.prodUserField5 = prodUserField5;
        return this;
    }

    public void setProdUserField5(String prodUserField5) {
        this.prodUserField5 = prodUserField5;
    }

    public Instant getProductDateCreated() {
        return productDateCreated;
    }

    public Products productDateCreated(Instant productDateCreated) {
        this.productDateCreated = productDateCreated;
        return this;
    }

    public void setProductDateCreated(Instant productDateCreated) {
        this.productDateCreated = productDateCreated;
    }

    public Instant getProductLastUpdated() {
        return productLastUpdated;
    }

    public Products productLastUpdated(Instant productLastUpdated) {
        this.productLastUpdated = productLastUpdated;
        return this;
    }

    public void setProductLastUpdated(Instant productLastUpdated) {
        this.productLastUpdated = productLastUpdated;
    }

    public Boolean isIsDeleted() {
        return isDeleted;
    }

    public Products isDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
        return this;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Products products = (Products) o;
        if (products.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), products.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Products{" +
            "id=" + getId() +
            ", prodID='" + getProdID() + "'" +
            ", prodDesc='" + getProdDesc() + "'" +
            ", prodCode='" + getProdCode() + "'" +
            ", subTypeName='" + getSubTypeName() + "'" +
            ", prodUserField1='" + getProdUserField1() + "'" +
            ", prodUserField2='" + getProdUserField2() + "'" +
            ", prodUserField3='" + getProdUserField3() + "'" +
            ", prodUserField4='" + getProdUserField4() + "'" +
            ", prodUserField5='" + getProdUserField5() + "'" +
            ", productDateCreated='" + getProductDateCreated() + "'" +
            ", productLastUpdated='" + getProductLastUpdated() + "'" +
            ", isDeleted='" + isIsDeleted() + "'" +
            "}";
    }
}
