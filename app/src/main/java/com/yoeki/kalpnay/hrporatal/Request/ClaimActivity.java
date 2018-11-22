package com.yoeki.kalpnay.hrporatal.Request;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.yoeki.kalpnay.hrporatal.HomeMenu.Menuitemmodel;
import com.yoeki.kalpnay.hrporatal.Login.Api;
import com.yoeki.kalpnay.hrporatal.Login.ApiInterface;
import com.yoeki.kalpnay.hrporatal.R;
import com.yoeki.kalpnay.hrporatal.setting.Edittextclass;
import com.yoeki.kalpnay.hrporatal.setting.Textclass;
import com.yoeki.kalpnay.hrporatal.setting.preferance;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClaimActivity extends AppCompatActivity implements View.OnClickListener{

    private LinearLayout ly_claimtypehotelview,ly_claimtypeother,ly_claimtypetravelview,ly_leavereqattachment,ly_attachmentclaim;
    private TextView tv_claimreqfromdate,tv_claimreqtime,tv_claimreqtype,tv_claimtypetravelmode,tv_claimreqattachment;
    private Edittextclass edt_claimreqdescription,tv_claimtypetravelarivalto,tv_claimtypetraveldeparturefrom,tv_claimtypehotelname,tv_claimtypehotellocation;
    private int mYear, mMonth, mDay,mHour,mMinute;
    String str="temp",filepath;
    AppCompatButton img_backrequestclaim;
    ArrayList<Menuitemmodel> arrayreqattachlist;
    RecyclerView rec_leavereqattachment;
    Textclass edt_claimreqamount,tv_leavereqdescription;
    Button tv_leavereqsubmit;
    ApiInterface apiInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_claim);
        initialize();

        arrayreqattachlist = new ArrayList<>();
        tv_claimreqfromdate.setOnClickListener(this);
        tv_claimreqtime.setOnClickListener(this);
        tv_claimreqtype.setOnClickListener(this);
        tv_claimtypetravelmode.setOnClickListener(this);
        tv_claimreqattachment.setOnClickListener(this);
        img_backrequestclaim.setOnClickListener(this);
        tv_leavereqsubmit.setOnClickListener(this);


    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.tv_claimreqfromdate:
                todate();
                break;

            case R.id.tv_claimreqtime:
                timepickerdialog();
                break;

            case R.id.tv_claimreqtype:
                claimtypedialog();
                break;

            case R.id.tv_claimtypetravelmode:
                modeoftravel();
                break;

            case  R.id.tv_claimreqattachment:
                uploadattachmentdialog();
                break;

            case R.id.img_backrequestclaim:
                finish();
                break;

            case  R.id.tv_leavereqsubmit:

                String  user_id=null;
                user_id = preferance.getInstance(getApplicationContext()).getUserId();

                String strtv_claimreqfromdate=tv_claimreqfromdate.getText().toString();
                String strtv_claimreqtime=tv_claimreqtime.getText().toString();
                String strtv_claimreqtype=tv_claimreqtype.getText().toString();
                String strtv_claimtypehotelname=tv_claimtypehotelname.getText().toString();
                String strtv_claimtypehotellocation=tv_claimtypehotellocation.getText().toString();
                String strtv_claimtypetravelmode=tv_claimtypetravelmode.getText().toString();
                String strtv_claimtypetraveldeparturefrom=tv_claimtypetraveldeparturefrom.getText().toString();
                String strtv_claimtypetravelarivalto=tv_claimtypetravelarivalto.getText().toString();
                String stredt_claimreqamount=edt_claimreqamount.getText().toString();
                String stredt_claimreqdescription=edt_claimreqdescription.getText().toString();

                if (strtv_claimreqtype.equals("Hotel")){

                    strtv_claimreqtype="1";

                }else if (strtv_claimreqtype.equals("Travel")){

                    strtv_claimreqtype="2";

                }else if (strtv_claimreqtype.equals("3")){
                    strtv_claimreqtype="3";
                }

                if (strtv_claimtypetravelmode.equals("Bus")){
                    strtv_claimtypetravelmode="1";
                }else if (strtv_claimtypetravelmode.equals("Train")){
                    strtv_claimtypetravelmode="2";
                }else if (strtv_claimtypetravelmode.equals("Personal vehical")){
                    strtv_claimtypetravelmode="3";
                }
                        saveClaim(user_id,strtv_claimreqfromdate,strtv_claimreqtime,strtv_claimreqtype,strtv_claimtypehotelname,
                                strtv_claimtypehotellocation,strtv_claimtypetravelmode,strtv_claimtypetraveldeparturefrom,
                                strtv_claimtypetravelarivalto,stredt_claimreqamount,"image",filepath,"png",stredt_claimreqdescription);
                break;
        }
    }

    public void claimtypedialog(){

        final RadioGroup radioGroup_leavetype;
        TextView tv_leavereqsubmit;
        final Dialog dialog = new Dialog(ClaimActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.claimtypedialog);

        radioGroup_leavetype=dialog.findViewById(R.id.radioGroup_leavetype);

        radioGroup_leavetype.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                Button btn=radioGroup_leavetype.findViewById(i);

                str=btn.getText().toString();
                tv_claimreqtype.setText(btn.getText().toString());

            }
        });
        tv_leavereqsubmit=dialog.findViewById(R.id.tv_claimtaypesubmit);
        tv_leavereqsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                if (str.equals("temp")){
                    Toast.makeText(ClaimActivity.this, "Please select claim type", Toast.LENGTH_SHORT).show();

                } else{
                    if (str.equals("Hotel")){
                        ly_claimtypehotelview.setVisibility(View.VISIBLE);
                       // ly_claimtypeother.setVisibility(View.GONE);
                        ly_claimtypetravelview.setVisibility(View.GONE);
                    }else if (str.equals("Travel")){
                        ly_claimtypehotelview.setVisibility(View.GONE);
                      //  ly_claimtypeother.setVisibility(View.GONE);
                        ly_claimtypetravelview.setVisibility(View.VISIBLE);
                    }else if (str.equals("Others")){
                        ly_claimtypehotelview.setVisibility(View.GONE);
                     //   ly_claimtypeother.setVisibility(View.VISIBLE);
                        ly_claimtypetravelview.setVisibility(View.GONE);
                    }
                    dialog.dismiss();
                }
            }
        });

        dialog.show();
    }

    public void  modeoftravel(){
        final RadioGroup radioGroup_leavetype;
        TextView tv_leavereqsubmit;
        final Dialog dialog = new Dialog(ClaimActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.travelmodedialog);
        radioGroup_leavetype=dialog.findViewById(R.id.radioGroup_leavetype);

        radioGroup_leavetype.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                Button btn=radioGroup_leavetype.findViewById(i);
                str=btn.getText().toString();
                tv_claimtypetravelmode.setText(btn.getText().toString());

            }
        });

        tv_leavereqsubmit=dialog.findViewById(R.id.tv_travelmodesubmit);
        tv_leavereqsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (str.equals("temp")){
                    Toast.makeText(ClaimActivity.this, "Please select mode of travel ", Toast.LENGTH_SHORT).show();
                }else {
                    dialog.dismiss();
                }
            }
        });

        dialog.show();
    }

    public void initialize(){
        ly_claimtypehotelview=findViewById(R.id.ly_claimtypehotelview);
        ly_claimtypeother=findViewById(R.id.ly_claimtypeother);
        ly_claimtypetravelview=findViewById(R.id.ly_claimtypetravelview);
        ly_leavereqattachment=findViewById(R.id.ly_leavereqattachment);

        tv_claimreqfromdate=findViewById(R.id.tv_claimreqfromdate);
        tv_claimreqtime=findViewById(R.id.tv_claimreqtime);
        tv_claimreqtype=findViewById(R.id.tv_claimreqtype);
        tv_claimtypetravelmode=findViewById(R.id.tv_claimtypetravelmode);
        tv_claimreqattachment=findViewById(R.id.tv_claimreqattachment);

        tv_claimtypehotelname=findViewById(R.id.tv_claimtypehotelname);
        tv_claimtypehotellocation=findViewById(R.id.tv_claimtypehotellocation);
        edt_claimreqdescription=findViewById(R.id.edt_claimreqdescription);
        tv_claimtypetraveldeparturefrom=findViewById(R.id.tv_claimtypetraveldeparturefrom);
        tv_claimtypetravelarivalto=findViewById(R.id.tv_claimtypetravelarivalto);
        rec_leavereqattachment=findViewById(R.id.rec_leavereqattachment);
        img_backrequestclaim=findViewById(R.id.img_backrequestclaim);
        ly_attachmentclaim=findViewById(R.id.ly_attachmentclaim);

        edt_claimreqamount=findViewById(R.id.edt_claimreqamount);
        edt_claimreqdescription=findViewById(R.id.edt_claimreqdescription);
        tv_leavereqsubmit=findViewById(R.id.tv_leavereqsubmit);

    }

    public void timepickerdialog(){
        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);
        // Launch Time Picker Dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {

                        tv_claimreqtime.setText(hourOfDay + ":" + minute);
                    }
                }, mHour, mMinute, false);
        timePickerDialog.show();

      }

    public  void todate(){
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        Calendar cc = Calendar.getInstance();
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        final String getCurrentDateTime = sdf.format(cc.getTime());

        DatePickerDialog datePickerDialog = new DatePickerDialog(ClaimActivity.this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        tv_claimreqfromdate.setText(year + "/" + (monthOfYear + 1) +"/"+dayOfMonth );
                        String strtodate=year + "/" + (monthOfYear + 1) +"/"+dayOfMonth;

                        Date date2 = null;
                        Date date1=null;
                        try {
                            date1 = sdf.parse(getCurrentDateTime);
                            date2 = sdf.parse(strtodate);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        if (date1.compareTo(date2) < 0)
                        {
                            tv_claimreqfromdate.setText(strtodate );
                            // Log.d("Return","getMyTime smaller than getCurrentDateTime ");
                        }
                        else {
                            tv_claimreqfromdate.setText(getCurrentDateTime );
                            // tv_leavereqtodate.setError("select correct date");
                        }
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    public void uploadattachmentdialog(){
        final CharSequence[] options = {"Choose from Gallery", "Cancel"};

        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(ClaimActivity.this);
        builder.setTitle("Upload file");
        builder.setItems(options, new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int item)
            {
                if (options[item].equals("Choose from Gallery"))
                {
                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    (ClaimActivity.this).startActivityForResult(intent, 2);
                }
                else if (options[item].equals("Cancel"))
                {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            final InputStream imageStream;
            try {
                imageStream = ClaimActivity.this.getContentResolver().openInputStream(selectedImage);
                Bitmap Image = BitmapFactory.decodeStream(imageStream);

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                Image.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                byte[] byteArrayImage = baos.toByteArray();

                filepath = Base64.encodeToString(byteArrayImage, Base64.DEFAULT);

                Menuitemmodel dataitem=new Menuitemmodel(Image);
                arrayreqattachlist.add(dataitem);

                if (arrayreqattachlist.size()>0){

                    ly_attachmentclaim.setVisibility(View.GONE);
                    ly_leavereqattachment.setVisibility(View.VISIBLE);

                    rec_leavereqattachment.setHasFixedSize(true);

                    LinearLayoutManager linearlayoutmanager = new LinearLayoutManager(this);

                    rec_leavereqattachment.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
                    // rec_leavereqattachment.setLayoutManager(new Hori);
                    rec_leavereqattachment.setItemAnimator(new DefaultItemAnimator());

                    Attachmentview_adapter adapter=new Attachmentview_adapter(ClaimActivity.this,arrayreqattachlist);
                    rec_leavereqattachment.setAdapter(adapter);

                }else{
                    ly_attachmentclaim.setVisibility(View.VISIBLE);
                    ly_leavereqattachment.setVisibility(View.GONE);

                }
                //Image = getResizedBitmap(Image, 400);
                /*ByteArrayOutputStream baoss = new ByteArrayOutputStream();
                Image.compress(Bitmap.CompressFormat.PNG, 100, baoss); //bm is the bitmap object
                byte[] bb = baoss.toByteArray();
                String final_path = Base64.encodeToString(bb, Base64.DEFAULT);*/
                // Get the cursor
                Cursor cursor = ClaimActivity.this.getContentResolver().query(selectedImage,filePathColumn, null, null, null);
                // Move to first row
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                String imgDecodableString = cursor.getString(columnIndex);
                cursor.close();
                // stu_image.setImageBitmap(Image);
            } catch (FileNotFoundException e){
                e.printStackTrace();
            }
        }
    }

    public void saveClaim(String UserId, String Datee, String Timee, String ClaimTypeId, String HotelName, String HotelLocation, String ModeOfTravelId, String DepartureFrom,
                          String ArriveTo, String Amount, String FileName, String FilePath, String FileType, String Description){

        final ProgressDialog progressDialog = new ProgressDialog(ClaimActivity.this);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show(); // show progress dialog

        apiInterface= Api.getClient().create(ApiInterface.class);

        SaveclaimrequestModel user = new SaveclaimrequestModel(UserId,Datee,Timee,ClaimTypeId,HotelName,HotelLocation,ModeOfTravelId,DepartureFrom,ArriveTo,Amount,FileName,FilePath,FileType,Description);

        Call<SaveclaimrequestModel> call1 = apiInterface.saveClaim(user);
        call1.enqueue(new Callback<SaveclaimrequestModel>() {
            @Override
            public void onResponse(Call<SaveclaimrequestModel> call, Response<SaveclaimrequestModel> response) {

                SaveclaimrequestModel user1 = response.body();
                progressDialog.dismiss();
                String str=user1.getMessage();
                String status=user1.getStatus();
                Toast.makeText(ClaimActivity.this, str, Toast.LENGTH_SHORT).show();

            }
            @Override
            public void onFailure(Call<SaveclaimrequestModel> call, Throwable t) {
                call.cancel();
               String str= call.toString();
                faillerdiaolog(str);
                //   Toast.makeText(LoginActivity.this, "somthing went wrong", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });
    }
    public  void faillerdiaolog(String msg){

        final Dialog dialog = new Dialog(ClaimActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.failuremsg);

        TextView tv_failmsg=dialog.findViewById(R.id.tv_failmsg);
        tv_failmsg.setText(msg);

        TextView tv_cancelmsg=dialog.findViewById(R.id.tv_cancelmsg);
        tv_cancelmsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
            }
        });
        dialog.show();
    }

}
