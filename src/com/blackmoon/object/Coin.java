package com.blackmoon.object;

import org.andengine.entity.modifier.LoopEntityModifier;
import org.andengine.entity.modifier.ScaleModifier;
import org.andengine.entity.sprite.Sprite;
import org.andengine.extension.physics.box2d.PhysicsConnector;
import org.andengine.extension.physics.box2d.PhysicsFactory;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.blackmoon.runtime.GameLevels.GameLevel;
import com.blackmoon.runtime.Layers.QuestionsLayer;
import com.blackmoon.runtime.Managers.ResourceManager;
import com.blackmoon.runtime.Managers.SceneManager;

/**
 * Much like the MetalBeamDynamic class, this class also represents a girder,
 * but the BodyType of this object is set to Static to create an immobile
 * barrier.
 * 
 *** @author Brian Broyles - IFL Game Studio
 **/
public class Coin {

	// ====================================================
	// CONSTANTS
	// ====================================================
	private static final float mMETAL_BEAM_DENSITY = 100f;
	private static final float mMETAL_BEAM_ELASTICITY = 0.0f;
	private static final float mMETAL_BEAM_FRICTION = 0.95f;
	private static final FixtureDef mCOIN_FIXTURE_DEF = PhysicsFactory
			.createFixtureDef(mMETAL_BEAM_DENSITY, mMETAL_BEAM_ELASTICITY,
					mMETAL_BEAM_FRICTION);

	// ====================================================
	// VARIABLES
	// ====================================================
	public Sprite mCoinSprite;

	// ====================================================
	// CONSTRUCTOR
	// ====================================================
	public Coin(float pX, float pY, final GameLevel pGameLevel, final Hero mHero) {

		pGameLevel.mBasePositions.add(new float[] { pX, pY });

		mCoinSprite = new Sprite(pX, pY, ResourceManager.gameCoinDynamicTR,
				ResourceManager.getActivity().getVertexBufferObjectManager()) {
			@Override
			protected void onManagedUpdate(float pSecondsElapsed) {
				// TODO Auto-generated method stub
				super.onManagedUpdate(pSecondsElapsed);
				if (mHero.mHero.collidesWith(this)) {
					this.setVisible(false);
					this.setIgnoreUpdate(true);
					GameLevel.addPointsToScore(100);
					pGameLevel.showQuestionPlayer();
					
					
					
					
				}
			}
		};
		pGameLevel.attachChild(mCoinSprite);



	}
}