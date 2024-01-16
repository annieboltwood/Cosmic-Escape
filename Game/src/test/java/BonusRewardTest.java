import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BonusRewardTest {
    private BonusReward reward;

    @BeforeEach
    void setUp(){
        reward = new BonusReward(5, 50, 50, 50, 50);
    }

    @AfterEach
    void tearDown(){
        reward = null;
    }

    @Test
    void GetDuration() {
        reward.setDuration(10);
        assertEquals(10, reward.getDuration(), 0.001);
    }
}
