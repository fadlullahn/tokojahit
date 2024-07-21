package com.example.tokojahit;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tokojahit.Adapter.SessionManager;
import com.example.tokojahit.Api.ApiClient;
import com.example.tokojahit.Api.ApiInterface;
import com.example.tokojahit.Model.Pesanan.PostPutDelPesanan;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PesananTambahActivity extends AppCompatActivity {
    TextView tvProses,tvNameBaju,tvPrice, tvNameKain, tvNameDesain,tvLingkarBadan, tvLingkarPinggang, tvPanjangDada, tvLebarDada, tvPanjangPunggung, tvLebarPunggung, tvLebarBahu, tvLingkarLeher, tvTinggiDada, tvJarakDada, tvLingkarPangkalLengan, tvPanjangLengan, tvLingkarSiku, tvLingkarPergelanganTangan, tvLingkarKerungLengan, tvLingkarPanggul1, tvLingkarPanggul2, tvLingkarRok;
    EditText etLingkarBadan, etLingkarPinggang, etPanjangDada, etLebarDada, etPanjangPunggung, etLebarPunggung, etLebarBahu, etLingkarLeher, etTinggiDada, etJarakDada, etLingkarPangkalLengan, etPanjangLengan, etLingkarSiku, etLingkarPergelanganTangan, etLingkarKerungLengan, etLingkarPanggul1, etLingkarPanggul2, etLingkarRok, etProses;
    EditText edtName, edtPrice;
    Button btnGalery, btSubmit;
    ImageView imgHolder;
    private String mediaPath;
    private String postPath;
    ApiInterface mApiInterface;
    private static final int REQUEST_PICK_PHOTO = Config.REQUEST_PICK_PHOTO;
    private static final int REQUEST_WRITE_PERMISSION = Config.REQUEST_WRITE_PERMISSION;
    private static final String INSERT_FLAG = Config.INSERT_FLAG;
    SessionManager sessionManager;

    // Akses Izin Ambil Gambar dari Storage
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == REQUEST_WRITE_PERMISSION && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            saveImageUpload();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesanan_tambah);
        sessionManager = new SessionManager(PesananTambahActivity.this);
        String nameU = sessionManager.getUserDetail().get(SessionManager.NAME);

        SharedPreferences sharedPreferences0 = PreferenceManager.getDefaultSharedPreferences(this);
        String NameBaju = sharedPreferences0.getString("NameBaju", "");
        SharedPreferences sharedPreferences3 = PreferenceManager.getDefaultSharedPreferences(this);
        String Price = sharedPreferences3.getString("Price", "");
        SharedPreferences sharedPreferences1 = PreferenceManager.getDefaultSharedPreferences(this);
        String NameKain = sharedPreferences1.getString("NameKain", "");
        SharedPreferences sharedPreferences2 = PreferenceManager.getDefaultSharedPreferences(this);
        String NameDesain = sharedPreferences2.getString("NameDesain", "");

        tvNameBaju = findViewById(R.id.tv_name_baju);
        tvNameBaju.setText(NameBaju);
        tvPrice = findViewById(R.id.tv_price);
        tvPrice.setText(Price);
        tvNameKain = findViewById(R.id.tv_name_kain);
        tvNameKain.setText(NameKain);
        tvNameDesain = findViewById(R.id.tv_name_desain);
        tvNameDesain.setText(NameDesain);

        // Identifikasi TextView
        tvLingkarBadan = findViewById(R.id.tv_lingkar_badan);
        tvLingkarPinggang = findViewById(R.id.tv_lingkar_pinggang);
        tvPanjangDada = findViewById(R.id.tv_panjang_dada);
        tvLebarDada = findViewById(R.id.tv_lebar_dada);
        tvPanjangPunggung = findViewById(R.id.tv_panjang_punggung);
        tvLebarPunggung = findViewById(R.id.tv_lebar_punggung);
        tvLebarBahu = findViewById(R.id.tv_lebar_bahu);
        tvLingkarLeher = findViewById(R.id.tv_lingkar_leher);
        tvTinggiDada = findViewById(R.id.tv_tinggi_dada);
        tvJarakDada = findViewById(R.id.tv_jarak_dada);
        tvLingkarPangkalLengan = findViewById(R.id.tv_lingkar_pangkal_lengan);
        tvPanjangLengan = findViewById(R.id.tv_panjang_lengan);
        tvLingkarSiku = findViewById(R.id.tv_lingkar_siku);
        tvLingkarPergelanganTangan = findViewById(R.id.tv_lingkar_pergelangan_tangan);
        tvLingkarKerungLengan = findViewById(R.id.tv_lingkar_kerung_lengan);
        tvLingkarPanggul1 = findViewById(R.id.tv_lingkar_panggul_1);
        tvLingkarPanggul2 = findViewById(R.id.tv_lingkar_panggul_2);
        tvLingkarRok = findViewById(R.id.tv_lingkar_rok);

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
        etProses = findViewById(R.id.et_proses);

        // Ambil data dari SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("UkuranPakaian", MODE_PRIVATE);

        tvLingkarBadan.setText(sharedPreferences.getString("LingkarBadan", "0"));
        tvLingkarPinggang.setText(sharedPreferences.getString("LingkarPinggang", "0"));
        tvPanjangDada.setText(sharedPreferences.getString("PanjangDada", "0"));
        tvLebarDada.setText(sharedPreferences.getString("LebarDada", "0"));
        tvPanjangPunggung.setText(sharedPreferences.getString("PanjangPunggung", "0"));
        tvLebarPunggung.setText(sharedPreferences.getString("LebarPunggung", "0"));
        tvLebarBahu.setText(sharedPreferences.getString("LebarBahu", "0"));
        tvLingkarLeher.setText(sharedPreferences.getString("LingkarLeher", "0"));
        tvTinggiDada.setText(sharedPreferences.getString("TinggiDada", "0"));
        tvJarakDada.setText(sharedPreferences.getString("JarakDada", "0"));
        tvLingkarPangkalLengan.setText(sharedPreferences.getString("LingkarPangkalLengan", "0"));
        tvPanjangLengan.setText(sharedPreferences.getString("PanjangLengan", "0"));
        tvLingkarSiku.setText(sharedPreferences.getString("LingkarSiku", "0"));
        tvLingkarPergelanganTangan.setText(sharedPreferences.getString("LingkarPergelanganTangan", "0"));
        tvLingkarKerungLengan.setText(sharedPreferences.getString("LingkarKerungLengan", "0"));
        tvLingkarPanggul1.setText(sharedPreferences.getString("LingkarPanggul1", "0"));
        tvLingkarPanggul2.setText(sharedPreferences.getString("LingkarPanggul2", "0"));
        tvLingkarRok.setText(sharedPreferences.getString("LingkarRok", "0"));

        etLingkarBadan.setText(sharedPreferences.getString("LingkarBadan", "0"));
        etLingkarPinggang.setText(sharedPreferences.getString("LingkarPinggang", "0"));
        etPanjangDada.setText(sharedPreferences.getString("PanjangDada", "0"));
        etLebarDada.setText(sharedPreferences.getString("LebarDada", "0"));
        etPanjangPunggung.setText(sharedPreferences.getString("PanjangPunggung", "0"));
        etLebarPunggung.setText(sharedPreferences.getString("LebarPunggung", "0"));
        etLebarBahu.setText(sharedPreferences.getString("LebarBahu", "0"));
        etLingkarLeher.setText(sharedPreferences.getString("LingkarLeher", "0"));
        etTinggiDada.setText(sharedPreferences.getString("TinggiDada", "0"));
        etJarakDada.setText(sharedPreferences.getString("JarakDada", "0"));
        etLingkarPangkalLengan.setText(sharedPreferences.getString("LingkarPangkalLengan", "0"));
        etPanjangLengan.setText(sharedPreferences.getString("PanjangLengan", "0"));
        etLingkarSiku.setText(sharedPreferences.getString("LingkarSiku", "0"));
        etLingkarPergelanganTangan.setText(sharedPreferences.getString("LingkarPergelanganTangan", "0"));
        etLingkarKerungLengan.setText(sharedPreferences.getString("LingkarKerungLengan", "0"));
        etLingkarPanggul1.setText(sharedPreferences.getString("LingkarPanggul1", "0"));
        etLingkarPanggul2.setText(sharedPreferences.getString("LingkarPanggul2", "0"));
        etLingkarRok.setText(sharedPreferences.getString("LingkarRok", "0"));
        etProses.setText(sharedPreferences.getString("Proses", "0"));
