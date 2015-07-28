package com.traversoft.hff.HFFWorld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.traversoft.hff.GameObjects.Fishy;
import com.traversoft.hff.GameObjects.ScrollingBackgroundHandler;
import com.traversoft.hff.GameObjects.SeaFloor;
import com.traversoft.hff.GameObjects.Seaweed;
import com.traversoft.hff.utils.AssetLoader;

import net.dermetfan.gdx.assets.AnnotationAssetManager;

public class HFFRenderer {

	private HFFWorld _world;
	private OrthographicCamera _camera;
	private ShapeRenderer _shapeRenderer;

    private SpriteBatch _batcher;

    private int _gameHeight, _midPointY;
    
    // Game Objects
    private Fishy _fishy;
    private ScrollingBackgroundHandler _scroller;
    private SeaFloor _frontFloor, _backFloor;
    private Seaweed _seaweed1, _seaweed2, _seaweed3;

    // Game Assets
//    private TextureRegion _backgroundTexture, _seafloorTexture;
    private Sprite _seaFloorSprite;
    private Animation _fishyFlapAnimation;
    private TextureRegion _fishyFlapDownTexture, _fishyFlapUpTexture, _deadFishyTexture, _bottomSeaweedTexture, _topSeaweedTexture;

	public HFFRenderer(HFFWorld world, int gameHeight, int midPointY)
	{
		_world = world;
		_gameHeight = gameHeight;
		_midPointY = midPointY;
		_camera = new OrthographicCamera();
		_camera.setToOrtho(true, 136, _gameHeight);
		
        _batcher = new SpriteBatch();
        _batcher.setProjectionMatrix(_camera.combined);

		_shapeRenderer = new ShapeRenderer();
		_shapeRenderer.setProjectionMatrix(_camera.combined);
		
		initGameObjects();
		initAssets();
	}

	private void initGameObjects() {
        _fishy = _world.getFishy();
        _scroller = _world.getScrollingHandler();
        _frontFloor = _scroller.getSeaFloor();
    	_backFloor = _scroller.getSeaFloor2();
    	_seaweed1 = _scroller.getWeed1();
    	_seaweed2 = _scroller.getWeed2();
    	_seaweed3 = _scroller.getWeed3();
    }

    private void initAssets() {

        _fishyFlapAnimation = AssetLoader.fishyFlapAnimation;
        _fishyFlapDownTexture = AssetLoader.fishyFlapDownTexture;
        _fishyFlapUpTexture = AssetLoader.fishyFlapUpTexture;
        _deadFishyTexture = AssetLoader.deadFishyTexture;
        _bottomSeaweedTexture = AssetLoader.bottomSeaweedTexture;
        _topSeaweedTexture = AssetLoader.topSeaweedTexture;
        _seaFloorSprite = AssetLoader.seafloorSprite;
    }

	public void render(float runTime) {		
		
		// Fill the entire screen with black, to prevent potential flickering.
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        _batcher.begin();
        _batcher.disableBlending();
        _batcher.draw(AssetLoader.backgroundSprite, 0, 0, 136, 223);

        _batcher.enableBlending();
        drawPipes();
        _batcher.disableBlending();

        drawForeground();

        _batcher.enableBlending();
        if (_fishy.shouldntFlap())  {

        	// Draw bird at its coordinates. Retrieve the Animation object from AssetLoader
        	// Pass in the runTime variable to get the current frame.
        	_batcher.draw(_fishyFlapDownTexture, 
        				  _fishy.getX(), _fishy.getY(),
        				  _fishy.getWidth() / 2.0f, _fishy.getHeight() / 2.0f, 
        				  _fishy.getWidth(), _fishy.getHeight(), 1, 1, _fishy.getRotation());
        }
        else {
            _batcher.draw(_fishyFlapAnimation.getKeyFrame(runTime), _fishy.getX(),
            		_fishy.getY(), _fishy.getWidth() / 2.0f,
            		_fishy.getHeight() / 2.0f, _fishy.getWidth(), _fishy.getHeight(),
                    1, 1, _fishy.getRotation());
        }
        
        _batcher.end();
        
//         Collision
        _shapeRenderer.begin(ShapeType.Filled);
        _shapeRenderer.setColor(Color.RED);
        _shapeRenderer.circle(_fishy.getBoundingCircle().x, _fishy.getBoundingCircle().y, _fishy.getBoundingCircle().radius);
        _shapeRenderer.end();
                
    }
	
