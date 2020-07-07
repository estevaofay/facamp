package br.com.facamp.com710.m05aula;

import javax.swing.ImageIcon;

public class Dalek extends Agent implements Runnable {

	public Dalek(int x, int y) {
		super(x,y);
		
		ImageIcon icon = new ImageIcon("dalek.gif");
		this.image = icon.getImage();
		this.sizeX = icon.getIconWidth();
		this.sizeY = icon.getIconHeight();		
	}

	@Override
	public void run() {
		while(alive) {
			for (int i=0; i<100; i++) {
				World.getInstance().move(this, 1, 1);
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	
}
