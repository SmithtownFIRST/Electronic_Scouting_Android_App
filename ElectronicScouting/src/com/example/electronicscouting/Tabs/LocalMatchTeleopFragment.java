package com.example.electronicscouting.Tabs;

import com.example.electronicscouting.HomeScreen;
import com.example.electronicscouting.R;
import com.example.electronicscouting.TabsPageAdapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class LocalMatchTeleopFragment extends Fragment {
	private HomeScreen home;
	private View root;
	protected String team;
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		
		this.home = HomeScreen.parent;
		team = home.selectedTeam;
		root = inflater.inflate(R.layout.fragment_local_match_teleop, container, false);
		TextView text = (TextView) root.findViewById(R.id.textView1);
		text.setText("Team " + team + " | Match " + home.selectedMatch + " | " + "Tele-Op");
		
		Button sum = (Button) root.findViewById(R.id.button2);
		Button auto = (Button) root.findViewById(R.id.button1);
		auto.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				home.setEditorPage(TabsPageAdapter.localEditorAutonomous);
				home.switchFragment(new LocalMatchAutonomousFragment());
				
			}
			
		});
		sum.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				home.setEditorPage(TabsPageAdapter.localEditorSummary);
				home.switchFragment(new LocalMatchSummaryFragment());
				
			}
			
			
			
		
		}); 
		return root;
	}
	
	
	public static LocalMatchTeleopFragment newInstance() {
		LocalMatchTeleopFragment l = new LocalMatchTeleopFragment();
		Bundle args = new Bundle();
		l.setArguments(args);
		return l;
	
	}
}
