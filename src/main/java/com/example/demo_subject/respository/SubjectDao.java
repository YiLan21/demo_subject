package com.example.demo_subject.respository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo_subject.entity.Subject;

@Repository
public interface SubjectDao extends JpaRepository<Subject, String> {

	public List<Subject> findAllBySubjectName(String subjectName);

	public List<Subject> findAllBySubjectNumber(String subjectNumber);

	public Optional<Subject> findBySubjectNumber(String subjectNumber);

	public List<Subject> findAllBysubjectNumber(String subjectNumber);
}
