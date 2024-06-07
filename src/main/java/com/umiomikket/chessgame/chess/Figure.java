package com.umiomikket.chessgame.chess;

import com.umiomikket.chessgame.ChessGame;
import com.umiomikket.crearengine.utils.ImageUtil;

import java.awt.image.BufferedImage;

public class Figure {
    public final Modification mod;
    public final String id;

    private BufferedImage spriteBlackTeam;
    private BufferedImage spriteWhiteTeam;

    private boolean defaultRender;

    public Figure(Modification mod, String id) {
        this.mod = mod;
        this.id = id;
        defaultRender = true;
    }

    public Modification getMod() { return mod; }
    public String getId() { return id; }

    // Sprite management
    public BufferedImage getSpriteBlackTeam() { return spriteBlackTeam; }
    public void setSpriteBlackTeam(BufferedImage image) { this.spriteBlackTeam = image; }
    public void setSpriteBlackTeam(String path) { this.spriteBlackTeam = ImageUtil.loadFrom(path); }

    public BufferedImage getSpriteWhiteTeam() { return spriteWhiteTeam; }
    public void setSpriteWhiteTeam(BufferedImage image) { this.spriteWhiteTeam = image; }
    public void setSpriteWhiteTeam(String path) { this.spriteWhiteTeam = ImageUtil.loadFrom(path); }

    public EventManager getEventManager() { return ChessGame.getEventManager(); }
    public void addEventClass(Object eventClass) { getEventManager().addEventClass(eventClass); }
    public void playEvent(Event event) { getEventManager().playEvent(event); }

    public boolean isDefaultRender() { return defaultRender; }
    public void setDefaultRender(boolean value) { defaultRender = value; }
}
