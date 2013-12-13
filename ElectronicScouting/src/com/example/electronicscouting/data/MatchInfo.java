package com.example.electronicscouting.data;

import java.io.Serializable;

import android.os.Parcel;
import android.os.Parcelable;

public class MatchInfo implements Parcelable {

	public boolean hasAutonomous = false;
	public float numStars = 0;
	public String additionalComments = "";
	
	public MatchInfo(boolean hasAuto, float numStars, String comments) {
		this.hasAutonomous = hasAuto;
		this.numStars = numStars;
		additionalComments = comments;
	}
	
	public MatchInfo() {
		
	}
	
	public String toString() {
		return hasAutonomous + "<SPLIT>" + numStars + "<SPLIT>" + additionalComments;
	}
	
	public static MatchInfo convertFromString(String raw) {
		String[] tokens = raw.split("<SPLIT>");
		boolean auto = Boolean.parseBoolean(tokens[0]);
		float stars = Float.parseFloat(tokens[1]);
		String additional = tokens[2];
		return new MatchInfo(auto, stars, additional);
	}
	
	public static final Parcelable.Creator<MatchInfo> CREATOR = new Parcelable.Creator<MatchInfo>() {
        public MatchInfo createFromParcel(Parcel in) {
            return new MatchInfo(in);
        }

        public MatchInfo[] newArray(int size) {
            return new MatchInfo[size];
        }
    };
    
	@Override
	public int describeContents() {
		return 0;
	}

	 private MatchInfo(Parcel in) {
	     	boolean[] bools = new boolean[1];
	     	in.readBooleanArray(bools);
	     	numStars = in.readFloat();
	     	additionalComments = in.readString();
	    }
	 
	@Override
	public void writeToParcel(Parcel p, int arg1) {
		p.writeBooleanArray(new boolean[] {hasAutonomous});
		p.writeFloat(numStars);
		p.writeString(additionalComments);
		
		
	}
}
