package com.blackmoon.runtime.Menus;



import org.andengine.entity.Entity;
import org.andengine.entity.modifier.MoveModifier;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.opengl.texture.region.TiledTextureRegion;

import com.blackmoon.runtime.TimelessWarriorsActivity;
import com.blackmoon.runtime.TimelessWarriorsSmoothCamera;
import com.blackmoon.runtime.Input.GrowButton;
import com.blackmoon.runtime.Input.GrowToggleButton;
import com.blackmoon.runtime.Layers.OptionsLayer;
import com.blackmoon.runtime.Managers.ResourceManager;
import com.blackmoon.runtime.Managers.SFXManager;
import com.blackmoon.runtime.Managers.SceneManager;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

/**
 * The MainMenu scene holds two Entitys, one representing the title screen and
 * one representing the level-select screen. The movement between the two
 * screens is achieved using entity modifiers. During the first showing of the
 * MainMenu, a loading screen is shown while the game resources are loaded.
 * 
 *** 
 **/
public class MainMenu extends ManagedMenuScene {

	// ====================================================
	// ENUMS
	// ====================================================
	public enum MainMenuScreens {
		LevelSelector, Title
	}

	// ====================================================
	// CONSTANTS
	// ====================================================
	private static final MainMenu INSTANCE = new MainMenu();
	private static final float mCameraHeight = ResourceManager.getInstance().cameraHeight;
	private static final float mCameraWidth = ResourceManager.getInstance().cameraWidth;
	private static final float mHalfCameraHeight = (ResourceManager
			.getInstance().cameraHeight / 2f);
	private static final float mHalfCameraWidth = (ResourceManager
			.getInstance().cameraWidth / 2f);

	// ====================================================
	// INSTANCE GETTER
	// ====================================================
	public static MainMenu getInstance() {
		return INSTANCE;
	}

	// ====================================================
	// VARIABLES
	// ====================================================
	public MainMenuScreens mCurrentScreen = MainMenuScreens.Title;
	private Entity mHomeMenuScreen;
	private Entity mLevelSelectScreen;
	private Sprite mMagneTankBGSprite;
	private LevelSelector mLevelSelector;

	// ====================================================
	// CONSTRUCTOR
	// ====================================================
	public MainMenu() {
		super(0.001f);
		this.setTouchAreaBindingOnActionDownEnabled(true); // set listen touch
															// event
		this.setTouchAreaBindingOnActionMoveEnabled(true);
	}

	// ====================================================
	// METHODS
	// ====================================================
	public void goToTitleScreen() {
		this.mCurrentScreen = MainMenuScreens.Title;
		this.mHomeMenuScreen.registerEntityModifier(new MoveModifier(0.25f,
				this.mHomeMenuScreen.getX(), this.mHomeMenuScreen.getY(), 0f,
				0f));
		this.mLevelSelectScreen.registerEntityModifier(new MoveModifier(0.25f,
				this.mLevelSelectScreen.getX(), this.mLevelSelectScreen.getY(),
				mCameraWidth, 0f));
		this.mMagneTankBGSprite.registerEntityModifier(new MoveModifier(0.25f,
				this.mMagneTankBGSprite.getX(), this.mMagneTankBGSprite.getY(),
				(this.mMagneTankBGSprite.getWidth() * this.mMagneTankBGSprite
						.getScaleX()) / 2f,
				(this.mMagneTankBGSprite.getHeight() * this.mMagneTankBGSprite
						.getScaleY()) / 2f));
	}

	@Override
	public void onHideScene() {
	}

	@Override
	public Scene onLoadingScreenLoadAndShown() {
		ResourceManager.loadMenuResources();
		TimelessWarriorsSmoothCamera.setupForMenus();
		final Scene MenuLoadingScene = new Scene();
		this.mMagneTankBGSprite = new Sprite(0f, 0f,
				ResourceManager.menuBackgroundTR, ResourceManager.getActivity()
						.getVertexBufferObjectManager());
		this.mMagneTankBGSprite
				.setScale(ResourceManager.getInstance().cameraHeight
						/ ResourceManager.menuBackgroundTR.getHeight());
		this.mMagneTankBGSprite.setPosition(
				(this.mMagneTankBGSprite.getWidth() * this.mMagneTankBGSprite
						.getScaleX()) / 2f,
				(this.mMagneTankBGSprite.getHeight() * this.mMagneTankBGSprite
						.getScaleY()) / 2f);
		this.mMagneTankBGSprite.setZIndex(-999);
		MenuLoadingScene.attachChild(this.mMagneTankBGSprite);
		MenuLoadingScene.attachChild(new Text(mHalfCameraWidth,
				mHalfCameraHeight, ResourceManager.fontDefaultMagneTank48,
				"Loading...", ResourceManager.getActivity()
						.getVertexBufferObjectManager()));
		return MenuLoadingScene;
	}

