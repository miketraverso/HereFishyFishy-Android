package com.traversoft.hff.utils;

import com.badlogic.gdx.InputProcessor;
import com.traversoft.hff.GameObjects.Button;
import com.traversoft.hff.GameObjects.Fishy;
import com.traversoft.hff.HFFCharacterScreen.CharacterSelectScreen;
import com.traversoft.hff.HFFCharacterScreen.HFFCharacterSelectWorld;
import com.traversoft.hff.HFFWorld.HFFWorld;
import com.traversoft.hff.Screens.GameScreen;

import java.util.ArrayList;
import java.util.List;

public class InputHandler implements InputProcessor
{
	private Fishy _fishy;
	private HFFWorld _world;
	private float _scaleFactorX;
	private float _scaleFactorY;
	private List<Button> buttons;
	private static Button fishSelectButton;

	public InputHandler(HFFWorld world,
						float scaleFactorX,
						float scaleFactorY) {
		_world = world;
		_fishy = world.getFishy();
		_scaleFactorX = scaleFactorX;
		_scaleFactorY = scaleFactorY;
	}

	public static List<Button> initButtons(int midPointY) {

		List<Button> buttons = new ArrayList<Button>();
		fishSelectButton = new Button(136 / 2 - 20,
				midPointY + 40,
				40,
				30,
				AssetLoader.buttonUpSprite,
				AssetLoader.buttonDownSprite);

		buttons.add(fishSelectButton);

		return buttons;
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

			screenX = scaleX(screenX);
			screenY = scaleY(screenY);

			if (!fishSelectButton.isTouchDown(screenX, screenY)) {

				_world.start();
			}
		}

		_fishy.onClick();

		if (_world.isGameOver()) {

			_world.restart();
		}

		return true;
	}
	
	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {

		screenX = scaleX(screenX);
		screenY = scaleY(screenY);

		if (fishSelectButton.isTouchUp(screenX, screenY)) {

			_world.getGame().setScreen(new CharacterSelectScreen(_world.getGame()));
			return true;
		}

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

	private int scaleX(int screenX) {
		return (int) (screenX / _scaleFactorX);
	}

	private int scaleY(int screenY) {
		return (int) (screenY / _scaleFactorY);
	}

}