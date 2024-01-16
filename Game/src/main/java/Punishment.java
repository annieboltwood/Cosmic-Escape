import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * The Punishment class represents a stationary enemy.
 * Punishments can deal damage to the player upon collision and have a timeout for managing damage dealing.

 */
public class Punishment extends Enemy{

    private int width;
    private int height;

    private int damageamt;

    private BufferedImage image;

    private boolean keepingDealingDmg;

    private long damageTimeoutStartTime;


    /**
     * Constructs a Punishment object with the specified position, size, and damage.
     *
     * @param x The x-coordinate of the Punishment.
     * @param y The y-coordinate of the Punishment.
     * @param width The width of the Punishment.
     * @param height The height of the Punishment.
     * @param damage The amount of damage the Punishment deals to the player.
     */
    public Punishment(int x, int y, int width, int height, int damage) {
        super(x, y);
        this.x = x;
        this.y = y;
        this.type = "punishment";
        this.damageamt = damage;

        this.keepingDealingDmg = true;
        this.damageTimeoutStartTime = 0;

        try {
            BufferedImage originalImage = ImageIO.read(getClass().getResource("/images/punishment.png"));

            // resizing image
            image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

            // Scale and draw the original image onto the new image
            Graphics2D g2d = image.createGraphics();
            g2d.drawImage(originalImage, 0, 0, width, height, null);
            g2d.dispose();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Draws the Punishment on the screen using the provided Graphics object.
     *
     * @param g The Graphics object used for drawing.
     */
    public void draw(Graphics g){
        g.drawImage(image, x, y, null);

    }

    /**
     * Returns whether the Punishment is currently dealing damage.
     *
     * @return True if the Punishment is currently dealing damage, false otherwise.
     */
    public boolean getKeepDealingDamage(){
        return this.keepingDealingDmg;
    }



    /**
     * Returns the start time of the damage timeout.
     *
     * @return The start time of the damage timeout.
     */
    public long setDamageTimeoutStartTime(){
        return this.damageTimeoutStartTime;
    }

    /**
     * Sets the start time of the damage timeout.
     *
     * @param damageTimeoutStartTime The start time of the damage timeout.
     */
    public void setDamageTimeoutStartTime(int damageTimeoutStartTime){
        this.damageTimeoutStartTime = damageTimeoutStartTime;
    }


    /**
     * Sets whether the Punishment is currently dealing damage.
     *
     * @param keepDealingDmg True to keep dealing damage, false otherwise.
     */
    public void setKeepDealingDamage(boolean keepDealingDmg){
        this.keepingDealingDmg = keepDealingDmg;
    }


    /**
     * Manages the damage dealing timeout for the Punishment.
     * called everytime player has collided with a punishment, and is called after the first damage to player has been dealt
     *
     * @param currentTime The current time in the game.
     */
    public void manageDamageDealingTimeout(long currentTime){
        //arbitrary timeoutDuration, currenlty 25 frames
        long timeoutDuration = 25;
        long endTime = this.damageTimeoutStartTime + timeoutDuration;


        //means player collided with punishment for the first time
        if(currentTime >= this.damageTimeoutStartTime & this.keepingDealingDmg){
            this.keepingDealingDmg = false;

            this.damageTimeoutStartTime = currentTime;


        }
        //check if currentTime is greated than endTime, if so start dealing damage again
        if(currentTime >= endTime & !this.keepingDealingDmg){
            this.keepingDealingDmg = true;
        }

    }

    /**
     * Returns the amount of damage the Punishment deals to the player.
     *
     * @return The amount of damage the Punishment deals to the player.
     */
    public int getDamageAmt() {
        if(this.keepingDealingDmg == false){
            return 0;
        }
        System.out.println("return 10 dmg");
        return this.damageamt;
    }

}
