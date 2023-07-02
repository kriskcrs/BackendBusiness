package com.business.backendBusiness.entity;


import jakarta.persistence.*;


@Entity
@Table(name = "person")
public class Person {
    @Id
    @Column(name = "idperson", nullable = false)
    private Long idperson;

    @Column(name = "last_name")
    private String lastName;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "ssn_itin")
    private String ssnItin;
    @Column(name = "phone")
    private String phone;
    @Column(name = "address")
    private String address;



    public Long getIdperson() {
        return idperson;
    }

    public void setIdperson(Long idperson) {
        this.idperson = idperson;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSsnItin() {
        return ssnItin;
    }

    public void setSsnItin(String ssnItin) {
        this.ssnItin = ssnItin;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
