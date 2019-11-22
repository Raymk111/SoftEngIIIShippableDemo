package spikeDemo.app;

import static org.junit.Assert.assertTrue;

import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
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
	
	class ModuleFields
	{
		String moduleName;
		StudentFields[] studentsToAdd, studentsToSee;
		boolean isValid;
		
		public ModuleFields(String moduleName, StudentFields studentsToAdd[], StudentFields studentsToSee[], boolean isValid)
		{
			this.moduleName = moduleName;
			this.studentsToAdd = studentsToAdd;
			this.studentsToSee = studentsToSee;
			this.isValid = isValid;
		}
		
	}

	@Test
	public void testAStudentInitializations()
	{
		//simple class kind of like a struct for test cases inputs and expected outcomes
		StudentTestCase testCases[] = {	new StudentTestCase(null, null, new StudentFields("", "", LocalDate.now(), 0, false)),
										new StudentTestCase("", LocalDate.now().plusYears(15), new StudentFields("", "", LocalDate.now(), 0, false)),
										new StudentTestCase("Tom", LocalDate.now().plusYears(18), new StudentFields("Tom", "Tom18", LocalDate.now().plusYears(18), Long.parseLong(String.valueOf(LocalDate.now().getYear()) + "000000"), true)),
										new StudentTestCase("Tim", LocalDate.now().plusYears(18), new StudentFields("Tim", "Tim18", LocalDate.now().plusYears(18), Long.parseLong(String.valueOf(LocalDate.now().getYear()) + "000001"), true))
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
	
	@Test
	public void testBFullCourseStudentModeIntegration()
	{
		//simple class kind of like a struct for test cases inputs and expected outcomes
		StudentFields studentsIn[] = {	new StudentFields("", "", LocalDate.now(), 0, false),
										new StudentFields("", "", LocalDate.now(), 0, false),
										new StudentFields("Peter", "Peter19", LocalDate.now().plusYears(19), Long.parseLong(String.valueOf(LocalDate.now().getYear()) + "000002"), true),
										new StudentFields("Mike", "Mike19", LocalDate.now().plusYears(19), Long.parseLong(String.valueOf(LocalDate.now().getYear()) + "000003"), true)
							};
		StudentFields studentsOut[] = {	new StudentFields("Peter", "Peter19", LocalDate.now().plusYears(19), Long.parseLong(String.valueOf(LocalDate.now().getYear()) + "000002"), true),
										new StudentFields("Mike", "Mike18", LocalDate.now().plusYears(19), Long.parseLong(String.valueOf(LocalDate.now().getYear()) + "000003"), true)
							};
		ModuleFields modulesToMake[] = {	new ModuleFields("", null, null, false),
											new ModuleFields("CT217", null, null, true),
											new ModuleFields("CT211", studentsIn, studentsOut, true)
				};
		
		Course courseToTest = new Course("2BCT1");
		ArrayList<Long> sIds = new ArrayList<Long>();
		for(StudentFields s : studentsIn)
		{
			assertTrue("testing enrolling", s.enrollable == courseToTest.enrollStudent(s.name, s.dob.toString()));
			if(s.enrollable)
			{
				sIds.add(s.id);
			}
		}
		
		for(ModuleFields mf : modulesToMake)
		{
			assertTrue(courseToTest.addModule(mf.moduleName, sIds) == mf.isValid);
			ArrayList<Module> moduleHits = courseToTest.getModuleByName(mf.moduleName);
			if(!mf.moduleName.isEmpty())
			{
				Module moduleBackOut = moduleHits.get(0);
				assertTrue("test module name", moduleBackOut.getModuleName().equals(mf.moduleName));
				//testModule students
				if(mf.studentsToSee != null)
				{
					for(StudentFields s : mf.studentsToSee)
					{
						ArrayList<Student> studentHits = moduleBackOut.getStudentByName(s.name);
						assertTrue("test student in module", studentHits.size() > 0);
					}
				}
			}
		}
	}
}
