package com.example.administrator.myapplication;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.alibaba.android.vlayout.layout.StickyLayoutHelper;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.myapplication.adapter.BaseDelegateAdapter;
import com.example.administrator.myapplication.fragment.Fragment1;
import com.example.administrator.myapplication.fragment.Fragment2;
import com.example.administrator.myapplication.fragment.Fragment3;
import com.example.administrator.myapplication.fragment.PriceFragmentViewpagerAdapter;
import com.example.administrator.myapplication.util.GlideImageLoader;
import com.example.administrator.myapplication.view.CustomViewpager;
import com.sunfusheng.marqueeview.MarqueeView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static com.example.administrator.myapplication.Main2Activity.Config.BANNER_VIEW_TYPE;
import static com.example.administrator.myapplication.Main2Activity.Config.GRID_URL;
import static com.example.administrator.myapplication.Main2Activity.Config.IMG_URLS;
import static com.example.administrator.myapplication.Main2Activity.Config.ITEM_NAMES;
import static com.example.administrator.myapplication.Main2Activity.Config.LIN_VIEW_TYPE;
import static com.example.administrator.myapplication.Main2Activity.Config.MARQUEE_VIEW_TYPE;
import static com.example.administrator.myapplication.Main2Activity.Config.MENU_VIEW_TYPE;
import static com.example.administrator.myapplication.Main2Activity.Config.NEWS_VIEW_TYPE;
import static com.example.administrator.myapplication.Main2Activity.Config.PRODUCT_VIEW_TYPE;
import static com.example.administrator.myapplication.Main2Activity.Config.TAB_VIEW_TYPE;

public class Main2Activity extends AppCompatActivity {

    private RecyclerView recycler;
    private List<DelegateAdapter.Adapter> mAdapters; //存放各个模块的适配器
    interface Config {
        //不同item必须不同的viewtype
        int BANNER_VIEW_TYPE = 1;
        int MENU_VIEW_TYPE = 2;
        int NEWS_VIEW_TYPE = 3;
        int PRODUCT_VIEW_TYPE = 5;
        int MARQUEE_VIEW_TYPE = 6;
        int LIN_VIEW_TYPE = 7;
        int TAB_VIEW_TYPE = 8;
        int[] IMG_URLS = {R.mipmap.ic_tian_mao, R.mipmap.ic_ju_hua_suan, R.mipmap.ic_tian_mao_guoji, R.mipmap.ic_waimai, R.mipmap.ic_chaoshi, R.mipmap.ic_voucher_center, R.mipmap.ic_travel, R.mipmap.ic_tao_gold, R.mipmap.ic_auction, R.mipmap.ic_classify};
        String[] ITEM_NAMES = {"天猫", "聚划算", "天猫国际", "外卖", "天猫超市", "充值中心", "飞猪旅行", "领金币", "拍卖", "分类"};
        int[] GRID_URL = {R.mipmap.home1, R.mipmap.home2, R.mipmap.flashsale3, R.mipmap.flashsale4,R.mipmap.home1,R.mipmap.home2};
        int[] ITEM_URL = {R.mipmap.item1, R.mipmap.item2, R.mipmap.item3, R.mipmap.item4, R.mipmap.item5};

    }

    String[] mTitles = new String[]{
            "自选", "EAT", "USDT"
    };

    List<Fragment> mFragments;
    private Fragment1 fragment1;
    private Fragment2 fragment2;
    private Fragment3 fragment3;
    private    CustomViewpager viewpager;

