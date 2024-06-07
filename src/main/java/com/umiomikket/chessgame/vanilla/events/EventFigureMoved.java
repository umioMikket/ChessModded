package com.umiomikket.chessgame.vanilla.events;

import com.umiomikket.chessgame.ChessGame;
import com.umiomikket.chessgame.chess.BoardCell;
import com.umiomikket.chessgame.chess.Event;
import com.umiomikket.chessgame.chess.Figure;
import com.umiomikket.chessgame.chess.Team;

public class EventFigureMoved extends Event {
    public final BoardCell figureCell;
    public final Integer[] move;

    public EventFigureMoved(BoardCell figureCell, Integer[] move) {
        super(ChessGame.getModificationsManager().getModification("vanilla"), "figureMoved");
        this.figureCell = figureCell;
        this.move = move;
    }

    public BoardCell getFigureCell() { return figureCell; }

    public Figure getFigure() { return figureCell.getFigure(); }
    public BoardCell setFigure(Figure figure) { return figureCell.setFigure(figure); }

    public Team getTeam() { return figureCell.getTeam(); }
    public BoardCell setTeam(Team team) { return figureCell.setTeam(team); }

    public BoardCell setFigure(Figure figure, Team team) { return figureCell.setFigure(figure, team); }

    public Integer[] getMove() { return move; }
    public int getMoveX() { return move[0]; }
    public int getMoveY() { return move[1]; }
}
