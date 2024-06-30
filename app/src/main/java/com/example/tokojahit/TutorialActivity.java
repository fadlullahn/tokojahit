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
        webViewUkur.loadUrl("https://youtube.com/watch?v=MFzoOidChFk&si=epXbgGPr-VtQXBjL");

        // WebView untuk cara membuat pola
        webViewPola = findViewById(R.id.wvCaraMembuatPola);
        WebSettings webSettingsPola = webViewPola.getSettings();
        webSettingsPola.setJavaScriptEnabled(true);
        webViewPola.setWebViewClient(new WebViewClient());
        webViewPola.loadUrl("https://youtube.com/watch?v=BCyjr6xvFU8&si=ZYIeclRBeDom_yFs");
    }
}