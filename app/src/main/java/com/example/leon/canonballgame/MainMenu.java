package com.example.leon.canonballgame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class MainMenu extends AppCompatActivity implements Button.OnClickListener {

    private Button btn_lvl_1;
    private Button btn_lvl_2;
    private Button btn_lvl_3;
    private Button btn_lvl_4;
    private Button btn_lvl_5;
    private Button btn_play;

    private int levelSelected;

    public void setLevel( int level ) {
        levelSelected = level;
    }

    public void setSelectedLevelButtonColour( Button btn ) {

        btn_lvl_1.setBackgroundColor(0xFF3079AB);
        btn_lvl_2.setBackgroundColor(0xFF3079AB);
        btn_lvl_3.setBackgroundColor(0xFF3079AB);
        btn_lvl_4.setBackgroundColor(0xFF3079AB);
        btn_lvl_5.setBackgroundColor(0xFF3079AB);

        btn.setBackgroundColor(0xFF33CC33);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        btn_lvl_1 = (Button) findViewById(R.id.menu_lvl_1_btn); btn_lvl_1.setOnClickListener(this);
        btn_lvl_2 = (Button) findViewById(R.id.menu_lvl_2_btn); btn_lvl_2.setOnClickListener(this);
        btn_lvl_3 = (Button) findViewById(R.id.menu_lvl_3_btn); btn_lvl_3.setOnClickListener(this);
        btn_lvl_4 = (Button) findViewById(R.id.menu_lvl_4_btn); btn_lvl_4.setOnClickListener(this);
        btn_lvl_5 = (Button) findViewById(R.id.menu_lvl_5_btn); btn_lvl_5.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.menu_lvl_1_btn:
                levelSelected = 1;
                setSelectedLevelButtonColour(btn_lvl_1);
                break;

            case R.id.menu_lvl_2_btn:
                levelSelected = 2;
                setSelectedLevelButtonColour(btn_lvl_2);
                break;

            case R.id.menu_lvl_3_btn:
                levelSelected = 3;
                setSelectedLevelButtonColour(btn_lvl_3);
                break;

            case R.id.menu_lvl_4_btn:
                levelSelected = 4;
                setSelectedLevelButtonColour(btn_lvl_4);
                break;

            case R.id.menu_lvl_5_btn:
                levelSelected = 5;
                setSelectedLevelButtonColour(btn_lvl_5);
                break;

        }
    }

//    @Override
//    protected void onPostCreate(Bundle savedInstanceState) {
//        super.onPostCreate(savedInstanceState);
//
//        // Trigger the initial hide() shortly after the activity has been
//        // created, to briefly hint to the user that UI controls
//        // are available.
//        delayedHide(100);
//    }
//
//    private void toggle() {
//        if (mVisible) {
//            hide();
//        } else {
//            show();
//        }
//    }
//
//    private void hide() {
//        // Hide UI first
//        ActionBar actionBar = getSupportActionBar();
//        if (actionBar != null) {
//            actionBar.hide();
//        }
//        mControlsView.setVisibility(View.GONE);
//        mVisible = false;
//
//        // Schedule a runnable to remove the status and navigation bar after a delay
//        mHideHandler.removeCallbacks(mShowPart2Runnable);
//        mHideHandler.postDelayed(mHidePart2Runnable, UI_ANIMATION_DELAY);
//    }
//
//    @SuppressLint("InlinedApi")
//    private void show() {
//        // Show the system bar
//        mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
//        mVisible = true;
//
//        // Schedule a runnable to display UI elements after a delay
//        mHideHandler.removeCallbacks(mHidePart2Runnable);
//        mHideHandler.postDelayed(mShowPart2Runnable, UI_ANIMATION_DELAY);
//    }
//
//    /**
//     * Schedules a call to hide() in [delay] milliseconds, canceling any
//     * previously scheduled calls.
//     */
//    private void delayedHide(int delayMillis) {
//        mHideHandler.removeCallbacks(mHideRunnable);
//        mHideHandler.postDelayed(mHideRunnable, delayMillis);
//    }
}
