package com.umiomikket.chessgame.vanilla.events;

import com.umiomikket.chessgame.chess.*;

public class EventBaseFigureCell extends Event {
    public final BoardCell figureCell;

    public EventBaseFigureCell(Modification mod, String id,  BoardCell figureCell) {
        super(mod, id);
        this.figureCell = figureCell;
    }

    public Board getBoard() { return figureCell.board; }
    public BoardCell getCell() { return figureCell; }
    public int getX() { return figureCell.x; }
    public int getY() { return figureCell.y; }

    public Figure getFigure() { return figureCell.getFigure(); }
    public BoardCell setFigure(Figure figure) { return figureCell.setFigure(figure); }

    public Team getTeam() { return figureCell.getTeam(); }
    public BoardCell setTeam(Team team) { return figureCell.setTeam(team); }

    public BoardCell set(Figure figure, Team team) { return figureCell.setFigure(figure, team); }
}
