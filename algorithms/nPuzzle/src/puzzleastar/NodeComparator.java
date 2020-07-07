/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puzzleastar;

import java.util.Comparator;

/**
 *
 * @author estevaofay
 */
public class NodeComparator implements Comparator<Node>{

    @Override
    public int compare(Node o1, Node o2) {
        if(o1.f() < o2.f()) {
            return -1;
        } else if (o1.f() == o2.f()) {
            return 0;
        } else {
            return 1;
        }
    }
    
}
