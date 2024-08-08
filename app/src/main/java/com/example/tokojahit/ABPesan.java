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

import com.example.tokojahit.Adapter.AdapterDataDesain;
import com.example.tokojahit.Adapter.SessionManager;
import com.example.tokojahit.Api.ApiClient;
import com.example.tokojahit.Api.ApiInterface;
import com.example.tokojahit.Model.Desain.Desain;
import com.example.tokojahit.Model.Desain.GetDesain;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ABPesan extends AppCompatActivity {
    TextView edtNameKain, edtPriceKain, edtWarnaKain;
    String ID;
    ApiInterface mApiInterface;
    SessionManager sessionManager;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    public static ABPesan ma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abpesan);



        // Identifikasi Komponen Form
        edtNameKain = (TextView) findViewById(R.id.edt_name_kain);
        edtPriceKain = (TextView) findViewById(R.id.edt_price_kain);
        edtWarnaKain = (TextView) findViewById(R.id.edt_warna_kain);


        // Identifikasi intent ke Komponen Form
        Intent mIntent = getIntent();
        ID = mIntent.getStringExtra("Id");
        edtNameKain.setText(mIntent.getStringExtra("NameKain"));
        edtPriceKain.setText(mIntent.getStringExtra("PriceKain"));
        edtWarnaKain.setText(mIntent.getStringExtra("Warna"));


        // Definisi API
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);

        sessionManager = new SessionManager(ABPesan.this);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_heros);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        ma = this;
        refresh();

        String level = sessionManager.getUserDetail().get(SessionManager.LEVEL);

        // Menyimpan nilai edtName ke dalam SharedPreferences
        SharedPreferences sharedPreferences1 = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences1.edit();
        editor.putString("NameKain", edtNameKain.getText().toString());
        editor.putString("PriceKain", edtPriceKain.getText().toString());
        editor.putString("Warna", edtWarnaKain.getText().toString());
        editor.apply();



        // Mengambil nilai 'Name' dari SharedPreferences
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String NameBaju = sharedPreferences.getString("NameBaju", "");
        String Price = sharedPreferences.getString("Price", "");

        // Gunakan nilai 'nameValue' sesuai kebutuhan
        // Misalnya, set nilai pada TextView
        TextView textView = findViewById(R.id.edt_name_baju);
        textView.setText(NameBaju);
        TextView textView1 = findViewById(R.id.edt_price);
        textView1.setText(Price);

        // Menyembunyikan TextView dari UI
        textView.setVisibility(View.GONE);
        textView1.setVisibility(View.GONE);
        edtNameKain.setVisibility(View.GONE);
        edtPriceKain.setVisibility(View.GONE);
        edtWarnaKain.setVisibility(View.GONE);
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
}