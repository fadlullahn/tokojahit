package com.example.tokojahit;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

public class TutorialActivity extends AppCompatActivity {
    private WebView webViewUkur, webViewPola;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);

        // WebView untuk cara mengukur
        webViewUkur = findViewById(R.id.wvCaraMengukur);
        WebSettings webSettingsUkur = webViewUkur.getSettings();
        webSettingsUkur.setJavaScriptEnabled(true);
        webViewUkur.setWebViewClient(new WebViewClient());
        webViewUkur.loadUrl("https://youtu.be/cekET4LZr20?si=Ko0j3XCU_TVNOpA1");

        // WebView untuk cara membuat pola
        webViewPola = findViewById(R.id.wvCaraMembuatPola);
        WebSettings webSettingsPola = webViewPola.getSettings();
        webSettingsPola.setJavaScriptEnabled(true);
        webViewPola.setWebViewClient(new WebViewClient());
        webViewPola.loadUrl("https://youtu.be/Dd3v-_lerQ4?si=ducHvYnF3whVUIJ0");
    }
}