package com.umiomikket.chessgame.chess;

public class Event {
    public final Modification mod;
    public final String id;

    public Event(Modification mod, String id) {
        this.mod = mod;
        this.id = id;
    }

    public Modification getMod() { return mod; }
    public String getId() { return id; }
}
