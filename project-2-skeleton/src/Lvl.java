import bagel.Font;
import bagel.Image;
import bagel.Window;

public class Lvl {
    private final static int TITLE_SIZE = 75;
    private  Image BACKGROUND_IMAGE = new Image("project-2-skeleton/res/background0.png");
    private final Font TITLE_FONT = new Font("project-2-skeleton/res/frostbite.ttf", TITLE_SIZE);
    private final static String OVER_MSG = "GAME OVER!";
    public void update(){

    }

    /**
     * Rendering losing screen
     */
    public void gameOver(){
        TITLE_FONT.drawString(OVER_MSG, (Window.getWidth()/2.0 - (TITLE_FONT.getWidth(OVER_MSG)/2.0)),
                (Window.getHeight()/2.0 + (TITLE_SIZE/2.0)));
    }

    public void drawBackground(){
        BACKGROUND_IMAGE.draw(Window.getWidth()/2.0, Window.getHeight()/2.0);
    }

    public void setBACKGROUND_IMAGE(Image BACKGROUND_IMAGE) {
        this.BACKGROUND_IMAGE = BACKGROUND_IMAGE;
    }
}
