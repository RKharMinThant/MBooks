package com.nobletecx.mbooks.ui;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.nobletecx.mbooks.Constants;
import com.nobletecx.mbooks.utils.PreferenceUtils;
import com.nobletecx.mbooks.R;
import com.nobletecx.mbooks.adapter.RecyclerViewAdapter;
import com.nobletecx.mbooks.model.Book;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    ConstraintLayout constraintLayout;
    ArrayList<Book> bookList = new ArrayList<>();
    RecyclerViewAdapter adapter;
    private RecyclerViewAdapter.onRecyclerViewItemClickListener recyclerViewOnItemClickListener = new RecyclerViewAdapter.onRecyclerViewItemClickListener() {
        @Override
        public void onItemClickListener(View view, int position) {
            switch (position) {
                case 0:
                    startActivity(new Intent(MainActivity.this, RockStarDev.class));
                    break;
                case 1:
                    startActivity(new Intent(MainActivity.this, ColorTheory.class));
                    break;
                case 2:
                    startActivity(new Intent(MainActivity.this, ProWebDev.class));
                    break;
                case 3:
                    startActivity(new Intent(MainActivity.this, WebDesign.class));
                    break;
                case 4:
                    startActivity(new Intent(MainActivity.this, WPGuide.class));
                    break;
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUIComponents();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }

    private void loadData() {
        bookList.clear();
        bookList.add(new Book(getResources().getString(R.string.rock_star_developer), getResources().getString(R.string.author_ei_maung), PreferenceUtils.getPreferenceInteger(this, Constants.PREF_ROCKSTAR_DEVELOPER), R.drawable.dev));
        bookList.add(new Book(getResources().getString(R.string.color_theory), getResources().getString(R.string.author_u_thit_lwin_soe), PreferenceUtils.getPreferenceInteger(this, Constants.PREF_COLOR_THEORY), R.drawable.ic_color_theory));
        bookList.add(new Book(getResources().getString(R.string.professional_web_developer), getResources().getString(R.string.author_ei_maung), PreferenceUtils.getPreferenceInteger(this, Constants.PREF_PROFESSIONAL_WEB_DEVELOPER), R.drawable.pro_web_dev));
        bookList.add(new Book(getResources().getString(R.string.learning_web_design), getResources().getString(R.string.author_u_thaung_lwin), PreferenceUtils.getPreferenceInteger(this, Constants.PREF_WEB_DESIGN), R.drawable.web_design));
        bookList.add(new Book(getResources().getString(R.string.wordprees_installation_guide), getResources().getString(R.string.author_unknown), PreferenceUtils.getPreferenceInteger(this, Constants.PREF_WORDPRESS_INSTALL_GUIDE), R.drawable.wp));

        adapter.setData(bookList);
    }

    private void initUIComponents() {
        recyclerView = findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        adapter = new RecyclerViewAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
        adapter.setOnItemClickListener(recyclerViewOnItemClickListener);
        constraintLayout = findViewById(R.id.constraintLayout);
        constraintLayout.startAnimation(AnimationUtils.loadAnimation(this, R.anim.translate_anim));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        if (PreferenceUtils.getPreferenceBoolean(this, Constants.PREF_IS_FULLSCREEN)) {
            menu.findItem(R.id.fullscreen).setIcon(R.drawable.ic_fullscreen_exit);
        } else {
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
                if (PreferenceUtils.getPreferenceBoolean(this, Constants.PREF_IS_FULLSCREEN)) {
                    item.setIcon(R.drawable.ic_fullscreen);
                    PreferenceUtils.savePreferenceBoolean(this, Constants.PREF_IS_FULLSCREEN, false);
                } else {
                    item.setIcon(R.drawable.ic_fullscreen_exit);
                    PreferenceUtils.savePreferenceBoolean(this, Constants.PREF_IS_FULLSCREEN, true);
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

}
