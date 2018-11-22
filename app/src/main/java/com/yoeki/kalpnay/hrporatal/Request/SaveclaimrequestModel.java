package com.yoeki.kalpnay.hrporatal.Request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SaveclaimrequestModel {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("UserId")
    @Expose
    private String UserId;
    @SerializedName("Date")
    @Expose
    private String Date;
    @SerializedName("Time")
    @Expose
    private String Time;
    @SerializedName("ClaimTypeId")
    @Expose
    private String ClaimTypeId;
    @SerializedName("HotelName")
    @Expose
    private String HotelName;
    @SerializedName("HotelLocation")
    @Expose
    private String HotelLocation;
    @SerializedName("ModeOfTravelId")
    @Expose
    private String ModeOfTravelId;
    @SerializedName("DepartureFrom")
    @Expose
    private String DepartureFrom;
    @SerializedName("ArriveTo")
    @Expose
    private String ArriveTo;
    @SerializedName("Amount")
    @Expose
    private String Amount;
    @SerializedName("FileName")
    @Expose
    private String FileName;
    @SerializedName("FilePath")
    @Expose
    private String FilePath;
    @SerializedName("FileType")
    @Expose
    private String FileType;
    @SerializedName("Description")
    @Expose
    private String Description;

    public  SaveclaimrequestModel(String UserId,String Date,String Time,String ClaimTypeId,String HotelName,String HotelLocation,String ModeOfTravelId,String DepartureFrom,String ArriveTo,String Amount,String FileName,String FilePath,String FileType,String Description ){

        this.UserId=UserId;
        this.Date=Date;
        this.Time=Time;
        this.ClaimTypeId=ClaimTypeId;
        this.HotelName=HotelName;
        this.HotelLocation=HotelLocation;
        this.ModeOfTravelId=ModeOfTravelId;
        this.DepartureFrom=DepartureFrom;
        this.ArriveTo=ArriveTo;
        this.Amount=Amount;
        this.FileName=FileName;
        this.FilePath=FilePath;
        this.FileType=FileType;
        this.Description=Description;
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
