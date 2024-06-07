package com.umiomikket.chessgame.chess;

import org.json.JSONObject;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class ModificationsManager {
    private final ArrayList<Modification> modifications;
    private final ArrayList<Modification> informationModifications;

    public ModificationsManager() {
        this.modifications = new ArrayList<>();
        informationModifications = new ArrayList<>();
    }

    public boolean hasModification(Modification modification) { return modifications.contains(modification); }

    public boolean hasModification(String id) {
        for (Modification m: modifications)
            if (m.id.equals(id))
                return true;
        return false;
    }

    public Modification addModification(Modification modification, Path infoFile) {
        modifications.add(modification);
        //JSONObject json = new JSONObject(Files.readAllLines(infoFile.toAbsolutePath()));
        return modification;
    }

    public Modification getModification(String id) {
        for (Modification m: modifications)
            if (m.id.equals(id))
                return m;
        return null;
    }

    public boolean removeModification(Modification modification) { return modifications.remove(modification); }
    public boolean removeModification(String id) { return modifications.removeIf(m -> m.id.equals(id)); }

    public void removeAllModifications() { modifications.clear(); }

    public ArrayList<Modification> cloneModificationsList() { return new ArrayList<>(modifications); }
}
