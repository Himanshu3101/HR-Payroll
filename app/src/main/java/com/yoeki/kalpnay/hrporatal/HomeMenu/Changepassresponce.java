package com.yoeki.kalpnay.hrporatal.HomeMenu;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Changepassresponce {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("UserId")
    @Expose
    private String UserId;

    @SerializedName("OldPassword")
    @Expose
    private String OldPassword;

    @SerializedName("NewPassword")
    @Expose
    private String NewPassword;

    public Changepassresponce(String UserId, String OldPassword, String NewPassword  ){
        this.UserId=UserId;
        this.OldPassword=OldPassword;
        this.NewPassword=NewPassword;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getOldPassword() {
        return OldPassword;
    }

    public void setOldPassword(String oldPassword) {
        OldPassword = oldPassword;
    }

    public String getNewPassword() {
        return NewPassword;
    }

    public void setNewPassword(String newPassword) {
        NewPassword = newPassword;
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
