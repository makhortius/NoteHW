package com.example.notehw.utils;

import com.example.notehw.entities.Note;

import java.util.HashSet;
import java.util.Set;

public class Publisher {
    private final Set<Observer> observers;

    public Publisher() {
        observers = new HashSet<>();
    }

    public void subscribe(Observer observer) {
        observers.add(observer);
    }

    public void unsubscribe(Observer observer) {
        observers.remove(observer);
    }

    public void notify(Note note) {
        for (Observer observer : observers) {
            observer.updateNote(note);
        }
    }
}