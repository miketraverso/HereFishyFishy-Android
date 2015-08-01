package com.traversoft.hff.GameObjects;


import com.badlogic.gdx.math.Intersector;

import java.util.logging.Logger;

public class SeaFloor extends ScrollingBackground {

	public SeaFloor(float x, float y, int width, int height, float scrollSpeed, float groundLevel) {
		super(x, y, width, height, scrollSpeed, groundLevel);
	}

    public void onRestart(float x, float scrollSpeed) {
        _position.x = x;
        _velocity.x = scrollSpeed;
    }

    public boolean collides (Fishy fishy) {

		if (_groundLevel < fishy.getY() + fishy.getHeight()) {

            return true;
		}

        return false;
	}
}