//         Menyembunyikan TextView dari UI
        etLingkarBadan.setVisibility(View.GONE);
        etLingkarPinggang.setVisibility(View.GONE);
        etPanjangDada.setVisibility(View.GONE);
        etLebarDada.setVisibility(View.GONE);
        etPanjangPunggung.setVisibility(View.GONE);
        etLebarPunggung.setVisibility(View.GONE);
        etLebarBahu.setVisibility(View.GONE);
        etLingkarLeher.setVisibility(View.GONE);
        etTinggiDada.setVisibility(View.GONE);
        etJarakDada.setVisibility(View.GONE);
        etLingkarPangkalLengan.setVisibility(View.GONE);
        etPanjangLengan.setVisibility(View.GONE);
        etLingkarSiku.setVisibility(View.GONE);
        etLingkarPergelanganTangan.setVisibility(View.GONE);
        etLingkarKerungLengan.setVisibility(View.GONE);
        etLingkarPanggul1.setVisibility(View.GONE);
        etLingkarPanggul2.setVisibility(View.GONE);
        etLingkarRok.setVisibility(View.GONE);
        etProses.setVisibility(View.GONE);

        // Identifikasi Komponen Form
        edtName = (EditText) findViewById(R.id.edt_name);
        edtName.setText(nameU);
        edtPrice = (EditText) findViewById(R.id.edt_price);
        edtPrice.setText(Price);
        // Menyembunyikan TextView dari UI
        edtName.setVisibility(View.GONE);
        edtPrice.setVisibility(View.GONE);

        imgHolder = (ImageView) findViewById(R.id.imgHolder);
        btnGalery = (Button) findViewById(R.id.btn_galery);
        btSubmit = (Button) findViewById(R.id.btn_submit);

        // Definisi API
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);

        // Fungsi Tombol Pilih Galery
        btnGalery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, REQUEST_PICK_PHOTO);
            }
        });

        // Fungsi Tombol Simpan
        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestPermission();
            }
        });
    }




    // Akses Izin Ambil Gambar dari Storage
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_PICK_PHOTO) {
                if (data != null) {
                    // Ambil Image Dari Galeri dan Foto
                    Uri selectedImage = data.getData();
                    String[] filePathColumn = {MediaStore.Images.Media.DATA};

                    Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                    assert cursor != null;
                    cursor.moveToFirst();

                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    mediaPath = cursor.getString(columnIndex);
                    imgHolder.setImageURI(data.getData());
                    cursor.close();

                    postPath = mediaPath;
                }
            }
        }
    }

    // Simpan Gambar
    private void saveImageUpload() {
        final String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

        if (mediaPath == null) {
            Toast.makeText(getApplicationContext(), "Pilih Gambar Terlebih Dahulu", Toast.LENGTH_LONG).show();
        } else {
            File imagefile = new File(mediaPath);
            RequestBody reqBody = RequestBody.create(MediaType.parse("multipart/form-file"), imagefile);
            MultipartBody.Part partImage = MultipartBody.Part.createFormData("image", imagefile.getName(), reqBody);

            Call<PostPutDelPesanan> postPesananCall = mApiInterface.postPesanan(
                    partImage,
                    RequestBody.create(MediaType.parse("text/plain"), edtName.getText().toString()),
                    RequestBody.create(MediaType.parse("text/plain"), edtPrice.getText().toString()),
                    RequestBody.create(MediaType.parse("text/plain"), date),
                    RequestBody.create(MediaType.parse("text/plain"), INSERT_FLAG),
                    RequestBody.create(MediaType.parse("text/plain"), tvNameBaju.getText().toString()),
                    RequestBody.create(MediaType.parse("text/plain"), tvNameKain.getText().toString()),
                    RequestBody.create(MediaType.parse("text/plain"), tvNameDesain.getText().toString()),
                    RequestBody.create(MediaType.parse("text/plain"), etLingkarBadan.getText().toString()),
                    RequestBody.create(MediaType.parse("text/plain"), etLingkarPinggang.getText().toString()),
                    RequestBody.create(MediaType.parse("text/plain"), etPanjangDada.getText().toString()),
                    RequestBody.create(MediaType.parse("text/plain"), etLebarDada.getText().toString()),
                    RequestBody.create(MediaType.parse("text/plain"), etPanjangPunggung.getText().toString()),
                    RequestBody.create(MediaType.parse("text/plain"), etLebarPunggung.getText().toString()),
                    RequestBody.create(MediaType.parse("text/plain"), etLebarBahu.getText().toString()),
                    RequestBody.create(MediaType.parse("text/plain"), etLingkarLeher.getText().toString()),
                    RequestBody.create(MediaType.parse("text/plain"), etTinggiDada.getText().toString()),
                    RequestBody.create(MediaType.parse("text/plain"), etJarakDada.getText().toString()),
                    RequestBody.create(MediaType.parse("text/plain"), etLingkarPangkalLengan.getText().toString()),
                    RequestBody.create(MediaType.parse("text/plain"), etPanjangLengan.getText().toString()),
                    RequestBody.create(MediaType.parse("text/plain"), etLingkarSiku.getText().toString()),
                    RequestBody.create(MediaType.parse("text/plain"), etLingkarPergelanganTangan.getText().toString()),
                    RequestBody.create(MediaType.parse("text/plain"), etLingkarKerungLengan.getText().toString()),
                    RequestBody.create(MediaType.parse("text/plain"), etLingkarPanggul1.getText().toString()),
                    RequestBody.create(MediaType.parse("text/plain"), etLingkarPanggul2.getText().toString()),
                    RequestBody.create(MediaType.parse("text/plain"), etLingkarRok.getText().toString()),
                    RequestBody.create(MediaType.parse("text/plain"), etProses.getText().toString())
            );

            // Tambahkan logging pada response dari server
            postPesananCall.enqueue(new Callback<PostPutDelPesanan>() {
                @Override
                public void onFailure(Call<PostPutDelPesanan> call, Throwable t) {
                    Log.d("RETRO", "ON FAILURE : " + t.getMessage());
                    Toast.makeText(getApplicationContext(), "Error, image", Toast.LENGTH_LONG).show();
                }
                @Override
                public void onResponse(Call<PostPutDelPesanan> call, Response<PostPutDelPesanan> response) {
                    Intent intent = new Intent(PesananTambahActivity.this, BajuDataActivity.class);
                    intent.putExtra("MESSAGE", "Terima kasih telah melakukan pembayaran");
                    startActivity(intent);
                    finish();
                }
            });


        }
    }



    private void requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            requestPermissions(new String[]{Manifest.permission.READ_MEDIA_IMAGES, android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_WRITE_PERMISSION);
        } else {
            saveImageUpload();
        }
    }

}