
package com.yoeki.kalpnay.hrporatal.Payroll;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

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
