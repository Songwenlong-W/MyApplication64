package com.example.myapplication.fragment;

import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.adapter.BaseAdapt;
import com.example.myapplication.base.BaseFragment;
import com.example.myapplication.bean.JsonBean;
import com.example.myapplication.utils.Util;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

public class Fragment_one extends BaseFragment {
    int page=10;
    ArrayList<JsonBean.ResultBean> list = new ArrayList<>();
    private PullToRefreshListView pull;

    @Override
    protected int getLayoutResID() {
        return R.layout.fragment_one;
    }

    @Override
    protected void getFragmentView(View view) {
        pull = view.findViewById(R.id.pull);
    }

    @Override
    protected void getFragmentData() {
        page=10;
        getServerData(page);
        pull.setMode(PullToRefreshBase.Mode.BOTH);
        pull.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                page=10;
                list.clear();
                getServerData(page);
                pull.onRefreshComplete();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                page++;
                getServerData(page);
                pull.onRefreshComplete();

            }
        });
    }

    public void getServerData(int page){
        String path="http://blog.zhaoliang5156.cn/baweiapi/news?page=1&pageSize="+page;
        Boolean wiFi = Util.getInstance().isWiFi(getActivity());
        if (wiFi){
            Util.getInstance().getJson(path, new Util.Inter() {
                @Override
                public void Ok(String json) {
                    Log.i("xxx",""+json);
                    Gson gson = new Gson();
                    JsonBean jsonBean = gson.fromJson(json, JsonBean.class);
                    List<JsonBean.ResultBean> result = jsonBean.getResult();
                    list.addAll(result);
                    BaseAdapt baseAdapt = new BaseAdapt(getActivity(), list);
                    pull.setAdapter(baseAdapt);
                }
            });
        }else {
            Toast.makeText(getContext(), "无网", Toast.LENGTH_SHORT).show();
        }
    }


}
