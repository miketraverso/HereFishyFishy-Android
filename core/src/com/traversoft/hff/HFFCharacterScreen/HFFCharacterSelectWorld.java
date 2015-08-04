package com.traversoft.hff.HFFCharacterScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.math.Rectangle;
import com.traversoft.hff.GameObjects.Fishy;
import com.traversoft.hff.GameObjects.SeaFloor;
import com.traversoft.hff.HereFishyFishyMain;
import com.traversoft.hff.Screens.GameScreen;
import com.traversoft.hff.utils.AssetLoader;

import static com.traversoft.hff.HFFCharacterScreen.HFFCharacterSelectWorld.GameCharacter.FISHY;

/**
 * Created by michaelrtraverso on 8/4/15.
 */
public class HFFCharacterSelectWorld {

    public enum GameState {

        RUNNING, CHARACTER_SELECTED
    }

    public enum GameCharacter {

        FISHY,
        SWEDISH_FISH,
        GIRL_FISH,
        CLOWN_FISH,
        STINKY_FISH,
        WOOD_FISH,
        SUPER_FISH,
        CAT_FISH,
        OLD_FISH,
        GOLD_FISH,
    }

    private static Preferences _prefs = Gdx.app.getPreferences("HFF");
    public static GameCharacter _characters[] = GameCharacter.values();

    private Fishy _fishy;
    private GameState _currentState;
    private int _midPointY;
    private HereFishyFishyMain _game;

    public HFFCharacterSelectWorld (int midPointY, HereFishyFishyMain game) {

        _game = game;
        _currentState = GameState.RUNNING;
        _fishy = new Fishy(33, midPointY - 150, 22, 17);
        _midPointY = midPointY;
    }

    public void update (float delta) {

        switch (_currentState) {
            case CHARACTER_SELECTED:
                updateCharacterSelected(delta);
                break;
            case RUNNING:
            default:
                updateRunning(delta);
                break;
        }
    }

    public void updateCharacterSelected (float delta) {

        // Set character selected

        _game.setScreen(new GameScreen(_game));
    }

    public void updateRunning(float delta) {

        // Delta cap in case games takes too long to update, our collision detection doesn't break
        if (delta > .15f) {

            delta = .15f;
        }

        //_fishy.update(delta);

        if (_fishy.isFinishedDying()) {

            _currentState = GameState.CHARACTER_SELECTED;
        }
    }

    public HereFishyFishyMain getGame() { return _game; }

    public Fishy getFishy() {
        return _fishy;
    }

    public int getMidPointY() {
        return _midPointY;
    }

    public static void setSelectedFish(GameCharacter fishSelected) {

        _prefs.putInteger("fish", fishSelected.ordinal());
        _prefs.flush();
        AssetLoader.updateSelectedFish();
    }

    public static int getMaxPayableFishCount() {

        return _characters.length;
    }

    public static GameCharacter getFishAtIndex(int fishOrdinal) {

        if (fishOrdinal>=_characters.length)
            return FISHY;
        else
            return _characters[fishOrdinal];
    }

    public static GameCharacter getSelectedFish() {

        return _characters[_prefs.getInteger("fish", FISHY.ordinal())];
    }

    public boolean isRunning() {

        return _currentState == GameState.RUNNING;
    }

    public boolean isCharacterSelected () {

        return _currentState == GameState.CHARACTER_SELECTED;
    }

    public void start () {

        _currentState = GameState.RUNNING;
    }
}