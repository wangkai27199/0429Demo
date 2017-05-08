package com.example.dell.a0429demo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.dell.a0429demo.fragment.FirstFragment;
import com.example.dell.a0429demo.fragment.SecondFragment;
import com.example.dell.a0429demo.fragment.ThirdFragment;

public class MainActivity extends FragmentActivity implements View.OnClickListener {

    private Button btn_jieshao;
    private Button btn_jianshe;
    private Button btn_zhize;
    private Fragment fragment;
    private FirstFragment firstFragment;
    private SecondFragment secondFragment;
    private ThirdFragment thirdFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_jieshao = (Button) findViewById(R.id.btn_jieshao);
        btn_jianshe = (Button) findViewById(R.id.btn_jianshe);
        btn_zhize = (Button) findViewById(R.id.btn_zhize);


        btn_jieshao.setOnClickListener(this);
        btn_jianshe.setOnClickListener(this);
        btn_zhize.setOnClickListener(this);
        if (firstFragment == null){
            firstFragment = new FirstFragment();
        }
        addFragment(firstFragment);
    }

    private void addFragment(Fragment f){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        if (fragment!= null){
            transaction.hide(fragment);
        }
        if(!f.isAdded()){
            transaction.add(R.id.fragmentLayout,f);
        }

        transaction.show(f);
        transaction.commit();
        fragment = f;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btn_jieshao:
                if (firstFragment == null){
                    fragment = new FirstFragment();
                }
                addFragment(firstFragment);
                break;
            case R.id.btn_zhize:
                if (secondFragment == null){
                    secondFragment = new SecondFragment();
                }
                addFragment(secondFragment);
                break;
            case R.id.btn_jianshe:
                if (thirdFragment == null){
                    thirdFragment = new ThirdFragment();
                }
                addFragment(thirdFragment);
                break;
        }
    }
}
