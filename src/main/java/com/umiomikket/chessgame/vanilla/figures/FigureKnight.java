package com.umiomikket.chessgame.vanilla.figures;

import com.umiomikket.chessgame.ChessGame;
import com.umiomikket.chessgame.chess.*;
import com.umiomikket.chessgame.vanilla.events.EventFigureGetPositions;
import com.umiomikket.chessgame.vanilla.events.EventStatisticsGetWorkingTime;

import java.util.ArrayList;

public class FigureKnight extends Figure {
    private long howLongUpdate;

    public FigureKnight() {
        super(ChessGame.getModificationsManager().getModification("vanilla"), "knight");
        setSpriteBlackTeam("./assets/B_Knight.png");
        setSpriteWhiteTeam("./assets/W_Knight.png");

        howLongUpdate = 0l;

        addEventClass(this);
    }

    @EventHandler
    public void getFigurePositions(EventFigureGetPositions event) {
        if (event.getFigure() != this) return;
        long startTime = System.nanoTime();

        ArrayList<Integer[]> positions = new ArrayList<>();

        BoardCell cell = event.getCell();
        Board board = cell.board;
        int fx = cell.x, fy = cell.y;

        int[][] hasPositions = new int[][] {
            {-1, 2}, {1, 2}, {-1, -2}, {1, -2},
            {-2, 1}, {-2, -1}, {2, 1}, {2, -1},
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
        howLongUpdate = System.nanoTime() - startTime;
    }

    @EventHandler
    public void getWorkingTime(EventStatisticsGetWorkingTime event) {
        event.addWorkingTime("figureKnight > getFigurePositions", howLongUpdate);
    }
}
