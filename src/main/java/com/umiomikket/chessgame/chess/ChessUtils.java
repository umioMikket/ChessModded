package com.umiomikket.chessgame.chess;

import java.util.ArrayList;

public class ChessUtils {
    public final ArrayList<Integer[]> positions;

    public ChessUtils(ArrayList<Integer[]> positions) {
        this.positions = positions;
    }

    public boolean hasPosition(int x, int y) {
        for (Integer[] p: positions)
            if (p[0] == x && p[1] == y)
                return true;
        return false;
    }

    public ArrayList<Integer[]> getPositions() { return positions; }
}
