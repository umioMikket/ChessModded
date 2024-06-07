package com.umiomikket.chessgame.vanilla.menus;

import com.umiomikket.chessgame.ChessGame;
import com.umiomikket.chessgame.ChessRenderTime;
import com.umiomikket.chessgame.chess.Board;
import com.umiomikket.chessgame.chess.EventHandler;
import com.umiomikket.chessgame.vanilla.events.EventRender;

import java.awt.*;

public class ScreenEndGame {
    private boolean isVisible;
    private String message;

    public ScreenEndGame() {
        isVisible = false;
        message = "";

        ChessGame.getEventManager().addEventClass(this);
    }

    @EventHandler
    public void renderEndGameScreen(EventRender event) {
        if (!(ChessGame.isIgnoreBoardControl())) return;
        if (event.renderTime != ChessRenderTime.BEFORE_END) return;

        Board board = ChessGame.getBoard();
        Graphics2D graphics = ChessGame.getRenderManager().getGraphics();
        Container window = ChessGame.getGameBox().window.frame.getContentPane();

        int cellSize = ChessGame.getChessRender().getCellSize();
        int drawAtX = ChessGame.getChessRender().getDrawAtX(),
            drawAtY = ChessGame.getChessRender().getDrawAtY();

        graphics.setColor(new Color(0, 0, 0, 128));
        graphics.fillRect(0, 0, window.getWidth(), window.getHeight());

        graphics.setColor(Color.WHITE);
        graphics.drawString(message, 0, 10);

        graphics.dispose();
    }

    public boolean isVisible() { return isVisible; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public void show() {
        isVisible = true;
        ChessGame.setIgnoreBoardControl(true);
    }

    public void hide() {
        isVisible = false;
        ChessGame.setIgnoreBoardControl(false);
    }
}
