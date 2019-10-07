package com.nobletecx.mbooks;

import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

        rr = findViewById(R.id.relativeLayout1);
        rr.startAnimation(AnimationUtils.loadAnimation(this, R.anim.translate_anim));


        //LINKING WITH UI
        TextView rockStarPage = findViewById(R.id.rockStar_Page);
        TextView colorTheoryPage = findViewById(R.id.colorTheory_page);
        TextView ProWebDevPage = findViewById(R.id.PWD_page);
        TextView WebDesignPage = findViewById(R.id.WD_Page);
        TextView WPGuidePage = findViewById(R.id.WPG_page);

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
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("Refresh Books")
                .setIcon(R.drawable.ic_refresh_black)
                .setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        menu.add("Info")
                .setIcon(R.drawable.ic_info_outline)
                .setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getTitle().toString()) {
            case "Refresh Books":
                Toast.makeText(this, "This feature will be added in next version.", Toast.LENGTH_LONG).show();
                break;

            case "Info":
                showDialog(this, "Info", "This app is developed for those who want to be a good programmer.\n" +
                        "Credits goes to Original PDF Writers.\n\n" +
                        "Developed by RKhar");
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
