package com.yoeki.kalpnay.hrporatal.Profile.Model.user_info;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BasicUserInfo {

@SerializedName("PesonalNumber")
@Expose
private String pesonalNumber;
@SerializedName("Code")
@Expose
private String code;
@SerializedName("Designation")
@Expose
private String designation;
@SerializedName("Title")
@Expose
private String title;
@SerializedName("UserName")
@Expose
private String userName;
@SerializedName("Gender")
@Expose
private String gender;
@SerializedName("MartialStatus")
@Expose
private String martialStatus;
@SerializedName("DOB")
@Expose
private String dOB;
@SerializedName("StartingDate")
@Expose
private String startingDate;

public String getPesonalNumber() {
return pesonalNumber;
}

public void setPesonalNumber(String pesonalNumber) {
this.pesonalNumber = pesonalNumber;
}

public String getCode() {
return code;
}

public void setCode(String code) {
this.code = code;
}

public String getDesignation() {
return designation;
}

public void setDesignation(String designation) {
this.designation = designation;
}

public String getTitle() {
return title;
}

public void setTitle(String title) {
this.title = title;
}

public String getUserName() {
return userName;
}

public void setUserName(String userName) {
this.userName = userName;
}

public String getGender() {
return gender;
}

public void setGender(String gender) {
this.gender = gender;
}

public String getMartialStatus() {
return martialStatus;
}

public void setMartialStatus(String martialStatus) {
this.martialStatus = martialStatus;
}

public String getDOB() {
return dOB;
}

public void setDOB(String dOB) {
this.dOB = dOB;
}

public String getStartingDate() {
return startingDate;
}

public void setStartingDate(String startingDate) {
this.startingDate = startingDate;
}

}

