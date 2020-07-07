package br.com.facamp.com710.m05aula;

import java.awt.Image;

public abstract class Agent{
	int sizeX;
	int sizeY;
	boolean alive;
	int x;
	int y;
	int vx;
	int vy;
	Image image;
	
	public Agent(int x, int y) {
		this.x = x;
		this.y = y;
		alive = true;
		vx = 0;
		vy = 0;
	}
	
}
