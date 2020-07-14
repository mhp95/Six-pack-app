package com.mhp.six_pack;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;

public class Prefs extends PreferenceActivity {
	
	public int Subjects_total_number;
	public Globals global = new Globals();
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
            
            Subjects_total_number = global.Subjects_total_number;
            
            // for favorites
            Boolean [] favorites = new Boolean [Subjects_total_number];
            for(int x = 1; x < Subjects_total_number+1; x++){
        		String each_subject = "subject_" + String.valueOf(x);
        		favorites [x] = prefs.getBoolean(each_subject, false);
        	}            
    }

}
