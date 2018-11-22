package com.yoeki.kalpnay.hrporatal.HomeMenu;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EmployeedetailModel {

    @SerializedName("EmpId")
    @Expose
    private String EmpId;

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("BasicUserInfo")
    @Expose
    private List<BasicUserInfo> basicUserInfo = null;
    @SerializedName("UserBankDetail")
    @Expose
    private List<Object> userBankDetail = null;
    @SerializedName("UserQualification")
    @Expose
    private List<UserQualification> userQualification = null;
    @SerializedName("UserCertification")
    @Expose
    private List<UserCertification> userCertification = null;
    @SerializedName("UserDependents")
    @Expose
    private List<UserDependent> userDependents = null;

    public  EmployeedetailModel(String EmpId){

        this.EmpId=EmpId;

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

    public List<BasicUserInfo> getBasicUserInfo() {
        return basicUserInfo;
    }

    public void setBasicUserInfo(List<BasicUserInfo> basicUserInfo) {
        this.basicUserInfo = basicUserInfo;
    }

    public List<Object> getUserBankDetail() {
        return userBankDetail;
    }

    public void setUserBankDetail(List<Object> userBankDetail) {
        this.userBankDetail = userBankDetail;
    }

    public List<UserQualification> getUserQualification() {
        return userQualification;
    }

    public void setUserQualification(List<UserQualification> userQualification) {
        this.userQualification = userQualification;
    }

    public List<UserCertification> getUserCertification() {
        return userCertification;
    }

    public void setUserCertification(List<UserCertification> userCertification) {
        this.userCertification = userCertification;
    }

    public List<UserDependent> getUserDependents() {
        return userDependents;
    }

    public void setUserDependents(List<UserDependent> userDependents) {
        this.userDependents = userDependents;
    }

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

    public class UserDependent {

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
    }
