import java.awt.Color;

import java.awt.Graphics;
import java.io.IOException;
import javax.swing.JPanel;

import net.sourceforge.jFuzzyLogic.FunctionBlock;
import net.sourceforge.jFuzzyLogic.plot.JFuzzyChart;

@SuppressWarnings("serial")
public class ParkingLot extends JPanel implements Runnable {


	private SimulatedCar sc;
	private ParkingSpace ps;
	private FuzzyDriver fd;
	private NNDriver nnDriver = null;
	private int frameWidth = 1400;
	private int frameHeight = 1000;
	private static ParkingLot singleton;
	private boolean randomPosition = true;
	private boolean move = false;

	public enum Driver {
		NN, FUZZY
	};

	private Driver currentDriver;

	private ParkingLot() throws IOException {
		setFuzzyDriver(new FuzzyDriver());
		currentDriver = Driver.FUZZY;
		nnDriver = new NNDriver(0.02, 0.00001, 1000);
		setSimulatedCar(new SimulatedCar(getFrameWidth(), getFrameHeight(), 0.0, 0.0));
		ps = new ParkingSpace(700, 85);
		this.setFocusable(false);

		if (getFuzzyDriver().getFis() == null) {
			System.err.println("Can't load file:");
			return;
		}
		 FunctionBlock fb = getFuzzyDriver().getFis().getFunctionBlock(null);

		// Show
		 JFuzzyChart.get().chart(fb);

		// Print ruleSet
		// System.out.println(getFuzzyDriver().getFis());

	}

	@Override
	public void paint(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(0, 0, frameWidth, frameHeight);
		g.setColor(Color.BLACK);
		g.drawString("Press SPACE to start/stop Car from moving", 1100, 800);
		g.drawString("Press UP to move the car", 1100, 820);
		g.drawString("Press R to start Recording", 1100, 840);
		g.drawString("Press S to stop Recording", 1100, 860);
		g.drawString("Press W to write fuzzy data", 1100, 880);
		g.drawString("Press P to prepare data for NN", 1100, 900);
		g.drawString("Press T to train network", 1100, 920);
		g.drawString("Press A to switch driver mode", 1100, 940);
		g.drawString("Current Driving mode is: " + currentDriver.name(), 1100, 100);

		ps.paint(g);
		getSimulatedCar().paint(g);
	}

	@Override
	public void run() {
		// reset();
		try {
			while (true) {
				// Set inputs
				switch (currentDriver) {
				case FUZZY:
					fd.calculate();
					//System.out.println("theta " + fd.getVariable("wheelOrientation"));
					sc.theta = fd.getVariable("wheelOrientation");
					Double fuzzyError = (Math.sqrt(Math.pow(getSimulatedCar().getCenterX() - ps.getX(), 2)
							+ Math.pow(getSimulatedCar().getCenterY() - ps.getY(), 2)));
					// System.out.println("E Error: " + fuzzyError);
					if (!(fuzzyError < 50) && move) {
						getSimulatedCar().move();
					} else if (randomPosition && move) {
						setNextCoordinates();
					}
					break;
				case NN:
					nnDriver.calculate();
					System.out.println("theta " + nnDriver.getTheta());
					sc.theta = nnDriver.getTheta();
					Double nnError = (Math.sqrt(Math.pow(getSimulatedCar().getCenterX() - ps.getX(), 2)
							+ Math.pow(getSimulatedCar().getCenterY() - ps.getY(), 2)));
					if (!(nnError < 50) && move) {
						getSimulatedCar().move();
					} else if (randomPosition && move) {
						setNextCoordinates();
					}
					break;
				}
				this.repaint();
				Thread.sleep(10);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			System.exit(1);
		}

	}

	private void reset() {
		// TODO Auto-generated method stub

	}

	public void move() {
		if (move) {
			move = false;
		} else {
			move = true;
		}
	}

	public void randomPosition() {
		if (randomPosition) {
			randomPosition = false;
		} else {
			randomPosition = true;
		}

	}

	private void setNextCoordinates() {
		int y = (int) (400 + (Math.random() * ((1000 - 400) + 1)));
		int x = (int) ((Math.random() * ((950) + 1)));
		double phi = Math.random() * 360;

		getSimulatedCar().setCoordinates(x, y);
		getSimulatedCar().setRotation(phi);
	}

	public SimulatedCar getCar() {
		return getSimulatedCar();
	}

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

	public SimulatedCar getSimulatedCar() {
		return sc;
	}

	public void setSimulatedCar(SimulatedCar sc) {
		this.sc = sc;
	}

	public FuzzyDriver getFuzzyDriver() {
		return fd;
	}

	public void setFuzzyDriver(FuzzyDriver fd) {
		this.fd = fd;
	}

	public void switchDriver() {
		if(currentDriver.equals(Driver.FUZZY)){
			currentDriver = Driver.NN;
		} else {
			currentDriver = Driver.FUZZY;
		}
	}

	public double getPositionError() {
		return getPositionError(sc, ps);
	}

	public NNDriver getNNDriver() {
		return nnDriver;
	}

	public ParkingSpace getParkingSpace() {
		return ps;
	}

	public Integer getPositionError(SimulatedCar sc, ParkingSpace ps) {
		return (sc.getCenterX() - ps.getX());// + (sc.getY() - ps.getY());
	}

	public static ParkingLot getInstance() {
		if (singleton == null) {
			try {
				singleton = new ParkingLot();
			} catch (IOException e1) {
				e1.printStackTrace();
			}

		}
		return singleton;
	}

}
