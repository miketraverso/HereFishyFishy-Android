package com.traversoft.hff.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.traversoft.hff.HFFCharacterScreen.HFFCharacterSelectWorld;


public class AssetLoader {

    public static Sprite seafloorSprite, backgroundSprite;
    public static TextureAtlas atlas;
    public static BitmapFont font, font_small, font_smallest, yellow_font, yellow_font_small;
    public static TextureRegion topSeaweedTexture, bottomSeaweedTexture;

    public static Animation selectedFishAnimation;
    public static Animation fishyFlapAnimation, swedishFlapAnimation, girlFlapAnimation, clownFlapAnimation,
            stinkyFlapAnimation, woodFlapAnimation, superFlapAnimation, catFlapAnimation,
            oldFlapAnimation ,goldFlapAnimation;

    public static Sprite selectedFishDeadTexture, selectedFishFlapUpTexture, selectedFishFlapDownTexture;
    public static Sprite fishyFlapUpTexture, fishyFlapDownTexture, deadFishySprite;
    public static Sprite swedishFlapUpTexture, swedishFlapDownTexture, swedishDeadSprite;
    public static Sprite girlFlapUpTexture, girlFlapDownTexture, girlDeadSprite;
    public static Sprite clownFlapUpTexture, clownFlapDownTexture, clownDeadSprite;
    public static Sprite stinkyFlapUpTexture, stinkyFlapDownTexture, stinkyDeadSprite;
    public static Sprite woodFlapUpTexture, woodFlapDownTexture, woodDeadSprite;
    public static Sprite superFlapUpTexture, superFlapDownTexture, superDeadSprite;
    public static Sprite catFlapUpTexture, catFlapDownTexture, catDeadSprite;
    public static Sprite oldFlapUpTexture, oldFlapDownTexture, oldDeadSprite;
    public static Sprite goldFlapUpTexture, goldFlapDownTexture, goldDeadSprite;

    public static Sound dead, bubbleUp, crash, ding;
    public static Music backgroundSound;
    public static Sprite titleFancy, tapToPlay, scorecard;
    public static Preferences prefs;
    public static Sprite leftButtonUpSprite, rightButtonUpSprite, leftButtonDownSprite, rightButtonDownSprite;
    public static Sprite okButtonUpSprite, okButtonDownSprite;
    public static TextureRegion buttonUpSprite, buttonDownSprite;

