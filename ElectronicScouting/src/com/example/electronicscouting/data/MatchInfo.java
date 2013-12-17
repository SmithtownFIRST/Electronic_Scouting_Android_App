package com.example.electronicscouting.data;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.Serializable;

import android.os.Parcel;
import android.os.Parcelable;

public class MatchInfo {
	private boolean isBlueAlliance = false;
	
	public MatchInfo() {
		
	}
	
	public void writeToFile(DataOutputStream out) throws IOException {
		out.writeBoolean(isBlueAlliance);
	}
	
	public void readFromFile(DataInputStream in) throws IOException {
		isBlueAlliance = in.readBoolean();
	}
	
	public void setAlliance(boolean isBlue) {
		this.isBlueAlliance = isBlue;
	}
	
	public boolean isAllianceBlue() {
		return isBlueAlliance;
	}
	

	
}
