package com.yoeki.kalpnay.hrporatal.Request;

import android.app.DatePickerDialog;
import android.app.Dialog;
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
import com.yoeki.kalpnay.hrporatal.R;
import com.yoeki.kalpnay.hrporatal.setting.Textclass;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ClaimActivity extends AppCompatActivity implements View.OnClickListener{

    private LinearLayout ly_claimtypehotelview,ly_claimtypeother,ly_claimtypetravelview,ly_leavereqattachment,ly_attachmentclaim;
    private TextView tv_claimreqfromdate,tv_claimreqtime,tv_claimreqtype,tv_claimtypetravelmode,tv_claimreqattachment;
    private Textclass tv_claimtypehotelname,edt_claimreqdescription,tv_claimtypehotellocation,tv_claimtypetravelarivalto,tv_claimtypetraveldeparturefrom;
    private int mYear, mMonth, mDay,mHour,mMinute;
    String str="temp";
    AppCompatButton img_backrequestclaim;
    ArrayList<Menuitemmodel> arrayreqattachlist;
    RecyclerView rec_leavereqattachment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_claim);

        initialize();
        arrayreqattachlist=new ArrayList<>();
        tv_claimreqfromdate.setOnClickListener(this);
        tv_claimreqtime.setOnClickListener(this);
        tv_claimreqtype.setOnClickListener(this);
        tv_claimtypetravelmode.setOnClickListener(this);
        tv_claimreqattachment.setOnClickListener(this);
        img_backrequestclaim.setOnClickListener(this);

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
        }
    }

    public void claimtypedialog(){
        final RadioGroup radioGroup_leavetype;
        TextView tv_leavereqsubmit;
        final Dialog dialog = new Dialog(ClaimActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
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
        dialog.setCancelable(false);
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
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        final String getCurrentDateTime = sdf.format(cc.getTime());

        DatePickerDialog datePickerDialog = new DatePickerDialog(ClaimActivity.this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        tv_claimreqfromdate.setText(year + "-" + (monthOfYear + 1) +"-"+dayOfMonth );
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
        builder.setTitle("upload file");
        builder.setItems(options, new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int item)
            {

                if (options[item].equals("Choose from Gallery"))
                {

                    Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
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

              //  Image = getResizedBitmap(Image, 400);
                /*ByteArrayOutputStream baoss = new ByteArrayOutputStream();
                Image.compress(Bitmap.CompressFormat.PNG, 100, baoss); //bm is the bitmap object
                byte[] bb = baoss.toByteArray();
                String final_path = Base64.encodeToString(bb, Base64.DEFAULT);*/
                // Get the cursor
                Cursor cursor = ClaimActivity.this.getContentResolver().query(selectedImage,
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

}
