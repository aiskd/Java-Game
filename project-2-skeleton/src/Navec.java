import bagel.Image;

public class Navec extends Demon{
    private final Image LEFT_NAVEC_IMAGE = new Image("project-2-skeleton/res/navec/navecLeft.png");
    private final Image RIGHT_NAVEC_IMAGE = new Image("project-2-skeleton/res/navec/navecRight.png");
    private final Image INVI_LEFT_NAVEC_IMAGE = new Image("project-2-skeleton/res/navec/navecInvincibleLeft.png");
    private final Image INVI_RIGHT_NAVEC_IMAGE = new Image("project-2-skeleton/res/navec/navecInvincibleRight.png");
    public Navec(int xPos, int yPos) {
        super(xPos, yPos);
        setPassiveType(false);
        setLEFT_DEMON_IMAGE(LEFT_NAVEC_IMAGE);
        setRIGHT_DEMON_IMAGE(RIGHT_NAVEC_IMAGE);
        setRadius(200);
        setMAX_HEALTH(80);
        setINVI_RIGHT_DEMON_IMAGE(INVI_RIGHT_NAVEC_IMAGE);
        setINVI_LEFT_DEMON_IMAGE(INVI_LEFT_NAVEC_IMAGE);
        setCurrentHealth(80);
    }

    public Navec() {
        setPassiveType(false);
        setLEFT_DEMON_IMAGE(LEFT_NAVEC_IMAGE);
        setRIGHT_DEMON_IMAGE(RIGHT_NAVEC_IMAGE);
    }




}
