package com.example.m7uf2_projecte2_grup_beta;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import me.biubiubiu.justifytext.library.JustifyTextView;

public class FragmentFundacio extends Fragment {

    public FragmentFundacio() {
        super(R.layout.fragment_fundacio);
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_fundacio,container,false);
        return root;
    }
}