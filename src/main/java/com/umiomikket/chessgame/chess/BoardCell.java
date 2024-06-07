package com.umiomikket.chessgame.chess;

public class BoardCell {
    public final Board board;
    public final int x, y;
    private Figure figure;
    private Team team;

    public BoardCell(Board board, int x, int y, Figure figure, Team team) {
        this.board = board;
        this.x = x;
        this.y = y;
        this.figure = figure;
        this.team = team;
    }

    public BoardCell(Board board, int x, int y) {
        this.board = board;
        this.x = x;
        this.y = y;
        this.figure = null;
        this.team = null;
    }

    public Board getBoard() { return board;}
    public int getX() { return x; }
    public int getY() { return y; }

    public Figure getFigure() { return figure; }
    public BoardCell setFigure(Figure figure) { this.figure = figure; return this; }

    public Team getTeam() { return team; }
    public BoardCell setTeam(Team team) { this.team = team; return this; }

    public BoardCell setFigure(Figure figure, Team team) {
        this.figure = figure;
        this.team = team;
        return this;
    }
}
