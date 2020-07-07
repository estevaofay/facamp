package br.com.facamp.com710.m05aula;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class WorldFrame extends JFrame {
	public WorldFrame(WorldPanel panel) {
		this.setTitle("The Doctor");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(500, 500);
		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().add(panel);
		this.setVisible(true);
	}
}
