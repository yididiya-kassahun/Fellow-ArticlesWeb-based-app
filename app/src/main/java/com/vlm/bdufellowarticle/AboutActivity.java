package com.vlm.bdufellowarticle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        Toolbar myToolbar = findViewById(R.id.toolbar);
        myToolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back));
        myToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                home();
            }
        });
    }

    public void home() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void evasueBibleStudy(View view) {
        startActivity(new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://play.google.com/store/apps/details?id=" + "com.mekimsc.myapp.evasuebiblestudy")));
    }

    public void goTandemApp(View view) {
        startActivity(new Intent(Intent.ACTION_VIEW,
                Uri.parse("https://play.google.com/store/apps/details?id=com.gotandem.wlbdufellow")));
    }

    public void goTandem(View view) {
        startActivity(new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://bdufellow.gotandem.com")));
    }

    // ********** Addresses **********

    public void facebook(View view) {
        startActivity(new Intent(Intent.ACTION_VIEW,
                Uri.parse("https://www.facebook.com/BDU-Fellowship-VLM-180332802610812")));
    }

    public void instagram(View view) {
        startActivity(new Intent(Intent.ACTION_VIEW,
                Uri.parse("https://www.instagram.com/bdu_fellowship_vlm/")));
    }

    public void youtube(View view) {
        startActivity(new Intent(Intent.ACTION_VIEW,
                Uri.parse("https://www.youtube.com/channel/UCbnR6qFRgmTCKYYcxYIAP2A")));
    }

    public void telegram(View view) {
        startActivity(new Intent(Intent.ACTION_VIEW,
                Uri.parse("https://t.me/bdu_fellowship")));
    }


}