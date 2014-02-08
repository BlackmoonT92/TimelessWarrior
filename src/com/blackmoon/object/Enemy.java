package com.blackmoon.object;

import java.util.Random;

import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.extension.physics.box2d.FixedStepPhysicsWorld;
import org.andengine.extension.physics.box2d.PhysicsConnector;
import org.andengine.extension.physics.box2d.PhysicsFactory;
import org.andengine.extension.physics.box2d.PhysicsWorld;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import android.util.Log;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.blackmoon.runtime.GameLevels.GameLevel;
import com.blackmoon.runtime.Managers.ResourceManager;

public class Enemy extends AnimatedSprite {
	private boolean isShootavaiable = true;
	private Body body;
	private float xmin;
	private float xmax;
	private int direction = 1; // right direction
	final long[] ENEMY_ANIMATE = new long[] { 20, 20, 20 };
	private float x;
	private GameLevel mGameLevel;
	private boolean isDied = false;
	private int timeShoot = 0;
	private int timeToShoot = 0;
	private int numberOfBullet = 0;
	private FixedStepPhysicsWorld mPhysicsWorld;
	PhysicsConnector mPhysicsConnector;

	public Enemy(float pX, float pY, FixedStepPhysicsWorld physicsWorld,
			GameLevel pGameScene, ITiledTextureRegion region) {
		// ITextureRegion region = ResourcesManager.getInstance().enemy1_region;
		super(pX, pY, region, ResourceManager.getEngine()
				.getVertexBufferObjectManager());
		mPhysicsWorld = physicsWorld;
		createPhysics(physicsWorld);
		// set x boundary for enemy
		xmin = pX - 200;
		xmax = pX + 200;
		x = pX;
		mGameLevel = pGameScene;
		setTimeToShoot();
		Log.w("maxmin", String.valueOf(xmin));
		Log.w("maxmin", String.valueOf(xmax));
		move(direction);
		animation();

	}

	private void setTimeToShoot() {
		timeToShoot = 100;
		numberOfBullet = 1;

	}

	private void shoot(FixedStepPhysicsWorld physicsWorld) {
		if (!isShootavaiable) {
			return;
		}
		for (int i = 0; i < numberOfBullet; i++) {
			Bullet bullet = new Bullet(getX(), getY(), physicsWorld, mGameLevel);
			bullet.setCullingEnabled(true);
			mGameLevel.attachChild(bullet);
			Random r = new Random();
			int radian = r.nextInt(2) - 1;
			bullet.shoot(new Vector2(15 * direction, radian));
			GameLevel.listEnemyBullet.add(bullet);
		}

	}

	private void move(int direction) {
		Vector2 velocity = new Vector2(10 * direction, 0);
		body.setLinearVelocity(velocity);

	}

	private void animation() {
		if (direction == 1) {

			animate(ENEMY_ANIMATE, 6, 8, true);
		} else {
			animate(ENEMY_ANIMATE, 3, 5, true);
		}
	}

	private void checkDirection() {
		if (getX() > xmax) {
			direction = -1;
			move(direction);
			animation();
		}
		if (getX() < xmin) {
			direction = 1;
			move(direction);
			animation();
		}
	}

	public void onDie() {

		isShootavaiable = false;
		isDied = true;
		this.destroy();
	}

	private void createPhysics(final FixedStepPhysicsWorld physicsWorld) {
		// TODO Auto-generated method stub
		body = PhysicsFactory.createBoxBody(physicsWorld, this,
				BodyType.KinematicBody,
				PhysicsFactory.createFixtureDef(0, 0, 0));

		body.setUserData("enemy1");
		body.setFixedRotation(true);
		mPhysicsConnector = new PhysicsConnector(this, body, true, false) {

			@Override
			public void onUpdate(float pSencondsElapsed) {
				super.onUpdate(pSencondsElapsed);
				checkDirection();
				timeShoot++;
				if (timeShoot > timeToShoot) {
					shoot(physicsWorld);
					timeShoot = 0;
				}

				for (Bullet mBullet : GameLevel.listPlayerBullet) {
					if (mBullet.collidesWith(Enemy.this)) {
						Log.w("collision", "start");

						// GameScene.listPlayerBullet.remove(mBullet);
						if (!isDied) {
							body.setType(BodyType.DynamicBody);
							onDie();
						}
						mBullet.onDie();
					}
				}

			}

		};
		physicsWorld.registerPhysicsConnector(mPhysicsConnector);

	}

	public void destroy() {
		mPhysicsWorld.unregisterPhysicsConnector(mPhysicsConnector);
		mPhysicsWorld.destroyBody(body);
		this.detachSelf();
		this.dispose();
		body = null;
		
	}

}
