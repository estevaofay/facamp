import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

public class SimulatedCar extends Car{
	private final Integer CAR_WIDTH = 50;
	private final Integer CAR_HEIGHT = 30;

	
	public SimulatedCar(Integer x, Integer y, Double phi, Double theta) {
		initialize(x,y,phi,theta);
	}
	
	private void initialize(Integer x, Integer y, Double phi, Double theta) {
		this.x = (int) (((int) x * 0.5) - (CAR_WIDTH / 2));
        this.y = (int) (((int) y * 0.5) - (CAR_HEIGHT / 2));
        this.phi = phi;
        this.theta = theta;
		
	}
	
	public int getCenterX() {
		return x + (CAR_WIDTH / 2);
	}
	
	public int getCenterY(){
		return y + (CAR_HEIGHT / 2);
	}

	@Override
	public void paint(Graphics g) {
		
		g.setColor(Color.BLUE);
		paintCar(g);
		
		
		g.setColor(Color.BLACK);
		
		paintBackWheels(g);
		paintFrontWheels(g);
		
	}
	
	@Override
	public void turnRight() {
		theta = theta + 1;
	}
	
	@Override
	public void turnLeft() {
		theta = theta - 1;
	}
	
	public Integer getCAR_WIDTH() {
		return CAR_WIDTH;
	}

	public Integer getCAR_HEIGHT() {
		return CAR_HEIGHT;
	}

	public void setCoordinates(Integer x, Integer y) {
		this.x = x;
		this.y = y;
				
	}
	
	public void setRotation(Double phi) {		
		this.phi = phi;
	}
	
	public void paintCar(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		if(this.getCenterY() < ParkingLot.getInstance().getParkingSpace().getY()) {
			g2d.rotate(Math.toRadians(phi), getCenterX(), getCenterY());
		} else {
			g2d.rotate(Math.toRadians(-phi), getCenterX(), getCenterY());
		}
		
		g.drawRect(this.x, this.y, CAR_WIDTH, CAR_HEIGHT);
	}
	
	private void paintFrontWheels(Graphics g){
		
		
		Graphics2D g2d1 = (Graphics2D) g;
		
		AffineTransform at = g2d1.getTransform();
		if(this.getY() < ParkingLot.getInstance().getParkingSpace().getY()) {
			g2d1.rotate(Math.toRadians(-theta), (getCenterX() - 25 + (int)(15/2)), getCenterY() - 20 - ((int)(5 /2)));
		} else {
			g2d1.rotate(Math.toRadians(theta), (getCenterX() - 25 + (int)(15/2)), getCenterY() - 20 - ((int)(5 /2)));
		}
		
		g.fillRect(getCenterX() - 25, getCenterY() - 20 , 15, 5);
		
		g2d1.setTransform(at);
		if(this.getY() < ParkingLot.getInstance().getParkingSpace().getY()) {
			g2d1.rotate(Math.toRadians(-theta), getCenterX() - 25 + ((int) (15 /2)), getCenterY() + 15 + ((int)(5 /2)));
		}else {
			g2d1.rotate(Math.toRadians(theta), getCenterX() - 25 + ((int) (15 /2)), getCenterY() + 15 + ((int)(5 /2)));
		}
		
		g.fillRect(getCenterX() - 25, getCenterY() + 15 , 15, 5);
		
	}
	
	private void paintBackWheels(Graphics g) {
		g.fillRect(getCenterX() + 10, getCenterY() + 15, 15, 5);
		g.fillRect(getCenterX() + 10, getCenterY() - 20 , 15, 5);
		
	}
	
	@Override
	public void move() {
		phi = phi + theta;
		x = (int) (x + 10 * Math.cos(Math.toRadians(phi)));	
		
		if(this.y < ParkingLot.getInstance().getParkingSpace().getY()) {
			y = (int) (y - 10 * Math.sin(Math.toRadians(-phi)));
		} else {
			y = (int) (y + 10 * Math.sin(Math.toRadians(-phi)));
		}
	}

	public double getPhi() {
		return phi;
	}

	public Integer getX() {
		return x;
	}

	public Integer getY() {
		return y;
	}

	public double getTheta() {
		return theta;
	}

}
