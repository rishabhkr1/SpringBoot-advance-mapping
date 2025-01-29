package com.daytona.cruddemo.dao;

import com.daytona.cruddemo.entity.Instructor;
import com.daytona.cruddemo.entity.InstructorDetail;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

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
}
