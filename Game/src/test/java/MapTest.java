import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class MapTest {
    private Map map;
    private Barrier barrier;
    private Punishment punishment;

    @BeforeEach
    void setUp() {
        map = new Map(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        barrier = new Barrier(Constants.WALL_SIZE, 50, 50, Constants.WALL_SIZE, Constants.WALL_SIZE);
    }

    @AfterEach
    void tearDown() {
        map = null;
    }

    @Test
    void setEntityAt() {
        map.setEntityAt(50, 50, barrier);

        assertEquals(barrier, map.getEntityAt(50, 50));
    }

}
