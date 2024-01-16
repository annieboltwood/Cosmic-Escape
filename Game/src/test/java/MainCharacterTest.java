import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Dictionary;

import static org.junit.jupiter.api.Assertions.*;

public class MainCharacterTest {

    private MainCharacter mainCharacter;
    private ArrayList<Barrier> barriers;
    private SpaceStation spaceStation;

    @BeforeEach
    void setUp() {
        mainCharacter = new MainCharacter(50, 50, 30, 40, 5);
        barriers = new ArrayList<>();
        spaceStation = new SpaceStation(50, 50, 150, 50,0);
        mainCharacter.set_barriers(barriers);
    }

    @AfterEach
    void tearDown() {
        mainCharacter = null;
        barriers = null;
        spaceStation = null;
    }

    @Test
    void moveLeft(){
        mainCharacter.moveLeft();
        assertEquals(45, mainCharacter.getX());
    }

    @Test
    void moveRight(){
        mainCharacter.moveRight();
        assertEquals(55, mainCharacter.getX());
    }

    @Test
    void moveUp(){
        mainCharacter.moveUp();
        assertEquals(45, mainCharacter.getY());
    }

    @Test
    void moveDown(){
        mainCharacter.moveDown();
        assertEquals(55, mainCharacter.getY());
    }

    @Test

    void isNearBarrier(){
        //Adding a barrier near the character
        barriers.add(new Barrier(50,mainCharacter.getX() + 20, mainCharacter.getY(), 50, 50));
        //Testing position near the barrier
        assertTrue(mainCharacter.isNearBarrier(mainCharacter.getX() + 30, mainCharacter.getY()));
        //Testing position away from the barrier
        assertFalse(mainCharacter.isNearBarrier(mainCharacter.getX() - 30, mainCharacter.getY()));
    }

    @Test
    void isMovingEnemyContact(){
        MovingEnemy movingEnemy = new MovingEnemy(50, 50, 1, 1, 5, 50,50);
        assertTrue(mainCharacter.isMovingEnemyContact(movingEnemy));
        assertEquals(25, mainCharacter.getScore());
    }

    @Test
    void isPunishmentContact(){
        Punishment punishment = new Punishment(50, 50, 50, 50, 5);
        assertTrue(mainCharacter.isPunishmentContact(punishment));
        assertEquals(25, mainCharacter.getScore());
    }

    @Test
    void isRewardContact(){
        Reward reward = new RegularReward(10, 50, 50, 50, 50, Constants.KEY_COLOR.BLUE);
        mainCharacter.isRewardContact(reward, spaceStation);
        assertEquals(40, mainCharacter.getScore());
        assertEquals(1, mainCharacter.getKeys_tracker().get(Constants.KEY_COLOR.BLUE));
    }

    @Test
    void isSpaceStationContact(){
        assertTrue(mainCharacter.isSpaceStationContact(spaceStation));
    }

    @Test
    void getKeys_tracker() {
        Dictionary<Constants.KEY_COLOR, Integer> keysTracker = mainCharacter.getKeys_tracker();
        assertEquals(0, keysTracker.get(Constants.KEY_COLOR.BLUE));
        assertEquals(0, keysTracker.get(Constants.KEY_COLOR.YELLOW));
        assertEquals(0, keysTracker.get(Constants.KEY_COLOR.PINK));
        assertEquals(0, keysTracker.get(Constants.KEY_COLOR.GREEN));
    }

}
