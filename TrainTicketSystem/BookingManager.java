package TrainTicket;

import java.util.*;
public class BookingManager {
	
	Scanner sc=new Scanner(System.in);
	List<Ticket>Ticketlist=new ArrayList<>();
	List<Passenger>passengers=new ArrayList<>();
	Berth berth=new Berth();
	int pnrcount=1000;
	public void book_ticket()
	{
		 Ticket ticket = new Ticket();
		 ticket.setpnr(pnrcount++);
		 
		boolean bookagain=true;
		
		while(bookagain)
		{
			System.out.println("Enter Your Name:");
			String name=sc.nextLine();
			
			System.out.println("Enter Your Age:");
			int  age=sc.nextInt();
			sc.nextLine();
			if(age<5)
			{
				System.out.println("No Ticket will be issued for children under 5");
				System.out.print("Do you want continue? (Y/N): ");
				  String ch = sc.nextLine();
			        if (ch.equalsIgnoreCase("N")) {
			            bookagain = false;
			        }
			    }
			
			
		   System.out.print("Enter Berth Preference(L/U/M/SU/SL:)");
		   String preference=sc.nextLine();
		   
		   Passenger passenger=new Passenger();
		   passenger.setname(name);
		   passenger.setage(age);
		   passenger.setpreference(preference);
		   if(berth.confirmed_available())
		   {
				berth.allocate_confirmed(passenger);
				ticket.addPassenger(passenger);
		   }
		   else if(berth.rac_available())
		   {
			    berth.allocateRAC(passenger);
				ticket.addPassenger(passenger);
		   }
		   else if(berth.waitlist_available())
		   {
			    berth.allocateWaitlist(passenger);
				ticket.addPassenger(passenger);
		   }
		   else
		   {
			   System.out.println("No tickets available.");
		   }
		   
		   
		   System.out.println("Do You want to add more (Y/N): ");
		   String ch = sc.nextLine();
	        if (ch.equalsIgnoreCase("N")) {
	            bookagain = false;
	        }
	    }
		   if (!ticket.getPassengerList().isEmpty()) {
	            String status = ticket.getPassengerList().get(0).getallotedBerth();
	            if (status.equals("WL")) ticket.setstatus("Waitlist");
	            else if (status.equals("RAC")) ticket.setstatus("RAC");
	            else ticket.setstatus("Confirmed");

	            Ticketlist.add(ticket); 
	            System.out.println("Ticket Booked Successfully:");
	            System.out.println(ticket);
	        }
	    }
	
	public void cancel_ticket()
	{
		System.out.println("Enter Your PNR:");
		int PNR=sc.nextInt();
		sc.nextLine();
		
	     Ticket found = null;
	        for (Ticket t : Ticketlist) {
	            if (t.getpnr() == PNR) {
	                found = t;
	                break;
	            }
	        }
	        if(found!=null)
	        {
	        	passengers=found.getPassengerList();
	        	for(Passenger p:passengers)
	        	{
	        		berth.cancelpassenger(p);
	        	}
	        	Ticketlist.remove(found);
	        	System.out.println("Ticket with  " + PNR + "PNR No Cancelled Successfully.");
	        } else {
	            System.out.println("PNR not found.");
	        }
		
	}
	public void printall()
	{
		 berth.printAll();
    }
	
	public void printavail()
	{
		  berth.printAvailability();
    }
}
