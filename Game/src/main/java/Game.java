import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.Timer;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

/**
 * Game class represents the main game controller.
 * It manages the game window, player, entities, and game state transitions.
 *
 * It uses JavaSwing for creating the game window and handling user input.
 * It also has a timer for game ticks, collision detection, and updates for game elements.
 *
 */
public class Game {

    private JFrame frame;
    private GamePanel gamePanel;
    private EntityFactory entityFactory;

    private BoardStateFactory boardStateFactory;
    private MainCharacter player;

    private SpaceStation spaceStation;

    private ArrayList<Barrier> barriers;

    //holds list of rewards
    private ArrayList<Reward> rewards;

    private GameTime clock;

    private Map map;

    //holds list of moving enemies
    private ArrayList<MovingEnemy> movingEnemies;

    private ArrayList<Punishment> punishments;

    private Constants.GAME_STATE currentState;

    private ScoreBoard scoreBoard;

    /**
     * Constructs the Game object, initializing the game window, entities, and game state.
     * This method sets up the JFrame, initializes the entities, and starts the game clock.
     */
    public Game(){
        frame = new JFrame("Cosmic Escape");
        entityFactory = createEntityFactory();
        boardStateFactory = new BoardStateFactory();

        //Start off start_screen
        currentState = Constants.GAME_STATE.START_SCREEN;

        frame.setSize(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        frame.setMinimumSize(new Dimension(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                Dimension newSize = frame.getSize();
                if(gamePanel != null) {
                    setResize(newSize.width, newSize.height);
                }

            }

        });

        //Creat map and clock
        clock = boardStateFactory.createTimer();
        map = boardStateFactory.createMap(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);

        //List of barriers
        barriers = entityFactory.createBarrier(map);
        //Create main character
        player = entityFactory.createMainCharacter(350, 350);
        map.setEntityAt(350,350,player);
        player.set_barriers(barriers);

        //List of rewards
        rewards = entityFactory.createReward(map);
        //Put spaces tation on screen
        spaceStation = entityFactory.createSpaceStation(550,50);
        //Create scoreboard
        scoreBoard = boardStateFactory.createScoreboard(player.getKeys_tracker());
        //List of enemies
        movingEnemies = entityFactory.createMovingEnemy(map);
        //List of punishments
        punishments = entityFactory.createPunishment(map);

        //Create game panel
        gamePanel = new GamePanel(player,barriers,rewards, movingEnemies,scoreBoard,clock,punishments,currentState, spaceStation);
        gamePanel.setBackground(new Color(0,4, 18));
        frame.add(gamePanel, BorderLayout.CENTER);

        //Key Events
        frame.addKeyListener(new KeyListener() {
            //No input characters
            @Override
            public void keyTyped(KeyEvent e) {
            }

            //Key-Pressed Events
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                handleKeyPresses(keyCode);
                gamePanel.repaint();
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        //Initialize Game Clock Info
        long startTime = System.currentTimeMillis();
        long elapsedTime = 0;

        //Game Clock Ticks
        Timer gameClock = new Timer(1000 / Constants.FRAME_RATE, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gamePanel.setCurrentState(currentState);
                if (currentState == Constants.GAME_STATE.PLAY) {
                    //increase clock
                    clock.incrementTime();

                    //check for reward collison with player
                    for (int i = 0; i < rewards.size(); i++) {
                        if (player.isRewardContact(rewards.get(i), spaceStation) == Boolean.TRUE) {
                            rewards.get(i).setRewardState(Reward.Reward_state.Deactivate);
                        }
                    }

                    //check for enemy collison with player and also update enemy location based on players location
                    for (int i = 0; i < movingEnemies.size(); i++) {
                        movingEnemies.get(i).set_barriers(barriers);
                        movingEnemies.get(i).updateLocation(player, barriers);
                        //checks if enemy is touching main character then updates players score
                        player.isMovingEnemyContact(movingEnemies.get(i));
                        if(player.isMovingEnemyContact(movingEnemies.get(i))){
                            currentState  = Constants.GAME_STATE.GAMEOVER;
                            player.resetUniqueColours();
                            spaceStation.resetSpaceStation();
                        }
                    }

                    for (int i = 0; i < punishments.size(); i++) {
                        boolean isCollided = player.isPunishmentContact(punishments.get(i));
                        if(isCollided == true){
                            punishments.get(i).manageDamageDealingTimeout(clock.getTime());
                            if(player.getScore() <= 0) {
                                currentState = Constants.GAME_STATE.GAMEOVER;
                                player.resetUniqueColours();
                                spaceStation.resetSpaceStation();
                            }
                        }
                    }

                    if(spaceStation.isDoorOpen() && player.isSpaceStationContact(spaceStation)) {
                        currentState = Constants.GAME_STATE.WIN;
                        player.resetUniqueColours();
                        spaceStation.resetSpaceStation();

                    }

                }
                // Update the panel
                gamePanel.repaint();
            }
        });
        gameClock.start();

    }

