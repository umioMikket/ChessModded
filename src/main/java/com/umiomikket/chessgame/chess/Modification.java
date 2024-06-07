package com.umiomikket.chessgame.chess;

import java.util.ArrayList;

public class Modification {
    public final String id;

    private final ArrayList<Figure> figures;

    public Modification(String id) {
        this.id = id;
        figures = new ArrayList<>();
    }

    public String getId() { return id; }

    // Figures management
    public boolean hasFigure(Figure figure) { return figures.contains(figure); }

    public boolean hasFigure(String id) {
        for (Figure f: figures)
            if (f.id.equals(id))
                return true;
        return false;
    }

    public Figure addFigure(Figure figure) { figures.add(figure); return figure; }

    public boolean removeFigure(Figure figure) { return figures.remove(figure); }
    public boolean removeFigure(String id) { return figures.removeIf(f -> f.id.equals(id)); }

    public void removeAllFigures() { figures.clear(); }

    public ArrayList<Figure> cloneFiguresList() { return new ArrayList<>(figures); }
}
