package com.example.electronicscouting.data;

import java.io.DataOutputStream;
import java.io.IOException;

public class Team implements Comparable<Team> {
	
public int teamNumber = 0;

	public Team (int teamNumber) {
		this.teamNumber = teamNumber;
	}
	
	@Override
	public String toString() {
		return String.valueOf(teamNumber);
	}
	
	@Override
	public boolean equals(Object other) {
		if (other instanceof Team) {
			Team team = (Team) other;
			return team.teamNumber == teamNumber;
		} else
			return false;
	}

	@Override
	public int compareTo(Team t) {
		return teamNumber - t.teamNumber;
					
	}
	
}
