package com.example.se.qlsv.entity;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import com.example.se.qlsv.enumric.ClassGroup;
import com.example.se.qlsv.enumric.LessionDay;
import com.example.se.qlsv.enumric.LessionTime;
import com.example.se.qlsv.enumric.LessionType;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class TimeTable implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(nullable = false, columnDefinition = "DATE DEFAULT CURRENT_DATE")
	private Date lessionDate;
	@Enumerated(EnumType.STRING)
	private LessionTime startLesstionTime;
	@Enumerated(EnumType.STRING)
	private LessionTime endLesstionTime;
	@Enumerated(EnumType.STRING)
	private LessionType lessionType;
	@Enumerated(EnumType.STRING)
	private LessionDay lessionDay;
	@Enumerated(EnumType.STRING)
	private ClassGroup classGroup;
	private String room;
	private String note;
	
	@ManyToOne
	@JoinColumn(name = "section_class_id")
	@JsonBackReference
	private SectionClass sectionClass; // ✔️
}
