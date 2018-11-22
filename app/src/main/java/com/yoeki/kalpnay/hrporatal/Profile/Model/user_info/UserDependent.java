package com.yoeki.kalpnay.hrporatal.Profile.Model.user_info;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by IACE on 27-Sep-18.
 */
public class UserDependent implements Serializable {

@SerializedName("Name")
@Expose
private String name;
@SerializedName("Relation")
@Expose
private String relation;
@SerializedName("ContactNo")
@Expose
private String contactNo;

public String getName() {
return name;
}

public void setName(String name) {
this.name = name;
}

public String getRelation() {
return relation;
}

public void setRelation(String relation) {
this.relation = relation;
}

public String getContactNo() {
return contactNo;
}

public void setContactNo(String contactNo) {
this.contactNo = contactNo;
}

}
