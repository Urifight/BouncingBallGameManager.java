package bricker.brick_strategies;

import bricker.BouncingBallGameManager;
import danogl.GameObject;
import danogl.collisions.GameObjectCollection;
import danogl.gui.Sound;
import danogl.gui.UserInputListener;
import danogl.gui.WindowController;
import danogl.gui.rendering.Renderable;
import danogl.util.Counter;

import java.util.Random;

public class BrickStrategyFactory {
    private final int minStrategies;
    private GameObjectCollection gameObjectCollection;
    private Renderable mockBallImage;
    private WindowController windowController;
    private Random random = new Random();
    private static final int MAX_STRATEGIES = 8;
    private final Counter bricksLeft;
    private int ballSpeed;
    private UserInputListener inputListener;
    private Renderable gravityImage;

    private int min = 0;
    private Counter collisions;
    private Sound collisionSound;
    private Renderable paddleImage;
    private float bricksTotalHeight;
    private Renderable slowMotionImage;
    private Renderable quickenMotionImage;
    private Counter slowOrQuicken;
    private Renderable widen;
    private Renderable narrow;
    private Counter paddleSizeBuffChange;
    private BouncingBallGameManager bouncingBallGameManager;
    private Counter randomStrategy;
    private Counter ballX;
    private Counter ballY;
    private Counter activateCamera;
    private Counter activateGravity;
    private Counter ballCollisionsForCamera;
    private GameObject ball;
    private Renderable goodBotImage;
    private Renderable badBotImage;
    private int maxStrategies;
    private Renderable redBrick;
    private Counter direction;
    private GameObject paddle;
    private boolean normalMode;
    private Counter ballFirstCollision;
    private float originalBallSpeedX;
    private float originalBallSpeedY;

    public BrickStrategyFactory(GameObjectCollection gameObjectCollection,
                                Counter bricksLeft,
                                Renderable mockBallImage,
                                WindowController windowController,
                                UserInputListener inputListener,
                                Sound collisionSound,
                                Renderable paddleImage,
                                float bricksTotalHeight,
                                int ballSpeed,
                                Renderable gravityImage,
                                Counter collisions,
                                Counter direction,
                                Renderable slowMotionImage,
                                Renderable quickenMotionImage,
                                Counter slowOrQuicken,
                                Renderable widen,
                                Renderable narrow,
                                Counter paddleSizeBuffChange,
                                BouncingBallGameManager bouncingBallGameManager,
                                Counter randomStrategy,
                                Counter ballX,
                                Counter ballY,
                                Counter activateCamera,
                                Counter activateGravity,
                                Counter ballCollisionsForCamera,
                                GameObject ball,
                                Renderable goodBotImage,
                                Renderable badBotImage,
                                int minStrategies,
                                int maxStrategies,
                                GameObject paddle,
                                boolean normalMode)
    {
        this.bricksLeft = bricksLeft;
        this.ballSpeed = ballSpeed;
        this.gameObjectCollection = gameObjectCollection;
        this.direction = direction;
        this.mockBallImage = mockBallImage;
        this.windowController = windowController;
        this.inputListener = inputListener;
        this.gravityImage = gravityImage;
        this.collisions = collisions;
        this.collisionSound = collisionSound;
        this.paddleImage = paddleImage;
        this.bricksTotalHeight = bricksTotalHeight;
        this.slowMotionImage = slowMotionImage;
        this.quickenMotionImage = quickenMotionImage;
        this.slowOrQuicken = slowOrQuicken;
        this.widen = widen;
        this.narrow = narrow;
        this.paddleSizeBuffChange = paddleSizeBuffChange;
        this.bouncingBallGameManager = bouncingBallGameManager;
        this.randomStrategy = randomStrategy;
        this.ballX = ballX;
        this.ballY = ballY;
        this.activateCamera = activateCamera;
        this.activateGravity = activateGravity;
        this.ballCollisionsForCamera = ballCollisionsForCamera;
        this.ball = ball;
        this.goodBotImage = goodBotImage;
        this.badBotImage = badBotImage;
        this.minStrategies = minStrategies;
        this.maxStrategies = maxStrategies;
        this.paddle = paddle;
        this.normalMode = normalMode;
        this.ballFirstCollision = ballFirstCollision;
        this.redBrick = redBrick;
    }

