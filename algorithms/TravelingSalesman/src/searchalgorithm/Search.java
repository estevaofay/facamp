package searchalgorithm;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 *
 * @author estevaofay
 */
public class Search {

    private LinkedList<City> fringe;
    private Set<City> closed;
    private Map map;
    private City start;
    private Boolean breadth;
    
    public Search(Map m, City start, boolean breadth){
        this.map = m;
        this.start = start;
        this.breadth = breadth;
    }
    
    public City search(){
        fringe = new LinkedList<>();
        closed = new HashSet<>();
        fringe.add(start);
        
        while(!fringe.isEmpty()){
            City aux = fringe.remove(0);
            
            if(aux.isGoal()){
                return aux;
            }
            
            Set<City> destinations = aux.possibleDestinations();
            closed.add(aux);
            for (City destination : destinations) {
                
                if(!closed.contains(destination)){
                    destination.getNode().setParent(aux.getNode());
                    if (!breadth)
                        fringe.addFirst(destination);
                    else
                        fringe.addLast(destination);
                }
            }
        }
        return null;
    }
    
    public static void main(String[] args) {
       //Inicialiso o mapa que contém os grafos
       Map nodeMap = new Map();
       //Atribui à city start a primeira cidade S do grafo
       City startCity = nodeMap.getCity("S");
       Node startNode = new Node(startCity);
       
       Search s = new Search(nodeMap,startCity,true); //Inicializa o método de busca com tudo o que ele precisa
       
       City destination = s.search(); //Atribua a destination se achou a cidade
       
       if(destination == null)
            System.out.println("Cidade não encontrada");
       else{
            System.out.println("Cidade encontrada: " + destination.getName());
            
            LinkedList<Node> printList = new LinkedList<>();
            Node aux = destination.getNode();
            while(aux.getParent() != null){
                printList.addFirst(aux);
                aux = aux.getParent();
            }
            System.out.print(startNode.getName());
            for(Node node : printList) {
                System.out.print("  ->  " + node.getName());                
            }
            System.out.println("");  
       }  
    }
}
