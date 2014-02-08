package com.blackmoon.runtime.GameLevels;

public abstract class LoadingRunnable implements Runnable{
	private final String mLoadingText;
	private final ManagedGameScene mGameScene;
	
	public abstract void onLoad();
	
	public LoadingRunnable(String pLoadingText, ManagedGameScene pGameScene)
	{
		mLoadingText = pLoadingText;
		mGameScene = pGameScene;
	}
	
	
	@Override
	public void run() {
		// Avoid using String.isEmpty() because at least a few phones do not support it.
				// An alternative is to use String.length()==0
				if(mLoadingText.trim().length()!=0)
					if(mGameScene!=null)
						if(mGameScene.mLoadingText!=null)
							if(mLoadingText!=null)
								mGameScene.mLoadingText.setText(mGameScene.mLoadingText.getText() + "\n" + mLoadingText);
				onLoad();		
	}
	
}
