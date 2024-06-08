package com.umiomikket.chessgame.vanilla.events;

import com.umiomikket.chessgame.ChessGame;
import com.umiomikket.chessgame.chess.Event;

import java.util.HashMap;

public class EventStatisticsGetWorkingTime extends Event {
    private final HashMap<String, Long> timeStatistic;

    public EventStatisticsGetWorkingTime() {
        super(ChessGame.getModificationsManager().getModification("vanilla"), "statisticsGetWorkingTime");
        timeStatistic = new HashMap<>();
    }

    public void addWorkingTime(String id, long howLong) {
        timeStatistic.put(id, howLong);
    }

    public HashMap<String, Long> getTimeStatistic() { return timeStatistic; }
}
