package com.example.musicapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class SongAdapter2 extends ArrayAdapter<Song> {
    public SongAdapter2(@NonNull Context context, @NonNull ArrayList<Song> songs) {
        super(context, 0, songs);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;
        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_view2, parent, false);
        }


        Song currentSong = getItem(position);


        TextView songNameTextView = (TextView) listItemView.findViewById(R.id.song_name_tv_2);



        songNameTextView.setText(currentSong.getName());



        TextView artistNameTextView = (TextView) listItemView.findViewById(R.id.song_artist_tv_2);


        artistNameTextView.setText(currentSong.getArtist());





        return listItemView;
    }
}
