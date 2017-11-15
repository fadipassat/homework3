package core.test;

import core.api.IAdmin;
import core.api.IStudent;
import core.api.IInstructor;
import core.api.impl.Instructor;
import core.api.impl.Student;
import core.api.impl.Admin;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class IStudentTest {
	private IStudent student;
    protected IAdmin admin = new Admin();
    protected IInstructor instructor = new Instructor();

    @Before
    public void setup(){
        this.student = new Student();
    }

    
    @Test
    public void enrollmentCapacity(){
        Boolean bool = true;
        this.admin.createClass("Test", 2017, "Instructor", 2);
        this.student.registerForClass("Student1", "Test", 2017);
        this.student.registerForClass("Student2", "Test", 2017);
        this.student.registerForClass("Student3", "Test", 2017);
        bool = this.student.isRegisteredFor("Student3", "Test", 2017);
        assertFalse(bool);
    }


    @Test
    public void enrollmentCapacity2(){
        Boolean bool = true;
        this.admin.createClass("Test", 2017, "Instructor", 2);
        this.student.registerForClass("Student1", "Test", 2017);
        this.student.registerForClass("Student2", "Test", 2017);
        bool = this.student.isRegisteredFor("Student2", "Test", 2017);
        assertTrue(bool);
    }


    @Test
    public void dropClass1(){
        this.admin.createClass("Test", 2017, "Instructor", 2);
        this.student.registerForClass("Student", "Test", 2017);
        this.student.dropClass("Student", "Test", 2017);
        assertTrue(! this.student.isRegisteredFor("Student", "Test", 2017));
    }


    @Test
    public void dropClass2(){
        this.admin.createClass("Test", 2017, "Instructor", 2);
        this.student.registerForClass("Student", "Test", 2017);
        this.student.dropClass("Student", "Test", 2017);
        assertFalse(this.student.isRegisteredFor("Student", "Test", 2017));
    }


    @Test
    public void dropClass3(){
        this.admin.createClass("Test", 2017, "Instructor", 2);
        this.student.dropClass("Student", "Test", 2017);
        assertFalse(this.student.isRegisteredFor("Student", "Test", 2017));
    }

    @Test
    public void submitHW2(){
        this.admin.createClass("Test", 2017, "Instructor", 22);
        this.student.registerForClass("Student", "Test", 2017);
        this.instructor.addHomework("Instructor", "Test", 2017, "hw3");
        this.student.submitHomework("Student", "hw3", "Programming", "Test", 2017);
        assertTrue(this.student.hasSubmitted("Student", "hw3", "Test", 2017));
    }



    @Test
    public void submitHW3(){
        this.admin.createClass("Test", 2017, "Instructor", 22);
        this.student.registerForClass("Student", "Test", 2017);
        this.instructor.addHomework("Instructor", "Test", 2017, "hw3");
        this.student.submitHomework("Student", "hw3", "Programming", "Test", 2017);
        assertFalse(this.student.hasSubmitted("Student", "hw3", "Test", 2016));
    }


    @Test
    public void submitHW4(){
        this.admin.createClass("Test", 2017, "Instructor", 22);
        this.student.registerForClass("Student", "Test", 2017);
        this.student.submitHomework("Student", "hw3", "Programming", "Test", 2017);
        assertFalse(this.student.hasSubmitted("Student", "hw3", "Test", 2017));
    }


    @Test
    public void submitHW5(){
        this.admin.createClass("Test", 2017, "Instructor", 22);
        this.instructor.addHomework("Instructor", "Test", 2017, "hw3");
        this.student.submitHomework("Student", "hw3", "Programming", "Test", 2017);
        assertFalse(! this.student.hasSubmitted("Student", "hw3", "Test", 2017));
    }


    @Test
    public void submitHW6(){
        this.student.registerForClass("Student", "Test", 2017);
        this.instructor.addHomework("Instructor", "Test", 2017, "hw3");
        this.student.submitHomework("Student", "hw3", "Programming", "Test", 2017);
        assertFalse(this.student.hasSubmitted("Student", "hw3", "Test", 2017));
    }


    @Test
    public void submitHW7(){
        this.admin.createClass("Test", 2017, "Instructor", 22);
        this.student.registerForClass("Student", "Test", 2017);
        this.instructor.addHomework("Instructor", "Test", 2017, "hw3");
        assertFalse(this.student.hasSubmitted("Student", "hw3", "Test", 2017));
    }


    @Test
    public void submitHW8(){
        this.admin.createClass("Test", 2017, "Instructor", 22);
        this.student.registerForClass("Student", "Test", 2017);
        this.instructor.addHomework("Instructor", "Test", 2017, "hw3");
        this.student.submitHomework("Student", "hw3", "Programming", "Test", 2017);
        assertFalse(this.student.hasSubmitted("Student", "hw4", "Test", 2017));
    }
}
