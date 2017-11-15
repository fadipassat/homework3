package core.test;

import core.api.IStudent;
import core.api.IAdmin;
import core.api.impl.Admin;
import core.api.impl.Student;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class IAdminTest {
	 private IAdmin admin;
	    protected IStudent student = new Student();

	    @Before
	    public void setup() {
	        this.admin = new Admin();
	    }

	    @Test
	    public void createClassInCurYear() {
	        this.admin.createClass("Test", 2017, "Instructor", 15);
	        assertTrue(this.admin.classExists("Test", 2017));
	    }


	    @Test
	    public void twoDiffClasses(){
	        admin.createClass("Test1", 2017, "Instructor", 22);
	        admin.createClass("Test2", 2017, "Instructor", 22);
	        assertTrue(this.admin.classExists("Test1", 2017));

	    }


	    @Test
	    public void createClassInPast() {
	        this.admin.createClass("Test", 2016, "Instructor", 15);
	        assertFalse(this.admin.classExists("Test", 2016));
	    }


	    @Test
	    public void noClassExist(){
	        assertFalse(this.admin.classExists("Test", 2017));
	    }


	    @Test
	    public void twoSameClasses(){
	        admin.createClass("Test1", 2017, "Instructor", 22);
	        admin.createClass("Test1", 2016, "Instructor", 22);
	        assertFalse(this.admin.classExists("Test1", 2016));
	    }


	    @Test
	    public void moreSameClass(){
	        admin.createClass("Test1", 2017, "Instructor", 22);
	        admin.createClass("Test1", 2017, "Instructor", 22);
	        admin.createClass("Test1", 2017, "Instructor", 22);
	        assertFalse(this.admin.classExists("Test1", 2017));

	    }


	    @Test
	    public void capacity1(){
	        this.admin.createClass("Test", 2017, "Instructor", 0);
	        assertFalse(this.admin.classExists("Test", 2017));
	    }


	    @Test
	    public void capacity2(){
	        this.admin.createClass("Test", 2017, "Instructor", -1);
	        assertFalse(this.admin.classExists("Test", 2017));
	    }


	    @Test
	    public void capacity3(){
	        this.admin.createClass("Test", 2017, "Instructor", 2);
	        assertTrue(this.admin.classExists("Test", 2017));
	    }


	    
	    @Test
	    public void enrolled(){
	        boolean answer = true;
	        this.admin.createClass("Test", 2017, "Instructor", 15);
	        this.student.registerForClass("Student1", "Test", 2017);
	        answer = this.student.isRegisteredFor("Student", "Test", 2017);
	        assertFalse(answer);
	    }


	    @Test
	    public void capacitymodified1(){
	        this.admin.createClass("Test", 2017, "Instructor", 1);
	        this.admin.changeCapacity("Test", 2017, 2);
	        this.student.registerForClass("Student1", "Test", 2017);
	        this.student.registerForClass("Student2", "Test", 2017);
	        assertEquals(2, this.admin.getClassCapacity("Test", 2017));
	    }


	    @Test
	    public void capacitymodified2(){
	        this.admin.createClass("Test", 2017, "Instructor", 1);
	        this.admin.changeCapacity("Test", 2017, 2);
	        this.student.registerForClass("Student1", "Test", 2017);
	        this.student.registerForClass("Student2", "Test", 2017);
	        this.student.registerForClass("Student3", "Test", 2017);
	        assertEquals(3, this.admin.getClassCapacity("Test", 2017));
	    }


	    @Test
	    public void capacitymodified3(){
	        this.admin.createClass("Test", 2017, "Instructor", 2);
	        this.student.registerForClass("Student1", "Test", 2017);
	        this.student.registerForClass("Student2", "Test", 2017);
	        this.admin.changeCapacity("Test", 2017, 1);
	        assertEquals(2, this.admin.getClassCapacity("Test", 2017));
	    }
	    
	    @Test
	    public void instructorName(){
	        String Str = null;
	        this.admin.createClass("Test", 2017, "Instructor", 15);
	        Str = this.admin.getClassInstructor("Test", 2017);
	        assertNotNull(Str);
	        assertEquals(Str, "Instructor");
	    }


}
