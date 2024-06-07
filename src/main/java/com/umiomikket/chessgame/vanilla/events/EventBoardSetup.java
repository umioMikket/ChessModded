package com.umiomikket.chessgame.vanilla.events;

import com.umiomikket.chessgame.ChessGame;
import com.umiomikket.chessgame.chess.Board;
import com.umiomikket.chessgame.chess.Event;

public class EventBoardSetup extends Event {
    private Board board;

    public EventBoardSetup() {
        super(ChessGame.getModificationsManager().getModification("vanilla"), "boardSetup");
    }

    public Board getBoard() { return board; }
    public void setBoard(Board board) {
        this.board = board;
    }
}
