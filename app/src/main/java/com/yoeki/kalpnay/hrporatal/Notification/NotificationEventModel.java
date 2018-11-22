package com.yoeki.kalpnay.hrporatal.Notification;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NotificationEventModel {

    @SerializedName("UserId")
    @Expose
    private String UserId;
    @SerializedName("flag")
    @Expose
    private String flag;

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("listSearchCircular")
    @Expose
    private List<ListSearchCircular> listSearchCircular = null;

    public NotificationEventModel(String UserId, String flag ){
        this.UserId=UserId;
        this.flag=flag;
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

    public List<ListSearchCircular> getListSearchCircular() {
        return listSearchCircular;
    }

    public void setListSearchCircular(List<ListSearchCircular> listSearchCircular) {
        this.listSearchCircular = listSearchCircular;
    }


    public class ListSearchCircular {

        @SerializedName("Name")
        @Expose
        private String name;
        @SerializedName("Date")
        @Expose
        private String date;
        @SerializedName("Description")
        @Expose
        private String description;

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

    }
}
