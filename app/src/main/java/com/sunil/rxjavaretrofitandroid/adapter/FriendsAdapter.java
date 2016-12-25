package com.sunil.rxjavaretrofitandroid.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sunil.rxjavaretrofitandroid.Friends;
import com.sunil.rxjavaretrofitandroid.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sunil on 12/25/16.
 */

public class FriendsAdapter  extends RecyclerView.Adapter<FriendsAdapter.FriendsViewHolder>{

    private List<Friends.User> mListFriends;

    public FriendsAdapter(List<Friends.User> friendsList){
        mListFriends = friendsList;
    }

    @Override
    public FriendsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_friends, parent, false);
        return new FriendsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(FriendsViewHolder holder, int position) {
        Friends.User friends = mListFriends.get(position);
        holder.nameTextView.setText(friends.getName());
        holder.emailTextView.setText(friends.getEmail());
    }

    @Override
    public int getItemCount() {
        return mListFriends.size();
    }

    class FriendsViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.name)
        TextView nameTextView;
        @BindView(R.id.email)
        TextView emailTextView;

        public FriendsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}