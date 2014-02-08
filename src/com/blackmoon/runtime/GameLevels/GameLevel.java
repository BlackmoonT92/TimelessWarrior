package com.blackmoon.runtime.GameLevels;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.andengine.extension.tmx.TMXTiledMap;
import org.andengine.engine.camera.hud.HUD;
import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.entity.modifier.DelayModifier;
import org.andengine.entity.modifier.MoveModifier;
import org.andengine.entity.modifier.SequenceEntityModifier;
import org.andengine.entity.scene.CameraScene;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.extension.physics.box2d.FixedStepPhysicsWorld;
import org.andengine.extension.physics.box2d.PhysicsConnector;
import org.andengine.extension.physics.box2d.PhysicsFactory;
import org.andengine.extension.physics.box2d.util.Vector2Pool;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.util.adt.pool.GenericPool;
import org.andengine.util.math.MathUtils;
import org.andengine.util.modifier.ease.EaseElasticOut;

import android.hardware.SensorManager;
import android.util.Log;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.blackmoon.object.Bullet;
import com.blackmoon.object.Coin;
import com.blackmoon.object.DeathBeamStatic;
import com.blackmoon.object.Enemy;
import com.blackmoon.object.Hero;
import com.blackmoon.object.MetalBeamStatic;
import com.blackmoon.object.ParallaxLayer;
import com.blackmoon.object.ParallaxLayer.ParallaxEntity;
import com.blackmoon.object.StatusPlayer;
import com.blackmoon.runtime.SwitchableFixedStepEngine;
import com.blackmoon.runtime.TimelessWarriorsSmoothCamera;
import com.blackmoon.runtime.GameLevels.Levels.BeamsInLevelDef;
import com.blackmoon.runtime.GameLevels.Levels.Coins;
import com.blackmoon.runtime.GameLevels.Levels.DeathsInLevelDef;
import com.blackmoon.runtime.GameLevels.Levels.EnemyObject;
import com.blackmoon.runtime.GameLevels.Levels.LevelDef;
import com.blackmoon.runtime.Input.GrowButton;
import com.blackmoon.runtime.Input.GrowToggleButton;
import com.blackmoon.runtime.Layers.LevelLoseLayer;
import com.blackmoon.runtime.Layers.LevelPauseLayer;
import com.blackmoon.runtime.Layers.LevelWonLayer;
import com.blackmoon.runtime.Layers.QuestionsLayer;
import com.blackmoon.runtime.Managers.GameManager;
import com.blackmoon.runtime.Managers.GameManager.GameLevelGoal;
import com.blackmoon.runtime.Managers.ResourceManager;
import com.blackmoon.runtime.Managers.SFXManager;
import com.blackmoon.runtime.Managers.SceneManager;

/**
 * The GameLevel class brings all of the other in-game classes together to form
 * the playable part of MagneTank. It handles the construction and execution of
 * each actual game level. It extends a customized ManagedGameScene that
 * incorporates a list of LoadingRunnables, which create the level in steps that
 * allow each progression of the level construction to be shown on the screen.
 * The GameLevel class also determines the completion or failure of each game
 * level using the GameManager class to test for win or lose conditions.
 * 
 *** @author Brian Broyles - IFL Game Studio
 **/
