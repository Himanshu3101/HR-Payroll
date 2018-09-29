
package com.yoeki.kalpnay.hrporatal.Payroll;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Paystructuremodel {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("UserId")
    @Expose
    private String UserId;
    @SerializedName("EarningDetail")
    @Expose
    public List<EarningDetail> earningDetail = new ArrayList<>();
    @SerializedName("DeductionDetail")
    @Expose
    public List<DeductionDetail> deductionDetail =new ArrayList<>();
    @SerializedName("TotalAmount")
    @Expose
    public List<TotalAmount> totalAmount =new ArrayList<>();


    public Paystructuremodel(String UserId){

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

    public List<EarningDetail> getEarningDetail() {
        return earningDetail;
    }

    public void setEarningDetail(List<EarningDetail> earningDetail) {
        this.earningDetail = earningDetail;
    }

    public List<DeductionDetail> getDeductionDetail() {
        return deductionDetail;
    }

    public void setDeductionDetail(List<DeductionDetail> deductionDetail) {
        this.deductionDetail = deductionDetail;
    }

    public List<TotalAmount> getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(List<TotalAmount> totalAmount) {
        this.totalAmount = totalAmount;
    }
    public class DeductionDetail {

        @SerializedName("Name")
        @Expose
        private String name;
        @SerializedName("Amount")
        @Expose
        private String amount;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

    }

    public class EarningDetail {

        @SerializedName("Name")
        @Expose
        private String name;
        @SerializedName("Amount")
        @Expose
        private String amount;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

    }
    public class TotalAmount {

        @SerializedName("Type")
        @Expose
        private Object type;
        @SerializedName("EarningAmount")
        @Expose
        private String earningAmount;
        @SerializedName("DeductionAmount")
        @Expose
        private String deductionAmount;
        @SerializedName("GrossAmount")
        @Expose
        private String grossAmount;

        public Object getType() {
            return type;
        }

        public void setType(Object type) {
            this.type = type;
        }

        public String getEarningAmount() {
            return earningAmount;
        }

        public void setEarningAmount(String earningAmount) {
            this.earningAmount = earningAmount;
        }

        public String getDeductionAmount() {
            return deductionAmount;
        }

        public void setDeductionAmount(String deductionAmount) {
            this.deductionAmount = deductionAmount;
        }

        public String getGrossAmount() {
            return grossAmount;
        }

        public void setGrossAmount(String grossAmount) {
            this.grossAmount = grossAmount;
        }

    }
}