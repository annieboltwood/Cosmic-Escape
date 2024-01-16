/**
 * The Main class serves is the entry point for the application.
 * It creates an instance of the Game class and starts the game.
 *
 * @author Ananga Bajgai, Annie Boltwood, Mohamed Mustafa, Austin Yu
 * @version 1.0
 */
public class Main {

    /**
     * The main method creates an instance of the Game class and starts the game.
     *
     * @param args The command-line arguments passed to the program.
     */
    public static void main(String[] args) {
        Game g = new Game();
        g.start();
    }

}
