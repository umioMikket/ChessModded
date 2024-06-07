package com.umiomikket.chessgame;

import com.umiomikket.chessgame.chess.*;
import com.umiomikket.chessgame.vanilla.Vanilla;
import com.umiomikket.chessgame.vanilla.events.EventFigureRender;
import com.umiomikket.chessgame.vanilla.events.EventRender;

import java.awt.*;

public class ChessRender {
    private float ruleBoardFill;

    private int cellSize, drawAtX, drawAtY;

    public ChessRender() {
        ruleBoardFill = 1.25f;
    }

    public void render(int frameNumber, boolean isEndFrame) {
        Vanilla vanilla = (Vanilla) ChessGame.getModificationsManager().getModification("vanilla");
        Container contentPane = ChessGame.getGameBox().window.frame.getContentPane();
        Graphics2D graphics = ChessGame.getGameBox().renderManager.getGraphics();
        Board board = ChessGame.getBoard();
        ChessUtils utilSFP = new ChessUtils(ChessGame.getSelectedFigurePositions());
        ChessUtils utilHFP = new ChessUtils(ChessGame.getHighlightFigurePositions());

        int width = contentPane.getWidth(), height = contentPane.getHeight();

        cellSize = width > height?
            (int) (height / ruleBoardFill / Math.max(board.width, board.height)) :
            (int) (width / ruleBoardFill / Math.max(board.width, board.height));

        drawAtX = (width - cellSize * board.width) / 2;
        drawAtY = (height - cellSize * board.height) / 2;

        // Board background render
        for (int y = 0; y < board.height; y++)
        for (int x = 0; x < board.width; x++) {
            graphics.setColor((x + y) % 2 == 0? new Color(89, 96, 112) : new Color(149, 161, 178));
            graphics.fillRect(drawAtX + cellSize * x, drawAtY + cellSize * y, cellSize, cellSize);

            if (board.getCell(x, y).getTeam() == ChessGame.getSelectedTeam()) {
                graphics.setColor(board.getCell(x, y) == ChessGame.getSelectedCell()?
                    new Color(63, 255, 0, 128) :
                    new Color(63, 255, 0, 26)
                );

                graphics.fillRect(drawAtX + cellSize * x, drawAtY + cellSize * y, cellSize, cellSize);
            } else if (
                board.getCell(x, y).getTeam() != ChessGame.getSelectedTeam() &&
                board.getCell(x, y).getTeam() != null
            ) {
                graphics.setColor(new Color(255, 0, 0, 26));
                graphics.fillRect(drawAtX + cellSize * x, drawAtY + cellSize * y, cellSize, cellSize);
            }

            if (utilHFP.hasPosition(x, y)) {
                graphics.setColor(new Color(255, 115, 0, 77));
            } else {
                if (utilSFP.positions == null || !utilSFP.hasPosition(x, y)) continue;
                if (board.getCell(x, y).getTeam() != ChessGame.getSelectedTeam() &&
                    board.getCell(x, y).getTeam() != null
                ) graphics.setColor(new Color(255, 0, 0, 64));
                else graphics.setColor(new Color(63, 255, 0, 64));
            }

            graphics.fillRect(drawAtX + cellSize * x, drawAtY + cellSize * y, cellSize, cellSize);
        }

        ChessGame.getEventManager().playEvent(
            new EventRender(ChessRenderTime.AFTER_BOARD_RENDER, frameNumber, isEndFrame)
        );

        // Board figures render
        for (int y = 0; y < board.height; y++)
        for (int x = 0; x < board.width; x++) {
            BoardCell cell = board.getCell(x, y);
            if (cell.getFigure() == null) continue;

            ChessGame.getEventManager().playEvent(new EventFigureRender(this, cell, x, y, true));

            if (cell.getFigure().isDefaultRender())
                graphics.drawImage(
                    cell.getTeam() == Team.BLACK ? cell.getFigure().getSpriteBlackTeam() : cell.getFigure().getSpriteWhiteTeam(),
                    drawAtX + cellSize * x, drawAtY + cellSize * (y - 1) - cellSize / 10,
                    cellSize, cellSize * 2,
                    null
                );

            ChessGame.getEventManager().playEvent(new EventFigureRender(this, cell, x, y, false));
        }

        ChessGame.getEventManager().playEvent(
            new EventRender(ChessRenderTime.AFTER_FIGURE_RENDER, frameNumber, isEndFrame)
        );

        graphics.dispose();
    }

    public float getRuleBoardFill() { return ruleBoardFill; }
    public void setRuleBoardFill(float ruleBoardFill) { this.ruleBoardFill = ruleBoardFill; }

    public int getCellSize() {return cellSize; }

    public int getDrawAtX() { return drawAtX; }
    public int getDrawAtY() { return drawAtY; }
}
