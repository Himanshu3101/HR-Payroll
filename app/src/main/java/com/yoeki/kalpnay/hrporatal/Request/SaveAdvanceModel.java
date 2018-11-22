package com.yoeki.kalpnay.hrporatal.Request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SaveAdvanceModel {

    @SerializedName("UserId")
    @Expose
    private String UserId;

    @SerializedName("Date")
    @Expose
    private String Date;

    @SerializedName("AdvanceMasterId")
    @Expose
    private String AdvanceMasterId;

    @SerializedName("FromDate")
    @Expose
    private String FromDate;

    @SerializedName("ToDate")
    @Expose
    private String ToDate;

    @SerializedName("Location")
    @Expose
    private String Location;

    @SerializedName("Description")
    @Expose
    private String Description;

    @SerializedName("Amount")
    @Expose
    private String Amount;

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;

    public SaveAdvanceModel(String UserId,String Date,String AdvanceMasterId,String FromDate,String ToDate,String Location,String Description,String Amount){
        this.UserId=UserId;
        this.Date=Date;
        this.AdvanceMasterId=AdvanceMasterId;
        this.FromDate=FromDate;
        this.ToDate=ToDate;
        this.Location=Location;
        this.Description=Description;
        this.Amount=Amount;
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
