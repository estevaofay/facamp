package Maze.ASTAR;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class Node {
    private final Ponto state;
    private Node parent;
    private Integer depth;

    public void setParent(Node parent) {
        this.depth = parent.getDepth() + 1 ;
        this.parent = parent;
    }

    public Integer getDepth() {
        return depth;
    }
    
    public Node getParent() {
        return parent;
    }
    
    public Node(Node parent, Ponto state){
        this.state = state;
        this.parent = parent;
        
        if(parent != null) {
            this.depth = parent.depth + 1;
        } else {
            this.depth = 0;
        }
        
    }
    public LinkedList solvedTree() {
        LinkedList<Node> list = new LinkedList<>();

        
        Node aux = this;
        while (aux.getParent() !=null){
            list.addFirst(aux);
            aux = aux.getParent();
        }
        
        return list;
    }
    public int getX(){
        return state.getX();
    }
    public int getY(){
        return state.getY();
    }
    
    @Override
    public boolean equals(Object o) {
        Node node = (Node) o;
        if((state.getX() == node.getX() && state.getY() == node.getY())){
            return true;
        } 
        return false;
    }
    
    public double f(){
        return this.depth + this.state.h();
    }
    
    public boolean isGoal() {
        return state.isGoal();
    }

    public Ponto getState() {
        return state;
    }
    
    public Maze getMaze() {
        return this.state.getMaze();
    }

    public Set<Node> expand() {
        Set<Node> ret = new HashSet<>();
        Set<Ponto> aux = new HashSet<>();
        aux.addAll(this.getState().getPossibleMoves());
        
        for (Ponto point : aux) {
            
            Node auxNode = new Node(this, point);
            
            ret.add(point.getNode());
        }
        return ret;
    }
}
