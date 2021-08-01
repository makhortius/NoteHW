package com.example.notehw.utils;

import android.content.Context;
import android.widget.Toast;

import com.example.notehw.MainActivity;
import com.example.notehw.R;
import com.example.notehw.entities.Note;
import com.example.notehw.entities.Priority;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Utils {

    public static List<Note> getListOfNotes() {
        List<Note> notes = new ArrayList<>();

        notes.add(
                new Note(
                        "Что купить в магазине",
                        "Хлеб, молоко, арбуз",
                        new Date(1626999400000L),
                        Priority.HIGH
                )
        );

        notes.add(
                new Note(
                        "Код для восстановления",
                        "qwerty123",
                        new Date(1626398400000L),
                        Priority.NORMAL
                )
        );

        notes.add(
                new Note(
                        "Рецепт блинов",
                        "Молоко - 0,5 л\n" +
                                "Яйца - 3 шт.\n" +
                                "Масло растительное - 1 ст. ложка + для смазывания сковороды\n" +
                                "Мука - 250 г\n" +
                                "Сахар - 1 ст. ложка\n" +
                                "Соль - 1 щепотка\n" +
                                "Масло сливочное - 1 ст. ложка",
                        new Date(1626486600000L),
                        Priority.NORMAL
                )
        );

        notes.add(
                new Note(
                        "На завтра",
                        "Сделать дз, скачать утилиты",
                        new Date(1626761025000L),
                        Priority.HIGH
                )
        );

        return notes;
    }

    public static void showToastShort(Context context, String message) {
        showToast(context, message, Toast.LENGTH_SHORT);
    }

    public static void showToast(Context context, String message, int toastLength) {
        Toast.makeText(context, message, toastLength).show();
    }
}