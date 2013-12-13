package com.example.electronicscouting;

import com.example.electronicscouting.Tabs.GlobalFragment;
import com.example.electronicscouting.Tabs.LocalTeamChooserFragment;
import com.example.electronicscouting.Tabs.LocalMatchAutonomousFragment;
import com.example.electronicscouting.Tabs.LocalMatchSummaryFragment;
import com.example.electronicscouting.Tabs.LocalMatchChooserFragment;
import com.example.electronicscouting.Tabs.LocalMatchTeleopFragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.widget.Toast;
 
public class TabsPageAdapter extends FragmentStatePagerAdapter {
	
	public static final int local = 0;
	public static final int global = 1;
	public static final int localTeamChooser = 0;
	public static final int localMatchChooser = 1;
	public static final int matchEditor = 2;
	public static final int localEditorSummary = 0;
	public static final int localEditorAutonomous = 1;
	public static final int localEditorTeleop = 2;
	public static final int numberOfTabs = 2;
	
    public TabsPageAdapter(FragmentManager fm) {
        super(fm);
    }
 

    @Override
    public Fragment getItem(int index) { 
        switch (index) { //The tab Number
        case local:
            switch(HomeScreen.parent.localStep.getStep()) {
			case localTeamChooser:
				return LocalTeamChooserFragment.newInstance();
			case localMatchChooser:
				return LocalMatchChooserFragment.newInstance();
			case matchEditor:
				switch(HomeScreen.parent.getEditorPage()) {
				case localEditorSummary:
					return LocalMatchSummaryFragment.newInstance();
				case localEditorAutonomous:
					return LocalMatchAutonomousFragment.newInstance();
				case localEditorTeleop:
					return LocalMatchTeleopFragment.newInstance();
				}
				return null;
			default:
				Toast.makeText(HomeScreen.parent, "Error Showing Layout", Toast.LENGTH_SHORT).show();
				break;
            }
        case global:
            return new GlobalFragment();
        }
        return null;
    }
 
    @Override
    public int getItemPosition(Object object) {
       return POSITION_NONE;
    }

    @Override
    public int getCount() {
        return numberOfTabs;
    }
    
 
 
}