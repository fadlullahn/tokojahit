package com.example.tokojahit;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.tokojahit.Api.ApiClient;
import com.example.tokojahit.Api.ApiInterface;
import com.example.tokojahit.Model.Desain.PostPutDelDesain;

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

public class DesainTambahActivity extends AppCompatActivity {
    EditText edtName, edtPrice;
    Button btnGalery,btnGalery2, btnGalery3,btnGalery4, btSubmit;
    ImageView imgHolder, imgHolder2,imgHolder3, imgHolder4;

    private String mediaPath, mediaPath2, mediaPath3, mediaPath4;
    private String postPath, postPath2, postPath3, postPath4;

    ApiInterface mApiInterface;
    private static final int REQUEST_PICK_PHOTO_FOUR = Config.REQUEST_PICK_PHOTO_FOUR;
    private static final int REQUEST_PICK_PHOTO_THREE = Config.REQUEST_PICK_PHOTO_THREE;
    private static final int REQUEST_PICK_PHOTO_SECOND = Config.REQUEST_PICK_PHOTO_SECOND;
    private static final int REQUEST_PICK_PHOTO = Config.REQUEST_PICK_PHOTO;
    private static final int REQUEST_WRITE_PERMISSION = Config.REQUEST_WRITE_PERMISSION;
    private static final String INSERT_FLAG = Config.INSERT_FLAG;

