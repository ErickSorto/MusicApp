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

public class AboutActivity extends AppCompatActivity {
    private String displaySongArtist;
    private String songName, artistName, date, description;
    private int currentIndex;
    private boolean isListRight;
    private boolean isPlay = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        ImageView playImageView = (ImageView) findViewById(R.id.play_image_view);
        TextView display = findViewById(R.id.song_name_playing_tv);

        Bundle bn = getIntent().getExtras();
        String name = bn.getString("displaySongArtist");
        isPlay = bn.getBoolean("isPlay");
        currentIndex = bn.getInt("index");
        isListRight = bn.getBoolean("isListRight");
        displaySongArtist = String.valueOf(name);

        if (isListRight) { //Checks which position the index should be on
            InfoPage aboutPage = infoPages().get(currentIndex);
            songName = aboutPage.getSong();
            artistName = aboutPage.getArtist();
            date = aboutPage.getDate();
            description = aboutPage.getDescription();
        } else if (isListRight == false) {
            InfoPage aboutPage = infoPages2().get(currentIndex);
            songName = aboutPage.getSong();
            artistName = aboutPage.getArtist();
            date = aboutPage.getDate();
            description = aboutPage.getDescription();
        }

        if (String.valueOf(name) == "null") {
            display.setText("Choose Music");
        } else {
            display.setText(String.valueOf(name));
        }

        ImageView infoImage = (ImageView) findViewById(R.id.info_song_playing_iv);
        playImageView.setImageResource(R.drawable.pause_button);
        infoImage.setImageResource(R.drawable.home);
        infoImage.setPadding(32, 32, 32, 32);

