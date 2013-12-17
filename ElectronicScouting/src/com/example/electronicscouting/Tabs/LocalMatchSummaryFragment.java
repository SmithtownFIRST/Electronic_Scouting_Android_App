package com.example.electronicscouting.Tabs;

import com.example.electronicscouting.HomeScreen;
import com.example.electronicscouting.R;
import com.example.electronicscouting.TabsPageAdapter;
import com.example.electronicscouting.data.MatchInfo;
import com.example.electronicscouting.data.MatchInfoKey;

import android.R.drawable;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

public class LocalMatchSummaryFragment extends Fragment {
	private HomeScreen home;
	private View root;
	protected String team;
	MatchInfo info;
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		
		this.home = HomeScreen.parent;
		team = home.selectedTeam;
		info = home.matchData.get(new MatchInfoKey(team, home.selectedMatch));
		
		root = inflater.inflate(R.layout.fragment_local_match_summary, container, false);
		final Switch alliance = (Switch) root.findViewById(R.id.switch1);
		alliance.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				info.setAlliance(arg1);
				
			}
			
			
		});
		TextView text = (TextView) root.findViewById(R.id.textView1);
		loadGui();
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
	
	private void loadGui() {
		Switch alliance = (Switch) root.findViewById(R.id.switch1);
		alliance.setButtonDrawable(drawable.btn_star_big_on);
		alliance.setChecked(info.isAllianceBlue());
		
	}
}
