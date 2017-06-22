package com.mmh.mapo.ui.screens.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mmh.mapo.R;
import com.mmh.mapo.ui.dvo.MapItemDvo;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by on 21.06.17.
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeViewHolder> {


    private Context context;
    private LayoutInflater mInflater;
    private OnItemClickListener listener;
    private List<MapItemDvo> data = new ArrayList<>();

    public HomeAdapter(Context context, OnItemClickListener listener) {
        this.context = context;
        this.listener = listener;
        this.mInflater = LayoutInflater.from(context);

    }

    @Override
    public HomeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new HomeViewHolder(
                mInflater.inflate(R.layout.map_item,parent,false)
        );
    }

    @Override
    public void onBindViewHolder(HomeViewHolder holder, int position) {
//        MapItemDvo dvo = data.get(position);
//        holder.bind(dvo);
        holder.itemView.setOnClickListener(v -> {
            listener.onItemClick();
        });
    }

    @Override
    public int getItemCount() {
        return 8;
    }

    public interface OnItemClickListener{

        void onItemClick();
    }

}
