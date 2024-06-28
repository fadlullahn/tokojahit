package com.example.tokojahit.Adapter;

import android.content.Intent;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.tokojahit.APesan;
import com.example.tokojahit.BajuUbahActivity;
import com.example.tokojahit.Config;
import com.example.tokojahit.Model.Baju.Baju;
import com.example.tokojahit.R;

import java.util.List;

public class AdapterDataBaju extends RecyclerView.Adapter<AdapterDataBaju.MyViewHolder> {
    List<Baju> mBajuList;
    public AdapterDataBaju(List<Baju> BajuList) {
        mBajuList = BajuList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item_baju, parent, false);
        MyViewHolder mViewHolder = new MyViewHolder(mView);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.mTextViewName.setText(mBajuList.get(position).getName());
        holder.mTextViewDate.setText(mBajuList.get(position).getDate());

        SpannableString spannablemTextViewPrice = new SpannableString("Rp." + mBajuList.get(position).getPrice());

        holder.mTextViewPrice.setText(spannablemTextViewPrice);

        Glide.with(holder.itemView.getContext())
                .load(Config.IMAGES_URL + mBajuList.get(position).getImage())
                .apply(new RequestOptions().override(350, 350))
                .into(holder.mImageViewFoto);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent;
                SessionManager sessionManager = new SessionManager(view.getContext());
                String level = sessionManager.getUserDetail().get(SessionManager.LEVEL);

                if ("admin".equals(level)) {
                    mIntent = new Intent(view.getContext(), BajuUbahActivity.class);
                    mIntent.putExtra("Id", mBajuList.get(position).getId());
                    mIntent.putExtra("NameBaju", mBajuList.get(position).getName());
                    mIntent.putExtra("Price", mBajuList.get(position).getPrice());
                    mIntent.putExtra("Date", mBajuList.get(position).getDate());
                    mIntent.putExtra("Image", mBajuList.get(position).getImage());
                } else {
                    mIntent = new Intent(view.getContext(), APesan.class);
                    mIntent.putExtra("NameBaju", mBajuList.get(position).getName());
                    mIntent.putExtra("Price", mBajuList.get(position).getPrice());
                }
                view.getContext().startActivity(mIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mBajuList.size();
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