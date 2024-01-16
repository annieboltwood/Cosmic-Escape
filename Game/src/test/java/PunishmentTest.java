import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PunishmentTest {

    private Punishment punishment;

    @BeforeEach
    void setUp() {
        punishment = new Punishment(65, 100, 50, 50, 5);
    }

    @AfterEach
    void tearDown() {
        punishment = null;
    }

    @Test
    void getX() {
        assertEquals(65, punishment.getX());
    }

    @Test
    void getY() {
        assertEquals(100, punishment.getY());
    }

    @Test
    void setX() {
        punishment.setX(150);
        assertEquals(150, punishment.getX());
    }

    @Test
    void setY() {
        punishment.setY(150);
        assertEquals(150, punishment.getY());
    }

    @Test
    void getType() {
        assertEquals("punishment", punishment.getType());
    }


    @Test
    void testKeepDealingDamage() {
        // Set to true at first
        assertTrue(punishment.getKeepDealingDamage());
        // Change to false and check it's false
        punishment.setKeepDealingDamage(false);
        assertFalse(punishment.getKeepDealingDamage());
    }

    @Test
    void testSetDamageTimeoutStartTime() {
        punishment.setDamageTimeoutStartTime(123);
        assertEquals(123, punishment.setDamageTimeoutStartTime());
    }

    @Test
    void manageDamageDealingTimeout() {

        //case player has collided with punishment initially
        punishment.setDamageTimeoutStartTime(0);
        punishment.setKeepDealingDamage(true);
        punishment.manageDamageDealingTimeout(0);
        assertFalse(punishment.getKeepDealingDamage());
        int damageTimeoutduration = 25;

        //case player has already collided with punishment initially, and now in a state where touching the same
        //punishment does not affect them
        punishment.manageDamageDealingTimeout(1);
        assertFalse(punishment.getKeepDealingDamage());


        //case player is at time after the damageTimeoutduration has worn off
        //currently 25 frames after first touch with punishment, punishment deals dmg again
        punishment.manageDamageDealingTimeout(25);
        assertTrue(punishment.getKeepDealingDamage());

    }

    @Test
    void getDamageAmt() {
        //Case that punishment is dealing dmg
        assertTrue(punishment.getKeepDealingDamage());
        assertEquals(5, punishment.getDamageAmt());

        //Set punishment to stop dealing damage
        punishment.setKeepDealingDamage(false);

        //Case that punishment isn't dealing dmg
        assertFalse(punishment.getKeepDealingDamage());
        assertEquals(0, punishment.getDamageAmt());
    }
}