package com.example.notehw.utils;
import com.example.notehw.entities.Note;

public interface Observer {
    void updateNote(Note note);
}