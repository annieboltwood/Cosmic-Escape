import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RegularRewardTest {

    private RegularReward reward;

    @BeforeEach
    void setUp(){
        reward = new RegularReward(10, 50, 50, 50, 50, Constants.KEY_COLOR.BLUE);
    }

    @AfterEach
    void tearDown(){
        reward = null;
    }

    @Test
    void getColor(){
        assertEquals(Constants.KEY_COLOR.BLUE, reward.getColor());
    }
}
