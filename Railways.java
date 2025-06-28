package Railway_Ticket;

import java.util.Arrays;
import java.util.*;
public class Railways implements Bookingmethods {
		final int tot_confirmed=14;
		final int tot_RAC=4;
		final int tot_waitlist=2;
		int confirmed = 0, rac = 0, waitlist = 0, count = 0,pnrcount=1000;
		private List<Integer> availableBerthNo = new ArrayList<>();{
		for (int i = 1; i <= 16; i++) {
            availableBerthNo.add(i);
        }
		}
		private List<String> availberth = new ArrayList<>();
		{
			for (int i = 0; i < 4; i++) availberth.add("L");
	        for (int i = 0; i < 4; i++) availberth.add("M");
	        for (int i = 0; i < 4; i++) availberth.add("U");
	        for (int i = 0; i < 2; i++) availberth.add("SU");
	        for (int i = 0; i < 2; i++) availberth.add("SL");
	        
		}
		 
	    private Queue<Passengerinfo> racQueue = new LinkedList<>();
	    private Queue<Passengerinfo> waitQueue = new LinkedList<>();
	    private List<Passengerinfo> passengerconfirmed = new ArrayList<>();
		Scanner s=new Scanner(System.in);
		  @Override
		public void book_ticket()
		{
			boolean bookagain = true;
			
	        while (bookagain) {
	        	
	        	int pnr = pnrcount++;
	            System.out.print("Enter Name: ");
	            String name = s.nextLine();
	            System.out.print("Enter Age: ");
	            int age = s.nextInt();
	            s.nextLine();

	            if (age < 5) {
	                System.out.println("No ticket is issued  below 5 years.");
	                System.out.print("Do you want to book more? (Y/N): ");
	                char ch = s.nextLine().charAt(0);
	                bookagain = (ch == 'Y' || ch == 'y');
	                continue;
	            }
			System.out.print("Enter Berth Preference(L/U/M/SU/SL:)");
			String preference=s.nextLine();
			
			
			Passengerinfo passenger=new Passengerinfo();
			 String berthAllocated = allocateBerth(age, preference);
			 if (!preference.isEmpty() && !preference.equals(berthAllocated)) {
		            System.out.println("Preferred berth " + preference + " not available.");
		            System.out.println("Available berth " + berthAllocated + " will be allocated.");
		            System.out.print("Do you want to proceed? (Y/N): ");
		            char ch = s.nextLine().charAt(0);
		            if (!(ch == 'Y' || ch == 'y')) {
		                System.out.print("Do you want to book more? (Y/N): ");
		                ch = s.nextLine().charAt(0);
		                bookagain = (ch == 'Y' || ch == 'y');
		                continue;
		            }
		        }

			if(!berthAllocated.isEmpty()&& !berthAllocated.equals("SL"))
			{
				if (!availableBerthNo.isEmpty()) {
			        int berthNo = availableBerthNo.remove(0);  
			        passenger.setTicket(name, age, berthAllocated, "CONFIRMED", berthNo, pnr);
			        passengerconfirmed.add(passenger);
			        availberth.remove(berthAllocated);
			        System.out.println("Ticket Confirmed: " + passenger);
			    } else {
			        System.out.println("No berth available");
			    }
			}
			else if(racQueue.size()<tot_RAC)
			{
				passenger.setTicket(name, age, "SL","RAC",-1,pnr);
				racQueue.offer(passenger);
				rac++;
				availberth.remove("SL");
				System.out.println("Ticket In RAC:"+passenger);	
			
			}
			else if(waitQueue.size()<tot_waitlist)
			{
				passenger.setTicket(name, age, "WL","Waiting List",-1,pnr);
				waitQueue.offer(passenger);
				waitlist++;
				System.out.println("Ticket In Waiting List:"+passenger);			
			}
			else
			{
				System.out.println("Ticket is Not available");			
			}
			 System.out.print("Do you want to book more? (Y/N): ");
	            char ch = s.nextLine().charAt(0);
	            bookagain = (ch == 'Y' || ch == 'y');
	        }
			
		}
		private String allocateBerth(int age,String preference)
		{
			if((age>60||age<5)&&(availberth.contains("L")))
			{
				return "L";
			}
			if(!preference.isEmpty() && availberth.contains(preference))
			{
				return preference;
			}
			for (String berth : Arrays.asList("L", "M", "U", "SU")) {
	            if (availberth.contains(berth)) {
	                return berth;
	            }
	        }
			return "";
		}
		@Override
		public void cancel_ticket(int pnr) {
			   
			Passengerinfo toRemove = null;
			for (Passengerinfo p : passengerconfirmed) {
			   if (p.getPnr() == pnr) {
			       toRemove = p;
			       break;
			   }
			}


		    if (toRemove != null) {
		        passengerconfirmed.remove(toRemove);
		        availberth.add(toRemove.getPreference());
		        availableBerthNo.add(toRemove.getBerthNo()); 
		        confirmed--;
		        System.out.println("Ticket Cancelled for PNR: " + pnr);

		        if (!racQueue.isEmpty()) {
		            Passengerinfo Rac = racQueue.poll();
		            Rac.setStatus("CONFIRMED");
		            Rac .setPreference(toRemove.getPreference());
		            int reassignedBerthNo = toRemove.getBerthNo();
		            Rac.setBerthNo(reassignedBerthNo);
	                passengerconfirmed.add(Rac );
	                confirmed++;
	                rac--;
	                availberth.remove(toRemove.getPreference());

		            System.out.println("Moved RAC Passenger to Confirmed: " + Rac);
		        }
		        if (!waitQueue.isEmpty()) {
	                Passengerinfo waitPassenger = waitQueue.poll();
	                waitPassenger.setStatus("RAC");
	                racQueue.offer(waitPassenger);
	                waitlist--;
	                rac++;
	                System.out.println("Moved Waiting List to RAC: " + waitPassenger);
	            }
		    } else {
		        System.out.println("No passenger found with PNR: " + pnr);
		    }
		}
		  @Override
		public void printall()
		{
			  System.out.println("Berth-wise Booking Status:");
			    for (int i = 1; i <= 16; i++) {
			        boolean booked = false;
			        for (Passengerinfo p : passengerconfirmed) {
			            if (p.getBerthNo() == i) {
			                System.out.println("Berth No: " + i + ", PNR: " + p.getPnr() + ", Name: " + p.getName() + ", Age: " + p.getAge());
			                booked = true;
			                break;
			            }
			        }
			        if (!booked) {
			            System.out.println("Berth No: " + i + ", vacant");
			        }
			    }
			    System.out.println("\n-------------------------");
			    System.out.println(" RAC Tickets");
			 if (racQueue.isEmpty()) {
		            System.out.println("No RAC Tickets");
		        } else {
		        	System.out.println("RACTickets");
		            for (Passengerinfo passenger : racQueue) {
		                System.out.println(passenger);
		            }
		        }
			 System.out.println("\n-------------------------");
			    System.out.println(" Waiting List");
			 if (waitQueue.isEmpty()) 
			 {
		            System.out.println("No Waiting List Tickets");
		     }
			 else {
				    System.out.println(" Waiting List Tickets");
		            for (Passengerinfo passenger : waitQueue) {
		                System.out.println(passenger);
		            }
		        }
		}
		  @Override
		public void printavail()
		{
			System.out.println("Available Berth: " + availberth.size());
			System.out.println("Available RAC: " + (tot_RAC - racQueue.size()));
			System.out.println("Available Waiting List: " + (tot_waitlist - waitQueue.size()));

			
		}
		 
		
		
		
	}
