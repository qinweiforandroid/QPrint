package com.qw.qprint;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


/**
 * fragment封装
 * Created by qinwei on 2017/4/16.
 */

public abstract class BaseFragment extends Fragment {
    private final String TAG = getClass().getSimpleName();
    protected boolean isDebug = true;
    protected boolean isViewInitialed;//view加载状态
    protected boolean isDataInitialed;//数据加载状态
    protected boolean isVisibleToUser;//viewpager中fragment可见状态

    private boolean isLazyLoadData;

    public void enableLazyLoadData() {
        isLazyLoadData = true;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        d("onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        d("onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        d("onCreateView");
        return getCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        d("onViewCreated");
        initView(view);
        isViewInitialed = true;
        if (isLazyLoadData) {
            if (savedInstanceState != null) {
                onRestoreInstanceState(savedInstanceState);
            }
            checkIfLoadData();
        } else {
            if (savedInstanceState != null) {
                onRestoreInstanceState(savedInstanceState);
            }
            initData();
        }
    }

    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        isDataInitialed = true;
    }

    private void checkIfLoadData() {
        if (isViewInitialed && isVisibleToUser && !isDataInitialed) {
            isDataInitialed = true;
            initData();
        }
    }


    @Override
    public void onStart() {
        super.onStart();
        d("onStart");
    }


    protected abstract View getCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);


    protected abstract void initView(View view);

    protected abstract void initData();

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        d("setUserVisibleHint:" + isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;
        checkIfLoadData();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        d("onHiddenChanged:hidden," + hidden);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        d("onSaveInstanceState");
    }

    @Override
    public void onResume() {
        super.onResume();
        d("onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        d("onPause");
    }


    @Override
    public void onDestroyOptionsMenu() {
        super.onDestroyOptionsMenu();
        d("onDestroyOptionsMenu");
    }

    @Override
    public void onStop() {
        super.onStop();
        d("onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        d("onDestroyView");
        isViewInitialed = false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        d("onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        d("onDetach");
    }

    private void d(String msg) {
        if (isDebug) {
        }
    }

    public String getTitle() {
        return "";
    }

    public boolean onBackPressed() {
        return false;
    }

}
