package bricker.brick_strategies;

import bricker.BouncingBallGameManager;
import danogl.GameObject;
import danogl.collisions.GameObjectCollection;
import danogl.gui.WindowController;
import danogl.gui.rendering.Camera;
import danogl.util.Counter;
import danogl.util.Vector2;

public class CameraStrategy extends RemoveBrickStrategy
{

    private static final int MAX_BALL_COLLISIONS = 4;
    private BouncingBallGameManager gameManager;
    private Counter activateCamera;
    private Counter ballCollisionsForCamera;
    private WindowController windowController;
    private GameObject ball;

    public CameraStrategy(GameObjectCollection gameObjectCollection, Counter bricksLeft, BouncingBallGameManager gameManager, Counter activateCamera, Counter ballCollisionsForCamera, WindowController windowController, GameObject ball) {
        super(gameObjectCollection, bricksLeft);
        this.gameManager = gameManager;
        this.activateCamera = activateCamera;
        this.ballCollisionsForCamera = ballCollisionsForCamera;
        this.windowController = windowController;
        this.ball = ball;
    }

    public void cameraNewPosition()
    {
        gameManager.setCamera(
                new Camera(
                        ball, 			//object to follow
                        Vector2.ZERO, 	//follow the center of the object
                        windowController.getWindowDimensions().mult(1.2f),  //widen the frame a bit
                        windowController.getWindowDimensions()   //share the window dimensions
                )
        );

    }




    public void onCollision(GameObject thisObj, GameObject otherObj)
    {
        super.onCollision(thisObj, otherObj);

        ballCollisionsForCamera.increment();
        cameraNewPosition();
    }

}