	private void drawForeground() {

        _batcher.draw(_seaFloorSprite,
                        _frontFloor.getX(),
                        _frontFloor.getY(),
                        _frontFloor.getWidth(),
                        _frontFloor.getHeight());

        _batcher.draw(_seaFloorSprite,
                        _backFloor.getX(),
                        _backFloor.getY(),
                        _backFloor.getWidth(),
                        _backFloor.getHeight());
    }


    private void drawPipes() {

    	_batcher.draw(_bottomSeaweedTexture,
                        _seaweed1.getX(),
                        _seaweed1.getY(),
                        _seaweed1.getWidth(),
    			        _seaweed1.getHeight());
    	_batcher.draw(_topSeaweedTexture,
                        _seaweed1.getX(),
                        _seaweed1.getY() + _seaweed1.getHeight() + Seaweed.VERTICAL_GAP,
            			_seaweed1.getWidth(),
                        _midPointY + 66 - (_seaweed1.getHeight() + Seaweed.VERTICAL_GAP));


        _batcher.draw(_bottomSeaweedTexture, _seaweed2.getX(),
                        _seaweed2.getY(),
                        _seaweed2.getWidth(),
        		        _seaweed2.getHeight());
        _batcher.draw(_topSeaweedTexture,
                        _seaweed2.getX(),
                        _seaweed2.getY() + _seaweed2.getHeight() + Seaweed.VERTICAL_GAP,
        		        _seaweed2.getWidth(),
                        _midPointY + 66 - (_seaweed2.getHeight() + Seaweed.VERTICAL_GAP));

        _batcher.draw(_bottomSeaweedTexture,
                        _seaweed3.getX(),
                        _seaweed3.getY(),
                        _seaweed3.getWidth(),
        		        _seaweed3.getHeight());
        _batcher.draw(_topSeaweedTexture,
                        _seaweed3.getX(),
                        _seaweed3.getY() + _seaweed3.getHeight() + Seaweed.VERTICAL_GAP,
        		        _seaweed3.getWidth(),
                        _midPointY + 66 - (_seaweed3.getHeight() + Seaweed.VERTICAL_GAP));

//        _shapeRenderer.begin(ShapeType.Filled);
//        _shapeRenderer.setColor(Color.GREEN);
//        _shapeRenderer.rect(_seaweed1.getX(), _seaweed1.getY(), _seaweed1.getWidth(), _seaweed1.getHeight());
//        _shapeRenderer.end();
//        _shapeRenderer.begin(ShapeType.Filled);
//        _shapeRenderer.setColor(Color.GREEN);
//        _shapeRenderer.rect(_seaweed1.getX(), _seaweed1.getY() + _seaweed1.getHeight() + 45, _seaweed1.getWidth(), _midPointY + 66 - (_seaweed1.getHeight() + 45));
//        _shapeRenderer.end();
//
//        _shapeRenderer.begin(ShapeType.Filled);
//        _shapeRenderer.setColor(Color.GREEN);
//        _shapeRenderer.rect(_seaweed2.getX(), _seaweed2.getY(), _seaweed2.getWidth(), _seaweed2.getHeight());
//        _shapeRenderer.end();
//        _shapeRenderer.begin(ShapeType.Filled);
//        _shapeRenderer.setColor(Color.GREEN);
//        _shapeRenderer.rect(_seaweed2.getX(), _seaweed2.getY() + _seaweed2.getHeight() + 45, _seaweed2.getWidth(), _midPointY + 66 - (_seaweed2.getHeight() + 45));
//        _shapeRenderer.end();
//
//        _shapeRenderer.begin(ShapeType.Filled);
//        _shapeRenderer.setColor(Color.GREEN);
//        _shapeRenderer.rect(_seaweed3.getX(), _seaweed3.getY(), _seaweed3.getWidth(), _seaweed3.getHeight());
//        _shapeRenderer.end();
//        _shapeRenderer.begin(ShapeType.Filled);
//        _shapeRenderer.setColor(Color.GREEN);
//        _shapeRenderer.rect(_seaweed3.getX(), _seaweed3.getY() + _seaweed3.getHeight() + 45, _seaweed3.getWidth(), _midPointY + 66 - (_seaweed3.getHeight() + 45));
//        _shapeRenderer.end();

    }
}
