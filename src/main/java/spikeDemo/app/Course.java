package spikeDemo.app;
import java.time.LocalDate;
import java.util.ArrayList;

public class Course
{
	final private String courseName;
	private ArrayList<Module> courseModules;
	private ArrayList<Student> studentsEnrolled;
	
	public Course(String courseName, ArrayList<Module> initialModules, ArrayList<Student> initialStudents)
	{
		this.courseName = courseName;
		this.courseModules = initialModules;
		this.studentsEnrolled = initialStudents;
	}
	
	public Course(String courseName, ArrayList<Module> initialModules)
	{
		this.courseName = courseName;
		this.courseModules = initialModules;
		this.studentsEnrolled = new ArrayList<Student>(0);
	}
	
	public Course(String courseName)
	{
		this.courseName = courseName;
		this.courseModules = new ArrayList<Module>(0);
		this.studentsEnrolled = new ArrayList<Student>(0);
	}
	
	public boolean addModule(String moduleName, ArrayList<Long> sIds)
	{
		if(moduleName == null || moduleName.isEmpty() || getModuleByName(moduleName).size() > 0)
		{
			return false;
		}
		
		ArrayList<Student> studentsFound = new ArrayList<Student>(0);
		for(Student s : studentsEnrolled)
		{
			for(long sId : sIds)
			{
				if(sId == s.getId())
				{
					studentsFound.add(s);
				}
			}
		}
		
		return courseModules.add(new Module(moduleName, studentsFound));
	}
	
	public ArrayList<Module> getModuleByName(String moduleName)
	{
		ArrayList<Module> modulesFound = new ArrayList<Module>(0);
		for(Module m : courseModules)
		{
			if(m.getModuleName().contains(moduleName))
			{
				modulesFound.add(m);
			}
		}
		return modulesFound;
	}
	
	public boolean removeModule(long moduleId)
	{
		for(Module m : courseModules)
		{
			if(m.getModuleId() == moduleId)
			{
				courseModules.remove(m);
				return true;
			}
		}
		return false;
	}
	
	public boolean enrollStudent(String sName, String dob)
	{
		Student s = new Student(sName, LocalDate.parse(dob));
		if(!s.isEnrollable())
		{
			return false;
		}
		return studentsEnrolled.add(s);
	}
	
	public ArrayList<Student> getStudentByName(String sName)
	{
		ArrayList<Student> studentsFound = new ArrayList<Student>(0);
		for(Student s : studentsEnrolled)
		{
			if(s.getName().contains(sName))
			{
				studentsFound.add(s);
			}
		}
		return studentsFound;
	}
	
	public boolean removeStudent(Student s)
	{
		for(Module m : courseModules)
		{
			m.removeStudent(s);
		}
		return studentsEnrolled.remove(s);
	}

	public String getCourseName()
	{
		return courseName;
	}
	
}
