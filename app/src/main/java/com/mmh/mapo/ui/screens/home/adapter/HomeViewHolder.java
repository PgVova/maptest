package com.mmh.mapo.ui.screens.home.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.mmh.mapo.R;
import com.mmh.mapo.ui.dvo.MapItemDvo;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by on 21.06.17.
 */

public class HomeViewHolder extends RecyclerView.ViewHolder{


    @BindView(R.id.map_item_distance)
    protected TextView vDistance;
    @BindView(R.id.map_item_time)
    protected TextView vTime;
    @BindView(R.id.map_item_primary_text)
    protected TextView vName;
    @BindView(R.id.map_item_secondary_text)
    protected TextView vAddress;
    @BindView(R.id.map_item_price)
    protected TextView vPrice;



    public HomeViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    public void bind(MapItemDvo dvo){
        vName.setText(dvo.getName());
        vAddress.setText(dvo.getAddress());
        vDistance.setText(dvo.getDistance());
        vPrice.setText(dvo.getPrice());
        vTime.setText(dvo.getTime());
    }
}
