package com.umiomikket.chessgame.vanilla.particles;

import com.umiomikket.chessgame.ChessGame;
import com.umiomikket.chessgame.ChessRenderTime;
import com.umiomikket.chessgame.chess.BoardCell;
import com.umiomikket.chessgame.chess.EventHandler;
import com.umiomikket.chessgame.vanilla.Particle;
import com.umiomikket.chessgame.vanilla.ParticleData;
import com.umiomikket.chessgame.vanilla.Vanilla;
import com.umiomikket.chessgame.vanilla.events.EventFigureEaten;
import com.umiomikket.chessgame.vanilla.events.EventRenderParticle;
import com.umiomikket.chessgame.vanilla.events.EventUpdateParticle;

import java.awt.*;

public class ParticleEaten extends Particle {
    public ParticleEaten() {
        super(ChessGame.getModificationsManager().getModification("vanilla"), "eaten");
        ChessGame.getEventManager().addEventClass(this);
    }

    public class ParticleEatenData extends ParticleData {
        private float rotation;
        private int updatesCount;

        public ParticleEatenData(Particle type, float x, float y, float rotation) {
            super(type, x, y);
            this.rotation = rotation;
        }

        public float getRotation() { return rotation; }
        public void setRotation(float value) { rotation = value; }

        public int getUpdatesCount() { return updatesCount; }
        public void setUpdatesCount(int value) { updatesCount = value; }
    }

    @EventHandler
    public void create(EventFigureEaten event) {
        if (event.getFigureEaten() == null) return;
        BoardCell cell = event.getCell();

        for (int i = 0; i < 30; i++)
        Vanilla.getMod().particleManager.createParticle(new ParticleEatenData(
            this,
            cell.x + 0.5f,
            cell.y + 0.5f,
            (float) Math.random() * 360
        ));
    }

    @EventHandler
    public void update(EventUpdateParticle event) {
        if (event.getParticle().type != this) return;
        ParticleEatenData data = (ParticleEatenData) event.getParticle();

        if (data.getUpdatesCount() >= 60) {
            Vanilla.getMod().particleManager.removeParticle(data);
            return;
        }

        data.setX(data.getX() + 0.01f * (float) Math.cos(data.getRotation()));
        data.setY(data.getY() + 0.01f * (float) Math.sin(data.getRotation()));

        data.setUpdatesCount(data.getUpdatesCount() + 1);
    }

    @EventHandler
    public void render(EventRenderParticle event) {
        if (event.particle.type != this) return;
        if (event.renderEvent.renderTime != ChessRenderTime.AFTER_BOARD_RENDER) return;
        ParticleEatenData data = (ParticleEatenData) event.getParticle();

        Graphics2D graphics = event.renderEvent.getGraphics2D();

        graphics.setColor(new Color(255, 255, 255, 255 - 255 / 60 * data.updatesCount));

        graphics.fillRect(
            (int) (event.renderEvent.getChessRenderClass().getDrawAtX() + event.getCellSize() * ((int) event.particle.getX()) + event.getCellSize() * (event.getParticle().getX() % 1)),
            (int) (event.renderEvent.getChessRenderClass().getDrawAtY() + event.getCellSize() * ((int) event.particle.getY()) + event.getCellSize() * (event.getParticle().getY() % 1)),
            event.getCellSize() / 5, 10
        );

        graphics.dispose();
    }
}
