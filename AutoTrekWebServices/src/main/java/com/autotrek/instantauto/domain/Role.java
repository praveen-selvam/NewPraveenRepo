package com.autotrek.instantauto.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author praveens
 */
@Entity
@Table(name = "ROLE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Role.findAll", query = "SELECT r FROM Role r")
    , @NamedQuery(name = "Role.findById", query = "SELECT r FROM Role r WHERE r.id = :id")
    , @NamedQuery(name = "Role.findByRoleName", query = "SELECT r FROM Role r WHERE r.roleName = :roleName")
    , @NamedQuery(name = "Role.findByCustomer", query = "SELECT r FROM Role r WHERE r.customer = :customer")
    , @NamedQuery(name = "Role.findByDescription", query = "SELECT r FROM Role r WHERE r.description = :description")
    , @NamedQuery(name = "Role.findByCreatedTimestamp", query = "SELECT r FROM Role r WHERE r.createdTimestamp = :createdTimestamp")
    , @NamedQuery(name = "Role.findByModifiedTimestamp", query = "SELECT r FROM Role r WHERE r.modifiedTimestamp = :modifiedTimestamp")})
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Access(AccessType.PROPERTY)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 240)
    @Column(name = "ROLE_NAME")
    private String roleName;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 240)
    @Column(name = "CUSTOMER")
    private String customer;

    @Basic(optional = false)

    @Size(min = 1, max = 150)
    @Column(name = "DESCRIPTION")
    private String description;

    @Basic(optional = false)
    @Column(name = "CREATED_TIMESTAMP", insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdTimestamp;

    @Column(name = "MODIFIED_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedTimestamp;

    @JoinColumn(name = "CREATED_BY", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private User createdBy;

    @JoinColumn(name = "MODIFIED_BY", referencedColumnName = "ID")
    @ManyToOne
    private User modifiedBy;

    public Role() {
    }

    public Role(Long id) {
        this.id = id;
    }

    public Role(Long id, String roleName, String customer, String description, Date createdTimestamp) {
        this.id = id;
        this.roleName = roleName;
        this.customer = customer;
        this.description = description;
        this.createdTimestamp = createdTimestamp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
