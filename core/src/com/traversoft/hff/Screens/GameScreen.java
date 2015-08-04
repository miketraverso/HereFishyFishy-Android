package com.traversoft.hff.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.traversoft.hff.HFFWorld.HFFRenderer;
import com.traversoft.hff.HFFWorld.HFFWorld;
import com.traversoft.hff.HereFishyFishyMain;
import com.traversoft.hff.utils.CharacterSelectInputHandler;
import com.traversoft.hff.utils.InputHandler;

public class GameScreen implements Screen {

	private HFFWorld _world;
	private HFFRenderer _renderer;
	private float _runTime = 0.0f;
	private HereFishyFishyMain _game;
    private InputHandler _inputHandler;

	public GameScreen(HereFishyFishyMain game) {
		System.out.println("GameScreen Attached");

        _game = game;
        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();      
        float gameWidth = 136;
        float gameHeight = screenHeight / (screenWidth / gameWidth);
        
        int midPointY = (int) (gameHeight / 2);

		_world = new HFFWorld(midPointY, game);
        _inputHandler = new InputHandler(_world, screenWidth / gameWidth, screenHeight / gameHeight);
        _renderer = new HFFRenderer(_world, (int)	gameHeight, midPointY);
	}
	
	@Override
	public void render(float delta) {

		_runTime += delta;
		_world.update(delta);
		_renderer.render(delta);
		
        Gdx.input.setInputProcessor(_inputHandler);
	}

    @Override
    public void resize(int width, int height) {
        System.out.println("GameScreen - resizing");
    }

    @Override
    public void show() {
        System.out.println("GameScreen - show called");
    }

    @Override
    public void hide() {
        System.out.println("GameScreen - hide called");     
    }

    @Override
    public void pause() {
        System.out.println("GameScreen - pause called");        
    }

    @Override
    public void resume() {
        System.out.println("GameScreen - resume called");       
    }

    @Override
    public void dispose() {
        // Leave blank
    }
}
