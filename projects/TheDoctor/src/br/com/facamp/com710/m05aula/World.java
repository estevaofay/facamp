package br.com.facamp.com710.m05aula;

import java.util.ArrayList;
import java.util.List;

public class World {
	private static World singleton;
	public static World getInstance() {
		if(singleton==null) {
			singleton = new World();
		}
		return singleton;
	}
	
	int sizeX;
	int sizeY;
	List<Agent> agentList;
	//Tardis tardis;
	List<WorldChangeListener> worldChangeListenerList;
	
	private World() {
		agentList = new ArrayList<>();
		worldChangeListenerList = new ArrayList<>();
		sizeX = 500;
		sizeY = 500;
	}
	
	public void addWorldChangeListener(WorldChangeListener listener) {
		worldChangeListenerList.add(listener);
	}
	
	public void addAgent(Agent a) {
		agentList.add(a);
		for(WorldChangeListener listener : worldChangeListenerList) {
			listener.worldHasChanged();
		}
	}
	
	public boolean move(Agent agent, int dx, int dy) {
		if(!canMove(agent,dx,dy))
			return false;
		
		agent.x += dx;
		agent.y += dy;
		for(WorldChangeListener listener : worldChangeListenerList) {
			listener.worldHasChanged();
		}
		return true;
	}
	
	private boolean canMove(Agent agent, int dx, int dy) {
		//TODO
		return true;
	}
	
}
