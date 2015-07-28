package com.traversoft.hff.GameObjects;


import com.badlogic.gdx.math.Intersector;

import java.util.logging.Logger;

public class SeaFloor extends ScrollingBackground {

	public SeaFloor(float x, float y, int width, int height, float scrollSpeed, float groundLevel) {
		super(x, y, width, height, scrollSpeed, groundLevel);
	}
	
	public boolean collides (Fishy fishy) {

		if (_groundLevel < fishy.getY() + fishy.getHeight()) {
			System.out.println();
			System.out.println("Ground Level: " + _groundLevel);
			System.out.println("Fishy: " + fishy.getY() + fishy.getHeight() + " y: " + fishy.getY() + " height:" + fishy.getHeight());
			System.out.println();
			return true;
		}

        return false;
	}
}
