package com.example.notehw.listeners;

import com.example.notehw.entities.Note;

@FunctionalInterface
public interface OnItemSelectedListener {
    void onItemSelected(Note note);

}
