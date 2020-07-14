package com.mhp.six_pack;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Favorites extends Activity {
	
	public int Subjects_total_number;
	public Globals global = new Globals();
	public List<String> Favorites_numbers;
	public List<String> Favorites_strings;
	
	// List view
    private ListView lv;
     
    // Listview Adapter
    ArrayAdapter<String> adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(mhp.sixpack.R.layout.favorites);
		
		// Listview Data
		Favorites_strings = new ArrayList<String>();
		Favorites_numbers = new ArrayList<String>();
		final SharedPreferences shared = getSharedPreferences("Prefs", MODE_PRIVATE);
		Subjects_total_number = global.Subjects_total_number;
		for(int x = 1; x < Subjects_total_number+1; x = x+1) {  
			String each_subject = "subject_" + String.valueOf(x);
    		Boolean b = shared.getBoolean(each_subject, false);
			if(b){
			    String this_subject = "subject_" + String.valueOf(x);
        	    int resID = getResources().getIdentifier(this_subject, "string", getPackageName());
        	    Favorites_strings.add(getResources().getString(resID));
    			String x_string = String.valueOf(x);
    			Favorites_numbers.add(x_string);
			}
		}
		
		
         
        lv = (ListView) findViewById(mhp.sixpack.R.id.list_view);
         
        // Adding items to listview
        adapter = new ArrayAdapter<String>(this, mhp.sixpack.R.layout.list_item_favorites, mhp.sixpack.R.id.favorites_textView, Favorites_strings);
        lv.setAdapter(adapter);
        
		
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                int position, long id) {
            	String favorite_number = Favorites_numbers.get(position);
            	Intent i = new Intent(getApplicationContext(), Show_Subjects.class);
				i.putExtra("subject_number", favorite_number);
				startActivity(i);
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
			startActivity(new Intent(Favorites.this, Favorites.class));
			return true;
		default: 
			return true;
		}	
	}

	

}
