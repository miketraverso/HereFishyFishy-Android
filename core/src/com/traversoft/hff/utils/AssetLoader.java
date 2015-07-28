package com.traversoft.hff.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetLoader {

    public static Texture texture;
//    public static TextureRegion backgroundTexture;
//    public static TextureRegion seafloorTexture;
    public static Sprite seafloorSprite, backgroundSprite;
    public static Animation fishyFlapAnimation;
    public static TextureRegion fishyFlapUpTexture, fishyFlapDownTexture, deadFishyTexture, topSeaweedTexture, bottomSeaweedTexture;
    public static Sound dead, bubbleUp, crash, gameOver, ding;
    public static TextureAtlas atlas;

    public static void load() {

        atlas = new TextureAtlas(Gdx.files.internal("data/textures/fish.pack"));
        seafloorSprite = atlas.createSprite("foreground");
        seafloorSprite.flip(false, true);
        backgroundSprite = atlas.createSprite("background");
        backgroundSprite.flip(true, true);

        texture = new Texture(Gdx.files.internal("data/texture.png"));
        texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

        TextureAtlas myTextures = new TextureAtlas("data/fishytexture.txt");

        fishyFlapDownTexture = myTextures.findRegion("fish-0@2x");// new TextureRegion(texture, 136, 0, 17, 12);
        fishyFlapDownTexture.flip(false, true);

        fishyFlapUpTexture = myTextures.findRegion("fish-1@2x");//new TextureRegion(texture, 153, 0, 17, 12);
        fishyFlapUpTexture.flip(false, true);

        deadFishyTexture = myTextures.findRegion("fish-dead@2x");//new TextureRegion(texture, 170, 0, 17, 12);
        deadFishyTexture.flip(false, true);

        TextureRegion[] fishys = { fishyFlapDownTexture, fishyFlapUpTexture};
        fishyFlapAnimation = new Animation(1.0f, fishys);
        fishyFlapAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
        
        topSeaweedTexture = atlas.createSprite("obstacle-weeds-top"); //myTextures.findRegion("obstacle-weeds@2x");// new TextureRegion(texture, 136, 16, 22, 3);
        topSeaweedTexture.flip(false, false);

        bottomSeaweedTexture = atlas.createSprite("obstacle-weeds-bottom"); //myTextures.findRegion("obstacle-weeds@2x");// new TextureRegion(texture, 136, 16, 22, 3);
        bottomSeaweedTexture.flip(false, false);

        dead = Gdx.audio.newSound(Gdx.files.internal("data/audio/gameOverLose.wav"));
        bubbleUp = Gdx.audio.newSound(Gdx.files.internal("data/audio/bubbleUp.m4a"));
        crash = Gdx.audio.newSound(Gdx.files.internal("data/audio/crash.m4a"));
        ding = Gdx.audio.newSound(Gdx.files.internal("data/audio/littleDing.m4a"));
    }
    
    public static void dispose() {
        // We must dispose of the texture when we are finished.
        texture.dispose();
    }

}