    ArrayList<String> arrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initViewpager();
        initView();
    }

    private void initView() {
        mAdapters = new LinkedList<>();
        recycler=findViewById(R.id.recycler);
//初始化
        VirtualLayoutManager layoutManager = new VirtualLayoutManager(this);
        recycler.setLayoutManager(layoutManager);

//设置回收复用池大小，（如果一屏内相同类型的 View 个数比较多，需要设置一个合适的大小，防止来回滚动时重新创建 View）：
        RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
        recycler.setRecycledViewPool(viewPool);
        viewPool.setMaxRecycledViews(0, 10);

        DelegateAdapter delegateAdapter = new DelegateAdapter(layoutManager, true);
        recycler.setAdapter(delegateAdapter);

        arrayList.add("http://s18.mogucdn.com/p2/170122/upload_66g1g3h491bj9kfb6ggd3i1j4c7be_750x500.jpg");
        arrayList.add("http://s16.mogucdn.com/p2/170206/upload_1759d25k9a3djeb125a5bcg0c43eg_750x500.jpg");
        arrayList.add("https://s2.mogucdn.com/mlcdn/c45406/170422_678did070ec6le09de3g15c1l7l36_750x500.jpg");
        arrayList.add("https://s2.mogucdn.com/mlcdn/c45406/170420_1hcbb7h5b58ihilkdec43bd6c2ll6_750x500.jpg");
        arrayList.add("http://s16.mogucdn.com/p2/170204/upload_56631h6616g4e2e45hc6hf6b7g08f_750x500.jpg");

        BaseDelegateAdapter bannerAdapter = new BaseDelegateAdapter(this, new LinearLayoutHelper(), R.layout.vlayout_banner, 1, BANNER_VIEW_TYPE) {
            @Override
            public void onBindViewHolder(BaseViewHolder holder, int position) {

                // 绑定数据
                Banner mBanner = holder.getView(R.id.banner);
                //设置banner样式
                mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
                //设置图片加载器
                mBanner.setImageLoader(new GlideImageLoader());
                //设置图片集合
                mBanner.setImages(arrayList);
                //设置banner动画效果
                mBanner.setBannerAnimation(Transformer.DepthPage);
                //设置标题集合（当banner样式有显示title时）
                //        mBanner.setBannerTitles(titles);
                //设置自动轮播，默认为true
                mBanner.isAutoPlay(true);
                //设置轮播时间
                mBanner.setDelayTime(5000);
                //设置指示器位置（当banner模式中有指示器时）
                mBanner.setIndicatorGravity(BannerConfig.CENTER);
                //banner设置方法全部调用完毕时最后调用
                mBanner.start();

                mBanner.setOnBannerListener(new OnBannerListener() {
                    @Override
                    public void OnBannerClick(int position) {
                        Toast.makeText(getApplicationContext(), "banner点击了" + position, Toast.LENGTH_SHORT).show();
                    }
                });

                super.onBindViewHolder(holder, position);
            }
        };
        //把轮播器添加到集合
        mAdapters.add(bannerAdapter);

        GridLayoutHelper gridLayoutHelper=new GridLayoutHelper(5); //每行五个
        gridLayoutHelper.setVGap(20); //行间距
        gridLayoutHelper.setMarginTop(20);
        BaseDelegateAdapter gridAdapter=new BaseDelegateAdapter(this,gridLayoutHelper,R.layout.gridlayout,10,MENU_VIEW_TYPE){
            @Override
            public void onBindViewHolder(BaseViewHolder holder, final int position) {
                super.onBindViewHolder(holder, position);
                ImageView iv=holder.getView(R.id.iv);
                iv.setImageResource(IMG_URLS[position]);
                TextView tv=holder.getView(R.id.text);
                tv.setText(ITEM_NAMES[position]);
                LinearLayout ll_menu=holder.getView(R.id.ll_menu);
                ll_menu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(), ITEM_NAMES[position], Toast.LENGTH_SHORT).show();
                    }
                });
            }
        };
        mAdapters.add(gridAdapter);

        //我淘我家的图片
        BaseDelegateAdapter baseDelegateAdapter=new BaseDelegateAdapter(this,new LinearLayoutHelper(),R.layout.love,1,NEWS_VIEW_TYPE);
        mAdapters.add(baseDelegateAdapter);

        //新闻滚动栏
        LinearLayoutHelper marqueeHelper=new LinearLayoutHelper();
        marqueeHelper.setMargin(20,20,20,1);
        BaseDelegateAdapter  marqueeAdapter=new BaseDelegateAdapter(this,marqueeHelper,R.layout.gundong,1,MARQUEE_VIEW_TYPE){
            @Override
            public void onBindViewHolder(BaseViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                MarqueeView marqueeView1 = holder.getView(R.id.marqueeView1);

                List<String> info1 = new ArrayList<>();
                info1.add("这个是用来搞笑的，不要在意这写小细节！");
                info1.add("这个是用来搞笑的，不要在意这写小细节！");
                marqueeView1.startWithList(info1);

                marqueeView1.setOnItemClickListener(new MarqueeView.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position, TextView textView) {
                        Toast.makeText(getApplicationContext(), textView.getText().toString(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        };

        mAdapters.add(marqueeAdapter);

        //添加一些底部的图片

//        LinearLayoutHelper product=new LinearLayoutHelper();

        GridLayoutHelper productGrid=new GridLayoutHelper(2);
        productGrid.setMargin(30, 0, 30, 0);
        productGrid.setBgColor(Color.WHITE);
//        productGrid.setVGap(5);// 控制子元素之间的垂直间距
//        productGrid.setHGap(5);// 控制子元素之间的水平间距
        BaseDelegateAdapter productAdapter=new BaseDelegateAdapter(this,productGrid,R.layout.product,6,PRODUCT_VIEW_TYPE){
            @Override
            public void onBindViewHolder(BaseViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
               ImageView imageView= holder.getView(R.id.iv_product);
               imageView.setImageResource(GRID_URL[position]);
            }
        }; //总共6个 每个两行

        mAdapters.add(productAdapter);


        //双11 全球狂欢节
        LinearLayoutHelper linearLayoutHelper=new LinearLayoutHelper();
        linearLayoutHelper.setMargin(20,20,20,0);
        BaseDelegateAdapter linAdapter=new BaseDelegateAdapter(this,linearLayoutHelper,R.layout.vlayout_lin,1,LIN_VIEW_TYPE){
            @Override
            public void onBindViewHolder(BaseViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                ImageView iv_1=holder.getView(R.id.iv_1);
                ImageView iv_2=holder.getView(R.id.iv_2);
                iv_1.setImageResource(R.mipmap.home1);
                iv_2.setImageResource(R.mipmap.home4);
            }
        };
        mAdapters.add(linAdapter);

        /**
         * tablayout
         */
        StickyLayoutHelper stickyLayoutHelper=new StickyLayoutHelper();
//        stickyLayoutHelper.setBgColor(Color.WHITE);
        BaseDelegateAdapter stickyAdapter=new BaseDelegateAdapter(this,stickyLayoutHelper,R.layout.valyout_stick,1,9){
            @Override
            public void onBindViewHolder(BaseViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                tabLayout=holder.getView(R.id.tabLayout);


            }
        };
        mAdapters.add(stickyAdapter);

        LinearLayoutHelper tabLayoutHelper=new LinearLayoutHelper();
        tabLayoutHelper.setMargin(20,20,20,0);
        BaseDelegateAdapter tabAdapter=new BaseDelegateAdapter(this,tabLayoutHelper,R.layout.vlayout_tab,1,TAB_VIEW_TYPE){
            @Override
            public void onBindViewHolder(BaseViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);


                    viewpager=holder.getView(R.id.viewpager);

                    viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                        @Override
                        public void onPageScrolled(int i, float v, int i1) {

                        }

                        @Override
                        public void onPageSelected(int position) {
                            current_position=position;
                            viewpager.resetHeight(position);
//                            switch (position){
//                                case 0:
//                                    fragment1.refreshHeight();
//                                    break;
//
//                                case 1:
//                                    fragment2.refreshHeight();
//                                    break;
//                                case 2:
//                                    fragment3.refreshHeight();
//                                    break;
//                            }
                        }

                        @Override
                        public void onPageScrollStateChanged(int i) {

                        }
                    });

                    mFragments = new ArrayList<>();
                    fragment1=new Fragment1();
                    fragment2=new Fragment2();
                    fragment3=new Fragment3();

                    fragment1.setData(viewpager);
                    fragment2.setData(viewpager);
                    fragment3.setData(viewpager);

                    // 第二步：为ViewPager设置适配器
                    mFragments.add(fragment1);
                    mFragments.add(fragment2);
                    mFragments.add(fragment3);
                    adapter = new PriceFragmentViewpagerAdapter(getSupportFragmentManager(), mFragments, mTitles);

//                        fragment1.refreshHeight();
                    viewpager.setAdapter(adapter);
                    //  第三步：将ViewPager与TableLayout 绑定在一起

                    tabLayout.setupWithViewPager(viewpager);
                    viewpager.setCurrentItem(current_position);
                   viewpager.resetHeight(current_position);

                }



        };
        mAdapters.add(tabAdapter);



        delegateAdapter.setAdapters(mAdapters);

    }

    PriceFragmentViewpagerAdapter adapter;
    TabLayout tabLayout;
    private boolean isload=false;
    private int current_position=0;
    public void initViewpager(){

    }
}
