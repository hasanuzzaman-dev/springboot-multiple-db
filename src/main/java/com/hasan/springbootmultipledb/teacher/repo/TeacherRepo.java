package com.hasan.springbootmultipledb.teacher.repo;

import com.hasan.springbootmultipledb.teacher.entities.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepo extends JpaRepository<Teacher,Integer> {

    Teacher findByName(String name);
}
