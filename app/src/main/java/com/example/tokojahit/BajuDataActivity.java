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

import com.example.tokojahit.Adapter.AdapterDataBaju;
import com.example.tokojahit.Adapter.SessionManager;
import com.example.tokojahit.Api.ApiClient;
import com.example.tokojahit.Api.ApiInterface;
import com.example.tokojahit.Model.Baju.Baju;
import com.example.tokojahit.Model.Baju.GetBaju;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BajuDataActivity extends AppCompatActivity {

    ApiInterface mApiInterface;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    public static BajuDataActivity ma;
    private FloatingActionButton fabAdd;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baju_data);

        Intent intent = getIntent();
        String message = intent.getStringExtra("MESSAGE");

        if (message != null && !message.isEmpty()) {
            Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        }

        sessionManager = new SessionManager(BajuDataActivity.this);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_heros);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        ma = this;
        refresh();

        fabAdd = findViewById(R.id.fab_add);

        String level = sessionManager.getUserDetail().get(SessionManager.LEVEL);

        if (level.equals("admin")) {
            fabAdd.setVisibility(View.VISIBLE);
        } else {
            fabAdd.setVisibility(View.GONE);
        }
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BajuDataActivity.this, BajuTambahActivity.class));
            }
        });
    }

    public void refresh() {
        Call<GetBaju> BajuCall = mApiInterface.getBaju();
        BajuCall.enqueue(new Callback<GetBaju>() {
            @Override
            public void onResponse(Call<GetBaju> call, Response<GetBaju>
                    response) {
                List<Baju> BajuList = response.body().getListDataBaju();
                Log.d("Retrofit Get", "Jumlah data Heros: " +
                        String.valueOf(BajuList.size()));
                mAdapter = new AdapterDataBaju(BajuList);
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<GetBaju> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
            }
        });
    }

    private void moveToLogin() {
        Intent intent = new Intent(BajuDataActivity.this, LoginActivity.class);
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