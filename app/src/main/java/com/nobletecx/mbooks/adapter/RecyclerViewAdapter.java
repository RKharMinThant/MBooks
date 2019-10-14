package com.nobletecx.mbooks.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.nobletecx.mbooks.R;
import com.nobletecx.mbooks.model.Book;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyHolder> {
    private List<Book> bookList = new ArrayList<>();
    private onRecyclerViewItemClickListener mItemClickListener;

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recyclerview_row_layout, viewGroup, false);
        return new MyHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {
        myHolder.tv_author.setText(bookList.get(i).author);
        myHolder.tv_title.setText(bookList.get(i).title);
        myHolder.tv_page_number.setText(String.valueOf(bookList.get(i).page_no));
        myHolder.img.setImageResource(bookList.get(i).image);
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    public void setData(List<Book> bookList) {
        this.bookList = bookList;
        notifyDataSetChanged();
    }

    public class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tv_title, tv_author, tv_page_number;
        ImageView img;

        private MyHolder(@NonNull View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_page_number = itemView.findViewById(R.id.tv_page_number);
            tv_author = itemView.findViewById(R.id.tv_author);
            img = itemView.findViewById(R.id.img);
            itemView.setOnClickListener(this);
        }

        public void onClick(View v) {
            if (mItemClickListener != null) {
                mItemClickListener.onItemClickListener(v, getAdapterPosition());
            }
        }
    }

    public void setOnItemClickListener(onRecyclerViewItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    public interface onRecyclerViewItemClickListener {
        void onItemClickListener(View view, int position);
    }
}