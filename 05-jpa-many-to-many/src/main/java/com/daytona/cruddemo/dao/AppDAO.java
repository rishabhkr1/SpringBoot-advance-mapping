package com.daytona.cruddemo.dao;

import com.daytona.cruddemo.entity.Course;
import com.daytona.cruddemo.entity.Instructor;
import com.daytona.cruddemo.entity.InstructorDetail;
import com.daytona.cruddemo.entity.Student;
import org.hibernate.usertype.CompositeUserType;

import java.util.List;

public interface AppDAO {
    void save(Instructor theInstructor);
    Instructor findInstructorById(int theId);
    void deleteInstructorById(int theId);
    InstructorDetail findInstructorDetailById(int theId);
    void deleteInstructorDetailsById(int theId);
    List<Course> findCoursesByInstructorId(int theId);
    Instructor findInstructorByIdJoinFetch(int theId);
    void update(Instructor tempInstructor);
    void update(Course tempCourse);
    Course findCourseById(int theId);
    void deleteCourseById(int theId);
    void save(Course theCourse);
    Course findCourseAndReviewByCourseId(int theId);
    Course findCourseAndStudentsByCourseId(int theId);
    Student findStudentAndCoursesByStudentId(int theId);
    void update(Student tempStudent);

    void deleteStudentById(int theId);


}
