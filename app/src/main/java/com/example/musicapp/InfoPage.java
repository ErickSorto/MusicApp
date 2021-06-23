package com.example.musicapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class InfoPage {
    String song;
    String artist;
    String date;
    String description;

    public InfoPage(String song, String artist, String date, String description) {
        this.song = song;
        this.artist = artist;
        this.date = date;
        this.description = description;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
