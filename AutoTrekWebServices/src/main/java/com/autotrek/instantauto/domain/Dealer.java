package com.autotrek.instantauto.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "DEALER")
public class Dealer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Access(AccessType.PROPERTY)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;

    @Basic(optional = false)
    @NotNull
    @Size(min = 2, max = 240)
    @Column(name = "DEALER_NAME")
    private String dealerName;

    @Basic(optional = false)
    @NotNull
    @Size(min = 2, max = 240)
    @Column(name = "CONTACT_NAME")
    private String contactName;

    @Basic(optional = false)
    @NotNull
    @Size(min = 6, max = 150)
    @Column(name = "CONTACT_EMAIL_ID")
    private String contactEmailId;

    @Basic(optional = false)
    @NotNull
    @Size(min = 6, max = 32)
    @Column(name = "CONTACT_MOBILE")
    private String contactMobile;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "DESIGNATION")
    private String designation;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "IQAMA_NO")
    private String iqamaNo;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "DEALER_IMAGE_PATH")
    private String dealerImagePath;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "ADDRESS_LINE_1")
    private String addressLine1;

    @Size(max = 100)
    @Column(name = "ADDRESS_LINE_2")
    private String addressLine2;

    @Size(max = 100)
    @Column(name = "ADDRESS_LINE_3")
    private String addressLine3;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "CITY")
    private String city;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "STATE")
    private String state;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "COUNTRY")
    private String country;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "POSTAL_CODE")
    private String postalCode;

    @Basic(optional = false)
    @Column(name = "CREATED_TIMESTAMP", insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdTimestamp;

    @Column(name = "MODIFIED_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedTimestamp;

    @JoinColumn(name = "CREATED_BY", referencedColumnName = "ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private User createdBy;

    @JoinColumn(name = "MODIFIED_BY", referencedColumnName = "ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private User modifiedBy;

    public Dealer() {
    }

    public Dealer(Long id) {
        this.id = id;
    }

    public Dealer(Long id, String dealerName, String contactName, String contactEmailId, String contactMobile, String designation, String iqamaNo, String dealerImagePath, String addressLine1, String city, String state, String country, String postalCode, Date createdTimestamp) {
        this.id = id;
        this.dealerName = dealerName;
        this.contactName = contactName;
        this.contactEmailId = contactEmailId;
        this.contactMobile = contactMobile;
        this.designation = designation;
        this.iqamaNo = iqamaNo;
        this.dealerImagePath = dealerImagePath;
        this.addressLine1 = addressLine1;
        this.city = city;
        this.state = state;
        this.country = country;
        this.postalCode = postalCode;
        this.createdTimestamp = createdTimestamp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDealerName() {
        return dealerName;
    }

    public void setDealerName(String dealerName) {
        this.dealerName = dealerName;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactEmailId() {
        return contactEmailId;
    }

    public void setContactEmailId(String contactEmailId) {
        this.contactEmailId = contactEmailId;
    }

    public String getContactMobile() {
        return contactMobile;
    }

    public void setContactMobile(String contactMobile) {
        this.contactMobile = contactMobile;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getIqamaNo() {
        return iqamaNo;
    }

    public void setIqamaNo(String iqamaNo) {
        this.iqamaNo = iqamaNo;
    }

    public String getDealerImagePath() {
        return dealerImagePath;
    }

    public void setDealerImagePath(String dealerImagePath) {
        this.dealerImagePath = dealerImagePath;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getAddressLine3() {
        return addressLine3;
    }

    public void setAddressLine3(String addressLine3) {
        this.addressLine3 = addressLine3;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public Date getCreatedTimestamp() {
        return createdTimestamp;
    }

    public void setCreatedTimestamp(Date createdTimestamp) {
        this.createdTimestamp = createdTimestamp;
    }

    public Date getModifiedTimestamp() {
        return modifiedTimestamp;
    }

    public void setModifiedTimestamp(Date modifiedTimestamp) {
        this.modifiedTimestamp = modifiedTimestamp;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public User getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(User modifiedBy) {
        this.modifiedBy = modifiedBy;
    }
}
