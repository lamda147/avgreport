package avgreport.mobitv.net.vn.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A SubActiveCount.
 */
@Entity
@Table(name = "sub_active_count")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class SubActiveCount implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sub_active_count_id")
    private String subActiveCountID;

    @Column(name = "sub_active_counts")
    private Integer subActiveCounts;

    @Column(name = "sub_active_count_user_field_1")
    private String subActiveCountUserField1;

    @Column(name = "sub_active_count_user_field_2")
    private String subActiveCountUserField2;

    @Column(name = "sub_active_count_user_field_3")
    private String subActiveCountUserField3;

    @Column(name = "sub_active_count_user_field_4")
    private String subActiveCountUserField4;

    @Column(name = "sub_active_count_user_field_5")
    private String subActiveCountUserField5;

    @Column(name = "sub_active_count_date_created")
    private Instant subActiveCountDateCreated;

    @Column(name = "sub_active_count_last_updated")
    private Instant subActiveCountLastUpdated;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @OneToOne    @JoinColumn(unique = true)
    private Addresses address;

    @OneToOne    @JoinColumn(unique = true)
    private Products product;

    @OneToOne    @JoinColumn(unique = true)
    private ReportTime reporttime;

    @OneToOne    @JoinColumn(unique = true)
    private Ouunits unit;

    @OneToOne    @JoinColumn(unique = true)
    private PromoPrograms promo;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubActiveCountID() {
        return subActiveCountID;
    }

    public SubActiveCount subActiveCountID(String subActiveCountID) {
        this.subActiveCountID = subActiveCountID;
        return this;
    }

    public void setSubActiveCountID(String subActiveCountID) {
        this.subActiveCountID = subActiveCountID;
    }

    public Integer getSubActiveCounts() {
        return subActiveCounts;
    }

    public SubActiveCount subActiveCounts(Integer subActiveCounts) {
        this.subActiveCounts = subActiveCounts;
        return this;
    }

    public void setSubActiveCounts(Integer subActiveCounts) {
        this.subActiveCounts = subActiveCounts;
    }

    public String getSubActiveCountUserField1() {
        return subActiveCountUserField1;
    }

    public SubActiveCount subActiveCountUserField1(String subActiveCountUserField1) {
        this.subActiveCountUserField1 = subActiveCountUserField1;
        return this;
    }

    public void setSubActiveCountUserField1(String subActiveCountUserField1) {
        this.subActiveCountUserField1 = subActiveCountUserField1;
    }

    public String getSubActiveCountUserField2() {
        return subActiveCountUserField2;
    }

    public SubActiveCount subActiveCountUserField2(String subActiveCountUserField2) {
        this.subActiveCountUserField2 = subActiveCountUserField2;
        return this;
    }

    public void setSubActiveCountUserField2(String subActiveCountUserField2) {
        this.subActiveCountUserField2 = subActiveCountUserField2;
    }

    public String getSubActiveCountUserField3() {
        return subActiveCountUserField3;
    }

    public SubActiveCount subActiveCountUserField3(String subActiveCountUserField3) {
        this.subActiveCountUserField3 = subActiveCountUserField3;
        return this;
    }

    public void setSubActiveCountUserField3(String subActiveCountUserField3) {
        this.subActiveCountUserField3 = subActiveCountUserField3;
    }

    public String getSubActiveCountUserField4() {
        return subActiveCountUserField4;
    }

    public SubActiveCount subActiveCountUserField4(String subActiveCountUserField4) {
        this.subActiveCountUserField4 = subActiveCountUserField4;
        return this;
    }

    public void setSubActiveCountUserField4(String subActiveCountUserField4) {
        this.subActiveCountUserField4 = subActiveCountUserField4;
    }

    public String getSubActiveCountUserField5() {
        return subActiveCountUserField5;
    }

    public SubActiveCount subActiveCountUserField5(String subActiveCountUserField5) {
        this.subActiveCountUserField5 = subActiveCountUserField5;
        return this;
    }

    public void setSubActiveCountUserField5(String subActiveCountUserField5) {
        this.subActiveCountUserField5 = subActiveCountUserField5;
    }

    public Instant getSubActiveCountDateCreated() {
        return subActiveCountDateCreated;
    }

    public SubActiveCount subActiveCountDateCreated(Instant subActiveCountDateCreated) {
        this.subActiveCountDateCreated = subActiveCountDateCreated;
        return this;
    }

    public void setSubActiveCountDateCreated(Instant subActiveCountDateCreated) {
        this.subActiveCountDateCreated = subActiveCountDateCreated;
    }

    public Instant getSubActiveCountLastUpdated() {
        return subActiveCountLastUpdated;
    }

    public SubActiveCount subActiveCountLastUpdated(Instant subActiveCountLastUpdated) {
        this.subActiveCountLastUpdated = subActiveCountLastUpdated;
        return this;
    }

    public void setSubActiveCountLastUpdated(Instant subActiveCountLastUpdated) {
        this.subActiveCountLastUpdated = subActiveCountLastUpdated;
    }

    public Boolean isIsDeleted() {
        return isDeleted;
    }

    public SubActiveCount isDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
        return this;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Addresses getAddress() {
        return address;
    }

    public SubActiveCount address(Addresses addresses) {
        this.address = addresses;
        return this;
    }

    public void setAddress(Addresses addresses) {
        this.address = addresses;
    }

    public Products getProduct() {
        return product;
    }

    public SubActiveCount product(Products products) {
        this.product = products;
        return this;
    }

    public void setProduct(Products products) {
        this.product = products;
    }

    public ReportTime getReporttime() {
        return reporttime;
    }

    public SubActiveCount reporttime(ReportTime reportTime) {
        this.reporttime = reportTime;
        return this;
    }

    public void setReporttime(ReportTime reportTime) {
        this.reporttime = reportTime;
    }

    public Ouunits getUnit() {
        return unit;
    }

    public SubActiveCount unit(Ouunits ouunits) {
        this.unit = ouunits;
        return this;
    }

    public void setUnit(Ouunits ouunits) {
        this.unit = ouunits;
    }

    public PromoPrograms getPromo() {
        return promo;
    }

    public SubActiveCount promo(PromoPrograms promoPrograms) {
        this.promo = promoPrograms;
        return this;
    }

    public void setPromo(PromoPrograms promoPrograms) {
        this.promo = promoPrograms;
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
        SubActiveCount subActiveCount = (SubActiveCount) o;
        if (subActiveCount.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), subActiveCount.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SubActiveCount{" +
            "id=" + getId() +
            ", subActiveCountID='" + getSubActiveCountID() + "'" +
            ", subActiveCounts=" + getSubActiveCounts() +
            ", subActiveCountUserField1='" + getSubActiveCountUserField1() + "'" +
            ", subActiveCountUserField2='" + getSubActiveCountUserField2() + "'" +
            ", subActiveCountUserField3='" + getSubActiveCountUserField3() + "'" +
            ", subActiveCountUserField4='" + getSubActiveCountUserField4() + "'" +
            ", subActiveCountUserField5='" + getSubActiveCountUserField5() + "'" +
            ", subActiveCountDateCreated='" + getSubActiveCountDateCreated() + "'" +
            ", subActiveCountLastUpdated='" + getSubActiveCountLastUpdated() + "'" +
            ", isDeleted='" + isIsDeleted() + "'" +
            "}";
    }
}
