package com.traversoft.hff.utils;

import com.badlogic.gdx.InputProcessor;
import com.traversoft.hff.GameObjects.Fishy;
import com.traversoft.hff.HFFWorld.HFFWorld;

public class InputHandler implements InputProcessor
{
	private Fishy _fishy;
	private HFFWorld _world;

	public InputHandler(HFFWorld world) {

		_world = world;
		_fishy = world.getFishy();
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {

		if (_world.isReady()) {

			_world.start();
		}

		_fishy.onClick();

		if (_world.isGameOver()) {

			_world.restart();
		}

		return true;
	}
	
	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}
}