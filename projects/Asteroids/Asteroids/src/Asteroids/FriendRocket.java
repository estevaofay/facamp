package Asteroids;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

public class FriendRocket extends Rocket {

    private Station station;

    private int count = 0;

    double secondY = 5 * Math.sin(Space.getInstance().getStationAngle());
    double secondX = 5 * Math.cos(Space.getInstance().getStationAngle());

    public FriendRocket() {
        station = Space.getInstance().getStation();
        double angle = station.getAngle();
        x = station.getTopPointx();
        y = station.getTopPointy();
        ly = modulo * Math.sin(angle);
        lx = modulo * Math.cos(angle);
        dx = Math.cos(angle) * speed;
        dy = Math.sin(angle) * speed;
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.BLACK);

        g.drawLine((int) x, (int) y, (int) (x + lx), (int) (y + ly));

    }

    @Override
    public void interactWith(List<? extends Agent> others) {
        for (Agent a : others) {
            //check colisao tiro com asteroids 
            if (a instanceof Asteroid) {
                if (collisionDetection((int) x, (int) y, (int) (x + lx), (int) (y + ly), a.x, a.y, a.sx)) {
                    a.hit(this);
                    this.hit(a);
                }
            }
            if (a instanceof Enemy) {
                if (collisionDetection((int) x, (int) y, (int) (x + lx), (int) (y + ly), a.x, a.y, a.sx)) {
                    a.hit(this);
                    this.hit(a);
                }
            }
        }
    }

    @Override
    public void hit(Agent agent) {
        this.alive = false;

    }

    @Override
    public void interactWith(Agent other) {
        // TODO Auto-generated method stub

    }

}
