package com.daytona.cruddemo.dao;

import com.daytona.cruddemo.entity.Instructor;

public interface AppDAO {
    void save(Instructor theInstructor);

    Instructor findInstructorById(int theId);
    void deleteInstructorById(int theId);


}
