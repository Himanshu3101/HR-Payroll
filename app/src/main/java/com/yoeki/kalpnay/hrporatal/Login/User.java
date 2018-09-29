package com.yoeki.kalpnay.hrporatal.Login;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("EmailId")
    String EmailId;
    @SerializedName("Password")
    String Password;

    public  User(String EmailId,String Password ){

        this.EmailId=EmailId;
        this.Password=Password;
    }

    public String getEmailId() {
        return EmailId;
    }

    public void setEmailId(String emailId) {
        EmailId = emailId;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
