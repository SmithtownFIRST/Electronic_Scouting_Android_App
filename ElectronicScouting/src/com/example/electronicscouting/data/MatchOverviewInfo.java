package com.example.electronicscouting.data;


import android.os.Parcel;
import android.os.Parcelable;

public class MatchOverviewInfo implements Parcelable{

	public int numMatches = 0;
	public String teamName = "";
	
	
	
	public MatchOverviewInfo() {
		
		
	}
	
	public MatchOverviewInfo(int num) {
		this.numMatches = num;
	}
	
	@Override
	public int describeContents() {
		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(teamName);
		dest.writeInt(numMatches);
		
	}
	public static final Parcelable.Creator<MatchOverviewInfo> CREATOR = new Parcelable.Creator<MatchOverviewInfo>() {
        public MatchOverviewInfo createFromParcel(Parcel in) {
            return new MatchOverviewInfo(in);
        }

        public MatchOverviewInfo[] newArray(int size) {
            return new MatchOverviewInfo[size];
        }
    };
    
    private MatchOverviewInfo(Parcel in) {
    	teamName = in.readString();
    	numMatches = in.readInt();
    }
    
}
