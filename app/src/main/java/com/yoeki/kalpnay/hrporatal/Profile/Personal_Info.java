package com.yoeki.kalpnay.hrporatal.Profile;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.AppCompatImageView;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yoeki.kalpnay.hrporatal.Profile.Model.user_info.BasicUserInfo;
import com.yoeki.kalpnay.hrporatal.Profile.Model.user_info.User;
import com.yoeki.kalpnay.hrporatal.R;
import com.yoeki.kalpnay.hrporatal.setting.Textclass;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Personal_Info  extends Fragment{
    private static Personal_Info personal_instance;
    Textclass personal_number,code,designation,title,name,gender,martial_stat,dob,ssn;
    ArrayList<User> mlist;
    AppCompatImageButton chngepic;
    AppCompatImageView imageView;
    int GET_FROM_GALLERY;
    Bitmap bitmapP = null;
    RoundImage roundImage1;
    String ba1;

    public static Personal_Info newInstance() {
        Personal_Info fragment = new Personal_Info();
        return fragment;
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        personal_number=(Textclass) getView().findViewById(R.id.personal_number);
        code=(Textclass)getView().findViewById(R.id.code);
        designation=(Textclass)getView().findViewById(R.id.designation);
        title=(Textclass)getView().findViewById(R.id.title);
        name=(Textclass)getView().findViewById(R.id.name);
        gender=(Textclass)getView().findViewById(R.id.gender);
        martial_stat = (Textclass)getView().findViewById(R.id.martial_stat);
        dob = (Textclass)getView().findViewById(R.id.dob);
        ssn = (Textclass)getView().findViewById(R.id.ssn);
        chngepic = (AppCompatImageButton)getView().findViewById(R.id.chngepic);
        imageView  = (AppCompatImageView)getView().findViewById(R.id.usr_logo);

        chngepic.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                try {
                    startActivityForResult(new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI), GET_FROM_GALLERY);
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        });


        for (final User list : mlist) {
            for(BasicUserInfo basicUserInfo : list.basicUserInfo) {
                personal_number.setText(basicUserInfo.getPesonalNumber());
                code.setText(basicUserInfo.getCode());
                designation.setText(basicUserInfo.getDesignation());
                title.setText(basicUserInfo.getTitle());
                name.setText(basicUserInfo.getUserName());
                gender.setText(basicUserInfo.getGender());
                martial_stat.setText(basicUserInfo.getMartialStatus());
                dob.setText(basicUserInfo.getDOB());
                ssn.setText(basicUserInfo.getStartingDate());
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.personal__info, container, false);
        Bundle args = getArguments();
        mlist = (ArrayList<User>) getArguments().getSerializable("keyForProfile");
        personal_instance=this;
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==GET_FROM_GALLERY && resultCode == Activity.RESULT_OK) {
            Uri selectedImage = data.getData();
            int width=200;
            int height=200;
            try {
                Context applicationContext = Profile.getContextOfApplication();

                bitmapP = MediaStore.Images.Media.getBitmap(applicationContext.getContentResolver(), selectedImage);
                bitmapP = Bitmap.createScaledBitmap(bitmapP, width,height, true);
                ByteArrayOutputStream bao = new ByteArrayOutputStream();
                bitmapP.compress(Bitmap.CompressFormat.JPEG, 100, bao);
                byte [] ba = bao.toByteArray();
                ba1= Base64.encodeToString(ba,Base64.DEFAULT);
                try {
                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                    StrictMode.setThreadPolicy(policy);
                    byte[] decodedString = Base64.decode(ba1, Base64.DEFAULT);
                    Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                    roundImage1 = new RoundImage(decodedByte);
                    imageView.setImageDrawable(roundImage1);
//                    HttpClient httpclient = new DefaultHttpClient();
////                    HttpPost httppost = new HttpPost("http://192.168.10.19:"+getString(R.string.port)+"/AccessControl/webresources/WebServices/changeImage?code=5");
//                    HttpPost httppost = new HttpPost("http://"+ip+".novusapl-online.com:"+getString(R.string.port)+"/AccessControl/webresources/WebServices/changeImage?code="+Sresult+"");
//                    httppost.setEntity(new StringEntity(ba1));
//                    HttpResponse response = httpclient.execute(httppost);
//                    if((response.getStatusLine().getStatusCode()==200)||(response.getStatusLine().getStatusCode()==201)){
//                        String server_response = EntityUtils.toString(response.getEntity());
//                        if(server_response.equals("1")){
//                            Toast.makeText(this, "Pic Updated...", Toast.LENGTH_SHORT).show();
//                        }else{Toast.makeText(this, "Pic not Update, Please Try After Sometime", Toast.LENGTH_SHORT).show();}
//                        Log.i("Server response", server_response );
//                    } else {
//                        Log.i("Server response", "Failed to get server response" );
//                        Toast.makeText(this, "Try After Sometime.....", Toast.LENGTH_SHORT).show();
//                    }
                } catch (Exception e) {
                    Log.e("log_tag", "Error in http connection " + e.toString());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