        infoImage.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers category is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link NumbersActivity}
                Intent mainActivityIntent = new Intent(AboutActivity.this, MainActivity.class);

                mainActivityIntent.putExtra("displaySongArtist", displaySongArtist);
                mainActivityIntent.putExtra("isPlay", isPlay);
                mainActivityIntent.putExtra("index", currentIndex);
                mainActivityIntent.putExtra("isListRight", isListRight);
                // Start the new activity
                finish();
                startActivity(mainActivityIntent);

            }
        });

        ListView listView = (ListView) findViewById(R.id.list);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                int index = listView.getPositionForView(v);
                Song song = arrayOfSongs(1).get(index);

                InfoPage infoPage = infoPages().get(index);
                songName = infoPage.getSong();
                artistName = infoPage.getArtist();
                date = infoPage.getDate();
                description = infoPage.getDescription();
                currentIndex = index;
                displaySongArtist = song.getName() + " - " + song.getArtist();
                isListRight = true;
                playImageView.setImageResource(R.drawable.pause_button);
                isPlay = false;

                display.setText(displaySongArtist);
                displayInformation();

            }
        });

        ListView listView2 = (ListView) findViewById(R.id.list2);

        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                int index = listView2.getPositionForView(v);
                Song song = arrayOfSongs(2).get(index);

                InfoPage infoPage = infoPages2().get(index);
                songName = infoPage.getSong();
                artistName = infoPage.getArtist();
                date = infoPage.getDate();
                description = infoPage.getDescription();
                currentIndex = index;
                isListRight = false;
                displaySongArtist = song.getName() + " - " + song.getArtist();

                display.setText(displaySongArtist);
                playImageView.setImageResource(R.drawable.pause_button);
                isPlay = false;
                displayInformation();

            }
        });

        //Changes to correct image after changing activities
        if (isPlay) {
            playImageView.setImageResource(R.drawable.play_button);
        } else {
            playImageView.setImageResource(R.drawable.pause_button);
        }

        SongAdapter adapter = new SongAdapter(this, arrayOfSongs(1));
        SongAdapter2 adapter2 = new SongAdapter2(this, arrayOfSongs(2));

        displayInformation();
        listView.setAdapter(adapter);
        listView2.setAdapter(adapter2);
        playOrPause(); //Switches pause and play after click
    }

    public void playOrPause() {
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

    public ArrayList<InfoPage> infoPages() { //Stores infopage
        ArrayList<InfoPage> aboutPagesArray = new ArrayList<>();

        aboutPagesArray.add(new InfoPage("Ignite", "Zedd", "September 26, 2016", "\"Ignite\" is the title of a song Zedd produced for the 2016 League of Legends World championship. It was released via the game's YouTube channel on September 26, 2016, and via Spotify and Apple Music the following day."));
        aboutPagesArray.add(new InfoPage("New Horizon", "KEPIK, Nick Ledesma, Luma", "November 20, 2020", "\"New Horizon is a song about staying hopeful and looking for a brighter tomorrow. With all that's going on in our world, I hope everyone keeps their eyes open for that New Horizon.\" - KEPIK"));
        aboutPagesArray.add(new InfoPage("Fly Away", "Rameses B", "February 18, 2019", "A sense of weightlessness fills the melodies of this track as it progresses through the soundscapes. A progressively chill atmosphere surrounds you as the delicate percussive hits and smooth bass keep you, in a sense, grounded."));
        aboutPagesArray.add(new InfoPage("Burning Heart", "Svrcina", "February 5, 2016", "NA"));
        aboutPagesArray.add(new InfoPage("Daydreamer", "AURORA", "June 7, 2019", "\"Daydreamer\" is the fourth track on the album A Different Kind of Human - Step 2. It was written by AURORA and Peter Wade and produced by AURORA, Askjell, Tim Bran, and Roy Kerr."));
        aboutPagesArray.add(new InfoPage("Un_gravitify", "Cashell", "January 17, 2008", "\"Un-Gravitify\" is the main theme as well as Team Heroes' theme in Sonic Riders: Zero Gravity. It was composed by Kenichi Tokoi, written by Runblebee and performed by Cashell."));
        aboutPagesArray.add(new InfoPage("Wind", "Akeboshi", "August 8, 2002", "Wind (ワインド, Waindo), performed by Akeboshi (明星), was the first ending song in the Japanese version of Part I of the Naruto series. It was replaced by Harmonia in Episode 26."));

        return aboutPagesArray;
    }

    public ArrayList<InfoPage> infoPages2() {
        ArrayList<InfoPage> aboutPagesArray = new ArrayList<>();
        aboutPagesArray.add(new InfoPage("Infra-Red", "Three Days Grace", "March 9, 2018", "This song is about two people with a secret relationship who understand each other in ways nobody else ever can.They know what the other thinks about them even though it was never said, and are perfect for each other."));
        aboutPagesArray.add(new InfoPage("Mad World", "Tears For Fears", "September 20, 1982", "“Mad World” was the 1982 hit single from British duo Tears For Fears, appearing as the second track on their debut studio album “The Hurting”. The track was written by member Roland Orzabal, with production done by Tears for Fears producer Chris Hughes, and record producer Ross Cullum."));
        aboutPagesArray.add(new InfoPage("joy.", "for KING & COUNTRY", "May 18, 2018", "The lyrics cut through the cacophony of societal noise to remind us about what is important: “Oh, hear my prayer tonight. ‘Cause this is do or die. The time has come to make a choice. I choose joy.”"));
        aboutPagesArray.add(new InfoPage("Mojo So Dope", "Kid Cudi", "November 9, 2010", "During a pre-release playback of some of Mr. Rager's tracks to music journalists and insiders, Cudi explained this cut is a message to the bloggers who are dissing him."));
        aboutPagesArray.add(new InfoPage("Becoming Insane", "Infected Mushroom", "January 29th, 2007", "Psytrance was a different beast back in 2007 and nobody could harness it’s power quite like Infected Mushroom did when they released their ‘Vicious Delicious’ album. “Becoming Insane” was one of their (if not the most) popular tracks they have released. Between the fast paced steel guitar, heart pounding bass, and multi-lingual lyrics. What more could you ask for?"));
        aboutPagesArray.add(new InfoPage("The Middle", "Jimmy Eat World", "November 5, 2001", "\"The Middle\" is a song by American rock band Jimmy Eat World. It was released in October 2001 as the second single of their fourth album Bleed American. It was a number-five hit on the US Billboard Hot 100 in 2002 and reached the top 50 in Australia, Ireland, New Zealand and the United Kingdom. The song was a breakthrough hit for Jimmy Eat World, who had self-financed the recording of the Bleed American album after being dropped by Capitol Records in 1999."));
        aboutPagesArray.add(new InfoPage("Obstacles\"", "Syd Matters", "May 5th, 2005", "\"Obstacles\" is a single by Syd Matters from their album, Someday We Will Foresee Obstacles and is featured in the reveal trailer of Life is Strange and at the end of Episode 1, as well as at the end of Episode 5 in the \"Sacrifice Arcadia Bay\" ending."));

        return aboutPagesArray;
    }


    public void displayInformation() { //displays description, artist, song, date
        TextView songNameTextView = (TextView) findViewById(R.id.song_name_info_text_view);
        TextView songDateTextView = (TextView) findViewById(R.id.song_date_info_text_view);
        TextView songArtistTextView = (TextView) findViewById(R.id.song_artist_info_text_view);
        TextView songDescriptionTextView = (TextView) findViewById(R.id.description_tv);

        songNameTextView.setText(songName);
        songDateTextView.setText(date);
        songDescriptionTextView.setText(description);
        songArtistTextView.setText(artistName);
    }

    public ArrayList<Song> arrayOfSongs(int arrayListNum) { //Stores songs for both lists using 2 different arrays<song>

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