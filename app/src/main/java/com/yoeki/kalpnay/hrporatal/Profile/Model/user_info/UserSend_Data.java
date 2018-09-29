package com.yoeki.kalpnay.hrporatal.Profile.Model.user_info;

import com.google.gson.annotations.SerializedName;

/**
 * Created by IACE on 27-Sep-18.
 */

public class UserSend_Data {

    @SerializedName("UserId")
    public String id;

    public UserSend_Data(String id) {
        this.id = id;
    }
}
