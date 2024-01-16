import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.event.KeyEvent;

import static org.junit.jupiter.api.Assertions.*;


class GameTest {

    Game g;
    public GameTest(){
        g = new Game();
    }
    @BeforeEach
    void setup(){
        g = new Game();
    }
    @Test
    public void gameInitializationTest() {
        assertNotNull(g);
    }

    @Test
    public void testStartMethod() {
        assertFalse(g.getFrame().isVisible());
        g.start();
        assertTrue(g.getFrame().isVisible());
    }

    @Test
    void changeGameStateTest() {
        setup();
        //test if game state changes properly
        //if spacebar is clicked, at start_screen, then it should go to PLAY game state
        g.setCurrentState(Constants.GAME_STATE.START_SCREEN);
        g.handleKeyPresses(KeyEvent.VK_SPACE);
        assertEquals(Constants.GAME_STATE.PLAY,g.getCurrentState());

        //if spacebar is clicked, at play Screem, then game state shouldn't change
        g.setCurrentState(Constants.GAME_STATE.PLAY);
        g.handleKeyPresses(KeyEvent.VK_SPACE);
        assertEquals(Constants.GAME_STATE.PLAY,g.getCurrentState());

        //if spacebar is clicked, when state is at game over, then game state change to start screen
        g.setCurrentState(Constants.GAME_STATE.GAMEOVER);
        g.handleKeyPresses(KeyEvent.VK_SPACE);
        assertEquals(Constants.GAME_STATE.START_SCREEN,g.getCurrentState());

        //if spacebar is clicked, when state is at game over, then game state change to start screen
        g.setCurrentState(Constants.GAME_STATE.GAMEOVER);
        g.handleKeyPresses(KeyEvent.VK_SPACE);
        assertEquals(Constants.GAME_STATE.START_SCREEN,g.getCurrentState());


    }

    @Test
    void handleKeyPressesTest() {
        setup();
        //test if game state changes properly
        //if spacebar is clicked, at start_screen, then it should go to PLAY game state
        g.setCurrentState(Constants.GAME_STATE.START_SCREEN);
        g.handleKeyPresses(KeyEvent.VK_SPACE);
        assertEquals(Constants.GAME_STATE.PLAY,g.getCurrentState());

        //if spacebar is clicked, at play Screem, then game state shouldn't change
        g.setCurrentState(Constants.GAME_STATE.PLAY);
        g.handleKeyPresses(KeyEvent.VK_SPACE);
        assertEquals(Constants.GAME_STATE.PLAY,g.getCurrentState());

        //if spacebar is clicked, when state is at game over, then game state change to start screen
        g.setCurrentState(Constants.GAME_STATE.GAMEOVER);
        g.handleKeyPresses(KeyEvent.VK_SPACE);
        assertEquals(Constants.GAME_STATE.START_SCREEN,g.getCurrentState());

        //if spacebar is clicked, when state is at game over, then game state change to start screen
        g.setCurrentState(Constants.GAME_STATE.GAMEOVER);
        g.handleKeyPresses(KeyEvent.VK_SPACE);
        assertEquals(Constants.GAME_STATE.START_SCREEN,g.getCurrentState());

        //player shouldn't be able to move when game_state is at start Screen or game_over or pause
        g.setCurrentState(Constants.GAME_STATE.GAMEOVER);
        int y = g.getPlayer().getY();
        g.handleKeyPresses(KeyEvent.VK_UP);
        assertEquals(y,g.getPlayer().getY());

        g.setCurrentState(Constants.GAME_STATE.START_SCREEN);
        int x = g.getPlayer().getX();
        g.handleKeyPresses(KeyEvent.VK_RIGHT);
        assertEquals(x,g.getPlayer().getX());

        g.setCurrentState(Constants.GAME_STATE.PAUSED);
        y = g.getPlayer().getY();
        g.handleKeyPresses(KeyEvent.VK_DOWN);
        assertEquals(y,g.getPlayer().getY());

        //player should not be able to move when game_state is at play
        g.setCurrentState(Constants.GAME_STATE.PLAY);
        x = g.getPlayer().getX();
        g.handleKeyPresses(KeyEvent.VK_LEFT);
        assertNotEquals(x,g.getPlayer().getX());

        g.setCurrentState(Constants.GAME_STATE.PLAY);
        y = g.getPlayer().getY();
        g.handleKeyPresses(KeyEvent.VK_DOWN);
        assertNotEquals(y,g.getPlayer().getY());




    }


    @Test
    void createEntityFactory() {
        assertNotNull(g.createEntityFactory());
    }




}