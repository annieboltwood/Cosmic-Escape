import java.awt.*;

/**
 * The Enemy class represents a generic enemy entity in the game.
 * It extends the Entity class and provides basic functionality for enemy entities.
 */
public class Enemy extends Entity{



    /**
     * Constructs an Enemy object with the specified initial position.
     *
     * @param x The initial x coordinate of the Enemy.
     * @param y The initial y coordinate of the Enemy.
     */
    public Enemy(int x, int y) {
        this.x = x;
        this.y = y;
    }


    /**
     * Increments the x coordinate of the Enemy by the specified amount.
     *
     * @param i The amount by which to increment the x coordinate.
     */
    public void incrementX(int i) {
        this.x = x +  i;
    }


    /**
     * Increments the y coordinate of the Enemy by the specified amount.
     *
     * @param i The amount by which to increment the y coordinate.
     */
    public void incrementY(int i) {
        this.y = y + i;
    }

    /**
     * Draws the Enemy on the screen using Graphics object.
     * This method overridden by subclasses for specific drawing behavior.
     *
     * @param g The Graphics object used for drawing.
     */
    public void draw(Graphics g){

    }

}