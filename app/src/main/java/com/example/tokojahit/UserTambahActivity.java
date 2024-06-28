package com.example.tokojahit;

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

public class UserTambahActivity extends AppCompatActivity {
    private EditText etName, etUsername, etLevel, etPassword, etEmail, etNowa;
    private Button btnSimpan;
    private String name, username, level,password, email, nowa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_tambah);

        etName = findViewById(R.id.et_name);
        etUsername = findViewById(R.id.et_username);
        etLevel = findViewById(R.id.et_level);
        etPassword = findViewById(R.id.et_password);
        etEmail = findViewById(R.id.et_email);
        etNowa = findViewById(R.id.et_nowa);
        btnSimpan = findViewById(R.id.btn_simpan);

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = etName.getText().toString();
                username = etUsername.getText().toString();
                level = etLevel.getText().toString();
                password = etPassword.getText().toString();
                email = etEmail.getText().toString();
                nowa = etNowa.getText().toString();

                if(name.trim().equals("")){
                    etName.setError("Name Harus Diisi");
                }
                else if(username.trim().equals("")){
                    etUsername.setError("Username Harus Diisi");
                }
                else if(level.trim().equals("")){
                    etLevel.setError("Level Harus Diisi");
                }
                else if(password.trim().equals("")){
                    etPassword.setError("Password Harus Diisi");
                } else if(email.trim().equals("")){
                    etEmail.setError("Password Harus Diisi");
                } else if(nowa.trim().equals("")){
                    etNowa.setError("Password Harus Diisi");
                }
                else{
                    createData();
                }
            }
        });
    }

    private void createData(){
        ApiInterface ardData = ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseModel> simpanData = ardData.ardCreateData(name, username, level, password, email, nowa);

        simpanData.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                int kode = response.body().getKode();
                String pesan = response.body().getPesan();

                Toast.makeText(UserTambahActivity.this, "Kode : "+kode+" | Pesan : "+pesan, Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(UserTambahActivity.this, "Gagal Menghubungi Server | "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
