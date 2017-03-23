package com.example.leon.cannonball;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

//TODO: Change title of actionbar to About

public class AboutActivity extends AppCompatActivity implements Button.OnClickListener {

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_about );

        ImageButton btn_close = ( ImageButton ) findViewById( R.id.about_close );
        btn_close.setOnClickListener( this );
    }

    @Override
    public void onClick( View v ) {
        if ( v.getId() == R.id.about_close ) finish();
    }
}
