package com.example.rp_project_frontend.Model;

public class Doctor {
    private String fullname;
    private String docType;
    private String email;
    private String mobileno;
    private String address;
    private String specialization;
    private String description;

    public Doctor(String fullname, String docType, String email, String mobileno, String address, String specialization, String description) {
        this.fullname = fullname;
        this.docType = docType;
        this.email = email;
        this.mobileno = mobileno;
        this.address = address;
        this.specialization = specialization;
        this.description = description;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getDocType() {
        return docType;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}