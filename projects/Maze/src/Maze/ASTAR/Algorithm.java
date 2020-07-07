package Maze.ASTAR;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class Algorithm {

    private LinkedList<Node> fringe;

    private final Node startNode;
    private int closedMap[][];
    private final NodeComparator nc = new NodeComparator();
    private final Maze maze;

    public Algorithm(Node startNode, Maze maze) {
        this.startNode = startNode;
        this.maze = maze;

    }

    public boolean isInFringe(Node node) {
        for (Node node1 : fringe) {
            if (node.getState().equals(node1.getState())) {
                return true;
            }
        }
        return false;

    }

    public Node search() {
        fringe = new LinkedList<>();
        //closed = new HashSet<>();
        closedMap = new int[(maze.getNRows() * 4)][(maze.getNCols() * 2)];
       

        fringe.add(startNode);
        while (!fringe.isEmpty()) {
            Node auxNode = fringe.remove(0);

            if (auxNode.isGoal()) {
                LinkedList<Node> list = new LinkedList<>();
                list.addAll(auxNode.solvedTree());

                for (Node node : list) {
                    auxNode.getState().getMaze().addPoint(
                            node.getX(), node.getY());
                }
                auxNode.getState().getMaze().display();

                return auxNode;
            }

            Set<Node> possibleMoves = new HashSet<>();
            possibleMoves.addAll(auxNode.expand());

            closedMap[auxNode.getX()][auxNode.getY()] = (int) auxNode.f();

            for (Node position : possibleMoves) {
                //try{
//                System.out.println("-----------");
//                System.out.println("XY");
//                   System.out.println(position.getX());
//                   System.out.println(position.getY());
//                   System.out.println("----------");
//                   System.out.println("Size");
//                   System.out.println(closedMap.length);
//                   System.out.println(closedMap[0].length);
                if (closedMap[position.getX()][position.getY()] == 0) {
                    closedMap[position.getX()][position.getY()] = (int)position.f();
                    position.setParent(auxNode);
                    int index = Collections.binarySearch(fringe, position, nc);

                    if (index < 0) {
                        fringe.add((-index - 1), position);
                    } else {
                        fringe.add(index, position);
                    }

                }
//                } catch (Exception e) {
//                    System.out.println(e.toString());
//                    int length = closedMap.length;
//                    System.out.println(length);
//                    
//                }
            }
            

        }

        return null;
    }
}
