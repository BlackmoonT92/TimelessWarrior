package com.blackmoon.object;

import java.util.ArrayList;
import java.util.List;

import org.andengine.engine.Engine;
import org.andengine.entity.IEntity;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.extension.physics.box2d.FixedStepPhysicsWorld;
import org.andengine.extension.physics.box2d.PhysicsConnector;
import org.andengine.extension.physics.box2d.PhysicsFactory;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.blackmoon.runtime.TimelessWarriorsSmoothCamera;
import com.blackmoon.runtime.GameLevels.GameLevel;
import com.blackmoon.runtime.Layers.LevelWonLayer;
import com.blackmoon.runtime.Managers.ResourceManager;
import com.blackmoon.runtime.Managers.SFXManager;
import com.blackmoon.runtime.Managers.SceneManager;

import android.content.Context;
import android.util.Log;

public class Hero implements InterfaceSprite {
	private int STATUS_PALYER = StatusPlayer.STAND_RIGHT;
	private Body body;
	FixedStepPhysicsWorld mPhysicsWorld;

	// vị trí ban đầu cho nhân vật
	private float positionX = 0;
	private float positionY = 0;

	protected List<Sprite> m_KillList;
	protected int m_CurrentSkill = 0;

	// texture hero
	protected AnimatedSprite mHero;
	public boolean isHeroDied = false;
	private int footContacts = 0;
	boolean startRunning = true;
	GameLevel mGameLevel;
	// =====================================================
	// CONTRUCTOR
	// =====================================================
	public Hero(int _HP, float posX, float posY, int level,
			FixedStepPhysicsWorld mPhysicsWorld,GameLevel pGameLevel) {
		mGameLevel = pGameLevel;
		this.positionX = posX;
		this.positionY = posY;
		this.STATUS_PALYER = StatusPlayer.STAND_RIGHT;
		this.m_KillList = new ArrayList<Sprite>();
		this.mPhysicsWorld = mPhysicsWorld;

	}

	private void createPhysics(final TimelessWarriorsSmoothCamera mCamera,
			FixedStepPhysicsWorld mPhysicsWorld) {
		body = PhysicsFactory.createBoxBody(mPhysicsWorld, mHero,
				BodyType.DynamicBody, PhysicsFactory.createFixtureDef(0, 0, 0));

		body.setUserData("hero");

		body.setFixedRotation(true);

		mPhysicsWorld.registerPhysicsConnector(new PhysicsConnector(mHero,
				body, true, false) {
			@Override
			public void onUpdate(float pSecondsElapsed) {
				super.onUpdate(pSecondsElapsed);
				mCamera.onUpdate(0.1f);
				if (startRunning) {
					
					body.setLinearVelocity(new Vector2(15, body
							.getLinearVelocity().y));
				}
				
				/*for (Bullet mBullet : GameLevel.listEnemyBullet) {
					if (mBullet.collidesWith(mHero)) {
						Log.w("collision", "start");
						mGameLevel.isLevelFail = true;
						mGameLevel.onLevelFailed();
						mBullet.onDie();
					}
				}*/
				
				
			}
		});
	}

	@Override
	public void onLoadResources(Engine mEngine, Context mContext) {

	}

	@Override
	public void onLoadScene(Scene mScene) {

		// chọn hành động cho hero
		showPlayerStatus();
		createPhysics(GameLevel.mCamera, mPhysicsWorld);
		mScene.attachChild(mHero);

	}

	public void jump() {
		if (footContacts < 1) {
			return;
		}
		SFXManager.playJump(1f, 1f);
		body.setLinearVelocity(new Vector2(body.getLinearVelocity().x, 30));
	}

	public void increaseFootContacts() {
		footContacts++;
	}

	public void decreaseFootContacts() {
		footContacts--;
	}

	public void setPositionX(float positionX) {
		Hero.this.positionX = positionX;
	}

	public float getPositionX() {
		return Hero.this.positionX;
	}

	public void setPositionY(float positionY) {
		Hero.this.positionY = positionY;
	}

	public float getPositionY() {
		return Hero.this.positionY;
	}

	public void setPositionXY(float positionX, float positionY) {
		Hero.this.positionX = positionX;
		Hero.this.positionY = positionY;
	}

	// =======================================|| setStatusPlayer
	// ||================================

	public void setStatusPlayer(int statusplayer) {
		Hero.this.STATUS_PALYER = statusplayer;

		showPlayerStatus();
	}

	// =======================================|| getStatusPlayer
	// ||================================

	public int getStatusPlayer() {
		return Hero.this.STATUS_PALYER;
	}

	// show action and direc
	private void showPlayerStatus() {
			mHero = new AnimatedSprite(this.positionX, this.positionY,
					ResourceManager.Hero_Move, ResourceManager.getActivity()
							.getVertexBufferObjectManager()){
				
			};
			mHero.setFlippedHorizontal(true);
			mHero.animate(100);	

		

	}

	public void moveX(float moveX) {
		this.positionX = moveX;
		moveHero();
	}

	public void moveY(float moveY) {
		this.positionY = moveY;
		moveHero();
	}

	public void moveRelativeX(float moveRelativeX) {
		this.positionX += moveRelativeX;
		moveHero();
	}

	public void moveRelativeY(float moveRelativeY) {
		this.positionY += moveRelativeY;
		moveHero();
	}

	public void moveRelativeXY(float moveRelativeX, float moveRelativeY) {
		this.positionX += moveRelativeX;
		this.positionY += moveRelativeY;
		moveHero();
	}

	/**
	 * Move player
	 */
	private void moveHero() {
		mHero.setPosition(this.positionX, this.positionY);
	}

	public AnimatedSprite getAnimatedSprite() {
		return this.mHero;
	}

	public void setRunning(boolean isRunning) {
		startRunning = isRunning;
	}

}
