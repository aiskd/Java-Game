import bagel.*;
import bagel.util.Point;
import bagel.util.Rectangle;
import org.w3c.dom.css.Rect;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import static bagel.Window.getWidth;

/**
 * Please enter your name below
 * Aurelia Iskandar
 */

public class ShadowDimension extends AbstractGame {
    private final static int WINDOW_WIDTH = 1024;
    private final static int WINDOW_HEIGHT = 768;
    private final static String GAME_TITLE = "SHADOW DIMENSION";
    private final static String TRANS_MSG1 = "PRESS SPACE TO START" ;
    private final static String TRANS_MSG2 = "  PRESS A TO ATTACK";
    private final static String TRANS_MSG3 = "DEFEAT NAVEC TO WIN";
    private final static String INST_MSG = " PRESS SPACE TO START\nUSE ARROW KEYS TO FIND GATE";
    private final Font TRANS_FONT = new Font("project-2-skeleton/res/frostbite.ttf", INST_SIZE);
    private final static int TITLE_SIZE = 75;
    private final static int INST_SIZE = 40;
    private final Font TITLE_FONT = new Font("project-2-skeleton/res/frostbite.ttf", TITLE_SIZE);
    private final Font INST_FONT = new Font("project-2-skeleton/res/frostbite.ttf", INST_SIZE);
    private final static int X_TRANS = 350;
    private final static int Y_TRANS = 350;
    private final static int X_TITLE = 260;
    private final static int Y_TITLE = 250;
    private final static int X_INST = 90;
    private final static int Y_INST = 190;
    private Player player;
    private Point topLeft;
    private Point bottomRight;
    private String LVL0 = "project-2-skeleton/res/level0.csv";
    private String LVL1 = "project-2-skeleton/res/level1.csv";
    private boolean gameStart = false;
    private boolean gameOver;
    private boolean gameWin0;
    private boolean lvl1 = false;
    private boolean lvl1Start = false;
    private Lvl0 lvl0 = new Lvl0();
    private boolean gameWin1 = false;
    private boolean read = false;
    private boolean timerStart =true;
    private boolean timeInvis =false;
    ArrayList<Obstacle> obstacles = new ArrayList<Obstacle>();
    ArrayList<Sinkhole> sinkholes = new ArrayList<Sinkhole>();
    ArrayList<Demon> demons = new ArrayList<Demon>();


    Navec navec = new Navec();
    private Image fireImage = new Image("project-2-skeleton/res/demon/demonFire.png");
    private Time inviTime = new Time(3000);

    public ShadowDimension(){
        super(WINDOW_WIDTH, WINDOW_HEIGHT, GAME_TITLE);
        readCSV(LVL0);
    }

    /**
     * The entry point for the program.
     */
    public static void main(String[] args) {
        ShadowDimension game = new ShadowDimension();
        game.run();
    }

