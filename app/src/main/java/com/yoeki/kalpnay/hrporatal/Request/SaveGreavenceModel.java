package com.yoeki.kalpnay.hrporatal.Request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SaveGreavenceModel {

    @SerializedName("UserId")
    @Expose
    private String UserId;

    @SerializedName("Date")
    @Expose
    private String Date;

    @SerializedName("ManagerName")
    @Expose
    private String ManagerName;

    @SerializedName("InvolvedEmpId")
    @Expose
    private String InvolvedEmpId;

    @SerializedName("Proceduree")
    @Expose
    private String Proceduree;

    @SerializedName("ProperSolution")
    @Expose
    private String ProperSolution;

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;

    public  SaveGreavenceModel(String UserId,String Date,String ManagerName,String InvolvedEmpId,String Proceduree,String ProperSolution){
        this.UserId=UserId;
        this.Date=Date;
        this.ManagerName=ManagerName;
        this.InvolvedEmpId=InvolvedEmpId;
        this.Proceduree=Proceduree;
        this.ProperSolution=ProperSolution;
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
