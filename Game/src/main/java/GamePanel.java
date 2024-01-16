import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

/**
 * GamePanel takes all entities objects and other objects, then draws those objects using those
 * objects methods. Using the input gameState, it dictates which state of the game it should draw, such as
 * game over screen or start screen.
 */
public class GamePanel extends JPanel{


    private MainCharacter player;
    private ArrayList<Barrier> barriers;
    private SpaceStation spaceStation;
    private ArrayList<Reward> rewards;

    private ArrayList<MovingEnemy> movingEnemies;
    private ArrayList<Punishment> punishments;
    private ScoreBoard scoreBoard;

    private GameTime clock;

    private Constants.GAME_STATE currentState;
    private BufferedImage backgroundImage;

    /**
     * Constructs a new GamePanel with the specified game elements.
     * Takes in all these params, so it can draw it in the screen.
     *
     * @param player       The main character of the game.
     * @param barriers     The list of barriers in the game.
     * @param rewards      The list of rewards in the game.
     * @param movingEnemies The list of moving enemies in the game.
     * @param scoreBoard   The score board of the game.
     * @param clock        The game time clock.
     * @param punishments  The list of punishments in the game.
     * @param currentState The current state of the game, such as GAME_OVER, START_SCREEN,
     *                     or others to identify current type of screen
     * @param spaceStation The space station in the game.
     */
    public GamePanel(MainCharacter player, ArrayList<Barrier> barriers, ArrayList<Reward> rewards, ArrayList<MovingEnemy> movingEnemies, ScoreBoard scoreBoard, GameTime clock, ArrayList<Punishment> punishments, Constants.GAME_STATE currentState, SpaceStation spaceStation){

        this.player = player;
        this.barriers = barriers;
        this.spaceStation = spaceStation;
        this.rewards = rewards;
        this.movingEnemies = movingEnemies;
        this.scoreBoard = scoreBoard;
        this.clock = clock;
        this.punishments = punishments;

        this.currentState = currentState;


        //the background image
        try{
            backgroundImage = ImageIO.read(getClass().getResource("/images/space_background.png"));
        }
        catch (IOException e) {
            e.printStackTrace();
            // Handle the exception (e.g., log the error or display a default image)
        }

    }

    /**
     * Sets the current state of the game. This
     *
     * @param currentState The new game state.
     */
    public void setCurrentState(Constants.GAME_STATE currentState){
        this.currentState = currentState;

    }

    /**
     * Overrides the paintComponent method to paint the game graphics based on the current state.
     *
     * @param g The Graphics object used for painting.
     */
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        //background from https://opengameart.org/content/starfield-background
        g.drawImage(backgroundImage, 0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT, null);

