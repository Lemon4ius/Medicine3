package com.example.medicine;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.telephony.data.UrspRule;

public class Medicine {

    public Uri poster;
    public String name;
    public String discription;
    public String image;

    Medicine(String name, String discription, Uri poster, String image) {

        this.poster = poster;
        this.name = name;
        this.discription = discription;
        this.image = image;

    }



}