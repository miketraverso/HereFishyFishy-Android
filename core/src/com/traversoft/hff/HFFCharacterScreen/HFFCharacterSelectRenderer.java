package com.traversoft.hff.HFFCharacterScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.traversoft.hff.GameObjects.Button;
import com.traversoft.hff.GameObjects.Fishy;
import com.traversoft.hff.GameObjects.SeaFloor;
import com.traversoft.hff.GameObjects.Seaweed;
import com.traversoft.hff.HFFWorld.HFFWorld;
import com.traversoft.hff.utils.AssetLoader;
import com.traversoft.hff.utils.CharacterSelectInputHandler;

import java.util.List;

/**
 * Created by michaelrtraverso on 8/4/15.
 */
public class HFFCharacterSelectRenderer {

    private HFFCharacterSelectWorld _world;
    private OrthographicCamera _camera;
    private ShapeRenderer _shapeRenderer;
    private SpriteBatch _batcher;

    private int _gameHeight, _midPointY;

    private Fishy _fishy;
    private Seaweed _seaweedLeft, _seaweedRight;
    private SeaFloor _floor;

    private Sprite _seaFloorSprite;
    private Animation _fishyFlapAnimation;
    private TextureRegion _seaweedTexture;

    private Sprite _titleFancy, _tapToPlay, _scorecard;
    private List<Button> _menuButtons;

    public HFFCharacterSelectRenderer(HFFCharacterSelectWorld world, int gameHeight, int midPointY) {

        _world = world;
        _gameHeight = gameHeight;
        _midPointY = midPointY;
        _camera = new OrthographicCamera();
        _camera.setToOrtho(true, 136, _gameHeight);

        _batcher = new SpriteBatch();
        _batcher.setProjectionMatrix(_camera.combined);

        _shapeRenderer = new ShapeRenderer();
        _shapeRenderer.setProjectionMatrix(_camera.combined);
        _menuButtons = CharacterSelectInputHandler.initButtons(midPointY);

        initGameObjects();
        initAssets();
    }

    private void initGameObjects() {

        _fishy = new Fishy(136/2 - 11, _midPointY, 22, 17);
        _seaweedLeft = new Seaweed((136/2) - 11 - _fishy.getWidth() - 10, 0, 22, 60, 0, _midPointY);
        _seaweedRight = new Seaweed((136/2) - 11 + _fishy.getWidth() + 10, 0, 22, 60, 0, _midPointY);
        _floor = new SeaFloor(0, _midPointY + 55, 233, 125, 0, _midPointY);
    }

    private void initAssets() {

        _fishyFlapAnimation = AssetLoader.selectedFishAnimation;
        _seaweedTexture = AssetLoader.topSeaweedTexture;
        _seaFloorSprite = AssetLoader.seafloorSprite;
    }

    public void render(float runTime) {

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        _batcher.begin();
        _batcher.disableBlending();
        _batcher.draw(AssetLoader.backgroundSprite, 0, 0, 136, 223);

        _batcher.enableBlending();
        drawSeaweed();
        _batcher.disableBlending();

        drawForeground();

        _batcher.enableBlending();
        _batcher.draw(AssetLoader.selectedFishAnimation.getKeyFrame(runTime),
                _fishy.getX(),
                _fishy.getY(),
                _fishy.getWidth() / 2.0f,
                _fishy.getHeight() / 2.0f,
                _fishy.getWidth(),
                _fishy.getHeight(),
                1,
                1,
                _fishy.getRotation());

        for (Button button : _menuButtons) {
            button.draw(_batcher);
        }

        _batcher.end();
    }

    private void drawForeground() {

        _batcher.draw(_seaFloorSprite,
                _floor.getX(),
                _floor.getY(),
                _floor.getWidth(),
                _floor.getHeight());
    }


    private void drawSeaweed() {

        _batcher.draw(_seaweedTexture,
                _seaweedLeft.getX(),
                _seaweedLeft.getY() + _seaweedLeft.getHeight() + Seaweed.VERTICAL_GAP,
                _seaweedLeft.getWidth(),
                _midPointY + 66 - (_seaweedLeft.getHeight() + Seaweed.VERTICAL_GAP));

        _batcher.draw(_seaweedTexture,
                _seaweedRight.getX(),
                _seaweedRight.getY() + _seaweedRight.getHeight() + Seaweed.VERTICAL_GAP,
                _seaweedRight.getWidth(),
                _midPointY + 66 - (_seaweedRight.getHeight() + Seaweed.VERTICAL_GAP));

//        _batcher.draw(_seaweedTexture,
//                _seaweedLeft.getX(),
//                _seaweedLeft.getY(),
//                _seaweedLeft.getWidth(),
//                _seaweedLeft.getHeight());
//
//
//        _batcher.draw(_seaweedTexture,
//                _seaweedRight.getX(),
//                _seaweedRight.getY(),
//                _seaweedRight.getWidth(),
//                _seaweedRight.getHeight());
    }
}
