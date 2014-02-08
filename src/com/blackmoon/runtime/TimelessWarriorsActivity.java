package com.blackmoon.runtime;

import org.andengine.engine.Engine;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.WakeLockOptions;
import org.andengine.engine.options.resolutionpolicy.FillResolutionPolicy;
import org.andengine.engine.options.resolutionpolicy.IResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.input.touch.controller.MultiTouch;
import org.andengine.opengl.view.RenderSurfaceView;
import org.andengine.ui.activity.BaseGameActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.Gravity;
import android.view.View;
import android.view.View.MeasureSpec;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.blackmoon.TimelessWarriors.R;
import com.blackmoon.runtime.Managers.ResourceManager;
import com.blackmoon.runtime.Managers.SFXManager;
import com.blackmoon.runtime.Managers.SceneManager;
import com.blackmoon.runtime.Menus.MainMenu;
import com.blackmoon.runtime.Menus.ManagedMenuScene;
import com.blackmoon.runtime.Menus.SplashScreens;
import com.blackmoon.runtime.Menus.MainMenu.MainMenuScreens;
import com.google.ads.AdRequest;
import com.google.ads.AdSize;
import com.google.ads.AdView;

/**
 * This activity class builds upon the standard AndEngine BaseGameActivity with
 * the addition of ads, some advanced resolution-scaling performed in the
 * onCreateEngineOptions() method, and shared-preference methods to save and
 * restore options and scores.
 * 
 * @author Brian Broyles - IFL Game Studio
 */
public class TimelessWarriorsActivity extends BaseGameActivity {

	// ====================================================
	// CONSTANTS
	// ====================================================
	// We define these constants to setup the game to use an
	// appropriate camera resolution independent of the actual
	// end-user's window resolution.

	// Define a minimum and maximum window resolution (to prevent
	// cramped or overlapping elements).
	static float MIN_WIDTH_PIXELS = 800f, MIN_HEIGHT_PIXELS = 480f;
	static float MAX_WIDTH_PIXELS = 1600f, MAX_HEIGHT_PIXELS = 960f;

	// This design device is the Samsung Galaxy Note (SGH-I717)
	// The resolution of the window with which you are developing.
	static float DESIGN_WINDOW_WIDTH_PIXELS = 1280f;
	static float DESIGN_WINDOW_HEIGHT_PIXELS = 800f;
	// The physical size of the window with which you are developing.
	// Calculated by dividing the window's x & y pixels by the x & y DPI.
	static float DESIGN_WINDOW_WIDTH_INCHES = 4.472441f;
	static float DESIGN_WINDOW_HEIGHT_INCHES = 2.805118f;

	// ====================================================
	// VARIABLES
	// ====================================================
	public float cameraWidth;
	public float cameraHeight;
	public float actualWindowWidthInches;
	public float actualWindowHeightInches;
	public TimelessWarriorsSmoothCamera mCamera;

	// ====================================================
	// CREATE ENGINE
	// ====================================================
	// Create and return a Switchable Fixed-Step Engine
	@Override
	public Engine onCreateEngine(final EngineOptions pEngineOptions) {
		return new SwitchableFixedStepEngine(pEngineOptions, 50, false);
	}

	// ====================================================
	// HANDLE THE BACK BUTTON
	// ====================================================
	@Override
	public void onBackPressed() {
		// If the resource manager has been setup...
		if (ResourceManager.getInstance().engine != null) {
			// if a layer is shown...
			if (SceneManager.getInstance().mIsLayerShown) {
				// hide the layer.
				// SceneManager.getInstance().mCurrentLayer.onHideLayer();
			}/*
			 * else
			 * if(SceneManager.getInstance().mCurrentScene.getClass().equals
			 * (GameLevel.class)) { // unload the level and go back to the Main
			 * Menu.
			 * ((GameLevel)SceneManager.getInstance().mCurrentScene).disposeLevel
			 * (); SceneManager.getInstance().showMainMenu(); }
			 */
			// or if any menu is shown other than the Main Menu...
			else if (SceneManager.getInstance().mCurrentScene.getClass()
					.getGenericSuperclass().equals(ManagedMenuScene.class)
					& !SceneManager.getInstance().mCurrentScene.getClass()
							.equals(MainMenu.class))
				// show the Main Menu.
				SceneManager.getInstance().showMainMenu();
			// or if the Main Menu is already shown...
			else
			// if the Main Menu is on the Level Select screen...
			if (MainMenu.getInstance().mCurrentScreen == MainMenuScreens.LevelSelector)
				// go to the Title Screen.
				MainMenu.getInstance().goToTitleScreen();
			// or if the Main Menu is already on the Title Screen...
			else
				// exit the game.
				System.exit(0);
		}
	}

