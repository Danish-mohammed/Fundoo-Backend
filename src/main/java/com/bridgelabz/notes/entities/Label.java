package com.bridgelabz.notes.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Label implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "label_id")
    private int labelId;

    @NotNull
    @Column(name = "label_name")
    private String labelName;

    @NotNull
    @Column(name = "user_id")
    private int userId;

    @Column(name = "label_created_date")
    private LocalDate createdDate;

    @Column(name = "label_updated_date")
    private LocalDate updatedDate;

    @JsonIgnoreProperties(value = "labels")
    @ManyToMany(mappedBy = "labels", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Notes> notes;

}
