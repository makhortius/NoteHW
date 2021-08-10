package com.example.notehw.fragments;


import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.notehw.MainActivity;
import com.example.notehw.R;
import com.example.notehw.entities.Note;
import com.example.notehw.entities.Priority;
import com.example.notehw.utils.Observer;
import com.example.notehw.utils.Publisher;
import com.example.notehw.utils.Utils;

import java.util.Calendar;
import java.util.Date;

public class NoteFragment extends Fragment implements Observer {

    public static final String NOTE_FRAGMENT_TAG = "NOTE_FRAGMENT_TAG";

    private static final Calendar CALENDAR = Calendar.getInstance();

    private boolean isViewInitialized = false;
    private Note currentNote;

    private EditText titleEditText;
    private TextView createdAtTextView;
    private CheckBox priorityCheckBox;
    private EditText textEditText;

    public View.OnClickListener clickAlertDialog = new View.OnClickListener(){
        @Override
        public void onClick(View view) {

            AlertDialog.Builder builder = new AlertDialog.Builder(NoteFragment.this);

            builder.setTitle(R.string.exclamation)

                    .setMessage(R.string.press_button)
                    .setIcon(R.mipmap.ic_launcher_round)
                    .setCancelable(false)
                    .setPositiveButton(R.string.button,

                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    Toast.makeText(NoteFragment.this, "Кнопка нажата", Toast.LENGTH_SHORT).show();
                                }
                            });
            AlertDialog alert = builder.create();
            alert.show();
            Toast.makeText(NoteFragment.this, "Диалог открыт", Toast.LENGTH_SHORT).show();
        }
    };

    public static NoteFragment newInstance() {
        return new NoteFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_note, container, false);
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViews(view);

        isViewInitialized = true;

        if (currentNote != null) {
            updateNoteInfo();
        }
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.main_fragment, menu);

        MenuItem actionSort = menu.findItem(R.id.action_sort);
        actionSort.setVisible(false);

        MenuItem actionSearch = menu.findItem(R.id.action_search);
        actionSearch.setVisible(false);
    }

    private void initViews(@NonNull View rootView) {
        titleEditText = rootView.findViewById(R.id.note_title_edit_text);
        createdAtTextView = rootView.findViewById(R.id.note_created_at_text_view);
        priorityCheckBox = rootView.findViewById(R.id.note_priority_check_box);
        textEditText = rootView.findViewById(R.id.note_text_edit_text);
    }

    private void updateNoteInfo() {
        titleEditText.setText(currentNote.getTitle());
        textEditText.setText(currentNote.getText());
        priorityCheckBox.setChecked(currentNote.getPriority() == Priority.HIGH);
        createdAtTextView.setText(currentNote.getCreatedAtInFormat());

        initDatePicker(createdAtTextView);

    }

    private void initDatePicker(TextView createdAtTextView) {
        createdAtTextView.setOnClickListener(view -> {
            DatePickerDialog.OnDateSetListener dateSetListener = (datePicker, year, monthOfYear, dayOfMonth) -> {
                CALENDAR.set(year, monthOfYear, dayOfMonth);
                currentNote.setCreatedAt(new Date(CALENDAR.getTimeInMillis()));
                createdAtTextView.setText(currentNote.getCreatedAtInFormat());
            };

            CALENDAR.setTime(currentNote.getCreatedAt());

            DatePickerDialog dialog = new DatePickerDialog(
                    requireContext(),
                    dateSetListener,
                    CALENDAR.get(Calendar.YEAR),
                    CALENDAR.get(Calendar.MONTH),
                    CALENDAR.get(Calendar.DAY_OF_MONTH)
            );

            dialog.show();
        });
    }

    @Override
    public void updateNote(Note note) {
        currentNote = note;

        if (currentNote != null && isViewInitialized) {
            updateNoteInfo();
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.action_save ) {
            Utils.showToastShort(requireContext(), getString(R.string.not_implemented));
        }


        return super.onOptionsItemSelected(item);
    }



}