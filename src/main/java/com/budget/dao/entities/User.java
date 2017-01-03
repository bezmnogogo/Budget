package com.budget.dao.entities;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;
import java.util.Set;

/**
 * Created by home on 14.11.16.
 */
@Entity
@Table(name = "user")
public class User implements UserDetails {

    @Id
    @GeneratedValue(generator = "user_native")
    @GenericGenerator(name = "user_native", strategy = "native")
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    private long id;
    @Column(name = "Username", nullable = false, unique = true)
    private String username;
    @Column(name = "Password", nullable = false)
    private String password;
    @Column(name = "cash", nullable = false)
    private float cash = 0;
    @Column(name = "Enabled", nullable = false)
    private boolean enabled;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private Set<Role> roles;

    @Column(name = "create_time", nullable = false)
    private Date createDate;

    @Column(name = "mounthly_limit", nullable = false)
    private float mounthlyLimit;

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public float getMounthlyLimit() {
        return mounthlyLimit;
    }

    public void setMounthlyLimit(float mounthlyLimit) {
        this.mounthlyLimit = mounthlyLimit;
    }

    @Column(name = "mail", nullable = false)
    private String mail;

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Record> records;

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "user", cascade = CascadeType.ALL)
    private Set<PlannedRecord> plannedRecords;

    public Set<Record> getRecords() {
        return records;
    }

    public void setRecords(Set<Record> records) {
        this.records = records;
    }

    public Set<PlannedRecord> getPlannedRecords() {
        return plannedRecords;
    }

    public void setPlannedRecords(Set<PlannedRecord> plannedRecords) {
        this.plannedRecords = plannedRecords;
    }

    public float getCash() {
        return cash;
    }

    public void setCash(float cash) {
        this.cash = cash;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.getRoles();
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {return true;}

    @Override
    public boolean isAccountNonLocked() {return true;}

    @Override
    public boolean isCredentialsNonExpired() {return true;}

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    public String getUsername(String username) {
        return this.username;
    }

    public String getPassword(String password) {
        return this.password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public boolean getEnabled(){
        return this.enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void addCash(float cash){
        this.cash += cash;
    }
}
