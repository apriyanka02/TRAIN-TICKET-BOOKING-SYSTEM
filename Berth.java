package TrainTicket;

import java.util.ArrayList;
import java.util.List;

public class Berth {
	public static final int Tot_Confirmed = 14;
	public static final int Tot_RAC = 4;
	public static final int Tot_Waitlist = 2;
	
	private List<Passenger> confirmed = new ArrayList<>();
	private List<Passenger> rac = new ArrayList<>();
	private List<Passenger> waitlist = new ArrayList<>();
	
	private List<Integer> SeatNos= new ArrayList<>();
	private List<String> berthTypes = new ArrayList<>();
	
	public Berth()
	{
		for (int i = 1; i <= 16; i++) 
		{
			SeatNos.add(i);
        }
		for (int i = 0; i < 4; i++) berthTypes.add("L");
        for (int i = 0; i < 4; i++) berthTypes.add("M");
        for (int i = 0; i < 4; i++) berthTypes.add("U");
        for (int i = 0; i < 2; i++) berthTypes.add("SU");
        for (int i = 0; i < 2; i++) berthTypes.add("SL");
	}
	
	  public boolean confirmed_available() 
	  {
	        return confirmed.size() < Tot_Confirmed;
	   }

	    public boolean rac_available() {
	        return rac.size() < Tot_RAC;
	    }

	    public boolean waitlist_available(){
	        return waitlist.size() < Tot_Waitlist;
	    }
	    
	   public void allocate_confirmed(Passenger passenger)
	   {
			
			String berth=allocate_Berth(passenger.getpreference(),passenger.getage());
			passenger.setallotedBerth(berth);
			passenger.setseatNo( SeatNos.remove(0));
			confirmed.add(passenger);
	   }
	   public String allocate_Berth(String preference,int age)
	   {
		   if(age>60&&berthTypes.contains("L"))
		   {
			   return removeBerth("L");
		   }
		   if(preference!=null&&berthTypes.contains(preference))
		   {
			   return removeBerth(preference);
		   }
		   if (berthTypes.contains("L")) 
		   {
			   return removeBerth("L");
		    }
	        return removeBerth(berthTypes.get(0));
	   }
	   public  String removeBerth(String type) {
	        berthTypes.remove(type);
	        return type;
	    }

	    public void allocateRAC(Passenger p) {
	        p.setallotedBerth("RAC");
	        rac.add(p);
	    }

	    public void allocateWaitlist(Passenger p) {
	        p.setallotedBerth("WL");
	        waitlist.add(p);
	    }
	   
	

	public void cancelpassenger(Passenger p) {
		if (confirmed.remove(p)) {
            SeatNos.add(p.getseatNo());
            berthTypes.add(p.getallotedBerth());
            if (!rac.isEmpty()) {
                Passenger moved = rac.remove(0);
                allocate_confirmed(moved);
                if (!waitlist.isEmpty()) {
                    rac.add(waitlist.remove(0));
                }
            }
        } else if (rac.remove(p)) {
            if (!waitlist.isEmpty()) {
                rac.add(waitlist.remove(0));
            }
        } else {
            waitlist.remove(p);
        }
    }
	
	public void printAll() {
		   int count = 1;
	        for (Passenger p : confirmed)
	        {
	            System.out.println(count++ + ". " + p);
	        }
	        for (Passenger p : rac)
	        {
	            System.out.println(count++ + ". " + p);
	        }
	        for (Passenger p : waitlist) 
	        {
	            System.out.println(count++ + ". " + p);
	        }
	    }

	public void printAvailability() {
		System.out.println("Available Confirmed: " + (Tot_Confirmed - confirmed.size()));
        System.out.println("Available RAC: " + (Tot_RAC - rac.size()));
        System.out.println("Available WL: " + (Tot_Waitlist - waitlist.size()));
    }

	
	
	
}
