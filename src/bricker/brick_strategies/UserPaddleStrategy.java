package bricker.brick_strategies;

import danogl.GameObject;
import danogl.collisions.GameObjectCollection;
import danogl.gui.Sound;
import danogl.gui.UserInputListener;
import danogl.gui.WindowController;
import danogl.gui.rendering.Renderable;
import danogl.util.Counter;

import java.util.Random;

public class UserPaddleStrategy extends RemoveBrickStrategy {
    private static final int MAX_COLLISIONS = 3;
    Random rnd = new Random();
    private static final float PADDLE_HEIGHT = 30;
    private static final float PADDLE_WIDTH = 210;
    private final GameObjectCollection gameObjectCollection;
    private final WindowController windowController;
    private final Sound collisionSound;
    private float bricksTotalHeight;
    private Counter paddleSizeBuffChange;
    private GameObject paddle;
    private Renderable paddleImage;
    private UserInputListener inputListener;

    private int countCollisions = 0;

    public UserPaddleStrategy(GameObjectCollection gameObjectCollection,
                              Counter bricksLeft,
                              Renderable paddleImage,
                              WindowController windowController,
                              UserInputListener inputListener,
                              Sound collisionSound,
                              float bricksTotalHeight,
                              Counter paddleSizeBuffChange,
                              GameObject paddle) {
        super(gameObjectCollection, bricksLeft);
        this.gameObjectCollection = gameObjectCollection;
        this.paddleImage = paddleImage;
        this.windowController = windowController;

        this.inputListener = inputListener;
        this.collisionSound = collisionSound;
        this.bricksTotalHeight = bricksTotalHeight;
        this.paddleSizeBuffChange = paddleSizeBuffChange;
        this.paddle = paddle;
    }


    @Override
    public void onCollision(GameObject thisObj, GameObject otherObj) {
        super.onCollision(thisObj, otherObj);
    }
}
//        countCollisions++;
//
//        Vector2 windowDimensions = windowController.getWindowDimensions();
//
//        GameObject userPaddle = new Paddle(
//                Vector2.ZERO,
//                new Vector2(paddle.getDimensions().x(), paddle.getDimensions().y()),
//                paddleImage,
//                windowDimensions,
//                new UserMovementStragety(inputListener, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT), true, gameObjectCollection, paddleSizeBuffChange);
//            //rnd.nextFloat(10, windowDimensions.x() - 10)
//
//        userPaddle.setCenter(new Vector2(paddle.getCenter().x(), rnd.nextFloat(bricksTotalHeight + 30, windowDimensions.y() - 10)));
//
//        gameObjectCollection.addGameObject(userPaddle);
//    }
//}
