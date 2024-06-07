package com.umiomikket.chessgame.vanilla;

import com.umiomikket.chessgame.chess.Modification;

public class ParticleData {
    public final Particle type;
    private float x, y;

    public ParticleData(Particle type, float x, float y) {
        this.type = type;
        this.x = x;
        this.y = y;
    }

    public Particle getType() { return type; }

    private Modification getMod() { return type.mod; }
    private String getModId() { return type.mod.id; }
    public String getId() { return type.id; }

    public float getX() { return x; }
    public void setX(float x) { this.x = x; }

    public float getY() { return y; }
    public void setY(float y) { this.y = y; }
}
