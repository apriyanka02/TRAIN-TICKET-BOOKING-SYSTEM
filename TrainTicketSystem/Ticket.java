package TrainTicket;

import java.util.*;

public class Ticket {
	private int PNR;
    private String status;
    private List<Passenger> passengerList = new ArrayList<>();
	
	
	//get methods
	public int getpnr()
	{
		return PNR;
	}
	public String getstatus()
	{
		return status;
	}

     public List<Passenger> getPassengerList() 
     {
	        return passengerList;
	 }
	//set methods
	public void setpnr(int PNR)
	{
		this.PNR=PNR;
	}
	public void setstatus(String status)
	{
		this.status=status;
	}
	public void addPassenger(Passenger p) {
        passengerList.add(p);
    }
	@Override
    public String toString() {
        return "PNR: " + PNR + ", Status: " + status + ", Passengers: " + passengerList;
    }
	
	
}
