package com.yoeki.kalpnay.hrporatal.Notification;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CircularModel {

    @SerializedName("UserId")
    @Expose
    private String UserId;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("listCircular")
    @Expose
    private List<ListCircular> listCircular = null;

    public CircularModel(String UserId){

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

    public List<ListCircular> getListCircular() {
        return listCircular;
    }

    public void setListCircular(List<ListCircular> listCircular) {
        this.listCircular = listCircular;
    }

    public class ListCircular {

        @SerializedName("Name")
        @Expose
        private String name;
        @SerializedName("Date")
        @Expose
        private String date;
        @SerializedName("Description")
        @Expose
        private String description;
        @SerializedName("FileName")
        @Expose
        private String fileName;
        @SerializedName("FilePath")
        @Expose
        private String filePath;
        @SerializedName("FileType")
        @Expose
        private String fileType;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getFileName() {
            return fileName;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }

        public String getFilePath() {
            return filePath;
        }

        public void setFilePath(String filePath) {
            this.filePath = filePath;
        }

        public String getFileType() {
            return fileType;
        }

        public void setFileType(String fileType) {
            this.fileType = fileType;
        }

    }
}