public class GameLevel extends ManagedGameScene implements
		IOnSceneTouchListener, GameLevelGoal {

	// ====================================================
	// CONSTANTS
	// ====================================================

	public static final float mLEVEL_WIDTH = 20000f;

	private static final float mPHYSICS_WORLD_GRAVITY = -SensorManager.GRAVITY_EARTH * 4f;
	private static final int mPHYSICS_WORLD_POSITION_ITERATIONS = 20;
	private static final int mPHYSICS_WORLD_VELOCITY_ITERATIONS = 30;
	private static final float mSKY_BACKGROUND_DETAIL_Y = 800f;
	private static final float mSKY_BACKGROUND_DETAIL_X = 1280f;
	private static final float mCAMERA_ZOOM = 0.433f;

	private static final String mLOADING_STEP_STRING_1 = "Painting the sky...";
	private static final String mLOADING_STEP_STRING_2 = "Moving dirt...";
	private static final String mLOADING_STEP_STRING_3 = "Create HUD Entites...";
	private static final String mLOADING_STEP_STRING_4 = "Create Hero...";
	private static final String mLOADING_STEP_STRING_5 = "Create coin and static wall...";
	private static final String mLOADING_STEP_STRING_6 = "Enemy in sight...";
	private static final String mLOADING_STEP_STRING_7 = "Done Loading";
	private static final String mLEVEL_NUMBER_PRETEXT = "LEVEL ";
	private static final String mON_SCREEN_SCORE_PRETEXT = "SCORE: ";

	// ====================================================
	// VARIABLES
	// ====================================================
	public FixedStepPhysicsWorld mPhysicsWorld;
	// MyAnalogOnScreenControl mAnalogOnScreenControl;
	public static Sprite endWorldWall;
	public final LevelDef mLevelDef;
	public float mBasePositionX;
	public float mBasePositionY;
	public ArrayList<float[]> mBasePositions = new ArrayList<float[]>();
	public Hero mHero;
	public boolean mIsLevelSettled = false;
	public float mBaseTotalMovementTime;
	public boolean isLevelFail = false;
	public boolean mIsThereBaseMovement = false;
	public int TotalScorePossible;
	public static int CurrentScore;
	
	private boolean isSecondButtleAvable = true;

	public final static TimelessWarriorsSmoothCamera mCamera = ResourceManager
			.getCamera();
	private static float SCALED_CAMERA_ZOOM = mCAMERA_ZOOM
			* ResourceManager.getInstance().cameraScaleFactorX;

	private static boolean mHasCompletionTimerRun = false;
	private static Text ScoreText;

	public static List<Bullet> listPlayerBullet = new ArrayList<Bullet>();

	public static List<Bullet> listEnemyBullet = new ArrayList<Bullet>();
	TMXTiledMap mTMXTiledMap;
	// ====================================================
	// UPDATE HANDLERS
	// ====================================================

	public IUpdateHandler onCompletionTimer = new IUpdateHandler() {
		@Override
		public void onUpdate(final float pSecondsElapsed) {
			if(isLevelFail){
				GameLevel.this.onLevelFailed();
			}
			if (mHasCompletionTimerRun) {

				if (GameLevel.this.isLevelCompleted()) {
					GameLevel.this.onLevelCompleted();

				}
				GameLevel.this.unregisterUpdateHandler(this);
			}
		}

		@Override
		public void reset() {
		}
	};

	// ====================================================
	// OBJECT POOLS
	// ====================================================
	GenericPool<Text> ScoreTextPool = new GenericPool<Text>() {
		@Override
		protected Text onAllocatePoolItem() {
			return new Text(0f, 0f, ResourceManager.fontDefaultMagneTank48, "",
					15, ResourceManager.getActivity()
							.getVertexBufferObjectManager()) {
				Text ThisText = this;

				@Override
				public void onAttached() {
					this.setVisible(true);
					this.setAlpha(1f);
					this.setScale(4f);
					this.setRotation(MathUtils.random(-35f, 35f));
					ResourceManager.getCamera();
				}

				@Override
				protected void onManagedUpdate(final float pSecondsElapsed) {
					super.onManagedUpdate(pSecondsElapsed);
					this.setAlpha(this.getAlpha() - (pSecondsElapsed / 2f));
					this.setScale(this.getScaleX() - pSecondsElapsed);
					this.setRotation(this.getRotation()
							- (this.getRotation() * pSecondsElapsed * 2f));
					if (this.getAlpha() <= 0.1f) {
						this.setVisible(false);
						ResourceManager.getActivity().runOnUpdateThread(
								new Runnable() {
									@Override
									public void run() {
										ThisText.detachSelf();
										GameLevel.this.ScoreTextPool
												.recyclePoolItem(ThisText);
									}
								});
					}
				
				}
			};
		}
	};

	// ====================================================
	// CONSTRUCTOR
	// ====================================================
	public GameLevel(final LevelDef pLevelDef) {
		this.mLevelDef = pLevelDef;
	}

	// ====================================================
	// METHODS
	// ====================================================
	public static void addPointsToScore(final int pPoints) {
		GameLevel.CurrentScore += pPoints;
		GameLevel.ScoreText.setText(mON_SCREEN_SCORE_PRETEXT.toString()
				+ GameLevel.CurrentScore);

	}

	public void createExplosion(final Vector2 pBombPos,
			final float pExplosionConstant) {
		final Iterator<Body> bodies = this.mPhysicsWorld.getBodies();
		while (bodies.hasNext()) {
			final Body b = bodies.next();
			if (b.getType() == BodyType.DynamicBody) {
				final Vector2 BodyPos = Vector2Pool.obtain(b.getWorldCenter());
				final Vector2 NormalizedDirectionFromBombToBody = Vector2Pool
						.obtain(BodyPos).sub(pBombPos).nor();
				final float dist = BodyPos.dst(pBombPos);
				final Vector2 ForceBasedOnDist = Vector2Pool.obtain(
						NormalizedDirectionFromBombToBody).mul(
						pExplosionConstant * (1f / dist));
				b.applyForce(ForceBasedOnDist, b.getWorldCenter());
				Vector2Pool.recycle(ForceBasedOnDist);
				Vector2Pool.recycle(NormalizedDirectionFromBombToBody);
				Vector2Pool.recycle(BodyPos);
			}
		}
	}

	public void disposeLevel() {
		GameLevel.mCamera.setChaseEntity(null);
		final HUD oldHUD = GameLevel.mCamera.getHUD();
		if (oldHUD != null) {
			oldHUD.detachSelf();
			oldHUD.dispose();
			GameLevel.mCamera.setHUD(null);
		}
		TimelessWarriorsSmoothCamera.setupForMenus();
	}

	@Override
	public boolean isLevelCompleted() {

		return mHasCompletionTimerRun;

	}

	@Override
	public boolean isLevelFailed() {
		return mHasCompletionTimerRun;
	}

	@Override
	public void onLevelCompleted() {
		if (this.mHasCompletionTimerRun) {
			// player won - show winning screen
			SceneManager.getInstance().showLayer(
					LevelWonLayer.getInstance(this), false, false, true);
		} else {
			GameLevel.this.registerUpdateHandler(this.onCompletionTimer);
		}
	}

	@Override
	public void onLevelFailed() {
		if (!this.mHasCompletionTimerRun) {
			// player lose

			SceneManager.getInstance().showLayer(
					LevelLoseLayer.getInstance(this), false, true, true);
		} else {
			GameLevel.this.registerUpdateHandler(this.onCompletionTimer);
		}
	}

	@Override
	public void onLoadLevel() {
		GameManager.setGameLevel(this);

		this.ScoreTextPool.batchAllocatePoolItems(8);

		this.mPhysicsWorld = new FixedStepPhysicsWorld(
				ResourceManager.getEngine().mStepsPerSecond, new Vector2(0f,
						mPHYSICS_WORLD_GRAVITY), true,
				mPHYSICS_WORLD_VELOCITY_ITERATIONS,
				mPHYSICS_WORLD_POSITION_ITERATIONS);
		this.registerUpdateHandler(this.mPhysicsWorld);

		this.addLoadingStep(new LoadingRunnable(mLOADING_STEP_STRING_1, this) {
			@Override
			public void onLoad() {
				final CameraScene testscn1 = new CameraScene(ResourceManager
						.getEngine().getCamera());

				final Sprite Sky = new Sprite(0f, 0f,
						ResourceManager.gameSkyBackgroundTR, ResourceManager
								.getActivity().getVertexBufferObjectManager());
				Sky.setAnchorCenter(0f, 0f);
				Sky.setSize(mSKY_BACKGROUND_DETAIL_X, mSKY_BACKGROUND_DETAIL_Y);
				testscn1.attachChild(Sky);
				GameLevel.this.attachChild(testscn1);

				final ParallaxLayer BGParallaxLayer = new ParallaxLayer(
						GameLevel.mCamera, true, mLEVEL_WIDTH);

				final Sprite BG4 = new Sprite(0f, 256f,
						ResourceManager.gameParallaxBackdrop4TR,
						ResourceManager.getActivity()
								.getVertexBufferObjectManager());
				BGParallaxLayer.attachParallaxEntity(new ParallaxEntity(0.25f,
						BG4, true));

				final Sprite BG3 = new Sprite(0f, 240f,
						ResourceManager.gameParallaxBackdrop3TR,
						ResourceManager.getActivity()
								.getVertexBufferObjectManager());
				BGParallaxLayer.attachParallaxEntity(new ParallaxEntity(0.5f,
						BG3, true));

				final Sprite BG2 = new Sprite(0f, 128f,
						ResourceManager.gameParallaxBackdrop2TR,
						ResourceManager.getActivity()
								.getVertexBufferObjectManager());
				BGParallaxLayer.attachParallaxEntity(new ParallaxEntity(0.75f,
						BG2, true));

				final Sprite BG1 = new Sprite(0f, 64f,
						ResourceManager.gameParallaxBackdrop1TR,
						ResourceManager.getActivity()
								.getVertexBufferObjectManager());
				BGParallaxLayer.attachParallaxEntity(new ParallaxEntity(1f,
						BG1, true));

				final ParallaxLayer CloudParallaxLayer = new ParallaxLayer(
						GameLevel.mCamera, true, mLEVEL_WIDTH);
				CloudParallaxLayer.setParallaxChangePerSecond(-200f);

				final Sprite Cloud1 = new Sprite(0f, 400f,
						ResourceManager.gameCloud1TR, ResourceManager
								.getActivity().getVertexBufferObjectManager());
				Cloud1.setScale(MathUtils.random(1.25f, 1.5f));
				Cloud1.setY((Cloud1.getScaleX() * 150f) + 250f);
				Cloud1.setAlpha(0.4f);
				CloudParallaxLayer.attachParallaxEntity(new ParallaxEntity(
						Cloud1.getScaleX() / 4f, Cloud1, false, MathUtils
								.random(4f, 6f)));

				final Sprite Cloud2 = new Sprite(MathUtils.random(128f, 256f),
						300f, ResourceManager.gameCloud2TR, ResourceManager
								.getActivity().getVertexBufferObjectManager());
				Cloud2.setScale(MathUtils.random(0.75f, 1.25f));
				Cloud2.setY((Cloud2.getScaleX() * 150f) + 150f);
				Cloud2.setAlpha(0.5f);
				CloudParallaxLayer.attachParallaxEntity(new ParallaxEntity(
						Cloud2.getScaleX() / 4f, Cloud2, false, MathUtils
								.random(4f, 6f)));

				testscn1.attachChild(BGParallaxLayer);
				testscn1.attachChild(CloudParallaxLayer);
				testscn1.setScale(Math.max(
						ResourceManager.getInstance().cameraWidth / 1280f,
						ResourceManager.getInstance().cameraHeight / 800f));
			}
		});
		this.addLoadingStep(new LoadingRunnable(mLOADING_STEP_STRING_2, this) {
			@Override
			public void onLoad() {
				new TexturedBezierLandscape(512f,
						GameLevel.mLEVEL_WIDTH - 512f, new float[] { 10f, 100f,
								0f, 0.9f, 10f, 3100f, 3190f, 0f, 1f, 10f },
						GameLevel.this);

			}
		});

		this.addLoadingStep(new LoadingRunnable(mLOADING_STEP_STRING_3, this) {
			@Override
			public void onLoad() {
				GameLevel.mCamera.setHUD(new HUD());
				GameLevel.mCamera.getHUD().setVisible(false);
				GameLevel.ScoreText = new Text(
						GameLevel.mCamera.getWidth() / 2f, 0f,
						ResourceManager.fontDefaultMagneTank48,
						mON_SCREEN_SCORE_PRETEXT + "0      ", ResourceManager
								.getActivity().getVertexBufferObjectManager());
				GameLevel.ScoreText.setPosition(
						GameLevel.mCamera.getWidth() / 2f,
						GameLevel.mCamera.getHeight()
								- (GameLevel.ScoreText.getHeight() / 2f));
				GameLevel.ScoreText.setScale(0.75f);
				GameLevel.ScoreText.setAlpha(0.85f);
				final GrowButton PauseButton = new GrowButton(
						ResourceManager.gamePauseButtonTR.getWidth() / 2f,
						GameLevel.mCamera.getHeight()
								- (ResourceManager.gamePauseButtonTR
										.getHeight() / 2f),
						ResourceManager.gamePauseButtonTR, false) {
					@Override
					public void onClick() {
						SceneManager.getInstance().showLayer(
								LevelPauseLayer.getInstance(GameLevel.this),
								false, true, true);
					}
				};

				final GrowToggleButton MusicToggleButton = new GrowToggleButton(
						PauseButton.getX() + 75f, PauseButton.getY(),
						ResourceManager.MusicToggleTTR, !SFXManager
								.isMusicMuted()) {
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
						MusicToggleButton.getX() + 75f, MusicToggleButton
								.getY(), ResourceManager.SoundToggleTTR,
						!SFXManager.isSoundMuted()) {
					@Override
					public boolean checkState() {
						return !SFXManager.isSoundMuted();
					}

					@Override
					public void onClick() {
						SFXManager.toggleSoundMuted();
					}
				};

				final GrowButton JumpButton = new GrowButton(
						ResourceManager.gameMagOrbCWTR.getWidth() / 2,
						ResourceManager.gameMagOrbCWTR.getHeight() / 2,
						ResourceManager.gameMagOrbCWTR, true) {
					@Override
					public void onClick() {
						mHero.jump();
					}
				};

				GameLevel.mCamera.getHUD().attachChild(GameLevel.ScoreText);
				GameLevel.mCamera.getHUD().attachChild(PauseButton);
				GameLevel.mCamera.getHUD().attachChild(MusicToggleButton);
				GameLevel.mCamera.getHUD().attachChild(SoundToggleButton);
				GameLevel.mCamera.getHUD().attachChild(JumpButton);
				GameLevel.mCamera.getHUD().registerTouchArea(PauseButton);
				GameLevel.mCamera.getHUD().registerTouchArea(MusicToggleButton);
				GameLevel.mCamera.getHUD().registerTouchArea(SoundToggleButton);
				GameLevel.mCamera.getHUD().registerTouchArea(JumpButton);
				final Text LevelIndexText = new Text(GameLevel.mCamera
						.getWidth() / 2f, GameLevel.mCamera.getHeight() / 2f,
						ResourceManager.fontDefaultMagneTank48,
						mLEVEL_NUMBER_PRETEXT
								+ GameLevel.this.mLevelDef.mLevelIndex,
						ResourceManager.getActivity()
								.getVertexBufferObjectManager());
				LevelIndexText.setAlpha(0.85f);
				LevelIndexText
						.registerEntityModifier(new SequenceEntityModifier(
								new DelayModifier(1.5f),
								new MoveModifier(
										2f,
										GameLevel.mCamera.getWidth() / 2f,
										GameLevel.mCamera.getHeight() / 2f,
										GameLevel.mCamera.getWidth()
												- (LevelIndexText.getWidth() * 0.6f),
										GameLevel.mCamera.getHeight()
												- (LevelIndexText.getHeight() * 0.6f),
										EaseElasticOut.getInstance())));
				GameLevel.mCamera.getHUD().attachChild(LevelIndexText);
				GameLevel.mCamera.getHUD()
						.setTouchAreaBindingOnActionDownEnabled(true);
				GameLevel.mCamera.getHUD()
						.setTouchAreaBindingOnActionMoveEnabled(true);
			}
		});
		this.addLoadingStep(new LoadingRunnable(mLOADING_STEP_STRING_4, this) {

			@Override
			public void onLoad() {
				// you must change level , default level = 1
				GameLevel.this.mHero = new Hero(500, 100f, 50f, 1,
						mPhysicsWorld, GameLevel.this);
				mHero.setStatusPlayer(StatusPlayer.MOVE_RIGHT);				
				mHero.onLoadScene(GameLevel.this);
				GameLevel.mCamera.setChaseEntity(mHero.getAnimatedSprite());
				
				// create end world
				ITextureRegion metalBeamStaticTRCopy = ResourceManager.gameMetalBeamStaticTR.deepCopy();
				metalBeamStaticTRCopy.setTextureWidth(2000);
				endWorldWall = new Sprite(mLEVEL_WIDTH, 0,
						metalBeamStaticTRCopy, ResourceManager
								.getEngine().getVertexBufferObjectManager());
				endWorldWall.setRotation(90);
				final FixtureDef FIXTURE_DEF = PhysicsFactory.createFixtureDef(
						0, 0.01f, 0.5f);
				final Body body = PhysicsFactory.createBoxBody(mPhysicsWorld,
						endWorldWall, BodyType.StaticBody, FIXTURE_DEF);
				body.setUserData("EndWorld");
				mPhysicsWorld.registerPhysicsConnector(new PhysicsConnector(
						endWorldWall, body, true, false));

			}
		});

		this.addLoadingStep(new LoadingRunnable(mLOADING_STEP_STRING_5, this) {
			@Override
			public void onLoad() {

				for (final BeamsInLevelDef curBeam : GameLevel.this.mLevelDef.mBeams) {
					switch (curBeam.mBeamType) {

					case MetalStatic:
						new MetalBeamStatic(curBeam.mX, curBeam.mY,
								curBeam.mLength, curBeam.mRotation,
								GameLevel.this);
						break;

					}
				}
				
				for (final Coins coin : GameLevel.this.mLevelDef.mCoins) {
					switch (coin.mCoin) {
					case Normal:

						new Coin(coin.mX, coin.mY, GameLevel.this, mHero);

						break;
					}
				}
				
				for (final DeathsInLevelDef death : GameLevel.this.mLevelDef.mDeaths) {				
					new DeathBeamStatic(death.mX, death.mY, death.mLength, death.mRotation, GameLevel.this);
				}

				float baseMinX = Float.MAX_VALUE;
				float baseMinY = Float.MAX_VALUE;
				float baseMaxX = Float.MIN_VALUE;
				float baseMaxY = Float.MIN_VALUE;
				for (int i = 1; i < GameLevel.this.mBasePositions.size(); i++) {
					baseMinX = Math.min(baseMinX,
							GameLevel.this.mBasePositions.get(i)[0]);
					baseMinY = Math.min(baseMinY,
							GameLevel.this.mBasePositions.get(i)[1]);
					baseMaxX = Math.max(baseMinX,
							GameLevel.this.mBasePositions.get(i)[0]);
					baseMaxY = Math.max(baseMinY,
							GameLevel.this.mBasePositions.get(i)[1]);
				}
				GameLevel.this.mBasePositionX = (baseMinX + baseMaxX) / 2f;
				GameLevel.this.mBasePositionY = (baseMinY + baseMaxY) / 2f;
				GameLevel.mCamera.setBasePosition(
						GameLevel.this.mBasePositionX,
						GameLevel.this.mBasePositionY);

			}
		});
		this.addLoadingStep(new LoadingRunnable(mLOADING_STEP_STRING_6, this) {
			@Override
			public void onLoad() {
				// load enemies....
				for (final EnemyObject enemy : GameLevel.this.mLevelDef.mEnemyObjects) {
					switch (enemy.mEnemyStatus) {
					case Normal:
						Enemy mEnemy= new Enemy(enemy.mX, enemy.mY, mPhysicsWorld, GameLevel.this	, ResourceManager.Hero_Die);
						GameLevel.this.attachChild(mEnemy);
						break;
					}
				}
				
			}
		});

		this.addLoadingStep(new LoadingRunnable(mLOADING_STEP_STRING_7, this) {
			@Override
			public void onLoad() {

				GameLevel.mCamera.getHUD().setVisible(true);

				GameLevel.mCamera.setZoomFactorDirect(SCALED_CAMERA_ZOOM);

				GameLevel.mCamera.setBounds(-50f, -50f, mLEVEL_WIDTH, 2000f);
				GameLevel.mCamera.setBoundsEnabled(true);

				GameManager.setGameLevelGoal(GameLevel.this);

				((SwitchableFixedStepEngine) ResourceManager.getEngine())
						.EnableFixedStep();

			}
		});

		// delegate contact listeners to their respective
		this.mPhysicsWorld.setContactListener(contactListener());

		this.setBackgroundEnabled(true);
		this.setBackground(new Background(0.1f, 0.1f, 0.1f));
		this.setOnSceneTouchListener(this);

	}
	
	
	private void createBullet(TouchEvent pSceneTouchEvent) {
		isSecondButtleAvable = false;
		
		float startX = mHero.getAnimatedSprite().getX() + ResourceManager.Hero_Move.getWidth()
					/ 2 + ResourceManager.bullet_region.getWidth() / 2;	
		
		Vector2 startPosition = new Vector2(startX, mHero.getAnimatedSprite().getY());
		Vector2 endPosition = new Vector2(pSceneTouchEvent.getX(),
				pSceneTouchEvent.getY());
		if(startX < endPosition.x){
			double distance = Math.sqrt(Math.abs((endPosition.x - startPosition.x)
					* (endPosition.x - startPosition.x)
					+ (endPosition.y - startPosition.y)
					* (endPosition.y - startPosition.y)));
			int vx = (int) ((endPosition.x - startPosition.x) * 30 / distance);
			int vy = (int) ((endPosition.y - startPosition.y) * 30 / distance);
			Vector2 velocityBullet = new Vector2(vx, vy);
			Bullet bullet = new Bullet(startPosition.x, startPosition.y, mPhysicsWorld, GameLevel.this);

			bullet.setCullingEnabled(true);
			this.attachChild(bullet);
			SFXManager.playShoot(1f, 1f);
			bullet.shoot(velocityBullet);
			Log.w("shoot", "start");
			listPlayerBullet.add(bullet);
			
			
		}
		isSecondButtleAvable = true;
	
	}
	
	

	private ContactListener contactListener() {
		ContactListener contactListener = new ContactListener() {
			public void beginContact(Contact contact) {
				final Fixture x1 = contact.getFixtureA();
				final Fixture x2 = contact.getFixtureB();

				if (x1.getBody().getUserData() != null
						&& x2.getBody().getUserData() != null) {
					if (x2.getBody().getUserData().equals("hero")||x1.getBody().getUserData().equals("hero")) {
						mHero.increaseFootContacts();
					}
	

					if (x2.getBody().getUserData().equals("death_wall")
							&& x1.getBody().getUserData().equals("hero")) {
						mHero.setRunning(false);
						isLevelFail = true;
						onLevelFailed();
						
					}
					
					
					if (x2.getBody().getUserData().equals("EndWorld")
							&& x1.getBody().getUserData().equals("hero")) {
						mHasCompletionTimerRun = true;
					}

				}
			}

			public void endContact(Contact contact) {
				final Fixture x1 = contact.getFixtureA();
				final Fixture x2 = contact.getFixtureB();

				if (x1.getBody().getUserData() != null
						&& x2.getBody().getUserData() != null) {
					if (x2.getBody().getUserData().equals("hero")||x1.getBody().getUserData().equals("hero")) {
						mHero.decreaseFootContacts();
					}
				}
			}

			public void preSolve(Contact contact, Manifold oldManifold) {

			}

			public void postSolve(Contact contact, ContactImpulse impulse) {

			}
		};
		return contactListener;
	}

	@Override
	public boolean onSceneTouchEvent(final Scene pScene,
			final TouchEvent pSceneTouchEvent) {
		if (pSceneTouchEvent.isActionDown()) {
			// xu ly dan ban
			// lay vi tri hien tai cua player, va vi tri touch, tao dan bay theo
			// huong do
			if(isSecondButtleAvable){
				createBullet(pSceneTouchEvent);
			}

		}
		
		return true;
	}

	public void restartLevel() {
		this.disposeLevel();
		SceneManager.getInstance().showScene(
				new GameLevel(Levels.getLevelDef(this.mLevelDef.mLevelIndex,
						this.mLevelDef.mWorldIndex)));

	}

	public void startNextLevel() {
		this.disposeLevel();

		SceneManager.getInstance().showScene(
				new GameLevel(Levels.getLevelDef(
						this.mLevelDef.mLevelIndex + 1,
						this.mLevelDef.mWorldIndex)));

	}

	public void showQuestionPlayer() {
		SceneManager.getInstance().showLayer(QuestionsLayer.getInstance(this),
				false, true, true);

	}
	
	
}