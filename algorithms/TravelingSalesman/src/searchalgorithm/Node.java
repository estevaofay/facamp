package searchalgorithm;

import java.util.List;
import java.util.Set;

public class Node {

    private Node parent;
    private City city;

    public Node(City city) {
        this.city = city;
        this.parent = null;
    }

    public String getName() {
        return this.city.getName();
    }

    public City getCity() {
        return city;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

}
