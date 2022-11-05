import bagel.Font;
import bagel.Image;
import bagel.Window;

import java.util.ArrayList;

public class Lvl1 extends Lvl{
    ArrayList<Obstacle> obstacles = new ArrayList<Obstacle>();
    ArrayList<Sinkhole> sinkholes = new ArrayList<Sinkhole>();
    ArrayList<Demon> demons = new ArrayList<Demon>();
    Player player;
    Navec navec = new Navec();
    boolean win;
    boolean lose;
    private final static int TITLE_SIZE = 75;
    private final Font TITLE_FONT = new Font("project-2-skeleton/res/frostbite.ttf", TITLE_SIZE);
    private final static String WIN_MSG = "CONGRATULATIONS!";
    private boolean gameEnd = false;

    public Lvl1(ArrayList<Obstacle> obstacles, ArrayList<Sinkhole> sinkholes, ArrayList<Demon> demons, Navec navec, boolean win, boolean lose) {
        this.obstacles = obstacles;
        this.sinkholes = sinkholes;
        this.demons = demons;
        this.navec = navec;
        this.win = win;
        this.lose = lose;
    }


    /**
     * Updates the state of Level 1 objects and characters
     */

    @Override
    public void update(){
        if (!gameEnd) {
//            Rendering background
            setBACKGROUND_IMAGE(new Image("project-2-skeleton/res/background1.png"));
            drawBackground();
            //            Drawing all objects
            for (Obstacle current : obstacles) {
                current.drawTree();
            }

            for (Sinkhole current : sinkholes) {
                current.drawHole();
            }

            for (Demon enemy : demons) {
                if (!enemy.dead()) {
                    enemy.drawDemon();
                }

            }
            if (!navec.dead()) {
                navec.drawDemon();
            }
        }
    }

    /**
     * Rendering winner screen when boss is defeated
     */
    public boolean complete(){
        TITLE_FONT.drawString(WIN_MSG, (Window.getWidth()/2.0 - (TITLE_FONT.getWidth(WIN_MSG)/2.0)),
                (Window.getHeight()/2.0 + (TITLE_SIZE/2.0)));
        return true;
    }
}
