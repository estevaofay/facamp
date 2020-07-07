package br.com.facamp.com710.m05aula;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class WorldPanel extends JPanel implements WorldChangeListener, MouseListener {
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		setBackground(Color.WHITE);
		
		World w = World.getInstance();
		for(Agent a : w.agentList) {
			g.drawImage(a.image, a.x, a.y, this);
		}
		
	}

	@Override
	public void worldHasChanged() {
		repaint();		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		// TODO IMPLEMENTAR AQUI CODIGO PARA MOVER A TARDIS
	}

	@Override
	public void mousePressed(MouseEvent e) {
		//Ignorar		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		//Ignorar		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		//Ignorar		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		//Ignorar		
	}
}
