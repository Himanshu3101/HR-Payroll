package com.yoeki.kalpnay.hrporatal.Request;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.yoeki.kalpnay.hrporatal.HomeMenu.Menuitemmodel;
import com.yoeki.kalpnay.hrporatal.Login.Api;
import com.yoeki.kalpnay.hrporatal.Login.ApiInterface;
import com.yoeki.kalpnay.hrporatal.R;
import com.yoeki.kalpnay.hrporatal.TimeAttendance.TimeAttendance_Menu;
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

public class LeaveRequest extends AppCompatActivity implements View.OnClickListener {

    private TextView /*tv_leavereqfromdate,tv_leavereqtodate,*/tv_leavereqtype,tv_leavereqattachment,edt_leavereqdescription;
    private int mYear, mMonth, mDay;
    private AppCompatButton img_backrequest;
    private LinearLayout ly_leavereqattachment,ly_leaveattachment;
    private RecyclerView rec_leavereqattachment;
    ArrayList<Menuitemmodel> arrayreqattachlist;
    LinearLayoutManager linearlayoutmanager;
    String str="temp",whereCome,filepath;
    Button time_entrySubmit;
    ApiInterface apiInterface;
//    ProgressDialog PD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave_request);

        Intent intent= getIntent();
        whereCome = intent.getStringExtra("whereCome");

//        PD = new ProgressDialog(LeaveRequest.this);

//        PD.show();
//        serverCode();

        initialize();

        Calendar cc = Calendar.getInstance();
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        arrayreqattachlist=new ArrayList<>();

//        tv_leavereqfromdate.setOnClickListener(this);
//        tv_leavereqtodate.setOnClickListener(this);
        tv_leavereqtype.setOnClickListener(this);
        img_backrequest.setOnClickListener(this);
        tv_leavereqattachment.setOnClickListener(this);
        time_entrySubmit.setOnClickListener(this);

