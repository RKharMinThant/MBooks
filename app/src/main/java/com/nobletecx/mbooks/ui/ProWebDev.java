package com.nobletecx.mbooks.ui;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.listener.OnPageErrorListener;
import com.github.barteksc.pdfviewer.listener.OnRenderListener;
import com.nobletecx.mbooks.Constants;
import com.nobletecx.mbooks.utils.PreferenceUtils;
import com.nobletecx.mbooks.R;
import com.nobletecx.mbooks.utils.ViewUtils;

public class ProWebDev extends AppCompatActivity implements OnPageChangeListener, OnPageErrorListener, OnRenderListener {

    private static String pdf = "Professional-Web-Developer - EiMaung.pdf";
    PDFView pdfView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_view);

        pdfView = findViewById(R.id.pdfView);

        //continuing full screen state
        if (PreferenceUtils.getPreferenceBoolean(this, Constants.PREF_IS_FULLSCREEN)) {
            ViewUtils.hideActionBar(this);
        }else{
            ViewUtils.showActionBar(this);
        }

        //getting current page
        int getSavedPage = PreferenceUtils.getPreferenceInteger(this, Constants.PREF_PROFESSIONAL_WEB_DEVELOPER);
        pdfView.fromAsset(pdf)
                //setting default page
                .defaultPage(getSavedPage)
                .enableSwipe(true) // allows to block changing pages using swipe
                .enableDoubletap(true)
                .scrollHandle(null)
                .swipeHorizontal(false)
                .invalidPageColor(Color.BLACK)
                .onPageError(this)
                .onPageChange(this)
                .spacing(10) // in dp
                .load();


    }

    @Override
    public void onPageError(int page, Throwable t) {
        Toast.makeText(getApplicationContext(), "Cant load pdf : " + page + " ::: " + t, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onPageChanged(int page, int pageCount) {

        ActionBar ab = getSupportActionBar();
        ab.setTitle("Professional Web Developer");
        ab.setSubtitle("Page: " + page + "/" + pageCount);
    }

    @Override
    public void onInitiallyRendered(int nbPages, float pageWidth, float pageHeight) {
        pdfView.fitToWidth(nbPages);
    }

    @Override
    protected void onPause() {
        super.onPause();
        int currentPage = pdfView.getCurrentPage();
        PreferenceUtils.savePreferenceInteger(this, Constants.PREF_PROFESSIONAL_WEB_DEVELOPER, currentPage);
        Toast.makeText(getApplicationContext(), "Saved Page for Professional-WebDeveloper  : " + currentPage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}