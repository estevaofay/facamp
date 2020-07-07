package Asteroids;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

public class Station extends Agent {

    public double angle = 0;
    private double modulo = 20.0;
    public boolean isTurningCW = false;
    public boolean isTurningCCW = false;
    public double speed = 1;
    public double acceleration = 0;
    public final double maxspeed = 2;
    public int life;
    double middleLiney;
    double middleLinex;
    double topPointx;
    double lifePercent;

   
    double topPointy;
    double baseLinex1;
    double baseLinex2;
    double baseLiney1;
    double baseLiney2;

    public Station() {
        initializeStation();
    }

    public void initializeStation() {
        this.x = (int) (0.5 * Space.getInstance().getFrameWidth());
        this.y = (int) (0.5 * Space.getInstance().getFrameHeight());
        sx = 20;
        sy = 20;
        alive = true;
        life = 100;
        lifePercent = life /100;
        acceleration = 0;
        angle = 0;
        
       
    }

    public double getLifePercent() {
        return lifePercent;
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
        if (dx < (-maxspeed)) {
            dx = (-maxspeed);
        }
        if (dy < (-maxspeed)) {
            dy = (-maxspeed);
        }

        x += dx;
        y -= dy;

        this.acceleration = 0; //quando solta botão, a = 0

        //System.out.println("dx " + dx + "dy" + dy);
        fixCoordinates();
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.BLACK);

        middleLiney = modulo * Math.sin(angle);
        middleLinex = modulo * Math.cos(angle);
        topPointx = (x + middleLinex);
        topPointy = (y - middleLiney);
        baseLinex1 = x - (modulo / 4) * Math.sin(angle);
        baseLinex2 = x + (modulo / 4) * Math.sin(angle);
        baseLiney1 = y + ((-modulo) / 4) * Math.cos(angle);
        baseLiney2 = y + +(modulo / 4) * Math.cos(angle);

        g.drawLine((int) x, (int) y, (int) topPointx, (int) topPointy);
        g.drawLine((int) baseLinex1, (int) baseLiney1, (int) baseLinex2, (int) baseLiney2); //linha da base
        g.drawLine((int) baseLinex1, (int) baseLiney1, (int) topPointx, (int) topPointy); //linha da esquerda
        g.drawLine((int) baseLinex2, (int) baseLiney2, (int) topPointx, (int) topPointy); //linha da direita
    }

    @Override
    public void interactWith(List<? extends Agent> others) {
        for (Agent other : others) {
            interactWith(other);
        }
    }

    @Override
    public void interactWith(Agent other) {
        //check colisao linhas laterais 
        if (other instanceof Asteroid) {
            if (collisionDetection(x, y, baseLinex1, baseLiney1, other.x + 20, other.y + 20, other.sx)) {
                other.hit(this);
                this.hit(other);
            }
            if (collisionDetection(x, y, baseLinex2, baseLiney2, other.x, other.y, other.sx)) {
                other.hit(this);
                this.hit(other);
            }
        }
        
        if(other instanceof FriendRocket){
            if(collisionDetection(x,y,baseLinex1,baseLiney1, other.x, other.y, 3)){
                other.hit(this);
                this.hit(other);
            }
        }
    }

    public void shoot() {
        if (this.alive) {
            FriendRocket r = new FriendRocket();
            Space.getInstance().createFRocket(r);
        }
        Applet.newAudioClip(getClass().getResource("rocket.wav")).play();

    }

    public double getModulo() {
        return modulo;
    }

    public void setModulo(double modulo) {
        this.modulo = modulo;
    }

    public boolean isIsTurningCW() {
        return isTurningCW;
    }

    public void setIsTurningCW(boolean isTurningCW) {
        this.isTurningCW = isTurningCW;
    }

    public boolean isIsTurningCCW() {
        return isTurningCCW;
    }

    public void setIsTurningCCW(boolean isTurningCCW) {
        this.isTurningCCW = isTurningCCW;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(double acceleration) {
        this.acceleration = acceleration;
    }

    public double getMiddleLiney() {
        return middleLiney;
    }

    public void setMiddleLiney(double middleLiney) {
        this.middleLiney = middleLiney;
    }

    public double getMiddleLinex() {
        return middleLinex;
    }

    public void setMiddleLinex(double middleLinex) {
        this.middleLinex = middleLinex;
    }

    public double getTopPointx() {
        return topPointx;
    }

    public void setTopPointx(double topPointx) {
        this.topPointx = topPointx;
    }

    public double getTopPointy() {
        return topPointy;
    }

    public void setTopPointy(double topPointy) {
        this.topPointy = topPointy;
    }

    public double getBaseLinex1() {
        return baseLinex1;
    }

    public void setBaseLinex1(double baseLinex1) {
        this.baseLinex1 = baseLinex1;
    }

    public double getBaseLinex2() {
        return baseLinex2;
    }

    public void setBaseLinex2(double baseLinex2) {
        this.baseLinex2 = baseLinex2;
    }

    public double getBaseLiney1() {
        return baseLiney1;
    }

    public void setBaseLiney1(double baseLiney1) {
        this.baseLiney1 = baseLiney1;
    }

    public double getBaseLiney2() {
        return baseLiney2;
    }

    public void setBaseLiney2(double baseLiney2) {
        this.baseLiney2 = baseLiney2;
    }

    public double getAngle() {
        return angle;
    }    

    public void hit(Agent agent) {
        if (agent instanceof Rocket) {
            life -= 100;
//            lifePercent = life /100;
        }
        if (agent instanceof Asteroid) {
            Asteroid asteroid = (Asteroid) agent;
            life -= (int) asteroid.sx / 20;
//            lifePercent = life /100;
        }

        if (life <= 0) {
            alive = false;
        } else {
            life -= 10;
//            lifePercent = life /100;
        }

    }

    public boolean isTurningCW() {
        return isTurningCW;
    }

    public void setTurningCW(boolean isTurningCW) {
        this.isTurningCW = isTurningCW;
    }

    public boolean isTurningCCW() {
        return isTurningCCW;
    }

    public void setTurningCCW(boolean isTurningCCW) {
        this.isTurningCCW = isTurningCCW;
    }

    public void accelerate(double a) {
        this.acceleration += a;
        //System.out.println(this.acceleration);
    }
    
     public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }


}
