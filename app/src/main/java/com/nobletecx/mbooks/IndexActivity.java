package com.nobletecx.mbooks;

import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class IndexActivity extends AppCompatActivity {

    RelativeLayout rr;
    TextView rockStarPage, colorTheoryPage, ProWebDevPage, WebDesignPage, WPGuidePage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

        rr = findViewById(R.id.relativeLayout1);
        rr.startAnimation(AnimationUtils.loadAnimation(this, R.anim.translate_anim));



        //LINKING WITH UI
         rockStarPage = findViewById(R.id.rockStar_Page);
         colorTheoryPage = findViewById(R.id.colorTheory_page);
         ProWebDevPage = findViewById(R.id.PWD_page);
         WebDesignPage = findViewById(R.id.WD_Page);
         WPGuidePage = findViewById(R.id.WPG_page);

         //getting saved page number and setting it
         setPageNumber();

    }

    public void goRockStar(View view) {
        startActivity(new Intent(IndexActivity.this, RockStarDev.class));
    }

    public void goColorTheory(View view) {
        startActivity(new Intent(IndexActivity.this, ColorTheory.class));
    }

    public void goProWebDev(View view) {
        startActivity(new Intent(IndexActivity.this, ProWebDev.class));
    }

    public void goWebDesign(View view) {
        startActivity(new Intent(IndexActivity.this, WebDesign.class));
    }

    public void goWPGuide(View view) {
        startActivity(new Intent(IndexActivity.this, WPGuide.class));
    }

    public void setPageNumber(){
        //getting saved page numbers
        SharedPreferences sp = getSharedPreferences("bookData", 0);
        int rockStar_SavedPage = sp.getInt("savedPage_RS", 0);
        int colorTheory_SavedPage = sp.getInt("savedPage_CT", 0);
        int ProWebDev_SavedPage = sp.getInt("savedPage_PWD", 0);
        int WebDesign_SavedPage = sp.getInt("savedPage_WD", 0);
        int WPGuide_SavedPage = sp.getInt("savedPage_WPG", 0);

        //setting up page numbers
        rockStarPage.setText(String.valueOf(rockStar_SavedPage) + "/482");
        colorTheoryPage.setText(String.valueOf(colorTheory_SavedPage) + "/64");
        ProWebDevPage.setText(String.valueOf(ProWebDev_SavedPage) + "/528");
        WebDesignPage.setText(String.valueOf(WebDesign_SavedPage) + "/170");
        WPGuidePage.setText(String.valueOf(WPGuide_SavedPage) + "/18");
    }

    public void showDialog(Context context, String title, String msg) {
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_helper);
        TextView tvTitle = dialog.findViewById(R.id.dialogTitle);
        TextView tvMsg = dialog.findViewById(R.id.dialogMsg);
        Button btnOK = dialog.findViewById(R.id.dialogButtonOK);

        tvTitle.setText(title);
        tvMsg.setText(msg);
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        //dialog.create();
        dialog.show();
    }

    @Override
    protected void onResume() {
        //rr.startAnimation(AnimationUtils.loadAnimation(this, R.anim.translate_anim));
        setPageNumber();
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        SharedPreferences sp = getSharedPreferences("appData",0);
        boolean getFSValue = sp.getBoolean("isfullScreen", false);
        if (getFSValue){
            menu.findItem(R.id.fullscreen).setIcon(R.drawable.ic_fullscreen_exit);
        }else {
            menu.findItem(R.id.fullscreen).setIcon(R.drawable.ic_fullscreen);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.refresh:
                Toast.makeText(this, "This feature will be added in next version.", Toast.LENGTH_SHORT).show();
                break;

            case R.id.fullscreen:
                //change icon
                SharedPreferences sp = getSharedPreferences("appData",0);
                SharedPreferences.Editor spEdit = sp.edit();
                // if full screen value is already have in SharedPreferences
                if (sp.contains("isfullScreen")) {
                    //getting boolean value for toggling button
                    boolean getFSValue = sp.getBoolean("isfullScreen", false);
                    if (getFSValue) {
                        item.setIcon(R.drawable.ic_fullscreen);
                        spEdit.putBoolean("isfullScreen", false);
                        spEdit.apply();

                    } else {
                        item.setIcon(R.drawable.ic_fullscreen_exit);
                        spEdit.putBoolean("isfullScreen", true);
                        spEdit.apply();

                    }
                }else {
                    //if fullscreen value is doesn't have
                    item.setIcon(R.drawable.ic_fullscreen_exit);
                    spEdit.putBoolean("isfullScreen", true);
                    spEdit.apply();

                }

                break;

            case R.id.info:
                showDialog(this, "Info", "This app is developed for those who want to be a good programmer.\n" +
                        "Credits goes to Original PDF Writers.\n\n" +
                        "Developed by RKhar");
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
