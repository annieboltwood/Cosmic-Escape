import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
/**
 * Represents a space station entity in the game, represents its position, size, how many keys has been collected and method to draw it.
 * The more keys collected, the more open the door to space station becomes.
 */
public class SpaceStation extends Entity{
    private Boolean isOpen;
    private int keyCount;
    private BufferedImage[] doorImages; // Images for different doors

    private int uniqueColours;
    private int width;
    private int height;

    /**
     * Constructs a SpaceStation object.
     *
     * @param x             The x-coordinate of the space station.
     * @param y             The y-coordinate of the space station.
     * @param width         The width of the space station.
     * @param height        The height of the space station.
     * @param uniqueColours The initial count of unique colors. This changes how open door to space station is
     */
    public SpaceStation(int x, int y, int width, int height, int uniqueColours) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.uniqueColours = 0;
        this.type = "space_station";
        this.keyCount = 0;
        this.isOpen = false;
        this.doorImages = new BufferedImage[5]; // Assuming 5 images for door states
        try {
            for (int i = 0; i < doorImages.length; i++) {
                BufferedImage originalImage = ImageIO.read(getClass().getResource("/images/ss" + (i+1) + ".PNG"));
                // resizing image
                doorImages[i] = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
                // Scale and draw the original image onto the new image
                Graphics2D g2d = doorImages[i].createGraphics();
                g2d.drawImage(originalImage, 0, 0, width, height, null);
                g2d.dispose();
            }
        } catch (IOException e) {
            e.printStackTrace();
            // Other error messages
        }
    }

    /**
     * Updates the count of unique colors for the space station door.
     *
     * @param uniqueColours The updated count of unique colors.
     */
    public void updateDoor(int uniqueColours) {
        //System.out.println(this.uniqueColours);
        this.uniqueColours = uniqueColours;

    }

    /**
     * Opens the space station door if all unique colors (4 of them) are collected.
     */
    public void openDoor() {
        //System.out.println("The keycount is: " + keyCount);
        if (uniqueColours == 4) {
            this.isOpen = true;
        }

    }

    /**
     * Opens the space station door if all unique colors are collected.
     */
    public Boolean isDoorOpen() {
        return isOpen;
    }

    /**
     * Draws the current state of the space station door.
     *
     * @param g The graphics context used for drawing.
     */
    public void draw(Graphics g) {
        //System.out.println(uniqueColours);
        if (uniqueColours >= 0 && uniqueColours < doorImages.length) {
            g.drawImage(doorImages[uniqueColours], this.x, this.y, null);
        }
    }

   /**
     * Returns the image of the space station door.
     *
     * @return The image of the space station door.
     */
    public BufferedImage getImage() {
        return doorImages[uniqueColours];
    }

    /**
     * Resets the space ship, closes door and sets uniqueColours to zero
     */
    public void resetSpaceStation() {
        uniqueColours = 0;
        isOpen = false;
    }

}
