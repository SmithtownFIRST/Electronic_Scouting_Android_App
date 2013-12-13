package com.example.electronicscouting;


import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.TreeMap;

import com.example.electronicscouting.Tabs.GlobalFragment;
import com.example.electronicscouting.Tabs.LocalTeamChooserFragment;
import com.example.electronicscouting.Tabs.LocalFragmentStep;
import com.example.electronicscouting.Tabs.LocalMatchChooserFragment;
import com.example.electronicscouting.data.MatchOverviewInfo;
import com.example.electronicscouting.data.MatchInfoKey;
import com.example.electronicscouting.data.ScoutSaver;
import com.example.electronicscouting.data.Team;
import com.example.electronicscouting.data.MatchInfo;
import com.example.electronicscouting.Tabs.*;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.widget.Toast;
 
public class HomeScreen extends FragmentActivity {
	
     public static HomeScreen parent; //Global Singleton instance. Was lazy and didn't feel like passing an instance every time a new fragment was made
     protected ViewPager viewPager;
     public String selectedTeam = "";
     public int selectedMatch = 0;
     public LocalStepWatcher localStep = new LocalStepWatcher();
	 public HashMap<MatchInfoKey, MatchInfo> matchData = new HashMap<MatchInfoKey, MatchInfo>();
	 public TreeMap<Team, MatchOverviewInfo> teamData = new TreeMap<Team, MatchOverviewInfo>();
	 private int matchViewerPage = TabsPageAdapter.localEditorSummary;
	 protected TabsPageAdapter mAdapter;

	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        parent = this;
	       
	        try {
				ScoutSaver.loadData();
			} catch (IOException e) {
				Toast.makeText(this, "Error at onCreate(): " + e.getMessage(), Toast.LENGTH_LONG).show(); 
				e.printStackTrace();
			}
	        setContentView(R.layout.activity_home_screen);
	        final ActionBar bar = getActionBar();
	        viewPager = (ViewPager) findViewById(R.id.pager);
	        mAdapter = new TabsPageAdapter(getSupportFragmentManager());
	        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
	        	 
	            @Override
	            public void onPageSelected(int position) {
	                bar.setSelectedNavigationItem(position);
	                
	            }
	         
	            @Override
	            public void onPageScrolled(int arg0, float arg1, int arg2) {
	            }
	         
	            @Override
	            public void onPageScrollStateChanged(int arg0) {
	            } 
	        });
	        viewPager.setAdapter(mAdapter);
	      //  getSupportFragmentManager().beginTransaction().add(new LocalFragment(), null).commit();
	        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
	        ft.add(new LocalTeamChooserFragment(), null);
	        ft.commit();
	        bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
	        LocalTeamChooserFragment localFragment = new LocalTeamChooserFragment();
	        GlobalFragment globalFragment = new GlobalFragment();
	        bar.addTab(bar.newTab().setText("Local").setTabListener(new MyTabsListener(this, localFragment)));
	        bar.addTab(bar.newTab().setText("Global").setTabListener(new MyTabsListener(this, globalFragment)));
	       
	    }
	    
	    public void setEditorPage(int page) {
	    	this.matchViewerPage = page;
	    }
	    
	    public int getEditorPage() {
	    	return matchViewerPage;
	    }
	    
	    public void setupData() {
	    	 String[] defaultTeams = {
		        		"810", 
		        		"2001",
		        		"912",
		        		"100",
		        		"9304",
		        		"234",
		        		"2452",
		        		"384",
		        		"2049"
		        }; 
	    	 for (String team : defaultTeams) {
	    		 MatchOverviewInfo mInfo = new MatchOverviewInfo();
	    		 mInfo.teamName = team;
	    		 teamData.put(new Team(Integer.parseInt(team)), mInfo);
	    	 }
	    
	    }
	    
	    public void switchFragment(Fragment newFragment) {
	    	if (newFragment instanceof LocalMatchChooserFragment) {
	    		localStep.setStep(1);
	    	} else if (newFragment instanceof LocalMatchSummaryFragment) {
	    		localStep.setStep(2);
	    	}
	    	mAdapter.notifyDataSetChanged();
	    	viewPager.setAdapter(mAdapter); 
	    }
	    @Override
	    public boolean onKeyDown(int keyCode, KeyEvent evt) {
	    	if (keyCode == KeyEvent.KEYCODE_BACK) {
	    		if (localStep.getStep() != 0) {
	    			localStep.setStep(localStep.getStep() - 1);
	    		mAdapter.notifyDataSetChanged();
	    	}
	    		setEditorPage(TabsPageAdapter.localEditorSummary); //Sets default editor page back to summary. So the next time it opens, it ensures it opens to Summary
	    		return true;
	    	}
	    	
	    	return super.onKeyDown(keyCode, evt);
	    }

	


@Override
public void onPause() {
	super.onPause();
}

@Override
public void onStop() {
	super.onStop();
	try {
		ScoutSaver.saveData();
	} catch (IOException e) {
		Toast.makeText(this, "Failed Saving Data: " + e.getMessage(), Toast.LENGTH_SHORT).show();
		e.printStackTrace();
	}
	//Toast.makeText(this, "Stopped", Toast.LENGTH_SHORT).show();
}
class MyTabsListener implements ActionBar.TabListener {

    private Fragment fragment;
    private HomeScreen home;
    public MyTabsListener(HomeScreen home, Fragment fragment) {
        this.fragment = fragment;
        this.home = home;
    }

    public void onTabReselected(Tab tab, android.app.FragmentTransaction ft) {
    }

    public void onTabSelected(Tab tab, android.app.FragmentTransaction ft) {
    	
    	home.viewPager.setCurrentItem(tab.getPosition());
    	
    }
    
   

    public void onTabUnselected(Tab tab, android.app.FragmentTransaction ft) {

    }
  }
}


