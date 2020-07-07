import java.awt.Graphics;

public abstract class Car {
	public Integer x;
	public Integer y;
	public Double theta;
	public Double phi;
	public abstract void paint(Graphics g);
	public abstract void move();
	public abstract void turnLeft();
	public abstract void turnRight();
}
