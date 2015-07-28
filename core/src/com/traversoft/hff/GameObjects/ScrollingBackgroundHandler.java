package com.traversoft.hff.GameObjects;

import com.traversoft.hff.utils.AssetLoader;

public class ScrollingBackgroundHandler {

	private SeaFloor _floor, _floor2;
	private Seaweed _weed1, _weed2, _weed3;
	
    public static final int kScrollingSpeed = -59;
    public static final int kSeaweedGap = 75;

    public ScrollingBackgroundHandler(float yPos) {
    	_floor = new SeaFloor(0, yPos, 320, 156, kScrollingSpeed, yPos); //(0.0, yPos, 143, 11, kScrollingSpeed);
        _floor2 = new SeaFloor(_floor.getTailX(), yPos, 320, 156, kScrollingSpeed, yPos);

    	_weed1 = new Seaweed(210, 0, 22, 60, kScrollingSpeed, yPos);
    	_weed2 = new Seaweed(_weed1.getTailX() + kSeaweedGap, 0, 22, 60, kScrollingSpeed, yPos);
    	_weed3 = new Seaweed(_weed2.getTailX() + kSeaweedGap, 0, 22, 60, kScrollingSpeed, yPos);
    }

    public void update(float delta) {
        // Update our objects
        _floor.update(delta);
        _floor2.update(delta);
        _weed1.update(delta);
        _weed2.update(delta);
        _weed3.update(delta);

        if (_weed1.isScrolledLeft()) {
        	_weed1.reset(_weed3.getTailX() + kSeaweedGap);
        }
        else if (_weed2.isScrolledLeft()) {
        	_weed2.reset(_weed1.getTailX() + kSeaweedGap);
        }
        else if (_weed3.isScrolledLeft()) {
        	_weed3.reset(_weed2.getTailX() + kSeaweedGap);
        }

        if (_floor.isScrolledLeft()) {
        	_floor.reset(Math.round(_floor2.getTailX()));
        }
        else if (_floor2.isScrolledLeft()) {
        	_floor2.reset(Math.round(_floor.getTailX()));
        }  
    }

    public void stop() {
        _floor.stop();
        _floor2.stop();
        _weed1.stop();
        _weed2.stop();
        _weed3.stop();
    }

    // Return true if ANY pipe hits the bird.
    public boolean collides(Fishy fishy) {
       return (_weed1.collides(fishy) || _weed2.collides(fishy) || _weed3.collides(fishy) ||
    		   _floor.collides(fishy) || _floor2.collides(fishy));
    }
    
    public SeaFloor getSeaFloor() {
		return _floor;
	}
    
    public SeaFloor getSeaFloor2() { return _floor2;
	}

	public Seaweed getWeed1() {
		return _weed1;
	}

	public Seaweed getWeed2() {
		return _weed2;
	}

	public Seaweed getWeed3() {
		return _weed3;
	}

    
}
