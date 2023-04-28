package bricker.gameobjects;

import bricker.BouncingBallGameManager;
import danogl.GameObject;
import danogl.gui.WindowController;
import danogl.gui.rendering.Camera;
import danogl.gui.rendering.Renderable;
import danogl.util.Counter;
import danogl.util.Vector2;

public class CameraObject extends GameObject
{

    private BouncingBallGameManager gameManager;
    private Counter activateCamera;
    private Counter ballCollisionsForCamera;
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
    public CameraObject(Vector2 topLeftCorner, Vector2 dimensions, Renderable renderable, BouncingBallGameManager gameManager, Counter ballCollisionsForCamera, WindowController windowController, GameObject ball) {
        super(topLeftCorner, dimensions, renderable);
        this.gameManager = gameManager;
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
}
