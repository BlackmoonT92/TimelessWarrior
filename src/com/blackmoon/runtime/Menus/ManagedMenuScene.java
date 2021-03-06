package com.blackmoon.runtime.Menus;

import com.blackmoon.runtime.ManagedScene;


public abstract class ManagedMenuScene extends ManagedScene {
	public ManagedMenuScene(float pLoadingScreenMinimumSecondsShown) {
		super(pLoadingScreenMinimumSecondsShown);
	}
	@Override
	public void onUnloadManagedScene() {
		if(isLoaded) {
			// For menus, we are disabling the reloading of resources.
			// isLoaded = false;
			onUnloadScene();
		}
	}
}