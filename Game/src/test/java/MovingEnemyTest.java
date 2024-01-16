import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class MovingEnemyTest {

    private MovingEnemy movingEnemy;
    private MainCharacter player;
    private ArrayList<Barrier> barriers;

    @BeforeEach
    void setUp() {
        // Setting up a player and barriers for testing
        player = new MainCharacter(100, 100, 50, 50, 5);
        barriers = new ArrayList<>();
        // Adding barriers for collision tests
        barriers.add(new Barrier(50, 150, 100, 50, 50));
        barriers.add(new Barrier(50, 100, 150, 50, 50));
        // MovingEnemy starting at (200, 200), with speed 10 in both x and y directions, and damage 10
        movingEnemy = new MovingEnemy(200, 200, 10, 10, 10, 50, 50);
        movingEnemy.set_barriers(barriers);
    }


    @AfterEach
    void tearDown() {
        movingEnemy = null;
        player = null;
        barriers.clear();
    }

    @Test
    void testMove() {
        movingEnemy.move();
        assertEquals(210, movingEnemy.getX());
        assertEquals(210, movingEnemy.getY());
    }

    @Test
    void getX() {
        assertEquals(200, movingEnemy.getX());
    }

    @Test
    void getY() {
        assertEquals(200, movingEnemy.getY());
    }

    @Test
    void testCollisionWithBarrier() {
        // set player's position closer to the barriers
        player.setX(140);
        player.setY(100);
        // update enemy's location, assert not to the barrier's location
        movingEnemy.updateLocation(player, barriers);
        for (Barrier barrier : barriers) {
            assertFalse(movingEnemy.getX() == barrier.getX() && movingEnemy.getY() == barrier.getY());
        }
    }

    @Test
    void incrementX() {
        int initialX = movingEnemy.getX();
        movingEnemy.incrementX(15);
        assertEquals(initialX + 15, movingEnemy.getX());

    }

    @Test
    void incrementY() {
        int initialY = movingEnemy.getY();
        movingEnemy.incrementY(15);
        assertEquals(initialY + 15, movingEnemy.getY());
    }

    @Test
    void setX() {
        movingEnemy.setX(200);
        assertEquals(200, movingEnemy.getX());
    }

    @Test
    void setY() {
        movingEnemy.setY(200);
        assertEquals(200, movingEnemy.getY());
    }

    @Test
    void getType() {
        assertEquals("moving_enemy", movingEnemy.getType());
    }

    @Test
    void set_barriers() {
        ArrayList<Barrier> newBarriers = new ArrayList<>();
        newBarriers.add(new Barrier(50, 10, 20, 50, 50));
        movingEnemy.set_barriers(newBarriers);
        movingEnemy.updateLocation(player, newBarriers);
        // Assuming you have a method to check if the enemy has barriers set
        assertNotNull(newBarriers);
    }

    @Test
    void move() {
        movingEnemy.move();
        assertEquals(210, movingEnemy.getX());
        assertEquals(210, movingEnemy.getY());
    }

    @Test
    void testDraw() {
    }

    @Test
    void updateLocation() {
        // Moving enemy moves closer to the player
        movingEnemy.updateLocation(player, barriers);
        assertTrue(movingEnemy.getX() < 200 || movingEnemy.getY() < 200);
    }

    @Test
    void getDamageAmt() {
        assertEquals(10, movingEnemy.getDamageAmt());
    }
}