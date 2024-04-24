package com.example.se.qlsv.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import com.example.se.qlsv.enumric.SectionClassStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class SectionClass implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String sectionClassNamePresent;
	private int maxStudent;
	private Date startDate;
	private Date endDate;
	private Date createdAt;
	@Enumerated(EnumType.STRING)
	private SectionClassStatus lockStatus;
	
	@ToString.Exclude
	@OneToMany(mappedBy = "sectionClass")
	@JsonManagedReference
	private Set<RegistrationSectionClass> registrationSectionClasses; // ✔️
	
	@ManyToOne
	@JoinColumn(name = "teacher_id")
	@JsonBackReference
	private Teacher teacher; // ✔️
	
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "subject_id")
	private Subject subject; // ✔️
	
	@ToString.Exclude
	@OneToMany(mappedBy = "sectionClass")
	@JsonManagedReference
	private Set<TimeTable> timeTables;
}
