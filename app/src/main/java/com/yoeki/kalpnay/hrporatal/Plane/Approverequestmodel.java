package com.yoeki.kalpnay.hrporatal.Plane;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Approverequestmodel {

    @SerializedName("UserId")
    @Expose
    private String UserId;

    @SerializedName("Status")
    @Expose
    private String Status;

    @SerializedName("EmpPlanId")
    @Expose
    private String EmpPlanId;

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;

    public Approverequestmodel(String UserId,String Status,String EmpPlanId ){

        this.UserId=UserId;
        this.Status=Status;
        this.EmpPlanId=EmpPlanId;

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
