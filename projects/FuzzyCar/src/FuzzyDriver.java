import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import net.sourceforge.jFuzzyLogic.FIS;

public class FuzzyDriver {
	private final FIS fis = FIS.load("fcl/driver.fcl",true);
	private PrintWriter pw;
	private StringBuilder sb;
	
	public enum collectingData {TRUE, FALSE};
	private collectingData isCollectingData = collectingData.FALSE;
	
	public FuzzyDriver(){
		sb = new StringBuilder();
		
	}
	
	public void calculate(){
		this.setVariable("carOrientation", ParkingLot.getInstance().getSimulatedCar().getPhi());
		int error =  (int) ParkingLot.getInstance().getPositionError();
		double phi = ParkingLot.getInstance().getSimulatedCar().getPhi();
		double theta = ParkingLot.getInstance().getSimulatedCar().getTheta();
        this.setVariable("positionError", (double)error); 
        
		switch(isCollectingData) {
		case TRUE:
			sb.append(phi);
	        sb.append(',');
	        
	        //sb.append(ParkingLot.getInstance().getSimulatedCar().getCenterX());
	        sb.append(error);
            sb.append(',');
            
            evaluate();
            
            sb.append(theta);
            sb.append('\n');
			break;
		case FALSE:
			evaluate();
			break;
		}
		
	}
	
	public void startCollectingData() throws IOException{
		pw = new PrintWriter(new File("fuzzyData.csv"));
		isCollectingData = collectingData.TRUE;
	}
	
	public void stopCollectingData(){
		isCollectingData = collectingData.FALSE;
	}
	
	public void writeData(){
		pw.write(sb.toString());
		pw.close();
	}
	public FIS getFis(){
		return fis;
	}
	
	public void setVariable(String varName, Double value){
		fis.setVariable(varName, value);
	}
	
	public void evaluate(){
		fis.evaluate();
		
	}
	
	public double getVariable(String varName){
		return fis.getVariable(varName).getValue();
	}
	
}
