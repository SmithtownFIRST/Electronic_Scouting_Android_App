package com.example.electronicscouting.data;

import java.io.*;
import java.util.*;

import android.content.Context;
import android.widget.Toast;

import com.example.electronicscouting.HomeScreen;

public class ScoutSaver {
	
	private ScoutSaver() {
		
	}
	
	public static void saveData() throws IOException {
		HomeScreen home = HomeScreen.parent;
		File file = new File(home.getFilesDir(), "ScoutingData.dat");
		if (!file.exists()) { 
			file.createNewFile();
		}
		TreeMap<Team, MatchOverviewInfo> matchData = home.teamData;
		HashMap<MatchInfoKey, MatchInfo> teamData = home.matchData;
		DataOutputStream out = new DataOutputStream(new BufferedOutputStream(home.openFileOutput("ScoutingData.dat", Context.MODE_PRIVATE)));
		out.writeInt(matchData.size());
		for (Team team : matchData.keySet()) {
			out.writeInt(team.teamNumber); 
			MatchOverviewInfo info = matchData.get(team);
			int numMatches = info.numMatches;
			out.writeInt(numMatches);
			for (int x = 1; x <= numMatches; x++) {
				MatchInfo match = teamData.get(new MatchInfoKey(team.toString(), x));
				match.writeToFile(out);
				
			}
			
			
			
		}
		out.close();
		
		
	} 
	
	public static void loadData() throws IOException {
		
		HomeScreen home = HomeScreen.parent;
		File file = new File(home.getFilesDir(), "ScoutingData.dat");
		if (!file.exists()) {
			file.createNewFile();
			home.setupData();
			saveData();
			loadData();
			return;
		}
		TreeMap<Team, MatchOverviewInfo> teamData = home.teamData;
		HashMap<MatchInfoKey, MatchInfo> matchData = home.matchData;
		matchData.clear();
		teamData.clear();
		DataInputStream in = new DataInputStream(new BufferedInputStream(home.openFileInput("ScoutingData.dat")));
		int teams = in.readInt();
		for (int x = 0; x < teams; x++) {
			Team team = new Team(in.readInt());
			int numMatches = in.readInt();
			teamData.put(team, new MatchOverviewInfo(numMatches));
			if (numMatches == 0) {
				continue;
			}
			for (int match = 1; match <= numMatches; match++) {
				MatchInfo matchInfo = new MatchInfo();
				matchInfo.readFromFile(in);
				matchData.put(new MatchInfoKey(team.toString(), match), matchInfo);
				
			}
		}
		in.close();
		
	}
}
