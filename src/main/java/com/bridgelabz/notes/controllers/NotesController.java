package com.bridgelabz.notes.controllers;

import java.util.List;

import com.bridgelabz.notes.entities.Notes;
import com.bridgelabz.notes.payload.ApiRes;
import com.bridgelabz.notes.payload.NotesDto;
import com.bridgelabz.notes.payload.ResponseDTO;
import com.bridgelabz.notes.services.NotesService;
import com.bridgelabz.notes.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notes")
@CrossOrigin
public class NotesController {

    @Autowired
    private NotesService notesService;

    @Autowired
    private UserService userService;

    //create
    @PostMapping("/{userId}")
    @CrossOrigin
    public ResponseEntity<ResponseDTO> createNote(@RequestBody NotesDto notes, @PathVariable String userId){
        Notes note =  this.notesService.createNote(notes, userId);
        ResponseDTO responseDTO = new ResponseDTO("Note created Successfully", note);
        return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.CREATED);
    }

//    //new api for create
//    @PostMapping("/{userId}")
//    public ResponseEntity<NotesDto> createNote(@RequestBody NotesDto notes, @PathVariable String userId) {
//        UserDto userDto = userService.getUserById(userId);
//        notes.setUserDto(userDto);
//        NotesDto note =  this.notesService.createNote(notes, userId);
//        return new ResponseEntity<>(note, HttpStatus.CREATED);
//    }


    //update
    @PutMapping("/{notesId}")
    public ResponseEntity<NotesDto> updateNote(@RequestBody NotesDto notes,@PathVariable Integer notesId){
        NotesDto updatedNote =  this.notesService.updateNote(notes, notesId);
        return new ResponseEntity<NotesDto>(updatedNote,HttpStatus.OK);
    }
    //delete
    @DeleteMapping("/{notesId}")
    public ResponseEntity<ApiRes> deleteNote(@PathVariable Integer notesId){
        this.notesService.deleteNote(notesId);
        return new ResponseEntity<ApiRes>(new ApiRes("Note deleted",true),HttpStatus.OK);
    }

    //get by user
    @GetMapping("/retriveByUser/{userId}")
    public ResponseEntity<List<Notes>> getNotesByUser(@PathVariable Integer userId){
        List<Notes> Note= notesService.getNoteByUser(userId);
        return new ResponseEntity<List<Notes>>(Note,HttpStatus.OK);
    }

    //get
     @GetMapping("/{notesId}")
     public ResponseEntity<Notes> getNotes(@PathVariable Integer notesId){
         Notes Note=this.notesService.getNote(notesId);
         return new ResponseEntity<Notes>(Note,HttpStatus.OK);
     }

    //getAll
    @GetMapping("/")
   @CrossOrigin
    public ResponseEntity<List<NotesDto>> getNotes(){
        List<NotesDto> Note=this.notesService.getAllNote();
        return new ResponseEntity<List<NotesDto>>(Note,HttpStatus.OK);
    }

    @PutMapping("/archive/{id}")
    public ResponseEntity<?> archiveNote(@PathVariable int id) {
        Notes note = notesService.archiveNotes(id);
        if (note != null) {
            return new ResponseEntity<>(note, HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Note not found with id: " + id);
        }
    }

    @PutMapping("/unarchive/{id}")
    public ResponseEntity<?> unArchiveNote(@PathVariable int id) {
        Notes note = notesService.unArchiveNotes(id);
        if (note != null) {
            return new ResponseEntity<>(note, HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Note not found with id: " + id);
        }
    }

    @PutMapping("/trash/{id}")
    public ResponseEntity<?> moveToTrash(@PathVariable int id) {
        Notes note = notesService.moveToTrash(id);

        if (note != null) {
            return new ResponseEntity<>(note, HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Note not found with id: " + id);
        }
    }

    @PutMapping("/restore/{id}")
    public ResponseEntity<?> restoreFromTrash(@PathVariable int id) {
        Notes note = notesService.restoreFromTrash(id);

        if (note != null) {
            return new ResponseEntity<>(note, HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Note not found with id: " + id);
        }
    }
}
