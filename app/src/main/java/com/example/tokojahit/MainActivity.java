package com.example.tokojahit;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.tokojahit.Adapter.SessionManager;
import com.example.tokojahit.Api.ApiClient;
import com.example.tokojahit.Api.ApiInterface;
import com.example.tokojahit.Model.Pesanan.GetPesanan;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private Button button3, button4, button5, button6, button7;
    SessionManager sessionManager;
    String level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sessionManager = new SessionManager(MainActivity.this);
        if (!sessionManager.isLoggedIn()) {
            moveToLogin();
        } else {
            level = sessionManager.getUserDetail().get(SessionManager.LEVEL);

            if ("admin".equals(level)) {
            } else if ("user".equals(level)) {
                Intent intent = new Intent(MainActivity.this, UseLanActivity.class);
                startActivity(intent);
                finish();
            }
        }

        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);
        button7 = findViewById(R.id.button7);

        level = sessionManager.getUserDetail().get(SessionManager.LEVEL);

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, DesainDataActivity.class));
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, BajuDataActivity.class));
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, KainDataActivity.class));
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, UserDataActivity.class));
            }
        });

        fetchPesananData();

        // Tambahkan ini di dalam onCreate() atau metode yang sesuai
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ambil data pesanan dan tampilkan jumlah di button7

                // Pindah ke activity berikutnya setelah data diambil
                Intent intent = new Intent(MainActivity.this, PesananDataActivity.class);
                startActivity(intent);
            }
        });
    }

    private void fetchPesananData() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

        Call<GetPesanan> call = apiInterface.getPesanan();
        call.enqueue(new Callback<GetPesanan>() {
            @Override
            public void onResponse(Call<GetPesanan> call, Response<GetPesanan> response) {
                if (response.isSuccessful() && response.body() != null) {
                    GetPesanan getPesanan = response.body();
                    int jumlahPesanan = getPesanan.getListDataPesanan() != null ? getPesanan.getListDataPesanan().size() : 0;

                    // Set teks button dengan jumlah pesanan
                    button7.setText("Konfirmasi Pesanan: " + jumlahPesanan);
                } else {
                    // Tangani kasus jika response tidak berhasil
                    button7.setText("Konfirmasi Pesanan: 0");
                }
            }

            @Override
            public void onFailure(Call<GetPesanan> call, Throwable t) {
                button7.setText("Konfirmasi Pesanan: 0");
                Toast.makeText(MainActivity.this, "Gagal memuat data pesanan", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void moveToLogin() {
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_admin, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.actionLogout) {
            sessionManager.logoutSession();
            moveToLogin();
            return true;
        } else if (item.getItemId() == R.id.actionProfil) {
            Intent intent = new Intent(this, ProfilActivity.class);
            startActivity(intent);
            return true;
        } else if (item.getItemId() == R.id.actionTutorial) {
            Intent intent = new Intent(this, TutorialActivity.class);
            startActivity(intent);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}