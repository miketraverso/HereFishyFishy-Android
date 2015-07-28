package com.traversoft.hff.GameObjects;

import com.badlogic.gdx.math.Vector2;

public class ScrollingBackground {

	protected Vector2 _position;
	protected Vector2 _velocity;
	
	protected int _width, _height;
	protected boolean _isScrolledLeft;
	protected float _groundLevel;

	public ScrollingBackground (float x, float y, int width, int height, float scrollSpeed, float groundLevel) {
		_position = new Vector2(x, y);
		_velocity = new Vector2(scrollSpeed, 0); // Not moving in the y-axis
		_height = height;
		_width = width;
		_groundLevel = groundLevel;
		_isScrolledLeft = false;
	}
	
	public void update(float delta) {
		_position.add(_velocity.cpy().scl(delta));
		if (_position.x + _width < 0) {
			_isScrolledLeft = true;
		}
	}
	
	public void reset (float newXPosition) {
		_position.x = newXPosition;
		_isScrolledLeft = false;
	}

	public void stop() {
		_velocity.x = 0;
	}
	
	public float getTailX() {
		return Math.round(_position.x) + Math.round(_width) - .55f;
	}

	public float getX() {
		return _position.x;
	}

	public float getY() {
		return _position.y;
	}

	public int getWidth() {
		return _width;
	}

	public int getHeight() {
		return _height;
	}

	public boolean isScrolledLeft() {
		return _isScrolledLeft;
	}
}
