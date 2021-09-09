package note.service.demo.controller.notes;

import note.service.demo.controller.users.UserController;
import note.service.demo.model.Note;
import note.service.demo.service.impls.NoteServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.text.ParseException;

import java.util.List;
import java.util.Optional;

@RestController
public class NoteController {

    private final NoteServiceImpl noteService;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    public NoteController(NoteServiceImpl noteServiceImpl){
        this.noteService = noteServiceImpl;
    }


    @PostMapping("/note")
    public ResponseEntity<Note> addNote(@RequestBody @NotNull Note note ) throws ParseException {

        System.out.println(note.getCreateTime());
        note = this.noteService.add(note);
        return ResponseEntity.ok(note);
    }




    @GetMapping("/notes/{noteId}")
    public ResponseEntity<Note> getNote(@PathVariable("noteId") int noteId){
        Optional<Note> noteEntity = this.noteService.getNote(noteId);
        if ((!noteEntity.isPresent())){
            logger.warn("note on this id does not exist");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(noteEntity.get());

    }


    @GetMapping("/notes")

    public ResponseEntity<List<Note>> getAllNotes(){
        return ResponseEntity.ok(this.noteService.getAllNotes());
    }

    @DeleteMapping("/notes/{noteId}")
    public ResponseEntity deleteByNoteId(@PathVariable("noteId") int noteId){
        Optional<Note> noteEntity =  this.noteService.getNote(noteId);
        if (!noteEntity.isPresent()){
            logger.warn("note on this id does not exist");
            return ResponseEntity.ok().build();
        }
        this.noteService.delete(noteId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update/{noteId}")
    public ResponseEntity<Note> updateNote(@PathVariable("noteId") int noteId, @RequestBody @NotNull Note note){
        this.noteService.update(noteId, note);
        logger.warn("such a note  does not exist");
        return ResponseEntity.ok().build();

    }


}
