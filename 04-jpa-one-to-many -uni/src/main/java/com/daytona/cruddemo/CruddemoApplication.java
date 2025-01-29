package com.daytona.cruddemo;

import com.daytona.cruddemo.dao.AppDAO;
import com.daytona.cruddemo.entity.Course;
import com.daytona.cruddemo.entity.Instructor;
import com.daytona.cruddemo.entity.InstructorDetail;
import com.daytona.cruddemo.entity.Review;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO){
		return runner->{

			//createCourseAndReview(appDAO);
			//retriveCourseAndReview(appDAO);
			deleteCourseAndReviewById(appDAO);


		};
	}

	private void deleteCourseAndReviewById(AppDAO appDAO) {
		int theId=10;
		System.out.println("Deleting course id: "+theId);
		appDAO.deleteCourseById(theId);
		System.out.println("Done");
	}

	private void retriveCourseAndReview(AppDAO appDAO) {
		int theId=10;
		//get the course and reviews
		Course tempCourse=appDAO.findCourseAndReviewByCourseId(theId);

		//print the course
		System.out.println(tempCourse);

		//print the reviews
		System.out.println(tempCourse.getReviews());
		System.out.println("Done");

	}

	private void createCourseAndReview(AppDAO appDAO) {
		//create a course
		Course tempCourse=new Course("How to become millionaire");

		//add review
		tempCourse.addReview(new Review("Great Course after this course i become millionaire"));
		tempCourse.addReview(new Review("Well Done!"));
		tempCourse.addReview(new Review("Fake course after this course i have lost my all money"));

		//save the course
		System.out.println("saving the course: "+tempCourse);
		System.out.println(tempCourse.getReviews());
		appDAO.save(tempCourse);
		System.out.println("Done");

	}

	private void deleteCourse(AppDAO appDAO) {
		int theId=10;

		System.out.println("deleting course id: "+theId);

		appDAO.deleteCourseById(theId);

		System.out.println("Done");
	}

	private void updateCourse(AppDAO appDAO) {
		int theId=10;
		//finding the course
		System.out.println("Finding course id: "+theId);
		Course tempCourse=appDAO.findCourseById(theId);

		//updating the course
		System.out.println("updating course id: "+theId);
		tempCourse.setTitle("Battle-Ground");
		appDAO.update(tempCourse);
		System.out.println("Done!");
	}

	private void updateInstructor(AppDAO appDAO) {
		int theId=1;

		//find the instructor
		System.out.println("Finding instructor id: "+theId);
		Instructor tempInstructor=appDAO.findInstructorById(theId);

		//updating the instructor
		System.out.println("updating instructor id: "+theId);
		tempInstructor.setLastName("Maria");
		appDAO.update(tempInstructor);
		System.out.println("Done");
	}

	private void findInstructorWithCoursesJoinFetch(AppDAO appDAO) {
		int theId=1;
		System.out.println("Finding Instructor id: "+theId);
		Instructor tempInstructor=appDAO.findInstructorByIdJoinFetch(theId);
		System.out.println("tempInstructor: "+tempInstructor);
		System.out.println("associated instructor: "+tempInstructor.getCourses());
		System.out.println("Done");
	}

	private void findCoursesForInstructor(AppDAO appDAO) {
		int theId=1;
		System.out.println("Finding instructor id: "+theId);
		Instructor tempInstructor=appDAO.findInstructorById(theId);
		System.out.println("temp instructor: "+tempInstructor);

		//Find courses for instructor
		System.out.println("Finding courses for instructor id: "+theId);
		List<Course>courses=appDAO.findCoursesByInstructorId(theId);

		//associate the objects
		tempInstructor.setCourses(courses);

		System.out.println("the associated courses: "+tempInstructor.getCourses());
		System.out.println("Done");
	}

	private void findInstructorWithCourses(AppDAO appDAO) {
		int theId=1;
		System.out.println("Finding instructor id: "+theId);
		Instructor tempInstructor=appDAO.findInstructorById(theId);
		System.out.println("temp instructor: "+tempInstructor);
		System.out.println("the associated courses: "+tempInstructor.getCourses());
		System.out.println("Done");
	}

	private void createInstructorWithCourses(AppDAO appDAO) {
		//create the instructor
		Instructor tempInstructor=
				new Instructor("Susan","tino","susan@mail.com");

		//create the instructor details
		InstructorDetail tempInstructorDetail=
				new InstructorDetail("www.youtube.com","Video Game");

		//associate the objects
		tempInstructor.setInstructorDetail(tempInstructorDetail);

		//Create some courses
		Course tempCourse1=new Course("Air-Guitar");
		Course tempCourse2=new Course("Mad-Max");

		//add courses to the instructor
		tempInstructor.add(tempCourse1);
		tempInstructor.add(tempCourse2);

		//save the instructor
		//Note: this will also SAVE the courses
		//because of CascadeType.persist
		System.out.println("Saving Instructor"+tempInstructor);
		System.out.println("The courses: "+tempInstructor.getCourses());
		appDAO.save(tempInstructor);

		System.out.println("Done");
	}

	private void deleteInstructorDetails(AppDAO appDAO) {
		int theId=3;
		System.out.println("Deleting instructor details: "+theId);
		appDAO.deleteInstructorDetailsById(theId);
		System.out.println("Done");
	}

	private void findInstructorDetail(AppDAO appDAO) {

		//get the instructor detail object
		int theId=2;
		InstructorDetail tempInstructorDetails=appDAO.findInstructorDetailById(theId);

		//print the instructor details
		System.out.println("temp instructor details: "+tempInstructorDetails);

		//print the associated instructor
		System.out.println("the associated instructor: "+tempInstructorDetails.getInstructor());
		System.out.println("Done");
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
		//create the instructor
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
