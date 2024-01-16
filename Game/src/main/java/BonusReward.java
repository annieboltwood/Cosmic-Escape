import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
/**
 * Represents a bonus reward entity in the game, which only increases points of a player.
 */
public class BonusReward extends Reward{
    private float duration;
    private int width;
    private int height;

    private BufferedImage image;

    /**
     * Constructs a BonusReward object with the specified parameters.
     *
     * @param scoreAmt The amount of score provided by the bonus reward.
     * @param x        The initial x-coordinate of the bonus reward.
     * @param y        The initial y-coordinate of the bonus reward.
     * @param width    The width of the bonus reward's image.
     * @param height   The height of the bonus reward's image.
     */

    public BonusReward(int scoreAmt,int x, int y, int width, int height){
        super(scoreAmt,x,y);
        try {
            //image = ImageIO.read(getClass().getResource("/images/character.PNG"));
            BufferedImage originalImage = ImageIO.read(getClass().getResource("/images/bonusReward.png"));

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
        super.setRewardType(Constants.REWARD_TYPE.BONUS_REWARD);
    }

    /**
     * Draws the bonus reward on the graphics context if its current time is within the activation and deactivation time.
     *
     * @param g           The graphics context on which to draw the bonus reward.
     * @param currentTime The current time in the game.
     */
    public void draw(Graphics g, long currentTime) {
        super.draw(g, currentTime);
        if(super.getRewardState() == Reward.Reward_state.Active){

            g.drawImage(image, x, y, null);
        }
    }

    /**
     * Sets the duration of the bonus reward.
     *
     * @param x The duration of the bonus reward.
     */
    public void setDuration(int x) {
        duration = x;
    }

    /**
     * Gets the duration of the bonus reward.
     *
     * @return The duration of the bonus reward.
     */
    public float getDuration() {
        return duration;
    }
}
