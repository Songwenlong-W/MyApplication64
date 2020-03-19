package com.example.myapplication.fragment;

import android.media.Image;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.myapplication.R;
import com.example.myapplication.base.BaseFragment;
import com.example.myapplication.utils.Util;

public class Fragment_three extends BaseFragment {

    private ImageView iv;
    String path="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1581432912182&di=78664f36b25bbd714f1703c1c8a0ddaa&imgtype=0&src=http%3A%2F%2Fc.hiphotos.baidu.com%2Fzhidao%2Fpic%2Fitem%2F6159252dd42a283476d00d6858b5c9ea15cebf5a.jpg";
    @Override
    protected int getLayoutResID() {
        return R.layout.fragment_three;
    }

    @Override
    protected void getFragmentView(View view) {
        iv = view.findViewById(R.id.iv);
    }

    @Override
    protected void getFragmentData() {
        Util.getInstance().getImage(path,iv);
        Log.i("xxx","图片设置");
    }




}
