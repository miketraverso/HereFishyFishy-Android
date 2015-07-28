package com.traversoft.hff;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.traversoft.hff.utils.AssetLoader;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.traversoft.hff.Screens.GameScreen;

public class HereFishyFishyMain extends Game {

	@Override
	public void create() {

		System.out.println("HFFGame created!");
		AssetLoader.load();
		setScreen(new GameScreen());
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
