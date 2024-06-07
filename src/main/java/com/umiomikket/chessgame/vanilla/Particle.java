package com.umiomikket.chessgame.vanilla;

import com.umiomikket.chessgame.chess.Modification;

public class Particle {
    public final Modification mod;
    public final String id;

    public Particle(Modification mod, String id) {
        this.mod = mod;
        this.id = id;
    }

    public Modification getMod() { return mod; }
    public String getId() { return id; }
}
