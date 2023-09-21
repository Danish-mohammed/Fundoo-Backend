package com.bridgelabz.notes.entities;

import java.time.LocalDate;

import com.bridgelabz.notes.payload.NotesDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class Notes {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer Id;

	private String Title;
	
	@Column(length = 1000)
	private String Description;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate date;

	private boolean archived;

//	private boolean deleted;

	private boolean trash;

	@ManyToOne
	private User user;


	public Notes(NotesDto notesDto,User user) {
		this.Title = notesDto.getTitle();
		this.Description = notesDto.getDescription();
		this.date = LocalDate.now();
		this.user = user;
	}
}
