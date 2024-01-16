import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents a moving enemy entity in the game, which is a type of enemy.
 */
public class MovingEnemy extends Enemy {
    private int movementX, movementY;
    private int damageAmt;

    private int width;
    private int height;

    private ArrayList<Barrier> barriers;


    private BufferedImage image;

    /**
     * Constructs a MovingEnemy object with the specified parameters.
     *
     * @param x       The initial x-coordinate of the moving enemy.(pixels)
     * @param y       The initial y-coordinate of the moving enemy.(pixels)
     * @param speedX  The movement speed of the moving enemy in the x-axis.(pixels)
     * @param speedY  The movement speed of the moving enemy in the y-axis.(pixels)
     * @param damage  The amount of damage the moving enemy inflicts.
     * @param width   The width of the moving enemy's image.(pixels)
     * @param height  The height of the moving enemy's image.(pixels)
     */
    public MovingEnemy(int x, int y, int speedX, int speedY, int damage, int width, int height) {
        super(x, y);
        this.movementX = speedX;
        this.movementY = speedY;
        this.damageAmt = damage;
        this.type = "moving_enemy";
        try {
            //image = ImageIO.read(getClass().getResource("/images/character.PNG"));
            BufferedImage originalImage = ImageIO.read(getClass().getResource("/images/movingEnemy.png"));

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
     * Sets the barriers that the moving enemy should consider when updating its location.
     *
     * @param barriers The list of barriers in the game.
     */
    public void set_barriers(ArrayList<Barrier> barriers){
        this.barriers = barriers;
    }


    // Move based on the movement speed (int only)
    public void move() {
        this.x += this.movementX;
        this.y += this.movementY;
    }

    /**
     * Draws the moving enemy on the graphics context.
     *
     * @param g The graphics context on which to draw the moving enemy.
     */
    public void draw(Graphics g) {
        g.drawImage(image, this.getX(), this.getY(), null);
    }

    /**
     * Updates the location of the moving enemy based on the player's position and barriers in the game.
     *
     * @param m        The main character in the game.
     * @param barriers The list of barriers in the game.
     */
    public void updateLocation(MainCharacter m, ArrayList<Barrier> barriers) {
        int player_x = m.getX();
        int player_y = m.getY();
        int distanceX = Math.abs(this.getX() - player_x); // X distance from player
        int distanceY = Math.abs(this.getY() - player_y); // Y distance from player

        // Figure out which direction to move
        boolean tryXFirst = distanceX > distanceY;

        // Try to move
        if (!attemptMove(tryXFirst, player_x, player_y, barriers)) {
            // If unable to move in the primary direction, try the other direction
            attemptMove(!tryXFirst, player_x, player_y, barriers);
        }
    }

    /**
     * Attempts to move the moving enemy in a specific direction.
     *
     * @param attemptXAxis If true, attempts to move in the x-axis direction; otherwise, attempts to move in the y-axis direction.
     * @param player_x     The x-coordinate of the player.
     * @param player_y     The y-coordinate of the player.
     * @param barriers     The list of barriers in the game.
     * @return True if the move was successful, false otherwise.
     */

    private boolean attemptMove(boolean attemptXAxis, int player_x, int player_y, ArrayList<Barrier> barriers) {
        int nextX = this.getX(); // MovingEnemy's next X position
        int nextY = this.getY(); // MovingEnemy's next Y position

        // Check and move in the X direction first
        if (attemptXAxis) {
            nextX += (this.getX() > player_x) ? -this.movementX : this.movementX;
            if (!isNearBarrier(nextX, this.getY(), barriers)) {
                this.incrementX((this.getX() > player_x) ? -this.movementX : this.movementX);
                return true; // Move was successful
            }
        } else { // Otherwise, attempt to move in the Y direction
            nextY += (this.getY() > player_y) ? -this.movementY : this.movementY;
            if (!isNearBarrier(this.getX(), nextY, barriers)) {
                this.incrementY((this.getY() > player_y) ? -this.movementY : this.movementY);
                return true; // Move was successful
            }
        }
        // Move didn't happen potentially because createMovingEnemy occurred inside a barrier
        return false;
    }

    // Add the collision detection method similar to the MainCharacter's
    /**
     * Checks if the moving enemy is near a barrier.
     *
     * @param xcoord   The x-coordinate to check.
     * @param ycoord   The y-coordinate to check.
     * @param barriers The list of barriers in the game.
     * @return True if the moving enemy is near a barrier, false otherwise.
     */
    private boolean isNearBarrier(int xcoord, int ycoord, ArrayList<Barrier> barriers) {
        int xepsilon = 35;
        int yepsilon = 70;
        for (Barrier b : barriers) {
            if (b.getX() < xcoord + xepsilon + 0 & b.getX() > xcoord - xepsilon &
                    b.getY() < ycoord + yepsilon -30 & b.getY() > ycoord - yepsilon +30) {
                return true;

            }
        }
        return false;
    }

    /**
     * Getter for this.damageAmt
     *
     * @return The amount of damage inflicted by the moving enemy.
     */
    public int getDamageAmt() {
        return this.damageAmt;
    }
}