    public static void load() {

        font = new BitmapFont(Gdx.files.internal("data/font/font.fnt"),
                              Gdx.files.internal("data/font/font-white.png"),
                              false);
        font.getData().setScale(.30f, -.30f);

        font_small = new BitmapFont(Gdx.files.internal("data/font/font.fnt"),
                Gdx.files.internal("data/font/font-white.png"),
                false);
        font_small.getData().setScale(.25f, -.25f);

        font_smallest = new BitmapFont(Gdx.files.internal("data/font/font.fnt"),
                Gdx.files.internal("data/font/font-white.png"),
                false);
        font_smallest.getData().setScale(.20f, -.20f);

                yellow_font = new BitmapFont(Gdx.files.internal("data/font/font-yellow.fnt"));
        yellow_font.getData().setScale(.30f, -.30f);
        yellow_font_small = new BitmapFont(Gdx.files.internal("data/font/font-yellow.fnt"));
        yellow_font_small.getData().setScale(.25f, -.25f);

        atlas = new TextureAtlas(Gdx.files.internal("data/textures/fish.pack"));
        seafloorSprite = atlas.createSprite("foreground");
        seafloorSprite.flip(false, true);
        backgroundSprite = atlas.createSprite("background");
        backgroundSprite.flip(true, true);

        leftButtonUpSprite = atlas.createSprite("nextButtonUp");
        leftButtonUpSprite.flip(true,true);
        leftButtonDownSprite = atlas.createSprite("nextButtonDown");
        leftButtonDownSprite.flip(true,true);
        rightButtonUpSprite = atlas.createSprite("nextButtonUp");
        rightButtonUpSprite.flip(false,true);
        rightButtonDownSprite = atlas.createSprite("nextButtonDown");
        rightButtonDownSprite.flip(false,true);

        okButtonUpSprite = atlas.createSprite("okUp");
        okButtonUpSprite.flip(false,true);
        okButtonDownSprite = atlas.createSprite("okDown");
        okButtonDownSprite.flip(false,true);

        buttonUpSprite = atlas.createSprite("button");
        buttonUpSprite.flip(false,true);
        buttonDownSprite = atlas.createSprite("button-dark");
        buttonDownSprite.flip(false,true);

        titleFancy = atlas.createSprite("title-fancy");
        titleFancy.flip(false, true);
        titleFancy.setScale(0.4f,0.4f);

        tapToPlay = atlas.createSprite("tap");
        tapToPlay.flip(false, true);
        tapToPlay.setScale(0.4f,0.4f);

        scorecard  = atlas.createSprite("scorecard");
        scorecard.flip(false, true);

        AssetLoader.initFishyFishSprite();
        AssetLoader.initSwedishFishSprite();
        AssetLoader.initGirlFishSprite();
        AssetLoader.initClownFishSprite();
        AssetLoader.initStinkyFishSprite();
        AssetLoader.initWoodFishSprite();
        AssetLoader.initSuperFishSprite();
        AssetLoader.initCatFishSprite();
        AssetLoader.initOldFishSprite();
        AssetLoader.initGoldFishSprite();

        topSeaweedTexture = atlas.createSprite("obstacle-weeds-top"); //myTextures.findRegion("obstacle-weeds@2x");// new TextureRegion(texture, 136, 16, 22, 3);
        topSeaweedTexture.flip(false, false);

        bottomSeaweedTexture = atlas.createSprite("obstacle-weeds-bottom"); //myTextures.findRegion("obstacle-weeds@2x");// new TextureRegion(texture, 136, 16, 22, 3);
        bottomSeaweedTexture.flip(false, false);

        dead = Gdx.audio.newSound(Gdx.files.internal("data/audio/gameOverLose.wav"));
        bubbleUp = Gdx.audio.newSound(Gdx.files.internal("data/audio/bubbleUp.m4a"));
        crash = Gdx.audio.newSound(Gdx.files.internal("data/audio/crash.m4a"));
        ding = Gdx.audio.newSound(Gdx.files.internal("data/audio/littleDing.m4a"));
        backgroundSound = Gdx.audio.newMusic(Gdx.files.internal("data/audio/backgroundLoop.wav"));
        backgroundSound.setLooping(true);

        prefs = Gdx.app.getPreferences("HFF");

        if (!prefs.contains("highScore")) {
            prefs.putInteger("highScore", 0);
        }

        if (!prefs.contains("fish")) {

            AssetLoader.updateSelectedFish();
        }
    }

