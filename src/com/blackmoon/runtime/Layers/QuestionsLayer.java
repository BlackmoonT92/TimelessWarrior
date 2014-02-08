package com.blackmoon.runtime.Layers;

import java.util.Random;

import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;

import com.blackmoon.questions.QuestionObject;
import com.blackmoon.questions.ReadXml;
import com.blackmoon.runtime.TimelessWarriorsActivity;
import com.blackmoon.runtime.GameLevels.GameLevel;
import com.blackmoon.runtime.GameLevels.Levels;
import com.blackmoon.runtime.Input.GrowButton;
import com.blackmoon.runtime.Input.GrowToggleTextButton;
import com.blackmoon.runtime.Managers.ResourceManager;
import com.blackmoon.runtime.Managers.SceneManager;

/**
 * The LevelPauseLayer class represents the layer that is shown to the player
 * when a level is paused. It displays the current level number, score, and
 * high-score as well as buttons to go back to the game, back to the
 * level-select screen, restart the level, or skip to the next level.
 * 
 *** @author Brian Broyles - IFL Game Studio
 **/
public class QuestionsLayer extends ManagedLayer {
	private static String rightAnswer = "D";
	static QuestionObject mQuestionObject = new QuestionObject();
	ReadXml readXml;
	// ====================================================
	// CONSTANTS
	// ====================================================
	private static final QuestionsLayer INSTANCE = new QuestionsLayer();

	// ====================================================
	// INSTANCE GETTERS
	// ====================================================
	public static QuestionsLayer getInstance() {
		return INSTANCE;
	}

	public static QuestionsLayer getInstance(GameLevel pCurrentLevel) {
		INSTANCE.setCurrentLevel(pCurrentLevel);
		
		return INSTANCE;
	}

	// ====================================================
	// VARIABLES
	// ====================================================
	private GameLevel mCurrentLevel;
	private boolean mIsGoingBackToLevel = true;
	private Sprite mLayerBG;
	private boolean keyFlipA = false;
	private boolean keyFlipB = false;
	private boolean keyFlipC = false;
	private boolean keyFlipD = false;

	private Text mQuestion;
	
	private GrowToggleTextButton mAnswerA;
	private GrowToggleTextButton mAnswerB;
	private GrowToggleTextButton mAnswerC;
	private GrowToggleTextButton mAnswerD;

	// ====================================================
	// UPDATE HANDLERS
	// ====================================================
	// Animates the layer to slide in from the top.
	IUpdateHandler mSlideInUpdateHandler = new IUpdateHandler() {
		@Override
		public void onUpdate(final float pSecondsElapsed) {
			if (QuestionsLayer.this.mLayerBG.getY() > 0f) {
				QuestionsLayer.this.mLayerBG
						.setY(Math.max(
								QuestionsLayer.this.mLayerBG.getY()
										- (pSecondsElapsed * mSLIDE_PIXELS_PER_SECONDS),
								0f));
			} else {
				ResourceManager.getInstance().engine
						.unregisterUpdateHandler(this);
			}
		}

		@Override
		public void reset() {
		}
	};
	// Animates the layer to slide out through the top and tell the SceneManager
	// to hide it when it is off-screen;
	IUpdateHandler mSlideOutUpdateHandler = new IUpdateHandler() {
		@Override
		public void onUpdate(final float pSecondsElapsed) {
			if (QuestionsLayer.this.mLayerBG.getY() < ((ResourceManager
					.getInstance().cameraHeight / 2f) + (QuestionsLayer.this.mLayerBG
					.getHeight() / 2f))) {
				QuestionsLayer.this.mLayerBG
						.setY(Math.min(
								QuestionsLayer.this.mLayerBG.getY()
										+ (pSecondsElapsed * mSLIDE_PIXELS_PER_SECONDS),
								(ResourceManager.getInstance().cameraHeight / 2f)
										+ (QuestionsLayer.this.mLayerBG
												.getHeight() / 2f)));
			} else {
				ResourceManager.getInstance().engine
						.unregisterUpdateHandler(this);
				SceneManager.getInstance().hideLayer();
				if (QuestionsLayer.this.mIsGoingBackToLevel) {
					return;
				}
				

			}
		}

		@Override
		public void reset() {
		}
	};

	// ====================================================
	// METHODS
	// ====================================================
	@Override
	public void onHideLayer() {
		// Register the slide-out animation with the engine.
		ResourceManager.getInstance().engine
				.registerUpdateHandler(this.mSlideOutUpdateHandler);
		keyFlipA = false;
		keyFlipB = false;
		keyFlipC = false;
		keyFlipD = false;
	}

