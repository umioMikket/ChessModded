package com.umiomikket.chessgame;

import com.umiomikket.chessgame.chess.*;
import com.umiomikket.chessgame.vanilla.events.EventFigureEaten;
import com.umiomikket.chessgame.vanilla.events.EventFigureGetPositions;
import com.umiomikket.chessgame.vanilla.events.EventFigureMoved;
import com.umiomikket.chessgame.vanilla.events.EventTeamSwitched;
import com.umiomikket.crearengine.managers.InputManager;

import java.awt.event.MouseEvent;

public class ChessUpdate {
    private BoardCell selectedCell;
    private int keyCooldown;
    private int mouseCellX, mouseCellY;

    public ChessUpdate() {
        selectedCell = null;
        keyCooldown = 0;
    }

    public void update() {
        InputManager input = ChessGame.getInputManager();
        ChessRender render = ChessGame.getChessRender();
        Board board = ChessGame.getBoard();

        if (render.getCellSize() == 0) return;
        if (keyCooldown != 0) keyCooldown--;

        mouseCellX = (input.getMouseX() - render.getDrawAtX()) / render.getCellSize();
        mouseCellY = (input.getMouseY() - render.getDrawAtY()) / render.getCellSize();

        // Mouse controls
        if (!(input.isButton(MouseEvent.BUTTON1) || input.isButton(MouseEvent.BUTTON3))) return;
        if (ChessGame.isIgnoreBoardControl()) return;

        if (mouseCellX < 0 || mouseCellX >= board.width || mouseCellY < 0 || mouseCellY >= board.height) return;
        BoardCell cell = board.getCell(mouseCellX, mouseCellY);

        if (cell.getFigure() != null && ChessGame.getSelectedTeam() == cell.getTeam()) {
            ChessGame.setSelectedFigure(cell);
            ChessGame.getEventManager().playEvent(new EventFigureGetPositions(cell));
            return;
        }

        ChessUtils utils = new ChessUtils(ChessGame.getSelectedFigurePositions());

        if (utils.hasPosition(mouseCellX, mouseCellY)) {
            int figureMoveX = ChessGame.getSelectedCell().x - mouseCellX,
                figureMoveY = ChessGame.getSelectedCell().y - mouseCellY;

            ChessGame.getSelectedFigurePositions().clear();

            Figure oldFigure = cell.getFigure();

            cell.setFigure(ChessGame.getSelectedCell().getFigure(), ChessGame.getSelectedTeam());
            ChessGame.getSelectedCell().setFigure(null, null);
            ChessGame.setSelectedTeam(ChessGame.getSelectedTeam() == Team.WHITE? Team.BLACK : Team.WHITE);

            ChessGame.getEventManager().playEvent(new EventTeamSwitched(cell, new Integer[] {figureMoveX, figureMoveY}));
            ChessGame.getEventManager().playEvent(new EventFigureMoved(cell, new Integer[] {figureMoveX, figureMoveY}));
            ChessGame.getEventManager().playEvent(new EventFigureEaten(cell, oldFigure, cell.getTeam() == Team.WHITE? Team.BLACK : Team.WHITE));
        }
    }

    public BoardCell getSelectedCell() { return selectedCell; }

    public int getKeyCooldown() { return keyCooldown; }
    public void setKeyCooldown(int cooldown) { keyCooldown = cooldown; }
    public void setKeyCooldown() { keyCooldown = 10; }

    public int getMouseCellX() { return mouseCellX; }
    public int getMouseCellY() { return mouseCellY; }
}
