package com.traversoft.hff.HFFWorld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.traversoft.hff.GameObjects.Fishy;
import com.traversoft.hff.GameObjects.ScrollingBackgroundHandler;

public class HFFWorld {

    public enum GameState {

        READY, RUNNING, GAMEOVER, HIGHSCORE, CHOOSEFISH
    }

    private Fishy _fishy;
    private ScrollingBackgroundHandler _scroller;
	private Rectangle _ground;
	private int _score = 0;
    private GameState _currentState;
    private int _midPointY;
    private Preferences _prefs;
    private boolean _isNewHighScore;

    public HFFWorld (int midPointY) {

        _currentState = GameState.READY;
    	_fishy = new Fishy(33, midPointY - 150, 22, 17);
    	_scroller = new ScrollingBackgroundHandler(this, midPointY + 55);
		_ground = new Rectangle(0, midPointY + 55, 136, 11);
        _midPointY = midPointY;
        _prefs = Gdx.app.getPreferences("HFF");
        _isNewHighScore = false;
	}

    public void update (float delta) {

        switch (_currentState) {
            case READY:
                updateReady(delta);
                break;
            case GAMEOVER:
                break;
            case RUNNING:
            default:
                updateRunning(delta);
                break;

        }
    }

    public void updateReady (float delta) {

        _isNewHighScore = false;
    }

	public void updateRunning(float delta) {

		// Delta cap in case games takes too long to update, our collision detection doesn't break
		if (delta > .15f) {

			delta = .15f;
		}

		_fishy.update(delta);
		_scroller.update(delta);

		if (_scroller.collides(_fishy) && _fishy.isAlive()) {

			_scroller.stop();
			_fishy.die();
        }

		if (Intersector.overlaps(_fishy.getBoundingCircle(), _ground)) {

            _scroller.stop();
            _fishy.die();
            _fishy.decelerate();
            _currentState = GameState.GAMEOVER;
        }

        if (_fishy.isFinishedDying()) {

            _currentState = GameState.GAMEOVER;
        }
	}

	public Fishy getFishy() {
        return _fishy;
    }
	
	public ScrollingBackgroundHandler getScrollingHandler() {
		return _scroller;
	}

	public int getScore() {

		return _score;
	}

	public void addScore(int increment) {

		_score += increment;
	}

    public void setHighScore(int score) {

        Integer highScore = _prefs.getInteger("highScore", 0);
        if (highScore < score) {

            _prefs.putInteger("highScore", score);
            _isNewHighScore = true;
            _prefs.flush();
        }
    }

    public boolean isNewHighScore () {
         return _isNewHighScore;
    }

    public int getHighScore() {
        return _prefs.getInteger("highScore", 0);
    }

    public boolean isReady() {

        return _currentState == GameState.READY;
    }

    public boolean isChoosingFish () {

        return _currentState == GameState.READY.CHOOSEFISH;
    }

    public boolean isGameOver() {

        return _currentState == GameState.GAMEOVER;
    }

    public boolean isHighScore() {

        return _currentState == GameState.HIGHSCORE;
    }

    public boolean isRunning () {

        return _currentState == GameState.RUNNING;
    }

    public void start () {

        _currentState = GameState.RUNNING;
    }

    public void restart () {
        _currentState = GameState.READY;
        _score = 0;
        _fishy.onRestart(33, _midPointY - 150);
        _scroller.onRestart();
        _currentState = GameState.READY;
    }
}
