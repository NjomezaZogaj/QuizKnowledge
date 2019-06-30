package com.example.generalknowledgequiz;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import java.util.Calendar;

import mehdi.sakout.aboutpage.AboutPage;
import mehdi.sakout.aboutpage.Element;

public class AboutUs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        Element adsElement = new Element();
        adsElement.setTitle("Advertise here");

        View aboutPage = new AboutPage(this)
                .isRTL(false)
                .setImage(R.drawable.download)
                .setDescription("Projekti ne Android")
                .addItem(new Element().setTitle("Version 1.0"))
                .addItem(adsElement)
                .addGroup("Connect with me")
                .addEmail("statovci661@gmail.com")
                .addFacebook("arbiasa.s")
                .addYoutube("UCPWzCdy0iQnZzjD6jNOAbKA")
                .addInstagram("arbias.statovci")
                .addGitHub("Arbias1")
                .addItem(createCopyright())
                .create();
        setContentView(aboutPage);
    }

    private Element createCopyright() {
        final Element copyright = new Element();
        String copyrightString = String.format("Copyright %d, Prishtine", Calendar.getInstance().get(Calendar.YEAR));
        copyright.setTitle(copyrightString);
        //copyright.setIconTint(R.mimap-hdpi.ic_launcher);
        copyright.setGravity(Gravity.CENTER);
        copyright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AboutUs.this, "Copy right", Toast.LENGTH_SHORT).show();
            }
        });

        return copyright;

    }
}

