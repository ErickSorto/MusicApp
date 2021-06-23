package com.example.musicapp;

public class Song {
    String name = "";
    String artist = "";
    String date ="";

    public Song(String name, String artist) {
        this.name = name;
        this.artist = artist;
    }

    public Song(String name, String artist, String date) {
        this.name = name;
        this.artist = artist;
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }
}
