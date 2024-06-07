package com.umiomikket.chessgame.chess;

public class Board {
    private final BoardCell[] cells;
    public final int width, height;

    public Board(int width, int height) {
        this.width = width; this.height = height;
        cells = new BoardCell[width * height];

        fillBoard(null, null);
    }

    public void fillBoard(Figure figure, Team team) {
        for (int y = 0; y < height; y++)
        for (int x = 0; x < width; x++) {
            if (cells[width * y + x] == null) {
                cells[width * y + x] = new BoardCell(this, x, y, figure, team);
                continue;
            }

            cells[width * y + x].setFigure(figure, team);
        }
    }

    public BoardCell getCell(int x, int y) { return cells[width * y + x]; }

    public BoardCell setFigure(Figure figure, int x, int y) { return cells[width * y + x].setFigure(figure); }

    public BoardCell setFigure(Figure figure, Team team, int x, int y) {
        return cells[width * y + x].setFigure(figure, team);
    }

    public int getWidth() { return width; }
    public int getHeight() { return height; }
}
