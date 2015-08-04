package com.traversoft.hff;

import com.badlogic.gdx.Game;
import com.traversoft.hff.Screens.GameScreen;
import com.traversoft.hff.utils.AssetLoader;

public class HereFishyFishyMain extends Game {

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
}

//		extends ApplicationAdapter {
//	SpriteBatch batch;
//	Texture img;
//
//	@Override
//	public void create () {
//		batch = new SpriteBatch();
//		img = new Texture("badlogic.jpg");
//	}
//
//	@Override
//	public void render () {
//		Gdx.gl.glClearColor(1, 0, 0, 1);
//		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//		batch.begin();
//		batch.draw(img, 0, 0);
//		batch.end();
//	}
//}