    public static void updateSelectedFish() {

        HFFCharacterSelectWorld.GameCharacter selectedFish = HFFCharacterSelectWorld.getSelectedFish();
        switch (selectedFish) {

            case SWEDISH_FISH:
                selectedFishAnimation = swedishFlapAnimation;
                selectedFishDeadTexture = swedishDeadSprite;
                selectedFishFlapDownTexture = swedishFlapDownTexture;
                selectedFishFlapUpTexture = swedishFlapUpTexture;
                break;

            case GIRL_FISH:
                selectedFishAnimation = girlFlapAnimation;
                selectedFishDeadTexture = girlDeadSprite;
                selectedFishFlapDownTexture = girlFlapDownTexture;
                selectedFishFlapUpTexture = girlFlapUpTexture;
                break;

            case CLOWN_FISH:
                selectedFishAnimation = clownFlapAnimation;
                selectedFishDeadTexture = clownDeadSprite;
                selectedFishFlapDownTexture = clownFlapDownTexture;
                selectedFishFlapUpTexture = clownFlapUpTexture;
                break;

            case STINKY_FISH:
                selectedFishAnimation = stinkyFlapAnimation;
                selectedFishDeadTexture = stinkyDeadSprite;
                selectedFishFlapDownTexture = stinkyFlapDownTexture;
                selectedFishFlapUpTexture = stinkyFlapUpTexture;
                break;

            case WOOD_FISH:
                selectedFishAnimation = woodFlapAnimation;
                selectedFishDeadTexture = woodDeadSprite;
                selectedFishFlapDownTexture = woodFlapDownTexture;
                selectedFishFlapUpTexture = woodFlapUpTexture;
                break;

            case SUPER_FISH:
                selectedFishAnimation = superFlapAnimation;
                selectedFishDeadTexture = superDeadSprite;
                selectedFishFlapDownTexture = superFlapDownTexture;
                selectedFishFlapUpTexture = superFlapUpTexture;
                break;

            case CAT_FISH:
                selectedFishAnimation = catFlapAnimation;
                selectedFishDeadTexture = catDeadSprite;
                selectedFishFlapDownTexture = catFlapDownTexture;
                selectedFishFlapUpTexture = catFlapUpTexture;
                break;

            case OLD_FISH:
                selectedFishAnimation = oldFlapAnimation;
                selectedFishDeadTexture = oldDeadSprite;
                selectedFishFlapDownTexture = oldFlapDownTexture;
                selectedFishFlapUpTexture = oldFlapUpTexture;
                break;

            case GOLD_FISH:
                selectedFishAnimation = goldFlapAnimation;
                selectedFishDeadTexture = goldDeadSprite;
                selectedFishFlapDownTexture = goldFlapDownTexture;
                selectedFishFlapUpTexture = goldFlapUpTexture;
                break;

            case FISHY:
            default:
                selectedFishAnimation = fishyFlapAnimation;
                selectedFishDeadTexture = deadFishySprite;
                selectedFishFlapDownTexture = fishyFlapDownTexture;
                selectedFishFlapUpTexture = fishyFlapUpTexture;
                break;
        }
    }

    private static void initFishyFishSprite() {

        fishyFlapDownTexture = atlas.createSprite("fish-0");
        fishyFlapDownTexture.flip(false, true);
        fishyFlapUpTexture = atlas.createSprite("fish-1");
        fishyFlapUpTexture.flip(false, true);
        deadFishySprite = atlas.createSprite("fish-dead");
        deadFishySprite.flip(false, true);

        TextureRegion[] fishys = { fishyFlapUpTexture, fishyFlapDownTexture };
        fishyFlapAnimation = new Animation(0.018f, fishys);
        fishyFlapAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);

