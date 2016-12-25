package com.sunil.rxjavaretrofitandroid.event;

import com.sunil.rxjavaretrofitandroid.Friends;

/**
 * Created by sunil on 12/25/16.
 */

public class FriendsEvent {

    private Friends friends;

    public FriendsEvent(Friends friends) {
        this.friends = friends;
    }

    public Friends getFriends() {
        return friends;
    }
}