    public CollisionStrategy getStrategy() {
        //choose randomly between the possible brick strategies
        int chosenStrategy;
        chosenStrategy = random.nextInt(minStrategies, maxStrategies);
//        while (chosenStrategy == randomStrategy.value()) {
//            chosenStrategy = random.nextInt(1, MAX_STRATEGIES);
//        }

        randomStrategy.reset();
        randomStrategy.increaseBy(chosenStrategy);
        CollisionStrategy collisionStrategy = null;
        switch (chosenStrategy) {
            case 0:
                collisionStrategy = new RemoveBrickStrategy(gameObjectCollection, bricksLeft);
                break;

            case 1:
                collisionStrategy = new MockBallsStrategy(gameObjectCollection, bricksLeft, mockBallImage, windowController, collisionSound, ballSpeed, collisions, direction, ballCollisionsForCamera, normalMode, ballFirstCollision);
                break;

            case 2:
                collisionStrategy = new UserPaddleStrategy(gameObjectCollection, bricksLeft, paddleImage, windowController, inputListener, collisionSound, bricksTotalHeight, paddleSizeBuffChange, paddle);
                break;

            case 3:
                collisionStrategy = new GravityStrategy(gameObjectCollection, bricksLeft, gravityImage, windowController, collisionSound, collisions, direction);
                break;

            case 4:
                collisionStrategy = new MotionStrategy(gameObjectCollection, bricksLeft, windowController, slowMotionImage, quickenMotionImage, slowOrQuicken);
                break;

            case 5:
                collisionStrategy = new WidenNarrowStrategy(gameObjectCollection, bricksLeft, windowController, widen, narrow, paddleSizeBuffChange, paddle);
                break;

            case 6:
                collisionStrategy = new BotStrategy(gameObjectCollection, bricksLeft, windowController, bricksTotalHeight, goodBotImage, badBotImage, ball);
                break;

            case 7:
                collisionStrategy = doubleStrategy();
                break;
        }
        return collisionStrategy;
    }

    private CollisionStrategy doubleStrategy()
    {

        CollisionStrategy collisionStrategy;
        CollisionStrategy chosenCollisionStrategy = null;
        CollisionStrategy collisionStrategy1 = null;
        CollisionStrategy collisionStrategy2 = null;

        for (int i = 1; i <= 2; i++) {
            int chosenStrategy = random.nextInt(1, MAX_STRATEGIES + 1);
            switch (chosenStrategy) {
                case 0:
                    chosenCollisionStrategy = new RemoveBrickStrategy(gameObjectCollection, bricksLeft);
                    break;

                case 1:
                    chosenCollisionStrategy = new MockBallsStrategy(gameObjectCollection, bricksLeft, mockBallImage, windowController, collisionSound, ballSpeed, collisions, direction, ballCollisionsForCamera, normalMode, ballFirstCollision);
                    break;

                case 2:
                    chosenCollisionStrategy = new UserPaddleStrategy(gameObjectCollection, bricksLeft, paddleImage, windowController, inputListener, collisionSound, bricksTotalHeight, paddleSizeBuffChange, paddle);
                    break;

                case 3:
                    chosenCollisionStrategy = new GravityStrategy(gameObjectCollection, bricksLeft, gravityImage, windowController, collisionSound, collisions, direction);
                    break;

                case 4:
                    chosenCollisionStrategy = new MotionStrategy(gameObjectCollection, bricksLeft, windowController, slowMotionImage, quickenMotionImage, slowOrQuicken);
                    break;

                case 5:
                    chosenCollisionStrategy = new WidenNarrowStrategy(gameObjectCollection, bricksLeft, windowController, widen, narrow, paddleSizeBuffChange, paddle);
                    break;

                case 6:
                    chosenCollisionStrategy = new BotStrategy(gameObjectCollection, bricksLeft, windowController, bricksTotalHeight, goodBotImage, badBotImage, ball);
                    break;

                case 7:
                    chosenCollisionStrategy = doubleStrategy();
                    break;
                case 8:
                    chosenCollisionStrategy = doubleStrategy();
                    break;

            }
            if (i == 1)
            {
                collisionStrategy1 = chosenCollisionStrategy;
            }
            else
                collisionStrategy2 = chosenCollisionStrategy;
        }
        collisionStrategy = new DoubleStrategy(gameObjectCollection, collisionStrategy1, collisionStrategy2, bricksLeft);
        return collisionStrategy;

    }

}