        if(this.currentState == Constants.GAME_STATE.PLAY) {
           //draw player
            player.draw(g);
            //g.drawImage(player.getImage(), player.getX(), player.getY(), null);
            //draw barriers
            for (Barrier b : barriers) {
                b.draw(g);
            }

            spaceStation.draw(g);

            //draw rewards
            for (Reward r : rewards) {
                r.draw(g, clock.getTimeSeconds());
            }

            //draw moving enemies
            for (MovingEnemy e : movingEnemies) {
                e.draw(g);
            }

            //draw punishments
            for (Punishment p : punishments) {
                p.draw(g);
            }

            //draw scoreboard
            scoreBoard.draw(g, player.getScore());

            //draw clock
            clock.draw(g);
        }
        else if(this.currentState == Constants.GAME_STATE.START_SCREEN){
            drawStartScreen(g);

        }
        else if(this.currentState == Constants.GAME_STATE.GAMEOVER){
            drawGameOverScreen(g);
        } else if (this.currentState == Constants.GAME_STATE.WIN) {
            drawGameWinScreen(g);
        }
    }



    // Setters and Getters for MainCharacter player
    /**
     * Gets the main character of the game.
     *
     * @return The main character.
     */
    public MainCharacter getPlayer() {
        return player;
    }

    /**
     * Sets the main character of the game.
     *
     * @param player The new main character.
     */
    public void setPlayer(MainCharacter player) {
        this.player = player;
    }

    // Setters and Getters for ArrayList<Barrier> barriers
    /**
     * Gets the list of barriers in the game.
     *
     * @return The list of barriers.
     */
    public ArrayList<Barrier> getBarriers() {
        return barriers;
    }

    /**
     * Sets the list of barriers in the game.
     *
     * @param barriers The new list of barriers.
     */

    public void setBarriers(ArrayList<Barrier> barriers) {
        this.barriers = barriers;
    }

    // Setters and Getters for ArrayList<Reward> rewards
    /**
     * Gets the list of rewards in the game.
     *
     * @return The list of rewards.
     */
    public ArrayList<Reward> getRewards() {
        return rewards;
    }

    /**
     * Sets the list of rewards in the game.
     *
     * @param rewards The new list of rewards.
     */
    public void setRewards(ArrayList<Reward> rewards) {
        this.rewards = rewards;
    }

    /**
     * Gets the list of moving enemies in the game.
     *
     * @return The list of moving enemies.
     */
    public ArrayList<MovingEnemy> getMovingEnemies() {
        return movingEnemies;
    }

    /**
     * Sets the list of moving enemies in the game.
     *
     * @param movingEnemies The new list of moving enemies.
     */
    public void setMovingEnemies(ArrayList<MovingEnemy> movingEnemies) {
        this.movingEnemies = movingEnemies;
    }

    /**
     * Gets the list of punishments in the game.
     *
     * @return The list of punishments.
     */
    public ArrayList<Punishment> getPunishments() {
        return punishments;
    }

    /**
     * Sets the list of punishments in the game.
     *
     * @param punishments The new list of punishments.
     */
    public void setPunishments(ArrayList<Punishment> punishments) {
        this.punishments = punishments;
    }

    /**
     * Gets the score board of the game.
     *
     * @return The score board.
     */
    public ScoreBoard getScoreBoard() {
        return scoreBoard;
    }

    /**
     * Sets the score board of the game.
     *
     * @param scoreBoard The new score board.
     */
    public void setScoreBoard(ScoreBoard scoreBoard) {
        this.scoreBoard = scoreBoard;
    }

    /**
     * Gets the game time clock.
     *
     * @return The game time clock.
     */
    public GameTime getClock() {
        return clock;
    }

    /**
     * Sets the game time clock.
     *
     * @param clock The new game time clock.
     */
    public void setClock(GameTime clock) {
        this.clock = clock;
    }

    public void drawGameWinScreen(Graphics g){
        g.drawImage(backgroundImage, 0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT, null);

        //draw border around screen

        // Cast to Graphics2D
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.gray);
        // Set the stroke (line thickness)
        g2d.setStroke(new BasicStroke(80)); // Adjust the thickness as needed

        //draws border around the map
        g2d.drawRect(0,0,Constants.SCREEN_WIDTH,Constants.SCREEN_HEIGHT);



        Font largerFont = new Font("Ravie", Font.BOLD, 70);


        BufferedImage gameOverBanner;
        try{
            gameOverBanner = ImageIO.read(getClass().getResource("/images/win.png"));
            Image newBanner = gameOverBanner.getScaledInstance(Constants.SCREEN_WIDTH-100, 150, Image.SCALE_DEFAULT);
            g.drawImage(newBanner, 50,150, null);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        //prompt user to play again
        g.setColor((new Color(125, 189, 126)));
        g.fillRect(100,425,Constants.SCREEN_WIDTH-200,125);
        g.setColor(Color.black);
        largerFont = new Font("Ravie", Font.PLAIN, 30);
        g.setFont(largerFont);
        g.drawString("Click Spacebar to start a new Game", 225,500);
    }

    public void drawStartScreen(Graphics g){
        g.drawImage(backgroundImage, 0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT, null);

        //draw border around screen

        // Cast to Graphics2D
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.gray);
        // Set the stroke (line thickness)
        g2d.setStroke(new BasicStroke(80)); // Adjust the thickness as needed

        //draws border around the map
        g2d.drawRect(0,0,Constants.SCREEN_WIDTH,Constants.SCREEN_HEIGHT);



        Font largerFont = new Font("Ravie", Font.BOLD, 70);


        BufferedImage bannerImage;
        try{
            bannerImage = ImageIO.read(getClass().getResource("/images/banner.png"));
            Image newBanner = bannerImage.getScaledInstance(Constants.SCREEN_WIDTH-100, 150, Image.SCALE_DEFAULT);
            g.drawImage(newBanner, 50,150, null);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        BufferedImage beginImage;
        try{
            beginImage = ImageIO.read(getClass().getResource("/images/beginText.png"));
            Image newBanner2 = beginImage.getScaledInstance((int)((Constants.SCREEN_WIDTH)*0.75), 75, Image.SCALE_DEFAULT);
            g.drawImage(newBanner2, 125,(Constants.SCREEN_HEIGHT/2)+50, null);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void drawGameOverScreen(Graphics g){
        System.out.println("Inside Gameover drawing");
        g.drawImage(backgroundImage, 0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT, null);

        //draw border around screen

        // Cast to Graphics2D
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.gray);
        // Set the stroke (line thickness)
        g2d.setStroke(new BasicStroke(80)); // Adjust the thickness as needed

        //draws border around the map
        g2d.drawRect(0,0,Constants.SCREEN_WIDTH,Constants.SCREEN_HEIGHT);



        Font largerFont = new Font("Ravie", Font.BOLD, 70);


        BufferedImage gameOverBanner;
        try{
            gameOverBanner = ImageIO.read(getClass().getResource("/images/gameOver.png"));
            Image newBanner = gameOverBanner.getScaledInstance(Constants.SCREEN_WIDTH-100, 150, Image.SCALE_DEFAULT);
            g.drawImage(newBanner, 50,150, null);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        //prompt user to play again
        g.setColor((new Color(125, 189, 126)));
        g.fillRect(100,425,Constants.SCREEN_WIDTH-200,125);
        g.setColor(Color.black);
        largerFont = new Font("Ravie", Font.PLAIN, 30);
        g.setFont(largerFont);
        g.drawString("Click Spacebar to start a new Game", 225,500);
    }



}
