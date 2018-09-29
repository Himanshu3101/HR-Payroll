
package com.yoeki.kalpnay.hrporatal.Payroll;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

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
