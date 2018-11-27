package avgreport.mobitv.net.vn.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A PromoPrograms.
 */
@Entity
@Table(name = "promo_programs")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class PromoPrograms implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "promo_id")
    private String promoID;

    @Column(name = "promoname")
    private String promoname;

    @Column(name = "promo_user_field_1")
    private String promoUserField1;

    @Column(name = "promo_user_field_2")
    private String promoUserField2;

    @Column(name = "promo_user_field_3")
    private String promoUserField3;

    @Column(name = "promo_date_created")
    private Instant promoDateCreated;

    @Column(name = "promo_last_updated")
    private Instant promoLastUpdated;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPromoID() {
        return promoID;
    }

    public PromoPrograms promoID(String promoID) {
        this.promoID = promoID;
        return this;
    }

    public void setPromoID(String promoID) {
        this.promoID = promoID;
    }

    public String getPromoname() {
        return promoname;
    }

    public PromoPrograms promoname(String promoname) {
        this.promoname = promoname;
        return this;
    }

    public void setPromoname(String promoname) {
        this.promoname = promoname;
    }

    public String getPromoUserField1() {
        return promoUserField1;
    }

    public PromoPrograms promoUserField1(String promoUserField1) {
        this.promoUserField1 = promoUserField1;
        return this;
    }

    public void setPromoUserField1(String promoUserField1) {
        this.promoUserField1 = promoUserField1;
    }

    public String getPromoUserField2() {
        return promoUserField2;
    }

    public PromoPrograms promoUserField2(String promoUserField2) {
        this.promoUserField2 = promoUserField2;
        return this;
    }

    public void setPromoUserField2(String promoUserField2) {
        this.promoUserField2 = promoUserField2;
    }

    public String getPromoUserField3() {
        return promoUserField3;
    }

    public PromoPrograms promoUserField3(String promoUserField3) {
        this.promoUserField3 = promoUserField3;
        return this;
    }

    public void setPromoUserField3(String promoUserField3) {
        this.promoUserField3 = promoUserField3;
    }

    public Instant getPromoDateCreated() {
        return promoDateCreated;
    }

    public PromoPrograms promoDateCreated(Instant promoDateCreated) {
        this.promoDateCreated = promoDateCreated;
        return this;
    }

    public void setPromoDateCreated(Instant promoDateCreated) {
        this.promoDateCreated = promoDateCreated;
    }

    public Instant getPromoLastUpdated() {
        return promoLastUpdated;
    }

    public PromoPrograms promoLastUpdated(Instant promoLastUpdated) {
        this.promoLastUpdated = promoLastUpdated;
        return this;
    }

    public void setPromoLastUpdated(Instant promoLastUpdated) {
        this.promoLastUpdated = promoLastUpdated;
    }

    public Boolean isIsDeleted() {
        return isDeleted;
    }

    public PromoPrograms isDeleted(Boolean isDeleted) {
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
        PromoPrograms promoPrograms = (PromoPrograms) o;
        if (promoPrograms.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), promoPrograms.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PromoPrograms{" +
            "id=" + getId() +
            ", promoID='" + getPromoID() + "'" +
            ", promoname='" + getPromoname() + "'" +
            ", promoUserField1='" + getPromoUserField1() + "'" +
            ", promoUserField2='" + getPromoUserField2() + "'" +
            ", promoUserField3='" + getPromoUserField3() + "'" +
            ", promoDateCreated='" + getPromoDateCreated() + "'" +
            ", promoLastUpdated='" + getPromoLastUpdated() + "'" +
            ", isDeleted='" + isIsDeleted() + "'" +
            "}";
    }
}
