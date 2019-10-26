package com.example.administrator.myapplication.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.adapter.Fragment1Adapter;
import com.example.administrator.myapplication.view.CustomViewpager;

import java.util.ArrayList;

public class Fragment1 extends Fragment {
    private RecyclerView recycler;
    private View view;
    private Fragment1Adapter fragment1Adapter;
    private ArrayList<String> arrayList=new ArrayList<>();
    private LinearLayoutManager linearLayoutManager;
    private View emptyView;
    private CustomViewpager viewpager;
    public Fragment1(){}

    public void setData(CustomViewpager viewpager){
        this.viewpager=viewpager;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragmengt1,container,false);
        recycler=view.findViewById(R.id.recycler);
        arrayList.clear();
        for (int i=0; i<10 ;i++){
            arrayList.add("fragment1界面的第"+i);
        }
        linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        fragment1Adapter=new Fragment1Adapter(R.layout.fragment_item1,arrayList);
        recycler.setLayoutManager(linearLayoutManager);
        emptyView = getLayoutInflater().inflate(R.layout.recycler_empty, (ViewGroup) recycler.getParent(), false);
        fragment1Adapter.setEmptyView(emptyView);
        if (viewpager !=null){
            viewpager.setObjectForPosition(view, 0);
        }

        recycler.setAdapter(fragment1Adapter);

        return  view;

    }
    public void refreshHeight(){
        if (viewpager !=null && view !=null){
            viewpager.setObjectForPosition(view, 0);
        }

    }
}
