import bagel.*;
import bagel.util.Colour;
import bagel.util.Rectangle;

public class Player extends Character{
    private final static String FAE_LEFT = "project-2-skeleton/res/fae/faeLeft.png";
    private final static String FAE_RIGHT = "project-2-skeleton/res/fae/faeRight.png";
    private final static String FAE_ATTACK_RIGHT = "project-2-skeleton/res/fae/faeAttackRight.png";
    private final static String FAE_ATTACK_LEFT = "project-2-skeleton/res/fae/faeAttackLeft.png";

    private final  int MAX_HEALTH_POINTS = 100;
    private final static int MOVE_SIZE = 2;
    private  int currentHealth;
    private boolean right = true;
    private Image currentImage = new Image(FAE_RIGHT);

    private final static int HEALTH_X = 20;
    private final static int HEALTH_Y = 25;
    private double prevX;
    private double prevY;
    private final int FONT_SIZE = 30;
    private final double  imageHeight = currentImage.getHeight();
    private final double imageWidth = currentImage.getWidth();
    private final int WIN_X = 950;
    private final int WIN_Y = 670;
    private boolean invincible = false;
    private boolean attack = false;
    private boolean attackCD = false;
    private boolean startTime=  false;
    Time cdTime = new Time(2000);
    Time attackTime = new Time(1000);


    public Player() {
    }

    public Player(int x, int y) {
        super(x, y);
        currentHealth = MAX_HEALTH_POINTS;
        this.currentImage = new Image(FAE_RIGHT);
        boundaryRectangle(currentImage);
    }

    /**
     * Drawing player depending on user input
     */
    public void drawPlayer(Input input){

        if (right) {
            currentImage = new Image(FAE_RIGHT);
        } else {
            currentImage = new Image(FAE_LEFT);
        }
//        Player movement logic
        if (input.isDown(Keys.UP)){
            prevY = getyPos();
            prevX = getxPos();
            move(0, -MOVE_SIZE);
        } else if (input.isDown(Keys.DOWN)){
            prevY = getyPos();
            prevX = getxPos();
            move(0, MOVE_SIZE);

        } else if (input.isDown(Keys.LEFT)){
            prevY = getyPos();
            prevX = getxPos();
            move(-MOVE_SIZE,0);
            if (right) {
                this.currentImage = new Image(FAE_LEFT);
                right = !right;
            }
        } else if (input.isDown(Keys.RIGHT)){
            prevY = getyPos();
            prevX = getxPos();
            move(MOVE_SIZE,0);
            if (!right) {
                this.currentImage = new Image(FAE_RIGHT);
                right = !right;
            }

        }

        if (input.isDown(Keys.A) && !attackCD){
//            Player attack logic
            attack = attack();
            attackTime.startCD();
        }

        if(attack){
            attack();
            attackTime.timer();
            if(!attackTime.isStart()){
                attack = false;
                attackCD = true;
                cdTime.startCD();
            }
        }


        if (attackCD){
            attack = false;
            cdTime.timer();
        }


        if (!cdTime.isStart() && attackCD){
            attackCD = false;
        }

        this.currentImage.drawFromTopLeft(getxPos(), getyPos());

        renderHealth(currentHealth,MAX_HEALTH_POINTS,FONT_SIZE,HEALTH_X,HEALTH_Y);
        center(currentImage);
    }

    /**
     * Allowing player to move around
     */
    public void move(int x , int y){
        setxPos(getxPos()+x);
        setyPos(getyPos()+y);
    }


    /**
     * Allowing player to attack and rendering attack images
     */
    public boolean attack(){
//        Rendering attack image only for 1 second
        if (right){
            this.currentImage = new Image(FAE_ATTACK_RIGHT);
        } else {
            this.currentImage = new Image(FAE_ATTACK_LEFT);
        }
        this.currentImage.drawFromTopLeft(getxPos(), getyPos());
        return true;
    }

    public boolean isAttack() {
        return attack;
    }

    /**
     * Boundary box for players
     */
    public Rectangle boundaryPlayer(){
        return boundaryRectangle(currentImage);
    }

    /**
     * Stopping player movement when player collides with obstacles
     */
    public void stop(){
        setxPos(prevX);
        setyPos(prevY);
    }

    public void setInvincible(boolean invincible) {
        this.invincible = invincible;
    }

    public boolean isInvincible() {
        return invincible;
    }

    public double getImageHeight() {
        return imageHeight;
    }

    public double getImageWidth() {
        return imageWidth;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    /**
     *Win Conditions in Level 0
     */
    public boolean win(){
        if((getyPos() >= WIN_Y ) && (getxPos() >= WIN_X)){
            return true;
        }
        return false;
    }

    /**
     * Lose conditions for player
     */
    public boolean lose(){
        if (currentHealth <= 0 ){
            return true;
        }
        return false;
    }

    /**
     * Logic when player gets damaged from other objects or characters
     */
    public void damage(int points){
        currentHealth -= points;

    }

    public int getMAX_HEALTH_POINTS() {
        return MAX_HEALTH_POINTS;
    }
}
