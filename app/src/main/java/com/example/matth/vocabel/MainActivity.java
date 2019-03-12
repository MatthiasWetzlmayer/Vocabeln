package com.example.matth.vocabel;

import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ArrayList<String> words=new ArrayList<>();

        Spinner s=findViewById(R.id.sp);
        final ListView lv=findViewById(R.id.lv);

        ArrayList<String> l=new ArrayList<>();
        l.add("Deutsch-English");
        l.add("English-Deutsch");

        ArrayAdapter a=new ArrayAdapter(this,android.R.layout.simple_list_item_1,l);
        s.setAdapter(a);

        AssetManager as=getAssets();
        String st=null;
        try {
            BufferedReader br=new BufferedReader(new InputStreamReader(as.open("voc.csv")));
            while((st=br.readLine())!=null){
                words.add(st);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList<String> w=new ArrayList<>();
        for(String str:words){
            String[] sp=str.split(";");
            w.add(sp[0]+": "+sp[1]);
        }
        ArrayAdapter lvA=new ArrayAdapter(this,android.R.layout.simple_list_item_1,w);
        lv.setAdapter(lvA);

        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){
                    ArrayList<String> w=new ArrayList<>();
                    for(String str:words){
                        String[] sp=str.split(";");
                        w.add(sp[0]+": "+sp[1]);
                    }
                    ArrayAdapter lvA=new ArrayAdapter(MainActivity.this,android.R.layout.simple_list_item_1,w);
                    lv.setAdapter(lvA);
                }else{
                    ArrayList<String> w=new ArrayList<>();
                    for(String str:words ){
                        String[] sp=str.split(";");
                        w.add(sp[1]+": "+sp[0]);
                    }
                    ArrayAdapter lvA=new ArrayAdapter(MainActivity.this,android.R.layout.simple_list_item_1,w);
                    lv.setAdapter(lvA);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
}
