package Maze.ASTAR;

import java.awt.geom.Point2D;
import java.util.HashSet;
import java.util.Set;

public class Ponto {

    private int x;
    private int y;
    private Maze maze;

    public enum Direction {
        UP, DOWN, LEFT, RIGHT, UPRIGHT, UPLEFT, DOWNRIGHT, DOWNLEFT
    }

    private Node node = new Node(null, this);

    public Ponto(int x, int y) {
        this.x = x;
        this.y = y;

    }

    public Ponto(int x, int y, Maze maze) {
        this.x = x;
        this.y = y;
        this.maze = maze;

    }

    public void setMaze(Maze maze) {
        this.maze = maze;
    }

    public Ponto(Ponto point) {
        this.x = point.getX();
        this.y = point.getY();
        this.maze = point.getMaze();
    }

    public Maze getMaze() {
        return maze;
    }

    public Node getNode() {
        return this.node;
    }

    public boolean isGoal() {
        return this.h() == 0;
    }

    public double h() {
        return Point2D.distance(
                maze.getDestinationX(), maze.getDestinationY(), this.x, this.y);
    }

    public Set<Ponto> getPossibleMoves() {
        Set<Ponto> ret = new HashSet<>();

        Ponto upMove = this.move(Direction.UP);
        Ponto downMove = this.move(Direction.DOWN);
        Ponto leftMove = this.move(Direction.LEFT);
        Ponto rightMove = this.move(Direction.RIGHT);
        Ponto upRightMove = this.move(Direction.UPRIGHT);
        Ponto downRightMove = this.move(Direction.DOWNRIGHT);
        Ponto upLeftMove = this.move(Direction.UPLEFT);
        Ponto downLeftMove = this.move(Direction.DOWNLEFT);

        if (upMove != null) {
            ret.add(upMove);
        }
        if (downMove != null) {
            ret.add(downMove);
        }
        if (leftMove != null) {
            ret.add(leftMove);
        }
        if (rightMove != null) {
            ret.add(rightMove);
        }

        if (upRightMove != null) {
            ret.add(upRightMove);
        }
        if (downLeftMove != null) {
            ret.add(downLeftMove);
        }
        if (upLeftMove != null) {
            ret.add(upLeftMove);
        }
        if (downRightMove != null) {
            ret.add(downRightMove);
        }

        return ret;
    }

    public boolean equals(Ponto ponto) {
        return this.getX() == ponto.getX() && this.getY() == ponto.getY();
    }

    public boolean hitsWall() {
        Boolean isEmpty = maze.isEmpty(getX(), getY());
        return isEmpty;

    }

    public Ponto move(Direction d) {
        Ponto movedPoint = new Ponto(this);

        switch (d) {
            case UP:
                movedPoint.setY(movedPoint.getY() - 1);
                break;
            case DOWN:
                movedPoint.setY(movedPoint.getY() + 1);
                break;
            case LEFT:
                movedPoint.setX(movedPoint.getX() - 1);
                break;
            case RIGHT:
                movedPoint.setX(movedPoint.getX() + 1);
                break;
            case UPRIGHT:
                movedPoint.setX(movedPoint.getX() + 1);
                movedPoint.setY(movedPoint.getY() - 1);
                break;
            case UPLEFT:
                movedPoint.setX(movedPoint.getX() - 1);
                movedPoint.setY(movedPoint.getY() - 1);
                break;
            case DOWNRIGHT:
                movedPoint.setX(movedPoint.getX() + 1);
                movedPoint.setY(movedPoint.getY() + 1);
                break;
            case DOWNLEFT:
                movedPoint.setX(movedPoint.getX() - 1);
                movedPoint.setY(movedPoint.getY() + 1);
                break;

        }
        if (maze.isEmpty(movedPoint.x,movedPoint.y)) {

            return movedPoint;
        }
        return null;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

}
