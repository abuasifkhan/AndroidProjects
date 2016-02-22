package com.example.asif.activityTest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by asif on 08/02/16.
 */
public class MenuScreen extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        //setup button listener
        Button startButton = (Button) findViewById(R.id.play_game);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGame();
            }
        });
    }
    private void startGame(){
        Intent launchGame = new Intent(this, PlayGame.class);
        startActivity(launchGame);
    }
}
