package com.example.electronicscouting.data;

import java.io.DataOutputStream;
import java.io.IOException;

public class MatchInfoKey {
private String team = "";
private int match = 0;
	
	public MatchInfoKey(String team, int match) {
		this.team = team;
		this.match = match;
	}
	
	@Override
	public boolean equals(Object other) {
		if (!(other instanceof MatchInfoKey)) {
			return false;
		}
		MatchInfoKey key = (MatchInfoKey) other;
		return (key.team.equals(team) && key.match == match);
	}
	

}