    /**
     * Method used to read file and create objects (You can change this
     * method as you wish).
     */
    private void readCSV(String filename){
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))){

            String line;


//            Checks every line in the CSV file
            while((line = reader.readLine()) != null){
                String[] sections = line.split(",");
                switch (sections[0]) {
//                    Makes classes depending on its input
                    case "Fae":
                        player = new Player(Integer.parseInt(sections[1]), Integer.parseInt(sections[2]));
                        break;
                    case "Wall":
                    case "Tree":
                        obstacles.add(new Obstacle(Integer.parseInt(sections[1]),Integer.parseInt(sections[2])));
                        break;
                    case "Sinkhole":
                        sinkholes.add(new Sinkhole(Integer.parseInt(sections[1]), Integer.parseInt(sections[2])));
                        break;
                    case "TopLeft":
                        topLeft = new Point(Integer.parseInt(sections[1]), Integer.parseInt(sections[2]));
                        break;
                    case "BottomRight":
                        bottomRight = new Point(Integer.parseInt(sections[1]), Integer.parseInt(sections[2]));
                        break;
                    case "Demon":
                        demons.add(new Demon(Integer.parseInt(sections[1]), Integer.parseInt(sections[2])));
                    case "Navec":
                        navec = new Navec(Integer.parseInt(sections[1]), Integer.parseInt(sections[2]));
                }
            }
        } catch (IOException e){
            e.printStackTrace();
            System.exit(-1);
        }

    }

    /**
     * Performs a state update.
     * allows the game to exit when the escape key is pressed.
     */
    @Override
    protected void update(Input input) {
        if (input.wasPressed(Keys.ESCAPE)){
            Window.close();
        }

        if(!gameStart){
//           Rendering Start Screen
            TITLE_FONT.drawString(GAME_TITLE, X_TITLE, Y_TITLE);
            INST_FONT.drawString(INST_MSG,X_TITLE + X_INST, Y_TITLE + Y_INST);
            if (input.wasPressed(Keys.SPACE)){
                gameStart = true;
            }
        }

        if (gameOver){
//          Rendering  Game Over Screen
            lvl0.gameOver();
        } else if (gameWin0 && !lvl1Start) {
//            Rendering Winner Screen in Lvl 0

            if (!timerStart){
                TRANS_FONT.drawString(TRANS_MSG1, X_TRANS, Y_TRANS);
                TRANS_FONT.drawString(TRANS_MSG2,X_TRANS, Y_TRANS + INST_SIZE);
                TRANS_FONT.drawString(TRANS_MSG3,X_TRANS, Y_TRANS + INST_SIZE*2);
                if (input.wasPressed(Keys.SPACE)){
                    lvl1Start = true;
                }
            }else {
                if(!lvl0.complete() && timerStart){
                    timerStart = false;
                }
            }


        }

        if (gameStart && !gameOver && !gameWin0){
            Lvl0 lvl0 = new Lvl0(obstacles, sinkholes, player.win(), player.lose());
//            Draws all objects in Level 0
            lvl0.update();
            player.drawPlayer(input);
            checkCollision(player.boundaryPlayer(), true);

            if (player.win()){
                gameWin0 = true;
            }
        } else if (gameStart && !gameOver && gameWin0 && lvl1Start){
//            Draws objects and characters for level 1
            if (!read){
                obstacles.removeAll(obstacles);
                sinkholes.removeAll(sinkholes);
                ArrayList<Demon> demons = new ArrayList<Demon>();

                readCSV(LVL1);
                read = true;
            }
            Lvl1 lvl1 = new Lvl1(obstacles,sinkholes,demons, navec, player.win(), player.lose());
            if (!gameWin1){
                lvl1.update();
                player.drawPlayer(input);
                checkCollision(player.boundaryPlayer(), true);

                if (navec.dead()){
                    gameWin1 = true;
                }

//                Implementing demon movement logic
                for (Demon enemy: demons){
                    if(checkCollision(enemy.boundaryDemon(), false)){
                        enemy.setHorizontal(enemy.getHorizontal()*-1);
                        enemy.setVertical(enemy.getVertical()*-1);
                    }
                    if ((enemy.getxPos() <= 0) || (enemy.getxPos() > (WINDOW_WIDTH - enemy.getLEFT_DEMON_IMAGE().getWidth()))){
                        enemy.setHorizontal(enemy.getHorizontal()*-1);
                    }

                    if ((enemy.getyPos() <= 0) || (enemy.getyPos() >= WINDOW_HEIGHT - enemy.getLEFT_DEMON_IMAGE().getHeight())){
                        enemy.setVertical(enemy.getVertical()*-1);
                    }
                }

//                Implementing navec movement logic
                if(checkCollision(navec.boundaryDemon(), false)){
                    navec.setHorizontal(navec.getHorizontal()*-1);
                    navec.setVertical(navec.getVertical()*-1);
                }
                if ((navec.getxPos() <= 0) || (navec.getxPos() > (WINDOW_WIDTH - navec.getLEFT_DEMON_IMAGE().getWidth()))){
                    navec.setHorizontal(navec.getHorizontal()*-1);
                }

                if ((navec.getyPos() <= 0) || (navec.getyPos() >= WINDOW_HEIGHT - navec.getLEFT_DEMON_IMAGE().getHeight())){
                    navec.setVertical(navec.getVertical()*-1);
                }

//                Implementing fire damage logic for demon
                for (Demon enemy: demons){
                    if (!enemy.dead()){
                        enemy.checkFire(player, false);
                    }
                }

                // Implementing fire damage logic for navec
                if(!navec.dead()){
                    navec.checkFire(player, true);
                }
            } else {
                lvl1.complete();
            }
        }
//        Lose Screen
        if (player.lose()){
            gameOver = true;
        }
    }

    /**
     * Checks whether there are any collisions between character and objects
     */
    private boolean checkCollision(Rectangle charBox, boolean isPlayer){

//       Logic for when player collides with obstacles
        for (Obstacle item : obstacles){
            if (charBox.intersects(item.boundaryWall())){
                if (isPlayer){
                    player.stop();
                }
                return true;
            }
        }

//        Logic and damage when player collides with sinkholes
        for(Sinkhole hole: sinkholes){
            if(charBox.intersects(hole.boundaryHole())){
                if(isPlayer){
                    sinkholes.remove(hole);
                    player.damage(hole.getDAMAGE_POINTS());
                    System.out.println("Sinkhole inflicts " + hole.getDAMAGE_POINTS() + " damage points on Fae. " +
                            "Fae's current health: " + player.getCurrentHealth() + "/" + player.getMAX_HEALTH_POINTS());
                }
                return true;
            }
        }


//        Logic when player collides with demons (including navec) and attack demons (including navec)
        if (isPlayer){
            for(Demon enemy: demons){
                if(charBox.intersects(enemy.boundaryDemon()) && player.isAttack() && !enemy.isInvincible()){
                    enemy.damaged();
                    enemy.setInvincible(true);
                    inviTime.startCD();
                    timeInvis = true;
                    System.out.println("Fae inflicts " + enemy.getDAMAGE_POINTS() + " damage points on Demon. " +
                            "Demon's current health: " + enemy.getCurrentHealth() + "/" + enemy.getMAX_HEALTH());
                }
                if (!inviTime.isStart() && enemy.isInvincible()){
                    enemy.setInvincible(false);
                    timeInvis = false;
                }
            }
            if(charBox.intersects(navec.boundaryDemon()) && player.isAttack() && !navec.isInvincible()){
                navec.damaged();
                navec.setInvincible(true);
                timeInvis = true;
                inviTime.startCD();
                System.out.println("Fae inflicts " + navec.getDAMAGE_POINTS() + " damage points on Navec. " +
                        "Navec's current health: " + navec.getCurrentHealth() + "/" + navec.getMAX_HEALTH());
            }
            if (!inviTime.isStart() && navec.isInvincible()){
                navec.setInvincible(false);
            }
            if(timeInvis){
                inviTime.timer();
            }




        }



//      Window Boundaries
        if ((player.getxPos() <= 0) || (player.getxPos() > (WINDOW_WIDTH - player.getImageWidth()))){
            player.stop();
        }

        if ((player.getyPos() <= 0) || (player.getyPos() >= WINDOW_HEIGHT - player.getImageHeight())){
            player.stop();
        }

        return false;
    }


}
