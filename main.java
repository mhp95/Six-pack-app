package com.mhp.six_pack;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class main extends Activity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(mhp.sixpack.R.layout.main);
        Button btnabout= (Button) findViewById(mhp.sixpack.R.id.button3);
        btnabout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent in3;
                in3 = new Intent(main.this, about.class);
                startActivity(in3);

            }
        });
        Button btn1 = (Button) findViewById(mhp.sixpack.R.id.button);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent in2;
                in2 = new Intent(main.this, MainActivity.class);
                startActivity(in2);

            }
        });
        Button btn2= (Button) findViewById(mhp.sixpack.R.id.button2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent in3;
                in3 = new Intent(main.this, Favorites.class);
                startActivity(in3);

            }
        });
    }
}