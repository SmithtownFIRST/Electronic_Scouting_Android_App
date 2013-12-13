package com.example.electronicscouting.Tabs;

import com.example.electronicscouting.HomeScreen;
import com.example.electronicscouting.R;
import com.example.electronicscouting.TabsPageAdapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

public class LocalMatchSummaryFragment extends Fragment {
	private HomeScreen home;
	private View root;
	protected String team;
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		
		this.home = HomeScreen.parent;
		team = home.selectedTeam;
		root = inflater.inflate(R.layout.fragment_local_match_summary, container, false);
		TextView text = (TextView) root.findViewById(R.id.textView1);
		text.setText("Team " + team + " | Match " + home.selectedMatch + " | " + "Summary");
		Button auto = (Button) root.findViewById(R.id.button1);
		Button tele = (Button) root.findViewById(R.id.button3);
		auto.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				home.setEditorPage(TabsPageAdapter.localEditorAutonomous);
				home.switchFragment(new LocalMatchAutonomousFragment());
				
			}
			
		});
		tele.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				home.setEditorPage(TabsPageAdapter.localEditorTeleop);
				home.switchFragment(new LocalMatchTeleopFragment());
				
			}
		}); 
	    return root;
	}
	public static LocalMatchSummaryFragment newInstance() {
		LocalMatchSummaryFragment l = new LocalMatchSummaryFragment();
		Bundle args = new Bundle();
		l.setArguments(args);
		return l;
	
	}
}
