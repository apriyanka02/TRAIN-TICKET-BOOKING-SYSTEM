package Railway_Ticket;

import java.util.*;

public class Booking_choice {
    Scanner scan = new Scanner(System.in);
    Railways railways = new Railways();
    
    public void start() {

        boolean running = true;
        while (running) {
            System.out.println("**-------------TRAIN TICKET BOOKING SYSTEM-------------**");
            System.out.println("1. BOOK TICKET");
            System.out.println("2. CANCEL TICKET");
            System.out.println("3. PRINT ALL");
            System.out.println("4. PRINT AVAILABILITY");
            System.out.println("5. EXIT");
            System.out.println("\n");
            System.out.print("Enter your choice: ");
            int choice = scan.nextInt();
            scan.nextLine(); 

            switch (choice) {
                case 1:
                    railways.book_ticket();
                    break;
                case 2:
                    System.out.print("Enter PNR number to cancel: ");
                    int pnr = scan.nextInt();
                    scan.nextLine(); 
                    railways.cancel_ticket(pnr);
                    break;
                case 3:
                    railways.printall();
                    break;
                case 4:
                    railways.printavail();
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
