package com.example.tokojahit;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tokojahit.Api.ApiClient;
import com.example.tokojahit.Api.ApiInterface;

public class ABCPesan extends AppCompatActivity {
    EditText etLingkarBadan, etLingkarPinggang, etPanjangDada, etLebarDada, etPanjangPunggung, etLebarPunggung, etLebarBahu, etLingkarLeher, etTinggiDada, etJarakDada, etLingkarPangkalLengan, etPanjangLengan, etLingkarSiku, etLingkarPergelanganTangan, etLingkarKerungLengan, etLingkarPanggul1, etLingkarPanggul2, etLingkarRok;
    TextView edtNameDesain, edtPriceDesain, edtWarnaKain;
    Button btnNext;
    Spinner spinnerUkuran;
    String ID;
    ApiInterface mApiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abcpesan);

        // Identifikasi Komponen Form
        spinnerUkuran = findViewById(R.id.spinner_ukuran);
        edtNameDesain = findViewById(R.id.edt_name_desain);
        edtPriceDesain = findViewById(R.id.edt_price_desain);
        edtWarnaKain = findViewById(R.id.edt_warna_kain);

        // Inisialisasi Spinner dengan pilihan ukuran
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.ukuran_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerUkuran.setAdapter(adapter);

        // Set listener untuk Spinner
        spinnerUkuran.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String ukuran = parentView.getItemAtPosition(position).toString();
                setUkuran(ukuran);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing
            }
        });

        // Identifikasi intent ke Komponen Form
        Intent mIntent = getIntent();
        ID = mIntent.getStringExtra("Id");
        edtNameDesain.setText(mIntent.getStringExtra("NameDesain"));
        edtPriceDesain.setText(mIntent.getStringExtra("PriceDesain"));

        SharedPreferences sharedPreferences4 = PreferenceManager.getDefaultSharedPreferences(this);
        String WarnaKain = sharedPreferences4.getString("Warna", "");

        // Gunakan nilai 'nameValue' sesuai kebutuhan
        edtWarnaKain.setText(WarnaKain);

        // Definisi API
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);

        // Menyimpan nilai edtName ke dalam SharedPreferences
        SharedPreferences sharedPreferences0 = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences0.edit();
        editor.putString("NameDesain", edtNameDesain.getText().toString());
        editor.putString("PriceDesain", edtPriceDesain.getText().toString());
        editor.putString("Warna", edtWarnaKain.getText().toString());
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

        SharedPreferences sharedPreferences3 = PreferenceManager.getDefaultSharedPreferences(this);
        String PriceKain = sharedPreferences3.getString("PriceKain", "");

        // Gunakan nilai 'nameValue' sesuai kebutuhan
        TextView textView = findViewById(R.id.edt_name_baju);
        textView.setText(NameBaju);

        // Gunakan nilai 'nameValue' sesuai kebutuhan
        TextView textView2 = findViewById(R.id.edt_price);
        textView2.setText(Price);

        // Gunakan nilai 'nameValue' sesuai kebutuhan
        TextView textView1 = findViewById(R.id.edt_name_kain);
        textView1.setText(NameKain);

        TextView textView3 = findViewById(R.id.edt_price_kain);
        textView3.setText(PriceKain);

        // Menyembunyikan TextView dari UI
        textView.setVisibility(View.GONE);
        textView1.setVisibility(View.GONE);
        textView2.setVisibility(View.GONE);
        edtNameDesain.setVisibility(View.GONE);
        edtPriceDesain.setVisibility(View.GONE);
        textView3.setVisibility(View.GONE);
        edtWarnaKain.setVisibility(View.GONE);

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

    private void setUkuran(String ukuran) {
        if (ukuran.equals("S")) {
            etLingkarBadan.setText("88");
            etLingkarPinggang.setText("62");
            etPanjangDada.setText("36");
            etLebarDada.setText("32");
            etPanjangPunggung.setText("36");
            etLebarPunggung.setText("19");
            etLebarBahu.setText("12");
            etLingkarLeher.setText("25");
            etTinggiDada.setText("17");
            etJarakDada.setText("15");
            etLingkarPangkalLengan.setText("24");
            etPanjangLengan.setText("50");
            etLingkarSiku.setText("23");
            etLingkarPergelanganTangan.setText("21");
            etLingkarKerungLengan.setText("12");
            etLingkarPanggul1.setText("84");
            etLingkarPanggul2.setText("85");
            etLingkarRok.setText("60");
        } else if (ukuran.equals("M")) {
            etLingkarBadan.setText("90");
            etLingkarPinggang.setText("64");
            etPanjangDada.setText("37");
            etLebarDada.setText("35");
            etPanjangPunggung.setText("37");
            etLebarPunggung.setText("21");
            etLebarBahu.setText("12");
            etLingkarLeher.setText("26");
            etTinggiDada.setText("18");
            etJarakDada.setText("16");
            etLingkarPangkalLengan.setText("25");
            etPanjangLengan.setText("52");
            etLingkarSiku.setText("25");
            etLingkarPergelanganTangan.setText("22");
            etLingkarKerungLengan.setText("14");
            etLingkarPanggul1.setText("86");
            etLingkarPanggul2.setText("87");
            etLingkarRok.setText("65");
        } else if (ukuran.equals("L")) {
            etLingkarBadan.setText("94");
            etLingkarPinggang.setText("72");
            etPanjangDada.setText("38");
            etLebarDada.setText("37");
            etPanjangPunggung.setText("38");
            etLebarPunggung.setText("25");
            etLebarBahu.setText("13");
            etLingkarLeher.setText("27");
            etTinggiDada.setText("19");
            etJarakDada.setText("17");
            etLingkarPangkalLengan.setText("26");
            etPanjangLengan.setText("53");
            etLingkarSiku.setText("27");
            etLingkarPergelanganTangan.setText("22");
            etLingkarKerungLengan.setText("15");
            etLingkarPanggul1.setText("87");
            etLingkarPanggul2.setText("89");
            etLingkarRok.setText("68");
        } else if (ukuran.equals("XL")) {
            etLingkarBadan.setText("100");
            etLingkarPinggang.setText("80");
            etPanjangDada.setText("39");
            etLebarDada.setText("38");
            etPanjangPunggung.setText("39");
            etLebarPunggung.setText("26");
            etLebarBahu.setText("13");
            etLingkarLeher.setText("27");
            etTinggiDada.setText("20");
            etJarakDada.setText("18");
            etLingkarPangkalLengan.setText("28");
            etPanjangLengan.setText("54");
            etLingkarSiku.setText("27");
            etLingkarPergelanganTangan.setText("23");
            etLingkarKerungLengan.setText("15");
            etLingkarPanggul1.setText("90");
            etLingkarPanggul2.setText("92");
            etLingkarRok.setText("72");
        } else if (ukuran.equals("XXL")) {
            etLingkarBadan.setText("101");
            etLingkarPinggang.setText("81");
            etPanjangDada.setText("40");
            etLebarDada.setText("40");
            etPanjangPunggung.setText("40");
            etLebarPunggung.setText("28");
            etLebarBahu.setText("14");
            etLingkarLeher.setText("28");
            etTinggiDada.setText("22");
            etJarakDada.setText("19");
            etLingkarPangkalLengan.setText("30");
            etPanjangLengan.setText("58");
            etLingkarSiku.setText("29");
            etLingkarPergelanganTangan.setText("23");
            etLingkarKerungLengan.setText("17");
            etLingkarPanggul1.setText("91");
            etLingkarPanggul2.setText("92");
            etLingkarRok.setText("76");
        }
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
