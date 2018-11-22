package com.yoeki.kalpnay.hrporatal.Request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Shiftchangemodel {

    @SerializedName("UserId")
    @Expose
    private String UserId;

    @SerializedName("Date")
    @Expose
    private String Date;

    @SerializedName("CurrentShiftId")
    @Expose
    private String CurrentShiftId;

    @SerializedName("ChangeShiftId")
    @Expose
    private String ChangeShiftId;

    @SerializedName("Reason")
    @Expose
    private String Reason;

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;

    public Shiftchangemodel(String UserId,String Date,String CurrentShiftId,String ChangeShiftId,String Reason){

        this.UserId=UserId;
        this.Date=Date;
        this.CurrentShiftId=CurrentShiftId;
        this.ChangeShiftId=ChangeShiftId;
        this.Reason=Reason;

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
