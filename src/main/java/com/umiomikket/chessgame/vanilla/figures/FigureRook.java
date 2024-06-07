package com.umiomikket.chessgame.vanilla.figures;

import com.umiomikket.chessgame.ChessGame;
import com.umiomikket.chessgame.chess.*;
import com.umiomikket.chessgame.vanilla.events.EventFigureGetPositions;

import java.util.ArrayList;

public class FigureRook extends Figure {
    public FigureRook() {
        super(ChessGame.getModificationsManager().getModification("vanilla"), "rook");
        setSpriteBlackTeam("./assets/B_Rook.png");
        setSpriteWhiteTeam("./assets/W_Rook.png");

        addEventClass(this);
    }

    @EventHandler
    public void getFigurePositions(EventFigureGetPositions event) {
        if (event.getFigure() != this) return;

        ArrayList<Integer[]> positions = new ArrayList<>();
        BoardCell cell = event.getCell();
        Board board = cell.board;
        int cx = cell.x, cy = cell.y;

        int[][] directions = new int[][] {{0, -1}, {1, 0}, {0, 1}, {-1, 0}};
        for (int[] dir: directions)
        for (int i = 1; true; i++) {
            int px = cx + dir[0] * i;
            int py = cy + dir[1] * i;

            if (px < 0 || py < 0 || px >= board.width || py >= board.height) break;
            if (board.getCell(px, py).getTeam() == cell.getTeam()) break;

            positions.add(new Integer[] {px, py});

            if (board.getCell(px, py).getTeam() != null) break;
        }

        event.setFigurePositions(positions);
    }
}
