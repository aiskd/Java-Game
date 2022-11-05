import bagel.DrawOptions;
import bagel.Font;
import bagel.util.Colour;

public class Character extends Entity{
    private final static int RED = 35;
    private final static int ORANGE = 65;
    private final static Colour GREEN_COLOR = new Colour(0, 0.8, 0.2);
    private final static Colour ORANGE_COLOR = new Colour(0.9, 0.6, 0);
    private final static Colour RED_COLOR = new Colour(1, 0, 0);
    private final static DrawOptions COLOUR = new DrawOptions();



    public Character(double xPos, double yPos) {
        super(xPos, yPos);
    }

    public Character() {
    }

    /**
     * Rendering health numbers on characters. Red when less than 35%. Orange when less than 65%.
     */
    public void renderHealth(int currentHealth, int MAX_HEALTH_POINTS, int FONT_SIZE, double xPos, double yPos){
        Font FONT = new Font("project-2-skeleton/res/frostbite.ttf", FONT_SIZE);
        COLOUR.setBlendColour(GREEN_COLOR);
        double percentage = ((double) currentHealth/MAX_HEALTH_POINTS) * 100;
        if (percentage <= RED){
            COLOUR.setBlendColour(RED_COLOR);
        } else if (percentage <= ORANGE){
            COLOUR.setBlendColour(ORANGE_COLOR);
        }
        FONT.drawString(Math.round(percentage) + "%", xPos, yPos, COLOUR);
    }
}
