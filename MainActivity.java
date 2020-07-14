package com.mhp.six_pack;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends ListActivity {

	public ListView lv;
	public Globals global = new Globals();
	public int Subjects_total_number;
	public String[] Subjects;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(mhp.sixpack.R.layout.activity_main);

		Subjects_total_number = global.Subjects_total_number;
		Subjects = new String[Subjects_total_number];
		for(int x = 1; x < Subjects_total_number+1; x = x+1) {
			String this_subject = "subject_" + String.valueOf(x);
    	    int resID = getResources().getIdentifier(this_subject, "string", getPackageName());
			Subjects[x-1] = getResources().getString(resID);
    	}
		
		setListAdapter(new MyAdapter(this, 
				android.R.layout.simple_list_item_1, mhp.sixpack.R.id.textView1,
				Subjects));	
		
		lv = getListView();

		lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                int position, long id) {            	    	
        		Intent i = new Intent(getApplicationContext(), Show_Subjects.class);
        		String Subject_number = String.valueOf(position+1); 
        		i.putExtra("subject_number", Subject_number);
        		startActivity(i);	
            }

          });
				
	}
	
	private class MyAdapter extends ArrayAdapter<String>{

		public MyAdapter(Context context, int resource, int textViewResourceId,
				String[] strings) {
			super(context, resource, textViewResourceId, strings);
			// TODO Auto-generated constructor stub
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View row = inflater.inflate(mhp.sixpack.R.layout.list_item_subjects, parent, false);
			String[] items = Subjects;
			
			TextView tv = (TextView) row.findViewById(mhp.sixpack.R.id.textView1);
			tv.setText(items[position]);
			int color = Color.parseColor("#FF010101");
			tv.setTextColor(color);

			return row;
		}	
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
			startActivity(new Intent(MainActivity.this, Favorites.class));
			return true;
		default: 
			return true;
		}	
	}

}
