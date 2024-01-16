import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class EntityFactoryTest {
    private EntityFactory entityFactory;
    private Map map;


    @BeforeEach
    void setUp() {
        entityFactory = new EntityFactory();
        map = new Map(Constants.SCREEN_WIDTH,Constants.SCREEN_HEIGHT);
    }

    @AfterEach
    void tearDown() {entityFactory = null;}

    @Test
    void createBarrier() {
        ArrayList<Barrier> barrierList = entityFactory.createBarrier(map);
        //check map if barriers are set on it
        for (Barrier barrier : barrierList) {
            Entity mapBarrier = (Barrier) map.getEntityAt(barrier.getX(), barrier.getY());
            assertEquals(barrier,mapBarrier);
        }
    }

    @Test
    void createPunishment() {
        ArrayList<Punishment> punishmentList = entityFactory.createPunishment(map);
        //check map if barriers are set on it
        for (Punishment punishment : punishmentList) {
            Punishment mapPunishment = (Punishment) map.getEntityAt(punishment.getX(), punishment.getY());
            assertEquals(punishment,mapPunishment);
        }
    }

    @Test
    void createRegReward() {
        ArrayList<Reward> rewardList = entityFactory.createReward(map);
        //check map if barriers are set on it
        for (Reward reward : rewardList) {
            if (reward.getRewardType() == Constants.REWARD_TYPE.REGULAR_REWARD) {
                Reward mapRegReward = (Reward) map.getEntityAt(reward.getX(), reward.getY());
                assertEquals(reward,mapRegReward);
            }

        }
    }
}
