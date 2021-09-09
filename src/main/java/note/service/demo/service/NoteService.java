package note.service.demo.service;

import note.service.demo.model.Note;

import java.util.List;
import java.util.Optional;

public interface NoteService {

    Note add(Note note);

    List<Note> getAllNotes();

    void delete(int noteId);

    Optional<Note> getNote(int noteId);

    void update(int noteId, Note note);

}
