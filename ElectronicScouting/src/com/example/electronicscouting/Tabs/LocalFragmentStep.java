package com.example.electronicscouting.Tabs;

import android.support.v4.app.Fragment;

public enum LocalFragmentStep {
TeamList(null, LocalTeamChooserFragment.class),
MatchList(TeamList, LocalMatchChooserFragment.class),
MatchEditor(MatchList, LocalMatchSummaryFragment.class);

public Class<? extends Fragment> fragmentClass;
public LocalFragmentStep previousStep;
	LocalFragmentStep(LocalFragmentStep previousStep, Class<? extends Fragment> fragmentClass) {
	this.fragmentClass = fragmentClass;
	this.previousStep = previousStep;	
}
	
	
}
