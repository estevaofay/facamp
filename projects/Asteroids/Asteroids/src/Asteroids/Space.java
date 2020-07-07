package Asteroids;

import java.awt.Color;
import java.awt.Graphics;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.swing.JPanel;

public class Space extends JPanel implements Runnable {

    private int frameWidth = 500;
    private int frameHeight = 400;
    public boolean hasStation = false;
    public boolean hasEnemy = false;

    private final int lifeBarX = 10;
    private final int lifeBarY = 15;
    private final int lifeBarWidth = 100;
    private final int lifeBarHeight = 50;

    public int getFrameWidth() {
        return frameWidth;
    }

    public void setFrameWidth(int frameWidth) {
        this.frameWidth = frameWidth;
    }

    public int getFrameHeight() {
        return frameHeight;
    }

    public void setFrameHeight(int frameHeight) {
        this.frameHeight = frameHeight;
    }

    private static Space singleton;

    public static Space getInstance() {
        if (singleton == null) {
            singleton = new Space();
        }
        return singleton;
    }

    private Space() {
        agents = new CopyOnWriteArrayList<>();
        friendRockets = new CopyOnWriteArrayList<>();
        enemyRockets = new CopyOnWriteArrayList<>();
        asteroids = new CopyOnWriteArrayList<>();
        this.setFocusable(false);
    }

    public void reset() {
        agents = new CopyOnWriteArrayList<>();
        friendRockets = new CopyOnWriteArrayList<>();
        enemyRockets = new CopyOnWriteArrayList<>();
        asteroids = new CopyOnWriteArrayList<>();
        enemy = new Enemy();
        station = new Station();

        for (int i = 0; i < 10; i++) {
            createAsteroid(new Asteroid());
        }

        for (Asteroid a : asteroids) {
            agents.add(a);
        }
//        for (FriendRocket fr : friendRockets) {
//            agents.add(fr);
//        }

    }

    public void createAsteroid(Asteroid asteroid) {
        asteroids.add(asteroid);
        agents.add(asteroid);

    }

    public void createStation(boolean a) {
        if (a == false) {
            agents.add(station);
            this.hasStation = true;
        }
    }

    public void createEnemy(boolean a) {
        if (a == false) {
            agents.add(enemy);
            this.hasEnemy = true;
        }
    }

    public void removeStation() {
        agents.remove(station);
        station = null;
    }

    public double getStationTipX() {
        return this.station.topPointx;
    }

    public double getStationTipY() {
        return this.station.topPointy;
    }

    public double getStationAngle() {
        return this.station.getAngle();
    }

    private CopyOnWriteArrayList<Agent> agents;
    private Station station;
    private Enemy enemy;
    private CopyOnWriteArrayList<FriendRocket> friendRockets;
    private CopyOnWriteArrayList<EnemyRocket> enemyRockets;
    private CopyOnWriteArrayList<Asteroid> asteroids;

    public Station getStation() {

        return station;
    }

    public Enemy getEnemy() {
        return enemy;
    }

    public void createFRocket(FriendRocket r) {
        if (friendRockets.size() < 10) {
            friendRockets.add(r);
            agents.add(r);
        }
        System.out.println("Number of Rockets: " + friendRockets.size());

    }

    public void createERocket(EnemyRocket r) {
        if (enemyRockets.size() < 10) {
            enemyRockets.add(r);
            agents.add(r);
        }
        // System.out.println("Number of Rockets: " + friendRockets.size());

    }

