package com.example.tokojahit.Adapter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.tokojahit.Config;
import com.example.tokojahit.Model.Pesanan.Pesanan;
import com.example.tokojahit.PesananUbahActivity;
import com.example.tokojahit.R;

import java.util.List;

public class AdapterDataPesanan extends RecyclerView.Adapter<AdapterDataPesanan.MyViewHolder> {
    List<Pesanan> mPesananList;
    private SharedPreferences sharedPreferences;
    public AdapterDataPesanan(List<Pesanan> PesananList) {
        mPesananList = PesananList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item_pesanan, parent, false);
        MyViewHolder mViewHolder = new MyViewHolder(mView);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

//        yang tampil di data pesanan
        holder.mTextViewName.setText(mPesananList.get(position).getName());
        holder.mTextViewDate.setText(mPesananList.get(position).getDate());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent;

                mIntent = new Intent(view.getContext(), PesananUbahActivity.class);
                mIntent.putExtra("Id", mPesananList.get(position).getId());
                mIntent.putExtra("Name", mPesananList.get(position).getName());
                mIntent.putExtra("Price", mPesananList.get(position).getPrice());
                mIntent.putExtra("Date", mPesananList.get(position).getDate());
                mIntent.putExtra("Image", mPesananList.get(position).getImage());
                mIntent.putExtra("Baju", mPesananList.get(position).getBaju());
                mIntent.putExtra("Kain", mPesananList.get(position).getKain());
                mIntent.putExtra("Desain", mPesananList.get(position).getDesain());
                mIntent.putExtra("LingkarBadan", mPesananList.get(position).getLingkarBadan());
                mIntent.putExtra("LingkarPinggang", mPesananList.get(position).getLingkarPinggang());
                mIntent.putExtra("PanjangDada", mPesananList.get(position).getPanjangDada());
                mIntent.putExtra("LebarDada", mPesananList.get(position).getLebarDada());
                mIntent.putExtra("PanjangPunggung", mPesananList.get(position).getPanjangPunggung());
                mIntent.putExtra("LebarPunggung", mPesananList.get(position).getLebarPunggung());
                mIntent.putExtra("LebarBahu", mPesananList.get(position).getLebarBahu());
                mIntent.putExtra("LingkarLeher", mPesananList.get(position).getLingkarLeher());
                mIntent.putExtra("TinggiDada", mPesananList.get(position).getTinggiDada());
                mIntent.putExtra("JarakDada", mPesananList.get(position).getJarakDada());
                mIntent.putExtra("LingkarPangkalLengan", mPesananList.get(position).getLingkarPangkalLengan());
                mIntent.putExtra("PanjangLengan", mPesananList.get(position).getPanjangLengan());
                mIntent.putExtra("LingkarSiku", mPesananList.get(position).getLingkarSiku());
                mIntent.putExtra("LingkarPergelanganTangan", mPesananList.get(position).getLingkarPergelanganTangan());
                mIntent.putExtra("LingkarKerungLengan", mPesananList.get(position).getLingkarKerungLengan());
                mIntent.putExtra("LingkarPanggul1", mPesananList.get(position).getLingkarPanggul1());
                mIntent.putExtra("LingkarPanggul2", mPesananList.get(position).getLingkarPanggul2());
                mIntent.putExtra("LingkarRok", mPesananList.get(position).getLingkarRok());
                view.getContext().startActivity(mIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mPesananList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextViewName, mTextViewPrice, mTextViewDate;
        public ImageView mImageViewFoto;
        public MyViewHolder(View itemView) {
            super(itemView);
            mTextViewName = (TextView) itemView.findViewById(R.id.tv_item_name);
            mTextViewPrice = (TextView) itemView.findViewById(R.id.tv_item_price);
            mTextViewDate = (TextView) itemView.findViewById(R.id.tv_item_date);
            mImageViewFoto = itemView.findViewById(R.id.img_item_photo);
        }
    }
}