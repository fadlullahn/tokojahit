package com.example.tokojahit;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tokojahit.Adapter.SessionManager;

import java.util.HashMap;

public class ProUseActivity extends AppCompatActivity {

    private TextView tvUserId, tvUsername, tvName, tvLevel, tvPassword, tvEmail, tvNowa, tvAlamat;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pro_use);

        // Inisialisasi SessionManager
        sessionManager = new SessionManager(this);

        // Menghubungkan dengan komponen di XML
        tvUserId = findViewById(R.id.tvUserId);
        tvUsername = findViewById(R.id.tvUsername);
        tvName = findViewById(R.id.tvName);
        tvLevel = findViewById(R.id.tvLevel);
        tvPassword = findViewById(R.id.tvPassword);
        tvEmail = findViewById(R.id.tvEmail);
        tvNowa = findViewById(R.id.tvNowa);
        tvAlamat = findViewById(R.id.tvAlamat);

        // Mengambil detail pengguna dari SessionManager
        HashMap<String, String> user = sessionManager.getUserDetail();

        // Menampilkan detail pengguna
        tvUserId.setText(user.get(SessionManager.USER_ID));
        tvUsername.setText(user.get(SessionManager.USERNAME));
        tvName.setText(user.get(SessionManager.NAME));
        tvLevel.setText(user.get(SessionManager.LEVEL));
        tvPassword.setText(user.get(SessionManager.PASSWORD));
        tvEmail.setText(user.get(SessionManager.EMAIL));
        tvNowa.setText(user.get(SessionManager.NOWA));
        tvAlamat.setText(user.get(SessionManager.ALAMAT));

    }
}
