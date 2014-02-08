package com.blackmoon.runtime.Layers;



import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.entity.modifier.AlphaModifier;
import org.andengine.entity.modifier.ScaleModifier;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.entity.text.Text;

import com.blackmoon.runtime.TimelessWarriorsActivity;
import com.blackmoon.runtime.GameLevels.GameLevel;
import com.blackmoon.runtime.GameLevels.Levels;
import com.blackmoon.runtime.Input.GrowButton;
import com.blackmoon.runtime.Managers.ResourceManager;
import com.blackmoon.runtime.Managers.SceneManager;

/** The LevelWonLayer class represents the layer that is shown to the player
 *  when a level is completed successfully. It displays the current level
 *  number, score, and high-score as well as the star rating that the player
 *  received. It also includes buttons to go back to the level-select screen,
 *  replay the level, or go on to the next level.
 *  
*** @author Brian Broyles - IFL Game Studio
**/
public class LevelLoseLayer extends ManagedLayer {
	
	// ====================================================
	// CONSTANTS
	// ====================================================
	private static final LevelLoseLayer INSTANCE = new LevelLoseLayer();
	
	// ====================================================
	// INSTANCE GETTERS
	// ====================================================
	public static LevelLoseLayer getInstance() {
		return INSTANCE;
	}
	
	public static LevelLoseLayer getInstance(final GameLevel pCurrentLevel) {
		INSTANCE.setCurrentLevel(pCurrentLevel);
		return INSTANCE;
	}
	
	// ====================================================
	// VARIABLES
	// ====================================================
	private GameLevel mCurrentLevel;
	private Sprite mLayerBG;
	private boolean mIsGoingToNextLevel = false;
	private boolean mIsGoingToRestartLevel = false;
	private Text mMainText;
	private Text mMessageText;
	// ====================================================
	// UPDATE HANDLERS
	// ====================================================
	// Animates the layer to slide in from the top.
	IUpdateHandler mSlideInUpdateHandler = new IUpdateHandler() {
		@Override
		public void onUpdate(final float pSecondsElapsed) {
			if(LevelLoseLayer.this.mLayerBG.getY() > 0f) {
				LevelLoseLayer.this.mLayerBG.setY(Math.max(LevelLoseLayer.this.mLayerBG.getY() - (pSecondsElapsed * ManagedLayer.mSLIDE_PIXELS_PER_SECONDS), 0f));
			} else {
				ResourceManager.getInstance().engine.unregisterUpdateHandler(this);
			}
		}
		
		@Override
		public void reset() {}
	};
	// Animates the layer to slide out through the top and tell the SceneManager
	// to hide it when it is off-screen;
	IUpdateHandler mSlideOutUpdateHandler = new IUpdateHandler() {
		@Override
		public void onUpdate(final float pSecondsElapsed) {
			if(LevelLoseLayer.this.mLayerBG.getY() < ((ResourceManager.getInstance().cameraHeight / 2f) + (LevelLoseLayer.this.mLayerBG.getHeight() / 2f))) {
				LevelLoseLayer.this.mLayerBG.setY(Math.min(LevelLoseLayer.this.mLayerBG.getY() + (pSecondsElapsed * ManagedLayer.mSLIDE_PIXELS_PER_SECONDS), (ResourceManager.getInstance().cameraHeight / 2f) + (LevelLoseLayer.this.mLayerBG.getHeight() / 2f)));
			} else {
				ResourceManager.getInstance().engine.unregisterUpdateHandler(this);
				SceneManager.getInstance().hideLayer();
				if(LevelLoseLayer.this.mIsGoingToRestartLevel) {
					LevelLoseLayer.this.mCurrentLevel.restartLevel();
					return;
				} else if(LevelLoseLayer.this.mIsGoingToNextLevel) {
					if(Levels.isNextLevelInCurrentWorld(LevelLoseLayer.this.mCurrentLevel.mLevelDef)) {
						LevelLoseLayer.this.mCurrentLevel.startNextLevel();
						return;
					}
				}
				LevelLoseLayer.this.mCurrentLevel.disposeLevel();
				SceneManager.getInstance().showMainMenu();
			}
		}
		
		@Override
		public void reset() {}
	};
	
