package spikeDemo.app;
import java.util.ArrayList;

public class Module
{
	//module ids are a little simpler
	private long moduleIdIncrementer = 0;
	final private long moduleId;
	final private String moduleName;
	private ArrayList<Student> students;
	final private boolean validModule;
	
	public Module(String moduleName, ArrayList<Student> initialStudents)
	{
		if(moduleName == null || moduleName.isEmpty())
		{
			this.validModule = false;
			this.moduleId = 0;
			this.moduleName = "";
			return;
		}
		
		this.moduleId = moduleIdIncrementer;
		moduleIdIncrementer++;
		this.moduleName = moduleName;
		this.students = initialStudents == null ? new ArrayList<Student>(0) : initialStudents;
		this.validModule = true;
	}
	
	//ewww
	public Module(String moduleName)
	{
		
		this.moduleId = moduleIdIncrementer;
		moduleIdIncrementer++;
		this.moduleName = moduleName;
		this.students = new ArrayList<Student>(0);
		this.validModule = true;
	}
	
	public boolean removeStudent(Student s)
	{
		return students.remove(s);
	}
	
	public boolean enrollStudent(Student s)
	{
		if(!students.contains(s))
		{
			students.add(s);
			return true;
		}
		return false;
	}
	
	public ArrayList<Student> getStudentByName(String sName)
	{
		ArrayList<Student> studentsFound = new ArrayList<Student>(0);
		for(Student s : students)
		{
			if(s.getName().contains(sName))
			{
				studentsFound.add(s);
			}
		}
		return studentsFound;
	}

	public long getModuleId() {
		return moduleId;
	}

	public String getModuleName() {
		return moduleName;
	}

	public boolean isValidModule() {
		return validModule;
	}
}
