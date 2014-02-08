package com.blackmoon.object;

import org.andengine.engine.Engine;
import org.andengine.entity.scene.Scene;

import android.content.Context;

public interface InterfaceSprite {
	public void onLoadResources(Engine mEngine, Context mContext);
	public void onLoadScene(Scene mScene);
}
