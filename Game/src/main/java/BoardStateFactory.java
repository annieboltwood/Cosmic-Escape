import java.util.Dictionary;

/**
 * The BoardStateFactory class creates instances of game state-related objects.
 * Has the logic for the game map, timer, and scoreboard.
 *
 */
public class BoardStateFactory {

    /**
     * Constructs a BoardStateFactory object.
     */
    public BoardStateFactory(){}


    /**
     * Creates and returns an instance of the Map with dimensions.
     *
     * @param mapWidth  The width of the game map.
     * @param mapHeight The height of the game map.
     * @return An instance of the Map with the specified dimensions.
     */
    public Map createMap(int mapWidth, int mapHeight)
                        { return new Map(mapWidth, mapHeight); }

    /**
     * Creates and returns an instance of the GameTime.
     * The GameTime class represents the game clock and manages time during gameplay.
     *
     * @return An instance of the GameTime.
     */
    public GameTime createTimer() {
        return new GameTime();
    }

    /**
     * Creates and returns an instance of the ScoreBoard with the given keysTracker.
     * The ScoreBoard displays the player's score and keys collected.
     *
     * @param keysTracker A dictionary containing information about the player's collected keys.
     * @return An instance of the ScoreBoard.
     */
    public ScoreBoard createScoreboard(Dictionary<Constants.KEY_COLOR, Integer> keysTracker) {
        return new ScoreBoard(keysTracker);
    }

}
