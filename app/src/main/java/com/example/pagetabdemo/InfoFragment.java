package com.example.pagetabdemo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * 查找fragment
 * @author admin
 *
 */
public class InfoFragment extends android.support.v4.app.Fragment {
    private View rootView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (null == rootView) {
            rootView = inflater.inflate(R.layout.fragment_info, container,
                    false);
            initView(rootView);
        }
        return rootView;
    }
    /**
     * 用于初始化，具体操作视自己情况而定
     * @param rootView
     */
    private void initView(View rootView) {

    }
}