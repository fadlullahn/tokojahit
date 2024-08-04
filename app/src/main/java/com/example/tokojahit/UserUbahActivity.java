package com.example.tokojahit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.tokojahit.Api.ApiClient;
import com.example.tokojahit.Api.ApiInterface;
import com.example.tokojahit.Model.User.ResponseModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserUbahActivity extends AppCompatActivity {
    private int xId;
    private String xName, xUsername, xLevel, xPassword, xEmail, xNowa,xAlamat;
    private EditText etName, etUsername, etLevel, etPassword, etEmail, etNowa, etAlamat;
    private Button btnUbah;
    private String yName, yUsername, yLevel,yPassword, yEmail, yNowa, yAlamat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_ubah);

        Intent terima = getIntent();
        xId = terima.getIntExtra("xId", -1);
        xName = terima.getStringExtra("xName");
        xUsername = terima.getStringExtra("xUsername");
        xLevel = terima.getStringExtra("xLevel");
        xPassword = terima.getStringExtra("xPassword");
        xEmail = terima.getStringExtra("xEmail");
        xNowa = terima.getStringExtra("xNowa");
        xAlamat = terima.getStringExtra("xAlamat");

        etName = findViewById(R.id.et_name);
        etUsername = findViewById(R.id.et_username);
        etLevel = findViewById(R.id.et_level);
        etPassword = findViewById(R.id.et_password);
        etEmail = findViewById(R.id.et_email);
        etNowa = findViewById(R.id.et_nowa);
        etAlamat = findViewById(R.id.et_alamat);
        btnUbah = findViewById(R.id.btn_ubah);

        etName.setText(xName);
        etUsername.setText(xUsername);
        etLevel.setText(xLevel);
        etPassword.setText(xPassword);
        etEmail.setText(xEmail);
        etNowa.setText(xNowa);
        etAlamat.setText(xAlamat);

        btnUbah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                yName = etName.getText().toString();
                yUsername = etUsername.getText().toString();
                yLevel = etLevel.getText().toString();
                yPassword = etPassword.getText().toString();
                yEmail = etEmail.getText().toString();
                yNowa = etNowa.getText().toString();
                yAlamat = etAlamat.getText().toString();

                updateData();
            }
        });
    }

    private void updateData(){
        ApiInterface ardData = ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseModel> ubahData = ardData.ardUpdateData(xId, yName, yUsername, yLevel,yPassword, yEmail, yNowa, yAlamat);

        ubahData.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                int kode = response.body().getKode();
                String pesan = response.body().getPesan();

                Toast.makeText(UserUbahActivity.this, "Kode : "+kode+" | Pesan : "+pesan, Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(UserUbahActivity.this, "Gagal Menghubungi Server | "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}