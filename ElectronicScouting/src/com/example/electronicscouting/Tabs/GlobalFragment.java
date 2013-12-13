package com.example.electronicscouting.Tabs;

import com.example.electronicscouting.HomeScreen;
import com.example.electronicscouting.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class GlobalFragment extends Fragment {
	private HomeScreen home;
	private View root;
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		home = HomeScreen.parent;
		//home.setTitle("Global View");
        root = inflater.inflate(R.layout.fragment_global_list, container, false);
		
		return root;
	}
}
