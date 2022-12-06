package com.example.demo_subject.respository;


import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo_subject.entity.Student;
@Repository
public interface StudentDao extends JpaRepository<Student, String >{

	public Optional<Student> findByStudentNumber(String studentNumber);
	
	public Optional<Student> findBySubjectNumber(String subjectNumber);
}
