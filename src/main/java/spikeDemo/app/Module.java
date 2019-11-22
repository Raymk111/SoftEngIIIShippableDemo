package spikeDemo.app;
import java.util.ArrayList;

public class Module
{
	//module ids are a little simpler
	private long moduleIdIncrementer = 0;
	final private long moduleId;
	final private String moduleName;
	private ArrayList<Student> students;
	
	public Module(String moduleName, ArrayList<Student> initialStudents)
	{
		this.moduleId = moduleIdIncrementer;
		moduleIdIncrementer++;
		this.moduleName = moduleName;
		this.students = initialStudents;
	}
	
	//ewww
	public Module(String moduleName)
	{
		this.moduleId = moduleIdIncrementer;
		moduleIdIncrementer++;
		this.moduleName = moduleName;
		this.students = new ArrayList<Student>(0);
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

	public long getModuleId() {
		return moduleId;
	}

	public String getModuleName() {
		return moduleName;
	}
}
