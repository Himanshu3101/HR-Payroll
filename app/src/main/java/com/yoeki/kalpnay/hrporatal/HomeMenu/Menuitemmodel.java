package com.yoeki.kalpnay.hrporatal.HomeMenu;

import android.graphics.Bitmap;

public class Menuitemmodel {

    Bitmap imageid;
    String menuname;

    public Bitmap getImageid() {
        return imageid;
    }

    public void setImageid(Bitmap imageid) {
        this.imageid = imageid;
    }

    public Menuitemmodel(Bitmap imageid){

        this.imageid=imageid;
        this.menuname=menuname;
    }
    public String getMenuname() {
        return menuname;
    }

    public void setMenuname(String menuname) {
        this.menuname = menuname;
    }


}
