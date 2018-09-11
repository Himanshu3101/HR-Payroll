package com.yoeki.kalpnay.hrporatal.Request;

import android.app.DatePickerDialog;
import android.app.Dialog;
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
import com.yoeki.kalpnay.hrporatal.R;
import com.yoeki.kalpnay.hrporatal.TimeAttendance.TimeAttendance_Menu;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class LeaveRequest extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_leavereqfromdate,tv_leavereqtodate,tv_leavereqtype,tv_leavereqattachment;
    private int mYear, mMonth, mDay;
    private AppCompatButton img_backrequest;
    private LinearLayout ly_leavereqattachment,ly_leaveattachment;
    private RecyclerView rec_leavereqattachment;
    ArrayList<Menuitemmodel> arrayreqattachlist;
    LinearLayoutManager linearlayoutmanager;
    String str="temp",whereCome;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave_request);

        Intent intent= getIntent();
        whereCome = intent.getStringExtra("whereCome");

        initialize();

        Calendar cc = Calendar.getInstance();
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        arrayreqattachlist=new ArrayList<>();

        tv_leavereqfromdate.setOnClickListener(this);
        tv_leavereqtodate.setOnClickListener(this);
        tv_leavereqtype.setOnClickListener(this);
        img_backrequest.setOnClickListener(this);
        tv_leavereqattachment.setOnClickListener(this);

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

          case R.id.tv_leavereqtodate:
              todate();
              break;

          case R.id.tv_leavereqtype:
              leavetypedialog();
              break;
          case R.id.img_backrequest:

              finish();
              break;
          case R.id.tv_leavereqattachment:
              uploadattachmentdialog();
             break;
      }
    }

    public  void initialize(){

        tv_leavereqfromdate=findViewById(R.id.tv_leavereqfromdate);
        tv_leavereqtodate=findViewById(R.id.tv_leavereqtodate);
        tv_leavereqtype=findViewById(R.id.tv_leavereqtype);
        img_backrequest=findViewById(R.id.img_backrequest);
        tv_leavereqattachment=findViewById(R.id.tv_leavereqattachment);
        ly_leavereqattachment=findViewById(R.id.ly_leavereqattachment);
        rec_leavereqattachment=findViewById(R.id.rec_leavereqattachment);
        ly_leaveattachment=findViewById(R.id.ly_leaveattachment);

    }

    public  void fromdate(){
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        Calendar cc = Calendar.getInstance();
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        final String getCurrentDateTime = sdf.format(cc.getTime());

        DatePickerDialog datePickerDialog = new DatePickerDialog(LeaveRequest.this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        tv_leavereqfromdate.setText(year + "-" + (monthOfYear + 1) +"-"+dayOfMonth );

                        String strtodate=year + "-" + (monthOfYear + 1) +"-"+dayOfMonth;

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
                            tv_leavereqfromdate.setText(strtodate );
                            // Log.d("Return","getMyTime smaller than getCurrentDateTime ");
                        }
                        else {
                            tv_leavereqfromdate.setText(getCurrentDateTime );
                          //  tv_leavereqfromdate.setError("select correct date");
                        }

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
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        final String getCurrentDateTime = sdf.format(cc.getTime());

        DatePickerDialog datePickerDialog = new DatePickerDialog(LeaveRequest.this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        tv_leavereqtodate.setText(year + "-" + (monthOfYear + 1) +"-"+dayOfMonth );

                        String strtodate=year + "-" + (monthOfYear + 1) +"-"+dayOfMonth;

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
                            tv_leavereqtodate.setText(strtodate );
                            // Log.d("Return","getMyTime smaller than getCurrentDateTime ");
                        }
                        else {
                            tv_leavereqtodate.setText(getCurrentDateTime );
                           // tv_leavereqtodate.setError("select correct date");
                        }

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    public void leavetypedialog(){
        final RadioGroup radioGroup_leavetype;
        TextView tv_leavereqsubmit;
        final Dialog dialog = new Dialog(LeaveRequest.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
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
        builder.setTitle("upload file");
        builder.setItems(options, new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int item)
            {

                if (options[item].equals("Choose from Gallery"))
                {

                    Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
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

}
