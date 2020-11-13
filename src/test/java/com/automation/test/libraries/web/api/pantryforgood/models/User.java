package com.automation.test.libraries.web.api.pantryforgood.models;

import java.util.ArrayList;
import java.util.Date;

public class User {
    /*
    {
        "firstName": "admin",
            "lastName": "user",
            "roles": [
        "roles/admin"
    ],
        "created": "2020-03-27T10:58:16.139Z",
            "notifications": [
        {
            "date": "2020-04-03T01:36:57.597Z",
                "message": "Customer Ngan Nguyen was created!",
                "url": "/customers/10065"
        }
    ],
        "_id": 10000,
            "displayName": "admin",
            "email": "admin@example.com",
            "provider": "local",
            "__v": 0
    }
    */

    private String firstName;
    private String lastName;
    private ArrayList<String> roles;
    private Date created;
    private ArrayList<Notification> notifications;
    private int _id;
    private String displayName;
    private String email;
    private String provider;
    private int __v;

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

    public ArrayList<String> getRoles() {
        return roles;
    }

    public void setRoles(ArrayList<String> roles) {
        this.roles = roles;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public ArrayList<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(ArrayList<Notification> notifications) {
        this.notifications = notifications;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public int get__v() {
        return __v;
    }

    public void set__v(int __v) {
        this.__v = __v;
    }

    class Notification {
        private Date date;
        private String message;
        private String url;

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
