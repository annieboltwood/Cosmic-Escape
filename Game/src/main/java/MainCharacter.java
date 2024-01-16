
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

import java.util.Dictionary;
import java.util.Hashtable;

/**
 * The MainCharacter class represents the main character in the game.
 * It extends the Entity class and includes features such as scoring, movement,
 * collision detection, and interaction with game elements.
 *
 * The class manages the character's position, image, score, keys collected,
 * and the main characters interactions with barriers, moving enemies, rewards, punishments, and the space station.
 *
 */


public class MainCharacter extends Entity{
    private int score;
    private Boolean hasCollectedAllKeys;
    private int width;  // Character width coord
    private int height; // Character height coord
    private int speed; // Pixels per move

    private BufferedImage image;
    private BufferedImage originalImage;

    private ArrayList<Barrier> barriers;

    private Dictionary<Constants.KEY_COLOR, Integer> keys_tracker;
    private int uniqueColours;

    private static enum Direction{UP, DOWN, LEFT, RIGHT};
    private Direction playerDirection;

    /**
     * Constructs a new MainCharacter
     *
     * @param x     The x-coordinate of the character. (in pixels)
     * @param y     The y-coordinate of the character. (in pixels)
     * @param width The width of the character. (in pixels)
     * @param height The height of the character. (in pixels)
     * @param speed The speed of the character in pixels per move.
     */
    public MainCharacter(int x, int y, int width, int height, int speed){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed = speed;
        this.barriers = new ArrayList<Barrier>();
        this.type = "main_character";
        this.uniqueColours = 0;
        this.score = 30;
        System.out.println(uniqueColours);

        this.playerDirection = Direction.UP;

        try {
            //image = ImageIO.read(getClass().getResource("/images/character.PNG"));
            originalImage = ImageIO.read(getClass().getResource("/images/character.png"));

            // resizing image
            // this means we can resize the image to fit the screen regardless of size of png
            image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            /*
            // Scale and draw the original image onto the new image
            Graphics2D g2d = image.createGraphics();
            g2d.drawImage(originalImage, 0, 0, width, height, null);
            g2d.dispose();*/

        } catch (IOException e) {
            e.printStackTrace();
            //other error messages
        }

        //track the number of unique keys aqquired
        keys_tracker = new Hashtable<>();
        keys_tracker.put(Constants.KEY_COLOR.BLUE, 0);
        keys_tracker.put(Constants.KEY_COLOR.YELLOW, 0);
        keys_tracker.put(Constants.KEY_COLOR.PINK, 0);
        keys_tracker.put(Constants.KEY_COLOR.GREEN, 0);


    }

    /**
     * Sets the barriers around the character.
     *
     * @param barriers The list of barriers.
     */
    public void set_barriers(ArrayList<Barrier> barriers){
        this.barriers = barriers;
    }
    /**
     * Moves the character left if not near a barrier.
     * Updates the character's position and direction.
     */
    public void moveLeft() {
        if (!isNearBarrier(x - speed, y)) {
            x -= speed;
            this.playerDirection = Direction.LEFT;
        }
    }

    /**
     * Moves the character right if not near a barrier.
     * Updates the character's position and direction.
     */
    public void moveRight() {
        if (!isNearBarrier(x + speed, y)) {
            x += speed;
            this.playerDirection = Direction.RIGHT;
        }
    }

    /**
     * Moves the character up if not near a barrier.
     * Updates the character's position and direction.
     */
    public void moveUp() {
        if (!isNearBarrier(x, y - speed)) {
            y -= speed;
            this.playerDirection = Direction.UP;
        }
    }

    /**
     * Moves the character down if not near a barrier.
     * Updates the character's position and direction.
     */
    public void moveDown() {
        if (!isNearBarrier(x, y + speed)) {
            y += speed;
            this.playerDirection = Direction.DOWN;
        }
    }

    /**
     * Draws the character on the specified Graphics object, applying rotation based on the character's direction.
     *
     * @param g The Graphics object to draw on.
     */

    public void draw(Graphics g){

        Graphics2D g2d = (Graphics2D) g.create();

        //0 degrees is up, for our image
        double rotationAngleInRadians = Math.toRadians(0);

        if(playerDirection == Direction.RIGHT){
            rotationAngleInRadians = Math.toRadians(90);
        }
        else if(playerDirection == Direction.DOWN){
            rotationAngleInRadians = Math.toRadians(180);
        }else if(playerDirection == Direction.LEFT){
            rotationAngleInRadians = Math.toRadians(270);
        }

        // Set the rotation transformation
        g2d.rotate(rotationAngleInRadians, x + (double) Constants.CHARACTER_SIZE / 2, y + (double) Constants.CHARACTER_SIZE / 2);

        // Scale and draw the originalImage onto the new image

        g2d.drawImage(originalImage, x, y, width, height, null);
        g2d.dispose();

        // Reset the transformation to avoid affecting other drawings
        g2d.dispose(); // Dispose of the copy of the graphics context
        //g.drawImage(image, x, y, null);
    }

    /**
     * Gets the image of the character.
     *
     * @return The image of the character.
     */

    public BufferedImage getImage() {
        return image;
    }

    /**
     * Sets the score of the character.
     *
     * @param x The new score value.
     */

    public void setScore(int x) {
        score = x;
    }

