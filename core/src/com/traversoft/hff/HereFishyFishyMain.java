package com.traversoft.hff;

import com.badlogic.gdx.Game;
import com.traversoft.hff.Screens.GameScreen;
import com.traversoft.hff.utils.ActionResolver;
import com.traversoft.hff.utils.AssetLoader;

public class HereFishyFishyMain extends Game {

	ActionResolver _actionResolver;

	public HereFishyFishyMain(ActionResolver actionResolver) {

		_actionResolver = actionResolver;
	}

	@Override
	public void create() {

		System.out.println("HFFGame created!");
		AssetLoader.load();
		setScreen(new GameScreen(this));
	}

	@Override
	public void dispose()
	{
		super.dispose();
		AssetLoader.dispose();
	}

//	public void x () {
//
//		_actionResolver.showAlertBox("!AlertBox title", "!AlertBox message", "Button text");
//	}

	public void launchIntent (String msg, String imgUri) {

		_actionResolver.launchIntent(msg, imgUri);
	}
}