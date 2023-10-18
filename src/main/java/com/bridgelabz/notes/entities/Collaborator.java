package com.bridgelabz.notes.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "collaborator")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Collaborator implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "collab_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int collabId;

    @Column(name = "collab_created_date")
    private LocalDate createdDate;

    @Column(name = "collab_updated_date")
    private LocalDate updatedDate;

    @Column(name = "collab_user_id")
    @NotNull
    private int userId;

    @Column(name = "collab_user_email")
    @NotNull
    private String userEmail;

    @Column(name = "collab_user_fname")
    private String userFname;

    @Column(name = "collab_user_lname")
    private String userLname;

    @JsonIgnoreProperties(value = "collaborators")
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Notes> notes;
}