        selectedFishAnimation = fishyFlapAnimation;
        selectedFishDeadTexture = deadFishySprite;
        selectedFishFlapDownTexture = fishyFlapDownTexture;
        selectedFishFlapUpTexture = fishyFlapUpTexture;
    }

    private static void initSwedishFishSprite() {

        swedishFlapDownTexture = atlas.createSprite("red-0");
        swedishFlapDownTexture.flip(false, true);
        swedishFlapUpTexture = atlas.createSprite("red-1");
        swedishFlapUpTexture.flip(false, true);
        swedishDeadSprite = atlas.createSprite("red-dead");
        swedishDeadSprite.flip(false, true);

        TextureRegion[] fishys = { swedishFlapUpTexture, swedishFlapDownTexture };
        swedishFlapAnimation = new Animation(0.018f, fishys);
        swedishFlapAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
    }

    private static void initGirlFishSprite() {

        girlFlapDownTexture = atlas.createSprite("girl-0");
        girlFlapDownTexture.flip(false, true);
        girlFlapUpTexture = atlas.createSprite("girl-1");
        girlFlapUpTexture.flip(false, true);
        girlDeadSprite = atlas.createSprite("girl-dead");
        girlDeadSprite.flip(false, true);

        TextureRegion[] fishys = { girlFlapUpTexture, girlFlapDownTexture };
        girlFlapAnimation = new Animation(0.018f, fishys);
        girlFlapAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
    }

    private static void initClownFishSprite() {

        clownFlapDownTexture = atlas.createSprite("clown-0");
        clownFlapDownTexture.flip(false, true);
        clownFlapUpTexture = atlas.createSprite("clown-1");
        clownFlapUpTexture.flip(false, true);
        clownDeadSprite = atlas.createSprite("clown-dead");
        clownDeadSprite.flip(false, true);

        TextureRegion[] fishys = { clownFlapUpTexture, clownFlapDownTexture };
        clownFlapAnimation = new Animation(0.018f, fishys);
        clownFlapAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
    }

    private static void initStinkyFishSprite() {

        stinkyFlapDownTexture = atlas.createSprite("stinky-0");
        stinkyFlapDownTexture.flip(false, true);
        stinkyFlapUpTexture = atlas.createSprite("stinky-1");
        stinkyFlapUpTexture.flip(false, true);
        stinkyDeadSprite = atlas.createSprite("stinky-dead");
        stinkyDeadSprite.flip(false, true);

        TextureRegion[] fishys = { stinkyFlapUpTexture, stinkyFlapDownTexture };
        stinkyFlapAnimation = new Animation(0.018f, fishys);
        stinkyFlapAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
    }

    private static void initWoodFishSprite() {

        woodFlapDownTexture = atlas.createSprite("wood-fish-0");
        woodFlapDownTexture.flip(false, true);
        woodFlapUpTexture = atlas.createSprite("wood-fish-1");
        woodFlapUpTexture.flip(false, true);
        woodDeadSprite = atlas.createSprite("wood-fish-dead");
        woodDeadSprite.flip(false, true);

        TextureRegion[] fishys = { woodFlapUpTexture, woodFlapDownTexture };
        woodFlapAnimation = new Animation(0.018f, fishys);
        woodFlapAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
    }

    private static void initSuperFishSprite() {

        superFlapDownTexture = atlas.createSprite("super-fish-0");
        superFlapDownTexture.flip(false, true);
        superFlapUpTexture = atlas.createSprite("super-fish-1");
        superFlapUpTexture.flip(false, true);
        superDeadSprite = atlas.createSprite("super-fish-dead");
        superDeadSprite.flip(false, true);

        TextureRegion[] fishys = { superFlapUpTexture, superFlapDownTexture };
        superFlapAnimation = new Animation(0.018f, fishys);
        superFlapAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
    }

    private static void initCatFishSprite() {

        catFlapDownTexture = atlas.createSprite("catfish-0");
        catFlapDownTexture.flip(false, true);
        catFlapUpTexture = atlas.createSprite("catfish-1");
        catFlapUpTexture.flip(false, true);
        catDeadSprite = atlas.createSprite("catfish-dead");
        catDeadSprite.flip(false, true);

        TextureRegion[] fishys = { catFlapUpTexture, catFlapDownTexture };
        catFlapAnimation = new Animation(0.018f, fishys);
        catFlapAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
    }

    private static void initOldFishSprite() {

        oldFlapDownTexture = atlas.createSprite("oldfish-0");
        oldFlapDownTexture.flip(false, true);
        oldFlapUpTexture = atlas.createSprite("oldfish-1");
        oldFlapUpTexture.flip(false, true);
        oldDeadSprite = atlas.createSprite("oldfish-dead");
        oldDeadSprite.flip(false, true);

        TextureRegion[] fishys = { oldFlapUpTexture, oldFlapDownTexture };
        oldFlapAnimation = new Animation(0.018f, fishys);
        oldFlapAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
    }

    private static void initGoldFishSprite() {

        goldFlapDownTexture = atlas.createSprite("goldfish-0");
        goldFlapDownTexture.flip(false, true);
        goldFlapUpTexture = atlas.createSprite("goldfish-1");
        goldFlapUpTexture.flip(false, true);
        goldDeadSprite = atlas.createSprite("goldfish-dead");
        goldDeadSprite.flip(false, true);

        TextureRegion[] fishys = { goldFlapUpTexture, goldFlapDownTexture };
        goldFlapAnimation = new Animation(0.018f, fishys);
        goldFlapAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
    }

    public static void dispose() {

        atlas.dispose();
        dead.dispose();
        bubbleUp.dispose();
        crash.dispose();
        ding.dispose();
        font.dispose();
        font_small.dispose();
        font_smallest.dispose();
        yellow_font.dispose();
        yellow_font_small.dispose();
        backgroundSound.dispose();
    }

}
