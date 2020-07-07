package Maze.ASTAR;

public class Search {

    public static void main(String[] args) {
        //int x = args.length >= 1 ? Integer.parseInt(args[0]) : 8;
        //int y = args.length == 2 ? Integer.parseInt(args[1]) : 8;
        int x = 8;
        int y = 8;

        Ponto origin = new Ponto(1, 1);
        Ponto destination = new Ponto((x*4) - 1, (y*2)-1);
        Maze maze = new Maze(x, y, 1,1, destination.getX(), destination.getY());
        origin.setMaze(maze);
        destination.setMaze(maze);

        maze.display();

        maze.addPoint(origin.getX(), origin.getY());

        Algorithm a = new Algorithm(origin.getNode(), maze);
        Node solved = a.search();
        

    }
}
