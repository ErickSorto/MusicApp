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

public class SongAdapter extends ArrayAdapter<Song> {
    private int songsSize;
    private Song lastSong;

    public SongAdapter(@NonNull Context context, @NonNull ArrayList<Song> songs) {
        super(context, 0, songs);
        songsSize = songs.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_view, parent, false);
        }


        Song currentSong = getItem(position);


        TextView songNameTextView = (TextView) listItemView.findViewById(R.id.song_name_tv_1);


        songNameTextView.setText(currentSong.getName());


        TextView artistNameTextView = (TextView) listItemView.findViewById(R.id.song_artist_tv_1);


        artistNameTextView.setText(currentSong.getArtist());

        if (songsSize == position) {

        }


        return listItemView;
    }
}
