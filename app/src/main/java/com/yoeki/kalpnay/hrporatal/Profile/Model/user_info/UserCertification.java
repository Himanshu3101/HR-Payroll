package com.yoeki.kalpnay.hrporatal.Profile.Model.user_info;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by IACE on 27-Sep-18.
 */
public class UserCertification {

@SerializedName("NameOfCertificate")
@Expose
private String nameOfCertificate;
@SerializedName("Type")
@Expose
private String type;
@SerializedName("Provider")
@Expose
private String provider;
@SerializedName("Expired")
@Expose
private String expired;

public String getNameOfCertificate() {
return nameOfCertificate;
}

public void setNameOfCertificate(String nameOfCertificate) {
this.nameOfCertificate = nameOfCertificate;
}

public String getType() {
return type;
}

public void setType(String type) {
this.type = type;
}

public String getProvider() {
return provider;
}

public void setProvider(String provider) {
this.provider = provider;
}

public String getExpired() {
return expired;
}

public void setExpired(String expired) {
this.expired = expired;
}

}
