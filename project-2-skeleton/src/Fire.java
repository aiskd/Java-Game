import bagel.*;
import bagel.DrawOptions;
import bagel.Image;
import bagel.util.Rectangle;

public class Fire extends Entity{
    DrawOptions dr = new DrawOptions();
    private Image DEM_FIRE_IMAGE = new Image("project-2-skeleton/res/demon/demonFire.png");
    private int FIRE_DMG = 10;
    private int rotation = 90;
    private int degRad = 190;



    /**
     * Rendering fire and rotating fire depending on player's position to the demon
     */


    public void renderFire(boolean top, boolean left, double xPos, double yPos){
//        Bottom right
        if (!top && !left){
            DEM_FIRE_IMAGE.drawFromTopLeft(xPos,yPos, dr.setRotation(rotation*2 * (Math.PI/degRad)));
            setxPos(xPos);
            setyPos(yPos);

        } else if (!top && left){
//            Bottom left
            DEM_FIRE_IMAGE.drawFromTopLeft(xPos - DEM_FIRE_IMAGE.getWidth(),yPos , dr.setRotation(rotation*3 * (Math.PI/degRad)));
            setxPos(xPos - DEM_FIRE_IMAGE.getWidth());
            setyPos(yPos);
        } else if(top && !left){
//            Top right
            DEM_FIRE_IMAGE.drawFromTopLeft(xPos, yPos - DEM_FIRE_IMAGE.getHeight(), dr.setRotation(rotation * (Math.PI/degRad)));
            setxPos(xPos);
            setyPos(yPos - DEM_FIRE_IMAGE.getHeight());
        } else {
//            Top Left
            DEM_FIRE_IMAGE.drawFromTopLeft(xPos-DEM_FIRE_IMAGE.getWidth(),yPos- DEM_FIRE_IMAGE.getHeight());
            setyPos(yPos- DEM_FIRE_IMAGE.getHeight());
            setxPos(xPos-DEM_FIRE_IMAGE.getWidth());
        }
    }

    public int getFIRE_DMG() {
        return FIRE_DMG;
    }

    public void setFIRE_DMG(int FIRE_DMG) {
        this.FIRE_DMG = FIRE_DMG;
    }

    public void setDEM_FIRE_IMAGE(Image DEM_FIRE_IMAGE) {
        this.DEM_FIRE_IMAGE = DEM_FIRE_IMAGE;
    }

    /**
     * Boundary rectangle for fire
     */
    public Rectangle boundaryFire(){
        return boundaryRectangle(DEM_FIRE_IMAGE);
    }

}