	@Override
	public void onLoadLayer() {
		//readXml = new ReadXml();
		if (this.mHasLoaded) {
			return;
		}
		

		this.mHasLoaded = true;
		this.setTouchAreaBindingOnActionDownEnabled(true);
		this.setTouchAreaBindingOnActionMoveEnabled(true);

		final Rectangle fadableBGRect = new Rectangle(0f, 0f,
				ResourceManager.getInstance().cameraWidth,
				ResourceManager.getInstance().cameraHeight, ResourceManager
						.getActivity().getVertexBufferObjectManager());
		fadableBGRect.setColor(0f, 0f, 0f, 0.8f);
		this.attachChild(fadableBGRect);

		this.mLayerBG = new Sprite(
				0f,
				(ResourceManager.getInstance().cameraHeight / 2f)
						+ (ResourceManager.gameLevelLayerBGTR.getHeight() / 2f),
				ResourceManager.gameLevelLayerBGTR, ResourceManager
						.getActivity().getVertexBufferObjectManager());
		this.attachChild(this.mLayerBG);
		this.mLayerBG
				.setScale(1.5f / ResourceManager.getInstance().cameraScaleFactorY);

		final GrowButton ReturnToLevelButton = new GrowButton((-ResourceManager
				.getEngine().getCamera().getWidth() / 2f)
				+ (ResourceManager.gamePauseButtonTR.getWidth() / 2f),
				(ResourceManager.getEngine().getCamera().getHeight() / 2f)
						- (ResourceManager.gamePauseButtonTR.getHeight() / 2f),
				ResourceManager.gamePauseButtonTR, false) {
			@Override
			public void onClick() {
				QuestionsLayer.this.onHideLayer();
			}
		};
		this.attachChild(ReturnToLevelButton);
		this.registerTouchArea(ReturnToLevelButton);

		this.mQuestion = new Text(0f, 0f,
				ResourceManager.fontDefaultMagneTank48,
				mQuestionObject.getTxtQuestion(), ResourceManager.getActivity()
						.getVertexBufferObjectManager());
		this.mQuestion.setScale(Math.min(390f / this.mQuestion.getWidth(), 1f));
		this.mQuestion.setPosition(256f, 225f);
		this.mQuestion.setColor(0.31f, 0.35f, 0.31f);
		this.mLayerBG.attachChild(this.mQuestion);

		
		this.mAnswerA = new GrowToggleTextButton(256f, 185f,
				"A: "+ mQuestionObject.getTxtAnswer1E(),"A: "+ mQuestionObject.getTxtAnswer1V(),
				ResourceManager.fontDefaultMagneTank48, !keyFlipA) {
			@Override
			public boolean checkState() {
				return !keyFlipA;
			}

			@Override
			public void onClick() {
				// show vietnamese and plus point
				if (rightAnswer.equalsIgnoreCase("A")) {
					addPointRightAnswer();
				} else {
					keyFlipA = !keyFlipA;
					QuestionsLayer.this.mIsGoingBackToLevel = false;
					GameLevel.addPointsToScore(-100);
				}

			}
		};

		this.mAnswerA.setScales(
				Math.min(390f / this.mAnswerA.getWidth(), 0.85f),
				Math.min(390f / this.mAnswerA.getWidth(), 0.85f) * 1.2f);
		this.mAnswerA.setColor(0.212f, 0.275f, 0.212f);
		this.mLayerBG.attachChild(this.mAnswerA);
		this.registerTouchArea(this.mAnswerA);

		this.mAnswerB = new GrowToggleTextButton(256f, 145f,
				"B: "+ mQuestionObject.getTxtAnswer2E(), "B: "+ mQuestionObject.getTxtAnswer2V(),
				ResourceManager.fontDefaultMagneTank48, !keyFlipB) {
			@Override
			public boolean checkState() {
				return !keyFlipB;
			}

			@Override
			public void onClick() {
				// show vietnamese and plus point
				if (rightAnswer.equalsIgnoreCase("B")) {
					addPointRightAnswer();
				} else {
					keyFlipB = !keyFlipB;
					QuestionsLayer.this.mIsGoingBackToLevel = false;
					GameLevel.addPointsToScore(-100);
				}
			}
		};
		this.mAnswerB.setScales(
				Math.min(390f / this.mAnswerB.getWidth(), 0.85f),
				Math.min(390f / this.mAnswerB.getWidth(), 0.85f) * 1.2f);
		this.mAnswerB.setColor(0.212f, 0.275f, 0.212f);
		this.mLayerBG.attachChild(this.mAnswerB);
		this.registerTouchArea(this.mAnswerB);

		this.mAnswerC = new GrowToggleTextButton(256f, 105f,
				"C: "+ mQuestionObject.getTxtAnswer3E(), "C: "+ mQuestionObject.getTxtAnswer3V(),
				ResourceManager.fontDefaultMagneTank48, !keyFlipC) {
			@Override
			public boolean checkState() {
				return !keyFlipC;
			}

			@Override
			public void onClick() {
				// show vietnamese and plus point
				if (rightAnswer.equalsIgnoreCase("C")) {
					addPointRightAnswer();
				} else {

					keyFlipC = !keyFlipC;
					QuestionsLayer.this.mIsGoingBackToLevel = false;
					GameLevel.addPointsToScore(-100);
				}
			}
		};

		this.mAnswerC.setScales(
				Math.min(390f / this.mAnswerC.getWidth(), 0.85f),
				Math.min(390f / this.mAnswerC.getWidth(), 0.85f) * 1.2f);
		this.mAnswerC.setColor(0.212f, 0.275f, 0.212f);
		this.mLayerBG.attachChild(this.mAnswerC);
		this.registerTouchArea(this.mAnswerC);

		this.mAnswerD = new GrowToggleTextButton(256f, 65f,
				"D: "+ mQuestionObject.getTxtAnswer4E(),  "D: "+ mQuestionObject.getTxtAnswer4V(),
				ResourceManager.fontDefaultMagneTank48, !keyFlipD) {
			@Override
			public boolean checkState() {
				return !keyFlipD;
			}

			@Override
			public void onClick() {
				// show vietnamese and plus point
				if (rightAnswer.equalsIgnoreCase("D")) {
					addPointRightAnswer();
				} else {

					keyFlipD = !keyFlipD;
					QuestionsLayer.this.mIsGoingBackToLevel = false;
					GameLevel.addPointsToScore(-100);
				}
			}

		};

		this.mAnswerD.setScales(
				Math.min(390f / this.mAnswerD.getWidth(), 0.85f),
				Math.min(390f / this.mAnswerD.getWidth(), 0.85f) * 1.2f);
		this.mAnswerD.setColor(0.212f, 0.275f, 0.212f);
		this.mLayerBG.attachChild(this.mAnswerD);
		this.registerTouchArea(this.mAnswerD);

		this.setPosition(ResourceManager.getInstance().cameraWidth / 2f,
				ResourceManager.getInstance().cameraHeight / 2f);
	}

