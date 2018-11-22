package com.yoeki.kalpnay.hrporatal.Request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SaveTraningRequest {

    @SerializedName("UserId")
    @Expose
    private String UserId;
    @SerializedName("Date")
    @Expose
    private String Date;
    @SerializedName("Title")
    @Expose
    private String Title;
    @SerializedName("ServiceTypeId")
    @Expose
    private String ServiceTypeId;
    @SerializedName("ServiceProvider")
    @Expose
    private String ServiceProvider;

    @SerializedName("TotalDays")
    @Expose
    private String TotalDays;

    @SerializedName("Cost")
    @Expose
    private String Cost;

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;

    public SaveTraningRequest(String UserId,String Date,String Title,String ServiceTypeId,String ServiceProvider,String TotalDays,String Cost ){
        this.UserId=UserId;
        this.Date=Date;
        this.Title=Title;
        this.ServiceTypeId=ServiceTypeId;
        this.ServiceProvider=ServiceProvider;
        this.TotalDays=TotalDays;
        this.Cost=Cost;
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
