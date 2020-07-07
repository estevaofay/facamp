/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puzzleastar;
/**
 *
 * @author estevaofay
 */
public class Search {

    public static void main(String[] args) {

        Puzzle startPuzzle = new Puzzle(3);
        SearchAlgorithm s = new SearchAlgorithm(startPuzzle.getNode());
        Node solved = s.search();

    }
}
