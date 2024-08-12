package com.example.tokojahit;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tokojahit.Adapter.AdapterDataPesanan;
import com.example.tokojahit.Adapter.SessionManager;
import com.example.tokojahit.Api.ApiClient;
import com.example.tokojahit.Api.ApiInterface;
import com.example.tokojahit.Model.Pesanan.GetPesanan;
import com.example.tokojahit.Model.Pesanan.Pesanan;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PesananDataActivity extends AppCompatActivity {
    ApiInterface mApiInterface;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    public static PesananDataActivity ma;
    private FloatingActionButton fabAdd;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesanan_data);
        sessionManager = new SessionManager(PesananDataActivity.this);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_heros);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        ma = this;
        refresh();

        // Menangkap intent dan mengambil data
        Intent intent = getIntent();
        String pesan = intent.getStringExtra("pesan");

        // Tampilkan pesan dengan Toast
        if (pesan != null && !pesan.isEmpty()) {
            Toast.makeText(this, pesan, Toast.LENGTH_SHORT).show();
        }

        fabAdd = findViewById(R.id.fab_add);
        fabAdd.setVisibility(View.GONE);
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PesananDataActivity.this, PesananTambahActivity.class));
            }
        });
    }

    public void refresh() {
        // Ambil username dan peran dari SessionManager
        String username = sessionManager.getUserDetail().get(SessionManager.USERNAME);
        String role = sessionManager.getUserDetail().get(SessionManager.LEVEL); // Misalkan role adalah kunci di SessionManager

        Call<GetPesanan> call = mApiInterface.getPesanan();
        call.enqueue(new Callback<GetPesanan>() {
            @Override
            public void onResponse(Call<GetPesanan> call, Response<GetPesanan> response) {
                if (response.isSuccessful()) {
                    List<Pesanan> pesananList = response.body().getListDataPesanan();

                    // Filter data jika pengguna adalah user
                    List<Pesanan> filteredList = new ArrayList<>();
                    if ("user".equalsIgnoreCase(role)) {
                        for (Pesanan pesanan : pesananList) {
                            if (pesanan.getName() != null && pesanan.getName().equalsIgnoreCase(username)) {
                                filteredList.add(pesanan);
                            }
                        }
                    } else {
                        // Tampilkan semua data jika pengguna bukan user
                        filteredList = pesananList;
                    }

                    Log.d("Retrofit Get", "Jumlah data Pesanan setelah filter: " + filteredList.size());
                    mAdapter = new AdapterDataPesanan(filteredList);
                    mRecyclerView.setAdapter(mAdapter);
                } else {
                    Log.e("Retrofit Get", "Response error: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<GetPesanan> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
            }
        });
    }



    private void moveToLogin() {
        Intent intent = new Intent(PesananDataActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        String level = sessionManager.getUserDetail().get(SessionManager.LEVEL);
        getMenuInflater().inflate(R.menu.menu_admin, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.actionLogout) {
            sessionManager.logoutSession();
            moveToLogin();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}