import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;


/**
 * Represents a regular reward entity in the game, which is a type of reward.
 */
public class RegularReward extends Reward{

    private int width;
    private int height;

    private BufferedImage image;
    private Constants.KEY_COLOR color;


    /**
     * Constructs a RegularReward object with the specified parameters.
     *
     * @param scoreAmt The amount of score provided by the reward.
     * @param x        The x-coordinate of the reward.
     * @param y        The y-coordinate of the reward.
     * @param width    The width of the reward image.
     * @param height   The height of the reward image.
     * @param color    The color of the regular reward (KEY_COLOR enum described in Constants file).
     */

    public RegularReward(int scoreAmt,int x, int y, int width, int height, Constants.KEY_COLOR color){
        super(scoreAmt,x,y);
        this.color = color;
        //this.setActivateTime(-1);
        //this.setDeactivateTime(9999);
        try {

            String fileName = "";
            if(this.color == Constants.KEY_COLOR.YELLOW){
                fileName = "key4.png";

            }
            else if(this.color == Constants.KEY_COLOR.GREEN){
                fileName = "key2.png";

            }
            else if(this.color == Constants.KEY_COLOR.PINK){
                fileName = "key3.png";

            }
            else{
                fileName = "key1.png";
            }

            BufferedImage originalImage = ImageIO.read(getClass().getResource("/images/"+fileName));

            // resizing image
            // this means we can resize the image to fit the screen regardless of size of png
            image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

            // Scale and draw the original image onto the new image
            Graphics2D g2d = image.createGraphics();
            g2d.drawImage(originalImage, 0, 0, width, height, null);
            g2d.dispose();

        } catch (IOException e) {
            e.printStackTrace();
            //other error messages
        }
        //set type of RewardType
        super.setRewardType(Constants.REWARD_TYPE.REGULAR_REWARD);
    }


    /**
     * Draws the regular reward on the graphics context based on its current state and activation time.
     *
     * @param g The graphics context on which to draw the reward.
     * @param currentTime The current time in the game.
     */
    public void draw(Graphics g, long currentTime) {
        super.draw(g, currentTime);
        if(super.getRewardState() == Reward_state.Active){
            g.drawImage(image, x, y, null);
        }
    }
    /**
     * Gets the color of the regular reward.
     *
     * @return The color of the regular reward
     */
    public Constants.KEY_COLOR getColor(){
        return this.color;
    }



}