        rec_leavereqattachment.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
                return false;
            }

            @Override
            public void onTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {

            }
            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean b) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(whereCome.equals("fromRequest")) {
            Intent intent = new Intent(getApplicationContext(), RequestMenu.class);
            startActivity(intent);
        }else {
            Intent intent = new Intent(getApplicationContext(), TimeAttendance_Menu.class);
            startActivity(intent);
        }
    }


    @Override
    public void onClick(View view) {
      switch (view.getId()){

          case R.id.tv_leavereqfromdate:
              fromdate();
              break;

//          case R.id.tv_leavereqtodate:
//              todate();
//              break;

          case R.id.tv_leavereqtype:
              leavetypedialog();
              break;
          case R.id.img_backrequest:
              if(whereCome.equals("fromRequest")) {
                  Intent intent = new Intent(getApplicationContext(), RequestMenu.class);
                  startActivity(intent);
              }else {
                  Intent intent = new Intent(getApplicationContext(), TimeAttendance_Menu.class);
                  startActivity(intent);
              }
              finish();
              break;
          case R.id.tv_leavereqattachment:
              uploadattachmentdialog();
             break;

          case R.id.time_entrySubmit:

              String  user_id=null;
              user_id = preferance.getInstance(getApplicationContext()).getUserId();
//              String strfromdate= tv_leavereqfromdate.getText().toString();
//              String leavereqtoda= tv_leavereqtodate.getText().toString();
              String leavereqtype= tv_leavereqtype.getText().toString();
              String leavereqdescription= edt_leavereqdescription.getText().toString();

              saveleave(user_id,"2"/*,strfromdate,leavereqtoda*/,leavereqdescription,"image",filepath,"png");

              break;

          }
    }

    public  void initialize(){
//        tv_leavereqfromdate=findViewById(R.id.tv_leavereqfromdate);
//        tv_leavereqtodate=findViewById(R.id.tv_leavereqtodate);
        tv_leavereqtype=findViewById(R.id.tv_leavereqtype);
        img_backrequest=findViewById(R.id.img_backrequest);
        tv_leavereqattachment=findViewById(R.id.tv_leavereqattachment);
        ly_leavereqattachment=findViewById(R.id.ly_leavereqattachment);
        rec_leavereqattachment=findViewById(R.id.rec_leavereqattachment);
        ly_leaveattachment=findViewById(R.id.ly_leaveattachment);

        edt_leavereqdescription=findViewById(R.id.edt_leavereqdescription);
        time_entrySubmit=findViewById(R.id.time_entrySubmit);
    }

    public  void fromdate(){

        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        Calendar cc = Calendar.getInstance();
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        final String getCurrentDateTime = sdf.format(cc.getTime());

        DatePickerDialog datePickerDialog = new DatePickerDialog(LeaveRequest.this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

//                        tv_leavereqfromdate.setText(year + "/" + (monthOfYear + 1) +"/"+dayOfMonth );

                        String strtodate=year + "/" + (monthOfYear + 1) +"/"+dayOfMonth;

                        Date date2 = null;
                        Date date1=null;
                        try {
                            date1 = sdf.parse(getCurrentDateTime);
                            date2 = sdf.parse(strtodate);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
//                        if (date1.compareTo(date2) < 0)
//                        {
//                            tv_leavereqfromdate.setText(strtodate );
//                            // Log.d("Return","getMyTime smaller than getCurrentDateTime ");
//                        }
//                        else {
//                            tv_leavereqfromdate.setText(getCurrentDateTime );
//                          //  tv_leavereqfromdate.setError("select correct date");
//                        }

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    public  void todate(){
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        Calendar cc = Calendar.getInstance();
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        final String getCurrentDateTime = sdf.format(cc.getTime());

        DatePickerDialog datePickerDialog = new DatePickerDialog(LeaveRequest.this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

//                        tv_leavereqtodate.setText(year + "/" + (monthOfYear + 1) +"/"+dayOfMonth );

                        String strtodate=year + "/" + (monthOfYear + 1) +"/"+dayOfMonth;

                        Date date2 = null;
                        Date date1=null;
                        try {
                            date1 = sdf.parse(getCurrentDateTime);
                            date2 = sdf.parse(strtodate);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
//                        if (date1.compareTo(date2) < 0)
//                        {
//                            tv_leavereqtodate.setText(strtodate );
//                            // Log.d("Return","getMyTime smaller than getCurrentDateTime ");
//                        }
//                        else {
//                            tv_leavereqtodate.setText(getCurrentDateTime );
//                           // tv_leavereqtodate.setError("select correct date");
//                        }

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    public void leavetypedialog(){
        final RadioGroup radioGroup_leavetype;
        TextView tv_leavereqsubmit;
        final Dialog dialog = new Dialog(LeaveRequest.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.leavetype_dialog);

        radioGroup_leavetype=dialog.findViewById(R.id.radioGroup_leavetype);

        radioGroup_leavetype.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
               Button btn=radioGroup_leavetype.findViewById(i);

                      str=btn.getText().toString();
                      tv_leavereqtype.setText(btn.getText().toString());

            }
        });

        tv_leavereqsubmit=dialog.findViewById(R.id.tv_leavereqsubmit);

        tv_leavereqsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (str.equals("temp")){
                    Toast.makeText(LeaveRequest.this, "Please select leave type", Toast.LENGTH_SHORT).show();
                }else{
                    dialog.dismiss();
                }
            }
        });

        dialog.show();
    }

    public void uploadattachmentdialog(){

        final CharSequence[] options = {"Choose from Gallery","Choose from file", "Cancel"};

        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(LeaveRequest.this);
        builder.setTitle("Upload file");
        builder.setItems(options, new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int item)
            {

                if (options[item].equals("Choose from Gallery"))
                {

                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    (LeaveRequest.this).startActivityForResult(intent, 2);
                }else if (options[item].equals("Choose from file")){

                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                    intent.setType("file/*");
                    startActivityForResult(intent, 3);

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
        if (resultCode == RESULT_OK&&requestCode==2) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            final InputStream imageStream;
            try {
                imageStream = LeaveRequest.this.getContentResolver().openInputStream(selectedImage);
                Bitmap Image = BitmapFactory.decodeStream(imageStream);

                ByteArrayOutputStream baos = new ByteArrayOutputStream();

                Image.compress(Bitmap.CompressFormat.JPEG, 100, baos);

                byte[] byteArrayImage = baos.toByteArray();

                 filepath = Base64.encodeToString(byteArrayImage, Base64.DEFAULT);

                Menuitemmodel dataitem=new Menuitemmodel(Image);
                arrayreqattachlist.add(dataitem);

                if (arrayreqattachlist.size()>0){

                    ly_leaveattachment.setVisibility(View.GONE);
                    ly_leavereqattachment.setVisibility(View.VISIBLE);

                    rec_leavereqattachment.setHasFixedSize(true);

                     linearlayoutmanager = new LinearLayoutManager(this);

                    rec_leavereqattachment.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
                   // rec_leavereqattachment.setLayoutManager(new Hori);
                    rec_leavereqattachment.setItemAnimator(new DefaultItemAnimator());

                    Attachmentview_adapter adapter=new Attachmentview_adapter(LeaveRequest.this,arrayreqattachlist);
                    rec_leavereqattachment.setAdapter(adapter);
                }else{
                    ly_leavereqattachment.setVisibility(View.GONE);
                    ly_leaveattachment.setVisibility(View.VISIBLE);
                }

   //                Image = getResizedBitmap(Image, 400);
                ByteArrayOutputStream baoss = new ByteArrayOutputStream();
              //  Image.compress(Bitmap.CompressFormat.PNG, 100, baoss); //bm is the bitmap object
                //byte[] bb = baoss.toByteArray();
               // String final_path = Base64.encodeToString(bb, Base64.DEFAULT);
                // Get the cursor
                Cursor cursor = LeaveRequest.this.getContentResolver().query(selectedImage,
                        filePathColumn, null, null, null);
                // Move to first row
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                String imgDecodableString = cursor.getString(columnIndex);
                cursor.close();

                // stu_image.setImageBitmap(Image);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float)width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }

    public void saveleave(String UserId,String LeaveTypeId/*,String FromDate,String ToDate*/,String Description,String FileName,String FilePath,String FileType){

    final ProgressDialog progressDialog = new ProgressDialog(LeaveRequest.this);
    progressDialog.setCancelable(false); // set cancelable to false
    progressDialog.setMessage("Please Wait"); // set message
    progressDialog.show(); // show progress dialog

    apiInterface= Api.getClient().create(ApiInterface.class);
    SaveLeave user = new SaveLeave(UserId, LeaveTypeId/*,FromDate,ToDate*/,Description,FileName,FilePath,FileType);

    Call<SaveLeave> call1 = apiInterface.saveleave(user);
    call1.enqueue(new Callback<SaveLeave>() {
        @Override
        public void onResponse(Call<SaveLeave> call, Response<SaveLeave> response) {
            SaveLeave user1 = response.body();
            progressDialog.dismiss();
            String str=user1.getMessage();
            String status=user1.getStatus();

            Toast.makeText(LeaveRequest.this, str, Toast.LENGTH_SHORT).show();
        }
        @Override
        public void onFailure(Call<SaveLeave> call, Throwable t) {
            call.cancel();
            faillerdiaolog("somthing went wrong");
            //   Toast.makeText(LoginActivity.this, "somthing went wrong", Toast.LENGTH_SHORT).show();
            progressDialog.dismiss();
        }
    });

}

    public  void faillerdiaolog(String msg){

        final Dialog dialog = new Dialog(LeaveRequest.this);
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





//    public void serverCode() {
//        String user_id = null;
//        user_id = preferance.getInstance(getApplicationContext()).getUserId();
//        final ProgressDialog pd = new ProgressDialog(getApplicationContext());
//        pd.setMessage("Loading...");
//        pd.setCancelable(true);
//        apiInterface = Api.getClient().create(ApiInterface.class);
//
//        LeaveRequestData leaveRequestData = new LeaveRequestData(user_id);
//        Call<WeekTIme_Entries_Recieve> call2 = apiInterface.idUser(timeEntryData);
//
//        call2.enqueue(new Callback<WeekTIme_Entries_Recieve>() {
//            @Override
//            public void onResponse(Call<WeekTIme_Entries_Recieve> call, Response<WeekTIme_Entries_Recieve> response) {
//                pd.dismiss();
//                WeekTIme_Entries_Recieve weekTIme_entries_recieve = response.body();
//                try {
//                    String status = weekTIme_entries_recieve.getStatus();
//                    String mess = weekTIme_entries_recieve.getMessage();
//                    dataofWeekEntries = weekTIme_entries_recieve.getListEmpTimeSheet();
//
//                    if (dataofWeekEntries.size() > 0) {
//                        String full_date = dataofWeekEntries.get(0).getDate();
//                        String[] full_month = full_date.split("/");
//                        montH = Integer.parseInt(full_month[0]);
//                        String[] full_Year = full_month[2].split("\\s+");
//                        yeaR = Integer.parseInt(full_Year[0]);
//
//                        for (int i = 0; i < dataofWeekEntries.size(); i++) {
//                            CmngstartTime = dataofWeekEntries.get(i).getStartTime().split(":");
//                            EndTime = dataofWeekEntries.get(i).getEndTime().split(":");
//                            description = dataofWeekEntries.get(i).getDescription();
//                            date = dataofWeekEntries.get(i).getDate().split("/");
//                        }
//                        mWeekView.notifyDatasetChanged();
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<WeekTIme_Entries_Recieve> call, Throwable t) {
//                pd.show();
//                serverCode();
//            }
//        });
//    }
}
