import bagel.*;
import bagel.util.Point;
import bagel.util.Rectangle;

public class Obstacle extends Entity{


    private final Image TREE_IMG = new Image("project-2-skeleton/res/tree.png");
    private final Image WALL_IMG = new Image("project-2-skeleton/res/wall.png");
    public Obstacle(int xPos, int yPos) {
        super(xPos, yPos);
    }

    /**
     * Drawing obstacle for level 1
     */
    public void drawTree(){
        TREE_IMG.drawFromTopLeft(getxPos(), getyPos());
        boundaryRectangle(TREE_IMG);
    }

    /**
     * Drawing obstacle for level 0
     */
    public void drawWall(){
        WALL_IMG.drawFromTopLeft(getxPos(), getyPos());
        boundaryRectangle(WALL_IMG);
    }

    /**
     * Making boundary box for Walls
     */
    public Rectangle boundaryWall(){
        return boundaryRectangle(WALL_IMG);
    }

    /**
     * Making boundary box for trees
     */
    public Rectangle boundaryTree(){
        return boundaryRectangle(TREE_IMG);
    }


}
