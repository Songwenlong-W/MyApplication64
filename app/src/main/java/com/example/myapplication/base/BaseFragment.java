package com.example.myapplication.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), getLayoutResID(), null);
        getFragmentView(view);
        getFragmentData();
        return view;
    }


    protected abstract int getLayoutResID();
    protected abstract void getFragmentView(View view);

    protected abstract void getFragmentData();



}