	private void addPointRightAnswer() {
		GameLevel.addPointsToScore(200);
		QuestionsLayer.this.onHideLayer();
	}

	@Override
	public void onShowLayer() {
	/*	Random random = new Random();
		int key = random.nextInt(5) + 0; 
		mQuestionObject = ReadXml.listQuestion.get(key);

		rightAnswer = mQuestionObject.getRightAnswer();*/
		
		QuestionsLayer.this.mIsGoingBackToLevel = true;
		// Register the slide-in animation with the engine.
		
/*
		// Set the title of the layer
		this.mQuestion.setText(mQuestionObject.getTxtQuestion());
		this.mQuestion.setScale(Math.min(390f / this.mQuestion.getWidth(), 1f));

		// Show the current level's score
		// this.mAnswerA.setText("CURRENT SCORE: " +
		// (this.mCurrentLevel.CurrentScore));
		this.mAnswerA.setmFalseText("A:"+ mQuestionObject.getTxtAnswer1E());
		this.mAnswerA.setmTrueText("A:"+ mQuestionObject.getTxtAnswer1V());
		this.mAnswerA.setScale(Math.min(352f / this.mAnswerA.getWidth(), 1f));

		this.mAnswerB.setmFalseText("B:"+ mQuestionObject.getTxtAnswer2E());
		this.mAnswerB.setmTrueText("B:"+ mQuestionObject.getTxtAnswer2V());
		this.mAnswerB.setScale(Math.min(352f / this.mAnswerB.getWidth(), 1f));
		
		this.mAnswerC.setmFalseText("C:"+ mQuestionObject.getTxtAnswer3E());
		this.mAnswerC.setmTrueText("C:"+ mQuestionObject.getTxtAnswer3V());
		this.mAnswerC.setScale(Math.min(352f / this.mAnswerC.getWidth(), 1f));
		
		this.mAnswerD.setmFalseText("D:"+ mQuestionObject.getTxtAnswer4E());
		this.mAnswerD.setmTrueText("D:"+ mQuestionObject.getTxtAnswer4V());
		this.mAnswerD.setScale(Math.min(352f / this.mAnswerD.getWidth(), 1f));*/
		ResourceManager.getInstance().engine
		.registerUpdateHandler(this.mSlideInUpdateHandler);
	}

	@Override
	public void onUnloadLayer() {
	}

	public void setCurrentLevel(final GameLevel pCurrentLevel) {
		this.mCurrentLevel = pCurrentLevel;
	}

}