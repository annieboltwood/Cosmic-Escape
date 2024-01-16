import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
/**
 * The Constants enum is a set of static variables representing constants used in the game.
 * Constants: screen dimensions, game element size, speeds, colours, reward types, and game states.

 */
public enum Constants {;
    public static int SCREEN_WIDTH = 1200;
    public static int SCREEN_HEIGHT = 800;

    public static final int WALL_SIZE = 50;
    public static final int CHARACTER_SIZE = 50;
    public static final int ENEMY_SIZE = 50;
    public static final int SPACE_STATION_WIDTH = 150;
    public static final int SPACE_STATION_HEIGHT = 50;

    public static final int REGULAR_REWARD_SIZE = 50;
    public static final int BONUS_REWARD_SIZE = 50;


    public static final int MOVING_ENEMY_SPEED = 1;
    public static final int MOVING_ENEMY_DAMAGE = 5;

    public static final int RANDOM_VALUE = 23;

    //1000/FRAME_RATE is number of times the game is drawn per second
    public static final int FRAME_RATE = 60;

    //enum for storing color of the keys
    public static enum KEY_COLOR{BLUE,GREEN,YELLOW,PINK};

    //enum for type of reward
    public static enum REWARD_TYPE{GENERIC,REGULAR_REWARD, BONUS_REWARD};


    //enum for game states

    public static enum GAME_STATE{PAUSED, PLAY, START_SCREEN, GAMEOVER, WIN};

}
