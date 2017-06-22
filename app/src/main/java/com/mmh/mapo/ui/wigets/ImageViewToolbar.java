package com.mmh.mapo.ui.wigets;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.mmh.mapo.core.utils.DimensionUtils;


/**
 * Created by on 05.12.16.
 */

public class ImageViewToolbar extends ImageView {


    public ImageViewToolbar(Context context) {
        super(context);
    }

    public ImageViewToolbar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ImageViewToolbar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setWitdth(boolean full){
        if (full){
            getLayoutParams().width = (int) DimensionUtils.dpToPixels(getContext(),(float) 150);
        }else {
            getLayoutParams().width = (int) DimensionUtils.dpToPixels(getContext(),(float) 34);
        }
    }
}
