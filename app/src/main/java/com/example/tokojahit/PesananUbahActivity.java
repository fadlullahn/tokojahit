package com.example.tokojahit;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.tokojahit.Api.ApiClient;
import com.example.tokojahit.Api.ApiInterface;
import com.example.tokojahit.Model.Pesanan.PostPutDelPesanan;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PesananUbahActivity extends AppCompatActivity {
    EditText etProses;
    TextView tvName, tvPrice, tvBaju, tvKain, tvDesain,tvLingkarBadan, tvLingkarPinggang, tvPanjangDada, tvLebarDada, tvPanjangPunggung, tvLebarPunggung, tvLebarBahu, tvLingkarLeher, tvTinggiDada, tvJarakDada, tvLingkarPangkalLengan, tvPanjangLengan, tvLingkarSiku, tvLingkarPergelanganTangan, tvLingkarKerungLengan, tvLingkarPanggul1, tvLingkarPanggul2, tvLingkarRok,tvWarna, tvUkuran;
    ImageView imgHolder;
    String ID;
    Button btUpdate;
    ApiInterface mApiInterface;
    private final int ALERT_DIALOG_CLOSE = Config.ALERT_DIALOG_CLOSE;
    private final int ALERT_DIALOG_DELETE = Config.ALERT_DIALOG_DELETE;
    private static final String UPDATE_FLAG = Config.UPDATE_FLAG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesanan_ubah);

        // Identifikasi Komponen Form
        tvName = (TextView) findViewById(R.id.tv_name);
        tvPrice = (TextView) findViewById(R.id.tv_price);
        tvBaju = (TextView) findViewById(R.id.tv_baju);
        tvKain = (TextView) findViewById(R.id.tv_kain);
        tvDesain = (TextView) findViewById(R.id.tv_desain);
        imgHolder = (ImageView) findViewById(R.id.imgHolder);
        tvWarna = (TextView) findViewById(R.id.tv_warna);
        tvUkuran = (TextView) findViewById(R.id.tv_ukuran_pakaian);

        btUpdate = (Button) findViewById(R.id.btn_submit);

        etProses = (EditText) findViewById(R.id.et_proses);
        etProses.setVisibility(View.GONE);

        // Identifikasi intent ke Komponen Form
        Intent mIntent = getIntent();
        ID = mIntent.getStringExtra("Id");
        tvName.setText(mIntent.getStringExtra("Name"));
        tvPrice.setText(mIntent.getStringExtra("Price"));
        tvBaju.setText(mIntent.getStringExtra("Baju"));
        tvKain.setText(mIntent.getStringExtra("Kain"));
        tvDesain.setText(mIntent.getStringExtra("Desain"));

        // Ambil harga dari Intent
        String priceFromIntent = mIntent.getStringExtra("Price");

// Konversi harga dari String ke double
        double priceValue = priceFromIntent.isEmpty() ? 0 : Double.parseDouble(priceFromIntent);

// Format angka dengan pemisah ribuan
        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.getDefault());
        String formattedPrice = numberFormat.format(priceValue);

// Set TextView
        tvPrice.setText("Rp. " + formattedPrice);


        // Identifikasi TextView Ukuran Pakaian
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

        // Identifikasi Intent Ukuran Pakaian ke Komponen Form
        tvLingkarBadan.setText(mIntent.getStringExtra("LingkarBadan"));
        tvLingkarPinggang.setText(mIntent.getStringExtra("LingkarPinggang"));
        tvPanjangDada.setText(mIntent.getStringExtra("PanjangDada"));
        tvLebarDada.setText(mIntent.getStringExtra("LebarDada"));
        tvPanjangPunggung.setText(mIntent.getStringExtra("PanjangPunggung"));
        tvLebarPunggung.setText(mIntent.getStringExtra("LebarPunggung"));
        tvLebarBahu.setText(mIntent.getStringExtra("LebarBahu"));
        tvLingkarLeher.setText(mIntent.getStringExtra("LingkarLeher"));
        tvTinggiDada.setText(mIntent.getStringExtra("TinggiDada"));
        tvJarakDada.setText(mIntent.getStringExtra("JarakDada"));
        tvLingkarPangkalLengan.setText(mIntent.getStringExtra("LingkarPangkalLengan"));
        tvPanjangLengan.setText(mIntent.getStringExtra("PanjangLengan"));
        tvLingkarSiku.setText(mIntent.getStringExtra("LingkarSiku"));
        tvLingkarPergelanganTangan.setText(mIntent.getStringExtra("LingkarPergelanganTangan"));
        tvLingkarKerungLengan.setText(mIntent.getStringExtra("LingkarKerungLengan"));
        tvLingkarPanggul1.setText(mIntent.getStringExtra("LingkarPanggul1"));
        tvLingkarPanggul2.setText(mIntent.getStringExtra("LingkarPanggul2"));
        tvLingkarRok.setText(mIntent.getStringExtra("LingkarRok"));
        tvWarna.setText(mIntent.getStringExtra("Warna"));

        // Masukan Gambar Ke Image View Gunakan Glide
        Glide.with(PesananUbahActivity.this)
                .load(Config.IMAGES_URL + mIntent.getStringExtra("Image"))
                .apply(new RequestOptions().override(550, 550))
                .into(imgHolder);

        // Definisi API
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);

        // Fungsi Tombol Update
