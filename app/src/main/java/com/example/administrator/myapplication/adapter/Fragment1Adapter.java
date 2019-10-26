package com.example.administrator.myapplication.adapter;

import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.myapplication.R;

import java.util.List;

public class Fragment1Adapter extends BaseQuickAdapter<String,BaseViewHolder> {
    public Fragment1Adapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        ((Button)helper.getView(R.id.btn)).setText(item);
    }
}
