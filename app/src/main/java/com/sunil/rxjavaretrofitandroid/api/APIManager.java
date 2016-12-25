package com.sunil.rxjavaretrofitandroid.api;

import android.content.Context;

import com.sunil.rxjavaretrofitandroid.Friends;
import com.sunil.rxjavaretrofitandroid.event.FriendsEvent;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;

import retrofit2.adapter.rxjava.HttpException;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by kuliza-195 on 12/25/16.
 */

public class APIManager {

    private APIService mAPIService;

    public APIManager(Context context) {
        mAPIService = getAPIServiceEndPoint();

    }

    public APIService getAPIServiceEndPoint() {
        if (mAPIService == null) {
            mAPIService = APIService.Creator.newApiClient();
        }
        return mAPIService;
    }


    public void getFriends() {

        Observable<Friends> friendResponseObservable = mAPIService.getMyFriends()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());

        friendResponseObservable.subscribe(new Observer<Friends>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                //handle error
                if (e instanceof HttpException) {
                    // We had non-2XX http error
                }
                if (e instanceof IOException) {
                    // A network or conversion error happened
                }

                // We don't know what happened. We need to simply convert to an unknown error
            }

            @Override
            public void onNext(Friends response) {
                //handle response
                EventBus.getDefault().post(new FriendsEvent(response));
            }
        });


    }

}
