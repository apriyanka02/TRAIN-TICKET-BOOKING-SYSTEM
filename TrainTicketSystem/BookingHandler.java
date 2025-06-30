package TrainTicket;

import java.util.*;
public class BookingHandler {
	Scanner scan=new Scanner(System.in);
	BookingManager book=new BookingManager();
	public void start()
	{
		
         boolean running=true;
         while(running)
         {
        	 System.out.println("*-----------------TRAIN TICKET BOOKING SYSTEM-------------------*");
     		System.out.println("1.BOOK TICKET");
     		System.out.println("2.CANCEL TICKET");
     		System.out.println("3.PRINT ALL TICKET");
     		System.out.println("4.PRINT AVAIL TICKET");
     		System.out.println("5.EXIT");
     		 System.out.println("\n");
              System.out.print("Enter your choice: ");
              int choice = scan.nextInt();
              scan.nextLine(); 
        	 
         switch (choice) {
             case 1:
            	 book.book_ticket();
                 break;
             case 2:
                 System.out.print("Enter PNR number to cancel: ");
                 book.cancel_ticket();
                 break;
             case 3:
            	 book.printall();
                 break;
             case 4:
            	 book.printavail();
                 break;
             case 5:
                 System.out.println("Exit");
                 running = false;
                 break;
             default:
                 System.out.println("Invalid choice. Please try again.");
                 break;
         }
     }
 }
}
