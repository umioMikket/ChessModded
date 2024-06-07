package com.umiomikket.chessgame.vanilla;

import com.umiomikket.chessgame.ChessGame;
import com.umiomikket.chessgame.chess.*;
import com.umiomikket.chessgame.vanilla.events.EventFigureGetPositions;
import com.umiomikket.chessgame.vanilla.events.EventTeamSwitched;
import com.umiomikket.chessgame.vanilla.figures.*;
import com.umiomikket.chessgame.vanilla.menus.ScreenEndGame;
import com.umiomikket.chessgame.vanilla.menus.ScreenStatistic;
import com.umiomikket.chessgame.vanilla.particles.ParticleEaten;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Vanilla extends Modification {
    public final ParticleManager particleManager;

    public final FigurePawn figurePawn;
    public final FigureRook figureRook;
    public final FigureKnight figureKnight;
    public final FigureBishop figureBishop;
    public final FigureQueen figureQueen;
    public final FigureKing figureKing;

    public final ParticleEaten particleEaten;

    public final ScreenStatistic screenStatistic;
    public final ScreenEndGame screenEndGame;

    public Vanilla() {
        super("vanilla");

        particleManager = new ParticleManager();

        figurePawn = (FigurePawn) addFigure(new FigurePawn());
        figureRook = (FigureRook) addFigure(new FigureRook());
        figureKnight = (FigureKnight) addFigure(new FigureKnight());
        figureBishop = (FigureBishop) addFigure(new FigureBishop());
        figureQueen = (FigureQueen) addFigure(new FigureQueen());
        figureKing = (FigureKing) addFigure(new FigureKing());

        particleEaten = new ParticleEaten();

        screenStatistic = new ScreenStatistic();
        screenEndGame = new ScreenEndGame();

        ChessGame.getEventManager().addEventClass(new VanillaBoard());
        ChessGame.getEventManager().addEventClass(this);
    }

    @EventHandler
    public void checkmateEvent(EventTeamSwitched event) {
        Board board = ChessGame.getBoard();

        BoardCell whiteKing = null, blackKing = null;

        for (int y = 0; y < board.height; y++)
        for (int x = 0; x < board.width; x++) {
            BoardCell cell = board.getCell(x, y);
            if (cell.getFigure() != figureKing) continue;
            if (cell.getTeam() == Team.WHITE) whiteKing = cell;
            else blackKing = cell;
        }

        if (whiteKing == null || blackKing == null) return;

        ArrayList<Integer[]> highlightPositions = new ArrayList<>();
        Team whoCreatedCheckmate = null;

        for (int y = 0; y < board.height; y++)
        for (int x = 0; x < board.width; x++) {
            BoardCell cell = board.getCell(x, y);
            if (cell.getFigure() == null) continue;

            EventFigureGetPositions fEvent = new EventFigureGetPositions(cell);
            ChessGame.getEventManager().playEvent(fEvent);

            for (Integer[] ps: fEvent.getFigurePositions()) {
                if (!((ps[0] == whiteKing.x && ps[1] == whiteKing.y) || (ps[0] == blackKing.x && ps[1] == blackKing.y))) continue;

                whoCreatedCheckmate = board.getCell(fEvent.cell.x, fEvent.cell.y).getTeam();
                highlightPositions.add(new Integer[]{fEvent.cell.x, fEvent.cell.y});
                highlightPositions.add(new Integer[] {ps[0], ps[1]});
                break;
            }
        }

        ChessGame.setHighlightFigurePositions(highlightPositions);

        if (highlightPositions.size() == 0 || whoCreatedCheckmate == null) return;
        if (whoCreatedCheckmate != event.newTeam) return;

        screenEndGame.setMessage(
            String.format(
                "Team '%s' has been checkmated! Won: %s team!",
                whoCreatedCheckmate == Team.WHITE? "black" : "white",
                whoCreatedCheckmate == Team.WHITE? "white" : "black"
            )
        );

        screenEndGame.show();
    }

    public ParticleManager getParticleManager() { return particleManager; }
    public static Vanilla getMod() { return (Vanilla) ChessGame.getModificationsManager().getModification("vanilla"); }
}
