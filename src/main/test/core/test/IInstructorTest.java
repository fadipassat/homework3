package core.test;

import core.api.IInstructor;
import core.api.IStudent;
import core.api.IAdmin;
import core.api.impl.Instructor;
import core.api.impl.Student;
import core.api.impl.Admin;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class IInstructorTest {
	  private IInstructor instructor;
	    protected IAdmin admin = new Admin();
	    protected IStudent student = new Student();

	    @Before
	    public void setup(){
	        this.instructor = new Instructor();
	    }


	    @Test
	    public void InstructorAssigned1(){
	        String InstructorName = null;
	        this.admin.createClass("Test", 2017, "Instructor", 50);
	        this.student.registerForClass("Student", "Test", 2017);
	        InstructorName = this.admin.getClassInstructor("Test", 2017);
	        assertTrue(InstructorName.contentEquals("Instructor"));
	    }


	    @Test
	    public void InstructorAssigned2(){
	        String InstructorName = null;
	        this.admin.createClass("Test", 2017, "Instructor", 50);
	        this.student.registerForClass("Student", "Test", 2017);
	        InstructorName = this.admin.getClassInstructor("Test", 2017);
	        assertFalse(InstructorName.contentEquals("Fadi"));
	    }


	    @Test
	    public void assignHomework1(){
	        this.admin.createClass("Test", 2017, "Instructor", 50);
	        this.student.registerForClass("Student", "Test", 2017);
	        this.instructor.addHomework("Instructor", "Test", 2017, "hw3");
	        this.student.submitHomework("Student", "hw3", "Programming", "Test", 2017);
	        assertTrue(this.instructor.homeworkExists("Test", 2017, "hw3"));
	    }



	    @Test
	    public void assignHomework2(){
	        this.admin.createClass("Test", 2017, "Instructor", 50);
	        this.student.registerForClass("Student", "Test", 2017);
	        this.student.submitHomework("Student", "hw3", "Programming", "Test", 2017);
	        assertFalse(this.instructor.homeworkExists("Test", 2017, "hw3"));
	    }

	  
	    @Test
	    public void assignHomework3(){
	        this.admin.createClass("Test", 2017, "Instructor", 50);
	        this.student.registerForClass("Student", "Test", 2017);
	        this.instructor.addHomework("Instructor", "Test", 2017, "hw3");
	        assertFalse(this.instructor.homeworkExists("Test", 2019, "hw3"));
	    }


	    @Test
	    public void assignHomework4(){
	        this.admin.createClass("Test", 2017, "Instructor", 50);
	        this.student.registerForClass("Student", "Test", 2017);
	        this.instructor.addHomework("Instructor", "Test", 2017, "hw3");
	        assertFalse(this.instructor.homeworkExists("Test", 2017, "hw4"));
	    }


	    @Test
	    public void assignHomework5(){
	        this.student.registerForClass("Student", "Test", 2017);
	        this.instructor.addHomework("Instructor", "Test", 2017, "hw3");
	        this.student.submitHomework("Student", "hw3", "Programming", "Test", 2017);
	        assertFalse(this.instructor.homeworkExists("Test", 2017, "hw3"));
	    }


	    @Test
	    public void grade1(){
	        int grade = 0;
	        this.admin.createClass("Test", 2017, "Instructor", 50);
	        this.student.registerForClass("Student", "Test", 2017);
	        this.instructor.addHomework("Instructor", "Test", 2017, "hw3");
	        this.student.submitHomework("Student", "hw3", "Programming", "Test", 2017);
	        this.instructor.assignGrade("Instructor", "Test", 2017, "hw3", "Student", 99);
	        grade = this.instructor.getGrade("Test", 2017, "hw3", "Student");
	        assertTrue("grade can't be negative", grade >= 0);
	    }


	    @Test
	    public void grade2(){
	        int grade = 0;
	        this.admin.createClass("Test", 2017, "Instructor", 50);
	        this.student.registerForClass("Student", "Test", 2017);
	        this.instructor.addHomework("Instructor", "Test", 2017, "hw3");
	        this.student.submitHomework("Student", "hw3", "Programming", "Test", 2017);
	        this.instructor.assignGrade("Instructor", "Test", 2017, "hw3", "Student", -1);
	        grade = this.instructor.getGrade("Test", 2017, "hw3", "Student");
	        assertNull(grade);

	    }


	    @Test
	    public void grade3(){
	        this.admin.createClass("Test", 2017, "Instructor", 50);
	        this.student.registerForClass("Student", "Test", 2017);
	        this.student.submitHomework("Student", "hw3", "Programming", "Test", 2017);
	        this.instructor.assignGrade("Instructor", "Test", 2017, "hw3", "Student", 10);
	        assertNull(this.instructor.getGrade("Test", 2017, "hw3", "Student"));
	    }


	    @Test
	    public void grade4(){
	        this.admin.createClass("Test", 2017, "Instructor", 50);
	        this.student.registerForClass("Student", "Test", 2017);
	        this.instructor.addHomework("Instructor", "Test", 2017, "hw3");
	        this.student.submitHomework("Student", "hw3", "Programming", "Test", 2017);
	        this.instructor.assignGrade("Instructor", "Test", 2017, "hw3", "Student", 99);
	        assertNull(this.instructor.getGrade("Test", 2016, "hw3", "Student"));
	    }


	    @Test
	    public void grade5() {
	        this.admin.createClass("Test", 2017, "Instructor", 50);
	        this.student.registerForClass("Student", "Test", 2017);
	        this.instructor.addHomework("Instructor", "Test", 2018, "hw3");
	        this.student.submitHomework("Student", "hw3", "Programming", "Test", 2017);
	        this.instructor.assignGrade("Instructor", "Test", 2017, "hw3", "Student", 99);
	        assertNull(this.instructor.getGrade("Test", 2017, "hw3", "Student"));
	    }


	    @Test
	    public void grade6() {
	        this.admin.createClass("Test", 2017, "Instructor", 50);
	        this.student.registerForClass("Student", "Test", 2017);
	        this.instructor.addHomework("Instructor1", "Test", 2017, "hw3");
	        this.student.submitHomework("Student", "hw3", "Programming", "Test", 2017);
	        this.instructor.assignGrade("Instructor2", "Test", 2017, "hw3", "Student", 99);
	        assertNull(this.instructor.getGrade("Test", 2017, "hw3", "Student"));
	    }


	    @Test
	    public void grade7(){
	        this.admin.createClass("Test", 2017, "Instructor", 50);
	        this.instructor.addHomework("Instructor", "Test", 2017, "hw3");
	        this.student.submitHomework("Student", "hw3", "Programming", "Test", 2017);
	        this.instructor.assignGrade("Instructor", "Test", 2017, "hw3", "Student", 99);
	        assertNull(this.instructor.getGrade("Test", 2017, "hw3", "Student"));
	    }
}
