package searchalgorithm;

public class Highway {
    City origin, destination;
    double distance;

    public Highway(City origin, City destination, double distance) {
        this.origin = origin;
        this.destination = destination;
        this.distance = distance;
    }

    public City getOrigin() {
        return origin;
    }

    public City getDestination() {
        return destination;
    }
    
    
}
