package com.traversoft.hff.utils;

import com.badlogic.gdx.InputProcessor;
import com.traversoft.hff.GameObjects.Button;
import com.traversoft.hff.GameObjects.Fishy;
import com.traversoft.hff.HFFCharacterScreen.CharacterSelectScreen;
import com.traversoft.hff.HFFWorld.HFFWorld;
import java.util.ArrayList;
import java.util.List;

public class InputHandler implements InputProcessor {

	private Fishy _fishy;
	private HFFWorld _world;
	private float _scaleFactorX;
	private float _scaleFactorY;
	private static Button btnTitleSelectFish, btnPlayAgain, btnEndGameSelectFish, btnShareScore;

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
		btnTitleSelectFish = new Button(136 / 2 - 20,
				midPointY + 40,
				40,
				30,
				AssetLoader.buttonUpSprite,
				AssetLoader.buttonDownSprite);

		buttons.add(btnTitleSelectFish);

		return buttons;
	}

	public static List<Button> initEndGameButtons(int midPointY) {

		List<Button> buttons = new ArrayList<Button>();
		btnEndGameSelectFish = new Button(8,
				midPointY - 20,
				54,
				30,
				AssetLoader.buttonUpSprite,
				AssetLoader.buttonDownSprite);

        btnShareScore = new Button(136 / 2 + 6,
				midPointY - 20,
				54,
				30,
				AssetLoader.buttonUpSprite,
				AssetLoader.buttonDownSprite);

        btnPlayAgain = new Button(136 / 2 - 50,
				midPointY + 20,
				100,
				30,
				AssetLoader.buttonUpSprite,
				AssetLoader.buttonDownSprite);

		buttons.add(btnPlayAgain);
		buttons.add(btnEndGameSelectFish);
		buttons.add(btnShareScore);

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

		screenX = scaleX(screenX);
		screenY = scaleY(screenY);

		if (_world.isReady()) {

			if (!btnTitleSelectFish.isTouchDown(screenX, screenY)) {

				_world.start();
			}
		}

		_fishy.onClick();

		if (_world.isGameOver()) {

            btnEndGameSelectFish.isTouchDown(screenX, screenY);
            btnPlayAgain.isTouchDown(screenX, screenY);
            btnShareScore.isTouchDown(screenX,screenY);
		}

		return true;
	}
	
	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {

		screenX = scaleX(screenX);
		screenY = scaleY(screenY);

		if (btnTitleSelectFish.isTouchUp(screenX, screenY)) {

			_world.getGame().setScreen(new CharacterSelectScreen(_world.getGame()));
			return true;
		}

   		if (_world.isGameOver()) {

            if (btnEndGameSelectFish.isTouchUp(screenX, screenY)) {

                _world.getGame().setScreen(new CharacterSelectScreen(_world.getGame()));
            }
            else if (btnShareScore.isTouchUp(screenX, screenY)) {

                ScreenshotFactory.saveScreenshot();
                String path = ScreenshotFactory.getScreenshotPath();
                _world.getGame().launchIntent("WooHOO! I scored " + _world.getScore() + " points in Here Fishy Fishy!", path);
            }
            else if (btnPlayAgain.isTouchUp(screenX, screenY)) {

                _world.restart(HFFWorld.GameState.RUNNING);
            }
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