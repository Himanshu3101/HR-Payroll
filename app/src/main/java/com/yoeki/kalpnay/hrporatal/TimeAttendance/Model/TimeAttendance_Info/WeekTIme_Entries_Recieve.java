package com.yoeki.kalpnay.hrporatal.TimeAttendance.Model.TimeAttendance_Info;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IACE on 12-Oct-18.
 */

public class WeekTIme_Entries_Recieve {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("listEmpTimeSheet")
    @Expose
    private List<ListEmpTimeSheet> listEmpTimeSheet =  new ArrayList();

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

    public List<ListEmpTimeSheet> getListEmpTimeSheet() {
        return listEmpTimeSheet;
    }

//    public void setListEmpTimeSheet(List<ListEmpTimeSheet> listEmpTimeSheet) {
//        this.listEmpTimeSheet = listEmpTimeSheet;
//    }

    public class ListEmpTimeSheet {

        @SerializedName("Date")
        @Expose
        private String date;
        @SerializedName("StartTime")
        @Expose
        private String startTime;
        @SerializedName("EndTime")
        @Expose
        private String endTime;
        @SerializedName("CreatedOn")
        @Expose
        private String createdOn;
        @SerializedName("Description")
        @Expose
        private String description;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public String getCreatedOn() {
            return createdOn;
        }

        public void setCreatedOn(String createdOn) {
            this.createdOn = createdOn;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }

}


//7503045636 - YUkti