    // Akses Izin Ambil Gambar dari Storage
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == REQUEST_WRITE_PERMISSION && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            saveImageUpload();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desain_tambah);

        // Identifikasi Komponen Form
        edtName = findViewById(R.id.edt_name);
        edtPrice = findViewById(R.id.edt_price);

        imgHolder = findViewById(R.id.imgHolder);
        imgHolder2 = findViewById(R.id.imgHolder2);
        imgHolder3 = findViewById(R.id.imgHolder3);
        imgHolder4 = findViewById(R.id.imgHolder4);

        btnGalery = findViewById(R.id.btn_galery);
        btnGalery2 = findViewById(R.id.btn_galery2);
        btnGalery3 = findViewById(R.id.btn_galery3);
        btnGalery4 = findViewById(R.id.btn_galery4);

        btSubmit = findViewById(R.id.btn_submit);

        // Definisi API
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);

        // Fungsi Tombol Pilih Galery
        btnGalery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, REQUEST_PICK_PHOTO);
            }
        });

        btnGalery2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent2 = new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent2, REQUEST_PICK_PHOTO_SECOND);
            }
        });

        btnGalery3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent3 = new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent3, REQUEST_PICK_PHOTO_THREE);
            }
        });

        btnGalery4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent4 = new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent4, REQUEST_PICK_PHOTO_FOUR);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_PICK_PHOTO) {
                if (data != null) {
                    // Ambil Image Dari Galeri
                    Uri selectedImage = data.getData();
                    String[] filePathColumn = {MediaStore.Images.Media.DATA};

                    // Mengambil gambar dari galeri
                    Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                    if (cursor != null) {
                        cursor.moveToFirst();
                        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                        mediaPath = cursor.getString(columnIndex);
                        imgHolder.setImageURI(selectedImage);
                        cursor.close();
                        postPath = mediaPath;
                    }
                }
            } else if (requestCode == REQUEST_PICK_PHOTO_SECOND) {
                if (data != null) {
                    // Ambil Image Kedua Dari Galeri
                    Uri selectedImage2 = data.getData();
                    String[] filePathColumn2 = {MediaStore.Images.Media.DATA};

                    // Mengambil gambar kedua dari galeri
                    Cursor cursor2 = getContentResolver().query(selectedImage2, filePathColumn2, null, null, null);
                    if (cursor2 != null) {
                        cursor2.moveToFirst();
                        int columnIndex2 = cursor2.getColumnIndex(filePathColumn2[0]);
                        mediaPath2 = cursor2.getString(columnIndex2);
                        imgHolder2.setImageURI(selectedImage2);
                        cursor2.close();
                        postPath2 = mediaPath2;
                    }
                }
            }else if (requestCode == REQUEST_PICK_PHOTO_THREE) {
                if (data != null) {
                    // Ambil Image Kedua Dari Galeri
                    Uri selectedImage3 = data.getData();
                    String[] filePathColumn3 = {MediaStore.Images.Media.DATA};

                    // Mengambil gambar kedua dari galeri
                    Cursor cursor3 = getContentResolver().query(selectedImage3, filePathColumn3, null, null, null);
                    if (cursor3 != null) {
                        cursor3.moveToFirst();
                        int columnIndex3 = cursor3.getColumnIndex(filePathColumn3[0]);
                        mediaPath3 = cursor3.getString(columnIndex3);
                        imgHolder3.setImageURI(selectedImage3);
                        cursor3.close();
                        postPath3 = mediaPath3;
                    }
                }
            }else if (requestCode == REQUEST_PICK_PHOTO_FOUR) {
                if (data != null) {
                    // Ambil Image Kedua Dari Galeri
                    Uri selectedImage4 = data.getData();
                    String[] filePathColumn4 = {MediaStore.Images.Media.DATA};

                    // Mengambil gambar kedua dari galeri
                    Cursor cursor4 = getContentResolver().query(selectedImage4, filePathColumn4, null, null, null);
                    if (cursor4 != null) {
                        cursor4.moveToFirst();
                        int columnIndex4 = cursor4.getColumnIndex(filePathColumn4[0]);
                        mediaPath4 = cursor4.getString(columnIndex4);
                        imgHolder4.setImageURI(selectedImage4);
                        cursor4.close();
                        postPath4 = mediaPath4;
                    }
                }
            }
        }
    }


    private void saveImageUpload() {
        final String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

        // Mengecek apakah ada mediaPath (gambar diunggah)
        if (mediaPath == null && mediaPath2 == null && mediaPath3 == null && mediaPath4 == null) {
            // Tidak ada gambar, mengirim permintaan tanpa file gambar
            Call<PostPutDelDesain> postDesainCall = mApiInterface.postDesain(
                    null,
                    null,
                    null,
                    null,
                    RequestBody.create(MediaType.parse("text/plain"), edtName.getText().toString()),
                    RequestBody.create(MediaType.parse("text/plain"), edtPrice.getText().toString()),
                    RequestBody.create(MediaType.parse("text/plain"), date),
                    RequestBody.create(MediaType.parse("text/plain"), INSERT_FLAG)
            );

            postDesainCall.enqueue(new Callback<PostPutDelDesain>() {
                @Override
                public void onResponse(Call<PostPutDelDesain> call, Response<PostPutDelDesain> response) {
                    DesainDataActivity.ma.refresh();
                    finish();
                }

                @Override
                public void onFailure(Call<PostPutDelDesain> call, Throwable t) {
                    Log.d("RETRO", "ON FAILURE : " + t.getMessage());
                    Toast.makeText(getApplicationContext(), "Error, no image", Toast.LENGTH_LONG).show();
                }
            });
        } else {
            // Menyiapkan bagian gambar pertama jika ada
            MultipartBody.Part partImage = null;
            if (mediaPath != null) {
                File imagefile = new File(mediaPath);
                RequestBody reqBody = RequestBody.create(MediaType.parse("multipart/form-file"), imagefile);
                partImage = MultipartBody.Part.createFormData("image", imagefile.getName(), reqBody);
            }

            // Menyiapkan bagian gambar kedua jika ada
            MultipartBody.Part partImage2 = null;
            if (mediaPath2 != null) {
                File imagefile2 = new File(mediaPath2);
                RequestBody reqBody2 = RequestBody.create(MediaType.parse("multipart/form-file"), imagefile2);
                partImage2 = MultipartBody.Part.createFormData("image2", imagefile2.getName(), reqBody2);
            }

            // Menyiapkan bagian gambar ketiga jika ada
            MultipartBody.Part partImage3 = null;
            if (mediaPath3 != null) {
                File imagefile3 = new File(mediaPath3);
                RequestBody reqBody3 = RequestBody.create(MediaType.parse("multipart/form-file"), imagefile3);
                partImage3 = MultipartBody.Part.createFormData("image3", imagefile3.getName(), reqBody3);
            }

            // Menyiapkan bagian gambar keempat jika ada
            MultipartBody.Part partImage4 = null;
            if (mediaPath4 != null) {
                File imagefile4 = new File(mediaPath4);
                RequestBody reqBody4 = RequestBody.create(MediaType.parse("multipart/form-file"), imagefile4);
                partImage4 = MultipartBody.Part.createFormData("image4", imagefile4.getName(), reqBody4);
            }

            Call<PostPutDelDesain> postDesainCall = mApiInterface.postDesain(
                    partImage,
                    partImage2,
                    partImage3,
                    partImage4,
                    RequestBody.create(MediaType.parse("text/plain"), edtName.getText().toString()),
                    RequestBody.create(MediaType.parse("text/plain"), edtPrice.getText().toString()),
                    RequestBody.create(MediaType.parse("text/plain"), date),
                    RequestBody.create(MediaType.parse("text/plain"), INSERT_FLAG)
            );

            postDesainCall.enqueue(new Callback<PostPutDelDesain>() {
                @Override
                public void onResponse(Call<PostPutDelDesain> call, Response<PostPutDelDesain> response) {
                    DesainDataActivity.ma.refresh();
                    finish();
                }

                @Override
                public void onFailure(Call<PostPutDelDesain> call, Throwable t) {
                    Log.d("RETRO", "ON FAILURE : " + t.getMessage());
                    Toast.makeText(getApplicationContext(), "Error, image", Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    private void requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            requestPermissions(new String[]{Manifest.permission.READ_MEDIA_IMAGES, Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_WRITE_PERMISSION);
        } else {
            saveImageUpload();
        }
    }
}
