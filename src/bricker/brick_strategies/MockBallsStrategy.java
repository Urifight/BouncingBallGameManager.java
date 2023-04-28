package bricker.brick_strategies;

import bricker.gameobjects.MockBall;
import danogl.GameObject;
import danogl.collisions.GameObjectCollection;
import danogl.gui.Sound;
import danogl.gui.WindowController;
import danogl.gui.rendering.Renderable;
import danogl.util.Counter;
import danogl.util.Vector2;

public class MockBallsStrategy extends RemoveBrickStrategy
{

    private static final int BALL_RADIUS = 35;
    private GameObjectCollection gameObjectCollection;
    private int ballSpeed;
    private Renderable mockBallImage;
    private WindowController windowController;
    private Sound collisionSound;
    private Counter collisions;
    private Counter direction;
    private Counter ballCollisionsForCamera;
    private boolean normalMode;

    public MockBallsStrategy(GameObjectCollection gameObjectCollection,
                             Counter bricksLeft,
                             Renderable mockBallImage,
                             WindowController windowController,
                             Sound collisionSound,
                             int ballSpeed,
                             Counter collisions,
                             Counter direction,
                             Counter ballCollisionsForCamera,
                             boolean normalMode) {
        super(gameObjectCollection, bricksLeft);
        this.gameObjectCollection = gameObjectCollection;
        this.ballSpeed = ballSpeed;
        this.mockBallImage = mockBallImage;
        this.windowController = windowController;


        this.collisionSound = collisionSound;
        this.collisions = collisions;
        this.direction = direction;
        this.ballCollisionsForCamera = ballCollisionsForCamera;
        this.normalMode = normalMode;
    }

    @Override
    public void onCollision(GameObject thisObj, GameObject otherObj) {
        super.onCollision(thisObj, otherObj);
        GameObject mockBall = new MockBall(
                Vector2.ZERO, new Vector2(BALL_RADIUS, BALL_RADIUS), mockBallImage, collisionSound, windowController, ballSpeed, gameObjectCollection, collisions, direction, ballCollisionsForCamera);

        mockBall.setCenter(new Vector2(getThisObj().getCenter().x(), getThisObj().getCenter().y() + 90));
        gameObjectCollection.addGameObject(mockBall);

        if (!normalMode) {
            mockBall = new MockBall(
                    Vector2.ZERO, new Vector2(BALL_RADIUS, BALL_RADIUS), mockBallImage, collisionSound, windowController, ballSpeed, gameObjectCollection, collisions, direction, ballCollisionsForCamera);

            mockBall.setCenter(new Vector2(getThisObj().getCenter().x() + 30, getThisObj().getCenter().y() + 90));
            gameObjectCollection.addGameObject(mockBall);
            mockBall = new MockBall(
                    Vector2.ZERO, new Vector2(BALL_RADIUS, BALL_RADIUS), mockBallImage, collisionSound, windowController, ballSpeed, gameObjectCollection, collisions, direction, ballCollisionsForCamera);

            mockBall.setCenter(new Vector2(getThisObj().getCenter().x() - 30, getThisObj().getCenter().y() + 90));
            gameObjectCollection.addGameObject(mockBall);
        }
    }
}
