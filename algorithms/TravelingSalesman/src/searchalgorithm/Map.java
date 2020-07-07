package searchalgorithm;

import java.util.HashSet;
import java.util.Set;

public class Map {

    Set<City> cities;

    public Map() {
        City s = new City("S");
        City a = new City("A");
        City b = new City("B");
        City c = new City("C");
        City d = new City("D");
        City e = new City("E");
        City g1 = new City("G1");
        City g2 = new City("G2");
        City g3 = new City("G3");

        s.addHighway(a, 1);
        s.addHighway(b, 7);
        a.addHighway(c, 1);
        a.addHighway(b, 2);
        a.addHighway(d, 15);
        b.addHighway(e, 4);
        b.addHighway(g3, 11);
        b.addHighway(d, 10);
        c.addHighway(s, 2);
        d.addHighway(g1, 5);
        d.addHighway(g2, 3);
        e.addHighway(d, 3);
        g2.addHighway(e, 6);

        City[] l = {s, a, b, c, d, e, g1, g2, g3};
        cities = new HashSet<>();

        for (City city : l) {
            cities.add(city);
        }

    }

    public City getCity(String name) {
        for (City c : cities) {
            if (c.getName().equals(name)) {
                return c;
            }

        }

        return null;
    }
}
