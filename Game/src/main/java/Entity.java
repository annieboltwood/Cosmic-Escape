/**
 * The Entity class represents a generic game entity with position and type information.
 * Entities can be different types and they are defined by their x and y coordinates.
 *
 */
public class Entity {
    int x;
    int y;

    String type;

    /**
     * Gets the x-coordinate of the entity.
     *
     * @return The x-coordinate of the entity.
     */
    public int getX(){
        return x;
    }

    /**
     * Gets the y-coordinate of the entity.
     *
     * @return The y-coordinate of the entity.
     */
    public int getY(){
        return y;
    }

    /**
     * Sets the x-coordinate of the entity.
     *
     * @param x1 The new x-coordinate value to set.
     */
    public void setX(int x1) {
        this.x = x1;
    }

    /**
     * Sets the y-coordinate of the entity.
     *
     * @param y1 The new y-coordinate value to set.
     */
    public void setY(int y1) {
        this.y = y1;
    }

    /**
     * Gets the type of the entity.
     *
     * @return The type of the entity.
     */
    public String getType() {
        return type;
    }


}
