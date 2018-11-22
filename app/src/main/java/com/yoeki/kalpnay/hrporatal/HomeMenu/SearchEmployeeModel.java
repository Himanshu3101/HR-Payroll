package com.yoeki.kalpnay.hrporatal.HomeMenu;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class SearchEmployeeModel {

    @SerializedName("UserId")
    @Expose
    private String UserId;
    @SerializedName("Name")
    @Expose
    private String Name;
    @SerializedName("Designation")
    @Expose
    private String Designation;
    @SerializedName("Department")
    @Expose
    private String Department;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("ListAllEmployee")
    @Expose
    private List<ListAllEmployee> listAllEmployee = null;

    public  SearchEmployeeModel(String UserId,String Name,String Designation,String Department){
        this.UserId=UserId;
        this.Name=Name;
        this.Designation=Designation;
        this.Department=Department;
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

    public List<ListAllEmployee> getListAllEmployee() {
        return listAllEmployee;
    }

    public void setListAllEmployee(List<ListAllEmployee> listAllEmployee) {
        this.listAllEmployee = listAllEmployee;
    }

    public class ListAllEmployee {

        @SerializedName("EmpId")
        @Expose
        private String empId;
        @SerializedName("Designation")
        @Expose
        private String designation;
        @SerializedName("EmailId")
        @Expose
        private String emailId;
        @SerializedName("Name")
        @Expose
        private String name;

        public String getEmpId() {
            return empId;
        }

        public void setEmpId(String empId) {
            this.empId = empId;
        }

        public String getDesignation() {
            return designation;
        }

        public void setDesignation(String designation) {
            this.designation = designation;
        }

        public String getEmailId() {
            return emailId;
        }

        public void setEmailId(String emailId) {
            this.emailId = emailId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
