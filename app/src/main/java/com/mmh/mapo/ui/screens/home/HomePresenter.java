package com.mmh.mapo.ui.screens.home;

import com.mmh.mapo.core.mvp.Presenter;

/**
 * Created by on 20.06.17.
 */

public interface HomePresenter extends Presenter<HomeView> {

    void onSettingsClick();
    void onUserClick();
    void onItemClick();
    void onPlusClick();

}
