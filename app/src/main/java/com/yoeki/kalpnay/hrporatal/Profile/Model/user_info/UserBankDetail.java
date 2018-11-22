package com.yoeki.kalpnay.hrporatal.Profile.Model.user_info;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by IACE on 27-Sep-18.
 */
public class UserBankDetail implements Serializable {

@SerializedName("BankName")
@Expose
private String bankName;
@SerializedName("AccountNo")
@Expose
private String accountNo;
@SerializedName("AccountType")
@Expose
private String accountType;
@SerializedName("BranchName")
@Expose
private String branchName;
@SerializedName("IFSCCode")
@Expose
private String iFSCCode;
@SerializedName("BranchAddress")
@Expose
private String branchAddress;

public String getBankName() {
return bankName;
}

public void setBankName(String bankName) {
this.bankName = bankName;
}

public String getAccountNo() {
return accountNo;
}

public void setAccountNo(String accountNo) {
this.accountNo = accountNo;
}

public String getAccountType() {
return accountType;
}

public void setAccountType(String accountType) {
this.accountType = accountType;
}

public String getBranchName() {
return branchName;
}

public void setBranchName(String branchName) {
this.branchName = branchName;
}

public String getIFSCCode() {
return iFSCCode;
}

public void setIFSCCode(String iFSCCode) {
this.iFSCCode = iFSCCode;
}

public String getBranchAddress() {
return branchAddress;
}

public void setBranchAddress(String branchAddress) {
this.branchAddress = branchAddress;
}

}
