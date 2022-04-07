package com.planradar.weather.utils;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;
import android.widget.LinearLayout;

import com.planradar.weather.R;

public class ProgressBar {
    public static Dialog progressDialog;

    private static Context mContext;
    public static void showProgressDialog(Context context){

        if (context != mContext)
            progressDialog = new Dialog(context);
        mContext = context;
        progressDialog.setContentView(R.layout.progress_bar);



        Window window = progressDialog.getWindow();
        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);

        progressDialog.setCancelable(false);


        progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        progressDialog.show();


    }
    public static void hideProgressDialog(){
        progressDialog.dismiss();
    }

}
