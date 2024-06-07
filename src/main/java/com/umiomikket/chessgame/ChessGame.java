package com.umiomikket.chessgame;

import com.umiomikket.chessgame.chess.*;
import com.umiomikket.chessgame.vanilla.Vanilla;
import com.umiomikket.chessgame.vanilla.events.EventBoardSetup;
import com.umiomikket.chessgame.vanilla.events.EventRender;
import com.umiomikket.chessgame.vanilla.events.EventUpdate;
import com.umiomikket.crearengine.GameBox;
import com.umiomikket.crearengine.GameListener;
import com.umiomikket.crearengine.managers.InputManager;
import com.umiomikket.crearengine.managers.RenderManager;
import com.umiomikket.crearengine.utils.Logger;
import com.umiomikket.crearengine.utils.UtilJar;

import java.io.File;
import java.util.ArrayList;

public class ChessGame extends GameListener {
    private static GameBox gameBox;

    private static RenderManager renderManager;
    private static InputManager inputManager;

    private static File modsFolder;
    private static File gamesFolder;

    private static ModificationsManager modificationsManager;
    private static EventManager eventManager;

    private static Board board;

    private static ChessRender chessRender;
    private static ChessUpdate chessUpdate;

    private static Team selectedTeam;
    private static BoardCell selectedFigure;
    private static ArrayList<Integer[]> selectedFigurePositions;
    private static ArrayList<Integer[]> highlightFigurePositions;

    private static boolean ignoreBoardControl;

    public static void main(String[] args) {
        gameBox = new GameBox(new ChessGame());

        Logger.info("Starting game...");
        gameBox.run();
    }

    public void update(GameBox gameBox, int updateNumber, boolean endUpdate) {
        eventManager.playEvent(new EventUpdate(updateNumber, endUpdate, true));
        chessUpdate.update();
        eventManager.playEvent(new EventUpdate(updateNumber, endUpdate, false));
    }

    public void render(GameBox gameBox, int frameNumber, boolean endFrame) {
        eventManager.playEvent(new EventRender(ChessRenderTime.AFTER_START, frameNumber, endFrame));
        chessRender.render(frameNumber, endFrame);
        eventManager.playEvent(new EventRender(ChessRenderTime.BEFORE_END, frameNumber, endFrame));
    }

    public void start(GameBox gameBox) {
        Logger.info("Setup folders...");
        try {
            String path = UtilJar.getProgramPath();

            String fileSeparator = System.getProperty("file.separator");
            String modsFolderPath = path + fileSeparator + "mods"/* + fileSeparator*/;
            String gamesFolderPath = path + fileSeparator + "games"/* + fileSeparator*/;

            modsFolder = new File(modsFolderPath);
            if (!modsFolder.exists()) {
                modsFolder.mkdir();
                Logger.info("Folder 'mods' has created!");
            }

            gamesFolder = new File(gamesFolderPath);
            if (!gamesFolder.exists()) {
                gamesFolder.mkdir();
                Logger.info("Folder 'games' has created!");
            }
        } catch (Exception e) { e.printStackTrace(); }

        renderManager = ChessGame.getGameBox().renderManager;
        inputManager = ChessGame.getGameBox().inputManager;

        Logger.info("Loading modification and event manager...");
        modificationsManager = new ModificationsManager();
        eventManager = new EventManager();

        Logger.info("Starting setup mods...");
        modificationsManager.addModification(new Vanilla(), null);

        chessRender = new ChessRender();
        chessUpdate = new ChessUpdate();

        Logger.info("Setup game...");
        selectedTeam = Team.WHITE;
        selectedFigure = null;
        selectedFigurePositions = new ArrayList<>();
        highlightFigurePositions = new ArrayList<>();

        EventBoardSetup eventBoardSetup = new EventBoardSetup();
        eventManager.playEvent(eventBoardSetup);
        board = eventBoardSetup.getBoard();
        ignoreBoardControl = false;

        Logger.info("Showing window!");
        Logger.info("Hello chess game!");
    }

    public void exit(GameBox gameBox) {
        Logger.info("Flush memory of images...");

        for (Modification mod: modificationsManager.cloneModificationsList())
        for (Figure figure: mod.cloneFiguresList()) {
            figure.getSpriteWhiteTeam().flush();
            figure.getSpriteBlackTeam().flush();
            Logger.info(mod.id + ":" + figure.id + " flushed!");
        }

        Logger.info("Stop all threads!");
        gameBox.stopUpdateLoop();
        gameBox.stopRenderLoop();

        Logger.info("Bye chess game!");
        System.exit(0);
    }

    public static GameBox getGameBox() { return gameBox; }

    public static RenderManager getRenderManager() { return renderManager; }
    public static InputManager getInputManager() { return inputManager; }

    public static File getModsFolder() { return modsFolder; }
    public static File getGamesFolder() { return gamesFolder; }

    public static ModificationsManager getModificationsManager() { return modificationsManager; }
    public static EventManager getEventManager() { return eventManager; }

    public static Board getBoard() { return board; }

    public static ChessRender getChessRender() { return chessRender; }
    public static ChessUpdate getChessUpdate() { return chessUpdate; }

    public static Team getSelectedTeam() { return selectedTeam; }
    public static void setSelectedTeam(Team selectedTeam) { ChessGame.selectedTeam = selectedTeam; }

    public static BoardCell getSelectedCell() { return selectedFigure; }
    public static void setSelectedFigure(BoardCell figure) { selectedFigure = figure; }

    public static ArrayList<Integer[]> getSelectedFigurePositions() { return selectedFigurePositions; }
    public static void setSelectedFigurePositions(ArrayList<Integer[]> positions) { selectedFigurePositions = positions;}

    public static ArrayList<Integer[]> getHighlightFigurePositions() { return highlightFigurePositions; }
    public static void setHighlightFigurePositions(ArrayList<Integer[]> positions) { highlightFigurePositions = positions; }

    public static boolean isIgnoreBoardControl() { return ignoreBoardControl; }
    public static void setIgnoreBoardControl(boolean value) { ignoreBoardControl = value; }
}
