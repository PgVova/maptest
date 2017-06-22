package com.mmh.mapo.ui.screens.home;

import com.mmh.mapo.core.mvp.BasePresenter;

/**
 * Created by on 20.06.17.
 */

public class HomePresenterImpl extends BasePresenter<HomeView> implements HomePresenter {


    public HomePresenterImpl() {
    }

    @Override
    public void onSettingsClick() {
        if (getView() == null) return;
        getView().showSetiings();
    }

    @Override
    public void onUserClick() {
        if (getView() == null) return;
        getView().showUser();
    }

    @Override
    public void onItemClick() {
        if (getView() == null) return;
        getView().showInfo();
    }

    @Override
    public void onPlusClick() {
        if (getView() == null) return;
        getView().showPlus();
    }
}
