package com.example.tokojahit.Adapter;

import android.content.DialogInterface;
import android.content.Intent;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

//    @Override
//    public void onBindViewHolder(MyViewHolder holder, final int position) {
//        holder.mTextViewName.setText(mKainList.get(position).getName());
//        holder.mTextViewDate.setText(mKainList.get(position).getDate());
//        holder.mTextViewWarna.setText(mKainList.get(position).getWarna());
//
//        SpannableString spannablemTextViewPrice = new SpannableString("Rp." + mKainList.get(position).getPrice());
//        holder.mTextViewPrice.setText(spannablemTextViewPrice);
//
//        Glide.with(holder.itemView.getContext())
//                .load(Config.IMAGES_URL + mKainList.get(position).getImage())
//                .apply(new RequestOptions().override(1200))
//                .into(holder.mImageViewFoto);
//
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent mIntent;
//                SessionManager sessionManager = new SessionManager(view.getContext());
//                String level = sessionManager.getUserDetail().get(SessionManager.LEVEL);
//
//                if ("admin".equals(level)) {
//                    mIntent = new Intent(view.getContext(), KainUbahActivity.class);
//                    mIntent.putExtra("Id", mKainList.get(position).getId());
//                    mIntent.putExtra("NameKain", mKainList.get(position).getName());
//                    mIntent.putExtra("Price", mKainList.get(position).getPrice());
//                    mIntent.putExtra("Date", mKainList.get(position).getDate());
//                    mIntent.putExtra("Warna", mKainList.get(position).getWarna());
//                    mIntent.putExtra("Image", mKainList.get(position).getImage());
//                } else {
//                    mIntent = new Intent(view.getContext(), ABPesan.class);
//                    mIntent.putExtra("NameKain", mKainList.get(position).getName());
//                    mIntent.putExtra("PriceKain", mKainList.get(position).getPrice());
//                    mIntent.putExtra("Warna", mKainList.get(position).getWarna());
//                }
//                view.getContext().startActivity(mIntent);
//            }
//        });
//    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.mTextViewName.setText(mKainList.get(position).getName());
        holder.mTextViewDate.setText(mKainList.get(position).getDate());
        holder.mTextViewWarna.setText(mKainList.get(position).getWarna());

//        SpannableString spannablemTextViewPrice = new SpannableString("Rp." + mKainList.get(position).getPrice());
//        holder.mTextViewPrice.setText(spannablemTextViewPrice);

        String priceString = mKainList.get(position).getPrice(); // Misalnya "10000"

// Konversi string ke double
        double price = Double.parseDouble(priceString);

// Format harga dengan pemisah ribuan
        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.getDefault());
        String formattedPrice = numberFormat.format(price);

// Buat SpannableString untuk menampilkan harga
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
                    String[] warnaArray = mKainList.get(position).getWarna().split(",");

                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                    builder.setTitle("Pilih Warna");
                    builder.setItems(warnaArray, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String selectedWarna = warnaArray[which];

                            // Kirim intent dengan warna yang dipilih
                            Intent mIntent = new Intent(view.getContext(), ABPesan.class);
                            mIntent.putExtra("NameKain", mKainList.get(position).getName());
                            mIntent.putExtra("PriceKain", mKainList.get(position).getPrice());
                            mIntent.putExtra("Warna", selectedWarna);
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