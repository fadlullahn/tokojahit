package com.example.tokojahit.Adapter;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.tokojahit.ABPesan;
import com.example.tokojahit.Config;
import com.example.tokojahit.KainUbahActivity;
import com.example.tokojahit.Model.Kain.Kain;
import com.example.tokojahit.R;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class AdapterDataKain extends RecyclerView.Adapter<AdapterDataKain.MyViewHolder> {
    List<Kain> mKainList;
    public AdapterDataKain(List<Kain> KainList) {
        mKainList = KainList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item_kain, parent, false);
        MyViewHolder mViewHolder = new MyViewHolder(mView);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.mTextViewName.setText(mKainList.get(position).getName());
        holder.mTextViewDate.setText(mKainList.get(position).getDate());
        // Ambil nilai warna dari objek
        String warnaText = mKainList.get(position).getWarna();

// Pisahkan warna berdasarkan koma
        String[] warnaArray = warnaText.split(", ");

// Buat StringBuilder untuk menyimpan teks warna tanpa kode warna
        StringBuilder warnaDisplay = new StringBuilder();

        for (String warna : warnaArray) {
            // Periksa apakah string mengandung kode warna (dengan tanda pagar)
            if (warna.contains("#")) {
                // Pisahkan nama warna dan kode warna
                String[] parts = warna.split(" ");
                if (parts.length > 1) {
                    warnaDisplay.append(parts[0].trim()); // Ambil nama warna
                }
            } else {
                warnaDisplay.append(warna.trim()); // Jika tidak ada kode warna
            }
            warnaDisplay.append(", "); // Tambahkan koma sebagai pemisah
        }

// Hapus koma terakhir
        if (warnaDisplay.length() > 0) {
            warnaDisplay.setLength(warnaDisplay.length() - 2);
        }

// Tampilkan warna yang telah diformat di TextView
        holder.mTextViewWarna.setText(warnaDisplay.toString());



        String priceString = mKainList.get(position).getPrice(); // Misalnya "10000"

        double price = Double.parseDouble(priceString);

        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.getDefault());
        String formattedPrice = numberFormat.format(price);

        SpannableString spannableTextViewPrice = new SpannableString("Rp. " + formattedPrice);
        holder.mTextViewPrice.setText(spannableTextViewPrice);

        Glide.with(holder.itemView.getContext())
                .load(Config.IMAGES_URL + mKainList.get(position).getImage())
                .apply(new RequestOptions().override(1200))
                .into(holder.mImageViewFoto);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent;
                SessionManager sessionManager = new SessionManager(view.getContext());
                String level = sessionManager.getUserDetail().get(SessionManager.LEVEL);

                if ("admin".equals(level)) {
                    mIntent = new Intent(view.getContext(), KainUbahActivity.class);
                    mIntent.putExtra("Id", mKainList.get(position).getId());
                    mIntent.putExtra("NameKain", mKainList.get(position).getName());
                    mIntent.putExtra("Price", mKainList.get(position).getPrice());
                    mIntent.putExtra("Date", mKainList.get(position).getDate());
                    mIntent.putExtra("Warna", mKainList.get(position).getWarna());
                    mIntent.putExtra("Image", mKainList.get(position).getImage());
                    view.getContext().startActivity(mIntent);
                } else {
                    // Pisahkan warna berdasarkan koma
                    String[] warnaArray = mKainList.get(position).getWarna().split(", ");

                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                    builder.setTitle("Pilih Warna");

                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(view.getContext(), R.layout.item_warna, R.id.text_warna, warnaArray) {
                        @Override
                        public View getView(int position, View convertView, ViewGroup parent) {
                            View view = super.getView(position, convertView, parent);

                            String warna = getItem(position);
                            View viewColor = view.findViewById(R.id.view_color);
                            TextView textWarna = view.findViewById(R.id.text_warna);

                            // Set the text
                            textWarna.setText(warna);

                            // Parse the color safely
                            try {
                                if (warna != null && warna.contains("#")) {
                                    // Pisahkan nama warna dan kode warna
                                    String namaWarna = warna.substring(0, warna.indexOf("#")).trim();
                                    String kodeWarna = warna.substring(warna.indexOf("#")).trim();

                                    // Tampilkan nama warna
                                    textWarna.setText(namaWarna);

                                    // Set warna jika kode warna valid
                                    viewColor.setVisibility(View.VISIBLE);
                                    viewColor.setBackgroundColor(Color.parseColor(kodeWarna));
                                } else {
                                    viewColor.setVisibility(View.GONE); // Hide the color box if no color code found
                                }
                            } catch (IllegalArgumentException e) {
                                // Handle invalid color code
                                viewColor.setVisibility(View.GONE);
                            }

                            return view;
                        }
                    };

                    builder.setAdapter(adapter, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String selectedWarna = warnaArray[which];

                            // Pisahkan nama warna dari kode warna
                            String namaWarna = selectedWarna.contains("#") ? selectedWarna.substring(0, selectedWarna.indexOf("#")).trim() : selectedWarna;

                            // Kirim intent dengan nama warna yang dipilih
                            Intent mIntent = new Intent(view.getContext(), ABPesan.class);
                            mIntent.putExtra("NameKain", mKainList.get(position).getName());
                            mIntent.putExtra("PriceKain", mKainList.get(position).getPrice());
                            mIntent.putExtra("Warna", namaWarna);
                            view.getContext().startActivity(mIntent);
                        }
                    });

                    builder.show();

                }
            }
        });
    }



    @Override
    public int getItemCount() {
        return mKainList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextViewName, mTextViewPrice, mTextViewDate, mTextViewWarna;
        public ImageView mImageViewFoto;

        public MyViewHolder(View itemView) {
            super(itemView);
            mTextViewName = (TextView) itemView.findViewById(R.id.tv_item_name);
            mTextViewPrice = (TextView) itemView.findViewById(R.id.tv_item_price);
            mTextViewDate = (TextView) itemView.findViewById(R.id.tv_item_date);
            mTextViewWarna = (TextView) itemView.findViewById(R.id.tv_item_warna);
            mImageViewFoto = itemView.findViewById(R.id.img_item_photo);
        }
    }
}