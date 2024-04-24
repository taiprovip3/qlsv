package com.example.se.qlsv.repository;

import java.util.Optional;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.se.qlsv.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	@Query("")
	Optional<User> findByMssv(String mssv);
}
