package com.example.tokojahit;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tokojahit.Api.ApiClient;
import com.example.tokojahit.Api.ApiInterface;


public class ABCPesan extends AppCompatActivity {
    EditText etLingkarBadan, etLingkarPinggang, etPanjangDada, etLebarDada, etPanjangPunggung, etLebarPunggung, etLebarBahu, etLingkarLeher, etTinggiDada, etJarakDada, etLingkarPangkalLengan, etPanjangLengan, etLingkarSiku, etLingkarPergelanganTangan, etLingkarKerungLengan, etLingkarPanggul1, etLingkarPanggul2, etLingkarRok;
    TextView edtNameDesain;
    Button btnNext;
    String ID;
    ApiInterface mApiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abcpesan);

        // Identifikasi Komponen Form
        edtNameDesain = (TextView) findViewById(R.id.edt_name_desain);

        // Identifikasi intent ke Komponen Form
        Intent mIntent = getIntent();
        ID = mIntent.getStringExtra("Id");
        edtNameDesain.setText(mIntent.getStringExtra("NameDesain"));

        // Definisi API
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);

        // Menyimpan nilai edtName ke dalam SharedPreferences
        SharedPreferences sharedPreferences0 = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences0.edit();
        editor.putString("NameDesain", edtNameDesain.getText().toString());
        editor.apply();

        // Mengambil nilai 'Name' dari SharedPreferences
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String NameBaju = sharedPreferences.getString("NameBaju", "");

        // Mengambil nilai 'Name' dari SharedPreferences
        SharedPreferences sharedPreferences2 = PreferenceManager.getDefaultSharedPreferences(this);
        String Price = sharedPreferences2.getString("Price", "");

        // Mengambil nilai 'Name' dari SharedPreferences
        SharedPreferences sharedPreferences1 = PreferenceManager.getDefaultSharedPreferences(this);
        String NameKain = sharedPreferences1.getString("NameKain", "");

        // Gunakan nilai 'nameValue' sesuai kebutuhan
        // Misalnya, set nilai pada TextView
        TextView textView = findViewById(R.id.edt_name_baju);
        textView.setText(NameBaju);

        // Gunakan nilai 'nameValue' sesuai kebutuhan
        // Misalnya, set nilai pada TextView
        TextView textView2 = findViewById(R.id.edt_price);
        textView2.setText(Price);

        // Gunakan nilai 'nameValue' sesuai kebutuhan
        // Misalnya, set nilai pada TextView
        TextView textView1 = findViewById(R.id.edt_name_kain);
        textView1.setText(NameKain);

        // Menyembunyikan TextView dari UI
        textView.setVisibility(View.GONE);
        textView1.setVisibility(View.GONE);
        textView2.setVisibility(View.GONE);
        edtNameDesain.setVisibility(View.GONE);

        // Identifikasi EditText
        etLingkarBadan = findViewById(R.id.et_lingkar_badan);
        etLingkarPinggang = findViewById(R.id.et_lingkar_pinggang);
        etPanjangDada = findViewById(R.id.et_panjang_dada);
        etLebarDada = findViewById(R.id.et_lebar_dada);
        etPanjangPunggung = findViewById(R.id.et_panjang_punggung);
        etLebarPunggung = findViewById(R.id.et_lebar_punggung);
        etLebarBahu = findViewById(R.id.et_lebar_bahu);
        etLingkarLeher = findViewById(R.id.et_lingkar_leher);
        etTinggiDada = findViewById(R.id.et_tinggi_dada);
        etJarakDada = findViewById(R.id.et_jarak_dada);
        etLingkarPangkalLengan = findViewById(R.id.et_lingkar_pangkal_lengan);
        etPanjangLengan = findViewById(R.id.et_panjang_lengan);
        etLingkarSiku = findViewById(R.id.et_lingkar_siku);
        etLingkarPergelanganTangan = findViewById(R.id.et_lingkar_pergelangan_tangan);
        etLingkarKerungLengan = findViewById(R.id.et_lingkar_kerung_lengan);
        etLingkarPanggul1 = findViewById(R.id.et_lingkar_panggul_1);
        etLingkarPanggul2 = findViewById(R.id.et_lingkar_panggul_2);
        etLingkarRok = findViewById(R.id.et_lingkar_rok);

        // Identifikasi tombol
        btnNext = findViewById(R.id.btn_next);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Simpan data ke SharedPreferences
                saveDataToSharedPreferences();

                // Pindah ke Activity selanjutnya
                Intent intent = new Intent(ABCPesan.this, PesananTambahActivity.class);
                startActivity(intent);
            }
        });
    }

    private void saveDataToSharedPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences("UkuranPakaian", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // Simpan nilai dari setiap EditText ke SharedPreferences
        editor.putString("LingkarBadan", etLingkarBadan.getText().toString());
        editor.putString("LingkarPinggang", etLingkarPinggang.getText().toString());
        editor.putString("PanjangDada", etPanjangDada.getText().toString());
        editor.putString("LebarDada", etLebarDada.getText().toString());
        editor.putString("PanjangPunggung", etPanjangPunggung.getText().toString());
        editor.putString("LebarPunggung", etLebarPunggung.getText().toString());
        editor.putString("LebarBahu", etLebarBahu.getText().toString());
        editor.putString("LingkarLeher", etLingkarLeher.getText().toString());
        editor.putString("TinggiDada", etTinggiDada.getText().toString());
        editor.putString("JarakDada", etJarakDada.getText().toString());
        editor.putString("LingkarPangkalLengan", etLingkarPangkalLengan.getText().toString());
        editor.putString("PanjangLengan", etPanjangLengan.getText().toString());
        editor.putString("LingkarSiku", etLingkarSiku.getText().toString());
        editor.putString("LingkarPergelanganTangan", etLingkarPergelanganTangan.getText().toString());
        editor.putString("LingkarKerungLengan", etLingkarKerungLengan.getText().toString());
        editor.putString("LingkarPanggul1", etLingkarPanggul1.getText().toString());
        editor.putString("LingkarPanggul2", etLingkarPanggul2.getText().toString());
        editor.putString("LingkarRok", etLingkarRok.getText().toString());

        // Simpan perubahan
        editor.apply();
    }
}