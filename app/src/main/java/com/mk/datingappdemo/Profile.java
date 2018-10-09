package com.mk.datingappdemo;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class Profile extends AppCompatActivity implements View.OnClickListener {

    ImageView personImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(R.string.username);
        ListView profileDetails = findViewById(R.id.profile_list);
        Button uploadButton = findViewById(R.id.uploadImage);
        personImageView = findViewById(R.id.personImage);
        LinearLayout containerView = findViewById(R.id.containerView);
        String[] labels = getResources().getStringArray(R.array.profile_labels);
        String[] values = getResources().getStringArray(R.array.profile_values);
        for(int i=0;i<labels.length;i++) {
            View view = LayoutInflater.from(this).inflate(R.layout.profile_list_element, null, false);
            ((TextView)view.findViewById(R.id.label)).setText(labels[i]);
            ((TextView)view.findViewById(R.id.value)).setText(values[i]);
            containerView.addView(view);

        }


        ProfileListAdapter adapter = new ProfileListAdapter(this, R.layout.profile_list_element);
        profileDetails.setAdapter(adapter);
        uploadButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.uploadImage: {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,
                        "Select file to upload "), 0);
            }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 0) {
            if(resultCode == RESULT_OK && data != null) {
                Uri uri = data.getData();
                personImageView.setImageURI(uri);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private class ProfileListAdapter extends ArrayAdapter<String> {

        private String[] labels, values;
        ProfileListAdapter(@NonNull Context context, int resource) {
            super(context, resource);
            labels = context.getResources().getStringArray(R.array.profile_labels);
            values = context.getResources().getStringArray(R.array.profile_values);
        }

        @Override
        public int getCount() {
            return labels.length;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            TextView label = null, value = null;
            convertView = getLayoutInflater().inflate(R.layout.profile_list_element, parent, false);
            label = convertView.findViewById(R.id.label);
            value = convertView.findViewById(R.id.value);
            label.setText(labels[position]);
            value.setText(values[position]);
            return convertView;
        }
    }

}
