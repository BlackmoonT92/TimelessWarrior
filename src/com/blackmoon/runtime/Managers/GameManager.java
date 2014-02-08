package com.blackmoon.runtime.Managers;

import org.andengine.engine.handler.IUpdateHandler;

import com.blackmoon.runtime.GameLevels.GameLevel;


public class GameManager implements IUpdateHandler{

	// ====================================================
		// INTERFACES
		// ====================================================
		public interface GameLevelGoal {
			public boolean isLevelCompleted();
			public void onLevelCompleted();
			public boolean isLevelFailed();
			public void onLevelFailed();
		}
		
		// ====================================================
		// CONSTANTS
		// ====================================================
		private static final GameManager INSTANCE = new GameManager();
		
		// ====================================================
		// VARIABLES
		// ====================================================
		public GameLevelGoal mGameLevelGoal;
		public GameLevel mGameLevel;
		
		// ====================================================
		// CONSTRUCTOR
		// ====================================================
		public GameManager() {
			ResourceManager.getEngine().registerUpdateHandler(this);
		}

		// ====================================================
		// METHODS
		// ====================================================
		@Override
		public void onUpdate(float pSecondsElapsed) {
			if(mGameLevelGoal!=null)
				if(mGameLevelGoal.isLevelCompleted()) {
					mGameLevelGoal.onLevelCompleted();
					mGameLevelGoal = null;
				} else if (mGameLevelGoal.isLevelFailed()) {
					mGameLevelGoal.onLevelFailed();
					mGameLevelGoal = null;
				}
		}
		@Override public void reset() {}
		
		public static void setGameLevel(GameLevel pGameLevel) {
			INSTANCE.mGameLevel = pGameLevel;
		}
		
		public static GameLevel getGameLevel() {
			return INSTANCE.mGameLevel;
		}
		
		public static void setGameLevelGoal(GameLevelGoal pGameLevelGoal) {
			INSTANCE.mGameLevelGoal = pGameLevelGoal;
		}
		
		public static void clearGameLevelGoal() {
			INSTANCE.mGameLevelGoal = null;
		}
		
}
