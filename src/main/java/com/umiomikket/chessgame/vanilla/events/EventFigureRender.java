package com.umiomikket.chessgame.vanilla.events;

import com.umiomikket.chessgame.ChessGame;
import com.umiomikket.chessgame.ChessRender;
import com.umiomikket.chessgame.chess.BoardCell;
import com.umiomikket.chessgame.chess.Event;

import java.awt.*;

public class EventFigureRender extends Event {
    public final ChessRender chessRender;
    public final BoardCell cellRendering;
    public final int cellX, cellY;

    public final boolean isBeforeFigureRender;

    public EventFigureRender(ChessRender chessRender, BoardCell cellRendering, int cellX, int cellY, boolean isBeforeFigureRender) {
        super(ChessGame.getModificationsManager().getModification("vanilla"), "figureRender");
        this.chessRender = chessRender;
        this.cellRendering = cellRendering;
        this.cellX = cellX;
        this.cellY = cellY;
        this.isBeforeFigureRender = isBeforeFigureRender;
    }

    public ChessRender getChessRender() { return chessRender; }
    public BoardCell getCellRendering() { return cellRendering; }
    public int getCellX() { return cellX; }
    public int getCellY() { return cellY; }

    public int getCellSize() { return chessRender.getCellSize(); }
    public int getDrawBoardAtX() { return chessRender.getDrawAtX(); }
    public int getDrawBoardAtY() { return chessRender.getDrawAtY(); }
    public float getDrawBoardRuleFill() { return chessRender.getRuleBoardFill(); }

    public Graphics2D getGraphics2D() { return ChessGame.getRenderManager().getGraphics(); }

    public boolean isBeforeFigureRender() { return isBeforeFigureRender; }
}
