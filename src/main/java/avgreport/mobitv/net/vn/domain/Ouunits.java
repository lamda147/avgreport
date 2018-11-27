package avgreport.mobitv.net.vn.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A Ouunits.
 */
@Entity
@Table(name = "ouunits")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Ouunits implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ouunitid")
    private String ouunitid;

    @Column(name = "ouunitname")
    private String ouunitname;

    @Column(name = "parentouunit")
    private String parentouunit;

    @Column(name = "grandparentouunit")
    private String grandparentouunit;

    @Column(name = "ouunituserfield_1")
    private String ouunituserfield1;

    @Column(name = "ouunituserfield_2")
    private String ouunituserfield2;

    @Column(name = "ouunituserfield_3")
    private String ouunituserfield3;

    @Column(name = "ouunit_date_created")
    private Instant ouunitDateCreated;

    @Column(name = "ouunit_last_updated")
    private Instant ouunitLastUpdated;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOuunitid() {
        return ouunitid;
    }

    public Ouunits ouunitid(String ouunitid) {
        this.ouunitid = ouunitid;
        return this;
    }

    public void setOuunitid(String ouunitid) {
        this.ouunitid = ouunitid;
    }

    public String getOuunitname() {
        return ouunitname;
    }

    public Ouunits ouunitname(String ouunitname) {
        this.ouunitname = ouunitname;
        return this;
    }

    public void setOuunitname(String ouunitname) {
        this.ouunitname = ouunitname;
    }

    public String getParentouunit() {
        return parentouunit;
    }

    public Ouunits parentouunit(String parentouunit) {
        this.parentouunit = parentouunit;
        return this;
    }

    public void setParentouunit(String parentouunit) {
        this.parentouunit = parentouunit;
    }

    public String getGrandparentouunit() {
        return grandparentouunit;
    }

    public Ouunits grandparentouunit(String grandparentouunit) {
        this.grandparentouunit = grandparentouunit;
        return this;
    }

    public void setGrandparentouunit(String grandparentouunit) {
        this.grandparentouunit = grandparentouunit;
    }

    public String getOuunituserfield1() {
        return ouunituserfield1;
    }

    public Ouunits ouunituserfield1(String ouunituserfield1) {
        this.ouunituserfield1 = ouunituserfield1;
        return this;
    }

    public void setOuunituserfield1(String ouunituserfield1) {
        this.ouunituserfield1 = ouunituserfield1;
    }

    public String getOuunituserfield2() {
        return ouunituserfield2;
    }

    public Ouunits ouunituserfield2(String ouunituserfield2) {
        this.ouunituserfield2 = ouunituserfield2;
        return this;
    }

    public void setOuunituserfield2(String ouunituserfield2) {
        this.ouunituserfield2 = ouunituserfield2;
    }

    public String getOuunituserfield3() {
        return ouunituserfield3;
    }

    public Ouunits ouunituserfield3(String ouunituserfield3) {
        this.ouunituserfield3 = ouunituserfield3;
        return this;
    }

    public void setOuunituserfield3(String ouunituserfield3) {
        this.ouunituserfield3 = ouunituserfield3;
    }

    public Instant getOuunitDateCreated() {
        return ouunitDateCreated;
    }

    public Ouunits ouunitDateCreated(Instant ouunitDateCreated) {
        this.ouunitDateCreated = ouunitDateCreated;
        return this;
    }

    public void setOuunitDateCreated(Instant ouunitDateCreated) {
        this.ouunitDateCreated = ouunitDateCreated;
    }

    public Instant getOuunitLastUpdated() {
        return ouunitLastUpdated;
    }

    public Ouunits ouunitLastUpdated(Instant ouunitLastUpdated) {
        this.ouunitLastUpdated = ouunitLastUpdated;
        return this;
    }

    public void setOuunitLastUpdated(Instant ouunitLastUpdated) {
        this.ouunitLastUpdated = ouunitLastUpdated;
    }

    public Boolean isIsDeleted() {
        return isDeleted;
    }

    public Ouunits isDeleted(Boolean isDeleted) {
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
        Ouunits ouunits = (Ouunits) o;
        if (ouunits.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), ouunits.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Ouunits{" +
            "id=" + getId() +
            ", ouunitid='" + getOuunitid() + "'" +
            ", ouunitname='" + getOuunitname() + "'" +
            ", parentouunit='" + getParentouunit() + "'" +
            ", grandparentouunit='" + getGrandparentouunit() + "'" +
            ", ouunituserfield1='" + getOuunituserfield1() + "'" +
            ", ouunituserfield2='" + getOuunituserfield2() + "'" +
            ", ouunituserfield3='" + getOuunituserfield3() + "'" +
            ", ouunitDateCreated='" + getOuunitDateCreated() + "'" +
            ", ouunitLastUpdated='" + getOuunitLastUpdated() + "'" +
            ", isDeleted='" + isIsDeleted() + "'" +
            "}";
    }
}
