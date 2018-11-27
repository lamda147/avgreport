package avgreport.mobitv.net.vn.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A Addresses.
 */
@Entity
@Table(name = "addresses")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Addresses implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "addressid")
    private String addressid;

    @Column(name = "addressarea")
    private String addressarea;

    @Column(name = "addressdistrict")
    private String addressdistrict;

    @Column(name = "addresscity")
    private String addresscity;

    @Column(name = "addressstate")
    private String addressstate;

    @Column(name = "addressuserfield_1")
    private String addressuserfield1;

    @Column(name = "addressuserfield_2")
    private String addressuserfield2;

    @Column(name = "addressuserfield_3")
    private String addressuserfield3;

    @Column(name = "addressuserfield_4")
    private String addressuserfield4;

    @Column(name = "addressuserfield_5")
    private String addressuserfield5;

    @Column(name = "addressuserfield_6")
    private String addressuserfield6;

    @Column(name = "address_date_created")
    private Instant addressDateCreated;

    @Column(name = "address_last_updated")
    private Instant addressLastUpdated;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddressid() {
        return addressid;
    }

    public Addresses addressid(String addressid) {
        this.addressid = addressid;
        return this;
    }

    public void setAddressid(String addressid) {
        this.addressid = addressid;
    }

    public String getAddressarea() {
        return addressarea;
    }

    public Addresses addressarea(String addressarea) {
        this.addressarea = addressarea;
        return this;
    }

    public void setAddressarea(String addressarea) {
        this.addressarea = addressarea;
    }

    public String getAddressdistrict() {
        return addressdistrict;
    }

    public Addresses addressdistrict(String addressdistrict) {
        this.addressdistrict = addressdistrict;
        return this;
    }

    public void setAddressdistrict(String addressdistrict) {
        this.addressdistrict = addressdistrict;
    }

    public String getAddresscity() {
        return addresscity;
    }

    public Addresses addresscity(String addresscity) {
        this.addresscity = addresscity;
        return this;
    }

    public void setAddresscity(String addresscity) {
        this.addresscity = addresscity;
    }

    public String getAddressstate() {
        return addressstate;
    }

    public Addresses addressstate(String addressstate) {
        this.addressstate = addressstate;
        return this;
    }

    public void setAddressstate(String addressstate) {
        this.addressstate = addressstate;
    }

    public String getAddressuserfield1() {
        return addressuserfield1;
    }

    public Addresses addressuserfield1(String addressuserfield1) {
        this.addressuserfield1 = addressuserfield1;
        return this;
    }

    public void setAddressuserfield1(String addressuserfield1) {
        this.addressuserfield1 = addressuserfield1;
    }

    public String getAddressuserfield2() {
        return addressuserfield2;
    }

    public Addresses addressuserfield2(String addressuserfield2) {
        this.addressuserfield2 = addressuserfield2;
        return this;
    }

    public void setAddressuserfield2(String addressuserfield2) {
        this.addressuserfield2 = addressuserfield2;
    }

    public String getAddressuserfield3() {
        return addressuserfield3;
    }

    public Addresses addressuserfield3(String addressuserfield3) {
        this.addressuserfield3 = addressuserfield3;
        return this;
    }

    public void setAddressuserfield3(String addressuserfield3) {
        this.addressuserfield3 = addressuserfield3;
    }

    public String getAddressuserfield4() {
        return addressuserfield4;
    }

    public Addresses addressuserfield4(String addressuserfield4) {
        this.addressuserfield4 = addressuserfield4;
        return this;
    }

    public void setAddressuserfield4(String addressuserfield4) {
        this.addressuserfield4 = addressuserfield4;
    }

    public String getAddressuserfield5() {
        return addressuserfield5;
    }

    public Addresses addressuserfield5(String addressuserfield5) {
        this.addressuserfield5 = addressuserfield5;
        return this;
    }

    public void setAddressuserfield5(String addressuserfield5) {
        this.addressuserfield5 = addressuserfield5;
    }

    public String getAddressuserfield6() {
        return addressuserfield6;
    }

    public Addresses addressuserfield6(String addressuserfield6) {
        this.addressuserfield6 = addressuserfield6;
        return this;
    }

    public void setAddressuserfield6(String addressuserfield6) {
        this.addressuserfield6 = addressuserfield6;
    }

    public Instant getAddressDateCreated() {
        return addressDateCreated;
    }

    public Addresses addressDateCreated(Instant addressDateCreated) {
        this.addressDateCreated = addressDateCreated;
        return this;
    }

    public void setAddressDateCreated(Instant addressDateCreated) {
        this.addressDateCreated = addressDateCreated;
    }

    public Instant getAddressLastUpdated() {
        return addressLastUpdated;
    }

    public Addresses addressLastUpdated(Instant addressLastUpdated) {
        this.addressLastUpdated = addressLastUpdated;
        return this;
    }

    public void setAddressLastUpdated(Instant addressLastUpdated) {
        this.addressLastUpdated = addressLastUpdated;
    }

    public Boolean isIsDeleted() {
        return isDeleted;
    }

    public Addresses isDeleted(Boolean isDeleted) {
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
        Addresses addresses = (Addresses) o;
        if (addresses.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), addresses.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Addresses{" +
            "id=" + getId() +
            ", addressid='" + getAddressid() + "'" +
            ", addressarea='" + getAddressarea() + "'" +
            ", addressdistrict='" + getAddressdistrict() + "'" +
            ", addresscity='" + getAddresscity() + "'" +
            ", addressstate='" + getAddressstate() + "'" +
            ", addressuserfield1='" + getAddressuserfield1() + "'" +
            ", addressuserfield2='" + getAddressuserfield2() + "'" +
            ", addressuserfield3='" + getAddressuserfield3() + "'" +
            ", addressuserfield4='" + getAddressuserfield4() + "'" +
            ", addressuserfield5='" + getAddressuserfield5() + "'" +
            ", addressuserfield6='" + getAddressuserfield6() + "'" +
            ", addressDateCreated='" + getAddressDateCreated() + "'" +
            ", addressLastUpdated='" + getAddressLastUpdated() + "'" +
            ", isDeleted='" + isIsDeleted() + "'" +
            "}";
    }
}
