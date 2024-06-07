package com.umiomikket.chessgame.vanilla.events;

import com.umiomikket.chessgame.ChessGame;
import com.umiomikket.chessgame.ChessUpdate;
import com.umiomikket.chessgame.chess.Event;

public class EventUpdate extends Event {
    public final int updateNumber;
    public final boolean endUpdate;
    public final boolean beforeUpdate;

    public EventUpdate(int updateNumber, boolean endUpdate, boolean beforeUpdate) {
        super(ChessGame.getModificationsManager().getModification("vanilla"), "updateGame");
        this.updateNumber = updateNumber;
        this.endUpdate = endUpdate;
        this.beforeUpdate = beforeUpdate;
    }

    public int getUpdateNumber() { return updateNumber; }
    public boolean isEndUpdate() { return endUpdate; }

    public boolean isBeforeUpdate() { return beforeUpdate; }

    public ChessUpdate getChessUpdateClass() { return ChessGame.getChessUpdate(); }
}
