package com.yoeki.kalpnay.hrporatal.Profile.Model.user_info;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by IACE on 27-Sep-18.
 */
public class User implements Serializable {

    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("message")
    @Expose
    public String message;


    @SerializedName("BasicUserInfo")
    @Expose
    public List<BasicUserInfo> basicUserInfo = null;
    @SerializedName("UserBankDetail")
    @Expose
    public List<UserBankDetail> userBankDetail = null;
    @SerializedName("UserQualification")
    @Expose
    public List<UserQualification> userQualification = null;
    @SerializedName("UserCertification")
    @Expose
    public List<UserCertification> userCertification = null;
    @SerializedName("UserDependents")
    @Expose
    public List<UserDependent> userDependents = null;


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


    public List<BasicUserInfo> getBasicUserInfo() {
        return basicUserInfo;
    }

    public void setBasicUserInfo(List<BasicUserInfo> basicUserInfo) {
        this.basicUserInfo = basicUserInfo;
    }


    public List<UserBankDetail> getUserBankDetail() {
        return userBankDetail;
    }

    public void setUserBankDetail(List<UserBankDetail> userBankDetail) {
        this.userBankDetail = userBankDetail;
    }


    public List<UserQualification> getUserQualification() {
        return userQualification;
    }

    public void setUserQualification(List<UserQualification> userQualification) {
        this.userQualification = userQualification;
    }


    public List<UserCertification> getUserCertification() {
        return userCertification;
    }

    public void setUserCertification(List<UserCertification> userCertification) {
        this.userCertification = userCertification;
    }


    public List<UserDependent> getUserDependents() {
        return userDependents;
    }

    public void setUserDependents(List<UserDependent> userDependents) {
        this.userDependents = userDependents;


    }
}
