package com.yoeki.kalpnay.hrporatal.TimeAttendance.Model.TimeAttendance_Info;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by IACE on 29-Sep-18.
 */

public class TimeAttendance_Recieve {

    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("message")
    @Expose
    public String message;
}
