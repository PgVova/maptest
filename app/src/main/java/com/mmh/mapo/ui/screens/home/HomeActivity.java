package com.mmh.mapo.ui.screens.home;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mmh.mapo.R;
import com.mmh.mapo.core.android.App;
import com.mmh.mapo.core.android.BaseActivity;
import com.mmh.mapo.core.utils.MapUtils;
import com.mmh.mapo.core.utils.MarkerUtils;
import com.mmh.mapo.ui.di.HomeComponent;
import com.mmh.mapo.ui.screens.home.adapter.HomeAdapter;
import com.mmh.mapo.ui.screens.home.adapter.ViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by on 20.06.17.
 */

public class HomeActivity extends BaseActivity implements HomeView, OnMapReadyCallback , HomeAdapter.OnItemClickListener{

    private static final LatLngBounds BOUNDS_MOSCOW = new LatLngBounds(
            new LatLng(55.590290, 37.424828),
            new LatLng(55.890751, 37.787999)

    );

    @BindView(R.id.toolbar)
    Toolbar vToolbar;
    @BindView(R.id.home_fragment_tabs)
    TabLayout vTabs;
    @BindView(R.id.home_view_pager)
    ViewPager vViewPager;
    @BindView(R.id.view_pager_distance)
    FrameLayout vDistanceFrame;
    @BindView(R.id.view_pager_price)
    FrameLayout vPriceFrame;
    @BindView(R.id.activity_home_frame_switch)
    FrameLayout vSwitch;
    @BindView(R.id.home_activity_container)
    LinearLayout vLayout;
    RecyclerView vDistanceRecycler;
    RecyclerView vPriceRecycler;

    private boolean isOpen = false;

    @Inject
    HomePresenter mHomePresenter;
    private GoogleMap googleMap;
    private ViewPagerAdapter mViewPagerAdapter;
    private HomeAdapter mDistanceAdapter;
    private HomeAdapter mPriceAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setupDagger();
        ButterKnife.bind(this);

        SupportMapFragment fragment = SupportMapFragment.newInstance();
        fragment.getMapAsync(this);
        replaceFragment(fragment, SupportMapFragment.class.getName()).commit();
        setupToolbar(vToolbar, "Fuel Buddy", left -> {
            mHomePresenter.onUserClick();
        }, right -> {
            mHomePresenter.onSettingsClick();
        });
        findView();
        initViewPager();
        initDistanceRecycler();
        initPriceRecycler();
        setupListener();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHomePresenter.detachView();
        googleMap = null;

    }

    private void setupDagger() {
        App.getApp(this)
                .getAppComponent()
                .plus(new HomeComponent.Module())
                .inject(this);
    }

    public void findView() {
        vDistanceRecycler = ButterKnife.findById(vDistanceFrame, R.id.distance_recyclerView);
        vPriceRecycler = ButterKnife.findById(vPriceFrame, R.id.price_recyclerView);
    }

    private void initViewPager() {
        vTabs.setupWithViewPager(vViewPager);
        mViewPagerAdapter = new ViewPagerAdapter();
        List<FrameLayout> frames = new ArrayList<>();
        List<String> titles = new ArrayList<>();
        frames.add(vDistanceFrame);
        frames.add(vPriceFrame);
        titles.add(getString(R.string.home_distance));
        titles.add(getString(R.string.home_price));
        mViewPagerAdapter.addFrame(frames, titles);
        vViewPager.setAdapter(mViewPagerAdapter);
        vViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
             /*   if (position == 0){
                    showDistanceRecycler();
                }else {
                    showPriceRecycler();
                }*/
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public void initDistanceRecycler() {
        mDistanceAdapter = new HomeAdapter(this,this);
        vDistanceRecycler.setLayoutManager(new LinearLayoutManager(this));
        vDistanceRecycler.setAdapter(mDistanceAdapter);

    }

    public void initPriceRecycler() {
        mPriceAdapter = new HomeAdapter(this,this);
        vPriceRecycler.setLayoutManager(new LinearLayoutManager(this));
        vPriceRecycler.setAdapter(mPriceAdapter);

    }

    public void setupListener(){

        vSwitch.setOnClickListener( view-> {
            ValueAnimator anim;
            if (!isOpen) {
                anim = ValueAnimator.ofInt(vViewPager.getMeasuredHeight(), 480);
                isOpen =!isOpen;
            }else {
                anim = ValueAnimator.ofInt(vViewPager.getMeasuredHeight(), 130);
                isOpen =!isOpen;
            }
                anim.addUpdateListener(animation -> {
                    int val = (Integer) animation.getAnimatedValue();
                    ViewGroup.LayoutParams layoutParams = vViewPager.getLayoutParams();
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, val);
                    layoutParams.height = val;
                    vViewPager.setLayoutParams(params);
                });
            anim.setDuration(1000);
            anim.start();
        });

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        googleMap.getUiSettings().setCompassEnabled(false);
        googleMap.getUiSettings().setZoomControlsEnabled(false);
        googleMap.getUiSettings().setIndoorLevelPickerEnabled(false);
        googleMap.getUiSettings().setMapToolbarEnabled(false);
        googleMap.getUiSettings().setMyLocationButtonEnabled(true);
        googleMap.setOnMapClickListener(latLng -> {
            if (BOUNDS_MOSCOW.contains(latLng)) {
                googleMap.addMarker(MarkerUtils.createEntityMarker(this, latLng));
            }
        });
        MapUtils.cameraGoTo(googleMap, new LatLng(55.754700, 37.621398), 14);
        mHomePresenter.attachView(this);

    }

    @Override
    public void showDistanceRecycler() {
        if (vViewPager == null) return;
        vViewPager.setCurrentItem(0);
    }

    @Override
    public void showPriceRecycler() {
        vViewPager.setCurrentItem(1);
    }

    @Override
    public void showSetiings() {
        Toast.makeText(this,"Settings",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showUser() {
        Toast.makeText(this,"User",Toast.LENGTH_SHORT).show();

    }

    @Override
    public void showInfo() {
        Toast.makeText(this,"Item",Toast.LENGTH_SHORT).show();

    }

    @Override
    public void showPlus() {
        Toast.makeText(this,"Plus",Toast.LENGTH_SHORT).show();

    }


    @OnClick(R.id.search_plus)
    public void onPlusClick(){
        mHomePresenter.onPlusClick();
    }

    @OnClick(R.id.toolbar_action)
    public void onUserClick(){
        mHomePresenter.onUserClick();
    }

    @OnClick(R.id.toolbar_settings)
    public void onSettinbgsClick(){
        mHomePresenter.onSettingsClick();
    }

    @Override
    public void onItemClick() {
        mHomePresenter.onItemClick();
    }
}
