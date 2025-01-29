package com.daytona.cruddemo.dao;

import com.daytona.cruddemo.entity.Instructor;
import com.daytona.cruddemo.entity.InstructorDetail;

public interface AppDAO {
    void save(Instructor theInstructor);

    Instructor findInstructorById(int theId);
    void deleteInstructorById(int theId);

    InstructorDetail findInstructorDetailById(int theId);
    void deleteInstructorDetailsById(int theId);


}
