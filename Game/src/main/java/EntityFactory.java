import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import static java.lang.Math.round;

/**
 * The EntityFactory class makes game entities using constants and randomization.
 */
public class EntityFactory {

    /**
     * Creates and returns an instance of the MainCharacter at specified coordinates.
     *
     * @param x The x-coordinate of the main character.
     * @param y The y-coordinate of the main character.
     * @return An instance of the MainCharacter.
     */
    public MainCharacter createMainCharacter(int x, int y) {

        return new MainCharacter(x, y, Constants.CHARACTER_SIZE, Constants.CHARACTER_SIZE, 10);
    }

    /**
     * Creates and returns an instance of the SpaceStation at specified coordinates.
     *
     * @param x The x-coordinate of the space station.
     * @param y The y-coordinate of the space station.
     * @return An instance of the SpaceStation.
     */
    public SpaceStation createSpaceStation(int x, int y) {
        return new SpaceStation(x, y, Constants.SPACE_STATION_WIDTH, Constants.SPACE_STATION_HEIGHT, 0);
    }



    /**
     * Creates and returns a list of Barrier instances based on the game map.
     *
     * @param map The game map on which the barriers are placed.
     * @return A list of Barrier instances.
     */
    public ArrayList<Barrier> createBarrier(Map map) {
        ArrayList<Barrier> barriers = new ArrayList<>();

       // add side walls into barriers
        for(int i = 0; i < Constants.SCREEN_HEIGHT; i += Constants.WALL_SIZE){
            barriers.add( new Barrier(Constants.WALL_SIZE, 0,i,Constants.WALL_SIZE,Constants.WALL_SIZE));
            map.setEntityAt(0,i,barriers.getLast());

            barriers.add( new Barrier(Constants.WALL_SIZE, Constants.SCREEN_WIDTH - Constants.WALL_SIZE,i,Constants.WALL_SIZE,Constants.WALL_SIZE));
            map.setEntityAt(Constants.SCREEN_WIDTH - Constants.WALL_SIZE,i,barriers.getLast());

        }
        //top and bottom walls
        for(int i = 0; i < Constants.SCREEN_WIDTH-Constants.WALL_SIZE; i += Constants.WALL_SIZE){
            if (map.getEntityAt(i,0) == null) {
                barriers.add(new Barrier(Constants.WALL_SIZE, i, 0, Constants.WALL_SIZE, Constants.WALL_SIZE));
                map.setEntityAt(i, 0, barriers.getLast());
            }
            if (map.getEntityAt(i+Constants.WALL_SIZE,Constants.SCREEN_HEIGHT-Constants.WALL_SIZE) == null) {
                barriers.add(new Barrier(Constants.WALL_SIZE, i + Constants.WALL_SIZE, Constants.SCREEN_HEIGHT - Constants.WALL_SIZE, Constants.WALL_SIZE, Constants.WALL_SIZE));
                map.setEntityAt(i+Constants.WALL_SIZE, Constants.SCREEN_HEIGHT - Constants.WALL_SIZE, barriers.getLast());
            }
        }

        //barrier generation algorithm
        //add barriers within map
        Random rand = new Random();

        //barrier generation algorithm here
        // Filling the maze with randomly placed barriers
        // Barrier generation algorithm
        for (int i = 0; i < Constants.SCREEN_WIDTH; i += 2 * Constants.WALL_SIZE) {
            for (int j = Constants.WALL_SIZE * 2; j < Constants.SCREEN_HEIGHT; j += 2 * Constants.WALL_SIZE) {
                if (map.getEntityAt(i + Constants.WALL_SIZE, j) == null && rand.nextInt(4) == 1) {
                    barriers.add(new Barrier(Constants.WALL_SIZE, i + Constants.WALL_SIZE, j, Constants.WALL_SIZE, Constants.WALL_SIZE));
                    map.setEntityAt(i + Constants.WALL_SIZE, j, barriers.get(barriers.size() - 1));
                }

                if (i < Constants.SCREEN_WIDTH - 2 * Constants.WALL_SIZE && map.getEntityAt(i, j + Constants.WALL_SIZE) == null && rand.nextInt(4) == 1) {
                    barriers.add(new Barrier(Constants.WALL_SIZE, i, j + Constants.WALL_SIZE, Constants.WALL_SIZE, Constants.WALL_SIZE));
                    map.setEntityAt(i, j + Constants.WALL_SIZE, barriers.get(barriers.size() - 1));
                }

                if (map.getEntityAt(i, j) == null) {
                    barriers.add(new Barrier(Constants.WALL_SIZE, i, j, Constants.WALL_SIZE, Constants.WALL_SIZE));
                    map.setEntityAt(i, j, barriers.get(barriers.size() - 1));
                }
            }
        }
        return barriers;
    }

    /**
     * Creates and returns a random position.
     *
     * @param rand the random value.
     * @param base base for the scaled wall size.
     * @param range The range in which the random integers can take on.
     * @return The final random position.
     */
    private int getRandomPosition(Random rand, int base, int range) {
        return base + rand.nextInt(range) * 50;
    }

