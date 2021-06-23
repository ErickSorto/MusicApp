package com.example.musicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private String displaySongArtist;
    private boolean isPlay = true;
    private boolean isChosen = false; // allows user to click info page after choosing song
    private int currentIndex;
    private boolean isListRight;
    private boolean isInfoDisplayed = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView infoImage = (ImageView) findViewById(R.id.info_song_playing_iv);
        TextView displaySongTextView = (TextView) findViewById(R.id.song_name_playing_tv);
        ImageView playImageView = (ImageView) findViewById(R.id.play_image_view);


        Bundle bn = getIntent().getExtras();

        if (bn != null) { //loads information after clicking home button from info page
            String name = bn.getString("displaySongArtist");
            displaySongArtist = String.valueOf(name);
            displaySongTextView.setText(displaySongArtist);
            isPlay = bn.getBoolean("isPlay");
            currentIndex = bn.getInt("index");
            isChosen = true;
            isListRight = bn.getBoolean("isListRight");
        }


        if (isPlay) { //sets image to play or pause after loading activity
            playImageView.setImageResource(R.drawable.play_button);
        } else {
            playImageView.setImageResource(R.drawable.pause_button);
        }


        ListView listView = (ListView) findViewById(R.id.list_left);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                int index = listView.getPositionForView(v); //retrieves index of clicked position
                Song song = arrayOfSongs(1).get(index); // creates song object based on position clicked
                displaySongArtist = song.getName() + " - " + song.getArtist(); //creates text for play display (song) + (artist)
                displaySongTextView.setText(displaySongArtist); //displays song and artist
                playImageView.setImageResource(R.drawable.pause_button); //plays song after new song is clicked
                isPlay = false;
                isChosen = true; // allows user to click info page after choosing song
                currentIndex = index;
                isListRight = true;
            }
        });


        ListView listView2 = (ListView) findViewById(R.id.list_right);

        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                int index = listView2.getPositionForView(v); //retrieves index of clicked position
                Song song = arrayOfSongs(2).get(index); // creates song object based on position clicked
                displaySongArtist = song.getName() + " - " + song.getArtist(); //creates text for play display (song) + (artist)
                displaySongTextView.setText(displaySongArtist); //displays song and artist
                playImageView.setImageResource(R.drawable.pause_button); //plays song after new song is clicked

                isPlay = false; //switches status of play or pause
                isChosen = true;
                isListRight = false;
                currentIndex = index;
            }
        });


        // Set a click listener on that View
        infoImage.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers category is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link NumbersActivity}
                if (isChosen) {
                    Intent infoIntent = new Intent(MainActivity.this, AboutActivity.class);
                    infoIntent.putExtra("displaySongArtist", displaySongArtist);
                    infoIntent.putExtra("isPlay", isPlay);
                    infoIntent.putExtra("index", currentIndex);
                    infoIntent.putExtra("isListRight", isListRight);

                    // Start the new activity

                    finish();
                    startActivity(infoIntent);
                }
            }
        });


        SongAdapter adapter = new SongAdapter(this, arrayOfSongs(1));
        listView.setAdapter(adapter);

        SongAdapter2 adapter2 = new SongAdapter2(this, arrayOfSongs(2));
        listView2.setAdapter(adapter2);

        playAndPause();
    }

    public void playAndPause() {
        ImageView playImageView = (ImageView) findViewById(R.id.play_image_view);

        playImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPlay) {
                    playImageView.setImageResource(R.drawable.pause_button);
                } else {
                    playImageView.setImageResource(R.drawable.play_button);
                }
                isPlay = !isPlay;
            }
        });

    }

    public ArrayList<Song> arrayOfSongs(int arrayListNum) {

        if (arrayListNum == 1) {

            ArrayList<Song> songs = new ArrayList<Song>();
            songs.add(new Song("Ignite", "Zedd"));
            songs.add(new Song("New Horizon", "KEPIK, Nick Ledesma, Luma"));
            songs.add(new Song("Fly Away", "Rameses B"));
            songs.add(new Song("Burning Heart", "Svrcina"));
            songs.add(new Song("Daydreamer", "AURORA"));
            songs.add(new Song("Un_gravitify", "Cashell"));
            songs.add(new Song("Wind", "Akeboshi"));

            return songs;
        } else if (arrayListNum == 2) {

            ArrayList<Song> songs2 = new ArrayList<Song>();
            songs2.add(new Song("Infra-Red", "Three Days Grace"));
            songs2.add(new Song("Mad World", "Tears For Fears"));
            songs2.add(new Song("joy.", "for KING & COUNTRY"));
            songs2.add(new Song("Mojo So Dope", "Kid Cudi"));
            songs2.add(new Song("Becoming Insane", "Infected Mushroom"));
            songs2.add(new Song("The Middle", "Jimmy Eat World"));
            songs2.add(new Song("Obstacles", "Syd Matters"));

            return songs2;
        }

        return null;
    }
}