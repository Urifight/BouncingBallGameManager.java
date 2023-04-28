package bricker.gameobjects;

import danogl.GameObject;
import danogl.gui.WindowController;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;

public class GoodBot extends Bot
{

    private GameObject ball;

    /**
     * Construct a new GameObject instance.
     *
     * @param topLeftCorner    Position of the object, in window coordinates (pixels).
     *                         Note that (0,0) is the top-left corner of the window.
     * @param dimensions       Width and height in window coordinates.
     * @param renderable       The renderable representing the object. Can be null, in which case
     *                         the GameObject will not be rendered.
     * @param windowController
     * @param ball
     * @param thisObj
     */
    public GoodBot(Vector2 topLeftCorner, Vector2 dimensions, Renderable renderable, WindowController windowController, GameObject ball) {
        super(topLeftCorner, dimensions, renderable, windowController, ball);
        this.ball = ball;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        if (ball.getCenter().y() < getCenter().y())
        {
            followBall();
        }
        else if (ball.getCenter().y() > getCenter().y())
        {
            escapeBall();
        }
    }
}
