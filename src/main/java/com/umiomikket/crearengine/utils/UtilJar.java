package com.umiomikket.crearengine.utils;

import java.io.File;
import java.net.URL;
import java.net.URLDecoder;

public class UtilJar {
    public static String getProgramPath() {
        URL url = UtilJar.class.getProtectionDomain().getCodeSource().getLocation();

        String jarPath = null;
        try { jarPath = URLDecoder.decode(url.getFile(), "UTF-8"); }
        catch (Exception e) { e.printStackTrace(); }
        if (jarPath == null) return null;

        return new File(jarPath).getParentFile().getPath();
    }
}
