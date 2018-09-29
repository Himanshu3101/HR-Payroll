package com.yoeki.kalpnay.hrporatal.Login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Forgotresponce {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("Email")
    String Email;

    public  Forgotresponce(String Email ){

        this.Email=Email;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
