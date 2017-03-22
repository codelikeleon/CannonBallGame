package com.example.leon.cannonball;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

/** //TODO: Documentation
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class MainMenuActivity extends AppCompatActivity implements Button.OnClickListener {

    private Button btn_lvl_1;
    private Button btn_lvl_2;
    private Button btn_lvl_3;
    private Button btn_lvl_4;
    private Button btn_lvl_5;
    private Button btn_play;
    private ImageButton btn_help;
    private ImageButton btn_about;

    private int levelSelected;

    public void setSelectedLevelButtonColour( Button btn ) {

        btn_lvl_1.setBackgroundColor( 0xFF3079AB );
        btn_lvl_2.setBackgroundColor( 0xFF3079AB );
        btn_lvl_3.setBackgroundColor( 0xFF3079AB );
        btn_lvl_4.setBackgroundColor( 0xFF3079AB );
        btn_lvl_5.setBackgroundColor( 0xFF3079AB );

        btn.setBackgroundColor( 0xFF33CC33 );
    }

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main_menu );

        btn_lvl_1 = ( Button ) findViewById( R.id.menu_lvl_1_btn ); btn_lvl_1.setOnClickListener( this );
        btn_lvl_2 = ( Button ) findViewById( R.id.menu_lvl_2_btn ); btn_lvl_2.setOnClickListener( this );
        btn_lvl_3 = ( Button ) findViewById( R.id.menu_lvl_3_btn ); btn_lvl_3.setOnClickListener( this );
        btn_lvl_4 = ( Button ) findViewById( R.id.menu_lvl_4_btn ); btn_lvl_4.setOnClickListener( this );
        btn_lvl_5 = ( Button ) findViewById( R.id.menu_lvl_5_btn ); btn_lvl_5.setOnClickListener( this );
        btn_play  = ( Button ) findViewById( R.id.menu_play_btn );  btn_play.setOnClickListener( this );
        btn_help  = ( ImageButton ) findViewById( R.id.menu_help_btn );  btn_help.setOnClickListener( this );
        btn_about = ( ImageButton ) findViewById( R.id.menu_about_btn ); btn_about.setOnClickListener( this );

        Intent media = new Intent( this, MediaService.class );
        startService( media );
    }

    @Override
    public void onClick( View v ) {

        switch ( v.getId() ) {
            case R.id.menu_lvl_1_btn:
                levelSelected = 1;
                System.out.println("button cilcked");
                setSelectedLevelButtonColour( btn_lvl_1 );
                break;

            case R.id.menu_lvl_2_btn:
                levelSelected = 2;
                setSelectedLevelButtonColour( btn_lvl_2 );
                break;

            case R.id.menu_lvl_3_btn:
                levelSelected = 3;
                setSelectedLevelButtonColour( btn_lvl_3 );
                break;

            case R.id.menu_lvl_4_btn:
                levelSelected = 4;
                setSelectedLevelButtonColour( btn_lvl_4 );
                break;

            case R.id.menu_lvl_5_btn:
                levelSelected = 5;
                setSelectedLevelButtonColour( btn_lvl_5 );
                break;

            case R.id.menu_play_btn:
                Intent gameActivity = new Intent( this, CannonBallActivity.class );
                startActivity( gameActivity );
                break;

            case R.id.menu_help_btn:
                Intent helpActivity = new Intent( this, HelpActivity.class );
                startActivity( helpActivity );  //Create and start help activity
                break;

            case R.id.menu_about_btn:
                Intent aboutActivity = new Intent( this, AboutActivity.class );
                startActivity( aboutActivity );
                break;

        }
    }
}