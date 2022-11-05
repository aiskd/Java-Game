import bagel.Image;
import bagel.util.Rectangle;

public class Demon extends Enemies{
    private Image LEFT_DEMON_IMAGE = new Image("project-2-skeleton/res/demon/demonLeft.png");
    private Image RIGHT_DEMON_IMAGE = new Image("project-2-skeleton/res/demon/demonRight.png");
    private Image INVI_RIGHT_DEMON_IMAGE = new Image("project-2-skeleton/res/demon/demonInvincibleRight.png");
    private Image INVI_LEFT_DEMON_IMAGE = new Image("project-2-skeleton/res/demon/demonInvincibleLeft.png");
    private int horizontal = 0;
    private int vertical = 0;
    private boolean facingLeft;
    private boolean firstDir = true;
    private  int MAX_HEALTH = 40;
    private int currentHealth = MAX_HEALTH;
    private final int FONT_SIZE = 15;
    private boolean invincible = false;
    private final int DAMAGE_POINTS = 20;
    private int radius = 150;
    Fire fire = new Fire();
    Time invincibilityTime = new Time(3000);


    public Demon(double xPos, double yPos) {
        super(xPos, yPos);
        boundaryRectangle(LEFT_DEMON_IMAGE);
    }

    public Demon() {
    }

    public void setInvincible(boolean invincible) {
        this.invincible = invincible;
    }

    /**
     * Logic when demon dies
     */
    public boolean dead(){
        if(currentHealth <=0){
            return true;
        }
        return false;
    }

    /**
     * Rendering demon images
     */
    public void drawDemon(){
        if (firstDir){
            if(getDirection() == 1){
                vertical = -1;
            } else if (getDirection() == 2){
                vertical = 1;
            } else if (getDirection() == 3){
                horizontal = -1;
                facingLeft = true;
            } else{
                horizontal = 1;
                facingLeft = false;
            }
            firstDir = false;
        }


        if(horizontal == -1){
            facingLeft = true;
        } else {
            facingLeft = false;
        }

        if(facingLeft && !invincible){
            LEFT_DEMON_IMAGE.drawFromTopLeft(getxPos(),getyPos());
            boundaryRectangle(LEFT_DEMON_IMAGE);
        } else if (!facingLeft && !invincible) {
            RIGHT_DEMON_IMAGE.drawFromTopLeft(getxPos(), getyPos());
            boundaryRectangle(RIGHT_DEMON_IMAGE);
        } else if (facingLeft && invincible){
            INVI_LEFT_DEMON_IMAGE.drawFromTopLeft(getxPos(),getyPos());
            boundaryRectangle(INVI_LEFT_DEMON_IMAGE);
        } else if (!facingLeft & invincible){
            INVI_RIGHT_DEMON_IMAGE.drawFromTopLeft(getxPos(),getyPos());
            boundaryRectangle(INVI_LEFT_DEMON_IMAGE);

        }

        if (!isPassiveType()){
            setyPos(getyPos()+getSpeed()*vertical);
            setxPos(getxPos()+getSpeed()*horizontal);
        }
        renderHealth(currentHealth, MAX_HEALTH, FONT_SIZE,getxPos(),getyPos()-6);

        center(LEFT_DEMON_IMAGE);

    }

    /**
     * When demon gets damaged by player and sets up invincibility
     */
    public void damaged(){
        currentHealth -= DAMAGE_POINTS;
    }

    /**
     * Boundary box for demon
     */
    public Rectangle boundaryDemon(){
        return boundaryRectangle(LEFT_DEMON_IMAGE);
    }

    public int getHorizontal() {
        return horizontal;
    }

    public void setHorizontal(int horizontal) {
        this.horizontal = horizontal;
    }

    public int getVertical() {
        return vertical;
    }

    public void setVertical(int vertical) {
        this.vertical = vertical;
    }

    public Image getLEFT_DEMON_IMAGE() {
        return LEFT_DEMON_IMAGE;
    }

    public void setLEFT_DEMON_IMAGE(Image LEFT_DEMON_IMAGE) {
        this.LEFT_DEMON_IMAGE = LEFT_DEMON_IMAGE;
    }

    public void setRIGHT_DEMON_IMAGE(Image RIGHT_DEMON_IMAGE) {
        this.RIGHT_DEMON_IMAGE = RIGHT_DEMON_IMAGE;
    }

    /**
     *Logic when player gets hit by Fire or gets close to demon's attack radius
     */
    public boolean checkFire(Player player, boolean navec){
//        Calculating distance from player to demon
        double distance = Math.sqrt(Math.pow((player.getxCenter()-xCenter),2) + Math.pow((player.getyCenter()-yCenter),2));
//        Rendering fire position depending on the player's position
        if(distance <= radius){
            if(navec){
                fire.setFIRE_DMG(20);
                fire.setDEM_FIRE_IMAGE(new Image("project-2-skeleton/res/navec/navecFire.png"));
            }
            if((player.getxCenter() <= xCenter)&&(player.getyCenter()<=yCenter)){
                fire.renderFire(true,true, getxPos(), getyPos());
            } else if ((player.getxCenter()<=xCenter) && (player.getyCenter() > yCenter)){
                fire.renderFire(false,true, getxPos(), getyPos()+LEFT_DEMON_IMAGE.getHeight());
            } else if ((player.getxCenter() > xCenter) && (player.getyCenter()<=yCenter)){
                fire.renderFire(true,false, getxPos()+LEFT_DEMON_IMAGE.getWidth(), getyPos());
            } else if ((player.getxCenter() > xCenter) && (player.getyCenter()>yCenter)){
                fire.renderFire(false,false, getxPos()+LEFT_DEMON_IMAGE.getWidth(), getyPos()+LEFT_DEMON_IMAGE.getHeight());
            }


//            Fire damage logic onto player
            if (player.boundaryPlayer().intersects(fire.boundaryFire())){
                if(!player.isInvincible()){
                    player.damage(fire.getFIRE_DMG());
                    if (navec){
                        System.out.println("Navec inflicts " + fire.getFIRE_DMG() + " damage points on Fae. " +
                                "Fae's current health: " + player.getCurrentHealth() + "/" + player.getMAX_HEALTH_POINTS());
                    } else {
                        System.out.println("Demon inflicts " + fire.getFIRE_DMG() + " damage points on Fae. " +
                                "Fae's current health: " + player.getCurrentHealth() + "/" + player.getMAX_HEALTH_POINTS());
                    }
                    player.setInvincible(true);
                    invincibilityTime.startCD();
                }
            }

            if (invincibilityTime.isStart()){
                invincibilityTime.timer();
            } else {
                player.setInvincible(false);
            }

            return true;
        }
        return false;
    }

    public void turnInvincible(){

    }

    public int getDAMAGE_POINTS() {
        return DAMAGE_POINTS;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public int getMAX_HEALTH() {
        return MAX_HEALTH;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public void setMAX_HEALTH(int MAX_HEALTH) {
        this.MAX_HEALTH = MAX_HEALTH;
    }

    public boolean isInvincible() {
        return invincible;
    }

    public void setINVI_RIGHT_DEMON_IMAGE(Image INVI_RIGHT_DEMON_IMAGE) {
        this.INVI_RIGHT_DEMON_IMAGE = INVI_RIGHT_DEMON_IMAGE;
    }

    public void setINVI_LEFT_DEMON_IMAGE(Image INVI_LEFT_DEMON_IMAGE) {
        this.INVI_LEFT_DEMON_IMAGE = INVI_LEFT_DEMON_IMAGE;
    }

    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }
}
