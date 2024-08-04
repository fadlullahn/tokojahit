package com.example.tokojahit.Adapter;

import android.content.Intent;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.tokojahit.ABCPesan;
import com.example.tokojahit.APesan;
import com.example.tokojahit.BajuUbahActivity;
import com.example.tokojahit.Config;
import com.example.tokojahit.DesainUbahActivity;
import com.example.tokojahit.Model.Desain.Desain;
import com.example.tokojahit.ProUseActivity;
import com.example.tokojahit.R;

import java.util.List;

public class AdapterDataDesain extends RecyclerView.Adapter<AdapterDataDesain.MyViewHolder> {
    List<Desain> mDesainList;
    public AdapterDataDesain(List<Desain> DesainList) {
        mDesainList = DesainList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item_desain, parent, false);
        MyViewHolder mViewHolder = new MyViewHolder(mView);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.mTextViewName.setText(mDesainList.get(position).getName());
        holder.mTextViewDate.setText(mDesainList.get(position).getDate());

        SpannableString spannablemTextViewPrice = new SpannableString("Rp." + mDesainList.get(position).getPrice());

        holder.mTextViewPrice.setText(spannablemTextViewPrice);

        Glide.with(holder.itemView.getContext())
                .load(Config.IMAGES_URL + mDesainList.get(position).getImage())
                .apply(new RequestOptions().override(1200))
                .into(holder.mImageViewFoto);

        Glide.with(holder.itemView.getContext())
                .load(Config.IMAGES_URL + mDesainList.get(position).getImage2())
                .apply(new RequestOptions().override(1200))
                .into(holder.mImageViewFoto2);

        Glide.with(holder.itemView.getContext())
                .load(Config.IMAGES_URL + mDesainList.get(position).getImage3())
                .apply(new RequestOptions().override(1200))
                .into(holder.mImageViewFoto3);

        Glide.with(holder.itemView.getContext())
                .load(Config.IMAGES_URL + mDesainList.get(position).getImage4())
                .apply(new RequestOptions().override(1200))
                .into(holder.mImageViewFoto4);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent;
                SessionManager sessionManager = new SessionManager(view.getContext());
                String level = sessionManager.getUserDetail().get(SessionManager.LEVEL);

                if ("admin".equals(level)) {
                    mIntent = new Intent(view.getContext(), DesainUbahActivity.class);
                    mIntent.putExtra("Id", mDesainList.get(position).getId());
                    mIntent.putExtra("NameDesain", mDesainList.get(position).getName());
                    mIntent.putExtra("Price", mDesainList.get(position).getPrice());
                    mIntent.putExtra("Date", mDesainList.get(position).getDate());
                    mIntent.putExtra("Image", mDesainList.get(position).getImage());
                    mIntent.putExtra("Image2", mDesainList.get(position).getImage2());
                    mIntent.putExtra("Image3", mDesainList.get(position).getImage3());
                    mIntent.putExtra("Image4", mDesainList.get(position).getImage4());

                } else {
                    mIntent = new Intent(view.getContext(), ABCPesan.class);
                    mIntent.putExtra("NameDesain", mDesainList.get(position).getName());
                    mIntent.putExtra("PriceDesain", mDesainList.get(position).getPrice());
                }
                view.getContext().startActivity(mIntent);
            }
        });

        holder.btnDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(view.getContext(), DesainUbahActivity.class); // Ganti APesan dengan Activity yang dituju
                mIntent.putExtra("Id", mDesainList.get(position).getId());
                mIntent.putExtra("NameDesain", mDesainList.get(position).getName());
                mIntent.putExtra("Price", mDesainList.get(position).getPrice());
                mIntent.putExtra("Date", mDesainList.get(position).getDate());
                mIntent.putExtra("Image", mDesainList.get(position).getImage());
                mIntent.putExtra("Image2", mDesainList.get(position).getImage2());
                mIntent.putExtra("Image3", mDesainList.get(position).getImage3());
                mIntent.putExtra("Image4", mDesainList.get(position).getImage4());
                view.getContext().startActivity(mIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDesainList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextViewName, mTextViewPrice, mTextViewDate;
        public ImageView mImageViewFoto, mImageViewFoto2, mImageViewFoto3, mImageViewFoto4;
        public Button btnDetail;

        public MyViewHolder(View itemView) {
            super(itemView);
            mTextViewName = (TextView) itemView.findViewById(R.id.tv_item_name);
            mTextViewPrice = (TextView) itemView.findViewById(R.id.tv_item_price);
            mTextViewDate = (TextView) itemView.findViewById(R.id.tv_item_date);
            mImageViewFoto = itemView.findViewById(R.id.img_item_photo);
            mImageViewFoto2 = itemView.findViewById(R.id.img_item_photo2);
            mImageViewFoto3 = itemView.findViewById(R.id.img_item_photo3);
            mImageViewFoto4 = itemView.findViewById(R.id.img_item_photo4);
            btnDetail = itemView.findViewById(R.id.btn_detail);


        }
    }
}