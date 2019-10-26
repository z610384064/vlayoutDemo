package com.example.administrator.myapplication;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.administrator.myapplication.fragment.Fragment1;
import com.example.administrator.myapplication.fragment.Fragment2;
import com.example.administrator.myapplication.fragment.Fragment3;
import com.example.administrator.myapplication.fragment.PriceFragmentViewpagerAdapter;
import com.example.administrator.myapplication.util.GlideImageLoader;
import com.example.administrator.myapplication.view.CustomViewpager;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    List<Fragment> mFragments;
    private Fragment1 fragment1;
    private Fragment2 fragment2;
    private Fragment3 fragment3;
    String[] mTitles = new String[]{
            "自选", "EAT", "USDT"
    };
    private TabLayout tabLayout;
    private ViewPager viewpager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout=findViewById(R.id.tabLayout);
        viewpager=findViewById(R.id.viewpager);

        viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int position) {
//                viewpager.resetHeight(position);
//                switch (position){
//                    case 0:
//                        fragment1.refreshHeight();
//                        break;
//
//                    case 1:
//                        fragment2.refreshHeight();
//                        break;
//                    case 2:
//                        fragment3.refreshHeight();
//                        break;
//                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
//        viewpager.resetHeight(0);
        mFragments = new ArrayList<>();
        fragment1=new Fragment1();
        fragment2=new Fragment2();
        fragment3=new Fragment3();

//        fragment1.setData(viewpager);
//        fragment2.setData(viewpager);
//        fragment3.setData(viewpager);

        // 第二步：为ViewPager设置适配器
        mFragments.add(fragment1);
        mFragments.add(fragment2);
        mFragments.add(fragment3);
        PriceFragmentViewpagerAdapter adapter = new PriceFragmentViewpagerAdapter(getSupportFragmentManager(), mFragments, mTitles);


        viewpager.setAdapter(adapter);
        //  第三步：将ViewPager与TableLayout 绑定在一起

        tabLayout.setupWithViewPager(viewpager);


    }
}
