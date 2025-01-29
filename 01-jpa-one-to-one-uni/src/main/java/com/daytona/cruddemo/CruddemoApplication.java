package com.daytona.cruddemo;

import com.daytona.cruddemo.dao.AppDAO;
import com.daytona.cruddemo.entity.Instructor;
import com.daytona.cruddemo.entity.InstructorDetail;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO){
		return runner->{
			//createInstructor(appDAO);
			//findInstructor(appDAO);
			deleteInstructor(appDAO);
		};
	}

	private void deleteInstructor(AppDAO appDAO) {
		int theId=1;
		System.out.println("Deleting the instructor id: "+theId);
		appDAO.deleteInstructorById(theId);
		System.out.println("Done");
	}

	private void findInstructor(AppDAO appDAO) {
		int theId=2;
		System.out.println("Finding instructor id: "+theId);
		Instructor tempInstructor=appDAO.findInstructorById(theId);
		System.out.println("tempInstructor: "+tempInstructor);
		System.out.println("the associated instructor details only: "+tempInstructor.getInstructorDetail());
	}

	private void createInstructor(AppDAO appDAO) {
		//create the instuctor
		Instructor tempInstructor=
				new Instructor("Rishi","Raj","rishi@mail.com");

		//create the instructor details
		InstructorDetail tempInstructorDetail=
				new InstructorDetail("www.youtube.com","Coding");

		//associate the objects
		tempInstructor.setInstructorDetail(tempInstructorDetail);

		//Save the instructor
		// NOTE : this will also save the object details
		// because of cascadeTyp.All

		System.out.println("Saving the instructor" +tempInstructor);
		appDAO.save(tempInstructor);

		System.out.println("Done!");

	}
}
