import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class RewardTest{

    private Reward reward;

    @BeforeEach
    void setUp(){
        reward = new Reward(10, 50, 50);
    }

    @AfterEach
    void tearDown(){
        reward = null;
    }

    @Test
    void getRewardType() {
        assertEquals(Constants.REWARD_TYPE.GENERIC, reward.getRewardType());
    }

    @Test
    void setRewardType(){
        reward.setRewardType(Constants.REWARD_TYPE.BONUS_REWARD);
        assertEquals(Constants.REWARD_TYPE.BONUS_REWARD, reward.getRewardType());
    }

    @Test
    void setScoreAmt(){
        reward.setScoreAmt(20);
        assertEquals(20, reward.getScoreAmt());
    }

    @Test
    void setActivateTime(){
        reward.setActivateTime(100);
        assertEquals(100, reward.getActivateTime());
    }

    @Test
    void setDeactivateTime(){
        reward.setDeactivateTime(200);
        assertEquals(200, reward.getDeactivateTime());
    }

    @Test
    void resetToRandomCoordinate(){
        reward.resetToRandomCoordinate();
        assertTrue(reward.getX() >= 0 && reward.getX() < Constants.RANDOM_VALUE * 50);
        assertTrue(reward.getY() >= 0 && reward.getY() < Constants.RANDOM_VALUE * 50);
    }

}
