package bricker.gameobjects;

import danogl.GameObject;
import danogl.collisions.GameObjectCollection;
import danogl.gui.WindowController;
import danogl.gui.rendering.Renderable;
import danogl.util.Counter;
import danogl.util.Vector2;

public class Widen extends Status
{

    private WindowController windowController;
    private Counter paddleSizeBuffChange;
    private GameObject paddle;

    /**
     * Construct a new GameObject instance.
     *
     * @param topLeftCorner        Position of the object, in window coordinates (pixels).
     *                             Note that (0,0) is the top-left corner of the window.
     * @param dimensions           Width and height in window coordinates.
     * @param renderable           The renderable representing the object. Can be null, in which case
     *                             the GameObject will not be rendered.
     * @param gameObjectCollection
     * @param windowController
     * @param paddleSizeBuffChange
     */
    public Widen(Vector2 topLeftCorner, Vector2 dimensions, Renderable renderable, GameObjectCollection gameObjectCollection, WindowController windowController, Counter paddleSizeBuffChange, GameObject paddle) {
        super(topLeftCorner, dimensions, renderable, gameObjectCollection, windowController);
        this.windowController = windowController;
        this.paddleSizeBuffChange = paddleSizeBuffChange;
        this.paddle = paddle;
        this.setTag("WIDEN");

    }

    public void enablePower(GameObject other)
    {
        other.setDimensions(new Vector2(other.getDimensions().x() + 30, other.getDimensions().y()));
    }
}
