package com.goals.facultymanagementsystem.repository;

import com.goals.facultymanagementsystem.dto.QualificationDetails;
import com.goals.facultymanagementsystem.entity.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Long> {

    @Query("SELECT new com.goals.facultymanagementsystem.dto.QualificationDetails(f.id,q.degree,q.experience) FROM Faculty f JOIN f.qualification q WHERE f.id = ?1")
    QualificationDetails getFacultyByQualification(long id);
}