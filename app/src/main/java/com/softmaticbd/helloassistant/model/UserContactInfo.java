package com.softmaticbd.helloassistant.model;

public class UserContactInfo extends User {
    private String contact_number;
    private String email;
    private String present_address;
    private String permanent_address;

    public UserContactInfo(int user_id, String user_name, String first_name, String last_name) {
        super(user_id, user_name, first_name, last_name);
    }

    public UserContactInfo(int user_id, String user_name, String first_name, String last_name, String contact_number, String email, String present_address, String permanent_address) {
        super(user_id, user_name, first_name, last_name);
        this.contact_number = contact_number;
        this.email = email;
        this.present_address = present_address;
        this.permanent_address = permanent_address;
    }

    public String getContact_number() {
        return contact_number;
    }

    public void setContact_number(String contact_number) {
        this.contact_number = contact_number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPresent_address() {
        return present_address;
    }

    public void setPresent_address(String present_address) {
        this.present_address = present_address;
    }

    public String getPermanent_address() {
        return permanent_address;
    }

    public void setPermanent_address(String permanent_address) {
        this.permanent_address = permanent_address;
    }
}
