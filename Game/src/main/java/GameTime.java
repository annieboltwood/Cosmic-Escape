import java.awt.*;

/**
 * The GameTime class represents the game clock.
 * It manages time during gameplay, has methods that increment time, gets time, draws the current time on the game screen.
 *
 */
public class GameTime {
    private long time;

    /**
     * Constructs a GameTime object with an initial time of 0.
     */
    public GameTime(){
        time = 0;
    }

    /**
     * Increments the game time by one tick.
     */
    public void incrementTime() {
        time++;
    }

    /**
     * Returns the current time in seconds (time divided by 60).
     *
     * @return The current time in seconds.
     */
    public int getTimeSeconds(){
        return (int) (time)/60;
    }

    /**
     * Returns the actual game time in ticks.
     *
     * @return The current game time.
     */
    public long getTime(){
        return time;
    }

    /**
     * Sets the game time to the specified value.
     *
     * @param time The new game time value.
     */
    public void setTime(long time){
        this.time = time;
    }

    /**
     * Draws the current time on the game screen.
     *
     * @param g The Graphics object used for drawing.
     */
    public void draw(Graphics g){
        g.setColor((new Color(192,206, 235)));
        g.fillRect(Constants.SCREEN_WIDTH - 150,0,150,50);
        g.setColor(Color.black);

        Font largerFont = new Font("Monospaced", Font.BOLD, 24);
        g.setFont(largerFont);

        g.drawString("CLOCK: " + this.getTimeSeconds(), Constants.SCREEN_WIDTH - 140,40);

    }
}