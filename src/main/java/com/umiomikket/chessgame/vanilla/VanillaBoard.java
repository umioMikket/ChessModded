package com.umiomikket.chessgame.vanilla;

import com.umiomikket.chessgame.ChessGame;
import com.umiomikket.chessgame.chess.Board;
import com.umiomikket.chessgame.chess.EventHandler;
import com.umiomikket.chessgame.chess.Figure;
import com.umiomikket.chessgame.chess.Team;
import com.umiomikket.chessgame.vanilla.events.EventBoardSetup;

public class VanillaBoard {
    @EventHandler
    public void boardSetup(EventBoardSetup event) {
        Vanilla vanilla = (Vanilla) ChessGame.getModificationsManager().getModification("vanilla");
        Board board = new Board(8, 8);

        for (int x = 0; x < 8; x++) {
            board.setFigure(vanilla.figurePawn, Team.BLACK, x, 1);
            board.setFigure(vanilla.figurePawn, Team.WHITE, x, board.height - 2);
        }

        for (int x = 0; x < 8; x++) {
            board.setFigure(getFigureOfX(vanilla, x), Team.BLACK, x, 0);
            board.setFigure(getFigureOfX(vanilla, x), Team.WHITE, x, board.height - 1);
        }

        event.setBoard(board);
    }

    private Figure getFigureOfX(Vanilla vanillaMod, int x) {
        return switch (x) {
            case 0, 7 -> vanillaMod.figureRook;
            case 1, 6 -> vanillaMod.figureKnight;
            case 2, 5 -> vanillaMod.figureBishop;
            case 3 -> vanillaMod.figureQueen;
            case 4 -> vanillaMod.figureKing;
            default -> null;
        };
    }
}
