package TrainTicket;

public class Passenger  {
	String name,preference,allotedBerth="NA";
	int age,seatNo=-1;
	
	//get methods
	public String getname()
	{
		return name;
	}
	public int getage()
	{
		return age;
	}
	public String getpreference()
	{
		return preference;
	}
	public String getallotedBerth()
	{
		return allotedBerth;
	}
	public int getseatNo()
	{
		return seatNo;
	}
	//set methods
	public void setname(String name)
	{
		this.name=name;
	}
	public void setage( int age)
	{
		this.age=age;
	}
	public void setallotedBerth(String allotedBerth)
	{
		this.allotedBerth=allotedBerth;
	}
	public void setpreference(String preference)
	{
		this.preference=preference;
	}
	public void setseatNo(int seatNo)
	{
		this.seatNo=seatNo;
	}
	
	
	
	public void setpassenger(String name,int age,String preference, int PNR,String status, String allotedBerth, int seatNo)
	{
		setname (name);
		setage(age);
		setpreference(preference);
		setallotedBerth(allotedBerth);
		setseatNo(seatNo);
	}
	public String toString()
	{
		return "Name:"+name+",Age:,"+ age +",Preference:"+preference+ ",Berth: " + allotedBerth+ ",Seat No:" + seatNo;
	}
	
}
