package bricker.gameobjects;

import danogl.GameObject;
import danogl.collisions.Collision;
import danogl.gui.Sound;
import danogl.gui.WindowController;
import danogl.gui.rendering.Renderable;
import danogl.util.Counter;
import danogl.util.Vector2;

public class Ball extends GameObject
{
    private static final int  MAX_COLLISIONS = 4;
    private int changingBallSpeed;
    private Vector2 windowDimensions;
    private static int originalBallSpeed;
    private Counter direction;
    private Counter ballCollisionsForCamera;
    private Counter collisions;

    private static final int MIN_DISTANCE_FROM_SCREEN_EDGE = 3;

    private Sound collisionSound;
    private Vector2 accelerationVector;
    private int MAX_COLLISIONS_FOR_CAMERA = 4;

    /**
     * Construct a new GameObject instance.
     *
     * @param topLeftCorner Position of the object, in window coordinates (pixels).
     *                      Note that (0,0) is the top-left corner of the window.
     * @param dimensions    Width and height in window coordinates.
     * @param renderable    The renderable representing the object. Can be null, in which case
     *                      the GameObject will not be rendered.
     */
    public Ball(Vector2 topLeftCorner,
                Vector2 dimensions,
                Renderable renderable,
                Sound collisionSound,
                WindowController windowController,
                int changingBallSpeed,
                Counter collisions,
                Counter direction,
                Counter ballCollisionsForCamera)
    {
        super(topLeftCorner, dimensions, renderable);
        this.collisionSound = collisionSound;
        windowDimensions = windowController.getWindowDimensions();
        this.changingBallSpeed = changingBallSpeed;
        this.collisions = collisions;
        originalBallSpeed = changingBallSpeed;
        this.direction = direction;
        this.ballCollisionsForCamera = ballCollisionsForCamera;
        this.transform().setAcceleration(Vector2.ZERO);

        this.setTag("BALL");


    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        if (getCenter().x() > windowDimensions.x() + 30 ||
                getCenter().y() < -10)
        {
            setCenter(new Vector2(windowDimensions.x() / 2, windowDimensions.y() / 2));
        }

    }

    private void addAndCheckCollisionNumberForCamera()
    {

        if (ballCollisionsForCamera.value() >= 1 && ballCollisionsForCamera.value() <= MAX_COLLISIONS_FOR_CAMERA)
        {
            ballCollisionsForCamera.increment();
        }
        else
        {
            ballCollisionsForCamera.reset();
        }
    }

    private void addAndCheckCollisionNumber()
    {

        if (collisions.value() >= 1 && collisions.value() <= MAX_COLLISIONS)
        {

            this.transform().setAcceleration(200, 200);
            collisions.increment();

        }
        else
        {
            this.setVelocity(this.getVelocity().normalized().mult(originalBallSpeed));
            this.transform().setAcceleration(Vector2.ZERO);
            collisions.reset();
        }
    }
    @Override
    public void onCollisionEnter(GameObject other, Collision collision) {
        super.onCollisionEnter(other, collision);
        int xVel = changingBallSpeed;
        //int yVel = changingBallSpeed;
        if (getVelocity().x() < 0) {
            xVel *= -1;
        }
        setVelocity(new Vector2(xVel, getVelocity().y()));
        accelerationVector = new Vector2(getVelocity().x(), getVelocity().y());

        Vector2 newVel = getVelocity().flipped(collision.getNormal()); // getVelocity = current speed
        setVelocity(newVel);

        addAndCheckCollisionNumber();
        addAndCheckCollisionNumberForCamera();
        collisionSound.play();
        this.renderer().setRenderableAngle(direction.value());
    }



}
