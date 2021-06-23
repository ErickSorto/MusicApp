package com.example.musicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class SongPlayer extends Activity {
    String song;
    String artist;

    public SongPlayer(String song, String artist) {
    }

    public void playSong(){
        TextView songArtistPlaying = (TextView) findViewById(R.id.song_name_playing_tv);
        String displayNameArtist = song + " - " + artist;
        songArtistPlaying.setText(displayNameArtist);
    }

    public String getSong() {
        return song;
    }

    public void setSong(String song) {
        this.song = song;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }
}
