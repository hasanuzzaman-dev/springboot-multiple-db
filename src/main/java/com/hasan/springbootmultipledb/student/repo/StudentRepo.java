package com.hasan.springbootmultipledb.student.repo;

import com.hasan.springbootmultipledb.student.entities.Student;
import com.hasan.springbootmultipledb.teacher.entities.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepo extends JpaRepository<Student,Integer> {

    Student findByName(String name);
}
