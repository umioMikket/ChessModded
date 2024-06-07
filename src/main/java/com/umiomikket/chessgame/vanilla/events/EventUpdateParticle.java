package com.umiomikket.chessgame.vanilla.events;

import com.umiomikket.chessgame.ChessGame;
import com.umiomikket.chessgame.chess.Event;
import com.umiomikket.chessgame.vanilla.Particle;
import com.umiomikket.chessgame.vanilla.ParticleData;

public class EventUpdateParticle extends Event {
    public final EventUpdate renderEvent;
    public final ParticleData particle;

    public EventUpdateParticle(EventUpdate renderEvent, ParticleData particle) {
        super(ChessGame.getModificationsManager().getModification("vanilla"), "updateParticle");
        this.renderEvent = renderEvent;
        this.particle = particle;
    }

    public EventUpdate getRenderEvent() { return renderEvent; }
    public ParticleData getParticle() { return particle; }
}
