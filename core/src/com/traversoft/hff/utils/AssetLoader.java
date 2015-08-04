package com.traversoft.hff.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


public class AssetLoader {

    public static Texture texture;
    public static Sprite seafloorSprite, backgroundSprite;
    public static Animation fishyFlapAnimation;
    public static TextureRegion fishyFlapUpTexture, fishyFlapDownTexture, deadFishyTexture, topSeaweedTexture, bottomSeaweedTexture;
    public static Sound dead, bubbleUp, crash, gameOver, backgroundSound, ding;
    public static TextureAtlas atlas;
    public static BitmapFont font, yellow_font;
    public static Sprite titleFancy, tapToPlay, scorecard;
    public static Preferences prefs;

    public static void load() {

        font = new BitmapFont(Gdx.files.internal("data/font/font.fnt"),
                              Gdx.files.internal("data/font/font-white.png"),
                              false);
        font.getData().setScale(.30f, -.30f);

        yellow_font = new BitmapFont(Gdx.files.internal("data/font/font-yellow.fnt"));
        yellow_font.getData().setScale(.30f, -.30f);

        atlas = new TextureAtlas(Gdx.files.internal("data/textures/fish.pack"));
        seafloorSprite = atlas.createSprite("foreground");
        seafloorSprite.flip(false, true);
        backgroundSprite = atlas.createSprite("background");
        backgroundSprite.flip(true, true);

        titleFancy = atlas.createSprite("title-fancy");
        titleFancy.flip(false, true);
        titleFancy.setScale(0.4f,0.4f);

        tapToPlay = atlas.createSprite("tap");
        tapToPlay.flip(false, true);
        tapToPlay.setScale(0.4f,0.4f);

        scorecard  = atlas.createSprite("scorecard");
        scorecard.flip(false, true);

        texture = new Texture(Gdx.files.internal("data/texture.png"));
        texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

        TextureAtlas myTextures = new TextureAtlas("data/fishytexture.txt");

        fishyFlapDownTexture = atlas.createSprite("fish-0");
        fishyFlapDownTexture.flip(false, true);

        fishyFlapUpTexture = atlas.createSprite("fish-1");
        fishyFlapUpTexture.flip(false, true);

        deadFishyTexture = atlas.createSprite("fish-dead");
        deadFishyTexture.flip(false, true);

        TextureRegion[] fishys = { fishyFlapUpTexture, fishyFlapDownTexture };
        fishyFlapAnimation = new Animation(0.018f, fishys);
        fishyFlapAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
        
        topSeaweedTexture = atlas.createSprite("obstacle-weeds-top"); //myTextures.findRegion("obstacle-weeds@2x");// new TextureRegion(texture, 136, 16, 22, 3);
        topSeaweedTexture.flip(false, false);

        bottomSeaweedTexture = atlas.createSprite("obstacle-weeds-bottom"); //myTextures.findRegion("obstacle-weeds@2x");// new TextureRegion(texture, 136, 16, 22, 3);
        bottomSeaweedTexture.flip(false, false);

        dead = Gdx.audio.newSound(Gdx.files.internal("data/audio/gameOverLose.wav"));
        bubbleUp = Gdx.audio.newSound(Gdx.files.internal("data/audio/bubbleUp.m4a"));
        crash = Gdx.audio.newSound(Gdx.files.internal("data/audio/crash.m4a"));
        ding = Gdx.audio.newSound(Gdx.files.internal("data/audio/littleDing.m4a"));
        backgroundSound = Gdx.audio.newSound(Gdx.files.internal("data/audio/littleDing.m4a"));
        prefs = Gdx.app.getPreferences("HFF");

        if (!prefs.contains("highScore")) {
            prefs.putInteger("highScore", 0);
        }
    }

    public static void dispose() {

        texture.dispose();
        atlas.dispose();
        dead.dispose();
        bubbleUp.dispose();
        crash.dispose();
        ding.dispose();
        font.dispose();
        backgroundSound.dispose();
    }

}
