package com.umiomikket.crearengine.utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class ImageUtil {
    public static BufferedImage loadFrom(String path) {
        BufferedImage image = null;

        try { image = ImageIO.read(new File(path)); }
        catch (Exception e) { e.printStackTrace(); }

        return image;
    }
}
