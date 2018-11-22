package com.yoeki.kalpnay.hrporatal.Plane;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Allplanmanagermodule {

    @SerializedName("UserId")
    @Expose
    private String UserId;

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("listEmpPlan")
    @Expose
    private List<ListEmpPlan> listEmpPlan = null;

    public  Allplanmanagermodule(String UserId){

        this.UserId=UserId;

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

    public List<ListEmpPlan> getListEmpPlan() {
        return listEmpPlan;
    }

    public void setListEmpPlan(List<ListEmpPlan> listEmpPlan) {
        this.listEmpPlan = listEmpPlan;
    }

    public class ListEmpPlan {

        @SerializedName("EmpName")
        @Expose
        private String empName;
        @SerializedName("EmpPlanId")
        @Expose
        private String empPlanId;
        @SerializedName("Wheree")
        @Expose
        private String wheree;
        @SerializedName("Location")
        @Expose
        private String location;
        @SerializedName("CreatedOn")
        @Expose
        private String createdOn;
        @SerializedName("Purpose")
        @Expose
        private String purpose;
        @SerializedName("Status")
        @Expose
        private String status;

        public String getEmpName() {
            return empName;
        }

        public void setEmpName(String empName) {
            this.empName = empName;
        }

        public String getEmpPlanId() {
            return empPlanId;
        }

        public void setEmpPlanId(String empPlanId) {
            this.empPlanId = empPlanId;
        }

        public String getWheree() {
            return wheree;
        }

        public void setWheree(String wheree) {
            this.wheree = wheree;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getCreatedOn() {
            return createdOn;
        }

        public void setCreatedOn(String createdOn) {
            this.createdOn = createdOn;
        }

        public String getPurpose() {
            return purpose;
        }

        public void setPurpose(String purpose) {
            this.purpose = purpose;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

    }
}
