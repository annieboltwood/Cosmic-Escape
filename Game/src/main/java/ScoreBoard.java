import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Dictionary;


/**
 * The ScoreBoard class represents the scoreboard in the game.
 * It is responsible for displaying the player's score, which includes the overall score and the count of keys collected in different colors.

 */
public class ScoreBoard {

    //store number of keys main character has
    Dictionary<Constants.KEY_COLOR, Integer> keysTracker;

    /**
     * Constructs a ScoreBoard with the keysTracker.
     *
     * @param keysTracker The dictionary storing the count of keys in different colors.
     */
    public ScoreBoard(Dictionary<Constants.KEY_COLOR, Integer> keysTracker){
        this.keysTracker = keysTracker;
    }


    /**
     * Draws the player's score and key count on the game screen.
     *
     * @param g The Graphics object used for drawing.
     * @param score The overall score of the player.
     */
    public void draw(Graphics g, int score){
        g.setColor((new Color(125, 189, 126)));
        g.fillRect(Constants.SCREEN_WIDTH - 300,0,300,50);
        g.setColor(Color.WHITE);
        Font largerFont = new Font("Monospaced", Font.BOLD, 24);
        g.setFont(largerFont);
        g.drawString("SCORE: " + score, Constants.SCREEN_WIDTH - 290,40);

        //draw score for the keys
        int keyWidth = 50;
        int keyHeight = 50;


        g.setColor(Color.white);
        g.fillRect(Constants.SCREEN_WIDTH - 700,0,400,50);

        //draw blue key score count
        try {
            BufferedImage image = ImageIO.read(getClass().getResource("/images/key1.png"));
            // Draw the image on the screen as needed
            g.drawImage(image, Constants.SCREEN_WIDTH - 700, 10, keyWidth, keyHeight, null);
            g.setColor(Color.blue);
            g.drawString(""+keysTracker.get(Constants.KEY_COLOR.BLUE), Constants.SCREEN_WIDTH - 650 ,40);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        //draw green key score count
        try {
            BufferedImage image = ImageIO.read(getClass().getResource("/images/key2.png"));
            // Draw the image on the screen as needed
            g.drawImage(image, Constants.SCREEN_WIDTH - 600, 10, keyWidth, keyHeight, null);
            g.setColor(Color.green);
            g.drawString(""+keysTracker.get(Constants.KEY_COLOR.GREEN), Constants.SCREEN_WIDTH - 550 ,40);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        //draw pink key score count
        try {
            BufferedImage image = ImageIO.read(getClass().getResource("/images/key3.png"));
            // Draw the image on the screen as needed
            g.drawImage(image, Constants.SCREEN_WIDTH - 500, 10, keyWidth, keyHeight, null);
            g.setColor(Color.pink);
            g.drawString(""+keysTracker.get(Constants.KEY_COLOR.PINK), Constants.SCREEN_WIDTH - 450 ,40);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        //draw yellow key score count
        try {
            BufferedImage image = ImageIO.read(getClass().getResource("/images/key4.png"));
            // Draw the image on the screen as needed
            g.drawImage(image, Constants.SCREEN_WIDTH - 400, 10, keyWidth, keyHeight, null);
            g.setColor(Color.orange);
            g.drawString(""+keysTracker.get(Constants.KEY_COLOR.YELLOW), Constants.SCREEN_WIDTH - 350 ,40);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Sets the keysTracker to the specified dictionary.
     *
     * @param keysTracker The dictionary storing the count of keys in different colors.
     */
    public void setKeysTracker(Dictionary<Constants.KEY_COLOR, Integer> keysTracker){
        this.keysTracker = keysTracker;
    }
}
