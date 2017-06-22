package com.mmh.mapo.core.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mmh.mapo.R;


/**
 * Created by vladimir on 12.08.16.
 */
public class MarkerUtils {

    public static MarkerOptions createMyMarker(Context context, long id, String name, double lat, double lan){
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(getMarkerBitmapFromView(context)));
        markerOptions.snippet(name);
        markerOptions.title(""+0);
        markerOptions.position(new LatLng(lat, lan));
        return markerOptions;
    }

    public static MarkerOptions createEntityMarker(Context context,LatLng latLng){
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(getMarkerBitmapFromView(context)));
        markerOptions.position(latLng);
        return markerOptions;
    }

    private static Bitmap getMarkerBitmapFromView(Context context) {
        View marker = LayoutInflater.from(context).inflate(R.layout.marker_detail, null);
        marker.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        marker.layout(0, 0, marker.getMeasuredWidth(), marker.getMeasuredHeight());
        marker.buildDrawingCache();
        Bitmap returnedBitmap = Bitmap.createBitmap(marker.getMeasuredWidth(), marker.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(returnedBitmap);
        canvas.drawColor(Color.WHITE, PorterDuff.Mode.SRC_IN);
        Drawable drawable = marker.getBackground();
        if (drawable != null)
            drawable.draw(canvas);
        marker.draw(canvas);
        return returnedBitmap;
    }
}
