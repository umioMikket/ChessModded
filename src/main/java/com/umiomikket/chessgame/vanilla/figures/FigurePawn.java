package com.umiomikket.chessgame.vanilla.figures;

import com.umiomikket.chessgame.ChessGame;
import com.umiomikket.chessgame.chess.*;
import com.umiomikket.chessgame.vanilla.events.EventFigureGetPositions;
import com.umiomikket.chessgame.vanilla.events.EventFigureMoved;

import java.util.ArrayList;

public class FigurePawn extends Figure {
    private boolean isWhiteMoved2Y;
    private boolean isBlackMoved2Y;

    public FigurePawn() {
        super(ChessGame.getModificationsManager().getModification("vanilla"), "pawn");
        setSpriteBlackTeam("./assets/B_Pawn.png");
        setSpriteWhiteTeam("./assets/W_Pawn.png");
        getEventManager().addEventClass(this);

        isWhiteMoved2Y = false;
        isBlackMoved2Y = false;
    }

    @EventHandler
    public void getFigurePositions(EventFigureGetPositions event) {
        if (event.getFigure() != this) return;

        ArrayList<Integer[]> positions = new ArrayList<>();

        BoardCell cell = event.getCell();
        Board board = cell.board;
        int fx = cell.x, fy = cell.y;
        boolean isTeamWhite = cell.getTeam() == Team.WHITE;

        for (int y = 1; y < 3; y++) {
            if (y == 2 && event.getFigureTeam() == Team.WHITE && isWhiteMoved2Y) break;
            if (y == 2 && event.getFigureTeam() == Team.BLACK && isBlackMoved2Y) break;

            int figurePositionY = isTeamWhite? fy - y : fy + y;

            if (figurePositionY < 0 || figurePositionY >= board.height) break;
            if (board.getCell(fx, figurePositionY).getTeam() != null) break;

            positions.add(new Integer[] {fx, figurePositionY});
        }

        int figurePositionY = isTeamWhite? fy - 1 : fy + 1;

        if (!(isTeamWhite? fy - 1 < 0 : fy + 1 >= board.height))
        for (int x = -1; x < 2; x++) {
            if (x == 0) continue;
            if (fx + x < 0 || fx + x > board.width) continue;

            if (board.getCell(fx + x, figurePositionY).getTeam() == null ||
                board.getCell(fx + x, figurePositionY).getTeam() == cell.getTeam())
                continue;

            positions.add(new Integer[] {fx + x, figurePositionY});
        }

        event.setFigurePositions(positions);
    }

    @EventHandler
    public void figureMoved(EventFigureMoved event) {
        if (event.getFigure() != this) return;

        if (event.getMoveY() == 2) isWhiteMoved2Y = true;
        if (event.getMoveY() == -2) isBlackMoved2Y = true;
    }
}
