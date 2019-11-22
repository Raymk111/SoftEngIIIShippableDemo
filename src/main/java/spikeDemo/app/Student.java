package spikeDemo.app;
import java.time.LocalDate;
import java.time.Period;
import java.util.HashMap;;

public class Student
{
	//just following assumed format of CAO year concat with incremental id
	static private HashMap<Integer, String> idIncrementers = new HashMap<>(0);
	final private String name, uName;
	final private LocalDate dob;
	final private long id;
	final private boolean enrollable;
	
	public Student(String name, LocalDate dob)
	{
		//student enrollment constructor
		LocalDate now = LocalDate.now();
		if(name == null || dob == null)
		{
			this.dob = now;
			this.name = "";
			this.uName = "";
			this.id = 0;
			this.enrollable = false;
			return;
		}
		
		Period ageOfPerson = Period.between(dob, now);
		if(ageOfPerson.getYears() < 16)
		{

			this.dob = now;
			this.name = "";
			this.uName = "";
			this.id = 0;
			this.enrollable = false;
			return;
		}
		
		this.enrollable = true;
		this.dob = dob;
		this.name = name;
		if(!idIncrementers.containsKey(now.getYear()))
		{
			idIncrementers.put(now.getYear(), "000000");
		}		
		this.id = Long.parseLong(idIncrementers.get(LocalDate.now().getYear()));
		Long nextIDForYear = Long.parseLong(idIncrementers.get(now.getYear())) + 1;
		idIncrementers.put(LocalDate.now().getYear(), String.valueOf(nextIDForYear));
		this.uName = String.format("%s%d", name, ageOfPerson.getYears());
	}
	
	public String getUsername()
	{
		return uName;
	}

	public String getName() {
		return name;
	}

	public LocalDate getDob() {
		return dob;
	}

	public long getId() {
		return id;
	}

	public boolean isEnrollable() {
		return enrollable;
	}
}
