import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;
import javax.imageio.ImageIO;

/**
 * The Barrier class represents a type of entity which is an obstacle that doesn't deal damage.
 * Barriers have a specified size, position, and an associated image.
 */
public class Barrier extends Entity{
    private int size;

    private int width;
    private int height;

    private BufferedImage image;

    /**
     * Constructs a Barrier object with the specified size, position, and image.
     *
     * @param size The size of the Barrier.
     * @param x The x-coordinate of the Barrier.
     * @param y The y-coordinate of the Barrier.
     * @param width The width of the Barrier image.
     * @param height The height of the Barrier image.
     */
    public Barrier(int size, int x, int y, int width, int height){
        this.size = size;
        this.x = x;
        this.y = y;
        this.type = "barrier";

        try {
            BufferedImage originalImage = ImageIO.read(getClass().getResource("/images/barrier2.png"));

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
     * Gets the size of the Barrier.
     *
     * @return The size of the Barrier.
     */
    public int getWidth() {
        return size;
    }

    /**
     * Sets the size of the Barrier.
     *
     * @param width The size of the Barrier.
     */
    public void setWidth(int width) {
        size = width;
    }

    /**
     * Draws the Barrier on the screen using the provided Graphics object.
     *
     * @param g The Graphics object used for drawing.
     */
    public void draw(Graphics g){

        g.drawImage(image, x, y, null);
    }
}
