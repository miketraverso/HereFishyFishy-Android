package com.traversoft.hff.HFFCharacterScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.traversoft.hff.HFFWorld.HFFRenderer;
import com.traversoft.hff.HFFWorld.HFFWorld;
import com.traversoft.hff.HereFishyFishyMain;
import com.traversoft.hff.Screens.GameScreen;
import com.traversoft.hff.TweenAccessors.SpriteAccessor;
import com.traversoft.hff.utils.AssetLoader;
import com.traversoft.hff.utils.CharacterSelectInputHandler;
import com.traversoft.hff.utils.InputHandler;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;

/**
 * Created by michaelrtraverso on 8/4/15.
 */
public class CharacterSelectScreen implements Screen {

    private TweenManager manager;
    private HFFCharacterSelectWorld _world;
    private HFFCharacterSelectRenderer _renderer;
    private float _runTime = 0.0f;
    private HereFishyFishyMain _game;
    private CharacterSelectInputHandler csInputHandler;

    public CharacterSelectScreen(HereFishyFishyMain game) {

        _game = game;
        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();
        float gameWidth = 136;
        float gameHeight = screenHeight / (screenWidth / gameWidth);

        int midPointY = (int) (gameHeight / 2);

        _world = new HFFCharacterSelectWorld(midPointY, game);
        csInputHandler = new CharacterSelectInputHandler(_world, screenWidth / gameWidth, screenHeight / gameHeight);
        _renderer = new HFFCharacterSelectRenderer(_world, (int)gameHeight, midPointY);
    }

    @Override
    public void show() {

//        _game.setScreen(new GameScreen(_game));

//        sprite = new Sprite(AssetLoader.backgroundSprite);
//        sprite.setColor(0, 0, 0, 0);
//
//        float width = Gdx.graphics.getWidth();
//        float height = Gdx.graphics.getHeight();
//        float desiredWidth = width * .7f;
//        float scale = desiredWidth / sprite.getWidth();
//
//        sprite.setSize(sprite.getWidth() * scale, sprite.getHeight() * scale);
//        sprite.setPosition((width / 2) - (sprite.getWidth() / 2), (height / 2)
//                - (sprite.getHeight() / 2));
//        setupTween();
//        batcher = new SpriteBatch();
    }

    private void setupTween() {
        Tween.registerAccessor(Sprite.class, new SpriteAccessor());
        manager = new TweenManager();

        TweenCallback cb = new TweenCallback() {
            @Override
            public void onEvent(int type, BaseTween<?> source) {
                _game.setScreen(new GameScreen(_game));
            }
        };

//        Tween.to(sprite, SpriteAccessor.ALPHA, .8f).target(1)
//                .ease(TweenEquations.easeInOutQuad).repeatYoyo(1, .4f)
//                .setCallback(cb).setCallbackTriggers(TweenCallback.COMPLETE)
//                .start(manager);
    }

    @Override
    public void render(float delta) {

        _runTime += delta;
        _world.update(delta);
        _renderer.render(delta);
        Gdx.input.setInputProcessor(csInputHandler);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void hide() {
        // TODO Auto-generated method stub

    }

    @Override
    public void pause() {
        // TODO Auto-generated method stub

    }

    @Override
    public void resume() {
        // TODO Auto-generated method stub

    }

    @Override
    public void dispose() {
        // TODO Auto-generated method stub

    }

}

