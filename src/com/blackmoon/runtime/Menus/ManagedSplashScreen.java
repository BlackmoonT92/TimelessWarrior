package com.blackmoon.runtime.Menus;



import org.andengine.entity.scene.Scene;

import com.blackmoon.runtime.ManagedScene;
import com.blackmoon.runtime.TimelessWarriorsSmoothCamera;
import com.blackmoon.runtime.Managers.ResourceManager;

/** Based on the ManagedMenuScene class.
 * 
***
**/
public abstract class ManagedSplashScreen extends ManagedScene {
	
	public ManagedSplashScreen thisManagedSplashScene = this;
	
	public ManagedSplashScreen() {
		this(0f);
	};
	
	public ManagedSplashScreen(float pLoadingScreenMinimumSecondsShown) {
		super(pLoadingScreenMinimumSecondsShown);
		this.setOnSceneTouchListenerBindingOnActionDownEnabled(true);
		this.setTouchAreaBindingOnActionDownEnabled(true);
		this.setTouchAreaBindingOnActionMoveEnabled(true);
		TimelessWarriorsSmoothCamera.setupForMenus();
		this.setPosition(0, ResourceManager.getInstance().cameraHeight/2f);
		this.dispose();
	}
	
	@Override
	public Scene onLoadingScreenLoadAndShown() {
		return null;
	}

	@Override
	public void onLoadingScreenUnloadAndHidden() {
	}
	
	@Override
	public void onShowScene() {
	}
	
	@Override
	public void onHideScene() {
	}
	
	@Override
	public void onUnloadScene() {
		ResourceManager.getInstance().engine.runOnUpdateThread(new Runnable() {
			@Override
			public void run() {
				thisManagedSplashScene.detachChildren();
				for(int i = 0; i < thisManagedSplashScene.getChildCount(); i++)
					thisManagedSplashScene.getChildByIndex(i).dispose();
				thisManagedSplashScene.clearEntityModifiers();
				thisManagedSplashScene.clearTouchAreas();
				thisManagedSplashScene.clearUpdateHandlers();
				thisManagedSplashScene.unloadSplashTextures();
			}});
	}
	
	public abstract void unloadSplashTextures();
}