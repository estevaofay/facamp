package br.com.facamp.com710.m05aula;

public class App {
	public static void main(String[] x) {
		WorldPanel panel = new WorldPanel();
		WorldFrame frame = new WorldFrame(panel);
		frame.setVisible(true);
		World.getInstance().addWorldChangeListener(panel);

		for (int i=0; i<40; i++) {
			Dalek d = new Dalek((int)(Math.random()*500),(int)(Math.random()*500));
			World.getInstance().addAgent(d);
			
			Thread t = new Thread(d);
			t.start();
		}		
		

	}
}
