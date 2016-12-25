package com.sunil.rxjavaretrofitandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.sunil.rxjavaretrofitandroid.api.APIManager;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by sunil on 12/25/16.
 */

public class RetrofitTestActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.test)
    public void testRetrofitClick(){
        startActivity(new Intent(RetrofitTestActivity.this, MainActivity.class));
    }
}
