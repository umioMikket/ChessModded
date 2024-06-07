package com.umiomikket.chessgame.vanilla;

import com.umiomikket.chessgame.ChessGame;
import com.umiomikket.chessgame.chess.EventHandler;
import com.umiomikket.chessgame.vanilla.events.EventRender;
import com.umiomikket.chessgame.vanilla.events.EventRenderParticle;
import com.umiomikket.chessgame.vanilla.events.EventUpdate;
import com.umiomikket.chessgame.vanilla.events.EventUpdateParticle;

import java.util.ArrayList;

public class ParticleManager {
    private final ArrayList<ParticleData> particles;

    public ParticleManager() {
        particles = new ArrayList<>();
        ChessGame.getEventManager().addEventClass(this);
    }

    public ParticleData createParticle(ParticleData particle) {
        particles.add(particle);
        return particle;
    }

    public boolean removeParticle(ParticleData particle) {
        return particles.remove(particle);
    }

    @EventHandler
    public void updateParticles(EventUpdate event) {
        for (ParticleData p: new ArrayList<>(particles))
        ChessGame.getEventManager().playEvent(new EventUpdateParticle(event, p));
    }

    @EventHandler
    public void renderParticles(EventRender event) {
        for (ParticleData p: new ArrayList<>(particles)) {
            if (p == null) continue;
            ChessGame.getEventManager().playEvent(new EventRenderParticle(event, p));
        }
    }
}
