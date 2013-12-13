package com.example.electronicscouting.Tabs;

import com.example.electronicscouting.HomeScreen;
import com.example.electronicscouting.R;
import com.example.electronicscouting.data.MatchOverviewInfo;
import com.example.electronicscouting.data.MatchInfoKey;
import com.example.electronicscouting.data.Team;
import com.example.electronicscouting.data.MatchInfo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class LocalMatchChooserFragment extends Fragment{
	private View root;
	private HomeScreen home;
	private MatchOverviewInfo matchInfo;
	private String team = "";
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		
		this.home = HomeScreen.parent;
		team = home.selectedTeam;
		matchInfo = home.teamData.get(new Team(Integer.parseInt(team)));
		
        root = inflater.inflate(R.layout.activity_match_chooser, container, false);
        ((TextView)root.findViewById(R.id.textView1)).setText("Team " + team + "'s Matches");
        setupList();
        Button button = (Button) root.findViewById(R.id.autonomous);
        button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				addMatch(String.valueOf(++matchInfo.numMatches));
				home.matchData.put(new MatchInfoKey(team, matchInfo.numMatches), new MatchInfo());
			}
        	
        });
		return root;
	}
	private void setupList() {
		final ListView listView = (ListView) root.findViewById(R.id.matches);
		String[] listValues = new String[matchInfo.numMatches];
		  for (int x = 0; x < listValues.length; x++) {
      		
  			listValues[x] = String.valueOf(x + 1);

  		}
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(root.getContext(), android.R.layout.simple_list_item_1, android.R.id.text1, new String[] {});
	    listView.setAdapter(adapter); 
	    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
	            	  
	                  @Override
	                  public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
	              
	                   String  itemValue = (String) listView.getItemAtPosition(position);
	                    openEditor(itemValue);
	                  }
	             }); 
	            for (String str : listValues) {
	    			addMatch(str);
	            }
	}
	
	private void openEditor(String match) {
		int matchNum = Integer.parseInt(match.substring(match.indexOf(' ') + 1));
		LocalMatchSummaryFragment localEditor = new LocalMatchSummaryFragment();
		home.selectedMatch = matchNum;
		home.switchFragment(localEditor);
		
	}
	private void addMatch(String matchNumber) {
		
		 ListView list = (ListView) root.findViewById(R.id.matches);
		         
		         ListAdapter adapter = list.getAdapter();
		         String[] newList = new String[adapter.getCount() + 1];
		         for (int x = 0; x < list.getAdapter().getCount(); x++) {
		         	newList[x] = adapter.getItem(x).toString();
		         }
		         newList[newList.length - 1] = "Match " + matchNumber;
		         ArrayAdapter<String> newAdapter = new ArrayAdapter<String>(root.getContext(), android.R.layout.simple_list_item_1, android.R.id.text1, newList);
		         list.setAdapter(newAdapter);
			}
	
	
	
	public static LocalMatchChooserFragment newInstance() {
		LocalMatchChooserFragment l = new LocalMatchChooserFragment();
		Bundle args = new Bundle();
		l.setArguments(args);
		return l;
	
	}
}