    /**
     * Gets the current score of the character.
     *
     * @return The current score of the character.
     */
    public int getScore() {
        return score;
    }

    /**
     * Checks if the character is near a barrier based on the hypothetical coordinates
     * the players
     * @param xCoord The x-coordinate to check.
     * @param yCoord The y-coordinate to check.
     * @return True if position intersects with a barrier, false otherwise.
     */

    public Boolean isNearBarrier(int xCoord, int yCoord) {
        int xEpsilon = 35;
        int yEpsilon = 70;

        for(Barrier b: barriers) {
            //the -30,+30 are hardcoded in values to make collision detection between player and
            // barrier more smooth
            if (b.getX() < xCoord + xEpsilon & b.getX() > xCoord - xEpsilon &
                    b.getY() < yCoord + yEpsilon -30 & b.getY() > yCoord - yEpsilon +30) {
                return true;

            }
        }
        return false;


    }
    /**
     * Checks if the character is in contact with the input moving enemy.
     *
     * @param m The moving enemy to check for contact.
     * @return True if in contact, false otherwise.
     */
    public Boolean isMovingEnemyContact(MovingEnemy m) {
        int epsilon = 50;
        if(m.getX() < this.x + epsilon & m.getX() > this.x - epsilon &
                m.getY() < this.y + epsilon & m.getY() > this. y - epsilon  ){
            //increase player score
            this.score -= m.getDamageAmt();
            if(this.score < 0){
                this.score = 0;
                uniqueColours = 0;
            }

            return true;

        }
        return false;
    }

    /**
     * Checks if the character is in contact with the input punishment
     *
     * @param m The punishment to check for contact.
     * @return True if in contact, false otherwise.
     */
    public Boolean isPunishmentContact(Punishment m) {
        int epsilon = 50;
        if(m.getX() < this.x + epsilon & m.getX() > this.x - epsilon &
                m.getY() < this.y + epsilon & m.getY() > this. y - epsilon  ){
            //decrease player score
            this.score -= m.getDamageAmt();
            if(this.score < 0){
                this.score = 0;
                uniqueColours = 0;
            }

            return true;

        }
        return false;

    }

    /**
     * Checks if the character is in contact with the input reward
     *
     * @param r The reward to check for contact.
     * @param spaceStation The spaceStation instance to update if, reward is a key
     * @return True if in contact, false otherwise.
     */
    public Boolean isRewardContact(Reward r, SpaceStation spaceStation) {
        int epsilon = 50;

        if(r.getX() < this.x + 2*epsilon & r.getX() > this.x - epsilon &
                    r.getY() < this.y + 2* epsilon & r.getY() > this. y - epsilon  ){
            //increase player score
            if (r.getRewardState() == Reward.Reward_state.Active) {
                this.score += r.getScoreAmt();
            }
            // Check if the reward is a key
            if (r.getRewardType() == Constants.REWARD_TYPE.REGULAR_REWARD & r.getRewardState() == Reward.Reward_state.Active) {
                RegularReward regularReward = (RegularReward) r;
                Constants.KEY_COLOR color = regularReward.getColor();

                if(keys_tracker.get(regularReward.getColor()) == 0) {
                    System.out.println(keys_tracker.get(regularReward.getColor()) == 0);
                    uniqueColours++;
                    //System.out.println(uniqueColours);
                    r.setRewardState(Reward.Reward_state.Deactivate);
                    spaceStation.updateDoor(uniqueColours);
                }

                // Update the keys_tracker for the specific color
                this.keys_tracker.put(regularReward.getColor(), this.keys_tracker.get(regularReward.getColor()) + 1 );
                System.out.println("Keys: " + keys_tracker);

                // Assume spaceStation is a class member accessible from here
                if (checkAllKeysCollected()) {
                    spaceStation.openDoor(); // Open the Space Station door
                }
            }

            return true;

        }

        return false;
    }
    /**
     * Checks if the character is in contact with a space station.
     *
     * @param s The space station to check for contact.
     * @return True if in contact, false otherwise.
     */
    public Boolean isSpaceStationContact(SpaceStation s) {
        int epsilon = 50;
        if(s.getX() < this.x + epsilon & s.getX() > this.x - epsilon &
                s.getY() < this.y + epsilon & s.getY() > this. y - epsilon  ){

            return true;

        }
        return false;
    }

    /**
     * Helper method to check if all 4 types of keys are collected.
     *
     * @return True if all 4 types of keys are collected, false otherwise.
     */
    private boolean checkAllKeysCollected() {
        if (uniqueColours == 4) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * Gets the dictionary tracking the number of keys acquired for each color.
     *
     * @return The dictionary containing the count of keys for each color.
     */
    public Dictionary<Constants.KEY_COLOR, Integer> getKeys_tracker() {
        return keys_tracker;
    }

    /**
     * Resets the keys tracker, setting the count for each color to zero.
     */
    public void resetKeysTracker() {
        keys_tracker = new Hashtable<>();
        keys_tracker.put(Constants.KEY_COLOR.BLUE, 0);
        keys_tracker.put(Constants.KEY_COLOR.YELLOW, 0);
        keys_tracker.put(Constants.KEY_COLOR.PINK, 0);
        keys_tracker.put(Constants.KEY_COLOR.GREEN, 0);
    }

    /**
     * Resets the count of unique colors to zero.
     */
    public void resetUniqueColours() {
        uniqueColours = 0;
    }


}
