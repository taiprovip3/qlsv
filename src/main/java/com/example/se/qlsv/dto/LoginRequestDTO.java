package com.example.se.qlsv.dto;

import jakarta.validation.constraints.NotBlank;

public class LoginRequestDTO {
	
	@NotBlank
	private String mssv;
	@NotBlank
	private String password;
	
	public String getMssv() {
		return mssv;
	}
	public void setMssv(String mssv) {
		this.mssv = mssv;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
