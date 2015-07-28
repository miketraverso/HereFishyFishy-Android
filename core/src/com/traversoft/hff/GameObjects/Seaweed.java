package com.traversoft.hff.GameObjects;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

import java.util.Random;

public class Seaweed extends ScrollingBackground {

    public static final int VERTICAL_GAP = 47;
	private int kSPACE_BETWEEN_PIPES = 17;

	private Random _randomNumber;
    private Rectangle barUp, barDown;

	public Seaweed(float x, float y, int width, int height, float scrollSpeed, float groundLevel) {

        super(x, y, width, height, scrollSpeed, groundLevel);
		_randomNumber = new Random();
        barUp = new Rectangle();
        barDown = new Rectangle();
	}
	
	@Override
    public void update(float delta) {

        super.update(delta);
        barUp.set(_position.x, _position.y, _width, _height);
        barDown.set(_position.x,
                    _position.y + _height + VERTICAL_GAP,
                    _width,
                    _groundLevel + 66 - ( /*_position.y + */ _height + VERTICAL_GAP));
    }

	@Override
	public void reset(float newXPosition) {

        super.reset(newXPosition);
		_height = _randomNumber.nextInt(90) + kSPACE_BETWEEN_PIPES;	
	}
	
	public boolean collides (Fishy fishy) {

        if (_position.x < fishy.getX() + fishy.getWidth()) {

            System.out.println();
            System.out.println("Seaweed: " + _position.x);
            System.out.println("Fishy: " + fishy.getX() + fishy.getWidth() + " x: " + fishy.getX() + " width: " + fishy.getWidth());

            System.out.println(Intersector.overlaps(fishy.getBoundingCircle(), barUp));
            System.out.println(Intersector.overlaps(fishy.getBoundingCircle(), barDown));
            System.out.println();

            return (Intersector.overlaps(fishy.getBoundingCircle(), barUp) ||
            		Intersector.overlaps(fishy.getBoundingCircle(), barDown));
        }
        
        return false;
	}
}
