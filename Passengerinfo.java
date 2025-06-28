package Railway_Ticket;

public class Passengerinfo {
    private String name, preference, status;
    private int age, berthNo, pnr;

    
    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }
    public String getPreference() {
        return preference;
    }
    public String getStatus() {
        return status;
    }
    public int getBerthNo() {
        return berthNo;
    }
    public int getPnr() {
        return pnr;
    }

    
    public void setName(String name) {
        this.name = name;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public void setPreference(String preference) {
        this.preference = preference;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public void setBerthNo(int berthNo) {
        this.berthNo = berthNo;
    }
    public void setPnr(int pnr) {
        this.pnr = pnr;
    }

    public void setTicket(String name, int age, String preference, String status, int berthNo, int pnr) {
        setName(name);
        setAge(age);
        setPreference(preference);
        setStatus(status);
        setBerthNo(berthNo);
        setPnr(pnr);
    }
    @Override
    public String toString() {
        return "PNR: " + pnr + ", Name: " + name + ", Age: " + age + ", Berth No: " + berthNo + ", Status: " + status;
    }

}
