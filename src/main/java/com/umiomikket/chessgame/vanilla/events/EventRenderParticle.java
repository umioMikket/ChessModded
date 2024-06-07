package com.umiomikket.chessgame.vanilla.events;

import com.umiomikket.chessgame.ChessGame;
import com.umiomikket.chessgame.chess.Event;
import com.umiomikket.chessgame.vanilla.ParticleData;

public class EventRenderParticle extends Event {
    public final EventRender renderEvent;
    public final ParticleData particle;

    public EventRenderParticle(EventRender renderEvent, ParticleData particle) {
        super(ChessGame.getModificationsManager().getModification("vanilla"), "renderParticle");
        this.renderEvent = renderEvent;
        this.particle = particle;
    }

    public EventRender getRenderEvent() { return renderEvent; }
    public ParticleData getParticle() { return particle; }

    public int getScreenX() {
        return (int) (ChessGame.getChessRender().getDrawAtX() + getCellSize() * ((int) particle.getX()));
    }

    public int getScreenY() {
        return (int) (ChessGame.getChessRender().getDrawAtX() + getCellSize() * ((int) particle.getY()));
    }

    public int getCellSize() {
        return ChessGame.getChessRender().getCellSize();
    }
}
