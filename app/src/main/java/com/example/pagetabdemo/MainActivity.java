package com.example.pagetabdemo;


import android.os.Bundle;
import java.util.ArrayList;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends FragmentActivity implements OnClickListener, OnPageChangeListener{
    private Fragment reportFragment = new ReportFragment();
    private Fragment pointsFragment = new PointsFragment();
    private Fragment infoFragment = new InfoFragment();

    private View reportLayout;
    private View pointsLayout;
    private View infoLayout;

    private ImageView reportImage;
    private ImageView pointsImage;
    private ImageView infoImage;

    private TextView reportText;
    private TextView pointsText;
    private TextView infoText;


    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        initViews();

        final ArrayList<Fragment> fragmentList = new ArrayList<Fragment>();
        fragmentList.add(reportFragment);
        fragmentList.add(pointsFragment);
        fragmentList.add(infoFragment);

        viewPager.setAdapter(new TabPagerAdapter(getSupportFragmentManager(), fragmentList));

        viewPager.addOnPageChangeListener(this);
        setTabSelection(0);
    }

    /**
     * 在这里获取到每个需要用到的控件的实例，并给它们设置好必要的点击事件。
     */
    private void initViews() {
        viewPager = (ViewPager) findViewById(R.id.content);
        reportLayout = findViewById(R.id.report_layout);
        pointsLayout = findViewById(R.id.points_layout);
        infoLayout = findViewById(R.id.info_layout);

        reportImage = (ImageView) findViewById(R.id.report_image);
        pointsImage = (ImageView) findViewById(R.id.points_image);
        infoImage = (ImageView) findViewById(R.id.info_image);

        reportText = (TextView) findViewById(R.id.report_text);
        pointsText = (TextView) findViewById(R.id.points_text);
        infoText = (TextView) findViewById(R.id.info_text);

        reportLayout.setOnClickListener(this);
        pointsLayout.setOnClickListener(this);
        infoLayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.report_layout:
                setTabSelection(0);
                viewPager.setCurrentItem(0);
                break;
            case R.id.points_layout:
                setTabSelection(1);
                viewPager.setCurrentItem(1);
                break;
            case R.id.info_layout:
                setTabSelection(2);
                viewPager.setCurrentItem(2);
                break;
            default:
                break;
        }
    }

    /**
     * 清除掉所有的选中状态。
     */
    private void clearSelection() {
        reportImage.setImageResource(R.drawable.report_img);
        reportText.setTextColor(Color.parseColor("#82858b"));
        pointsImage.setImageResource(R.drawable.points_img);
        pointsText.setTextColor(Color.parseColor("#82858b"));
        infoImage.setImageResource(R.drawable.info_img);
        infoText.setTextColor(Color.parseColor("#82858b"));
    }

    @Override
    public void onPageScrollStateChanged(int arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onPageSelected(int arg0) {
        setTabSelection(arg0);
    }

    private void setTabSelection(int index) {
        // 每次选中之前先清楚掉上次的选中状态
        clearSelection();

        switch (index) {
            case 0:
                // 当点击了主页tab时，改变控件的图片和文字颜色
                reportImage.setImageResource(R.drawable.report_img);
                reportText.setTextColor(Color.BLACK);
                break;
            case 1:
                // 当点击了语言设置tab时，改变控件的图片和文字颜色
                pointsImage.setImageResource(R.drawable.points_img);
                pointsText.setTextColor(Color.BLACK);
                break;
            case 2:
                // 当点击了更多tab时，改变控件的图片和文字颜色
                infoImage.setImageResource(R.drawable.info_img);
                infoText.setTextColor(Color.BLACK);
                break;
            case 3:
            default:
                break;
        }
    }
}