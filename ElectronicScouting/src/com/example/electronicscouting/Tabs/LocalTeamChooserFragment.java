package com.example.electronicscouting.Tabs;


import java.util.Arrays;
import java.util.Collections;

import com.example.electronicscouting.HomeScreen;
import com.example.electronicscouting.R;
import com.example.electronicscouting.data.MatchOverviewInfo;
import com.example.electronicscouting.data.Team;
import com.example.electronicscouting.data.MatchInfo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class LocalTeamChooserFragment extends Fragment {
	private View root;
	private HomeScreen home;

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		home = HomeScreen.parent;
        root = inflater.inflate(R.layout.activity_main, container, false);
        setupList();
        Button button = (Button) root.findViewById(R.id.autonomous);
        button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				onAddClick();				
			}
        	
        });
        
        return root;
    }
	 
	
	private void setupList() {
		   String[] listValues = {}; 
	        final ListView listView = (ListView) root.findViewById(R.id.matches);
		  ArrayAdapter<String> adapter = new ArrayAdapter<String>(root.getContext(),
	                android.R.layout.simple_list_item_1, android.R.id.text1, listValues);
	              listView.setAdapter(adapter); 
	              listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
	            	  
	                  @Override
	                  public void onItemClick(AdapterView<?> parent, View view, int position, long id) {               
	                   String itemValue = (String)listView.getItemAtPosition(position);
	                     openEditor(itemValue);	                 
	                  }
	             }); 
	              for (Team team : home.teamData.keySet()) {
	            	  addNewItemToList(team.toString());
	              }
	}
	
	
	private void openEditor(String team) {
		home.selectedTeam = team.substring(team.indexOf(' ') + 1);;
		LocalMatchChooserFragment localMatch = new LocalMatchChooserFragment();
		home.switchFragment(localMatch);
		
	}
	
	public static LocalTeamChooserFragment newInstance() {
		LocalTeamChooserFragment l = new LocalTeamChooserFragment();
		Bundle args = new Bundle();
		l.setArguments(args);
		return l;
	
	}
	
	
	public void addNewTeam(final View view) {
    	final EditText input = new EditText(root.getContext());
    	input.setInputType(InputType.TYPE_CLASS_NUMBER);
    	new AlertDialog.Builder(root.getContext())
    	    .setTitle("Enter New Team Number")
    	    .setMessage("Team Number:")
    	    .setView(input)
    	    .setPositiveButton("Add Team", new DialogInterface.OnClickListener() {
    	         public void onClick(DialogInterface dialog, int whichButton) {
    	            Editable editable = input.getText(); 
    	            
    	            String number = editable.toString();
    	            if (number.equalsIgnoreCase("000000000")) { //Breadfish Easter Egg
    	            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.breadfish.co.uk"));
    	            startActivity(browserIntent);
    	            return;
    	            }
    	            if (number.length() == 0) {
    	            	Toast.makeText(view.getContext(), "Error: No team number entered!", Toast.LENGTH_SHORT).show();
    	            } else
    	            if (number.length() >= 9) {
    	            	Toast.makeText(view.getContext(), "Error: Team number to large", Toast.LENGTH_SHORT).show();
    	            }
    	            else
    	            if (!home.teamData.containsKey(new Team(Integer.parseInt(number)))) {
    	           addNewItemToList(number);
    	            } else {
    	            	Toast.makeText(view.getContext(), "Error: Team already exists!", Toast.LENGTH_SHORT).show();
    	            }
    	         }
    	    })
    	    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
    	         public void onClick(DialogInterface dialog, int whichButton) {
    	        	 
    	         }
    	    }).show();

		}
	protected void onAddClick() {
		addNewTeam(root);
	}
	
	private void addNewItemToList(String item) {
   	 ListView list = (ListView) root.findViewById(R.id.matches);
        
        ListAdapter adapter = list.getAdapter();
        String[] newList = new String[adapter.getCount() + 1];
        for (int x = 0; x < list.getAdapter().getCount(); x++) {
        	newList[x] = adapter.getItem(x).toString();
        }
        newList[newList.length - 1] = "Team " + item;
        ArrayAdapter<String> newAdapter = new ArrayAdapter<String>(root.getContext(), android.R.layout.simple_list_item_1, android.R.id.text1, newList);
        list.setAdapter(newAdapter);
        String teamName = item;
        if (!home.teamData.containsKey(new Team(Integer.parseInt(item)))) {
       	 MatchOverviewInfo matchInfo = new MatchOverviewInfo();
       	 matchInfo.teamName = teamName;
        home.teamData.put(new Team(Integer.parseInt(teamName)), matchInfo);
        }
   }

	
	
}
