package spikeDemo.app;

import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
	class StudentTestCase {
		public String name;
		public LocalDate dob;
		public StudentFields expected;
		
		public StudentTestCase(String name, LocalDate dob, StudentFields expected)
		{
			this.name = name;
			this.dob = dob;
			this.expected = expected;
		}
	}
	
	class StudentFields
	{
		String name, uName;
		LocalDate dob;
		long id;
		boolean enrollable;
		
		public StudentFields(String name, String uName, LocalDate dob, long id, boolean enrollable)
		{
			this.name = name;
			this.uName = uName;
			this.dob = dob;
			this.id = id;
			this.enrollable = enrollable;
		}
		
	}

	@Test
	public void testStudentInitializations()
	{
		//simple class kind of like a struct for test cases inputs and expected outcomes
		StudentTestCase testCases[] = {	new StudentTestCase(null, null, new StudentFields("", "", LocalDate.now(), 0, false)),
										new StudentTestCase("", LocalDate.now().plusYears(15), new StudentFields("", "", LocalDate.now(), 0, false))
										};
		for(StudentTestCase t : testCases)
		{
			Student s = new Student(t.name, t.dob);
			assertTrue("test name", s.getName().equals(t.expected.name));
			assertTrue("test dob", s.getDob().toString().equals(t.expected.dob.toString()));
			assertTrue("test enrollable", s.isEnrollable() == t.expected.enrollable);
			assertTrue("test username", s.getUsername().equals(t.expected.uName));
			assertTrue("test id", String.valueOf(s.getId()).equals(String.valueOf(t.expected.id)));
		}
	}
}
