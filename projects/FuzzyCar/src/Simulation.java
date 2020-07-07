import javax.swing.JFrame;


public class Simulation extends JFrame {
	
	public Simulation() {
		this.addMouseListener(new MouseClickListener());
		this.addKeyListener(new KeyboardListener());
		ParkingLot pl = ParkingLot.getInstance();
		add(pl);
		setSize(1400, 1100);
		setTitle("Autonomous Parking Simulation");
		
	}
	
	public void start(){
		Thread t = new Thread(ParkingLot.getInstance());
		t.start();
	}
	
	public static void main(String[] args){
		
		Simulation simulation = new Simulation();
		simulation.setVisible(true);
		simulation.start();
		simulation.setDefaultCloseOperation(EXIT_ON_CLOSE);
		simulation.getContentPane().add(ParkingLot.getInstance());
		simulation.setVisible(true);
		
		
	}

}
