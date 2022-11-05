import bagel.Image;
import bagel.util.Point;
import bagel.util.Rectangle;

public class Sinkhole extends Entity{
    private final static int DAMAGE_POINTS = 30;
    private final Image SINKHOLE_IMAGE = new Image("project-2-skeleton/res/sinkhole.png");



    public Sinkhole(int xPos, int yPos) {
        super(xPos, yPos);
    }


    /**
     * Drawing sinkhole
     */
    public void drawHole(){
        SINKHOLE_IMAGE.drawFromTopLeft(super.getxPos(), super.getyPos());
        boundaryRectangle(SINKHOLE_IMAGE);
    }

    /**
     * Making boundary box for sinkhole
     */
    public Rectangle boundaryHole(){
        return boundaryRectangle(SINKHOLE_IMAGE);
    }

    public int getDAMAGE_POINTS() {
        return DAMAGE_POINTS;
    }
}
