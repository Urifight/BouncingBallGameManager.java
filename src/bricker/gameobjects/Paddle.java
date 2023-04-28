package bricker.gameobjects;

import bricker.movement_stragety.MovementStrategy;
import danogl.GameObject;
import danogl.collisions.Collision;
import danogl.collisions.GameObjectCollection;
import danogl.gui.rendering.Renderable;
import danogl.util.Counter;
import danogl.util.Vector2;

public class Paddle extends GameObject
{
    private static final float MIN_DISTANCE_FROM_SCREEN_EDGE = 3;

    private static final float MOVEMENT_SPEED = 400;
    private final Vector2 windowDimensions;
    private final float height;
    private final float width;
    private MovementStrategy movementStrategy;
    private boolean temporaryPaddle;
    private GameObjectCollection gameObjectCollection;
    private Counter paddleSizeBuffChange;

    private static final int MAX_COLLISIONS = 3;
    private int countCollisions = 0;


    /**
     * Construct a new GameObject instance.
     *
     * @param topLeftCorner    Position of the object, in window coordinates (pixels).
     *                         Note that (0,0) is the top-left corner of the window.
     * @param dimensions       Width and height in window coordinates.
     * @param renderable       The renderable representing the object. Can be null, in which case
     *                         the GameObject will not be rendered.
     * @param movementStrategy
     */
    public Paddle(Vector2 topLeftCorner, Vector2 dimensions, Renderable renderable, Vector2 windowDimensions, MovementStrategy movementStrategy, boolean temporaryPaddle, GameObjectCollection gameObjectCollection, Counter paddleSizeBuffChange)
    {

        super(topLeftCorner, dimensions, renderable);
        this.windowDimensions = windowDimensions;
        this.movementStrategy = movementStrategy;

        this.temporaryPaddle = temporaryPaddle;
        this.gameObjectCollection = gameObjectCollection;
        this.paddleSizeBuffChange = paddleSizeBuffChange;

        height = getDimensions().y();
        width = getDimensions().x();

        this.setTag("PADDLE");
    }



    public void padInWalls()
    {
        if (getTopLeftCorner().x() < MIN_DISTANCE_FROM_SCREEN_EDGE)
        {
            transform().setTopLeftCorner(MIN_DISTANCE_FROM_SCREEN_EDGE + 10, getTopLeftCorner().y());
        }
        else if(getTopLeftCorner().x() > windowDimensions.x() - MIN_DISTANCE_FROM_SCREEN_EDGE - getDimensions().x())
        {
            transform().setTopLeftCorner(windowDimensions.x() - MIN_DISTANCE_FROM_SCREEN_EDGE - getDimensions().x() - 10,
                    getTopLeftCorner().y());
        }
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        Vector2 movementDirection = movementStrategy.calcMovementDirection(this);
        setVelocity(movementDirection.mult(MOVEMENT_SPEED));
        padInWalls();

    }


    public void checkIfCollidedEnough()
    {
        if (countCollisions == MAX_COLLISIONS)
            gameObjectCollection.removeGameObject(this);

    }
    @Override
    public void onCollisionEnter(GameObject other, Collision collision) {
        super.onCollisionEnter(other, collision);


        if (temporaryPaddle && other.getTag().equals("BALL")) {
            countCollisions++;
            checkIfCollidedEnough();
        }

    }

}
