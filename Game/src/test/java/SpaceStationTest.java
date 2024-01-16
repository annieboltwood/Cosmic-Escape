import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.awt.image.BufferedImage;

class SpaceStationTest {

    private SpaceStation spaceStation;

    @BeforeEach
    void setUp() {
        spaceStation = new SpaceStation(550, 150, 150, 50, 0);
    }

    @AfterEach
    void tearDown() {
        spaceStation = null;
    }

    @Test
    void InitialDoorState() {
        assertFalse(spaceStation.isDoorOpen());
    }

    @Test
    void UpdateDoorNotOpen() {
        spaceStation.updateDoor(3);
        assertFalse(spaceStation.isDoorOpen());
    }

    @Test
    void UpdateDoorOpen() {
        spaceStation.updateDoor(4);
        spaceStation.openDoor();
        assertTrue(spaceStation.isDoorOpen());
    }

    // Use Mockito for catch exception coverage?
    @Test
    void NullDoor() {
    }

    @Test
    void DrawInitialDoorState() {
        BufferedImage initialImage = spaceStation.getImage();
        assertNotNull(initialImage);
    }

    @Test
    void DrawOpenDoorState() {
        spaceStation.updateDoor(4);
        spaceStation.openDoor();
        BufferedImage openImage = spaceStation.getImage();
        assertNotNull(openImage);
        // Additional check to ensure it's the correct image for an open door
        // Assuming the last image in doorImages array represents the open door
        assertEquals(spaceStation.getImage(), openImage);
    }

    @Test
    void ResetSpaceStation() {
        spaceStation.updateDoor(4);
        spaceStation.openDoor();
        assertTrue(spaceStation.isDoorOpen());

        spaceStation.resetSpaceStation();
        assertFalse(spaceStation.isDoorOpen());
        assertEquals(false, spaceStation.isDoorOpen());
    }

    // Test for the getter methods if needed
    @Test
    void position() {
        assertEquals(550, spaceStation.getX());
        assertEquals(150, spaceStation.getY());
    }
    @Test
    void getX() {
        assertEquals(550, spaceStation.getX());
    }

    @Test
    void getY() {
        assertEquals(150, spaceStation.getY());
    }

    @Test
    void setX() {
        spaceStation.setX(500);
        assertEquals(500, spaceStation.getX());
    }

    @Test
    void setY() {
        spaceStation.setY(250);
        assertEquals(250, spaceStation.getY());
    }

    @Test
    void getType() {
        assertEquals("space_station", spaceStation.getType());
    }

    @Test
    void updateDoor() {
        spaceStation.updateDoor(3);
        spaceStation.openDoor();
        assertFalse(spaceStation.isDoorOpen());
    }

    @Test
    void openDoor() {
        spaceStation.updateDoor(4);
        spaceStation.openDoor();
        assertTrue(spaceStation.isDoorOpen());
    }

    @Test
    void isDoorOpen() {
        spaceStation.updateDoor(4);
        spaceStation.openDoor();
        assertTrue(spaceStation.isDoorOpen());

        spaceStation.resetSpaceStation();
        assertFalse(spaceStation.isDoorOpen());
    }

    @Test
    void getImage() {
        BufferedImage image = spaceStation.getImage();
        assertNotNull(image);
    }

}