package com.umiomikket.chessgame.chess;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;

public class EventManager {
    public final HashMap<Method, Object> events;

    public EventManager() {
        events = new HashMap<>();
    }

    public void addEventClass(Object eventClass) {
        /*
        for (Method method: eventClass.getClass().getMethods()) {
            if (!(method.isAnnotationPresent(EventHandler.class) &&
                  Modifier.isStatic(method.getModifiers()) &&
                  method.getParameters().length == 1 &&
                  method.getParameters()[0].getType().isAssignableFrom(Event.class)
                  //Event.class.isAssignableFrom(method.getParameters()[0].getType())
            )) continue;

            eventMethods.add(method);
        }
        */

        for (Method method: eventClass.getClass().getMethods()) {
            if (!(method.isAnnotationPresent(EventHandler.class) &&
                  method.getParameters().length == 1 &&
                  Event.class.isAssignableFrom(method.getParameters()[0].getType())
            )) continue;

            events.put(method, eventClass);
        }
    }

    // TODO: Invoke methods without classes
    public void playEvent(Event event) {
        for (Method method: events.keySet()) {
            if (!(method.getParameters()[0].getType().isAssignableFrom(event.getClass()))) continue;

            try { method.invoke(events.get(method), event); }
            catch (Exception e) { e.printStackTrace(); System.exit(-1); }
        }
    }
}
