package com.example.se.qlsv.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.se.qlsv.dto.LoginRequestDTO;
import com.example.se.qlsv.dto.LoginResponseDTO;
import com.example.se.qlsv.entity.Student;
import com.example.se.qlsv.entity.User;
import com.example.se.qlsv.enumric.RoleName;
import com.example.se.qlsv.security.JwtUltilities;
import com.example.se.qlsv.service.StudentService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private JwtUltilities jwtUltilities;

	@PostMapping("/login")
	public ResponseEntity<?> login(@Valid @RequestBody LoginRequestDTO loginRequestDTO) {
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDTO.getMssv(), loginRequestDTO.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		if(authorities.isEmpty()) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unrole user access");
		}
		Object[] userRoles = authorities.toArray();
		String userRole = userRoles[0].toString();
		RoleName userRoleName = RoleName.valueOf(userRole);
		String token = jwtUltilities.generateToken(loginRequestDTO.getMssv(), userRoleName);
		User principalForceToUser = (User) authentication.getPrincipal();;
		System.out.println("principalForceToUser=" + principalForceToUser.getId());
		Student student = studentService.findByUserId(principalForceToUser.getId());
		long studentId = student.getId();
		LoginResponseDTO loginResponseDTO = new LoginResponseDTO();
		loginResponseDTO.setStudentId(studentId);
		loginResponseDTO.setToken(token);
		return ResponseEntity.ok(loginResponseDTO);
	}
}
