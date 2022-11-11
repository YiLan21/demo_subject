package com.example.demo_subject.respository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo_subject.entity.Subject;

@Repository
public interface SubjectDao extends JpaRepository<Subject, String> {

	public List<Subject> findAllBySubName(String subName);
	/*
	 * 自己要新增list findAllBySubName 的這方法 ; 所以要來Dao宣告 並寫輸入輸入值得型態
	 */
	public List<Subject> findAllBySubNumber(String subNumber);

	public Optional<Subject> findBySubNumber(String subNumber);
//	public List<Subject> findAllBySubNumber(List<String> subNumberList);
	// public Optional<Subject> findBySubNumber(List<String> subNumberList);

	public List<Subject> findAllBysubNumber(String subNumber);
}