	@Override
	public void onLoadingScreenUnloadAndHidden() {
		this.mMagneTankBGSprite.detachSelf();
	}

	@Override
	public void onLoadScene() {
		// Load the game resources
		ResourceManager.loadGameResources();

		this.mLevelSelector = new LevelSelector(
				mHalfCameraWidth,
				mHalfCameraHeight,
				TimelessWarriorsActivity
						.getIntFromSharedPreferences(TimelessWarriorsActivity.SHARED_PREFS_LEVEL_MAX_REACHED) + 1,
				1, ResourceManager.menuLevelIconTR,
				ResourceManager.fontDefault32Bold, this);

		this.mHomeMenuScreen = new Entity(0f, mCameraHeight) {
			boolean hasloaded = false;

			@Override
			protected void onManagedUpdate(final float pSecondsElapsed) {
				super.onManagedUpdate(pSecondsElapsed);
				if (!this.hasloaded) {
					this.hasloaded = true;
					this.registerEntityModifier(new MoveModifier(0.25f, 0f,
							mCameraHeight, 0f, 0f));
				}
			}
		};
		this.mLevelSelectScreen = new Entity(mCameraWidth, 0f);

		final TiledTextureRegion MainMenuButtons = ResourceManager.menuMainButtonsTTR;
		final float ButtonSpacing = 25f * ResourceManager.getInstance().cameraScaleFactorY;
		final GrowButton PlayButton = new GrowButton(
				(mCameraWidth * 0.75f),
				mHalfCameraHeight + MainMenuButtons.getHeight() + ButtonSpacing,
				MainMenuButtons.getTextureRegion(0), false) {
			@Override
			public void onClick() {
				if (MainMenu.this.mCurrentScreen == MainMenuScreens.Title) {
					MainMenu.this.mCurrentScreen = MainMenuScreens.LevelSelector;
					MainMenu.this.mHomeMenuScreen
							.registerEntityModifier(new MoveModifier(0.25f,
									MainMenu.this.mHomeMenuScreen.getX(),
									MainMenu.this.mHomeMenuScreen.getY(),
									-mCameraWidth, 0f));
					// animation hiện level screen
					MainMenu.this.mLevelSelectScreen
							.registerEntityModifier(new MoveModifier(0.25f,
									MainMenu.this.mLevelSelectScreen.getX(),
									MainMenu.this.mLevelSelectScreen.getY(),
									0f, 0f));
					// chuyển tấm hình sang phải xíu tạo cảm giác chuyển trang
					MainMenu.this.mMagneTankBGSprite
							.registerEntityModifier(new MoveModifier(
									0.25f,
									MainMenu.this.mMagneTankBGSprite.getX(),
									MainMenu.this.mMagneTankBGSprite.getY(),
									((MainMenu.this.mMagneTankBGSprite
											.getWidth() * MainMenu.this.mMagneTankBGSprite
											.getScaleX()) / 2f)
											- (150f * MainMenu.this.mMagneTankBGSprite
													.getScaleX()),
									(MainMenu.this.mMagneTankBGSprite
											.getHeight() * MainMenu.this.mMagneTankBGSprite
											.getScaleY()) / 2f));
				}
			}
		};
		this.mHomeMenuScreen.attachChild(PlayButton);
		this.registerTouchArea(PlayButton);

		final GrowButton OptionsButton = new GrowButton(
				PlayButton.getX(),
				PlayButton.getY() - MainMenuButtons.getHeight() - ButtonSpacing,
				MainMenuButtons.getTextureRegion(1), false) {
			@Override
			public void onClick() {
				SceneManager.getInstance().showLayer(OptionsLayer.getInstance(), false, false, true);
			}
		};
		this.mHomeMenuScreen.attachChild(OptionsButton);
		this.registerTouchArea(OptionsButton);

		// If we were using an upgrade button, it would go here.

		final GrowButton AboutButton = new GrowButton(OptionsButton.getX(),
				OptionsButton.getY() - MainMenuButtons.getHeight()
						- ButtonSpacing, MainMenuButtons.getTextureRegion(3), false) {
			@Override
			public void onClick() {
				ResourceManager.getActivity().runOnUiThread(new Runnable() {
					@Override
					public void run() {
						final AlertDialog.Builder builder = new AlertDialog.Builder(
								ResourceManager.getActivity())
								.setIcon(com.blackmoon.TimelessWarriors.R.drawable.icon)
								.setTitle("MagneTank")
								.setMessage(
										Html.fromHtml("<u>Running Man</u>"
												+ "<br><b>Game vừa chơi vừa học tiếng anh.</b>"
												+ "<br><b><u>Đồ Án: </u></b><br>Nhập Môn Game."
												+ "<br><b><u>GVHD: </u></b><br>Ths. Hầu Nguyễn Thành Nam."
												+ "<br>"
												+ "<br><b><u>Tài Liệu Tham Khảo:</u></b>"
												+ "<br><b>Sách:</b> <i>AndEngine for Android Game Development Cookbook</i>"
												+ "<br><b>Tác Giả:</b> Jayme Schroeder, Brian Broyles"
												+ "<br><b>Load Map:</b> MapEditor Andengine"
												+ "<br><b>Source Tham Khảo:</b> BoomOffine - Mr.Giỏi (Android.vn)"
												+ "<br>"
												+ "<br><b><u>Contact Information:</u></b>"
												+ "<br><a href=\"mailto:phiung1992@gmail.com\">Email BlackmoonT92</a>"))
								.setPositiveButton("Back",
										new DialogInterface.OnClickListener() {
											@Override
											public void onClick(
													final DialogInterface dialog,
													final int id) {
											}
										});
						final AlertDialog alert = builder.create();
						alert.show();
						((TextView) alert.findViewById(android.R.id.message))
								.setMovementMethod(LinkMovementMethod
										.getInstance());
					}
				});
			}
		};
		this.mHomeMenuScreen.attachChild(AboutButton);
		this.registerTouchArea(AboutButton);

		final Sprite MainTitleText = new Sprite(0f, 0f,
				ResourceManager.menuMainTitleTR, ResourceManager.getActivity()
						.getVertexBufferObjectManager());
		MainTitleText.setPosition(mCameraWidth / 2f, mCameraHeight
				- (MainTitleText.getHeight() / 2f));
		this.mHomeMenuScreen.attachChild(MainTitleText);

		this.mLevelSelectScreen.attachChild(this.mLevelSelector);
		final GrowButton BackToTitleButton = new GrowButton(0f, 0f,
				ResourceManager.menuArrow1TR, false) {
			@Override
			public void onClick() {
				if (MainMenu.this.mCurrentScreen == MainMenuScreens.LevelSelector) {
					MainMenu.this.goToTitleScreen();
				}
			}
		};
		BackToTitleButton.setFlippedHorizontal(true);
		BackToTitleButton.setScales(
				2f / ResourceManager.getInstance().cameraScaleFactorY,
				(2f / ResourceManager.getInstance().cameraScaleFactorY) * 1.2f);
		BackToTitleButton
				.setPosition((BackToTitleButton.getWidth() * BackToTitleButton
						.getScaleX()) / 2f, mHalfCameraHeight);
		this.mLevelSelectScreen.attachChild(BackToTitleButton);
		this.registerTouchArea(BackToTitleButton);

		this.attachChild(this.mHomeMenuScreen);
		this.attachChild(this.mLevelSelectScreen);

		final GrowToggleButton MusicToggleButton = new GrowToggleButton(
				ResourceManager.MusicToggleTTR.getWidth() / 2f,
				ResourceManager.MusicToggleTTR.getHeight() / 2f,
				ResourceManager.MusicToggleTTR, !SFXManager.isMusicMuted()) {
			@Override
			public boolean checkState() {
				return !SFXManager.isMusicMuted();
			}

			@Override
			public void onClick() {
				SFXManager.toggleMusicMuted();
			}
		};
		final GrowToggleButton SoundToggleButton = new GrowToggleButton(
				MusicToggleButton.getX() + 75f, MusicToggleButton.getY(),
				ResourceManager.SoundToggleTTR, !SFXManager.isSoundMuted()) {
			@Override
			public boolean checkState() {
				return !SFXManager.isSoundMuted();
			}

			@Override
			public void onClick() {
				SFXManager.toggleSoundMuted();
			}
		};
		this.attachChild(MusicToggleButton);
		this.attachChild(SoundToggleButton);
		this.registerTouchArea(MusicToggleButton);
		this.registerTouchArea(SoundToggleButton);
	}

	@Override
	public void onShowScene() {
		this.RefreshLevelStars();
		TimelessWarriorsSmoothCamera.setupForMenus();
		if (!this.mMagneTankBGSprite.hasParent()) {
			this.attachChild(this.mMagneTankBGSprite);
			this.sortChildren();
		}
	}

	@Override
	public void onUnloadScene() {
	}

	public void RefreshLevelStars() {
		this.mLevelSelector.refreshAllButtonStars();
	}
}