import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class KeyboardListener implements KeyListener{

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
        case 39: //RIGHT KEY
            //ParkingLot.getInstance().getCar().turnRight();
            break;
        case 37: //LEFT KEY
            //ParkingLot.getInstance().testNetwork();
            break;
        case 38://UP KEY
            ParkingLot.getInstance().getSimulatedCar().move();
            break;    
        case 40://DOWN KEY
            break;
        case 65://A KEY
        	
				//ParkingLot.getInstance().getNNDriver().testNetwork();
        		ParkingLot.getInstance().switchDriver();
          break;
        case 32://SPACE KEY
        	ParkingLot.getInstance().move();
          break;
        case 87://W KEY
        	ParkingLot.getInstance().getFuzzyDriver().writeData();
          break;
        case 82://R KEY
        	try {
				ParkingLot.getInstance().getFuzzyDriver().startCollectingData();
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
          break;
        case 83://S KEY
        	ParkingLot.getInstance().getFuzzyDriver().stopCollectingData();
          break;
        case 84://T KEY
        	try {
				ParkingLot.getInstance().getNNDriver().trainNetwork();
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
          break;
        case 80://P KEY
        	try {
				ParkingLot.getInstance().getNNDriver().prepareData();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        break;
		}
		//System.out.println(e.getKeyCode());
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
