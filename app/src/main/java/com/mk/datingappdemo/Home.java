package com.mk.datingappdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class Home extends AppCompatActivity implements OnItemClickListener {

    private final String[] options = {"Messages", "Will Respond", "Search", "Nearby", "My Matches",
    "Viewed Me", "Meet Me", "Profile", "Favorites"};
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home2);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }

        RecyclerView optionRecyclerView = findViewById(R.id.optionGridRecyclerView);
        //optionRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        optionRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        optionRecyclerView.addItemDecoration(new SpacesItemDecoration(25,25, 25));
        optionRecyclerView.setAdapter(new OptionRecyclerViewAdapter(this));

        RecyclerView prospectsRecyclerView = findViewById(R.id.prospectsRecyclerView);
        prospectsRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        prospectsRecyclerView.addItemDecoration(new SpacesItemDecoration(20,20,10));
        prospectsRecyclerView.setAdapter(new ProspectsRecyclerViewAdapter());

        drawerLayout = findViewById(R.id.mDrawerLayout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                item.setChecked(true);
                drawerLayout.closeDrawers();
                switch (item.getItemId()) {
                    case R.id.profile: {
                        Intent intent = new Intent(Home.this, Profile.class);
                        startActivity(intent);
                    }
                }
                return true;
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(String name) {
        switch (name) {
            case "Profile": {
                Intent intent = new Intent(this, Profile.class);
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
            //View view = LayoutInflater.from(Home.this).inflate(R.layout.home_recycler_view_option_element, parent, false);
            View view = LayoutInflater.from(Home.this).inflate(R.layout.home_recycler_view_images_element, parent, false);
            return new ViewHolder(view);
            //}

        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            ViewHolder viewHolder = (ViewHolder) holder;
            viewHolder.onBind(options[position], R.drawable.boy, listener);
        }

        @Override
        public int getItemCount() {
            return 6;
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            ImageView iv;
            TextView tv;
            ViewHolder(View itemView) {
                super(itemView);
                iv = itemView.findViewById(R.id.imageView);

                //iv = itemView.findViewById(R.id.icon);
                //tv = itemView.findViewById(R.id.label);
            }

            void onBind(final String name, int drawable, final OnItemClickListener listener) {
                iv.setImageResource(drawable);
                //tv.setText(name);
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
