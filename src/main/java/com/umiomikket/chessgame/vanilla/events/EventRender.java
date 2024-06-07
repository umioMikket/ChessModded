package com.umiomikket.chessgame.vanilla.events;

import com.umiomikket.chessgame.ChessGame;
import com.umiomikket.chessgame.ChessRender;
import com.umiomikket.chessgame.ChessRenderTime;
import com.umiomikket.chessgame.chess.Event;

import java.awt.*;

public class EventRender extends Event {
    public final ChessRenderTime renderTime;
    public final int frameNumber;
    public final boolean endFrame;

    public EventRender(ChessRenderTime renderTime, int frameNumber, boolean endFrame) {
        super(ChessGame.getModificationsManager().getModification("vanilla"), "renderGame");
        this.renderTime = renderTime;
        this.frameNumber = frameNumber;
        this.endFrame = endFrame;
    }

    public ChessRenderTime getRenderTime() { return renderTime; }

    public int getFrameNumber() { return frameNumber; }
    public boolean isEndFrame() { return endFrame; }

    public Graphics2D getGraphics2D() { return ChessGame.getRenderManager().getGraphics(); }

    public ChessRender getChessRenderClass() { return ChessGame.getChessRender(); }
}
