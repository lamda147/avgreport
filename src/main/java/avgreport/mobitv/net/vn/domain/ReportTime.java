package avgreport.mobitv.net.vn.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A ReportTime.
 */
@Entity
@Table(name = "report_time")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ReportTime implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "report_time_id")
    private String reportTimeID;

    @Column(name = "report_time_hour")
    private Integer reportTimeHour;

    @Column(name = "report_time_day")
    private Integer reportTimeDay;

    @Column(name = "report_time_year_week")
    private Integer reportTimeYearWeek;

    @Column(name = "report_time_month")
    private Integer reportTimeMonth;

    @Column(name = "report_time_year_quater")
    private Integer reportTimeYearQuater;

    @Column(name = "report_time_year")
    private Integer reportTimeYear;

    @Column(name = "report_time_user_field_1")
    private String reportTimeUserField1;

    @Column(name = "report_time_user_field_2")
    private String reportTimeUserField2;

    @Column(name = "report_time_user_field_3")
    private String reportTimeUserField3;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReportTimeID() {
        return reportTimeID;
    }

    public ReportTime reportTimeID(String reportTimeID) {
        this.reportTimeID = reportTimeID;
        return this;
    }

    public void setReportTimeID(String reportTimeID) {
        this.reportTimeID = reportTimeID;
    }

    public Integer getReportTimeHour() {
        return reportTimeHour;
    }

    public ReportTime reportTimeHour(Integer reportTimeHour) {
        this.reportTimeHour = reportTimeHour;
        return this;
    }

    public void setReportTimeHour(Integer reportTimeHour) {
        this.reportTimeHour = reportTimeHour;
    }

    public Integer getReportTimeDay() {
        return reportTimeDay;
    }

    public ReportTime reportTimeDay(Integer reportTimeDay) {
        this.reportTimeDay = reportTimeDay;
        return this;
    }

    public void setReportTimeDay(Integer reportTimeDay) {
        this.reportTimeDay = reportTimeDay;
    }

    public Integer getReportTimeYearWeek() {
        return reportTimeYearWeek;
    }

    public ReportTime reportTimeYearWeek(Integer reportTimeYearWeek) {
        this.reportTimeYearWeek = reportTimeYearWeek;
        return this;
    }

    public void setReportTimeYearWeek(Integer reportTimeYearWeek) {
        this.reportTimeYearWeek = reportTimeYearWeek;
    }

    public Integer getReportTimeMonth() {
        return reportTimeMonth;
    }

    public ReportTime reportTimeMonth(Integer reportTimeMonth) {
        this.reportTimeMonth = reportTimeMonth;
        return this;
    }

    public void setReportTimeMonth(Integer reportTimeMonth) {
        this.reportTimeMonth = reportTimeMonth;
    }

    public Integer getReportTimeYearQuater() {
        return reportTimeYearQuater;
    }

    public ReportTime reportTimeYearQuater(Integer reportTimeYearQuater) {
        this.reportTimeYearQuater = reportTimeYearQuater;
        return this;
    }

    public void setReportTimeYearQuater(Integer reportTimeYearQuater) {
        this.reportTimeYearQuater = reportTimeYearQuater;
    }

    public Integer getReportTimeYear() {
        return reportTimeYear;
    }

    public ReportTime reportTimeYear(Integer reportTimeYear) {
        this.reportTimeYear = reportTimeYear;
        return this;
    }

    public void setReportTimeYear(Integer reportTimeYear) {
        this.reportTimeYear = reportTimeYear;
    }

    public String getReportTimeUserField1() {
        return reportTimeUserField1;
    }

    public ReportTime reportTimeUserField1(String reportTimeUserField1) {
        this.reportTimeUserField1 = reportTimeUserField1;
        return this;
    }

    public void setReportTimeUserField1(String reportTimeUserField1) {
        this.reportTimeUserField1 = reportTimeUserField1;
    }

    public String getReportTimeUserField2() {
        return reportTimeUserField2;
    }

    public ReportTime reportTimeUserField2(String reportTimeUserField2) {
        this.reportTimeUserField2 = reportTimeUserField2;
        return this;
    }

    public void setReportTimeUserField2(String reportTimeUserField2) {
        this.reportTimeUserField2 = reportTimeUserField2;
    }

    public String getReportTimeUserField3() {
        return reportTimeUserField3;
    }

    public ReportTime reportTimeUserField3(String reportTimeUserField3) {
        this.reportTimeUserField3 = reportTimeUserField3;
        return this;
    }

    public void setReportTimeUserField3(String reportTimeUserField3) {
        this.reportTimeUserField3 = reportTimeUserField3;
    }

    public Boolean isIsDeleted() {
        return isDeleted;
    }

    public ReportTime isDeleted(Boolean isDeleted) {
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
        ReportTime reportTime = (ReportTime) o;
        if (reportTime.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), reportTime.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ReportTime{" +
            "id=" + getId() +
            ", reportTimeID='" + getReportTimeID() + "'" +
            ", reportTimeHour=" + getReportTimeHour() +
            ", reportTimeDay=" + getReportTimeDay() +
            ", reportTimeYearWeek=" + getReportTimeYearWeek() +
            ", reportTimeMonth=" + getReportTimeMonth() +
            ", reportTimeYearQuater=" + getReportTimeYearQuater() +
            ", reportTimeYear=" + getReportTimeYear() +
            ", reportTimeUserField1='" + getReportTimeUserField1() + "'" +
            ", reportTimeUserField2='" + getReportTimeUserField2() + "'" +
            ", reportTimeUserField3='" + getReportTimeUserField3() + "'" +
            ", isDeleted='" + isIsDeleted() + "'" +
            "}";
    }
}
