package Asteroids;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Enemy extends Station implements Runnable {

	 public Enemy() {
        this.x = (int) (Math.random() * Space.getInstance().getFrameWidth());
        this.y = (int) (Math.random() * Space.getInstance().getFrameHeight());
        this.setAcceleration(1);
        life = 100;
    }

         @Override
    public void interactWith(Agent other) {
        //check colisao linhas laterais 
                
        if(other instanceof FriendRocket){
            if(collisionDetection(x,y,baseLinex1,baseLiney1, other.x, other.y, 3)){
                other.hit(this);
                this.hit(other);
            }
        }
    }
    @Override
    public void paint(Graphics g) {
        g.setColor(Color.RED);

        middleLiney = this.getModulo() * Math.sin(this.getAngle());
        middleLinex = this.getModulo() * Math.cos(this.getAngle());
        topPointx = (x + middleLinex);
        topPointy = (y - middleLiney);
        baseLinex1 = x - (this.getModulo() / 4) * Math.sin(this.getAngle());
        baseLinex2 = x + (this.getModulo() / 4) * Math.sin(this.getAngle());
        baseLiney1 = y + ((-this.getModulo()) / 4) * Math.cos(this.getAngle());
        baseLiney2 = y + +(this.getModulo() / 4) * Math.cos(this.getAngle());

        g.drawLine((int) x, (int) y, (int) topPointx, (int) topPointy);
        g.drawLine((int) baseLinex1, (int) baseLiney1, (int) baseLinex2, (int) baseLiney2); //linha da base
        g.drawLine((int) baseLinex1, (int) baseLiney1, (int) topPointx, (int) topPointy); //linha da esquerda
        g.drawLine((int) baseLinex2, (int) baseLiney2, (int) topPointx, (int) topPointy); //linha da direita
    }

    @Override
    public void move() {
        if (isTurningCW) {
            angle -= 0.1;
        }
        if (isTurningCCW) {
            angle += 0.1;
        }

        this.dx += Math.cos(angle) * speed * acceleration;
        this.dy += Math.sin(angle) * speed * acceleration;

        if (dx > maxspeed) {
            dx = maxspeed;
        }
        if (dy > maxspeed) {
            dy = maxspeed;
        }
        if (dx < -maxspeed) {
            dx = -maxspeed;
        }
        if (dy < -maxspeed) {
            dy = -maxspeed;
        }

        x += dx;
        y -= dy;

        fixCoordinates();
    }

    public void shoot(Station station) {
        if (station != null && this.alive) {
            double theta = Math.atan(Math.abs(y - station.y) / Math.abs(x - station.x));

            if (station.x > x && station.y > y) {
                theta = -theta;
            } else if (station.x > x && station.y < y) {
                //theta = 2 * Math.PI - theta;
            } else if (station.x < x && station.y > y) {
                theta += Math.PI;
            } else {
                theta += Math.PI / 2;
            }

//            EnemyRocket rocket = new EnemyRocket(theta);
//            Space.getInstance().getEnemyRockets().add(rocket);
        }
    }

    @Override
    public void run() {

        while (this.alive) {
            this.angle = Math.round(Math.random() * 2 * Math.PI);
            this.speed = Math.round(Math.random() * 2);

            //shoot(Space.getInstance().getStation());

            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(Enemy.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void initializeEnemy() {
        this.x = (int) (Math.random() * Space.getInstance().getFrameWidth());
        this.y = (int) (Math.random() * Space.getInstance().getFrameHeight());
        sx = 20;
        sy = 20;
        alive = true;
        life = 100;
        lifePercent = life /100;
        acceleration = 0;
        angle = 0;
        
       
    }

    @Override
    public void hit(Agent agent) {
        
        if (agent instanceof FriendRocket) {
            life -= 10;
//            lifePercent = life /100;
        }
        if (agent instanceof Station) {
            life -= 100;
        }
        if (life <= 0) {
            alive = false;
            Applet.newAudioClip(getClass().getResource("dead.wav")).play();
        } else {
            life -= 10;
        }
    }

}
