package com.blackmoon.object;

import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.extension.physics.box2d.FixedStepPhysicsWorld;
import org.andengine.extension.physics.box2d.PhysicsConnector;
import org.andengine.extension.physics.box2d.PhysicsFactory;
import org.andengine.extension.physics.box2d.PhysicsWorld;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.blackmoon.runtime.GameLevels.GameLevel;
import com.blackmoon.runtime.Managers.ResourceManager;
import com.blackmoon.runtime.Managers.SFXManager;

public class Bullet extends AnimatedSprite {
	private float xmin;
	private float xmax;
	PhysicsConnector mPhysicConnector;
	
	public Vector2 position;
	private Body body;
	final long[] BULLET_ANIMATE = new long[] { 20, 20, 20, 20, 20 };
	private GameLevel mScene;
	FixedStepPhysicsWorld mPhysicsWorld;

	public Bullet(float pX, float pY, FixedStepPhysicsWorld physicsWorld, final GameLevel pGameScene){
		super(pX, pY, ResourceManager.bullet_region, ResourceManager.getEngine().getVertexBufferObjectManager());
		position = new Vector2(pX, pY);
		mPhysicsWorld = physicsWorld;
		xmin = pX - 2000;
		xmax = pX + 2000;
		createPhysics(physicsWorld);
		animation();
		mScene = pGameScene;
	}	
	

	public void animation() {
		animate(BULLET_ANIMATE, 0, 4, true);

	}

	public void shoot(Vector2 velocity) {
		getBody().setLinearVelocity(velocity);
	}

	public void onDie() {
		mScene.detachChild(this);

		// this.dispose();
	}

	private void createPhysics(PhysicsWorld physicsWorld) {
		// TODO Auto-generated method stub
		setBody(PhysicsFactory.createBoxBody(physicsWorld, this,
				BodyType.KinematicBody,
				PhysicsFactory.createFixtureDef(0, 0, 0)));

		getBody().setUserData("bullet");
		getBody().setFixedRotation(true);
		mPhysicConnector = new PhysicsConnector(this,
				getBody(), true, false) {
			@Override
			public void onUpdate(float pSencondsElapsed) {
				super.onUpdate(pSencondsElapsed);
				checkDirection();
			}
		};
		physicsWorld.registerPhysicsConnector(mPhysicConnector);
	}

	private void checkDirection() {
		if (getX() > xmax) {
			this.destroy();
		}
		if (getX() < xmin) {
			this.destroy();
		}
	}
	
	public void destroy() {
		mPhysicsWorld.unregisterPhysicsConnector(mPhysicConnector);
		mPhysicsWorld.destroyBody(body);
		this.detachSelf();
		this.dispose();

		body = null;
	}
	
	public Body getBody() {
		return body;
	}

	public void setBody(Body body) {
		this.body = body;
	}

	@Override
	protected void onManagedUpdate(float pSecondsElapsed) {
		super.onManagedUpdate(pSecondsElapsed);
		if (this.getX() > ResourceManager.getEngine().getCamera()
				.getXMax()
				|| this.getX() < ResourceManager.getEngine().getCamera().getXMin()
				|| this.getY() < 0
				|| this.getY() > ResourceManager.getEngine().getCamera().getHeight()) {
			GameLevel.listPlayerBullet.remove(this);
		}
	}
}
