package com.yoeki.kalpnay.hrporatal.TimeAttendance.Model.TimeAttendance_Info;

import com.google.gson.annotations.SerializedName;

/**
 * Created by IACE on 29-Sep-18.
 */

public class TimeEntryData extends WeekTIme_Entries_Recieve {

    @SerializedName("Date")
    public String date;
    @SerializedName("FromTime")
    public String fromTime;
    @SerializedName("ToTime")
    public String toTime;
    @SerializedName("Description")
    public String description;
    @SerializedName("WorkingHour")
    public String workingHour;
    @SerializedName("UserId")
    public String id;

    public TimeEntryData(String date,String fromTime,String toTime,String description,String workingHour,String id) {
        this.date = date;
        this.fromTime = fromTime;
        this.toTime = toTime;
        this.description = description;
        this.workingHour = workingHour;
        this.id = id;
    }

    public TimeEntryData(String id) {
        this.id = id;
    }
}
