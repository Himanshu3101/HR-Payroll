package com.yoeki.kalpnay.hrporatal.Request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SaveLeave {

    @SerializedName("UserId")
    @Expose
    private String UserId;
    @SerializedName("LeaveTypeId")
    @Expose
    private String LeaveTypeId;
    @SerializedName("FromDate")
    @Expose
    private String FromDate;
    @SerializedName("ToDate")
    @Expose
    private String ToDate;
    @SerializedName("Description")
    @Expose
    private String Description;
    @SerializedName("FileName")
    @Expose
    private String FileName;
    @SerializedName("FilePath")
    @Expose
    private String FilePath;
    @SerializedName("FileType")
    @Expose
    private String FileType;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;

public SaveLeave(String UserId,String LeaveTypeId,/*String FromDate,String ToDate,*/String Description,String FileName,String FilePath,String FileType  ){
    this.UserId=UserId;
    this.LeaveTypeId=LeaveTypeId;
    this.FromDate=FromDate;
    this.ToDate=ToDate;
    this.Description=Description;
    this.FileName=FileName;
    this.FilePath=FilePath;
    this.FileType=FileType;
}
    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getLeaveTypeId() {
        return LeaveTypeId;
    }

    public void setLeaveTypeId(String leaveTypeId) {
        LeaveTypeId = leaveTypeId;
    }

    public String getFromDate() {
        return FromDate;
    }

    public void setFromDate(String fromDate) {
        FromDate = fromDate;
    }

    public String getToDate() {
        return ToDate;
    }

    public void setToDate(String toDate) {
        ToDate = toDate;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getFileName() {
        return FileName;
    }

    public void setFileName(String fileName) {
        FileName = fileName;
    }

    public String getFilePath() {
        return FilePath;
    }

    public void setFilePath(String filePath) {
        FilePath = filePath;
    }

    public String getFileType() {
        return FileType;
    }

    public void setFileType(String fileType) {
        FileType = fileType;
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
