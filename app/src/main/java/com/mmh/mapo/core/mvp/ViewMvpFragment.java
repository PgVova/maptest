package com.mmh.mapo.core.mvp;


import com.mmh.mapo.core.android.BaseFragment;

/**
 * Fragment which contain attached Presenter and provide life-cycle with presenter
 */
public abstract class ViewMvpFragment<F extends Presenter> extends BaseFragment {

    private F presenter;

    public final void attachPresenter(Object f) {
        presenter = (F) f;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter = null;
    }

    public F getPresenter() {
        return presenter;
    }
}
