package com.example.se.qlsv.dto;

import com.example.se.qlsv.entity.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponseDTO {
	private long studentId;
	private String token;
}
