package com.traversoft.hff.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.PixmapIO;
import com.badlogic.gdx.utils.ScreenUtils;

import java.io.File;
import java.nio.ByteBuffer;

public class ScreenshotFactory {

    private static int counter = 1;

    public static void saveScreenshot() {

        try {

            FileHandle fh;
            if (Gdx.files.isExternalStorageAvailable()) {

                do{
                    fh = new FileHandle("score" + counter + ".png");
                }while (fh.exists());

//                fh = new FileHandle(Gdx.files.getExternalStoragePath() + "score-" + counter++ + ".png");
                Pixmap pixmap = getScreenshot(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), false);
                PixmapIO.writePNG(fh, pixmap);
                pixmap.dispose();
                counter++;
            }
        }
        catch (Exception e) {}
    }

    public static String getScreenshotPath() {

        return "score-" + counter + ".png";
    }


    private static Pixmap getScreenshot(int x, int y, int w, int h, boolean yDown) {

        final Pixmap pixmap = ScreenUtils.getFrameBufferPixmap(x, y, w, h);

        if (yDown) {
            // Flip the pixmap upside down
            ByteBuffer pixels = pixmap.getPixels();
            int numBytes = w * h * 4;
            byte[] lines = new byte[numBytes];
            int numBytesPerLine = w * 4;
            for (int i = 0; i < h; i++) {
                pixels.position((h - i - 1) * numBytesPerLine);
                pixels.get(lines, i * numBytesPerLine, numBytesPerLine);
            }
            pixels.clear();
            pixels.put(lines);
            pixels.clear();
        }

        return pixmap;
    }
}