    public void resetStation(Station station) {
        station.x = (int) (0.5 * Space.getInstance().getFrameWidth());
        station.y = (int) (0.5 * Space.getInstance().getFrameHeight());
        station.dy = 0;
        station.dx = 0;
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(0, 0, frameWidth, frameHeight);
        for (Agent a : agents) {
            a.paint(g);
        }
        if (station != null) {
            if (station.getLife() >= 50) {
                g.setColor(Color.green);
            } else if (station.getLife() > 0) {
                g.setColor(Color.red);
            }

            g.fillRect(lifeBarX, lifeBarY, lifeBarWidth * station.getLife() / 100, lifeBarHeight);
            g.setColor(Color.white);
            g.drawRect(lifeBarX, lifeBarY, lifeBarWidth, lifeBarHeight);
            g.drawString("Health Points", 5, 12);
        }
        if (enemy != null) {
            if (enemy.getLife() >= 100) {
                g.setColor(Color.green);
            } else {
                g.setColor(Color.red);
            }
            g.fillRect(lifeBarX + 370, lifeBarY, lifeBarWidth * enemy.getLife() / 100, lifeBarHeight);
            g.setColor(Color.white);
            g.setColor(Color.BLACK);
            g.drawRect(lifeBarX + 370, lifeBarY, lifeBarWidth, lifeBarHeight);
            g.drawString((int) (getEnemy().getLife()) + "%", 420, 45);
            g.drawString("ENEMY HP", 375, 12);
        }

        if (station.getLife() > 0) {
            g.setColor(Color.BLACK);
            g.drawRect(lifeBarX, lifeBarY, lifeBarWidth, lifeBarHeight);
            g.drawString("LIFE", 10, 10);
            g.drawString((int) (getStation().getLife()) + "%", 50, 45);
            g.drawString("Ammo: " + (int) ((-friendRockets.size()) + 10) + "/10", 10, 90);
        }

        g.drawString("N of Asteroids: " + (int) (asteroids.size()), 10, 105);
    }

    @Override
    public void run() {
        reset();
        try {
            while (true) {
                // Mover agentes
                for (Agent a : agents) {
                    a.move();
                }

                // Check de interação
                // Check colisao entre station e asteroid
                if (this.hasStation == true) {
                    station.interactWith(asteroids);
                }
                // Check colisao entre rocket e asteroid
                for (Asteroid asteroid : asteroids) {
                    asteroid.interactWith(friendRockets);
                }
                // Para cada rocket: rocket interage com asteroid
                for (Rocket rocket : friendRockets) {
                    rocket.interactWith(asteroids);
                }
                // Check colisao entre rocket station
                if (this.hasStation == true) {
                    for (Rocket rocket : enemyRockets) {
                        rocket.interactWith(station);
                    }
                    for (Rocket rocket : friendRockets) {
                        rocket.interactWith(station);
                    }
                }
                // Check colisao entre rocket enemy
                if (this.hasEnemy == true) {
                    
                    for (Rocket rocket : friendRockets) {
                        rocket.interactWith(enemy);
                    }
                }
                // Remover todos elementos mortos
                removeDeadAgents();

                // Criar novos asteroids
                // Pinta cena
                this.repaint();
                // Dorme 30ms
                Thread.sleep(30);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }
    }

    private void removeDeadAgents() {
        for (Agent a : agents) {
            if (!a.isAlive()) {
                agents.remove(a);
            }
        }

        for (FriendRocket fr : friendRockets) {
            if (!fr.isAlive()) {
                friendRockets.remove(fr);
            }
        }

        for (Asteroid as : asteroids) {
            if (!as.isAlive()) {
                asteroids.remove(as);
            }
        }

        while (asteroids.size() < 10) {
            createAsteroid(new Asteroid());
        }
    }

    public void turnStationCW() {
        if (station == null) {
            return;
        }
        station.setTurningCW(true);
    }

    public void turnStationCCW() {
        if (station == null) {
            return;
        }
        station.setTurningCCW(true);
    }

    public void stopTurningStationCW() {
        if (station == null) {
            return;
        }
        station.setTurningCW(false);
    }

    public void stopTurningStationCCW() {
        if (station == null) {
            return;
        }
        station.setTurningCCW(false);
    }

    public void acceleratingStation(double a) {
        if (station == null) {
            return;
        }
        station.accelerate(a);
    }
}
