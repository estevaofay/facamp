package puzzleastar;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author estevaofay
 */
public class SearchAlgorithm {

    private LinkedList<Node> fringe;
    private HashSet<Node> closed;
    private final Node startNode;
    private final NodeComparator nc = new NodeComparator();

    public SearchAlgorithm(Node startNode) {
        this.startNode = startNode;

    }

    public boolean isInClosedList(Node node) {
        for (Node node1 : closed) {
            if (node1.equals(node)) {
                return true;
            }
        }
        return false;
    }

    public Node search() {
        //fazer aqui logica da busca
        fringe = new LinkedList<>();
        closed = new HashSet<>();

        fringe.add(startNode);

        while (!startNode.getState().isSolvable()) {
            startNode.getState().Scramble();
        }

        while (!fringe.isEmpty()) {
            //System.out.println("FRINGE: " + fringe.size());
            Node auxNode = fringe.remove(0);

            if (auxNode.isGoal()) {
                System.out.println(auxNode.printTree());
                return auxNode;
            }

            Set<Node> possibleMoves = new HashSet<>();
            possibleMoves.addAll(auxNode.expand());

            closed.add(auxNode);
            //System.out.println("CLOSED: " + closed.size());

            for (Node position : possibleMoves) {

                if (!isInClosedList(position)) {
                    position.setParent(auxNode);
                    int index = Collections.binarySearch(fringe, position, nc);
                    //System.out.println("---------------------");
                    if (index < 0) {
                        fringe.add((-index - 1), position);
                    } else {
                        fringe.add(index, position);
                    }

                }
            }

        }

        return null;
    }

}
