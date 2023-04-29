package bricker.gameobjects;

import danogl.GameObject;
import danogl.collisions.Collision;
import danogl.collisions.GameObjectCollection;
import danogl.collisions.Layer;
import danogl.gui.WindowController;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;

public abstract class Status extends GameObject
{

    private static final int VELOCITY = 15;
    private GameObjectCollection gameObjectCollection;
    private WindowController windowController;

    /**
     * Construct a new GameObject instance.
     *
     * @param topLeftCorner Position of the object, in window coordinates (pixels).
     *                      Note that (0,0) is the top-left corner of the window.
     * @param dimensions    Width and height in window coordinates.
     * @param renderable    The renderable representing the object. Can be null, in which case
     *                      the GameObject will not be rendered.
     */
    public Status(Vector2 topLeftCorner, Vector2 dimensions, Renderable renderable, GameObjectCollection  gameObjectCollection, WindowController windowController) {
        super(topLeftCorner, dimensions, renderable);
        this.gameObjectCollection = gameObjectCollection;
        this.windowController = windowController;
        setVelocity(new Vector2(0, VELOCITY).mult(15));
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        if (getCenter().y() > windowController.getWindowDimensions().y())
            gameObjectCollection.removeGameObject(this, Layer.STATIC_OBJECTS);

    }


    @Override
    public void onCollisionEnter(GameObject other, Collision collision) {
        super.onCollisionEnter(other, collision);
        if (other.getTag().equals("PADDLE")) {
            gameObjectCollection.removeGameObject(this, Layer.STATIC_OBJECTS);
            enablePower(other);
        }
        else
            System.out.println(other.getTag());

    }

    public void enablePower(GameObject other)
    {

    }
    @Override
    public boolean shouldCollideWith(GameObject other) {
        if (other.getTag().equals("PADDLE"))
            return true;
        //return super.shouldCollideWith(other);
        return false;
    }
}
