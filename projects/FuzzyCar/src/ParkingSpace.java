import java.awt.Color;
import java.awt.Graphics;

public class ParkingSpace {
	private Integer x;
	private Integer y;
	
	public ParkingSpace(Integer x, Integer y) {
		this.x = x;
		this.y = y;
	}
	
	public void paint(Graphics g){
		g.setColor(Color.gray);
		g.fillRect(x - 60, y - 85, 120, 170);
	}

	public Integer getX() {
		return x;
	}

	public void setX(Integer x) {
		this.x = x;
	}

	public Integer getY() {
		return y;
	}

	public void setY(Integer y) {
		this.y = y;
	}

	public void setCoordinates(int x, int y) {
		this.x = x;
		this.y = y;
		
	}
	
	
}
