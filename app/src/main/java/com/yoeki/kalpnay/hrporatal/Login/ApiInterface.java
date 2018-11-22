package com.yoeki.kalpnay.hrporatal.Login;

import com.yoeki.kalpnay.hrporatal.HomeMenu.Changepassresponce;
import com.yoeki.kalpnay.hrporatal.HomeMenu.EmployeedetailModel;
import com.yoeki.kalpnay.hrporatal.HomeMenu.SearchEmployeeModel;
import com.yoeki.kalpnay.hrporatal.HomeMenu.Searchmodel;
import com.yoeki.kalpnay.hrporatal.Notification.CircularModel;
import com.yoeki.kalpnay.hrporatal.Notification.NotificationEventModel;
import com.yoeki.kalpnay.hrporatal.Payroll.EarningDetail;
import com.yoeki.kalpnay.hrporatal.Payroll.Paystructuremodel;
import com.yoeki.kalpnay.hrporatal.Plane.Allplanmanagermodule;
import com.yoeki.kalpnay.hrporatal.Plane.Approverequestmodel;
import com.yoeki.kalpnay.hrporatal.Request.GetMasterInfo;
import com.yoeki.kalpnay.hrporatal.Request.SaveAdvanceModel;
import com.yoeki.kalpnay.hrporatal.Request.SaveGreavenceModel;
import com.yoeki.kalpnay.hrporatal.Request.SaveLeave;
import com.yoeki.kalpnay.hrporatal.Request.SaveTraningRequest;
import com.yoeki.kalpnay.hrporatal.Request.SaveclaimrequestModel;
import com.yoeki.kalpnay.hrporatal.Request.Shiftchangemodel;
import com.yoeki.kalpnay.hrporatal.TimeAttendance.Model.TimeAttendance_Info.TimeAttendance_Recieve;
import com.yoeki.kalpnay.hrporatal.TimeAttendance.Model.TimeAttendance_Info.TimeEntryData;
import com.yoeki.kalpnay.hrporatal.TimeAttendance.Model.TimeAttendance_Info.WeekTIme_Entries_Recieve;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {

    //@FormUrlEncoded // annotation used in POST type requests
    @POST("service1.svc/LoginUser")
    Call<Loginresponce>login(@Body Loginresponce user);

    @POST("service1.svc/ForgetPassword")
    Call<Forgotresponce>forgot(@Body Forgotresponce user);


    @POST("service1.svc/ChangePassword")
    Call<Changepassresponce>changepass(@Body Changepassresponce user);

    @POST("service1.svc/PayRoll")
    Call<Paystructuremodel>paystructure(@Body Paystructuremodel user);

    @POST("service1.svc/SaveLeave")
    Call<Paystructuremodel>saveleave(@Body Paystructuremodel user);

    @GET("service1.svc/PayRoll?")
    Call<List<EarningDetail>> doGetEarningList();

    @POST("service1.svc/SaveTimeSheet")
    Call<TimeAttendance_Recieve> date_Time(@Body TimeEntryData timeEntryData);

    @POST("Service1.svc/BindTimeSheet")
    Call<WeekTIme_Entries_Recieve> idUser(@Body TimeEntryData userId);

    @POST("service1.svc/SaveLeave")
    Call<SaveLeave>saveleave(@Body SaveLeave user);

    @POST("service1.svc/SaveClaimRequest")
    Call<SaveclaimrequestModel>saveClaim(@Body SaveclaimrequestModel user);

    @POST("service1.svc/GetMasterInfo")
    Call<GetMasterInfo>saveallrequestlist(@Body GetMasterInfo user);

    @POST("service1.svc/SaveTraningRequest")
    Call<SaveTraningRequest>savetrainingrequest(@Body SaveTraningRequest user);

    @POST("service1.svc/SearchEmployee")
    Call<Searchmodel>searchemploye(@Body Searchmodel user);

    @POST("service1.svc/SaveShiftChangeRequest")
    Call<Shiftchangemodel>saveshiftrequest(@Body Shiftchangemodel user);

    @POST("service1.svc/SaveAdvanceRequest")
    Call<SaveAdvanceModel>saveadvancerequest(@Body SaveAdvanceModel user);

    @POST("service1.svc/SaveGreveanceRequest")
    Call<SaveGreavenceModel>savegreavancerequest(@Body SaveGreavenceModel user);

    @POST("service1.svc/BindEmployee")
    Call<SearchEmployeeModel>searchemployee(@Body SearchEmployeeModel user);

    @POST("service1.svc/BindEmployeeProfile")
    Call<EmployeedetailModel>employeedetail(@Body EmployeedetailModel user);

    @POST("service1.svc/BindCircular")
    Call<CircularModel>notificationcircular(@Body CircularModel user);

    @POST("service1.svc/SearchCircularDateWise")
    Call<NotificationEventModel>notificationevent(@Body NotificationEventModel user);

    @POST("service1.svc/BindEmpPlan")
    Call<Allplanmanagermodule>allplanmanager(@Body Allplanmanagermodule user);

    @POST("service1.svc/ChangePlanRequestStatus")
    Call<Approverequestmodel>approverequestmodel(@Body Approverequestmodel user);
}
