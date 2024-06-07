package com.umiomikket.chessgame.vanilla.events;

import com.umiomikket.chessgame.ChessGame;
import com.umiomikket.chessgame.chess.BoardCell;
import com.umiomikket.chessgame.chess.Figure;
import com.umiomikket.chessgame.chess.Team;

public class EventFigureEaten extends EventBaseFigureCell {
    public final Figure figure;
    public final Team team;

    public EventFigureEaten(BoardCell figureEating, Figure figure, Team team) {
        super(ChessGame.getModificationsManager().getModification("vanilla"), "figureGetPositions", figureEating);
        this.figure = figure;
        this.team = team;
    }

    public Figure getFigureEaten() { return figure; }
    public Team getFigureEatenTeam() { return team; }
}
