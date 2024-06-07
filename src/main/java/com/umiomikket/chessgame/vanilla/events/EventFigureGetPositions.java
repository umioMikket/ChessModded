package com.umiomikket.chessgame.vanilla.events;

import com.umiomikket.chessgame.ChessGame;
import com.umiomikket.chessgame.chess.*;

import java.util.ArrayList;

public class EventFigureGetPositions extends Event {
    public final BoardCell cell;

    public EventFigureGetPositions(BoardCell cell) {
        super(ChessGame.getModificationsManager().getModification("vanilla"), "figureGetPositions");
        this.cell = cell;
    }

    public BoardCell getCell() { return cell; }

    public int getFigureX() { return cell.x; }
    public int getFigureY() { return cell.y; }

    public Figure getFigure() { return cell.getFigure(); }
    public Modification getFigureMod() { return cell.getFigure().mod; }

    public String getFigureModId() { return cell.getFigure().mod.id; }
    public String getFigureId() { return cell.getFigure().id; }

    public BoardCell setFigure(Figure figure) { return cell.setFigure(figure); }

    public Team getFigureTeam() { return cell.getTeam(); }
    public BoardCell setFigureTeam(Team team) { return cell.setTeam(team); }

    public ArrayList<Integer[]> getFigurePositions() {
        return ChessGame.getSelectedFigurePositions();
    }

    public void setFigurePositions(ArrayList<Integer[]> positions) {
        ChessGame.setSelectedFigurePositions(positions);
    }
}
