package bricker.gameobjects;

import danogl.collisions.GameObjectCollection;
import danogl.gui.Sound;
import danogl.gui.WindowController;
import danogl.gui.rendering.Renderable;
import danogl.util.Counter;
import danogl.util.Vector2;

import java.util.Random;

public class MockBall extends Ball
{

    private Counter direction;
    private final WindowController windowController;
    private final GameObjectCollection gameObjectCollection;

    Random rnd = new Random();


    /**
     * Construct a new GameObject instance.
     *
     * @param topLeftCorner           Position of the object, in window coordinates (pixels).
     *                                Note that (0,0) is the top-left corner of the window.
     * @param dimensions              Width and height in window coordinates.
     * @param renderable              The renderable representing the object. Can be null, in which case
     *                                the GameObject will not be rendered.
     * @param collisionSound
     * @param windowController
     * @param ballSpeed
     * @param ballCollisionsForCamera
     */

    public MockBall(Vector2 topLeftCorner, Vector2 dimensions, Renderable renderable, Sound collisionSound, WindowController windowController, int ballSpeed, GameObjectCollection gameObjectCollection, Counter collision, Counter direction, Counter ballCollisionsForCamera) {
        super(topLeftCorner, dimensions, renderable, collisionSound, windowController, ballSpeed, collision, direction, ballCollisionsForCamera);
        this.windowController = windowController;
        this.gameObjectCollection = gameObjectCollection;
        this.direction = direction;
        float ballVelX = ballSpeed;
        float ballVelY = ballSpeed;
        if (rnd.nextBoolean())
        {
            ballVelX *= rnd.nextFloat(-1, 1);
        }
            ballVelY *= 1;

        this.setVelocity(new Vector2(ballVelX, ballVelY));
    }

    public void update(float deltaTime) {
        super.update(deltaTime);
        checkOutOfScreen();
    }
    public void checkOutOfScreen()
    {
        if (getCenter().y() > windowController.getWindowDimensions().y())
            gameObjectCollection.removeGameObject(this);

    }
}
