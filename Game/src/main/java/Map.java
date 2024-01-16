import java.util.ArrayList;
import java.util.List;

public class Map {
    private int width;
    private int height;

    private static Entity[][] mapGrid;

    //entities that can move

    //includes a string representation of their name so that we know which action to take
    //includes a position(x,y) so we know where to access in the map
    //private List<List<String>> movableEntities;

    private MainCharacter player;
    private ArrayList<Barrier> barriers;

    private ArrayList<Reward> rewards;

    private ArrayList<MovingEnemy> movingEnemies;
    private ArrayList<Punishment> punishments;
    private ScoreBoard scoreBoard;

    private GameTime clock;



    public Map(int mapWidth, int mapHeight){


        this.width = mapWidth;
        this.height = mapHeight;
        mapGrid = new Entity[mapWidth][mapHeight];
    }


    public Entity getEntityAt(int x, int y) {
        return mapGrid[x][y];
    }

    public void setEntityAt(int x, int y, Entity entity) {
        mapGrid[x][y] = entity;
    }

    public Entity[][] getMapGrid() {
        return mapGrid;
    }


    //set null at mapgrid point after an entity moves to another space
    public void setNullAt(int x, int y) { mapGrid[x][y] = null;}

    // reset map values
    public void resetMap(){
        this.mapGrid = new Entity[width][height];
    }


}
