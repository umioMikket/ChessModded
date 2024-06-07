package com.umiomikket.chessgame.chess;

public class ModificationInformation {
    public final String name;
    public final float version;
    public final String description;
    public final String author;
    public final String website;
    public final String[] depends;

    public ModificationInformation(String name, float version, String description,
                                   String author, String website, String[] depends
    ) {
        this.name = name;
        this.version = version;
        this.description = description;
        this.author = author;
        this.website = website;
        this.depends = depends;
    }
}
