import java.util.Random;

public class Enemies extends Character{
    Random rd = new Random();
//    1 would indicate Up, 2 would indicate down, 3 indicates left, 4 indicates right
    private int direction = rd.nextInt(4) + 1;
    private boolean passiveType = rd.nextBoolean();
    private final double MAX_SPEED = 0.7;
    private final double MIN_SPEED = 0.2;
    private double speed = (double) (Math.random()*(MAX_SPEED-MIN_SPEED)) + MIN_SPEED;
    public Enemies(double xPos, double yPos) {
        super(xPos, yPos);
    }

    public Enemies() {
    }

    public int getDirection() {
        return direction;
    }

    public boolean isPassiveType() {
        return passiveType;
    }

    public void setPassiveType(boolean passiveType) {
        this.passiveType = passiveType;
    }

    public double getSpeed() {
        return speed;
    }


}