	// ====================================================
	// METHODS
	// ====================================================
	@Override
	public void onHideLayer() {
		// Register the slide-out animation with the engine.
		ResourceManager.getInstance().engine.registerUpdateHandler(this.mSlideOutUpdateHandler);
	}
	
	@Override
	public void onLoadLayer() {
		if(this.mHasLoaded) {
			return;
		}
		this.mHasLoaded = true;
		
		this.setTouchAreaBindingOnActionDownEnabled(true);
		this.setTouchAreaBindingOnActionMoveEnabled(true);
		
		final Rectangle fadableBGRect = new Rectangle(0f, 0f, ResourceManager.getInstance().cameraWidth, ResourceManager.getInstance().cameraHeight, ResourceManager.getActivity().getVertexBufferObjectManager());
		fadableBGRect.setColor(0f, 0f, 0f, 0.5f);
		this.attachChild(fadableBGRect);
		
		this.attachChild(this.mLayerBG = new Sprite(0f, (ResourceManager.getInstance().cameraHeight / 2f) + (ResourceManager.gameLevelLayerBGTR.getHeight() / 2f), ResourceManager.gameLevelLayerBGTR, ResourceManager.getActivity().getVertexBufferObjectManager()));
		this.mLayerBG.setScale(1.5f / ResourceManager.getInstance().cameraScaleFactorY);
		
		final GrowButton BackToLevelSelectButton = new GrowButton(150f, 75f, ResourceManager.gameLevelLayerButtonLevelSelectTR, false) {
			@Override
			public void onClick() {
				LevelLoseLayer.this.mIsGoingToRestartLevel = false;
				LevelLoseLayer.this.mIsGoingToNextLevel = false;
				LevelLoseLayer.this.onHideLayer();
			}
		};
		this.mLayerBG.attachChild(BackToLevelSelectButton);
		this.registerTouchArea(BackToLevelSelectButton);
		
		final GrowButton RestartLevelButton = new GrowButton(350f, 75f, ResourceManager.gameLevelLayerButtonRestartLevelTR, false) {
			@Override
			public void onClick() {
				LevelLoseLayer.this.mIsGoingToRestartLevel = true;
				LevelLoseLayer.this.mIsGoingToNextLevel = false;
				LevelLoseLayer.this.onHideLayer();
			}
		};
		this.mLayerBG.attachChild(RestartLevelButton);
		this.registerTouchArea(RestartLevelButton);
		
		
		this.mMainText = new Text(0f, 0f, ResourceManager.fontDefault72Bold, "SORRY YOU JUST LOSE THE GAME!!!", ResourceManager.getActivity().getVertexBufferObjectManager());
		this.mMainText.setScale(Math.min(390f / this.mMainText.getWidth(), 1f));
		this.mMainText.setPosition(256f, 205f);
		this.mMainText.setColor(0.31f, 0.35f, 0.31f);
		this.mLayerBG.attachChild(this.mMainText);
		
		this.mMessageText = new Text(0f, 0f, ResourceManager.fontDefault72Bold, "Please Try Again....", ResourceManager.getActivity().getVertexBufferObjectManager());
		this.mMessageText.setScale(Math.min(390f / this.mMainText.getWidth(), 1f));
		this.mMessageText.setPosition(256f, 150f);
		this.mMessageText.setColor(0.31f, 0.35f, 0.31f);
		this.mLayerBG.attachChild(this.mMessageText);
		
		this.setPosition(ResourceManager.getInstance().cameraWidth / 2f, ResourceManager.getInstance().cameraHeight / 2f);
	}
	
	@Override
	public void onShowLayer() {
		// Register the slide-in animation with the engine.
		ResourceManager.getInstance().engine.registerUpdateHandler(this.mSlideInUpdateHandler);
		
		this.mIsGoingToRestartLevel = false;
		this.mIsGoingToNextLevel = false;	

	}
	
	@Override
	public void onUnloadLayer() {}
	
	public void setCurrentLevel(final GameLevel pCurrentLevel) {
		this.mCurrentLevel = pCurrentLevel;
	}
}