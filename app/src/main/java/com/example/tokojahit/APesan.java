package com.example.tokojahit;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tokojahit.Adapter.AdapterDataKain;
import com.example.tokojahit.Adapter.SessionManager;
import com.example.tokojahit.Api.ApiClient;
import com.example.tokojahit.Api.ApiInterface;
import com.example.tokojahit.Model.Kain.GetKain;
import com.example.tokojahit.Model.Kain.Kain;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class APesan extends AppCompatActivity {
    TextView edtNameBaju, edtPrice;
    String ID;
    ApiInterface mApiInterface;
    SessionManager sessionManager;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    public static APesan ma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apesan);
        // Identifikasi Komponen Form
        edtNameBaju = (TextView) findViewById(R.id.edt_name_baju);
        edtPrice = (TextView) findViewById(R.id.edt_price);

        // Menyembunyikan TextView dari UI
        edtNameBaju.setVisibility(View.GONE);
        edtPrice.setVisibility(View.GONE);

        // Identifikasi intent ke Komponen Form
        Intent mIntent = getIntent();
        ID = mIntent.getStringExtra("Id");
        edtNameBaju.setText(mIntent.getStringExtra("NameBaju"));
        edtPrice.setText(mIntent.getStringExtra("Price"));

        // Definisi API
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);

        sessionManager = new SessionManager(APesan.this);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_heros);
        mLayoutManager = new LinearLayoutManager(this);
            mRecyclerView.setLayoutManager(mLayoutManager);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        ma = this;
        refresh();

        String level = sessionManager.getUserDetail().get(SessionManager.LEVEL);

        // Menyimpan nilai edtName ke dalam SharedPreferences
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("NameBaju", edtNameBaju.getText().toString());
        editor.putString("Price", edtPrice.getText().toString());
        editor.apply();
    }

    public void refresh() {
        Call<GetKain> HerosCall = mApiInterface.getKain();
        HerosCall.enqueue(new Callback<GetKain>() {
            @Override
            public void onResponse(Call<GetKain> call, Response<GetKain>
                    response) {
                List<Kain> HerosList = response.body().getListDataKain();
                Log.d("Retrofit Get", "Jumlah data Heros: " +
                        String.valueOf(HerosList.size()));
                mAdapter = new AdapterDataKain(HerosList);
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<GetKain> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
            }
        });
    }

}