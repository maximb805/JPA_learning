package edu.jpa_study.model;


import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.StringTokenizer;

@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(generator = "ID_GENERATOR")
    protected Long id;
    protected String username;
    protected String firstname;
    protected String lastname;
    protected String email;
    protected LocalDate registrationDate;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "city", column = @Column(name = "home_city")),
            @AttributeOverride(name = "street", column = @Column(name = "home_street")),
            @AttributeOverride(name = "zipcode", column = @Column(name = "home_zipcode"))
    })
    protected Address homeAddress;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "city", column = @Column(name = "billing_city")),
            @AttributeOverride(name = "street", column = @Column(name = "billing_street")),
            @AttributeOverride(name = "zipcode", column = @Column(name = "billing_zipcode"))
    })
    protected Address billingAddress;

    protected int level;
    protected boolean active;

    public User() {
    }

    public User(String username, String firstname, String lastname, String email) {
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.registrationDate = LocalDate.now();
    }

    public User(String username, String firstname,
                String lastname, String email,
                LocalDate registrationDate,
                int level, boolean active) {
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.registrationDate = registrationDate;
        this.level = level;
        this.active = active;
    }

    public User(String username, String firstname,
                String lastname, String email,
                LocalDate registrationDate) {
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.registrationDate = registrationDate;
    }

    public User(String username, String firstname,
                String lastname, String email,
                LocalDate registrationDate,
                Address homeAddress, Address billingAddress,
                int level, boolean active) {
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.registrationDate = registrationDate;
        this.homeAddress = homeAddress;
        this.billingAddress = billingAddress;
        this.level = level;
        this.active = active;
    }

    public BigDecimal calcShippingCosts(Address fromLocation) {
        // TODO calc logic
        return null;
    }

    public String getName() {
        return firstname + ' ' + lastname;
    }

    public void setName(String name) {
        StringTokenizer st = new StringTokenizer(name);
        firstname = st.nextToken();
        lastname = st.nextToken();
    }


    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(Address homeAddress) {
        this.homeAddress = homeAddress;
    }

    public Address getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(Address billingAddress) {
        this.billingAddress = billingAddress;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", registrationDate=" + registrationDate +
                ", homeAddress=" + homeAddress +
                ", billingAddress=" + billingAddress +
                ", level=" + level +
                ", active=" + active +
                '}';
    }
}
