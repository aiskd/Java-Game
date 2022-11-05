import bagel.Font;
import bagel.Input;
import bagel.Window;

import javax.swing.plaf.synth.SynthTextAreaUI;
import java.util.ArrayList;

public class Lvl0 extends Lvl{
    private final static String WIN0_MSG = "LEVEL COMPLETE!";

    private final static int TITLE_SIZE = 75;
    private final Font TITLE_FONT = new Font("project-2-skeleton/res/frostbite.ttf", TITLE_SIZE);
    ArrayList<Obstacle> obstacles = new ArrayList<Obstacle>();
    ArrayList<Sinkhole> sinkholes = new ArrayList<Sinkhole>();
    private final int screenTime = 3;
    boolean win;
    boolean lose;
    Time transitionTime = new Time(3000);

    public Lvl0(ArrayList<Obstacle> obstacles, ArrayList<Sinkhole> sinkholes, boolean win, boolean lose) {
        this.obstacles = obstacles;
        this.sinkholes = sinkholes;
        this.win = win;
        this.lose = lose;
    }

    public Lvl0() {
    }

    /**
     * Updating state of objects and characters in Level 0
     */
    @Override
    public void update(){
        drawBackground();
        //            Drawing all objects
        for(Obstacle current: obstacles){
            current.drawWall();
        }

        for(Sinkhole current: sinkholes){
            current.drawHole();
        }


    }

    /**
     * Screen when player completes level 0
     */
    public boolean complete(){
        transitionTime.startCD();
        transitionTime.timer();

        if(transitionTime.isStart()){
            TITLE_FONT.drawString(WIN0_MSG, (Window.getWidth()/2.0 - (TITLE_FONT.getWidth(WIN0_MSG)/2.0)),
                    (Window.getHeight()/2.0 + (TITLE_SIZE/2.0)));
            return true;
        }
        return false;






    }
}
