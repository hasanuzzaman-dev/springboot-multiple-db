package com.hasan.springbootmultipledb;

import com.hasan.springbootmultipledb.student.entities.Student;
import com.hasan.springbootmultipledb.student.repo.StudentRepo;
import com.hasan.springbootmultipledb.teacher.entities.Teacher;
import com.hasan.springbootmultipledb.teacher.repo.TeacherRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootMultipledbApplicationTests {

    @Test
    void contextLoads() {

    }

    @Autowired
    private StudentRepo studentRepo;
    @Autowired
    private TeacherRepo teacherRepo;

    @Test
    public void dbTest(){
        System.out.println("Testing");
        Student student = Student.builder()
                .name("Hasan")
                .age(30)
                .className("Masters")
                .build();

        Teacher teacher = Teacher.builder()
                .name("Ahsan")
                .age(50)
                .degree("PHD")
                .build();

        studentRepo.save(student);
        teacherRepo.save(teacher);

        System.out.println(studentRepo.save(student));
    }

}
