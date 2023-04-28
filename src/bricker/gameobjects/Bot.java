package bricker.gameobjects;

import danogl.GameObject;
import danogl.gui.WindowController;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;

public abstract class Bot extends GameObject
{

    private static final float MOVEMENT_SPEED = 200;
    private WindowController windowController;
    private GameObject ball;

    /**
     * Construct a new GameObject instance.
     *
     * @param topLeftCorner Position of the object, in window coordinates (pixels).
     *                      Note that (0,0) is the top-left corner of the window.
     * @param dimensions    Width and height in window coordinates.
     * @param renderable    The renderable representing the object. Can be null, in which case
     *                      the GameObject will not be rendered.
     */
    public Bot(Vector2 topLeftCorner, Vector2 dimensions, Renderable renderable, WindowController windowController, GameObject ball) {
        super(topLeftCorner, dimensions, renderable);
        this.windowController = windowController;

        this.ball = ball;
    }



    public void followBall()
    {
        Vector2 movementDirectionX = Vector2.ZERO;

        if (getCenter().x() < ball.getCenter().x())
        {
            movementDirectionX = Vector2.RIGHT;
        }

        else if (getCenter().x() > ball.getCenter().x())
        {
            movementDirectionX = Vector2.LEFT;
        }


        setVelocity(movementDirectionX.mult(MOVEMENT_SPEED));
    }

    public void escapeBall()
    {
        if (this.getCenter().x() < windowController.getWindowDimensions().x() - getDimensions().x() / 2)
        {
            setVelocity(Vector2.RIGHT.mult(MOVEMENT_SPEED));
        }
        else
            setVelocity(Vector2.ZERO);
    }


}
