package com.example.tokojahit;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tokojahit.Adapter.AdapterDataDesain;
import com.example.tokojahit.Adapter.SessionManager;
import com.example.tokojahit.Api.ApiClient;
import com.example.tokojahit.Api.ApiInterface;
import com.example.tokojahit.Model.Desain.Desain;
import com.example.tokojahit.Model.Desain.GetDesain;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DesainDataActivity extends AppCompatActivity {

    ApiInterface mApiInterface;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    public static DesainDataActivity ma;
    private FloatingActionButton fabAdd;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desain_data);
        sessionManager = new SessionManager(DesainDataActivity.this);
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
                startActivity(new Intent(DesainDataActivity.this, DesainTambahActivity.class));
            }
        });
    }

    public void refresh() {
        Call<GetDesain> DesainCall = mApiInterface.getDesain();
        DesainCall.enqueue(new Callback<GetDesain>() {
            @Override
            public void onResponse(Call<GetDesain> call, Response<GetDesain>
                    response) {
                List<Desain> DesainList = response.body().getListDataDesain();
                Log.d("Retrofit Get", "Jumlah data Heros: " +
                        String.valueOf(DesainList.size()));
                mAdapter = new AdapterDataDesain(DesainList);
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<GetDesain> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
            }
        });
    }

    private void moveToLogin() {
        Intent intent = new Intent(DesainDataActivity.this, LoginActivity.class);
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