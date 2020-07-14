package com.mhp.six_pack;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Show_Subjects extends Activity {
	
	public String Subject_number;
	public SharedPreferences shared;
	public SharedPreferences.Editor editor;
	Globals global = new Globals();
	public ImageView iv_favorites;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(mhp.sixpack.R.layout.show_subjects);
        
        iv_favorites = (ImageView) findViewById(mhp.sixpack.R.id.imageView1);
        
        Bundle extras = getIntent().getExtras();
		if (extras != null) {
			Subject_number = extras.getString("subject_number"); 
		}

		
		TextView tv1 = (TextView) findViewById(mhp.sixpack.R.id.textView1);

		String stringName_1 = "subject_" + String.valueOf(Subject_number);;
		int resID_1 = getResources().getIdentifier(stringName_1, "string", getPackageName());
		tv1.setText(resID_1);

		WebView wb= (WebView)  findViewById(mhp.sixpack.R.id.webView);
		wb.getSettings().setJavaScriptEnabled(true);
		wb.getSettings().setLoadWithOverviewMode(true);
		wb.getSettings().setUseWideViewPort(true);
		wb.getSettings().setBuiltInZoomControls(true);
		wb.setBackgroundColor(Color.TRANSPARENT);
        String str1=String.valueOf(Subject_number);
        String file_path = "file:///android_asset/html/"+str1+".htm";
        wb.loadUrl(file_path);


		// Favorites
        shared = getSharedPreferences("Prefs", MODE_PRIVATE);
        editor = shared.edit();
        
        final int subject_number_int = Integer.parseInt(Subject_number);
        final String this_subject = "subject_" + String.valueOf(subject_number_int);
        
        final Boolean b1 = shared.getBoolean(this_subject, false);
        if (b1){
        	iv_favorites.setImageResource(mhp.sixpack.R.drawable.favorite_selected);
		}else{
			iv_favorites.setImageResource(mhp.sixpack.R.drawable.favorite_not_selected);
		}
        
        iv_favorites.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Boolean b2 = shared.getBoolean(this_subject, false);
				if (b2){
				    editor.putBoolean(this_subject, false);
				    editor.apply();
				    iv_favorites.setImageResource(mhp.sixpack.R.drawable.favorite_not_selected);
				    // show message
				    String message = getResources().getString(mhp.sixpack.R.string.favorites_removed);
				    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
				}else{
					editor.putBoolean(this_subject, true);
					editor.apply();
					iv_favorites.setImageResource(mhp.sixpack.R.drawable.favorite_selected);
					// show message
					String message = getResources().getString(mhp.sixpack.R.string.favorites_added);
					Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
				}
		    }
		});
    }


    @Override
	  public boolean onCreateOptionsMenu(Menu menu) {
			MenuInflater inflater = getMenuInflater();
		    inflater.inflate(mhp.sixpack.R.menu.menu, menu);
		    return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case mhp.sixpack.R.id.itemFavorites:
			startActivity(new Intent(Show_Subjects.this, Favorites.class));
			return true;
		default: 
			return true;
		}	
	}
    
}
