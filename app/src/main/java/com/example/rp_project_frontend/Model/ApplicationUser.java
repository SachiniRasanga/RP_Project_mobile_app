package com.example.rp_project_frontend.Model;

public class ApplicationUser {
    public String userId;
    public String firstName;
    public String lastName;
    public String username;
    public String email;
    public String mobileNo;
    public String password;
    public String nic;
    public String gender;
    public int age;
    public String imageRef;
    public String description;
    public boolean isActive;
    public int roleId;

    public ApplicationUser(String userId, String firstName, String lastName, String username, String email, String mobileNo, String password, String nic, String gender, int age, String imageRef, String description, boolean isActive, int roleId) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.mobileNo = mobileNo;
        this.password = password;
        this.nic = nic;
        this.gender = gender;
        this.age = age;
        this.imageRef = imageRef;
        this.description = description;
        this.isActive = isActive;
        this.roleId = roleId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getImageRef() {
        return imageRef;
    }

    public void setImageRef(String imageRef) {
        this.imageRef = imageRef;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
}