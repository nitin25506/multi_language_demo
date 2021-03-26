package com.example.multilanguage;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {


    TextView messageView;
    Button btnChangeLang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        messageView = findViewById(R.id.textView);
        btnChangeLang = findViewById(R.id.btnChangeLang);
        loadLocal();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(getResources().getString(R.string.app_name));

        messageView.setText(getResources().getString(R.string.language));
        btnChangeLang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                changeLanguageDialog();
            }
        });


    }

    //multi language dialog
    private void changeLanguageDialog() {
        final String[] listItem = {"English","ગુજરાતી","हिन्दी"};

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
        alertDialog.setTitle("Choose Language...");
        alertDialog.setSingleChoiceItems(listItem, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                if(i==0){
                    setLocal("en");
                    recreate();
                }
                if(i==1){
                    setLocal("gu");
                    recreate();
                }
                if(i==2){
                    setLocal("hi");
                    recreate();
                }
              
            }
        });

        AlertDialog dialog =alertDialog.create();
        dialog.show();
    }

    private void setLocal(String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,getBaseContext().getResources().getDisplayMetrics());

        SharedPreferences.Editor editor = getSharedPreferences("Settings",MODE_PRIVATE).edit();
        editor.putString("My_lang",lang);
        editor.apply();


    }

    private  void loadLocal(){
        SharedPreferences pref = getSharedPreferences("Settings",MODE_PRIVATE);
        String language = pref.getString("My_lang","");
        setLocal(language);
    }
}