//        btUpdate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                updateUpload();
//
//                Intent intent = new Intent(PesananUbahActivity.this, PesananDataActivity.class);
//                intent.putExtra("pesan", "Pesanan Telah Dikonfirmasi");
//                startActivity(intent);
//            }
//        });

        // Di dalam metode btUpdate.setOnClickListener
        btUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showStatusDialog();
            }
        });

        String lingkarBadanValue = tvLingkarBadan.getText().toString();

        if (lingkarBadanValue.equals("88")) {
            tvUkuran.setText("S");
        } else if (lingkarBadanValue.equals("90")) {
            tvUkuran.setText("M");
        } else if (lingkarBadanValue.equals("94")) {
            tvUkuran.setText("L");
        } else if (lingkarBadanValue.equals("100")) {
            tvUkuran.setText("XL");
        } else if (lingkarBadanValue.equals("101")) {
            tvUkuran.setText("XXL");
        } else {
            tvUkuran.setText("Ukuran Tidak Diketahui"); // Default case
        }


    }

    private void showStatusDialog() {
        final String[] statuses = {
                "Konfirmasi Pesanan",
                "Pesanan Sedang Diproses",
                "Pesanan Selesai Dibuat",
                "Pesanan Sedang Dikirim",
                "Pesanan Sudah Diterima oleh Tujuan"
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(PesananUbahActivity.this);
        builder.setTitle("Pilih Status Pesanan")
                .setItems(statuses, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Set nilai ke etProses berdasarkan pilihan
                        etProses.setText(statuses[which]);

                        // Lakukan updateUpload setelah status dipilih
                        updateUpload();

                        // Pindah ke activity berikutnya
                        Intent intent = new Intent(PesananUbahActivity.this, PesananDataActivity.class);
                        intent.putExtra("pesan", "Pesanan Telah Dikonfirmasi");
                        startActivity(intent);
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void updateUpload() {
        final String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
            Call<PostPutDelPesanan> putPesananCall = mApiInterface.postUpdatePesanan
                    (null,
                            RequestBody.create(MediaType.parse("text/plain"), ID),
                            RequestBody.create(MediaType.parse("text/plain"), tvName.getText().toString()),
                            RequestBody.create(MediaType.parse("text/plain"), tvPrice.getText().toString()),
                            RequestBody.create(MediaType.parse("text/plain"), date),
                            RequestBody.create(MediaType.parse("text/plain"), UPDATE_FLAG),
                            RequestBody.create(MediaType.parse("text/plain"), etProses.getText().toString())
                    );
            putPesananCall.enqueue(new Callback<PostPutDelPesanan>() {
                @Override
                public void onResponse(Call<PostPutDelPesanan> call, Response<PostPutDelPesanan> response) {
                    PesananDataActivity.ma.refresh();
                    finish();
                }

                @Override
                public void onFailure(Call<PostPutDelPesanan> call, Throwable t) {
                    Log.d("RETRO", "ON FAILURE : " + t.getMessage());
                    //Log.d("RETRO", "ON FAILURE : " + t.getCause());
                    Toast.makeText(getApplicationContext(), "Error, image", Toast.LENGTH_LONG).show();
                }
            });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_delete, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.actionDelete) {
            showAlertDialog(ALERT_DIALOG_DELETE);
            return true;
        } else if (item.getItemId() == android.R.id.home) {
            showAlertDialog(ALERT_DIALOG_CLOSE);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {
        showAlertDialog(ALERT_DIALOG_CLOSE);
    }

    private void showAlertDialog(int type) {
        final boolean isDialogClose = type == ALERT_DIALOG_CLOSE;
        String dialogTitle, dialogMessage;

        if (isDialogClose) {
            dialogTitle = "Batal";
            dialogMessage = "Apakah anda ingin membatalkan perubahan pada form?";
        } else {
            dialogMessage = "Apakah anda yakin ingin menghapus item ini?";
            dialogTitle = "Hapus Heros";
        }

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle(dialogTitle);
        alertDialogBuilder
                .setMessage(dialogMessage)
                .setCancelable(false)
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if (isDialogClose) {
                            finish();
                        } else {
                            // Kode Hapus
                            if (ID.trim().isEmpty() == false) {
                                Call<PostPutDelPesanan> deletePesanan = mApiInterface.deletePesanan(ID);
                                deletePesanan.enqueue(new Callback<PostPutDelPesanan>() {
                                    @Override
                                    public void onResponse(Call<PostPutDelPesanan> call, Response<PostPutDelPesanan> response) {
                                        PesananDataActivity.ma.refresh();
                                        finish();
                                    }
                                    @Override
                                    public void onFailure(Call<PostPutDelPesanan> call, Throwable t) {
                                        Toast.makeText(getApplicationContext(), "Error 1", Toast.LENGTH_LONG).show();
                                    }
                                });
                            } else {
                                Toast.makeText(getApplicationContext(), "Error 2", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                })
                .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}