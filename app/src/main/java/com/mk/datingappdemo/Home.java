package com.mk.datingappdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class Home extends AppCompatActivity implements OnItemClickListener {

    private final String[] options = {"Messages", "Will Respond", "Search", "Nearby", "My Matches",
    "Viewed Me", "Meet Me", "Profile", "Favorites"};
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        RecyclerView optionRecyclerView = findViewById(R.id.optionGridRecyclerView);
        optionRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        optionRecyclerView.addItemDecoration(new SpacesItemDecoration(25,25, 50));
        optionRecyclerView.setAdapter(new OptionRecyclerViewAdapter(this));

        RecyclerView prospectsRecyclerView = findViewById(R.id.prospectsRecyclerView);
        prospectsRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        prospectsRecyclerView.addItemDecoration(new SpacesItemDecoration(20,20,10));
        prospectsRecyclerView.setAdapter(new ProspectsRecyclerViewAdapter());

    }

    @Override
    public void onItemClick(String name) {
        switch (name) {
            case "Profile": {
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            }
            break;

        }
    }

    private class OptionRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private OnItemClickListener listener;

        OptionRecyclerViewAdapter(OnItemClickListener listener) {
            super();
            this.listener = listener;
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            //if(viewType == 0) {
            View view = LayoutInflater.from(Home.this).inflate(R.layout.home_recycler_view_option_element, parent, false);
            return new ViewHolder(view);
            //}

        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            ViewHolder viewHolder = (ViewHolder) holder;
            viewHolder.onBind(options[position], R.mipmap.ic_launcher, listener);
        }

        @Override
        public int getItemCount() {
            return 9;
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            ImageView iv;
            TextView tv;
            ViewHolder(View itemView) {
                super(itemView);
                iv = itemView.findViewById(R.id.icon);
                tv = itemView.findViewById(R.id.label);
            }

            void onBind(final String name, int drawable, final OnItemClickListener listener) {
                iv.setImageResource(drawable);
                tv.setText(name);
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.onItemClick(name);
                    }
                });
            }
        }
    }

    private class ProspectsRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(Home.this).inflate(R.layout.home_recycler_view_images_element, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            ViewHolder viewHolder = (ViewHolder) holder;
            viewHolder.iv.setImageResource(R.drawable.person);
        }

        @Override
        public int getItemCount() {
            return 10;
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            ImageView iv;
            ViewHolder(View itemView) {
                super(itemView);
                iv = itemView.findViewById(R.id.imageView);
            }
        }

    }
}
