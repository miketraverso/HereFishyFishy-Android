package com.traversoft.hff.GameObjects;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

public class Fishy {
	private Vector2 _position;
	private Vector2 _velocity;
	private Vector2 _acceleration;
	private Circle _boundingCircle;
	private float _rotation;
	private int _width, _height;

	public Fishy (float x, float y, int width, int height)
	{
        _width = width;
        _height = height;
        _position = new Vector2(x, y);
        _velocity = new Vector2(0, 0);
        _acceleration = new Vector2(0, 460);
        setBoundingCircle(new Circle());
	}
	
    public void update(float delta) {

        _velocity.add(_acceleration.cpy().scl(delta));

        if (_velocity.y > 200) {
            _velocity.y = 200;
        }

        _position.add(_velocity.cpy().scl(delta));
        _boundingCircle.set(_position.x + 9, _position.y + 6, 6.5f);

//        // Rotate counter-clockwise
//        if (_velocity.y < 0) {
//            _rotation -= 600 * delta;
//
//            if (_rotation < -20) {
//                _rotation = -20;
//            }
//        }
//
//        // Rotate clockwise
//        if (isFalling()) {
//
//            _rotation += 480 * delta;
//
//            if (_rotation > 90) {
//                _rotation = 90;
//            }
//
//        }
    }

    public void onClick() {
        _velocity.y = -140;
    }

    public float getX() {
        return _position.x;
    }

    public float getY() {
        return _position.y;
    }

    public float getWidth() {
        return _width;
    }

    public float getHeight() {
        return _height;
    }

    public float getRotation() {
        return _rotation;
    }

    public boolean isFalling() {
        return _velocity.y > 110;
    }

    public boolean shouldntFlap() {
        return _velocity.y > 70;
    }

	public Circle getBoundingCircle() {
		return _boundingCircle;
	}

	public void setBoundingCircle(Circle _boundingCircle) {
		this._boundingCircle = _boundingCircle;
	}
}
