package com.umiomikket.chessgame.vanilla.events;

import com.umiomikket.chessgame.chess.BoardCell;
import com.umiomikket.chessgame.chess.Team;

public class EventTeamSwitched extends EventFigureMoved {
    public final Team newTeam;
    public final Team oldTeam;

    public EventTeamSwitched(BoardCell figureCell, Integer[] move) {
        super(figureCell, move);
        newTeam = figureCell.getTeam();
        oldTeam = newTeam == Team.WHITE? Team.BLACK : Team.WHITE;
    }

    public Team getNewTeam() { return newTeam; }
    public Team getOldTeam() { return oldTeam; }
}
