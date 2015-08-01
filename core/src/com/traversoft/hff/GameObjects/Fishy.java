package com.traversoft.hff.GameObjects;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.traversoft.hff.utils.AssetLoader;

public class Fishy {
	private Vector2 _position;
	private Vector2 _velocity;
	private Vector2 _acceleration;
	private Circle _boundingCircle;
	private float _rotation;
	private int _width, _height;
    private boolean _isAlive;

	public Fishy (float x, float y, int width, int height)
	{
        _width = width;
        _height = height;
        _position = new Vector2(x, y);
        _velocity = new Vector2(0, 0);
        _acceleration = new Vector2(0, 460);
        _isAlive = true;
        setBoundingCircle(new Circle());
	}
	
    public void update(float delta) {

        if (_isAlive)
            _velocity.add(_acceleration.cpy().scl(delta));

        if (_isAlive == false) {

            if (_position.y < 0) {
                _velocity.y = 0;
            }
        }

        if (_velocity.y > 200) {
            _velocity.y = 200;
        }

        if (_position.y < -1) {
            _position.y = -1;
            _velocity.y = 0;
        }

        _position.add(_velocity.cpy().scl(delta));
        _boundingCircle.set(_position.x + 9, _position.y + 6, 6.5f);
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

    public boolean isAlive() {
        return _isAlive;
    }

    public void onClick() {

        if (_isAlive) {

            AssetLoader.bubbleUp.play();
            _velocity.y = -140;
        }
    }

    public void die() {

        _isAlive = false;
        _velocity.y = -100;
        AssetLoader.dead.play();
        _rotation = 180;
    }

    public boolean isFinishedDying () {

        return (!_isAlive && _velocity.y == 0);
    }

    public void decelerate() {

        // We want the bird to stop accelerating downwards once it is dead.
        _acceleration.y = 0;
    }

    public void onRestart(int restartX, int restartY) {

        _rotation = 0;
        _position.x = restartX;
        _position.y = restartY;
        _velocity.x = _velocity.y = 0;
        _acceleration.x = 0;
        _acceleration.y = 460;
        _isAlive = true;
    }

    public boolean shouldntFlap() {

        System.out.println("Fishy velocity.y: " + _velocity.y);
        return _velocity.y > 70 || !_isAlive;
    }

	public Circle getBoundingCircle() {

        return _boundingCircle;
	}

	public void setBoundingCircle(Circle _boundingCircle) {

        this._boundingCircle = _boundingCircle;
	}
}
