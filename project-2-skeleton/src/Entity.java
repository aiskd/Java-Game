import bagel.*;
import bagel.util.Point;
import bagel.util.Rectangle;

public class Entity {
    private double xPos, yPos;
    double xCenter, yCenter;

    private Rectangle boundaryBox;
    public Image img;

    public Entity() {
        this.xPos = 0;
        this.yPos = 0;
    }

    public Entity(double xPos, double yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public Entity(double xPos, double yPos, Image img){
        this.xPos = xPos;
        this.yPos = yPos;
        this.img = img;
    }

    public double getxPos() {
        return xPos;
    }

    public void setxPos(double xPos) {
        this.xPos = xPos;
    }

    public double getyPos() {
        return yPos;
    }

    public void setyPos(double yPos) {
        this.yPos = yPos;
    }

    /**
     * Finding center positions of image
     */
    public void center(Image img){
        xCenter = xPos + (img.getWidth()/2);
        yCenter = yPos + (img.getHeight() /2);
    }


    /**
     * Drawing rectangles around images
     */
    public Rectangle boundaryRectangle(Image img){
        center(img);
        boundaryBox =  img.getBoundingBoxAt(new Point(xCenter, yCenter));
        return boundaryBox;
    }

    public double getxCenter() {
        return xCenter;
    }

    public double getyCenter() {
        return yCenter;
    }

    public void render(){

    }


}
