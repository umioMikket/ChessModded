package com.umiomikket.chessgame.vanilla.menus;

import com.umiomikket.chessgame.ChessGame;
import com.umiomikket.chessgame.chess.EventHandler;
import com.umiomikket.chessgame.chess.Figure;
import com.umiomikket.chessgame.chess.Modification;
import com.umiomikket.chessgame.vanilla.events.EventRender;
import com.umiomikket.chessgame.vanilla.events.EventUpdate;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class ScreenStatistic {
    private ArrayList<String> statistics;
    private boolean isEnabled;
    private int endUpdateNumber, endFrameNumber;

    public ScreenStatistic() {
        statistics = new ArrayList<>();
        isEnabled = false;

        ChessGame.getEventManager().addEventClass(this);
    }

    @EventHandler
    public void update(EventUpdate event) {
        if (event.endUpdate) endUpdateNumber = event.updateNumber;

        if (event.getUpdateNumber() == 0) updateStatistics();
        if (!(ChessGame.getInputManager().isKey(KeyEvent.VK_F3))) return;
        if (event.getChessUpdateClass().getKeyCooldown() != 0) return;

        isEnabled = !isEnabled;

        event.getChessUpdateClass().setKeyCooldown();
    }

    @EventHandler
    public void render(EventRender event) {
        if (event.endFrame) endFrameNumber = event.frameNumber;

        Graphics2D graphics = event.getGraphics2D();

        if (!(isEnabled())) return;

        graphics.setColor(Color.WHITE);

        int drawAtY = ChessGame.getGameBox().window.frame.getContentPane().getHeight() - statistics.size() * 10;

        String[] copyStatistics = statistics.toArray(new String[0]).clone();

        for (int i = 0; i < copyStatistics.length; i++) {
            graphics.drawString(copyStatistics[i] != null? copyStatistics[i] : "ERROR WHILE DRAWING", 0, drawAtY + i * 10);
        }

        graphics.dispose();
    }

    public void updateStatistics() {
        statistics.clear();

        // GameBox statistics
        statistics.add("Statistics from GameBox (CrearEngine):");
        statistics.add(String.format("Updates statistics: endUpdateNumber[%s]", endUpdateNumber));
        statistics.add(String.format("Frames statistics: endRenderNumber[%s]", endFrameNumber));

        statistics.add("");
        // Figure statistics
        statistics.add("Registered figures:");
        for (Modification mod: ChessGame.getModificationsManager().cloneModificationsList())
        for (Figure figure: mod.cloneFiguresList())
            statistics.add(mod.id + ":" + figure.id);

        statistics.add("");

        // Board statistics
        statistics.add("Map stats:");
        statistics.add("Size: " + ChessGame.getBoard().width + "x" + ChessGame.getBoard().height);

        statistics.add("");
    }

    public boolean isEnabled() { return isEnabled; }
    public void setEnabled(boolean value) { isEnabled = value; }
}
