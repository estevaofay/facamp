package statcalc;

public class Entry implements Comparable {
    
    private int id;
    private String timestamp;
    private String date;
    private double temperature;
    
    public Entry(int id, String timestamp, String date, double temp) {
        this.id = id;
        this.date = date;
        this.temperature = temp;
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }   

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    @Override
    public int compareTo(Object o) {
        Entry e = (Entry) o;
        if(e.getTemperature() > this.getTemperature())
            return -1;
        if(e.getTemperature() == this.getTemperature())
            return 0;
        return 1;
    }
}
