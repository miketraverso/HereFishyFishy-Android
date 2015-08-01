package com.traversoft.hff.GameObjects;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

import java.util.Random;

public class Seaweed extends ScrollingBackground {

    public static final int VERTICAL_GAP = 47;
	private int kSPACE_BETWEEN_PIPES = 17;

	private Random _randomNumber;
    private Rectangle _barUp, _barDown;
    private boolean _isScored = false;

	public Seaweed(float x, float y, int width, int height, float scrollSpeed, float groundLevel) {

        super(x, y, width, height, scrollSpeed, groundLevel);
		_randomNumber = new Random();
        _barUp = new Rectangle();
        _barDown = new Rectangle();
	}

    public void onRestart(float x, float scrollSpeed) {

        _velocity.x = scrollSpeed;
        reset(x);
    }

    @Override
    public void update(float delta) {

        super.update(delta);
        _barUp.set(_position.x, _position.y, _width, _height);
        _barDown.set(_position.x,
                        _position.y + _height + VERTICAL_GAP,
                        _width,
                        _groundLevel + 66 - (_height + VERTICAL_GAP));
    }

	@Override
	public void reset(float newXPosition) {

        super.reset(newXPosition);
		_height = _randomNumber.nextInt(90) + kSPACE_BETWEEN_PIPES;
        _isScored = false;
    }
	
	public boolean collides (Fishy fishy) {

        if (_position.x < fishy.getX() + fishy.getWidth()) {

            return (Intersector.overlaps(fishy.getBoundingCircle(), _barUp) ||
            		Intersector.overlaps(fishy.getBoundingCircle(), _barDown));
        }

        return false;
	}

    public boolean isScored() {
        return _isScored;
    }

    public void setScored(boolean scored) {

        _isScored = scored;
    }
}
