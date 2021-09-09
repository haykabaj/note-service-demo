package note.service.demo.service.impls;

import note.service.demo.controller.users.UserController;
import note.service.demo.model.Note;
import note.service.demo.repository.NoteRepository;
import note.service.demo.service.NoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class NoteServiceImpl implements NoteService {

    private final NoteRepository noteRepository;
    private static final Logger logger = LoggerFactory.getLogger(NoteServiceImpl.class);

    @Autowired
    public NoteServiceImpl(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @Override
    public Note add(Note note) {
        return this.noteRepository.save(note);
    }

    @Override
    public List<Note> getAllNotes() {
        return this.noteRepository.findAll();
    }

    @Override
    public void delete(int noteId) {
        this.noteRepository.deleteById(noteId);
    }

    @Override
    public Optional<Note> getNote(int noteId) {
        return this.noteRepository.findById(noteId);
    }

    @Override
    public void update(int noteId, Note note) {
        Note noteFromDB = this.noteRepository.getById(noteId);
        noteFromDB.setCreateTime(note.getCreateTime());
        noteFromDB.setLastUpdateTime(new Date());
        noteFromDB.setNote(note.getNote());
        noteFromDB.setTitle(note.getTitle());
        this.noteRepository.save(noteFromDB);
        logger.info("note updated successfully");
    }
}
