package com.nobletecx.mbooks;

import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.provider.OpenableColumns;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.listener.OnPageErrorListener;
import com.github.barteksc.pdfviewer.listener.OnRenderListener;
import com.shockwave.pdfium.PdfDocument;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;

public class WebDesign extends AppCompatActivity implements OnPageChangeListener, OnPageErrorListener, OnRenderListener {

    private static String pdf = "Learning Web Design - UThaungWin.pdf";
    PDFView pdfView;
    TextView Name, Author, Subj;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pdfView = findViewById(R.id.pdfView);


        //check permission
        int permissionCheck = ContextCompat.checkSelfPermission(this,
                READ_EXTERNAL_STORAGE);

        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{READ_EXTERNAL_STORAGE},
                    0
            );

            return;
        }

        //getting current page
        SharedPreferences sharedPreferences = getSharedPreferences("bookData", 0);
        int getSavedPage = sharedPreferences.getInt("savedPage_WD", 0);
        if (sharedPreferences.contains("savedPage_WD")) {
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
        } else {
            pdfView.fromAsset(pdf)
                    //.pages(0, 2, 1, 3, 3, 3, 4) // all pages are displayed by default
                    .enableSwipe(true) // allows to block changing pages using swipe
                    .enableDoubletap(true)
                    .defaultPage(0)
                    .scrollHandle(null)
                    .swipeHorizontal(false)

                    .invalidPageColor(Color.BLACK)
                    .onPageError(this)
                    .onPageChange(this)
                    .spacing(10) // in dp
                    .load();
        }

    }

    @Override
    public void onPageError(int page, Throwable t) {
        Toast.makeText(getApplicationContext(), "Cant load pdf : " + page + " ::: " + t, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onPageChanged(int page, int pageCount) {

        ActionBar ab = getSupportActionBar();
        ab.setTitle("Learning Web Design - UThaungWin");
        ab.setSubtitle("Page: " + page + "/" + pageCount);
    }

    @Override
    public void onInitiallyRendered(int nbPages, float pageWidth, float pageHeight) {
        pdfView.fitToWidth(nbPages);
    }

    @Override
    public void onBackPressed() {
        int currentPage = pdfView.getCurrentPage();
        SharedPreferences sharedPreferences = getSharedPreferences("bookData", 0);
        SharedPreferences.Editor spEdit = sharedPreferences.edit();

        spEdit.putInt("savedPage_WD", currentPage);
        spEdit.apply();
        Toast.makeText(getApplicationContext(), "Saved Page for Learning WebDesign : " + currentPage, Toast.LENGTH_SHORT).show();


        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        int currentPage = pdfView.getCurrentPage();
        SharedPreferences sharedPreferences = getSharedPreferences("bookData", 0);
        SharedPreferences.Editor spEdit = sharedPreferences.edit();

        spEdit.putInt("savedPage_WD", currentPage);
        spEdit.apply();
        Toast.makeText(getApplicationContext(), "Saved Page for Learning WebDesign : " + currentPage, Toast.LENGTH_SHORT).show();

        super.onPause();
    }

    @Override
    protected void onDestroy() {
        int currentPage = pdfView.getCurrentPage();
        SharedPreferences sharedPreferences = getSharedPreferences("bookData", 0);
        SharedPreferences.Editor spEdit = sharedPreferences.edit();

        spEdit.putInt("savedPage_WD", currentPage);
        spEdit.apply();
        Toast.makeText(getApplicationContext(), "Saved Page for Learning WebDesign : " + currentPage, Toast.LENGTH_SHORT).show();

        super.onDestroy();
    }
}
