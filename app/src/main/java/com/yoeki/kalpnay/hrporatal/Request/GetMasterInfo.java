package com.yoeki.kalpnay.hrporatal.Request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetMasterInfo {

        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("message")
        @Expose
        private String message;

        @SerializedName("UserId")
        @Expose
        private String UserId;

    @SerializedName("ListAdvanceMaster")
    @Expose
    private List<ListAdvanceMaster_> listAdvanceMaster = null;
        @SerializedName("ListLeaveType")
        @Expose
        private List<ListLeaveType> listLeaveType = null;
        @SerializedName("ListClaimType")
        @Expose
        private List<ListClaimType> listClaimType = null;
        @SerializedName("ListModeOfTravel")
        @Expose
        private List<ListModeOfTravel> listModeOfTravel = null;
        @SerializedName("ListShiftMaster")
        @Expose
        private List<ListShiftMaster> listShiftMaster = null;

         @SerializedName("ListEmployee")
        @Expose
        private List<ListEmployee> listEmployee = null;

        @SerializedName("ListTraningServiceType")
        @Expose
        private List<ListTraningServiceType> listTraningServiceType = null;

    public List<ListAdvanceMaster_> getListAdvanceMaster() {
        return listAdvanceMaster;
    }

    public void setListAdvanceMaster(List<ListAdvanceMaster_> listAdvanceMaster) {
        this.listAdvanceMaster = listAdvanceMaster;
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
    public List<ListEmployee> getListEmployee() {
        return listEmployee;
    }

    public void setListEmployee(List<ListEmployee> listEmployee) {
        this.listEmployee = listEmployee;
    }

        public List<ListLeaveType> getListLeaveType() {
            return listLeaveType;
        }

        public void setListLeaveType(List<ListLeaveType> listLeaveType) {
            this.listLeaveType = listLeaveType;
        }

        public List<ListClaimType> getListClaimType() {
            return listClaimType;
        }
        public void setListClaimType(List<ListClaimType> listClaimType) {
            this.listClaimType = listClaimType;
        }
        public List<ListModeOfTravel> getListModeOfTravel() {
            return listModeOfTravel;
        }

        public void setListModeOfTravel(List<ListModeOfTravel> listModeOfTravel) {
            this.listModeOfTravel = listModeOfTravel;
        }
        public List<ListShiftMaster> getListShiftMaster() {
            return listShiftMaster;
        }
        public void setListShiftMaster(List<ListShiftMaster> listShiftMaster) {
            this.listShiftMaster = listShiftMaster;
        }
        public List<ListTraningServiceType> getListTraningServiceType() {
            return listTraningServiceType;
        }
        public void setListTraningServiceType(List<ListTraningServiceType> listTraningServiceType) {
            this.listTraningServiceType = listTraningServiceType;
        }

     public GetMasterInfo(String UserId){
            this.UserId=UserId;
     }

    public class ListClaimType {

        @SerializedName("ClaimTypeId")
        @Expose
        private String claimTypeId;
        @SerializedName("Name")
        @Expose
        private String name;

        public String getClaimTypeId() {
            return claimTypeId;
        }

        public void setClaimTypeId(String claimTypeId) {
            this.claimTypeId = claimTypeId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }

    public class ListLeaveType {

        @SerializedName("LeaveTypeId")
        @Expose
        private String leaveTypeId;
        @SerializedName("name")
        @Expose
        private String name;

        public String getLeaveTypeId() {
            return leaveTypeId;
        }

        public void setLeaveTypeId(String leaveTypeId) {
            this.leaveTypeId = leaveTypeId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public class ListModeOfTravel {

        @SerializedName("ModeOfTravelId")
        @Expose
        private String modeOfTravelId;
        @SerializedName("Name")
        @Expose
        private String name;

        public String getModeOfTravelId() {
            return modeOfTravelId;
        }

        public void setModeOfTravelId(String modeOfTravelId) {
            this.modeOfTravelId = modeOfTravelId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
    public class ListShiftMaster {

        @SerializedName("ShiftId")
        @Expose
        private String shiftId;
        @SerializedName("Name")
        @Expose
        private String name;

        public String getShiftId() {
            return shiftId;
        }

        public void setShiftId(String shiftId) {
            this.shiftId = shiftId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }


    public class ListTraningServiceType {

        @SerializedName("ServiceTypeId")
        @Expose
        private String serviceTypeId;
        @SerializedName("Name")
        @Expose
        private String name;

        public String getServiceTypeId() {
            return serviceTypeId;
        }

        public void setServiceTypeId(String serviceTypeId) {
            this.serviceTypeId = serviceTypeId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
           }

    public class ListEmployee {

        @SerializedName("EmpId")
        @Expose
        private String empId;
        @SerializedName("Name")
        @Expose
        private String name;

        public String getEmpId() {
            return empId;
        }

        public void setEmpId(String empId) {
            this.empId = empId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
    public class ListAdvanceMaster_ {

        @SerializedName("AdvanceMasterId")
        @Expose
        private String advanceMasterId;
        @SerializedName("Name")
        @Expose
        private String name;

        public String getAdvanceMasterId() {
            return advanceMasterId;
        }

        public void setAdvanceMasterId(String advanceMasterId) {
            this.advanceMasterId = advanceMasterId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }
        }