    /**
     * Creates and returns a list of Punishment instances based on the game map.
     *
     * @param map The game map on which the punishments are placed.
     * @return A list of Punishment instances.
     */
    public ArrayList<Punishment> createPunishment(Map map) {
        ArrayList<Punishment> punishments = new ArrayList<>();
        Random rand = new Random();
        int wallScale1 = 2;
        int wallScale2 = 3;
        int randomBound1 = 20;
        int randomBound2 = 11;


        int randomX = getRandomPosition(rand, wallScale1 * Constants.WALL_SIZE, randomBound1);
        int randomY = getRandomPosition(rand, wallScale2 * Constants.WALL_SIZE, randomBound2);

        //spawn punishments randomly across the map
        //check if the randomly generated x,y position would be an already taken space in the map
        for (int i = 0; i < 3; i++) {
            while (map.getEntityAt(randomX, randomY) != null) {
                randomX = wallScale1*Constants.WALL_SIZE + rand.nextInt(randomBound1) * 50;
                randomY = wallScale2*Constants.WALL_SIZE + rand.nextInt(randomBound2) * 50;
            }
            punishments.add(new Punishment(randomX, randomY, Constants.ENEMY_SIZE, Constants.ENEMY_SIZE, Constants.MOVING_ENEMY_DAMAGE));
            map.setEntityAt(randomX, randomY, punishments.getLast());
        }

        return punishments;
    }

    /**
     * Creates and returns a list of MovingEnemy instances based on the game map.
     *
     * @param map The game map on which the moving enemies are placed.
     * @return A list of MovingEnemy instances.
     */
    public ArrayList<MovingEnemy> createMovingEnemy(Map map) {
        ArrayList<MovingEnemy> movingEnemies = new ArrayList<>();

        int wallScale1 = 2;
        int wallScale2 = 3;


        int movementX = Constants.MOVING_ENEMY_SPEED;
        int movementY = Constants.MOVING_ENEMY_SPEED;

        Random rand = new Random();

        int randomX = getRandomPosition(rand, wallScale1 * Constants.WALL_SIZE, 20);
        int randomY = getRandomPosition(rand, wallScale2 * Constants.WALL_SIZE, 11);

        //spawn punishments randomly across the map
        //check if the randomly generated x,y position would be an already taken space in the map
        for (int i = 0; i < 3; i++) {
            while (map.getEntityAt(randomX, randomY) != null) {
                randomX = rand.nextInt(23) * 50;
                randomY = rand.nextInt(15) * 50;
            }
            movingEnemies.add(new MovingEnemy(randomX, randomY, movementX, movementY, Constants.MOVING_ENEMY_DAMAGE,Constants.ENEMY_SIZE,Constants.ENEMY_SIZE));
            randomX = rand.nextInt(23) * 50;
            randomY = rand.nextInt(15) * 50;
        }

        return movingEnemies;
    }

    /**
     * Creates and returns a list of Reward instances based on the game map.
     *
     * @param map The game map on which the rewards are placed.
     * @return A list of Reward instances.
     */
    public ArrayList<Reward> createReward(Map map) {
        ArrayList<Reward> rewards = new ArrayList<Reward>();
        int wallScale1 = 2;
        int wallScale2 = 3;
        Random rand = new Random();


        int randomX = getRandomPosition(rand, wallScale1 * Constants.WALL_SIZE, 19);
        int randomY = getRandomPosition(rand, wallScale2 * Constants.WALL_SIZE, 11);

        //spawn regular rewards randomly across map within Screen bounds
        //check if the randomly generated x,y position would be an already taken space in the map
        for (int i = 0; i < 4; i++) {
            while (map.getEntityAt(randomX, randomY) != null) {
                randomX = wallScale1*Constants.WALL_SIZE + rand.nextInt(20) * 50;
                randomY = wallScale2*Constants.WALL_SIZE + rand.nextInt(11) * 50;
            }

            //index for choosing colors from Constants.KEY_COLOR, the % converts i to one of {0,1,2,3}
            int colorIndex = i % 4;
            rewards.add(new RegularReward(10, randomX, randomY, Constants.REGULAR_REWARD_SIZE, Constants.REGULAR_REWARD_SIZE,Constants.KEY_COLOR.values()[colorIndex]  ));
            map.setEntityAt(randomX, randomY, rewards.getLast());
        }

        //bonus rewards generation
        for (int i = 0; i < 8; i++) {
            while (map.getEntityAt(randomX, randomY) != null) {
                randomX = 2*Constants.WALL_SIZE + rand.nextInt(20) * 50;
                randomY = 3*Constants.WALL_SIZE + rand.nextInt(11) * 50;
            }
            rewards.add(new BonusReward(10, randomX, randomY, Constants.BONUS_REWARD_SIZE, Constants.BONUS_REWARD_SIZE));
            randomX = 2*Constants.WALL_SIZE + rand.nextInt(20) * 50;
            randomY = 3*Constants.WALL_SIZE + rand.nextInt(11) * 50;
        }
        return rewards;

    }



}
