//package com.bridgelabz.notes.services.impl;
//
//import java.time.LocalDate;
//import java.util.List;
//import java.util.stream.Collectors;
//import com.bridgelabz.notes.services.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.ResourceAccessException;
//import com.bridgelabz.notes.entities.Notes;
//import com.bridgelabz.notes.entities.User;
//import com.bridgelabz.notes.payload.NotesDto;
//import com.bridgelabz.notes.payload.UserDto;
//import com.bridgelabz.notes.repositories.NotesRepo;
//import com.bridgelabz.notes.repositories.UserRepo;
//import com.bridgelabz.notes.services.NotesService;
//@Service
//public class NotesServiceImpl implements NotesService {
//
//	@Autowired
//	private NotesRepo notesRepo;
//
//	@Autowired
//	private UserRepo userRepo;
//
//	@Autowired
//	private UserService userService;
//
//	//new api for createNotes
//	@Override
//	public Notes createNote(NotesDto notesDto, String userId) {
//		Integer userIdAsInt = Integer.parseInt(userId);
//		User user = userRepo.findById(userIdAsInt)
//				.orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));
//		Notes notes = new Notes(notesDto,user);
//		System.out.println(notes);
//		return notesRepo.save(notes);
//	}
//
//	//update
//	@Override
//	public NotesDto updateNote(NotesDto notesDto, Integer notesId) {
//		Notes notes= this.notesRepo.findById(notesId).orElseThrow(()->new ResourceAccessException("not found"));
//		notesDto.setDate(LocalDate.now());
//		notes.setTitle(notesDto.getTitle());
//		notes.setDate(notesDto.getDate());
//		notes.setDescription(notesDto.getDescription());
//		Notes res=this.notesRepo.save(notes);
//		return this.NotesToDto(res);
//	}
//
//	// delete
//	@Override
//	public void deleteNote(Integer notesId) {
//		Notes notes=notesRepo.findById(notesId).orElseThrow();
//		this.notesRepo.delete(notes);
//	}
//
//	//get
//	@Override
//	public Notes getNote(Integer notesId) {
//        return (this.notesRepo.findById(notesId).orElseThrow());
//	}
//	//get by user
//	public List<Notes> getNoteByUser(Integer userId) {
//		User user=userService.getUserById(userId);
//		return notesRepo.findByUserId(user.getUser_id());
////		System.out.println(user);
////		List<NotesDto> allNotes= notes.stream().map((note)->this.NotesToDto(note)).collect(Collectors.toList());
////		return allNotes;
//	}
//
//	@Override
//	public Notes archiveNotes(int id) {
//		Notes note = getNote(id);
//		if (note !=null) {
//			note.setArchive(true);
//			return notesRepo.save(note);
//		}
//		return null;
//	}
//
//	@Override
//	public Notes unArchiveNotes(int id) {
//		Notes note = getNote(id);
//		if (note !=null) {
//			note.setArchive(false);
//			return notesRepo.save(note);
//		}
//		return null;
//	}
//
//	@Override
//	public Notes moveToTrash(int id) {
//		Notes note = getNote(id);
//		if (note != null) {
//			note.setTrash(true);
//			return notesRepo.save(note);
//		}
//		return null; // Note not found
//	}
//
//	@Override
//	public Notes restoreFromTrash(int id) {
//		Notes note = getNote(id);
//		if (note != null) {
//			note.setTrash(false);
//			return notesRepo.save(note);
//		}
//		return null; // Note not found
//	}
//
//	//get all
//	@Override
//	public List<NotesDto> getAllNote() {
//		List<Notes> notes = this.notesRepo.findAll();
//        return notes.stream().map(this::NotesToDto).collect(Collectors.toList());
//	}
//
//	//new code for NotesToDto
//	public NotesDto NotesToDto(Notes notes) {
//		NotesDto notesDto = new NotesDto();
////		notesDto.setID(notes.getId());
//		notesDto.setTitle(notes.getTitle());
//		notesDto.setDescription(notes.getDescription());
//		notesDto.setDate(notes.getDate());
//		// Map the User object to UserDto
//		if (notes.getUser() != null) {
//			UserDto userDto = new UserDto();
//			userDto.setId(notes.getUser().getId());
//			userDto.setFname(notes.getUser().getFname());
//			userDto.setLname(notes.getUser().getLname());
//			userDto.setEmail(notes.getUser().getEmail());
//			userDto.setPassword(notes.getUser().getPassword());
////			notesDto.setUserDto(userDto);
//		}
//		return notesDto;
//	}
//}