	// ====================================================
	// CREATE ENGINE OPTIONS
	// ====================================================
	@Override
	public EngineOptions onCreateEngineOptions() {
		// Override the onMeasure method of the ResolutionPolicy to set the
		// camera's size. This way usually gives better results compared
		// to the DisplayMetrics.getWidth method because it uses the
		// window instead of the display. This should also be better for if
		// the game is placed in a layout where simply measuring the display
		// would give entirely wrong results.
		FillResolutionPolicy EngineFillResolutionPolicy = new FillResolutionPolicy() {
			@Override
			public void onMeasure(
					final IResolutionPolicy.Callback pResolutionPolicyCallback,
					final int pWidthMeasureSpec, final int pHeightMeasureSpec) {
				super.onMeasure(pResolutionPolicyCallback, pWidthMeasureSpec,
						pHeightMeasureSpec);

				final int measuredWidth = MeasureSpec
						.getSize(pWidthMeasureSpec);
				final int measuredHeight = MeasureSpec
						.getSize(pHeightMeasureSpec);

				// Determine the device's physical window size.
				actualWindowWidthInches = measuredWidth
						/ getResources().getDisplayMetrics().xdpi;
				actualWindowHeightInches = measuredHeight
						/ getResources().getDisplayMetrics().ydpi;

				// Get an initial width for the camera, and bound it to the
				// minimum or maximum values.
				float actualScaledWidthInPixels = DESIGN_WINDOW_WIDTH_PIXELS
						* (actualWindowWidthInches / DESIGN_WINDOW_WIDTH_INCHES);
				float boundScaledWidthInPixels = Math.round(Math.max(
						Math.min(actualScaledWidthInPixels, MAX_WIDTH_PIXELS),
						MIN_WIDTH_PIXELS));

				// Get the height for the camera based on the width and the
				// height/width ratio of the device
				float boundScaledHeightInPixels = boundScaledWidthInPixels
						* (actualWindowHeightInches / actualWindowWidthInches);
				// If the height is outside of the set bounds, scale the width
				// to match it.
				if (boundScaledHeightInPixels > MAX_HEIGHT_PIXELS) {
					float boundAdjustmentRatio = MAX_HEIGHT_PIXELS
							/ boundScaledHeightInPixels;
					boundScaledWidthInPixels *= boundAdjustmentRatio;
					boundScaledHeightInPixels *= boundAdjustmentRatio;
				} else if (boundScaledHeightInPixels < MIN_HEIGHT_PIXELS) {
					float boundAdjustmentRatio = MIN_HEIGHT_PIXELS
							/ boundScaledHeightInPixels;
					boundScaledWidthInPixels *= boundAdjustmentRatio;
					boundScaledHeightInPixels *= boundAdjustmentRatio;
				}
				// set the height and width variables
				cameraHeight = boundScaledHeightInPixels;
				cameraWidth = boundScaledWidthInPixels;
				// apply the height and width variables
				mCamera.set(0f, 0f, cameraWidth, cameraHeight);
			}
		};

		// Create the Camera and EngineOptions.
		mCamera = new TimelessWarriorsSmoothCamera(0, 0, 320, 240, 1200f,
				800f, 0.5f);
		EngineOptions engineOptions = new EngineOptions(true,
				ScreenOrientation.LANDSCAPE_SENSOR, EngineFillResolutionPolicy,
				mCamera);

		// Enable sounds.
		engineOptions.getAudioOptions().setNeedsSound(true);
		// Enable music.
		engineOptions.getAudioOptions().setNeedsMusic(true);
		// Turn on Dithering to smooth texture gradients.
		engineOptions.getRenderOptions().setDithering(true);
		// Turn on MultiSampling to smooth the alias of hard-edge elements.
		engineOptions.getRenderOptions().getConfigChooserOptions()
				.setRequestedMultiSampling(true);
		// Set the Wake Lock options to prevent the engine from dumping textures
		// when focus changes.
		engineOptions.setWakeLockOptions(WakeLockOptions.SCREEN_ON);

		// set multiTouch for GamePlay
		engineOptions.getTouchOptions().setNeedsMultiTouch(true);

		if (MultiTouch.isSupported(this)) {
			if (MultiTouch.isSupportedDistinct(this)) {
				Toast.makeText(
						this,
						"MultiTouch detected --> Both controls will work properly!",
						Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(
						this,
						"MultiTouch detected, but your device has problems distinguishing between fingers.\n\nControls are placed at different vertical locations.",
						Toast.LENGTH_LONG).show();
			}
		} else {
			Toast.makeText(
					this,
					"Sorry your device does NOT support MultiTouch!\n\n(Falling back to SingleTouch.)\n\nControls are placed at different vertical locations.",
					Toast.LENGTH_LONG).show();
		}

		// Return our engine options.
		return engineOptions;
	}

	// ====================================================
	// ACCESS TO SHARED RESOURCES
	// ====================================================

	// The following list of Strings are keys within the Shared Preferences.
	// The name of the shared preferences used by this game.
	public static final String SHARED_PREFS_MAIN = "MagneTankSettings";
	// The quality (Boolean) setting. True = High Quality.
	public static final String SHARED_PREFS_HIGH_QUALITY_GRAPHICS = "quality";
	// How many stars (Integer) the player got in each level.
	public static final String SHARED_PREFS_LEVEL_STARS = "level.stars.";
	// The highscore (Integer) that the player has gotten for each level.
	public static final String SHARED_PREFS_LEVEL_HIGHSCORE = "level.highscore.";
	// The highest level (Integer) reached by the player.
	public static final String SHARED_PREFS_LEVEL_MAX_REACHED = "levels.reached.";
	// The number (Integer) of times that the application has been started.
	public static final String SHARED_PREFS_ACTIVITY_START_COUNT = "count";
	// The player has rated the game. True = player has agreed to rate it.
	public static final String SHARED_PREFS_RATING_SUCCESS = "rating";
	// The muted state of the music. True = muted.
	public static final String SHARED_PREFS_MUSIC_MUTED = "mute.music";
	// The muted state of the sound effects. True = muted.
	public static final String SHARED_PREFS_SOUNDS_MUTED = "mute.sounds";

	// The number of times that the application has started, stored as a local
	// value
	public int numTimesActivityOpened;

	// Methods to write/read Integers in the Shared Preferences.
	public static int writeIntToSharedPreferences(final String pStr,
			final int pValue) {
		// The apply() method requires API level 9 in the manifest.
		ResourceManager.getActivity()
				.getSharedPreferences(SHARED_PREFS_MAIN, 0).edit()
				.putInt(pStr, pValue).apply();
		return pValue;
	}

	public static int getIntFromSharedPreferences(final String pStr) {
		return ResourceManager.getActivity()
				.getSharedPreferences(SHARED_PREFS_MAIN, 0).getInt(pStr, 0);
	}

	// Methods to write/read Booleans in the Shared Preferences
	public static void writeBooleanToSharedPreferences(final String pStr,
			final boolean pValue) {
		// The apply() method requires API level 9 in the manifest.
		ResourceManager.getActivity()
				.getSharedPreferences(SHARED_PREFS_MAIN, 0).edit()
				.putBoolean(pStr, pValue).apply();
	}

	public static boolean getBooleanFromSharedPreferences(final String pStr) {
		return ResourceManager.getActivity()
				.getSharedPreferences(SHARED_PREFS_MAIN, 0)
				.getBoolean(pStr, false);
	}

	// A convenience method for accessing how many stars the player achieved on
	// a certain level.
	public static int getLevelStars(final int pLevelNumber) {
		return getIntFromSharedPreferences(SHARED_PREFS_LEVEL_STARS
				+ String.valueOf(pLevelNumber));
	}

	// ====================================================
	// CREATE RESOURCES
	// ====================================================
	@Override
	public void onCreateResources(
			OnCreateResourcesCallback pOnCreateResourcesCallback) {
		// Setup the ResourceManager.
		ResourceManager.setup(this,
				(SwitchableFixedStepEngine) this.getEngine(),
				this.getApplicationContext(), cameraWidth, cameraHeight,
				cameraWidth / DESIGN_WINDOW_WIDTH_PIXELS, cameraHeight
						/ DESIGN_WINDOW_HEIGHT_PIXELS);

		// Retrieve and increment the number of times that the application has
		// started.
		numTimesActivityOpened = getIntFromSharedPreferences(SHARED_PREFS_ACTIVITY_START_COUNT) + 1;
		writeIntToSharedPreferences(SHARED_PREFS_ACTIVITY_START_COUNT,
				numTimesActivityOpened);

		// Retrieve the quality level setting.
		final boolean DeviceLimited = getBooleanFromSharedPreferences(SHARED_PREFS_HIGH_QUALITY_GRAPHICS);
		ResourceManager.getInstance().useHighQuality = DeviceLimited;

		// Tell the callback that the resources have loaded.
		// We'll be handling the actual loading of resources at specific points
		// throughout the application flow.
		pOnCreateResourcesCallback.onCreateResourcesFinished();
	}

	// ====================================================
	// CREATE SCENE
	// ====================================================
	@Override
	public void onCreateScene(OnCreateSceneCallback pOnCreateSceneCallback) {
		// On every third time that the application is started...
		if (numTimesActivityOpened % 3 == 0) {
			// If a market rating has not already been given, ask for one.
			if (getIntFromSharedPreferences(SHARED_PREFS_RATING_SUCCESS) < 1)
				this.runOnUiThread(new Runnable() {
					@Override
					public void run() {
						AlertDialog.Builder builder = new AlertDialog.Builder(
								TimelessWarriorsActivity.this);
						builder.setTitle("Like what you see?");
						builder.setIcon(R.drawable.icon);
						builder.setMessage(
								"Would you like to rate MagneTank in the market?")
								.setPositiveButton("Of course!",
										new DialogInterface.OnClickListener() {
											public void onClick(
													DialogInterface dialog,
													int id) {
												// Start the market and navigate
												// to the application's page.
												Intent intent = new Intent(
														Intent.ACTION_VIEW);
												intent.setData(Uri
														.parse("market://details?id=ifl.games.MagneTank"));
												startActivity(intent);
												writeIntToSharedPreferences(
														SHARED_PREFS_RATING_SUCCESS,
														1);
											}
										})
								.setNegativeButton("Maybe later...",
										new DialogInterface.OnClickListener() {
											public void onClick(
													DialogInterface dialog,
													int id) {
											}
										}).setCancelable(false);
						AlertDialog alert = builder.create();
						alert.show();
					}
				});
		}

		// Load the resources necessary for the Main Menu
		ResourceManager.loadMenuResources();
		// Tell the SceneManager to show the splash screens.
		SceneManager.getInstance().showScene(new SplashScreens());
		// Set the MainMenu to the Engine's scene.
		pOnCreateSceneCallback.onCreateSceneFinished(mEngine.getScene());

		// Start the background music.
		SFXManager.playMusic();
		// If the music is muted in the settings, mute it in the game.
		if (getIntFromSharedPreferences(SHARED_PREFS_MUSIC_MUTED) > 0)
			SFXManager.setMusicMuted(true);
		// If the sound effects are muted in the settings, mute them in the
		// game.
		if (getIntFromSharedPreferences(SHARED_PREFS_SOUNDS_MUTED) > 0)
			SFXManager.setSoundMuted(true);
	}

	// ====================================================
	// POPULATE SCENE
	// ====================================================
	@Override
	public void onPopulateScene(Scene pScene,
			OnPopulateSceneCallback pOnPopulateSceneCallback) {
		// Our SceneManager will handle the population of the scenes, so we do
		// nothing here.
		pOnPopulateSceneCallback.onPopulateSceneFinished();
	}

	// ====================================================
	// LIFECYCLE METHODS
	// ====================================================
	// When the game is already loaded and paused, pause the music.
	@Override
	protected void onPause() {
		super.onPause();
		if (this.isGameLoaded())
			SFXManager.pauseMusic();
	}

	// When the game is resumed, tell the system that it should perform a
	// garbage collection to clean up previous applications.
	// If the game is already loaded resume the music.
	@Override
	protected synchronized void onResume() {
		super.onResume();
		System.gc();
		if (this.isGameLoaded())
			SFXManager.resumeMusic();
	}

	// Some devices do not exit the game when the activity is destroyed.
	// This ensures that the game is closed.
	@Override
	protected void onDestroy() {
		super.onDestroy();
		System.exit(0);
	}
}