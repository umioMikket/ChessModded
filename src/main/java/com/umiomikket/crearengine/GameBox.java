package com.umiomikket.crearengine;

import com.umiomikket.crearengine.managers.*;

public class GameBox {
    public final GameListener gameListener;

    public final InputManager inputManager;
    public final RenderManager renderManager;
    public final WindowManager windowManager;
    public final GameUpdateLoop updateLoop;
    public final GameRenderLoop renderLoop;
    public final GameWindow window;

    public GameBox(GameListener gameListener) {
        window = new GameWindow();

        this.gameListener = gameListener;

        inputManager = new InputManager(this);
        updateLoop = new GameUpdateLoop(this);
        renderManager = new RenderManager(this);
        windowManager = new WindowManager(this);
        renderLoop = new GameRenderLoop(this);
    }

    public void run() {
        playRun();

        if (!renderLoop.isRunning()) runRenderLoop();
        if (!updateLoop.isRunning()) runUpdateLoop();

        window.frame.setVisible(true);
    }

    public void stop() {
        if (renderLoop.isRunning()) renderLoop.stop();
        if (updateLoop.isRunning()) updateLoop.stop();
        window.frame.setVisible(false);

        playExit();
    }

    public void exit() {
        renderManager.dispose();
    }

    public void runUpdateLoop() { updateLoop.start(); }
    public void runRenderLoop() { renderLoop.start(); }
    public void stopUpdateLoop() { updateLoop.stop(); }
    public void stopRenderLoop() { renderLoop.stop(); }

    public void playUpdate(int frameNumber, boolean endFrame) {
        gameListener.update(this, frameNumber, endFrame);
    }

    public void playRender(int frameNumber, boolean endFrame) {
        gameListener.render(this, frameNumber, endFrame);
    }

    public void playRun() { gameListener.start(this); }
    public void playExit() { gameListener.exit(this); }
}
