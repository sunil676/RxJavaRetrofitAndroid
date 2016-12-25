package com.sunil.rxjavaretrofitandroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.sunil.rxjavaretrofitandroid.adapter.FriendsAdapter;
import com.sunil.rxjavaretrofitandroid.api.APIManager;
import com.sunil.rxjavaretrofitandroid.event.FriendsEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscription;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.recyclerView1)
    RecyclerView mRecyclerView;
    @BindView(R.id.progressbar)
    ProgressBar progressbar;

    private APIManager mAPIManager;
    private Subscription mSubscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setActionBarWithBackButton();

        progressbar.setVisibility(View.GONE);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAPIManager = new APIManager(this);

        loadFriends();
    }

    protected void setActionBarWithBackButton() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(getResources().getDrawable(R.drawable.navigate_back));
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }

    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mSubscription != null)
            mSubscription.unsubscribe();
        EventBus.getDefault().unregister(this);
    }

    public void loadFriends() {
        // use loader
        progressbar.setVisibility(View.VISIBLE);
        mAPIManager.getFriends();
    }

    @Subscribe
    public void OnEvent(FriendsEvent response) {
        // cancel loader
        progressbar.setVisibility(View.GONE);
        if (response.getFriends() != null) {
            if (response.getFriends().getUser().size() < 1) {
                Toast.makeText(this, "No Data available", Toast.LENGTH_LONG).show();
            } else {
                showFriends(response.getFriends());
            }
        }
    }

    public void showFriends(Friends friends) {
        FriendsAdapter adapter = new FriendsAdapter(friends.getUser());
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }
}
