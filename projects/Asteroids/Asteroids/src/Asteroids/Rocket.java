package Asteroids;

import java.awt.Graphics;
import java.awt.geom.Point2D;

public abstract class Rocket extends Agent {

    double distance = 0;
    final int speed = 3;
    final double modulo = 3;
    double lx, ly;
    
    public void move() {
        if (distance < 500) {
            x += dx;
            y -= dy;
            fixCoordinates();
            //distance += Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
            distance = Point2D.distance(x,y,dx,dy);
            
        } else {
            this.alive = false;
        }
    }
    
}
