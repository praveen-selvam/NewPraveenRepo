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

/**
 * A very basic user entity.
 *
 * @author Joe C. McPherson
 */
@Entity
@Table(name = "USER")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Access(AccessType.PROPERTY)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;

    @Basic(optional = false)
    @NotNull
    @Size(min = 4, max = 128)
    @Column(name = "USERNAME")
    private String username;

    @Basic(optional = false)
    @NotNull
    @Size(min = 4, max = 128)
    @Column(name = "PASSWORD")
    private String password;

    @Basic(optional = false)
    @NotNull
    @Column(name = "PASSWORD_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date passwordTimestamp;

    @Basic(optional = false)
    @NotNull
    @Size(min = 2, max = 128)
    @Column(name = "FIRST_NAME")
    private String firstName;

    @Basic(optional = false)
    @NotNull
    @Size(min = 2, max = 128)
    @Column(name = "LAST_NAME")
    private String lastName;

    @Basic(optional = false)
    @NotNull
    @Column(name = "CREATED_TIMESTAMP")
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

    public User() {
    }

    public User(Long id) {
        this.id = id;
    }

    public User(Long id, String username, String password, Date passwordTimestamp, String firstName, String lastName, Date createdTimestamp) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.passwordTimestamp = passwordTimestamp;
        this.firstName = firstName;
        this.lastName = lastName;
        this.createdTimestamp = createdTimestamp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getPasswordTimestamp() {
        return passwordTimestamp;
    }

    public void setPasswordTimestamp(Date passwordTimestamp) {
        this.passwordTimestamp = passwordTimestamp;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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
