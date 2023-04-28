package bricker.gameobjects;

import danogl.GameObject;
import danogl.collisions.GameObjectCollection;
import danogl.gui.WindowController;
import danogl.gui.rendering.Renderable;
import danogl.util.Counter;
import danogl.util.Vector2;

public class QuickenMotion extends Status
{
    private GameObjectCollection gameObjectCollection;
    private WindowController windowController;
    private Counter slowOrQuicken;

    /**
     * Construct a new GameObject instance.
     *
     * @param topLeftCorner Position of the object, in window coordinates (pixels).
     *                      Note that (0,0) is the top-left corner of the window.
     * @param dimensions    Width and height in window coordinates.
     * @param renderable    The renderable representing the object. Can be null, in which case
     *                      the GameObject will not be rendered.
     * @param slowOrQuicken
     */
    public QuickenMotion(Vector2 topLeftCorner, Vector2 dimensions, Renderable renderable, GameObjectCollection gameObjectCollection, WindowController windowController, Counter slowOrQuicken)
    {
        super(topLeftCorner, dimensions, renderable, gameObjectCollection, windowController);
        this.gameObjectCollection = gameObjectCollection;
        this.windowController = windowController;
        this.slowOrQuicken = slowOrQuicken;

        slowOrQuicken.reset();
        slowOrQuicken.increaseBy(1);
    }
    public void enablePower(GameObject other)
    {
        windowController.setTimeScale(1.3f);

    }



}