    /**
     * Starts the game by making the game window visible.
     */
    public void start(){
        frame.setVisible(true);
    }


    /**
     * Creates and returns an instance of the EntityFactory.
     * The EntityFactory creates entities: barriers, rewards, enemies, etc.
     */
    public EntityFactory createEntityFactory() {
        return new EntityFactory();
    }


    /**
     * Handles the resizing of the game window and updates the game panel.
     *
     * @param newWidth  The new width of the game window.
     * @param newHeight The new height of the game window.
     */
    private void setResize(int newWidth, int newHeight) {
        Constants.SCREEN_WIDTH = newWidth;
        Constants.SCREEN_HEIGHT = newHeight;

        if(gamePanel != null) {
            gamePanel.setPreferredSize(new Dimension(newWidth, newHeight));
            gamePanel.revalidate();
            gamePanel.repaint();
        }

    }

    /**a getter for gettign JFrame frame object of game, mainly useful for testing
     *
     * @return the frame object
     */
    public JFrame getFrame(){
        return frame;
    }

    /**a getter for gettign the game state, mainly useful for testing
     *
     * @return the frame object
     */
    public Constants.GAME_STATE getCurrentState(){
        return currentState;
    }

    /**a setter for gettign the game state, mainly useful for testing
     *
     * @return the frame object
     */
    public void setCurrentState(Constants.GAME_STATE state){
        this.currentState = state;
    }



    /**Handles all key presses and appropirately calls right methods or does right action for the input keyCode
     *
     * @param keyCode a int representation of key press
     */
    public void handleKeyPresses(int keyCode){
        if (currentState == Constants.GAME_STATE.PLAY) {
            switch (keyCode) {
                case KeyEvent.VK_LEFT:
                case KeyEvent.VK_A:
                    player.moveLeft();
                    break;
                case KeyEvent.VK_RIGHT:
                case KeyEvent.VK_D:
                    player.moveRight();
                    break;
                case KeyEvent.VK_UP:
                case KeyEvent.VK_W:
                    player.moveUp();
                    break;
                case KeyEvent.VK_DOWN:
                case KeyEvent.VK_S:
                    player.moveDown();
                    break;
                default:
                    // Handle other key events for the PLAY state if needed
                    break;
            }
        } else if (currentState == Constants.GAME_STATE.START_SCREEN) {
            if (keyCode == KeyEvent.VK_SPACE) {
                currentState = Constants.GAME_STATE.PLAY;
                //resetGame();
            }
        } else if (currentState == Constants.GAME_STATE.GAMEOVER) {
            if (keyCode == KeyEvent.VK_SPACE) {
                currentState = Constants.GAME_STATE.START_SCREEN;
                resetGame();
            }
        } else if (currentState == Constants.GAME_STATE.WIN) {
            if (keyCode == KeyEvent.VK_SPACE) {
                System.out.println("You win!");
                currentState = Constants.GAME_STATE.START_SCREEN;
                resetGame();
            }
        }

        }
    // Method to reset the game state
    private void resetGame() {

        gamePanel.setCurrentState(currentState);

        player.setX(350);
        player.setY(350);
        player.setScore(30);
        player.resetKeysTracker();
        gamePanel.setPlayer(player);

        clock.setTime(0);
        gamePanel.setClock(clock);

        scoreBoard.setKeysTracker(player.getKeys_tracker());
        gamePanel.setScoreBoard(scoreBoard);

        movingEnemies = entityFactory.createMovingEnemy(map);
        gamePanel.setMovingEnemies(movingEnemies);

        punishments = entityFactory.createPunishment(map);
        gamePanel.setPunishments(punishments);

        rewards = entityFactory.createReward(map);
        gamePanel.setRewards(rewards);
    }

    /**getter for Player
     *
     * @return the main character player
     */
    public MainCharacter getPlayer(){
            return player;
        }




}
