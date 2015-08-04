package com.traversoft.hff.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.traversoft.hff.GameObjects.Button;
import com.traversoft.hff.GameObjects.Fishy;
import com.traversoft.hff.HFFCharacterScreen.HFFCharacterSelectWorld;
import com.traversoft.hff.HFFWorld.HFFWorld;
import com.traversoft.hff.Screens.GameScreen;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by michaelrtraverso on 8/4/15.
 */
public class CharacterSelectInputHandler implements InputProcessor {

    private Fishy _fishy;
    private HFFCharacterSelectWorld _world;
    private float _scaleFactorX;
    private float _scaleFactorY;
    private List<Button> menuButtons;
    private static Button rightButton, leftButton, okButton;

    public CharacterSelectInputHandler(HFFCharacterSelectWorld world,
                                       float scaleFactorX,
                                       float scaleFactorY) {
        _world = world;
        _fishy = world.getFishy();
        _scaleFactorX = scaleFactorX;
        _scaleFactorY = scaleFactorY;
    }

    public static List<Button> initButtons(int midPointY) {

        List<Button> buttons = new ArrayList<Button>();
        rightButton = new Button(136 / 2 + 30,
                midPointY + 50,
                15,
                15,
                AssetLoader.rightButtonUpSprite,
                AssetLoader.rightButtonDownSprite);

        leftButton = new Button(136 / 2 - 45,
                midPointY + 50,
                15,
                15,
                AssetLoader.leftButtonUpSprite,
                AssetLoader.leftButtonDownSprite);

        okButton = new Button(136 / 2 - 15,
                midPointY + 50,
                30,
                15,
                AssetLoader.okButtonUpSprite,
                AssetLoader.okButtonDownSprite);

        buttons.add(rightButton);
        buttons.add(leftButton);
        buttons.add(okButton);

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

        System.out.println(pointer + " " + screenX + " " + screenY);

        leftButton.isTouchDown(screenX, screenY);
        rightButton.isTouchDown(screenX, screenY);
        okButton.isTouchDown(screenX, screenY);

        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {

        screenX = scaleX(screenX);
        screenY = scaleY(screenY);

        HFFCharacterSelectWorld.GameCharacter selectedFish = HFFCharacterSelectWorld.getSelectedFish();

        if (leftButton.isTouchUp(screenX, screenY)) {

            if (selectedFish.ordinal() <= 0) {

                selectedFish = HFFCharacterSelectWorld.GameCharacter.GOLD_FISH;
            }
            else {

                selectedFish = HFFCharacterSelectWorld.getFishAtIndex(selectedFish.ordinal() - 1);
            }

            HFFCharacterSelectWorld.setSelectedFish(selectedFish);
            return true;
        }
        else if (rightButton.isTouchUp(screenX, screenY)) {

            if (selectedFish.ordinal() >= HFFCharacterSelectWorld.getMaxPayableFishCount()) {

                selectedFish = HFFCharacterSelectWorld.GameCharacter.FISHY;
            }
            else {

                selectedFish = HFFCharacterSelectWorld.getFishAtIndex(selectedFish.ordinal() + 1);
            }

            HFFCharacterSelectWorld.setSelectedFish(selectedFish);
            return true;
        }
        else if (okButton.isTouchUp(screenX, screenY)) {

            _world.getGame().setScreen(new GameScreen(_world.getGame()));
            return true;
        }

        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    private int scaleX(int screenX) {
        return (int) (screenX / _scaleFactorX);
    }

    private int scaleY(int screenY) {
        return (int) (screenY / _scaleFactorY);
    }

    public List<Button> getMenuButtons() {
        return menuButtons;
    }
}