package searchalgorithm;

import java.util.HashSet;
import java.util.Set;

public class City {

    String name;
    Set<Highway> highwaySet;
    private Node node = new Node(this);

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public City(String name) {
        this.name = name;
        highwaySet = new HashSet<>();

    }

    public String getName() {
        return name;
    }

    public Set<Highway> getHighwaySet() {
        return highwaySet;
    }

    public void addHighway(Highway e) {
        highwaySet.add(e);
    }

    public void addHighway(City destination, double distance) {
        Highway e = new Highway(this, destination, distance);
        highwaySet.add(e);
    }

    public boolean isGoal() {
        return name.startsWith("G");
    }

    public Set<City> possibleDestinations() {
        Set<City> ret = new HashSet<>();
        for (Highway e : highwaySet) {
            ret.add(e.getDestination());
        }

        return ret;
    }

}
