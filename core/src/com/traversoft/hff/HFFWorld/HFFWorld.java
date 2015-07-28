package com.traversoft.hff.HFFWorld;

import com.traversoft.hff.GameObjects.Fishy;
import com.traversoft.hff.GameObjects.ScrollingBackgroundHandler;
import com.traversoft.hff.utils.AssetLoader;

public class HFFWorld 
{
    private Fishy _fishy;
    private ScrollingBackgroundHandler _scroller;
	private boolean _isAlive = true;

    public HFFWorld (int midPointY) {

    	_fishy = new Fishy(33, midPointY - 150, 22, 17);
    	_scroller = new ScrollingBackgroundHandler(midPointY + 55);
    }
    
	public void update(float delta) {

		_fishy.update(delta);
		_scroller.update(delta);

		if (_isAlive && _scroller.collides(_fishy)) {

			_scroller.stop();
			//_fishy.setDead();
			AssetLoader.dead.play();
			_isAlive = false;
		}
	}

	public Fishy getFishy() {
        return _fishy;
    }
	
	public ScrollingBackgroundHandler getScrollingHandler() {
		return _scroller;
	}
}
