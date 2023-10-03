package com.bridgelabz.notes.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity
@Table(name = "note")
@Data
public class Notes implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "note_id")
	private Integer noteId;

	@Column(name = "note_name")
	private String title;

	@Column(name = "note_description")
	private String description;

	@Column(name = "note_reminder")
	private Date reminder;

	@Column(name = "note_color")
	@Pattern(regexp = "^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})|([0]{0})$", message = "Invalid color")
	private String color;

	@Column(name = "note_pin", columnDefinition = "boolean default false")
	private boolean isPin;

	@Column(name = "note_archive", columnDefinition = "boolean default false")
	private boolean isArchive;

	@Column(name = "note_trash", columnDefinition = "boolean default false")
	private boolean isTrash;

	@Column(name = "note_created_date")
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date createdDate;

	@Column(name = "note_updated_date")
	@Temporal(TemporalType.TIMESTAMP)
	@UpdateTimestamp
	private Date updatedDate;

	@Column(name = "user_id")
	@NotNull
	private int userId;

	@Column(name = "user_fname")
	private String userFname;

	@Column(name = "user_lname")
	private String userLname;

	@Column(name = "user_email")
	private String userEmail;

	@JsonIgnoreProperties(value = "notes")
	@ManyToMany(fetch = FetchType.LAZY)
//	@JoinTable(name = "note_labels", joinColumns = @JoinColumn(referencedColumnName = "note_id", name = "label_id"), inverseJoinColumns = @JoinColumn(name = "note_id", referencedColumnName = "label_id"))
	private List<Label> labels;

	@JsonIgnoreProperties(value = "notes")
	@ManyToMany(fetch = FetchType.LAZY)
	private List<Collaborator> collaborators;

}