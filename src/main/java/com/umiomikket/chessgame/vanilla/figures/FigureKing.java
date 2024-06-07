package com.umiomikket.chessgame.vanilla.figures;

import com.umiomikket.chessgame.ChessGame;
import com.umiomikket.chessgame.chess.*;
import com.umiomikket.chessgame.vanilla.Vanilla;
import com.umiomikket.chessgame.vanilla.events.EventFigureEaten;
import com.umiomikket.chessgame.vanilla.events.EventFigureGetPositions;

import java.awt.*;
import java.util.ArrayList;

public class FigureKing extends Figure {
    private Team teamWin;

    public FigureKing() {
        super(ChessGame.getModificationsManager().getModification("vanilla"), "king");
        setSpriteBlackTeam("./assets/B_King.png");
        setSpriteWhiteTeam("./assets/W_King.png");

        addEventClass(this);
    }

    @EventHandler
    public void getFigurePositions(EventFigureGetPositions event) {
        if (event.getFigure() != this) return;

        ArrayList<Integer[]> positions = new ArrayList<>();

        BoardCell cell = event.getCell();
        Board board = cell.board;
        int fx = cell.x, fy = cell.y;

        int[][] hasPositions = new int[][] {
            {-1, 1}, {0, 1}, {1, 1}, {-1, 0},
            {1, 0}, {-1, -1}, {0, -1}, {1, 0}
        };

        for (int[] p: hasPositions) {
            if (fx + p[0] < 0 || fx + p[0] >= board.width ||
                fy + p[1] < 0 || fy + p[1] >= board.height)
                continue;

            if (board.getCell(fx + p[0], fy + p[1]).getTeam() == cell.getTeam())
                continue;

            positions.add(new Integer[] {fx + p[0], fy + p[1]});
        }

        event.setFigurePositions(positions);
    }

    @EventHandler
    public void kingEaten(EventFigureEaten event) {
        if (event.getFigureEaten() != this) return;

        ((Vanilla) getMod()).screenEndGame.setMessage(
            String.format(
                "The king of team '%s' was eaten! Winner: %s team! Idk how u do that...",
                event.getFigureEatenTeam() == Team.WHITE? Team.BLACK : Team.WHITE,
                event.getCell().getTeam() == Team.WHITE? Team.BLACK : Team.WHITE
            )
        );

        ((Vanilla) getMod()).screenEndGame.show();
    }
}
