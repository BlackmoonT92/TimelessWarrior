package com.blackmoon.object;

import org.andengine.engine.Engine;
import org.andengine.engine.camera.hud.controls.AnalogOnScreenControl;
import org.andengine.engine.camera.hud.controls.BaseOnScreenControl;
import org.andengine.engine.camera.hud.controls.AnalogOnScreenControl.IAnalogOnScreenControlListener;
import org.andengine.entity.modifier.ScaleModifier;
import org.andengine.entity.modifier.SequenceEntityModifier;
import org.andengine.entity.scene.Scene;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import com.blackmoon.runtime.TimelessWarriorsSmoothCamera;
import com.blackmoon.runtime.GameLevels.GameLevel;
import com.blackmoon.runtime.Managers.ResourceManager;

import android.content.Context;
import android.opengl.GLES20;

public class MyAnalogOnScreenControl implements InterfaceSprite {
	public static ITextureRegion mOnScreenControlBaseTextureRegion;
	public static ITextureRegion mOnScreenControlKnobTextureRegion;
	public AnalogOnScreenControl analogOnScreenControl;
	Scene analogScene;

	

	@Override
	public void onLoadResources(Engine mEngine, Context mContext) {

		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
		BitmapTextureAtlas mOnScreenControlTexture = new BitmapTextureAtlas(
				ResourceManager.getActivity().getTextureManager(), 256, 128,
				TextureOptions.BILINEAR);
		mOnScreenControlBaseTextureRegion = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(mOnScreenControlTexture,
						ResourceManager.getActivity(),
						"onscreen_control_base.png", 0, 0);
		mOnScreenControlKnobTextureRegion = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(mOnScreenControlTexture,
						ResourceManager.getActivity(),
						"onscreen_control_knob.png", 128, 0);
		mOnScreenControlTexture.load();
	}

	@Override
	public void onLoadScene(Scene mScene) {
		showAnalog();
		mScene.setChildScene(analogScene);
	}

	private void showAnalog() {
		// add Analog
		analogScene = new Scene();
		analogOnScreenControl = new AnalogOnScreenControl(
				mOnScreenControlBaseTextureRegion.getWidth()/2, mOnScreenControlBaseTextureRegion.getHeight()/2,
				ResourceManager.getCamera(), mOnScreenControlBaseTextureRegion,
				mOnScreenControlKnobTextureRegion, 0.1f, 200,
				ResourceManager.getActivity().getVertexBufferObjectManager(),
				new IAnalogOnScreenControlListener() {
					@Override
					public void onControlChange(
							final BaseOnScreenControl pBaseOnScreenControl,
							final float pValueX, final float pValueY) {
						/*physicsHandler
								.setVelocity(pValueX * 100, pValueY * 100);*/
					}

					@Override
					public void onControlClick(
							org.andengine.engine.camera.hud.controls.AnalogOnScreenControl arg0) {
						// TODO Auto-generated method stub
						
					}

				
				});
		analogOnScreenControl.getControlBase().setBlendFunction(
				GLES20.GL_SRC_ALPHA, GLES20.GL_ONE_MINUS_SRC_ALPHA);
		analogOnScreenControl.getControlBase().setAlpha(0.5f);
		analogOnScreenControl.getControlBase().setScaleCenter(0, 128);
		analogOnScreenControl.getControlBase().setScale(1.25f);
		analogOnScreenControl.getControlKnob().setScale(1.25f);
		analogOnScreenControl.refreshControlKnobPosition();
	}

}
