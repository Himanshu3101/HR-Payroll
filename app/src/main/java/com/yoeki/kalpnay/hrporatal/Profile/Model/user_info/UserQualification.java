package com.yoeki.kalpnay.hrporatal.Profile.Model.user_info;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by IACE on 27-Sep-18.
 */
public class UserQualification {

@SerializedName("Qualification")
@Expose
private String qualification;
@SerializedName("Session")
@Expose
private String session;
@SerializedName("Percentage")
@Expose
private String percentage;
@SerializedName("University")
@Expose
private String university;

public String getQualification() {
return qualification;
}

public void setQualification(String qualification) {
this.qualification = qualification;
}

public String getSession() {
return session;
}

public void setSession(String session) {
this.session = session;
}

public String getPercentage() {
return percentage;
}

public void setPercentage(String percentage) {
this.percentage = percentage;
}

public String getUniversity() {
return university;
}

public void setUniversity(String university) {
this.university = university;
}

}
