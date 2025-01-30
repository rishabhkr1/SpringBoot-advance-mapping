package com.daytona.cruddemo.dao;

import com.daytona.cruddemo.entity.Course;
import com.daytona.cruddemo.entity.Instructor;
import com.daytona.cruddemo.entity.InstructorDetail;
import com.daytona.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AppDAOImpl implements AppDAO{
    //define field for entityManager
    private EntityManager entityManager;

    //Inject the entityManager using constructor Injection
    public AppDAOImpl(EntityManager entityManager){
        this.entityManager=entityManager;
    }

    @Override
    @Transactional
    public void save(Instructor theInstructor) {
        entityManager.persist(theInstructor);
    }

    @Override
    public Instructor findInstructorById(int theId) {
        return entityManager.find(Instructor.class,theId);
    }

    @Override
    @Transactional
    public void deleteInstructorById(int theId) {
        //retrieve the instructor
        Instructor tempInstructor=entityManager.find(Instructor.class,theId);

        //get the courses
        List<Course> courses=tempInstructor.getCourses();

        //break the association of all courses for the instructor
        for(Course tempCourse: courses){
            tempCourse.setInstructor(null);
        }

        //delete the instructor
        entityManager.remove(tempInstructor);
    }

    @Override
    public InstructorDetail findInstructorDetailById(int theId) {
        return entityManager.find(InstructorDetail.class, theId);
    }

    @Override
    @Transactional
    public void deleteInstructorDetailsById(int theId) {
        //retrieve the instructor detail
        InstructorDetail tempInstructorDetails=entityManager.find(InstructorDetail.class,theId);

        //remove the associated object reference
        //remove bidirectional link
        tempInstructorDetails.getInstructor().setInstructorDetail(null);

        //delete the instructor detail
        entityManager.remove(tempInstructorDetails);
    }

    @Override
    public List<Course> findCoursesByInstructorId(int theId) {
        //create a query
        TypedQuery<Course> query=entityManager.createQuery("from Course where instructor.id= :data", Course.class);
        query.setParameter("data",theId);

        //Ececute Query
        List<Course> courses=query.getResultList();
        return courses;
    }

    @Override
    public Instructor findInstructorByIdJoinFetch(int theId) {
        //create a query
        TypedQuery<Instructor> query=entityManager.createQuery(
                "select i from Instructor i "+
                        "JOIN FETCH i.courses "+
                        "JOIN FETCH i.instructorDetail "+
                        "where i.id=:data",Instructor.class);
        query.setParameter("data",theId);
        Instructor instructor=query.getSingleResult();
        return instructor;
    }

    @Override
    @Transactional
    public void update(Instructor tempInstructor) {
        entityManager.merge(tempInstructor);
    }

    @Override
    @Transactional
    public void update(Course tempCourse) {
        entityManager.merge(tempCourse);
    }

    @Override
    public Course findCourseById(int theId) {
        return entityManager.find(Course.class,theId);
    }

    @Override
    @Transactional
    public void deleteCourseById(int theId) {
       Course tempCourse= entityManager.find(Course.class,theId);
       entityManager.remove(tempCourse);

    }

    @Override
    @Transactional
    public void save(Course theCourse) {
        entityManager.persist(theCourse);
    }

    @Override
    public Course findCourseAndReviewByCourseId(int theId) {
        //create a query
        TypedQuery<Course>query=entityManager.createQuery(
                "select c from Course c "+
                        "JOIN FETCH c.reviews "+
                        "where c.id=:data", Course.class);

        query.setParameter("data",theId);

        //execute a query
        Course course = query.getSingleResult();
        return course;
    }

    @Override
    public Course findCourseAndStudentsByCourseId(int theId) {
        //create a query
        TypedQuery<Course>query=entityManager.createQuery(
                "select c from Course c "+
                        "JOIN FETCH c.students "+
                        "where c.id= :data", Course.class);
        query.setParameter("data",theId);

        //execute a query
        Course course=query.getSingleResult();
        return course;
    }

    @Override
    public Student findStudentAndCoursesByStudentId(int theId) {
        TypedQuery<Student> query=entityManager.createQuery(
                "select s from Student s "+
                        "JOIN FETCH s.courses "+
                        "where s.id=:data",Student.class);

        query.setParameter("data",theId);
        Student student=query.getSingleResult();
        return student;
    }

    @Override
    @Transactional
    public void update(Student tempStudent) {
        entityManager.merge(tempStudent);
    }

    @Override
    @Transactional
    public void deleteStudentById(int theId) {
        Student tempStudent=entityManager.find(Student.class,theId);
        entityManager.remove(tempStudent);
    }
}
